package nextstep.shoppingcart.presentation.productlist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ProductUiModel
import nextstep.shoppingcart.domain.usecase.product.ProductUseCase
import nextstep.shoppingcart.domain.usecase.shoppingcart.ShoppingCartUseCase
import nextstep.shoppingcart.presentation.productlist.ProductListEvent.AddProduct
import nextstep.shoppingcart.presentation.productlist.ProductListEvent.DecreaseProductQuantity
import nextstep.shoppingcart.presentation.util.Screen
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel
    @Inject
    constructor(
        private val productUseCase: ProductUseCase,
        private val shoppingCartUseCase: ShoppingCartUseCase,
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val _state: MutableStateFlow<ProductListState> =
            MutableStateFlow(ProductListState(snackbarMessage = savedStateHandle.toRoute<Screen.ProductListScreen>().snackbarMessage))
        val state: StateFlow<ProductListState> =
            _state.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ProductListState(isLoading = true),
            )

        fun onEvent(event: ProductListEvent) {
            when (event) {
                is AddProduct -> addProduct(event.product)
                is DecreaseProductQuantity -> decreaseProductQuantity(event.product)
            }
        }

        fun loadProducts() {
            viewModelScope.launch {
                productUseCase.getProducts().fold(
                    onSuccess = { products ->
                        val productUiModels =
                            products.map { product ->
                                ProductUiModel(
                                    product = product,
                                    quantity =
                                        shoppingCartUseCase.getQuantityByProduct(product)
                                            .getOrDefault(0),
                                )
                            }

                        _state.update { currentState ->
                            currentState.copy(
                                products = productUiModels.toMutableStateList(),
                                isLoading = false,
                                error = null,
                            )
                        }
                    },
                    onFailure = { error ->
                        _state.update { currentState ->
                            currentState.copy(
                                products = mutableStateListOf(),
                                isLoading = false,
                                error = error.message,
                            )
                        }
                    },
                )
            }
        }

        fun clearSnackbarMessage() {
            _state.update { currentState ->
                currentState.copy(snackbarMessage = null)
            }
        }

        private fun addProduct(product: Product) {
            shoppingCartUseCase.addProduct(product)

            _state.update { currentState ->
                val updatedProducts =
                    currentState.products.map { productItem ->
                        if (productItem.product.id == product.id) {
                            productItem.copy(quantity = productItem.quantity + 1)
                        } else {
                            productItem
                        }
                    }

                currentState.copy(products = updatedProducts.toMutableStateList())
            }
        }

        private fun decreaseProductQuantity(product: Product) {
            shoppingCartUseCase.decreaseProductQuantity(product)

            _state.update { currentState ->
                val updatedProducts =
                    currentState.products.map { productItem ->
                        if (productItem.product.id == product.id) {
                            if (productItem.quantity > 1) {
                                productItem.copy(quantity = productItem.quantity - 1)
                            } else {
                                shoppingCartUseCase.removeProduct(product)
                                productItem.copy(quantity = 0)
                            }
                        } else {
                            productItem
                        }
                    }

                currentState.copy(products = updatedProducts.toMutableStateList())
            }
        }
    }
