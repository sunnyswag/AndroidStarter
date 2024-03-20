package com.example.composemultitheme

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemultitheme.ui.theme.ComposeMultiThemeTheme
import com.example.composemultitheme.ui.theme.ThemeEnum
import com.sunnyswag.compose_theme.ResourcesManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var themeEnum by remember {
                mutableStateOf(ThemeEnum.SkyBlue)
            }
            ComposeMultiThemeTheme (themeEnum) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = themeColorResource(id = R.color.black, ResourcesManager.resources)
                ) {
                    ThemeSwitcherView { themeEnum = it }
                }
            }
        }
    }
}

@Composable
fun ThemeSwitcherView(selected: (ThemeEnum) -> Unit = {}) {
    LazyRow (
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(ThemeEnum.values()) { theme ->
            ThemeCard(theme = theme, onThemeSelected = selected)
        }
    }
}

@Composable
fun ThemeCard(theme: ThemeEnum, onThemeSelected: (ThemeEnum) -> Unit) {
    Surface(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable { onThemeSelected(theme) }
            .border(1.dp, Color.White, RoundedCornerShape(4.dp)),
        color = theme.themeColor
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = theme.themeName)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeMultiThemeTheme {
        ThemeSwitcherView()
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun themeColorResource(@ColorRes id: Int, resource: Resources): Color {
    val context = LocalContext.current
    return if (resource == context.resources) {
        changeToDefaultTheme(id)
    } else {
        val resName = context.resources.getResourceEntryName(id)
        val type = context.resources.getResourceTypeName(id)
        val resId = resource.getIdentifier(resName, type, resource.getResourcePackageName(id))
        Color(resource.getColor(resId, null))
    }
}

@Composable
fun changeToDefaultTheme(@ColorRes id: Int): Color {
    val context = LocalContext.current
    return Color(context.resources.getColor(id, context.theme))
}