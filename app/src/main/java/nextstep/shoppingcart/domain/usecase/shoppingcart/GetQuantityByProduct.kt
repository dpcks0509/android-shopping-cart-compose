package nextstep.shoppingcart.domain.usecase.shoppingcart

import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.repository.ShoppingCartRepository

class GetQuantityByProduct(
    private val repository: ShoppingCartRepository
) {
    operator fun invoke(product: Product): Result<Int> {
        return repository.getQuantityByProduct(product)
    }
}
