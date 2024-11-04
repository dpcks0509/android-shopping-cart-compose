package nextstep.shoppingcart.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme
import nextstep.signup.R

@Composable
fun QuantityControl(
    quantity: Int,
    minusQuantity: () -> Unit,
    plusQuantity: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { minusQuantity() }) {
            Icon(
                painter = painterResource(R.drawable.ic_minus),
                contentDescription = stringResource(R.string.minus_quantity),
                modifier = Modifier.size(42.dp)
            )
        }

        Text(
            text = quantity.toString(),
            modifier = Modifier.width(42.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        IconButton(onClick = { plusQuantity() }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.plus_quantity),
                modifier = Modifier.size(42.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun QuantityAdjusterPreview() {
    ShoppingCartTheme {
        QuantityControl(
            quantity = 1,
            minusQuantity = {},
            plusQuantity = {},
        )
    }
}