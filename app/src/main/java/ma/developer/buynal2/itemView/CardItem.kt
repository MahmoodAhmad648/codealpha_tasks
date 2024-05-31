package ma.developer.buynal2.itemView

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextOverflow
import ma.developer.buynal2.model.CartModel

@Composable
fun CartItemView(
    cartItem: CartModel,
    onIncreaseQuantity: () -> Unit,
    onDecreaseQuantity: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cartItem.name,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = onDecreaseQuantity) {
                        Text("-")
                    }
                    Text(
                        text = "Quantity: ${cartItem.quantity}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Button(onClick = onIncreaseQuantity) {
                        Text("+")
                    }
                }
                Text(
                    text = "Price: $${cartItem.price}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(
                text = "$${String.format("%.2f", cartItem.price * cartItem.quantity)}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}