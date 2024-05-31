package ma.developer.buynal2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ma.developer.buynal2.model.ProductModel


class ProductViewModel : ViewModel() {
    private val db = FirebaseDatabase.getInstance()
    private val productReference = db.getReference("products")

    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        productReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = mutableListOf<ProductModel>()
                for (productSnapshot in snapshot.children) {
                    val product = productSnapshot.getValue(ProductModel::class.java)
                    product?.let {
                        result.add(it)
                    }
                }
                _products.value = result
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}