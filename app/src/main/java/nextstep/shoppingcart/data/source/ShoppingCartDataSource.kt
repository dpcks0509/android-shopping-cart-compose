package nextstep.shoppingcart.data.source

import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ShoppingCartProduct

interface ShoppingCartDataSource {
    fun getShoppingCartProducts(): Result<List<ShoppingCartProduct>>

    fun getQuantityByProduct(product: Product): Result<Int>

    fun addProduct(product: Product): Result<Unit>

    fun decreaseProductQuantity(product: Product): Result<Unit>

    fun removeProduct(product: Product): Result<Unit>

    fun clearProducts(): Result<Unit>
}
