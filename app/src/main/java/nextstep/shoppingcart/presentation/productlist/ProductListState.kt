package nextstep.shoppingcart.presentation.productlist

import nextstep.shoppingcart.domain.model.ProductUiModel

data class ProductListState(
    val products: List<ProductUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
