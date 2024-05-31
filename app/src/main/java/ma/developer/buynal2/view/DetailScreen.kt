package ma.developer.buynal2.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import ma.developer.buynal2.R
import ma.developer.buynal2.model.CartModel
import ma.developer.buynal2.navigation.Screen
import ma.developer.buynal2.viewModel.CartViewModel
import ma.developer.buynal2.viewModel.ProductDetailViewModel

@Composable
fun ProductDetail(
    productId: String,
    navController: NavHostController
) {
    val productDetailViewModel: ProductDetailViewModel = viewModel()
    val productDetail by productDetailViewModel.productDetail.observeAsState()
    val cartViewModel: CartViewModel = viewModel()

    LaunchedEffect(productId) {
        delay(250)
        productDetailViewModel.fetchProductDetail(productId)
    }

    // Fetch product detail when the composable is first composed
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        // Display the product detail
        productDetail?.let { product ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(product.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp)
                        .fillMaxWidth()
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            androidx.compose.material.Text(
                text = product.title,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                ),
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                androidx.compose.material.Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Price: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 18.sp)) {
                            append(product.price)
                        }
                    },
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                )

                androidx.compose.material.Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Size: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 16.sp)) {
                            append(product.sizes)
                        }
                    },
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            androidx.compose.material.Text(
                text = product.description,
                style = MaterialTheme.typography.body2.copy(
                    color = Color.DarkGray,
                    fontSize = 16.sp
                ),
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val priceDouble = product.price.toDoubleOrNull() ?: 0.0
                    cartViewModel.addToCart(
                        CartModel(
                            name = product.title,
                            price = priceDouble,
                            quantity = 1, // Default to 1 or any initial quantity
                            imageUrl = product.image
                        )
                    )
                    navController.navigate(Screen.Cart.routes)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colors.primary)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = "Cart",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                androidx.compose.material.Text(
                    text = "Add to Cart",
                    style = MaterialTheme.typography.button.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}