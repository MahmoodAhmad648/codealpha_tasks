package ma.developer.buynal2.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import ma.developer.buynal2.model.ProductModel
import java.util.UUID

class AddProductViewModel: ViewModel() {
    private val db = FirebaseDatabase.getInstance()
    private val userRef = db.getReference("products")

    private val storageRef = FirebaseStorage.getInstance().reference
    private val imageRef = storageRef.child("products/${UUID.randomUUID()}.jpg")


    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted: LiveData<Boolean> = _isPosted

    fun saveImage(
        title: String,
        description: String,
        collections: String,
        sizes: String,
        imageUri: Uri,
        userId: String?,
        price: Float
    ) {

        val uploadTask = imageRef.putFile(imageUri)
        uploadTask.addOnCompleteListener {
            if (it.isSuccessful){
                imageRef.downloadUrl.addOnSuccessListener {uri ->
                    if (userId != null) {
                        saveData(title, description, collections, sizes, uri.toString(), userId, price.toString())
                    }

                }
            }else {
                _isPosted.postValue(false) // Indicate failure if the upload wasn't successful
            }



        }
    }

    fun saveData(
        title: String,
        description: String,
        collections: String,
        sizes: String,
        image: String,
        userId: String,
        price: String
    ) {
        val productId = userRef.push().key ?: return

        val productData = ProductModel(
            id = productId, // Added 'id' field to ProductModel
            title = title,
            description = description,
            collections = collections,
            sizes = sizes,
            image = image,
            userId = userId,
            price = price
        )


        userRef.child(productId).setValue(productData)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }.addOnFailureListener {
                _isPosted.postValue(false)
            }
    }
}