package nextstep.shoppingcart.domain.usecase.product

import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.repository.ProductRepository

class GetProducts(
    private val repository: ProductRepository
) {

    operator fun invoke(): Result<List<Product>> {
        return repository.getProducts()
    }
}