package nextstep.shoppingcart.presentation.mapper

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ProductUiModel

fun List<Product>.toUi(): SnapshotStateList<ProductUiModel> {
    val uiList = mutableStateListOf<ProductUiModel>()
    this.forEach { product ->
        uiList.add(ProductUiModel(product = product, quantity = 0))
    }
    return uiList
}
