package nextstep.shoppingcart.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import nextstep.shoppingcart.presentation.productdetail.ProductDetailScreen
import nextstep.shoppingcart.presentation.productlist.ProductListScreen
import nextstep.shoppingcart.presentation.shoppingcart.ShoppingCartScreen
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme
import nextstep.shoppingcart.presentation.util.Screen.ProductDetailScreen
import nextstep.shoppingcart.presentation.util.Screen.ProductListScreen
import nextstep.shoppingcart.presentation.util.Screen.ShoppingCartScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingCartTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ProductListScreen()
                ) {
                    composable<ProductListScreen> {
                        val args = it.toRoute<ProductListScreen>()

                        ProductListScreen(
                            navController = navController,
                            snackbarMessage = args.snackbarMessage
                        )
                    }

                    composable<ProductDetailScreen> {
                        val args = it.toRoute<ProductDetailScreen>()

                        ProductDetailScreen(
                            navController = navController,
                            productId = args.productId
                        )
                    }

                    composable<ShoppingCartScreen> {
                        ShoppingCartScreen(navController = navController)
                    }
                }
            }
        }
    }
}