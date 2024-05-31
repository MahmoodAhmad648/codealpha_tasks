package ma.developer.buynal2.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController
import ma.developer.buynal2.itemView.CartItemView
import ma.developer.buynal2.viewModel.CartViewModel

// In CartScreen.kt

@Composable
fun CartScreen(navController: NavHostController) {
    val cartViewModel: CartViewModel = viewModel()
    val cartItems by cartViewModel.cartItems.observeAsState(emptyList())

    LaunchedEffect(key1 = true) {
        cartViewModel.fetchCartItems()
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Your Cart",
            modifier = Modifier.padding(vertical = 8.dp).align(Alignment.CenterHorizontally),
            fontSize = 24.sp,
        )

        LazyColumn {
            items(cartItems) { cartItem ->
                CartItemView(
                    cartItem = cartItem,
                    onIncreaseQuantity = {
                        cartViewModel.updateQuantity(cartItem, cartItem.quantity + 1)
                    },
                    onDecreaseQuantity = {
                        if (cartItem.quantity > 1) {
                            cartViewModel.updateQuantity(cartItem, cartItem.quantity - 1)
                        }
                    }
                )
            }
        }
    }
}