package nextstep.shoppingcart.presentation.productlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ProductUiModel
import nextstep.shoppingcart.domain.usecase.product.ProductUseCase
import nextstep.shoppingcart.domain.usecase.shoppingcart.ShoppingCartUseCase
import nextstep.shoppingcart.presentation.productlist.ProductListEvent.AddProduct
import nextstep.shoppingcart.presentation.productlist.ProductListEvent.DecreaseProductQuantity
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val shoppingCartUseCase: ShoppingCartUseCase
) : ViewModel() {
    var state by mutableStateOf(ProductListState())
        private set

    init {
        loadProducts()
    }

    fun onEvent(event: ProductListEvent) {
        when (event) {
            is AddProduct -> addProduct(event.product)
            is DecreaseProductQuantity -> decreaseProductQuantity(event.product)
        }
    }

    fun loadProducts() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )

            productUseCase.getProducts().fold(
                onSuccess = { products ->
                    val productUiModels = products.map { product ->
                        ProductUiModel(
                            product = product,
                            quantity = shoppingCartUseCase.getQuantityByProduct(product)
                                .getOrDefault(0)
                        )
                    }

                    state = state.copy(
                        products = productUiModels.toList(),
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    state = state.copy(
                        products = emptyList(),
                        isLoading = false,
                        error = error.message
                    )
                }
            )
        }
    }

    private fun addProduct(product: Product) {
        shoppingCartUseCase.addProduct(product)
        updateProductQuantity(productId = product.id, value = 1)
    }

    private fun decreaseProductQuantity(product: Product) {
        shoppingCartUseCase.decreaseProductQuantity(product)
        updateProductQuantity(productId = product.id, value = -1)
    }

    private fun updateProductQuantity(productId: Long, value: Int) {
        val updatedProducts = state.products.map { productUiModel ->
            if (productUiModel.product.id == productId) {
                productUiModel.copy(quantity = productUiModel.quantity + value)
            } else {
                productUiModel
            }
        }
        state = state.copy(products = updatedProducts)
    }
}
