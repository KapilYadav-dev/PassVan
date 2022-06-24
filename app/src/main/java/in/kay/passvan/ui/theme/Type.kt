package `in`.kay.passvan.ui.theme

import `in`.kay.passvan.R
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val Typography = Typography(
    h1 = TextStyle(fontFamily = BebasNue(), fontWeight = FontWeight.Normal),
    defaultFontFamily = Poppins()
)


fun Poppins() = FontFamily(
    Font(R.font.font_poppins_light, FontWeight.Light),
    Font(R.font.font_poppins_regular, FontWeight.Normal),
    Font(R.font.font_poppins_semibold, FontWeight.SemiBold),
)


fun BebasNue() = FontFamily(
    Font(R.font.font_bebas_neue, FontWeight.Normal)
)
