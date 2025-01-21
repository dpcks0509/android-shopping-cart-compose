package nextstep.shoppingcart.presentation.shoppingcart

import nextstep.shoppingcart.domain.model.ShoppingCartProduct

data class ShoppingCartState(
    val shoppingCartProducts: List<ShoppingCartProduct> = emptyList(),
    val totalPrice: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)
