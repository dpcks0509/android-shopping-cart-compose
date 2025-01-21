package nextstep.shoppingcart.domain.usecase.product

import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.repository.ProductRepository

class GetProduct(
    private val repository: ProductRepository
) {

    operator fun invoke(productId: Long): Result<Product?> {
        return repository.getProductById(productId)
    }
}