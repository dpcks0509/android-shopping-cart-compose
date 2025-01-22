package nextstep.shoppingcart.presentation.shoppingcart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
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

    fun onEvent(event: ShoppingCartEvent) {
        when (event) {
            is ShoppingCartEvent.AddProduct -> addProduct(event.product)
            is ShoppingCartEvent.DecreaseProductQuantity -> decreaseProductQuantity(event.product)
            is ShoppingCartEvent.RemoveProduct -> removeProduct(event.product)
            is ShoppingCartEvent.ClearProducts -> clearProducts()
        }
    }

    fun loadShoppingCartProducts() {
        shoppingCartUseCase.getShoppingCartProducts().fold(onSuccess = { shoppingCartProducts ->
            state = state.copy(
                shoppingCartProducts = shoppingCartProducts.toMutableStateList(),
                isLoading = false,
                error = null
            )
        }, onFailure = { error ->
            state = state.copy(
                shoppingCartProducts = mutableStateListOf(),
                isLoading = false,
                error = error.message
            )
        })
    }

    private fun addProduct(product: Product) {
        shoppingCartUseCase.addProduct(product)
        val index = state.shoppingCartProducts.indexOfFirst { shoppingCartProduct ->
            shoppingCartProduct.product.id == product.id
        }
        state.shoppingCartProducts[index] =
            state.shoppingCartProducts[index].copy(quantity = state.shoppingCartProducts[index].quantity + 1)
    }

    private fun decreaseProductQuantity(product: Product) {
        shoppingCartUseCase.decreaseProductQuantity(product)
        val index = state.shoppingCartProducts.indexOfFirst { shoppingCartProduct ->
            shoppingCartProduct.product.id == product.id
        }
        if (state.shoppingCartProducts[index].quantity != 1) {
            state.shoppingCartProducts[index] =
                state.shoppingCartProducts[index].copy(quantity = state.shoppingCartProducts[index].quantity - 1)
        } else {
            state.shoppingCartProducts.removeAt(index)
        }
    }

    private fun removeProduct(product: Product) {
        shoppingCartUseCase.removeProduct(product)
        val index = state.shoppingCartProducts.indexOfFirst { shoppingCartProduct ->
            shoppingCartProduct.product.id == product.id
        }
        state.shoppingCartProducts.removeAt(index)
    }

    private fun clearProducts() {
        shoppingCartUseCase.clearProducts()
        state.shoppingCartProducts.clear()
    }
}
