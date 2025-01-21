package nextstep.shoppingcart.presentation.productlist

import nextstep.shoppingcart.domain.model.Product

sealed interface ProductListEvent {
    data class AddProduct(val product: Product) : ProductListEvent

    data class DecreaseProductQuantity(val product: Product) : ProductListEvent
}
