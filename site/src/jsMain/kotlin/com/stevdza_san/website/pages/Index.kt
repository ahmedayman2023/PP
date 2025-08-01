package com.stevdza_san.website.pages

import androidx.compose.runtime.*
import com.stevdza_san.website.components.ProfileCard
import com.stevdza_san.website.components.ThemeSwitchButton
import com.stevdza_san.website.components.SocialLinks
import com.stevdza_san.website.components.SkillsSection
import com.stevdza_san.website.components.QuickLinks
import com.stevdza_san.website.util.Res
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.px
import kotlinx.browser.localStorage

@Page
@Composable
fun HomePage() {
    var colorMode by ColorMode.currentState

    LaunchedEffect(colorMode) {
        val savedTheme = localStorage.getItem(Res.String.SAVED_THEME) ?: ColorMode.LIGHT.name
        colorMode = ColorMode.valueOf(savedTheme)
    }

    Box(
        Modifier
            .fillMaxSize()
            .backgroundImage(
                linearGradient(
                    dir = LinearGradient.Direction.ToRight,
                    from = if (colorMode.isLight) Res.Theme.GRADIENT_ONE.color
                    else Res.Theme.GRADIENT_ONE_DARK.color,
                    to = if (colorMode.isLight) Res.Theme.GRADIENT_TWO.color
                    else Res.Theme.GRADIENT_TWO_DARK.color
                )
            )
            .styleModifier {
                property("overflow-y", "auto")
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(topBottom = 24.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Theme switcher positioned at the top right
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(right = 24.px),
                contentAlignment = Alignment.TopEnd
            ) {
                ThemeSwitchButton(
                    colorMode = colorMode,
                    onClick = {
                        colorMode = colorMode.opposite
                        localStorage.setItem(Res.String.SAVED_THEME, colorMode.name)
                    }
                )
            }

            // Main content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .maxWidth(800.px)
                    .padding(24.px),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile section
                ProfileCard(colorMode = colorMode)
                
                Spacer(Modifier.height(32.px))
                
                // Social links
                SocialLinks(colorMode = colorMode)
                
                Spacer(Modifier.height(32.px))
                
                // Skills section
                SkillsSection(colorMode = colorMode)
                
                Spacer(Modifier.height(32.px))
                
                // Quick links section
                QuickLinks(colorMode = colorMode)
            }
        }
    }
}
