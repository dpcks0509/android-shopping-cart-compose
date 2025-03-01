package nextstep.shoppingcart.presentation.productlist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val _state: MutableStateFlow<ProductListState> = MutableStateFlow(ProductListState())
    val state: StateFlow<ProductListState> = _state.asStateFlow()

    init {
        loadProducts()
    }

    fun onEvent(event: ProductListEvent) {
        when (event) {
            is AddProduct -> addProduct(event.product)
            is DecreaseProductQuantity -> decreaseProductQuantity(event.product)
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
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

                    _state.value = _state.value.copy(
                        products = productUiModels.toMutableStateList(),
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { error ->
                    _state.value = _state.value.copy(
                        products = mutableStateListOf(),
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
        val index = _state.value.products.indexOfFirst { productItem ->
            productItem.product.id == productId
        }
        if (index != -1) {
            _state.value.products[index] =
                _state.value.products[index].copy(quantity = _state.value.products[index].quantity + value)
        }
    }
}
