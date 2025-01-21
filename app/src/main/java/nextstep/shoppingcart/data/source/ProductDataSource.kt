package nextstep.shoppingcart.data.source

import nextstep.shoppingcart.domain.model.Product

interface ProductDataSource {
    fun getProducts(): Result<List<Product>>

    fun getProductById(productId: Long): Result<Product?>
}