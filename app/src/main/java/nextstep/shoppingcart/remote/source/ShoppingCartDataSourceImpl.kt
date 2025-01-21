package nextstep.shoppingcart.remote.source

import nextstep.shoppingcart.data.source.ShoppingCartDataSource
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ShoppingCartProduct
import javax.inject.Inject

class ShoppingCartDataSourceImpl @Inject constructor() : ShoppingCartDataSource {
    private val shoppingCartProducts = mutableListOf<ShoppingCartProduct>()
    private var currentId: Long = 0L

    override fun getShoppingCartProducts(): Result<List<ShoppingCartProduct>> {
        return try {
            Result.success(shoppingCartProducts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getQuantityByProduct(product: Product): Result<Int> {
        return try {
            Result.success(
                shoppingCartProducts.find { shoppingCartProduct -> shoppingCartProduct.product == product }?.quantity
                    ?: 0
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun addProduct(product: Product): Result<Unit> {
        return try {
            val shoppingCartProduct =
                shoppingCartProducts.find { shoppingCartProduct ->
                    shoppingCartProduct.product == product
                }

            if (shoppingCartProduct == null) {
                shoppingCartProducts.add(
                    ShoppingCartProduct(
                        id = currentId++,
                        product = product,
                        quantity = 1,
                    ),
                )
            } else {
                val index = shoppingCartProducts.indexOf(shoppingCartProduct)
                shoppingCartProducts[index] = shoppingCartProduct.plusQuantity()
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun decreaseProductQuantity(product: Product): Result<Unit> {
        return try {
            val shoppingCartProduct =
                shoppingCartProducts.find { shoppingCartProduct ->
                    shoppingCartProduct.product == product
                }

            if (shoppingCartProduct != null) {
                val index = shoppingCartProducts.indexOf(shoppingCartProduct)
                shoppingCartProducts[index] = shoppingCartProduct.minusQuantity()
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun removeProduct(product: Product): Result<Unit> {
        return try {
            shoppingCartProducts.removeIf { shoppingCartProduct ->
                shoppingCartProduct.product == product
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun clearProducts(): Result<Unit> {
        return try {
            shoppingCartProducts.clear()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
