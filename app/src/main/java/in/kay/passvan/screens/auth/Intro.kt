package `in`.kay.passvan.screens.auth

import `in`.kay.passvan.ui.theme.BebasNue
import `in`.kay.passvan.ui.theme.Poppins
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun Intro(navController: NavController) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        pageCount = introItems().size,
        initialOffscreenLimit = 2,
        initialPage = 0,
        infiniteLoop = false
    )
    BackHandler() {
        if (pagerState.currentPage > 0) {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
            return@BackHandler
        }
        navController.navigate("splash")
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        )
        {
            HorizontalPager(state = pagerState) {
                IntroPage(pagerState.currentPage, scope, navController)
            }
        }

    }
}

@Composable
fun IntroPage(page: Int, scope: CoroutineScope, navController: NavController) {
    BoxWithConstraints {
        ConstraintLayout(
            introConstraintSet(),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Outlined.Password,
                modifier = Modifier
                    .size(48.dp)
                    .layoutId("ivIcon"),
                contentDescription = null,
                tint = Color(0xfffFF6464)
            )
            Text(
                buildAnnotatedString {
                    when (page) {
                        0 -> {
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xfff545974),
                                    fontFamily = BebasNue(),
                                    fontSize = 56.sp
                                )
                            ) {
                                append("GENERATE\n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xfffFF6464),
                                    fontFamily = BebasNue(),
                                    fontSize = 56.sp
                                )
                            ) {
                                append("SECURE\n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xfff545974),
                                    fontFamily = BebasNue(),
                                    fontSize = 56.sp
                                )
                            ) {
                                append("PASSWORDS.")
                            }
                        }
                        1 -> {
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xfff545974),
                                    fontFamily = BebasNue(),
                                    fontSize = 56.sp
                                )
                            ) {
                                append("ALL YOUR\n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xfffFF6464),
                                    fontFamily = BebasNue(),
                                    fontSize = 56.sp
                                )
                            ) {
                                append("PASSWORDS")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xfff545974),
                                    fontFamily = BebasNue(),
                                    fontSize = 56.sp
                                )
                            ) {
                                append(" ARE HERE.")
                            }
                        }
                        2 -> {
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xfff545974),
                                    fontFamily = BebasNue(),
                                    fontSize = 56.sp
                                )
                            ) {
                                append("DON’T TYPE,")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xfffFF6464),
                                    fontFamily = BebasNue(),
                                    fontSize = 56.sp
                                )
                            ) {
                                append(" AUTOFILL")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xfff545974),
                                    fontFamily = BebasNue(),
                                    fontSize = 56.sp
                                )
                            ) {
                                append(" YOUR CREDENTIALS.")
                            }
                        }
                    }
                }, modifier = Modifier.layoutId("tvTitle")
            )
            Text(
                text = introItems()[page].description,
                color = Color(0xfffBABABA),
                fontFamily = Poppins(),
                fontSize = 15.sp,
                modifier = Modifier.layoutId("tvDesc")
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("btnContainer"),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedButton(
                    border = if (page == 2) BorderStroke(
                        4.dp,
                        Color(0xfffFF6464)
                    ) else BorderStroke(4.dp, Color(0xfffE0E0E0)),
                    onClick = {
                        navController.navigate("register") {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f),
                    enabled = page == 2
                ) {
                    Text(
                        text = "REGISTER",
                        fontFamily = BebasNue(),
                        fontSize = 18.sp,
                        color = if (page == 2) Color(0xfffFF6464) else Color(0xfffE0E0E0)
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))
                Button(
                    onClick = {
                        scope.launch {
                            navController.navigate("login") {
                                popUpTo(0)
                            }
                        }
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f),
                    enabled = page == 2,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xfffFF6464))
                ) {
                    Text(
                        text = "LOGIN",
                        fontFamily = BebasNue(),
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .layoutId("viewPageNo"), verticalAlignment = Alignment.CenterVertically
            ) {
                when (page + 1) {
                    1 -> {
                        HighlitedText(page = page + 1)
                        Spacer(modifier = Modifier.width(8.dp))
                        UnhighlitedText(page = 2)
                        Spacer(modifier = Modifier.width(8.dp))
                        UnhighlitedText(page = 3)
                    }
                    2 -> {
                        UnhighlitedText(page = 1)
                        Spacer(modifier = Modifier.width(8.dp))
                        HighlitedText(page = page + 1)
                        Spacer(modifier = Modifier.width(8.dp))
                        UnhighlitedText(page = 3)
                    }
                    3 -> {
                        UnhighlitedText(page = 1)
                        Spacer(modifier = Modifier.width(8.dp))
                        UnhighlitedText(page = 2)
                        Spacer(modifier = Modifier.width(8.dp))
                        HighlitedText(page = page + 1)
                    }
                }
            }
        }
    }
}

@Composable
fun HighlitedText(page: Int) {
    Text(
        text = page.toString(),
        color = Color(0xfffFF6464),
        fontFamily = BebasNue(),
        fontSize = 40.sp,
        modifier = Modifier.fillMaxHeight()
    )
}

@Composable
fun UnhighlitedText(page: Int) {
    Text(
        text = page.toString(),
        color = Color(0xfffBABABA),
        fontFamily = BebasNue(),
        fontSize = 20.sp
    )
}

fun introConstraintSet(): ConstraintSet {
    return ConstraintSet {
        val icon = createRefFor("ivIcon")
        val tvTitle = createRefFor("tvTitle")
        val tvDesc = createRefFor("tvDesc")
        val btnContainer = createRefFor("btnContainer")
        val pageNo = createRefFor("viewPageNo")

        constrain(icon) {
            top.linkTo(parent.top, 48.dp)
            start.linkTo(parent.start, 24.dp)
        }
        constrain(tvTitle) {
            width = Dimension.fillToConstraints
            start.linkTo(parent.start, 24.dp)
            end.linkTo(parent.end, 24.dp)
            top.linkTo(icon.bottom, 40.dp)

        }
        constrain(tvDesc) {
            width = Dimension.fillToConstraints
            top.linkTo(tvTitle.bottom, 32.dp)
            start.linkTo(parent.start, 24.dp)
            end.linkTo(parent.end, 24.dp)
        }

        constrain(pageNo) {
            bottom.linkTo(btnContainer.top, 32.dp)
            start.linkTo(parent.start, 24.dp)
        }
        constrain(btnContainer) {
            bottom.linkTo(parent.bottom, 32.dp)
            start.linkTo(parent.start, 24.dp)
            end.linkTo(parent.end, 24.dp)
            width = Dimension.matchParent
        }
    }
}


data class IntroData(
    val description: String
)

fun introItems() = listOf(
    IntroData(
        "Stop using unsecure passwords for your online accounts, level up with OnePass. Get the most secure and difficult-to-crack passwords."
    ),
    IntroData(
        "Store and manage all of your passwords from one place. Don’t remember hundreds of passwords, just remember one."
    ),
    IntroData(
        "Don’t compromise your passwords by typing them in public, let OnePass autofill those and keep your credentials secure."
    )
)