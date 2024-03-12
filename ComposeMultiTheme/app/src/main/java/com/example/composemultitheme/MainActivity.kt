package com.example.composemultitheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composemultitheme.ui.theme.ComposeMultiThemeTheme
import com.example.composemultitheme.ui.theme.ThemeEnum

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMultiThemeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    ThemeSwitcherView()
                }
            }
        }
    }
}

@Composable
fun ThemeSwitcherView() {
    var themeEnum by remember {
        mutableStateOf(ThemeEnum.SkyBlue)
    }

    LazyRow {
        items(ThemeEnum.values()) { theme ->
            ThemeCard(theme = theme, onThemeSelected = { selectedTheme ->
                themeEnum = selectedTheme
            })
        }
    }
}

@Composable
fun ThemeCard(theme: ThemeEnum, onThemeSelected: (ThemeEnum) -> Unit) {
    Surface(
        modifier = Modifier.size(100.dp),
        color = theme.themeColor
    ) {
        Text(
            text = theme.themeName,
            modifier = Modifier.clickable {
                onThemeSelected(theme)
            }
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeMultiThemeTheme {
        Greeting("Android")
    }
}