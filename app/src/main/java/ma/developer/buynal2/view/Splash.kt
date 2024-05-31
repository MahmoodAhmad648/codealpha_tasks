package ma.developer.buynal2.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import ma.developer.buynal2.R
import ma.developer.buynal2.navigation.Screen

@Composable
fun Splash(navController: NavHostController) {

    val rotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }
    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.buynal),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .rotate(rotation.value)
            )
        }
    }


    LaunchedEffect(true) {
        delay(2000)
        if (FirebaseAuth.getInstance().currentUser!= null)
            navController.navigate(Screen.BottomNav.routes) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true

            }
        else
            navController.navigate(Screen.Login.routes) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
    }
}