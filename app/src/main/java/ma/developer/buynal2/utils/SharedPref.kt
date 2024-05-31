package ma.developer.buynal2.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SharedPref {

    fun storeData(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        imageUrl: String,
        context: Context

    ) {

        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("firstName", firstName)
        editor.putString("lastName", lastName)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.putString("imageUrl", imageUrl)
        editor.apply()
    }

    fun getFirstName(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("firstName", "")!!
    }

    fun getLastName(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("lastName", "")!!
    }

    fun getEmail(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("email", "")!!
    }

    fun getImage(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("imageUrl", "")!!
    }
    fun clearUserData(context: Context) {
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }

}