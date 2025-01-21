package nextstep.shoppingcart.presentation.shoppingcart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ShoppingCartProduct
import nextstep.shoppingcart.presentation.component.ProductImage
import nextstep.shoppingcart.presentation.component.QuantityControlButton
import nextstep.shoppingcart.presentation.shoppingcart.ShoppingCartEvent.AddProduct
import nextstep.shoppingcart.presentation.shoppingcart.ShoppingCartEvent.DecreaseProductQuantity
import nextstep.shoppingcart.presentation.shoppingcart.ShoppingCartEvent.RemoveProduct
import nextstep.shoppingcart.presentation.ui.theme.Gray10
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun ShoppingCartItem(
    shoppingCartProduct: ShoppingCartProduct,
    onEvent: (ShoppingCartEvent) -> Unit,
) {
    OutlinedCard(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 16.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Gray10),
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = shoppingCartProduct.product.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )

            IconButton(
                onClick = {
                    onEvent(RemoveProduct(product = shoppingCartProduct.product))
                },
                modifier = Modifier.size(24.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = stringResource(R.string.remove_shopping_cart_item),
                )
            }
        }

        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    start = 18.dp,
                    end = 18.dp,
                    top = 6.dp,
                    bottom = 18.dp,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ProductImage(
                product = shoppingCartProduct.product,
                modifier = Modifier.size(width = 136.dp, height = 84.dp),
                contentScale = ContentScale.FillWidth,
            )

            Column(modifier = Modifier.align(Alignment.Bottom)) {
                Text(
                    text = stringResource(R.string.price_format, shoppingCartProduct.totalPrice),
                    modifier =
                    Modifier
                        .align(Alignment.End),
                    fontSize = 16.sp,
                )

                QuantityControlButton(
                    quantity = shoppingCartProduct.quantity,
                    minusQuantity = { onEvent(DecreaseProductQuantity(product = shoppingCartProduct.product)) },
                    plusQuantity = { onEvent(AddProduct(product = shoppingCartProduct.product)) },
                    modifier = Modifier.size(width = 126.dp, height = 42.dp),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ShoppingCartItemPreview() {
    ShoppingCartTheme {
        ShoppingCartItem(
            shoppingCartProduct =
            ShoppingCartProduct(
                id = 0L,
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
            onEvent = {},
        )
    }
}
