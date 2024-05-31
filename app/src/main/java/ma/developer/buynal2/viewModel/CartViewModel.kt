package ma.developer.buynal2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.coroutines.launch
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ma.developer.buynal2.model.CartModel

class CartViewModel : ViewModel() {
    private val db = FirebaseDatabase.getInstance()
    private val cartReference = db.getReference("cart")

    private val _cartItems = MutableLiveData<List<CartModel>>(emptyList())
    val cartItems: LiveData<List<CartModel>> = _cartItems

    init {
        // Fetch cart items when ViewModel is initialized
        fetchCartItems()
    }

    fun fetchCartItems() {
        cartReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<CartModel>()
                for (child in snapshot.children) {
                    val item = child.getValue(CartModel::class.java)
                    item?.let {
                        items.add(it)
                    }
                }
                _cartItems.value = items
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun addToCart(cartItem: CartModel) {
        // Add item to local cart items
        val currentItems = _cartItems.value.orEmpty().toMutableList()
        currentItems.add(cartItem)
        _cartItems.value = currentItems

        // Update database
        cartReference.setValue(currentItems)
    }

    fun updateQuantity(cartItem: CartModel, newQuantity: Int) {
        val updatedItems = _cartItems.value.orEmpty().map {
            if (it.id == cartItem.id) {
                it.copy(quantity = newQuantity)
            } else {
                it
            }
        }
        _cartItems.value = updatedItems
        cartReference.setValue(updatedItems)
    }
}