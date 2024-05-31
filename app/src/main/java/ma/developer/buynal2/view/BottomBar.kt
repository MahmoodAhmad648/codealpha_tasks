package ma.developer.buynal2.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ma.developer.buynal2.model.BottomNavItem
import ma.developer.buynal2.navigation.Screen

@Composable
fun BottomBar() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { MyBottomBar(navController) }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.routes,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.routes) {
                HomeScreen()
            }
            composable(Screen.Products.routes) {
                Products(navController)
            }
            composable(Screen.AddProduct.routes) {
                AddProduct(navController)
            }
            composable(Screen.Cart.routes) {
                CartScreen(navController)

            }
            composable(Screen.Profile.routes) {
                Profile(navController)
            }
            composable(
                route = "productDetail/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
                ProductDetail(productId, navController)
            }

        }

    }

}

@Composable
fun MyBottomBar(navController: NavHostController) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    val list = listOf(
        BottomNavItem(
            title = "Home",
            route = "home",
            icon = Icons.Rounded.Home
        ),
        BottomNavItem(
            title = "Products",
            route = "products",
            icon = Icons.Filled.Check
        ),
        BottomNavItem(
            title = "Add Product",
            route = "add_product",
            icon = Icons.Rounded.Add
        ),
        BottomNavItem(
            title = "Cart",
            route = "cart",
            icon = Icons.Rounded.ShoppingCart
        ),
        BottomNavItem(
            title = "Profile",
            route = "profile",
            icon = Icons.Rounded.Person
        ),
        BottomNavItem(
            title = "Product Detail",
            route = "detail", // Provide a placeholder product ID or leave it blank
            icon = Icons.Rounded.Build
        )


    )

    BottomAppBar {
        list.forEach {
            val selected = it.route == backStackEntry.value?.destination?.route

            NavigationBarItem(selected = selected, onClick = {
                navController.navigate(it.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true

                    }
                    launchSingleTop = true
                }
            }, icon = {
                Image(
                    imageVector = it.icon,
                    contentDescription = it.title
                )
            })


        }
    }
}