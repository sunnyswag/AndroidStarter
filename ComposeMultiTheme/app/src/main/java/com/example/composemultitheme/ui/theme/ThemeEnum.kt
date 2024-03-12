package com.example.composemultitheme.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

enum class ThemeEnum(val themeColor: Color, val themeId: Int, val themeName: String) {
    SkyBlue(SkyBlueColor,1, "Sky Blue"),
    Gray(GrayColor, 2, "Gray"),
    Green(GreenColor, 3, "Green"),
    Purple(PurpleColor, 4, "Purple");

    companion object {
        fun Int.toThemeEnum(): ThemeEnum {
            return values().first { it.themeId == this }
        }

        fun ThemeEnum.getThemeScheme() = lightColorScheme(
            primary = themeColor
        )
    }
}