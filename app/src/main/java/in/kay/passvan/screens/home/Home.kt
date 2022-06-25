package `in`.kay.passvan.screens.home

import `in`.kay.passvan.R
import `in`.kay.passvan.getMockList
import `in`.kay.passvan.models.CredentialsModel
import `in`.kay.passvan.ui.theme.BebasNue
import `in`.kay.passvan.ui.theme.Poppins
import `in`.kay.passvan.ui.theme.Typography
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


@Preview
@Composable
fun Home() {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    val scaffoldState = rememberScaffoldState()

    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }

    var searchQueryEmpty by rememberSaveable {
        mutableStateOf(false)
    }


    val passwordStored by rememberSaveable {
        mutableStateOf(5)
    }

    val passwordBreached by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = CircleShape,
                contentColor = Color.White,
                modifier = Modifier.padding(top = 48.dp)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon")
            }
        },
        bottomBar = {
            HomeBottomMenu(
                scaffoldState = scaffoldState,
                scope = rememberCoroutineScope()
            )
        }
    ) {
        BoxWithConstraints(Modifier.padding(it)) {
            ConstraintLayout(homeConstraintSet(), modifier = Modifier.fillMaxSize()) {
                Icon(
                    imageVector = Icons.Outlined.Password,
                    contentDescription = null,
                    tint = Color(0xfffFF6464),
                    modifier = Modifier
                        .layoutId("ivIcon")
                )
                Row(
                    modifier = Modifier
                        .layoutId("tvName")
                ) {
                    Text(
                        text = "PASS",
                        fontFamily = BebasNue(),
                        color = Color(0xfffFF6464),
                        fontSize = 20.sp
                    )
                    Text(
                        text = " VAN",
                        fontFamily = BebasNue(),
                        color = Color(0xfff545974),
                        fontSize = 20.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .layoutId("headerContainer"),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HeaderBoxes(
                        passwordStored, "Passwords\nStored",
                        Modifier
                            .weight(1f)
                            .height(160.dp)
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    HeaderBoxes(
                        passwordBreached, "Passwords\nCompromised",
                        Modifier
                            .weight(1f)
                            .height(160.dp)
                    )
                }
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    },
                    shape = RoundedCornerShape(12.dp),
                    label = {
                        Text(
                            text = "Search here.",
                            fontFamily = BebasNue(),
                            color = Color(0xfff545974)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .layoutId("etSearch"),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                LazyColumn(modifier = Modifier.layoutId("rv")) {
                    val filterdList =
                        getMockList().filter { s -> s.toString().contains(searchQuery) }
                    if (filterdList.isEmpty()) {
                        searchQueryEmpty = true
                    } else {
                        searchQueryEmpty = false
                        itemsIndexed(filterdList) { index, item ->
                            RecyclerItems(index, item, clipboardManager)
                        }
                    }
                }
                AnimatedVisibility(
                    visible = searchQueryEmpty,
                    enter = fadeIn(
                        initialAlpha = 0.4f
                    ),
                    exit = fadeOut(
                        animationSpec = tween(durationMillis = 250)
                    ), modifier = Modifier.layoutId("ivNotFound")
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_not_found),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}


@Composable
fun RecyclerItems(index: Int, item: CredentialsModel, clipboardManager: ClipboardManager) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(
                2.dp,
                color = Color(0xffff1f1f1),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        val (ivIcon, name, ivCopy) = createRefs()
        val color = if (index % 2 == 0) {
            Color(0xfffFF6464)
        } else
            Color(0xfff545974)
        Text(
            text = item.name[0].toString(),
            color = Color.White,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            fontFamily = BebasNue(),
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .background(color, RoundedCornerShape(10.dp))
                .padding(8.dp)
                .size(30.dp)
                .constrainAs(ivIcon) {
                    start.linkTo(parent.start, 8.dp)
                    centerVerticallyTo(parent)
                }
        )
        Text(
            text = item.name,
            color = Color(0xfff545974),
            fontSize = 16.sp,
            fontFamily = Poppins(),
            fontWeight = FontWeight.SemiBold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.constrainAs(name) {
                start.linkTo(ivIcon.end, 16.dp)
                end.linkTo(ivCopy.start, 16.dp)
                width = Dimension.fillToConstraints
                centerVerticallyTo(parent)
            }
        )
        IconButton(onClick = {
            clipboardManager.setText(AnnotatedString(item.password))
        }, modifier = Modifier.constrainAs(ivCopy) {
            end.linkTo(parent.end, 8.dp)
            centerVerticallyTo(parent)
        }) {
            Icon(
                imageVector = Icons.Outlined.ContentCopy,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color(0xfffFF6464),
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun HeaderBoxes(count: Int, string: String, modifier: Modifier) {
    Column(
        modifier = modifier.composed {
            this
                .height(140.dp)
                .fillMaxWidth()
                .background(Color(0xffff1f1f1), RoundedCornerShape(10.dp))
        },
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(
            text = count.toString(),
            style = Typography.h1,
            modifier = Modifier.padding(start = 24.dp)
        )
        Text(
            text = string,
            fontFamily = Poppins(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color(0xfff545974),
            modifier = Modifier.padding(start = 24.dp)
        )
    }
}

fun homeConstraintSet(): ConstraintSet {
    return ConstraintSet {
        val ivIcon = createRefFor("ivIcon")
        val tvName = createRefFor("tvName")
        val headerContainer = createRefFor("headerContainer")
        val etSearch = createRefFor("etSearch")
        val rv = createRefFor("rv")
        val ivNotFound = createRefFor("ivNotFound")

        constrain(ivIcon) {
            start.linkTo(parent.start, 24.dp)
            top.linkTo(parent.top, 20.dp)
        }
        constrain(tvName) {
            start.linkTo(parent.start, 20.dp)
            top.linkTo(ivIcon.bottom)
        }
        constrain(headerContainer) {
            width = Dimension.matchParent
            start.linkTo(parent.start, 24.dp)
            end.linkTo(parent.end, 24.dp)
            top.linkTo(tvName.bottom, 24.dp)
        }
        constrain(etSearch) {
            width = Dimension.matchParent
            start.linkTo(parent.start, 24.dp)
            end.linkTo(parent.end, 24.dp)
            top.linkTo(headerContainer.bottom, 16.dp)
        }
        constrain(rv) {
            width = Dimension.matchParent
            start.linkTo(parent.start, 24.dp)
            end.linkTo(parent.end, 24.dp)
            top.linkTo(etSearch.bottom, 16.dp)
            bottom.linkTo(parent.bottom)
            height = Dimension.fillToConstraints
        }
        constrain(ivNotFound) {
            width = Dimension.matchParent
            start.linkTo(parent.start, 24.dp)
            end.linkTo(parent.end, 24.dp)
            top.linkTo(etSearch.bottom, 16.dp)
            bottom.linkTo(parent.bottom)
            height = Dimension.fillToConstraints
        }
    }
}

