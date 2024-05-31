package ma.developer.buynal2.viewModel

import android.content.Context
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.storage
import ma.developer.buynal2.model.UserModel
import ma.developer.buynal2.utils.SharedPref
import java.util.UUID

class AuthViewModel : ViewModel() {

    val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()
    val userRef = db.getReference("users")

    private val storageRef = Firebase.storage.reference
    private val imageRef = storageRef.child("users/${UUID.randomUUID()}.jpg")


    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser: MutableLiveData<FirebaseUser?> = _firebaseUser

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    init {
        _firebaseUser.value = auth.currentUser
    }

    fun login(email: String, password: String, context: Context) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _firebaseUser.postValue(auth.currentUser)

                    getData(auth.currentUser!!.uid,context)
                } else {
                    _error.postValue("Something went wrong.")

                }
            }
    }

    private fun getData(uid: String, context: Context) {
        userRef.child(uid).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userData = snapshot.getValue(UserModel::class.java)
                SharedPref.storeData(userData!!.email,userData!!.password,userData!!.firstName,userData!!.lastName,userData!!.image,context)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun signup(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        imageUri: Uri,
        context: Context
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _firebaseUser.postValue(auth.currentUser)
                    saveImage(email, password, firstName, lastName, imageUri, auth.currentUser?.uid,context)

                } else {
                    _error.postValue("Something went wrong.")

                }
            }
    }

    private fun saveImage(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        imageUri: Uri,
        uid: String?,
        context: Context
    ) {

        val uploadTask = imageRef.putFile(imageUri)
        uploadTask.addOnCompleteListener {
            imageRef.downloadUrl.addOnSuccessListener {
                if (uid != null) {
                    saveData(email, password, firstName, lastName, it.toString(), uid,context)
                }
            }


        }
    }

    private fun saveData(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        toString: String,
        uid: String?,
        context: Context
    ) {

        val userData = UserModel(email, password, firstName, lastName, toString,uid!!)
        userRef.child(uid).setValue(userData)
            .addOnSuccessListener {
                SharedPref.storeData(email, password,firstName, lastName, toString, context)
            }.addOnFailureListener {

            }
    }

    fun logout(context: Context) {

        auth.signOut()
        _firebaseUser.postValue(null)
        SharedPref.clearUserData(context)
    }

}