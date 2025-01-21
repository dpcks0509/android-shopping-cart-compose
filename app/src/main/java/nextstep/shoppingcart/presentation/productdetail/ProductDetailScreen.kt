package nextstep.shoppingcart.presentation.productdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.presentation.component.BackNavigationTopAppBar
import nextstep.shoppingcart.presentation.component.ProductImage
import nextstep.shoppingcart.presentation.component.RectangleButton
import nextstep.shoppingcart.presentation.ui.theme.Gray10
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.presentation.util.Screen.ShoppingCartScreen
import nextstep.signup.R

@Composable
fun ProductDetailScreen(
    navController: NavController,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    ProductDetailContent(
        navController = navController,
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ProductDetailContent(
    navController: NavController,
    state: ProductDetailState,
    onEvent: (ProductDetailEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavigationTopAppBar(
                title = stringResource(R.string.product_list),
                navigateToBack = { navController.popBackStack() },
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            state.product?.let { product ->
                Column(
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                ) {
                    ProductImage(
                        product = product,
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop,
                    )

                    Text(
                        text = product.name,
                        modifier = Modifier.padding(18.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    HorizontalDivider(thickness = 1.dp, color = Gray10)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = stringResource(R.string.price),
                            modifier = Modifier.padding(18.dp),
                            fontSize = 20.sp,
                        )

                        Text(
                            text = stringResource(R.string.price_format, product.price),
                            modifier = Modifier.padding(18.dp),
                            fontSize = 20.sp,
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    RectangleButton(
                        onClick = {
                            onEvent(ProductDetailEvent.AddProduct(product = product))
                            navController.navigate(ShoppingCartScreen)
                        },
                        text = stringResource(R.string.add_shopping_cart_product),
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                    )
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            state.error?.let { error ->
                Text(
                    text = error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ProductDetailScreenPreview() {
    ShoppingCartTheme {
        ProductDetailContent(navController = rememberNavController(), state = ProductDetailState(
            product = Product(
                id = 0L,
                imageUrl =
                "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                        "%2FMjAyNDAyMjNfMjkg%2FMDAxNzA4NjE1NTg1ODg5.ZFPHZ3Q2HzH7GcYA1_Jl0lsIdvAnzUF2h6Qd6bgDLHkg." +
                        "_7ffkgE45HXRVgX2Bywc3B320_tuatBww5y1hS4xjWQg.JPEG%2FIMG_5278.jpg&type=sc960_832",
                name = "대전 장인약과",
                price = 12000,
            )
        ), onEvent = {})
    }
}
