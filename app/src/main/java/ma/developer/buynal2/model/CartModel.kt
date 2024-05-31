package ma.developer.buynal2.model

data class CartModel(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0, // Provide a default value for the price
    val quantity: Int = 0, // Provide a default value for the quantity
    val imageUrl: String = "")