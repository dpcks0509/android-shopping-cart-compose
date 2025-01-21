package nextstep.shoppingcart.presentation.productlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ProductUiModel
import nextstep.shoppingcart.presentation.component.PlusButton
import nextstep.shoppingcart.presentation.component.ProductImage
import nextstep.shoppingcart.presentation.component.QuantityControlButton
import nextstep.shoppingcart.presentation.productlist.ProductListEvent.AddProduct
import nextstep.shoppingcart.presentation.productlist.ProductListEvent.DecreaseProductQuantity
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun ProductItem(
    item: ProductUiModel,
    event: (ProductListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Box {
            ProductImage(
                product = item.product,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,
            )

            if (item.quantity > 0) {
                QuantityControlButton(
                    quantity = item.quantity,
                    minusQuantity = { event(DecreaseProductQuantity(product = item.product)) },
                    plusQuantity = { event(AddProduct(product = item.product)) },
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White)
                        .align(Alignment.BottomCenter),
                )
            } else {
                PlusButton(
                    onClick = { event(AddProduct(product = item.product)) },
                    modifier =
                    Modifier
                        .padding(12.dp)
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .align(Alignment.BottomEnd),
                )
            }
        }

        Text(
            text = item.product.name,
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = stringResource(R.string.price_format, item.product.price),
            modifier = Modifier.padding(horizontal = 4.dp),
            fontSize = 16.sp,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ProductItemPreview(
    @PreviewParameter(ProductItemPreviewParameterProvider::class) item: ProductUiModel,
) {
    ShoppingCartTheme {
        ProductItem(item = item, event = {})
    }
}

class ProductItemPreviewParameterProvider : PreviewParameterProvider<ProductUiModel> {
    override val values: Sequence<ProductUiModel> =
        sequenceOf(
            ProductUiModel(
                Product(
                    id = 0L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                            "%2FMjAyNDAyMjNfMjkg%2FMDAxNzA4NjE1NTg1ODg5.ZFPHZ3Q2HzH7GcYA1_Jl0lsIdvAnzUF2h6Qd6bgDLHkg." +
                            "_7ffkgE45HXRVgX2Bywc3B320_tuatBww5y1hS4xjWQg.JPEG%2FIMG_5278.jpg&type=sc960_832",
                    name = "대전 장인약과",
                    price = 12000,
                ),
                quantity = 0,
            ),
            ProductUiModel(
                Product(
                    id = 0L,
                    imageUrl =
                    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                            "%2FMjAyNDAyMjNfMjkg%2FMDAxNzA4NjE1NTg1ODg5.ZFPHZ3Q2HzH7GcYA1_Jl0lsIdvAnzUF2h6Qd6bgDLHkg." +
                            "_7ffkgE45HXRVgX2Bywc3B320_tuatBww5y1hS4xjWQg.JPEG%2FIMG_5278.jpg&type=sc960_832",
                    name = "대전 장인약과",
                    price = 12000,
                ),
                quantity = 2,
            ),
        )
}
