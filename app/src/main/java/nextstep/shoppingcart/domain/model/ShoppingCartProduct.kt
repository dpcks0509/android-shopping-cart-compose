package nextstep.shoppingcart.domain.model

data class ShoppingCartProduct(
    val id: Long,
    val product: Product,
    val quantity: Int,
) {
    val totalPrice: Int get() = product.price * quantity

    fun plusQuantity(): ShoppingCartProduct {
        return copy(quantity = quantity + OFFSET_QUANTITY)
    }

    fun minusQuantity(): ShoppingCartProduct {
        return copy(quantity = quantity - OFFSET_QUANTITY)
    }

    companion object {
        private const val OFFSET_QUANTITY = 1
    }
}
