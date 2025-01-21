package nextstep.shoppingcart.presentation.productdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.usecase.product.ProductUseCase
import nextstep.shoppingcart.domain.usecase.shoppingcart.ShoppingCartUseCase
import nextstep.shoppingcart.presentation.util.Screen.ProductDetailScreen
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val shoppingCartUseCase: ShoppingCartUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(ProductDetailState())
        private set

    private val productId = savedStateHandle.toRoute<ProductDetailScreen>().productId

    init {
        loadProduct(productId = productId)
    }

    fun onEvent(event: ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.AddProduct -> {
                addProduct(product = event.product)
            }
        }
    }

    private fun loadProduct(productId: Long) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )

            productUseCase.getProduct(productId = productId).fold(
                onSuccess = { product ->
                    state = state.copy(
                        product = product,
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = {
                    state = state.copy(
                        product = null,
                        isLoading = false,
                        error = null
                    )
                }
            )
        }
    }

    private fun addProduct(product: Product) {
        shoppingCartUseCase.addProduct(product = product)
        state = state.copy(product = product)
    }
}