package nextstep.shoppingcart.data.repository

import nextstep.shoppingcart.data.source.ProductDataSource
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val source: ProductDataSource
) : ProductRepository {
    override fun getProducts(): Result<List<Product>> {
        return source.getProducts()
    }

    override fun getProductById(productId: Long): Result<Product?> {
        return source.getProductById(productId)
    }
}
