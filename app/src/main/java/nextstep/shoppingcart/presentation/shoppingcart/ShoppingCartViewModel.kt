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
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.usecase.shoppingcart.ShoppingCartUseCase
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val shoppingCartUseCase: ShoppingCartUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<ShoppingCartState> =
        MutableStateFlow(ShoppingCartState())
    val state: StateFlow<ShoppingCartState> = _state
        .onStart {
            loadShoppingCartProducts()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ShoppingCartState(isLoading = true)
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
            _state.value = _state.value.copy(
                shoppingCartProducts = shoppingCartProducts.toMutableStateList(),
                isLoading = false,
                error = null
            )
        }, onFailure = { error ->
            _state.value = _state.value.copy(
                shoppingCartProducts = mutableStateListOf(),
                isLoading = false,
                error = error.message
            )
        })
    }

    private fun addProduct(product: Product) {
        shoppingCartUseCase.addProduct(product)
        val index = _state.value.shoppingCartProducts.indexOfFirst { shoppingCartProduct ->
            shoppingCartProduct.product.id == product.id
        }
        _state.value.shoppingCartProducts[index] =
            _state.value.shoppingCartProducts[index].copy(quantity = _state.value.shoppingCartProducts[index].quantity + 1)
    }

    private fun decreaseProductQuantity(product: Product) {
        shoppingCartUseCase.decreaseProductQuantity(product)
        val index = _state.value.shoppingCartProducts.indexOfFirst { shoppingCartProduct ->
            shoppingCartProduct.product.id == product.id
        }
        if (_state.value.shoppingCartProducts[index].quantity != 1) {
            _state.value.shoppingCartProducts[index] =
                _state.value.shoppingCartProducts[index].copy(quantity = _state.value.shoppingCartProducts[index].quantity - 1)
        } else {
            shoppingCartUseCase.removeProduct(product)
            _state.value.shoppingCartProducts.removeAt(index)
        }
    }

    private fun removeProduct(product: Product) {
        shoppingCartUseCase.removeProduct(product)
        val index = _state.value.shoppingCartProducts.indexOfFirst { shoppingCartProduct ->
            shoppingCartProduct.product.id == product.id
        }
        _state.value.shoppingCartProducts.removeAt(index)
    }

    private fun clearProducts() {
        shoppingCartUseCase.clearProducts()
        _state.value.shoppingCartProducts.clear()
    }
}
