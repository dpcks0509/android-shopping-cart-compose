package nextstep.shoppingcart.presentation.productlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nextstep.shoppingcart.presentation.mapper.toUi
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.presentation.util.Screen.ProductDetailScreen
import nextstep.shoppingcart.presentation.util.Screen.ShoppingCartScreen
import nextstep.shoppingcart.remote.source.ProductDataSourceImpl.Companion.products
import nextstep.signup.R

@Composable
fun ProductListScreen(
    navController: NavController,
    snackbarMessage: String? = null,
    viewModel: ProductListViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.loadProducts()
    }

    ProductListContent(
        navController = navController,
        coroutineScope = coroutineScope,
        snackbarHostState = snackbarHostState,
        snackbarMessage = snackbarMessage,
        state = viewModel.state,
        onEvent = viewModel::onEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListContent(
    navController: NavController,
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    snackbarMessage: String?,
    state: ProductListState,
    onEvent: (ProductListEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.product_list),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(ShoppingCartScreen)
                        },
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = stringResource(R.string.shopping_cart),
                        )
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { contentPadding ->
        if (snackbarMessage != null) {
            LaunchedEffect(key1 = snackbarMessage) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = snackbarMessage,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = rememberLazyGridState(),
                contentPadding = PaddingValues(
                    start = 18.dp,
                    end = 18.dp,
                    top = 12.dp,
                    bottom = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(
                    items = state.products,
                    key = { productItem -> productItem.product.id },
                ) { productItem ->
                    val navigateToProductDetail = remember(productItem.product.id) {
                        { navController.navigate(ProductDetailScreen(productId = productItem.product.id)) }
                    }

                    ProductItem(
                        item = productItem,
                        onEvent = onEvent,
                        onClick = navigateToProductDetail
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
private fun ProductListScreenPreView() {
    ShoppingCartTheme {
        ProductListContent(
            navController = rememberNavController(),
            coroutineScope = rememberCoroutineScope(),
            snackbarHostState = SnackbarHostState(),
            snackbarMessage = null,
            state = ProductListState(products = products.toUi()),
            onEvent = {}
        )
    }
}
