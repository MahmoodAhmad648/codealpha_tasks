package ma.developer.buynal2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ma.developer.buynal2.view.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.routes) {
        composable(Screen.Splash.routes) {
            Splash(navController)
        }
        composable(Screen.Home.routes) {
            HomeScreen()
        }
        composable(Screen.Products.routes) {
            Products(navController)
        }
        composable(Screen.Cart.routes) {
            CartScreen(navController)
        }
        composable(Screen.AddProduct.routes) {
            AddProduct(navController)
        }
        composable(Screen.Profile.routes) {
            Profile(navController)
        }
        composable(Screen.BottomNav.routes) {
            BottomBar()
        }
        composable(Screen.Login.routes) {
            LogIn(navController)
        }
        composable(Screen.SignUp.routes) {
            SignUp(navController)
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