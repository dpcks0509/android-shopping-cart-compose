package nextstep.shoppingcart.presentation.util

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data class ProductListScreen(
        val snackbarMessage: String? = null
    ) : Screen

    @Serializable
    data class ProductDetailScreen(
        val productId: Long
    ) : Screen

    @Serializable
    data object ShoppingCartScreen : Screen
}