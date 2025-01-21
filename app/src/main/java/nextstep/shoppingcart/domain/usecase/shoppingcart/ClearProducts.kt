package nextstep.shoppingcart.domain.usecase.shoppingcart

import nextstep.shoppingcart.domain.repository.ShoppingCartRepository

class ClearProducts(
    private val repository: ShoppingCartRepository
) {
    operator fun invoke(): Result<Unit> {
        return repository.clearProducts()
    }
}