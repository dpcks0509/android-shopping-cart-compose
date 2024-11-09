package nextstep.shoppingcart.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Long,
    val imageUrl: String,
    val name: String,
    val price: Int,
) : Parcelable
