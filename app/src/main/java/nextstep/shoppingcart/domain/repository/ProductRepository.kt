package nextstep.shoppingcart.domain.repository

import nextstep.shoppingcart.domain.model.Product

interface ProductRepository {
    fun getProducts(): Result<List<Product>>

    fun getProductById(productId: Long): Result<Product?>
}
