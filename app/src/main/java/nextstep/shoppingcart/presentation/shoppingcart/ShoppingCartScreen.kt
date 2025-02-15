package nextstep.shoppingcart.presentation.shoppingcart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nextstep.shoppingcart.domain.model.Product
import nextstep.shoppingcart.domain.model.ShoppingCartProduct
import nextstep.shoppingcart.presentation.component.BackNavigationTopAppBar
import nextstep.shoppingcart.presentation.component.RectangleButton
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.presentation.util.Screen.ProductListScreen
import nextstep.signup.R

@Composable
fun ShoppingCartScreen(
    navController: NavController,
    viewModel: ShoppingCartViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true) {
        viewModel.loadShoppingCartProducts()
    }

    ShoppingCartContent(
        navController = navController,
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ShoppingCartContent(
    navController: NavController,
    state: ShoppingCartState,
    onEvent: (ShoppingCartEvent) -> Unit
) {
    val snackbarMessage = stringResource(R.string.shopping_cart_order_completed)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavigationTopAppBar(
                title = stringResource(R.string.shopping_cart),
                navigateToBack = { navController.popBackStack() },
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
            )
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                state = rememberLazyListState(),
            ) {
                items(
                    items = state.shoppingCartProducts,
                    key = { shoppingCartProduct -> shoppingCartProduct.id },
                ) { shoppingCartProduct ->
                    ShoppingCartItem(shoppingCartProduct = shoppingCartProduct, onEvent = onEvent)
                }
            }

            RectangleButton(
                onClick = {
                    onEvent(ShoppingCartEvent.ClearProducts)
                    navController.navigate(ProductListScreen(snackbarMessage = snackbarMessage))
                },
                text =
                stringResource(
                    R.string.order_price,
                    state.totalPrice
                ),
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                enabled = state.shoppingCartProducts.isNotEmpty()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ShoppingCartScreenPreview() {
    ShoppingCartTheme {
        ShoppingCartContent(
            navController = rememberNavController(),
            state = ShoppingCartState(
                shoppingCartProducts = listOf(
                    ShoppingCartProduct(
                        id = 0L,
                        product = Product(
                            id = 0L,
                            imageUrl =
                            "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                                    "%2FMjAyNDAyMjNfMjkg%2FMDAxNzA4NjE1NTg1ODg5.ZFPHZ3Q2HzH7GcYA1_Jl0lsIdvAnzUF2h6Qd6bgDLHkg." +
                                    "_7ffkgE45HXRVgX2Bywc3B320_tuatBww5y1hS4xjWQg.JPEG%2FIMG_5278.jpg&type=sc960_832",
                            name = "대전 장인약과",
                            price = 12000,
                        ),
                        quantity = 1
                    ),
                    ShoppingCartProduct(
                        id = 1L,
                        product = Product(
                            id = 0L,
                            imageUrl =
                            "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                                    "%2FMjAyNDAyMjNfMjkg%2FMDAxNzA4NjE1NTg1ODg5.ZFPHZ3Q2HzH7GcYA1_Jl0lsIdvAnzUF2h6Qd6bgDLHkg." +
                                    "_7ffkgE45HXRVgX2Bywc3B320_tuatBww5y1hS4xjWQg.JPEG%2FIMG_5278.jpg&type=sc960_832",
                            name = "대전 장인약과",
                            price = 12000,
                        ),
                        quantity = 1
                    ), ShoppingCartProduct(
                        id = 2L,
                        product = Product(
                            id = 0L,
                            imageUrl =
                            "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                                    "%2FMjAyNDAyMjNfMjkg%2FMDAxNzA4NjE1NTg1ODg5.ZFPHZ3Q2HzH7GcYA1_Jl0lsIdvAnzUF2h6Qd6bgDLHkg." +
                                    "_7ffkgE45HXRVgX2Bywc3B320_tuatBww5y1hS4xjWQg.JPEG%2FIMG_5278.jpg&type=sc960_832",
                            name = "대전 장인약과",
                            price = 12000,
                        ),
                        quantity = 1
                    ), ShoppingCartProduct(
                        id = 3L,
                        product = Product(
                            id = 0L,
                            imageUrl =
                            "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net" +
                                    "%2FMjAyNDAyMjNfMjkg%2FMDAxNzA4NjE1NTg1ODg5.ZFPHZ3Q2HzH7GcYA1_Jl0lsIdvAnzUF2h6Qd6bgDLHkg." +
                                    "_7ffkgE45HXRVgX2Bywc3B320_tuatBww5y1hS4xjWQg.JPEG%2FIMG_5278.jpg&type=sc960_832",
                            name = "대전 장인약과",
                            price = 12000,
                        ),
                        quantity = 1
                    )
                ).toMutableStateList()
            ),
            onEvent = {}
        )
    }
}
