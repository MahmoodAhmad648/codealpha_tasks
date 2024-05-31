package ma.developer.buynal2.view

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import ma.developer.buynal2.R
import ma.developer.buynal2.navigation.Screen
import ma.developer.buynal2.viewModel.AddProductViewModel


@Composable
fun AddProduct(navController: NavHostController) {
    val productViewModel : AddProductViewModel = viewModel()
    val isPosted by productViewModel.isPosted.observeAsState(false)
    var title by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0f) }
    var description by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedCollection by remember { mutableStateOf("Select a Collection") }

    val collections = listOf("Dresses", "Clothing", "Shoes", "Featured Products")
    var expandedSize by remember { mutableStateOf(false) }
    var selectedSize by remember { mutableStateOf("Select a Size") }
    val sizes = listOf("Small", "Medium", "Large", "X-Large")
    val context = LocalContext.current

    var imageUri by remember { mutableStateOf<String?>(null) }

    val permissionToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else Manifest.permission.READ_EXTERNAL_STORAGE

    var launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri.toString()
        }

    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {

                isGranted ->
            Boolean
            if (isGranted) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

    LaunchedEffect(isPosted ) {
        if(isPosted){
            title = ""
            price = 0f
            description = ""
            selectedCollection = "Select a Collection"
            selectedSize = "Select a Size"
            imageUri = null
            Toast.makeText(context, "Product Added Successfully", Toast.LENGTH_SHORT).show()

            navController.navigate(Screen.Products.routes){
                popUpTo(Screen.AddProduct.routes){
                    inclusive = true
                }
            }

        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Row {
                Image(painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "close",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {
                            navController.navigate(Screen.Products.routes){
                                popUpTo(Screen.AddProduct.routes){
                                    inclusive = true
                                }
                            }
                        }
                )

                Text(
                    text = "Add Product",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {

            if (imageUri == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.LightGray)
                        .clickable {
                            val isGranted = ContextCompat.checkSelfPermission(
                                context,
                                permissionToRequest
                            ) == PackageManager.PERMISSION_GRANTED

                            if (isGranted) {
                                launcher.launch("image/*")
                            } else {
                                permissionLauncher.launch(permissionToRequest)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.add_product),
                        contentDescription = "Add Image",
                        modifier = Modifier.size(100.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
            else{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUri),
                        contentDescription = "Add Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remove Image",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .clickable {
                                imageUri = null
                            })
                }


            }
        }

        item {
            OutlinedTextField(
                value = title, onValueChange = { title = it },
                placeholder = {
                    Text(
                        text = "Enter Title",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal)
                    )
                },
                label = { Text("Title") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color(0xFFD6D6D6),
                )
            )

            OutlinedTextField(
                value = description, onValueChange = { description = it },
                placeholder = {
                    Text(
                        text = "Enter Description",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal)
                    )
                },
                label = { Text("Description") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color(0xFFD6D6D6),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            // Dropdown Menu for selecting collection
            OutlinedTextField(
                value = selectedCollection,
                onValueChange = { selectedCollection = it },
                readOnly = true,
                label = { Text("Collection") },
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        Modifier.clickable { expanded = !expanded }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color(0xFFD6D6D6),
                )
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                collections.forEach { collection ->
                    DropdownMenuItem(
                        text = { Text(text = collection) },
                        onClick = {
                            selectedCollection = collection
                            expanded = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            // Dropdown Menu for selecting size
            OutlinedTextField(
                value = selectedSize,
                onValueChange = { selectedSize = it },
                readOnly = true,
                label = { Text("Size") },
                trailingIcon = {
                    Icon(
                        imageVector = if (expandedSize) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        Modifier.clickable { expandedSize = !expandedSize }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedSize = true },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color(0xFFD6D6D6),
                )
            )
            DropdownMenu(
                expanded = expandedSize,
                onDismissRequest = { expandedSize = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                sizes.forEach { size ->
                    DropdownMenuItem(
                        text = { Text(text = size) },
                        onClick = {
                            selectedSize = size
                            expandedSize = false // Close the dropdown after selecting a size
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            OutlinedTextField(
                value = price.toString(), onValueChange = {
                    price = if (it.isNotEmpty()) {
                        it.toFloat()
                    } else {
                        0f
                    }
                },
                placeholder = {
                    Text(
                        text = "Enter Price",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal)
                    )
                },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color(0xFFD6D6D6),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Box(
                contentAlignment = Alignment.Center
            ){
                Button(
                    onClick = {
                        if (title.isEmpty() || description.isEmpty() || collections.isEmpty() || sizes.isEmpty()|| price == 0f || imageUri == null) {
                            Toast.makeText(context, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
                        }else{
                            productViewModel.saveImage(
                                title, description, selectedCollection,
                                selectedSize, Uri.parse(imageUri!!), FirebaseAuth.getInstance().currentUser!!.uid, price
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Add Product")
                }
            }

        }
    }
}