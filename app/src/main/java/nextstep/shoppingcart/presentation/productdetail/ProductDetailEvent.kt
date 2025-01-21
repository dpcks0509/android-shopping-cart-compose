package nextstep.shoppingcart.presentation.productdetail

import nextstep.shoppingcart.domain.model.Product

sealed interface ProductDetailEvent {
    data class AddProduct(val product: Product) : ProductDetailEvent
}
