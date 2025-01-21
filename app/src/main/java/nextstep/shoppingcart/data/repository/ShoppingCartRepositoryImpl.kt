package nextstep.shoppingcart.data.repository

import nextstep.shoppingcart.data.source.ShoppingCartDataSource
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ShoppingCartProduct
import nextstep.shoppingcart.domain.repository.ShoppingCartRepository
import javax.inject.Inject

class ShoppingCartRepositoryImpl @Inject constructor(
    private val source: ShoppingCartDataSource
) : ShoppingCartRepository {

    override fun getShoppingCartProducts(): Result<List<ShoppingCartProduct>> {
        return source.getShoppingCartProducts()
    }

    override fun getQuantityByProduct(product: Product): Result<Int> {
        return source.getQuantityByProduct(product)
    }

    override fun addProduct(product: Product): Result<Unit> {
        return source.addProduct(product)
    }

    override fun decreaseProductQuantity(product: Product): Result<Unit> {
        return source.decreaseProductQuantity(product)
    }

    override fun removeProduct(product: Product): Result<Unit> {
        return source.removeProduct(product)
    }

    override fun clearProducts(): Result<Unit> {
        return source.clearProducts()
    }
}
