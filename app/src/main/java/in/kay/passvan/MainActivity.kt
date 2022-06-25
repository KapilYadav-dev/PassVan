package `in`.kay.passvan

import `in`.kay.passvan.screens.Intro
import `in`.kay.passvan.screens.Splash
import `in`.kay.passvan.screens.auth.Home
import `in`.kay.passvan.screens.auth.Login
import `in`.kay.passvan.screens.auth.Register
import `in`.kay.passvan.ui.theme.PassVanTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCenter.start(
            application, BuildConfig.APP_CENTER_SECRET ?: BuildConfig.APP_CENTER_SECRET_CLOUD,
            Analytics::class.java, Crashes::class.java
        )
        setContent {
            PassVanTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash",
                ) {
                    composable("splash") {
                        Splash(navController)
                    }
                    composable("intro") {
                        Intro(navController)
                    }
                    composable("login") {
                        Login(navController)
                    }
                    composable("register") {
                        Register(navController)
                    }
                    composable("home" ){
                        Home()
                    }
                }
            }
        }
    }
}
