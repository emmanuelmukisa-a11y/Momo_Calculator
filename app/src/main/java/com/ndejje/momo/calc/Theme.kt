package com.ndejje.momo.calc

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = NavyBlue,
    onPrimary = White,
    secondary = BrandGold,
    onSecondary= NavyBlueDark,
    background= LightGrey,
    onBackground= DarkSurface,
    surface = White,
    onSurface = DarkSurface,
    error = ErrorRed,
    onError = OnErrorWhite
)

@Composable
fun MoMoAppTheme(
    content: @Composable () -> Unit
){
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = MomoTypography,
        shapes = MoMoShapes,
        content = content
    )

}