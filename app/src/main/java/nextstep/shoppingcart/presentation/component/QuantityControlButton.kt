package nextstep.shoppingcart.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.presentation.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun QuantityControlButton(
    quantity: Int,
    minusQuantity: () -> Unit,
    plusQuantity: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { minusQuantity() }, modifier = Modifier.weight(1f)) {
            Icon(
                painter = painterResource(R.drawable.ic_minus),
                contentDescription = stringResource(R.string.minus_quantity),
            )
        }

        Text(
            text = quantity.toString(),
            modifier = Modifier.weight(1f),
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
        )

        IconButton(onClick = { plusQuantity() }, modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.plus_quantity),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun QuantityAdjusterPreview() {
    ShoppingCartTheme {
        QuantityControlButton(
            quantity = 1,
            minusQuantity = {},
            plusQuantity = {},
        )
    }
}
