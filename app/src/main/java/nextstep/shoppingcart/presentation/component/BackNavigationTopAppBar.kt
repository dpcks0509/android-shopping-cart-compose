package nextstep.shoppingcart.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackNavigationTopAppBar(
    title: String,
    navigateToBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(title = {
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }, navigationIcon = {
        IconButton(
            onClick = {
                navigateToBack()
            },
            modifier = modifier,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.shopping_cart),
            )
        }
    })
}

@Composable
@Preview(showBackground = true)
private fun BackNavigationTopAppBarPreview() {
    ShoppingCartTheme {
        BackNavigationTopAppBar(title = "BackNavigationTopAppBar", navigateToBack = {})
    }
}
