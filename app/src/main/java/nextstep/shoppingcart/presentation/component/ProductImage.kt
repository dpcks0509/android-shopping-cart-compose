package nextstep.shoppingcart.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.SubcomposeAsyncImage
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun ProductImage(
    product: Product,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
) {
    ProductImageContent(
        product = product,
        modifier = modifier,
        contentScale = contentScale
    )
}

@Composable
private fun ProductImageContent(
    product: Product,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
) {
    SubcomposeAsyncImage(
        model = product.imageUrl,
        contentDescription = product.name,
        modifier = modifier,
        contentScale = contentScale,
        error = {
            if (LocalInspectionMode.current) {
                Image(
                    painter = painterResource(id = R.drawable.sample_product_image),
                    contentDescription = null,
                    modifier = modifier,
                    contentScale = contentScale
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun ProductImagePreview() {
    ShoppingCartTheme {
        ProductImageContent(
            product =
            Product(
                id = 0L,
                imageUrl =
                "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                        "%2FMjAyNDAyMjNfMjkg%2FMDAxNzA4NjE1NTg1ODg5.ZFPHZ3Q2HzH7GcYA1_Jl0lsIdvAnzUF2h6Qd6bgDLHkg." +
                        "_7ffkgE45HXRVgX2Bywc3B320_tuatBww5y1hS4xjWQg.JPEG%2FIMG_5278.jpg&type=sc960_832",
                name = "대전 장인약과",
                price = 12000,
            ),
            contentScale = ContentScale.Crop,
        )
    }
}
