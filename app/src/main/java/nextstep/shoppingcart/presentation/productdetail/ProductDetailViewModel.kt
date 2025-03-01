package nextstep.shoppingcart.presentation.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.usecase.product.ProductUseCase
import nextstep.shoppingcart.domain.usecase.shoppingcart.ShoppingCartUseCase
import nextstep.shoppingcart.presentation.util.Screen
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel
    @Inject
    constructor(
        private val productUseCase: ProductUseCase,
        private val shoppingCartUseCase: ShoppingCartUseCase,
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        val productId = savedStateHandle.toRoute<Screen.ProductDetailScreen>().productId

        private val _state: MutableStateFlow<ProductDetailState> =
            MutableStateFlow(ProductDetailState())
        val state: StateFlow<ProductDetailState> =
            _state
                .onStart {
                    loadProduct(productId)
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = ProductDetailState(isLoading = true),
                )

        fun onEvent(event: ProductDetailEvent) {
            when (event) {
                is ProductDetailEvent.AddProduct -> {
                    addProduct(product = event.product)
                }
            }
        }

        private fun loadProduct(productId: Long) {
            viewModelScope.launch {
                productUseCase.getProduct(productId = productId).fold(
                    onSuccess = { product ->
                        _state.value =
                            _state.value.copy(
                                product = product,
                                isLoading = false,
                                error = null,
                            )
                    },
                    onFailure = {
                        _state.value =
                            _state.value.copy(
                                product = null,
                                isLoading = false,
                                error = null,
                            )
                    },
                )
            }
        }

        private fun addProduct(product: Product) {
            shoppingCartUseCase.addProduct(product = product)
            _state.value = _state.value.copy(product = product)
        }
    }
