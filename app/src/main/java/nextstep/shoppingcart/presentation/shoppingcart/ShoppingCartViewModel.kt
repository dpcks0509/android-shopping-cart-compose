package nextstep.shoppingcart.presentation.shoppingcart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.usecase.shoppingcart.ShoppingCartUseCase
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val shoppingCartUseCase: ShoppingCartUseCase
) : ViewModel() {
    var state by mutableStateOf(ShoppingCartState())
        private set

    init {
        loadShoppingCartProducts()
    }

    fun onEvent(event: ShoppingCartEvent) {
        when (event) {
            is ShoppingCartEvent.AddProduct -> addProduct(event.product)
            is ShoppingCartEvent.DecreaseProductQuantity -> decreaseProductQuantity(event.product)
            is ShoppingCartEvent.RemoveProduct -> removeProduct(event.product)
            is ShoppingCartEvent.ClearProducts -> clearProducts()
        }
    }

    private fun loadShoppingCartProducts() {
        shoppingCartUseCase.getShoppingCartProducts().fold(
            onSuccess = { shoppingCartProducts ->
                state = state.copy(
                    shoppingCartProducts = shoppingCartProducts.toList(),
                    totalPrice = shoppingCartProducts.sumOf { shoppingCartProduct ->
                        shoppingCartProduct.totalPrice
                    },
                    isLoading = false,
                    error = null
                )
            },
            onFailure = { error ->
                state = state.copy(
                    shoppingCartProducts = emptyList(),
                    totalPrice = 0,
                    isLoading = false,
                    error = error.message
                )
            }
        )
    }

    private fun addProduct(product: Product) {
        shoppingCartUseCase.addProduct(product)
        addCartQuantity(productId = product.id)
    }

    private fun decreaseProductQuantity(product: Product) {
        shoppingCartUseCase.decreaseProductQuantity(product)
        decreaseCartQuantity(productId = product.id)
    }

    private fun removeProduct(product: Product) {
        shoppingCartUseCase.removeProduct(product)
        val updatedShoppingCartProducts =
            state.shoppingCartProducts.filterNot { shoppingCartProduct ->
                shoppingCartProduct.product.id == product.id
            }
        state = state.copy(
            shoppingCartProducts = updatedShoppingCartProducts,
            totalPrice = updatedShoppingCartProducts.sumOf { shoppingCartProduct ->
                shoppingCartProduct.totalPrice
            })
    }

    private fun clearProducts() {
        shoppingCartUseCase.clearProducts()
        state = state.copy(shoppingCartProducts = emptyList(), totalPrice = 0)
    }

    private fun addCartQuantity(productId: Long) {
        val updatedProducts = state.shoppingCartProducts.map { shoppingCartProduct ->
            if (shoppingCartProduct.product.id == productId) {
                shoppingCartProduct.copy(quantity = shoppingCartProduct.quantity + 1)
            } else {
                shoppingCartProduct
            }
        }
        state = state.copy(
            shoppingCartProducts = updatedProducts,
            totalPrice = updatedProducts.sumOf { shoppingCartProduct ->
                shoppingCartProduct.totalPrice
            })
    }


    private fun decreaseCartQuantity(productId: Long) {
        val cartProduct = state.shoppingCartProducts.find { shoppingCartProduct ->
            shoppingCartProduct.product.id == productId
        } ?: return
        if (cartProduct.quantity == 1) {
            removeProduct(product = cartProduct.product)
        }

        val updatedProducts = state.shoppingCartProducts.map { shoppingCartProduct ->
            if (shoppingCartProduct.product.id == productId) {
                shoppingCartProduct.copy(quantity = shoppingCartProduct.quantity - 1)
            } else {
                shoppingCartProduct
            }
        }
        state = state.copy(
            shoppingCartProducts = updatedProducts,
            totalPrice = updatedProducts.sumOf { shoppingCartProduct ->
                shoppingCartProduct.totalPrice
            })
    }
}
