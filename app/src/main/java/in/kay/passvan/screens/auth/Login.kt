package `in`.kay.passvan.screens.auth

import `in`.kay.passvan.ui.theme.BebasNue
import `in`.kay.passvan.ui.theme.Poppins
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Password
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.compose.runtime.saveable.rememberSaveable as rememberSaveable1

@Composable
fun Login(navController: NavController) {
    var email by rememberSaveable1 {
        mutableStateOf("")
    }
    var password by rememberSaveable1 {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable1 { mutableStateOf(false) }

    BoxWithConstraints {
        ConstraintLayout(splashConstraintSet(), modifier = Modifier.fillMaxSize()) {
            Icon(
                imageVector = Icons.Outlined.Password,
                modifier = Modifier
                    .size(48.dp)
                    .layoutId("ivIcon"),
                contentDescription = null,
                tint = Color(0xfffFF6464)
            )
            Text(
                text = "Login",
                fontSize = 64.sp,
                fontFamily = BebasNue(),
                color = Color(0xfff545974),
                modifier = Modifier.layoutId("tvTitle")
            )
            Text(
                text = "Let’s get back to your account!",
                fontSize = 14.sp,
                fontFamily = Poppins(),
                color = Color(0xfffbababa),
                modifier = Modifier.layoutId("tvDesc")
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text(
                        text = "Email",
                        fontFamily = BebasNue(),
                        color = Color(0xfff545974)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("etEmail"),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            OutlinedTextField(
                value = password,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                onValueChange = {
                    password = it
                },
                label = {
                    Text(
                        text = "Password",
                        fontFamily = BebasNue(),
                        color = Color(0xfff545974)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("etPassword"),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val icon = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val color = if (passwordVisible) {
                        Color(0xfffFF6464)
                    } else Color(0xfffbababa)

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { passwordVisible = !passwordVisible },
                        tint = color
                    )
                }
            )
            Text(
                text = "Forgot Password",
                fontSize = 14.sp,
                fontFamily = Poppins(),
                color = Color(0xfffFF6464),
                modifier = Modifier
                    .layoutId("tvForgotPassword")
                    .clickable {
                    }
            )
            Button(
                onClick = {
                    navController.navigate("home") {
                        popUpTo(0)
                    }
                },
                modifier = Modifier
                    .layoutId("btnLogin")
                    .height(48.dp)
                    .shadow(
                        10.dp,
                        RoundedCornerShape(12.dp)
                    ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xfffFF6464)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "LOGIN",
                    fontFamily = BebasNue(),
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Text(
                text = "Don't have an account yet ?",
                fontSize = 14.sp,
                fontFamily = Poppins(),
                color = Color(0xfff545974),
                modifier = Modifier.layoutId("tvDont")
            )

            Text(
                text = "REGISTER",
                fontSize = 16.sp,
                fontFamily = BebasNue(),
                color = Color(0xfffFF6464),
                modifier = Modifier
                    .layoutId("tvRegister")
                    .clickable {
                        navController.navigate("register") {
                            popUpTo(0)
                        }
                    }
                    .padding(16.dp)
            )
        }
    }
}


fun splashConstraintSet(): ConstraintSet {
    return ConstraintSet {
        val ivIcon = createRefFor("ivIcon")
        val tvTitle = createRefFor("tvTitle")
        val tvDesc = createRefFor("tvDesc")
        val etEmail = createRefFor("etEmail")
        val etPassword = createRefFor("etPassword")
        val tvForgotPassword = createRefFor("tvForgotPassword")
        val btnLogin = createRefFor("btnLogin")
        val tvRegister = createRefFor("tvRegister")
        val tvDont = createRefFor("tvDont")

        constrain(ivIcon) {
            start.linkTo(parent.start, 20.dp)
            top.linkTo(parent.top, 40.dp)
        }
        constrain(tvTitle) {
            start.linkTo(parent.start, 20.dp)
            top.linkTo(ivIcon.bottom, 20.dp)
        }
        constrain(tvDesc) {
            start.linkTo(parent.start, 20.dp)
            end.linkTo(parent.end, 20.dp)
            width = Dimension.fillToConstraints
            top.linkTo(tvTitle.bottom, 16.dp)
        }
        constrain(etEmail) {
            start.linkTo(parent.start, 24.dp)
            end.linkTo(parent.end, 24.dp)
            top.linkTo(tvDesc.bottom, 40.dp)
            width = Dimension.fillToConstraints
        }
        constrain(etPassword) {
            start.linkTo(parent.start, 24.dp)
            end.linkTo(parent.end, 24.dp)
            top.linkTo(etEmail.bottom, 20.dp)
            width = Dimension.fillToConstraints
        }
        constrain(tvForgotPassword) {
            centerHorizontallyTo(parent)
            top.linkTo(etPassword.bottom, 16.dp)
        }
        constrain(btnLogin) {
            start.linkTo(parent.start, 24.dp)
            end.linkTo(parent.end, 24.dp)
            width = Dimension.fillToConstraints
            top.linkTo(tvForgotPassword.bottom, 24.dp)
        }
        constrain(tvDont) {
            top.linkTo(btnLogin.bottom, 32.dp)
            centerHorizontallyTo(parent)
        }
        constrain(tvRegister) {
            top.linkTo(tvDont.bottom, 8.dp)
            centerHorizontallyTo(parent)
        }
    }
}
