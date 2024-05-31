package ma.developer.buynal2.navigation

sealed class Screen(val routes: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Products : Screen("Products")
    object Cart : Screen("cart")
    object AddProduct : Screen("add_product")
    object Profile : Screen("profile")
    object BottomNav : Screen("bottom_nav")
    object Login : Screen("login")
    object SignUp : Screen("sign_up")
    object Detail : Screen("productDetail/{productId}") {
        fun createRoute(productId: String) = "productDetail/$productId"
    }
}