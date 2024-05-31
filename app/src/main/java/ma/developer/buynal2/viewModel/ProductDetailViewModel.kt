package ma.developer.buynal2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ma.developer.buynal2.model.ProductModel

class ProductDetailViewModel : ViewModel() {
    private val db = FirebaseDatabase.getInstance()
    private val productReference = db.getReference("products")

    private val _productDetail = MutableLiveData<ProductModel?>()
    val productDetail: MutableLiveData<ProductModel?> = _productDetail

    fun fetchProductDetail(productId: String) {
        productReference.child(productId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val product = snapshot.getValue(ProductModel::class.java)
                _productDetail.value = product
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}
