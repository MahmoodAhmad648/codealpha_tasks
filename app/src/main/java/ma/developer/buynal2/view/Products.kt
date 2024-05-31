package ma.developer.buynal2.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import ma.developer.buynal2.itemView.ProductItem
import ma.developer.buynal2.viewModel.ProductViewModel

@Composable
fun Products(navController: NavHostController) {
    val productViewModel: ProductViewModel = viewModel()
    val products by productViewModel.products.observeAsState(emptyList())

    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: ""


    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Your Cart",
            modifier = Modifier.padding(vertical = 8.dp).align(Alignment.CenterHorizontally),
            fontSize = 24.sp,
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(products) { product ->
                ProductItem(product, navController, userId = currentUserId)
            }
        }
    }}