package com.example.composemultitheme.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

enum class ThemeEnum(val themeColor: Color, val themeName: String) {
    SkyBlue(SkyBlueColor, "Sky Blue"),
    Gray(GrayColor, "Gray"),
    Green(GreenColor, "Green"),
    Purple(PurpleColor, "Purple");

    companion object {

        fun ThemeEnum.getLightThemeScheme() = lightColorScheme(
            primary = themeColor
        )

        fun ThemeEnum.getDarkThemeScheme() = darkColorScheme(
            primary = themeColor
        )
    }
}