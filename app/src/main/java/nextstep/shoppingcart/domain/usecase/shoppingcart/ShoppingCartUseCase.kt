package nextstep.shoppingcart.domain.usecase.shoppingcart

data class ShoppingCartUseCase(
    val getShoppingCartProducts: GetShoppingCartProducts,
    val getQuantityByProduct: GetQuantityByProduct,
    val addProduct: AddProduct,
    val decreaseProductQuantity: DecreaseProductQuantity,
    val removeProduct: RemoveProduct,
    val clearProducts: ClearProducts
)
