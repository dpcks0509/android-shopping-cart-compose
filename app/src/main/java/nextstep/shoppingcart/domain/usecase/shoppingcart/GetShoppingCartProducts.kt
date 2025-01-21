package nextstep.shoppingcart.domain.usecase.shoppingcart

import nextstep.shoppingcart.domain.model.ShoppingCartProduct
import nextstep.shoppingcart.domain.repository.ShoppingCartRepository

class GetShoppingCartProducts(
    private val repository: ShoppingCartRepository
) {
    operator fun invoke(): Result<List<ShoppingCartProduct>> {
        return repository.getShoppingCartProducts()
    }
}
