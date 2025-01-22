package nextstep.shoppingcart.presentation.shoppingcart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import nextstep.shoppingcart.domain.model.ShoppingCartProduct

data class ShoppingCartState(
    val shoppingCartProducts: SnapshotStateList<ShoppingCartProduct> = mutableStateListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val totalPrice: Int
        get() = shoppingCartProducts.sumOf { shoppingCartProduct ->
            shoppingCartProduct.totalPrice
        }
}
