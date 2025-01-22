package nextstep.shoppingcart.presentation.productlist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import nextstep.shoppingcart.domain.model.ProductUiModel

data class ProductListState(
    val products: SnapshotStateList<ProductUiModel> = mutableStateListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)