package nextstep.shoppingcart.domain.usecase.shoppingcart

import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.repository.ShoppingCartRepository

class AddProduct(
    private val repository: ShoppingCartRepository
) {
    operator fun invoke(product: Product): Result<Unit> {
        return repository.addProduct(product)
    }
}