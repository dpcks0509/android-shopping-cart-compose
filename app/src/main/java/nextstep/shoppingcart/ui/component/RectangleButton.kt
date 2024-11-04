package nextstep.shoppingcart.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.shoppingcart.ui.theme.Blue50
import nextstep.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun RectangleButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        enabled = enabled,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(Blue50),
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun RectangleButtonPreview() {
    ShoppingCartTheme {
        RectangleButton(
            text = "RectangleButton",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(54.dp),
            enabled = true,
            onClick = {},
        )
    }
}