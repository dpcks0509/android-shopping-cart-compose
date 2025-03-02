package nextstep.shoppingcart.presentation.shoppingcart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.usecase.shoppingcart.ShoppingCartUseCase
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel
@Inject
constructor(
    private val shoppingCartUseCase: ShoppingCartUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<ShoppingCartState> =
        MutableStateFlow(ShoppingCartState())
    val state: StateFlow<ShoppingCartState> =
        _state
            .onStart {
                loadShoppingCartProducts()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ShoppingCartState(isLoading = true),
            )

    fun onEvent(event: ShoppingCartEvent) {
        when (event) {
            is ShoppingCartEvent.AddProduct -> addProduct(event.product)
            is ShoppingCartEvent.DecreaseProductQuantity -> decreaseProductQuantity(event.product)
            is ShoppingCartEvent.RemoveProduct -> removeProduct(event.product)
            is ShoppingCartEvent.ClearProducts -> clearProducts()
        }
    }

    private fun loadShoppingCartProducts() {
        shoppingCartUseCase.getShoppingCartProducts().fold(onSuccess = { shoppingCartProducts ->
            _state.update { currentState ->
                currentState.copy(
                    shoppingCartProducts = shoppingCartProducts.toMutableStateList(),
                    isLoading = false,
                    error = null,
                )
            }
        }, onFailure = { error ->
            _state.update { currentState ->
                currentState.copy(
                    shoppingCartProducts = mutableStateListOf(),
                    isLoading = false,
                    error = error.message,
                )
            }
        })
    }

    private fun addProduct(product: Product) {
        shoppingCartUseCase.addProduct(product)

        _state.update { currentState ->
            val updatedProducts = currentState.shoppingCartProducts.map { shoppingCartProduct ->
                if (shoppingCartProduct.product.id == product.id) {
                    shoppingCartProduct.copy(quantity = shoppingCartProduct.quantity + 1)
                } else {
                    shoppingCartProduct
                }
            }

            currentState.copy(shoppingCartProducts = updatedProducts.toMutableStateList())
        }
    }

    private fun decreaseProductQuantity(product: Product) {
        shoppingCartUseCase.decreaseProductQuantity(product)

        _state.update { currentState ->
            val updatedProducts =
                currentState.shoppingCartProducts.mapNotNull { shoppingCartProduct ->
                    if (shoppingCartProduct.product.id == product.id) {
                        if (shoppingCartProduct.quantity > 1) {
                            shoppingCartProduct.copy(quantity = shoppingCartProduct.quantity - 1)
                        } else {
                            shoppingCartUseCase.removeProduct(product)
                            null
                        }
                    } else {
                        shoppingCartProduct
                    }
                }

            currentState.copy(shoppingCartProducts = updatedProducts.toMutableStateList())
        }
    }

    private fun removeProduct(product: Product) {
        shoppingCartUseCase.removeProduct(product)

        _state.update { currentState ->
            val updatedProducts =
                currentState.shoppingCartProducts.filterNot { shoppingCartProduct ->
                    shoppingCartProduct.product.id == product.id
                }

            currentState.copy(shoppingCartProducts = updatedProducts.toMutableStateList())
        }
    }

    private fun clearProducts() {
        shoppingCartUseCase.clearProducts()

        _state.update { currentState ->
            currentState.copy(shoppingCartProducts = mutableStateListOf())
        }
    }
}
