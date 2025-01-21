package nextstep.shoppingcart.presentation.shoppingcart

import nextstep.shoppingcart.domain.model.Product

sealed interface ShoppingCartEvent {
    data class AddProduct(val product: Product) : ShoppingCartEvent

    data class DecreaseProductQuantity(val product: Product) : ShoppingCartEvent

    data class RemoveProduct(val product: Product) : ShoppingCartEvent

    data object ClearProducts : ShoppingCartEvent
}
