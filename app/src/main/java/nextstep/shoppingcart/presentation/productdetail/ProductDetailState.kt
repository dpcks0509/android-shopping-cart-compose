package nextstep.shoppingcart.presentation.productdetail

import nextstep.shoppingcart.domain.model.Product

data class ProductDetailState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
