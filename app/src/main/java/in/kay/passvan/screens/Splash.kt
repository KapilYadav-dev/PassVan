package `in`.kay.passvan.screens

import `in`.kay.passvan.ui.theme.BebasNue
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(1000L)
        navController.navigate("intro") {

        }
    }

    BoxWithConstraints {
        ConstraintLayout(
            splashConstraintSet(),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Outlined.Password,
                modifier = Modifier
                    .size(48.dp)
                    .layoutId("ivIcon")
                    .scale(scale.value),
                contentDescription = null,
                tint = Color(0xfffFF6464)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.layoutId("tvName")) {
                Text(
                    text = "PASS",
                    fontFamily = BebasNue(),
                    color = Color(0xfffFF6464),
                    fontSize = 32.sp
                )
                Text(
                    text = " VAN",
                    fontFamily = BebasNue(),
                    color = Color(0xfff545974),
                    fontSize = 32.sp
                )
            }
            Text(
                text = "The only password manager you’ll ever need.",
                fontSize = 10.sp,
                color = Color(0xfffBABABA),
                modifier = Modifier
                    .layoutId("tvTagLine"),

                )
        }
    }
}

fun splashConstraintSet(): ConstraintSet {
    return ConstraintSet {
        val icon = createRefFor("ivIcon")
        val name = createRefFor("tvName")
        val tagline = createRefFor("tvTagLine")

        constrain(icon) {
            centerTo(parent)
        }
        constrain(name) {
            top.linkTo(icon.bottom, 8.dp)
            centerHorizontallyTo(parent)
        }
        constrain(tagline) {
            centerHorizontallyTo(parent)
            bottom.linkTo(parent.bottom, 24.dp)
        }
    }
}
