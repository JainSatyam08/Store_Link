package com.example.storelink.screen

import androidx.navigation.NavHostController
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.NavyBlue

import kotlinx.coroutines.launch



import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.delay
import com.example.store_link.R
import com.example.store_link.ui.theme.LightBg
import com.example.store_link.ui.theme.SubtitleGray


// ─── Brand Colors ───────────────────────────────────────────────────────────


/**
 * SplashScreen
 *
 * Ye screen app open hote hi dikhti hai.
 * Animations ek ke baad ek trigger hote hain:
 *   1. Logo  → scale + fade in
 *   2. Brand name → slide up + fade in
 *   3. Tagline → fade in
 *   4. Bottom illustration → fade in
 *   5. Auto-navigate after 2.5s
 *
 * @param onSplashComplete  callback jab animation khatam ho, navigate karo aage
 */
@Composable
fun SplashScreen(
    nav: NavHostController
) {
    // ── Animation states ───────────────────────────────────────────────────

    // Logo: starts small & invisible → grows to full size
    val logoScale = remember { Animatable(0.4f) }
    val logoAlpha = remember { Animatable(0f) }

    // Brand text: starts slightly below → slides up
    val textOffsetY = remember { Animatable(40f) }
    val textAlpha   = remember { Animatable(0f) }

    // Tagline: simple fade in
    val taglineAlpha = remember { Animatable(0f) }

    // Bottom illustration: fade in last
    val illustrationAlpha = remember { Animatable(0f) }

    // ── Run animations sequentially ────────────────────────────────────────
    LaunchedEffect(Unit) {

        // Step 1 — Logo animates in (scale + fade), bouncy feel
        launch {
            logoScale.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness    = Spring.StiffnessMedium
                )
            )
        }
        launch {
            logoAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 600, easing = EaseOut)
            )
        }

        // Step 2 — Brand name slides up after 400ms
        delay(400)
        launch {
            textOffsetY.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 500, easing = EaseOutCubic)
            )
        }
        launch {
            textAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500)
            )
        }

        // Step 3 — Tagline fades in
        delay(300)
        taglineAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 400)
        )

        // Step 4 — Bottom illustration fades in
        delay(200)
        illustrationAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500)
        )

        // Step 5 — Wait then navigate
        delay(800)
        nav.navigate("signin") {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }

    // ── Root Container ─────────────────────────────────────────────────────
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBg),
        contentAlignment = Alignment.Center
    ) {

        // ── Top Section: Logo + Brand Name ─────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(bottom = 80.dp),         // push up a bit from center
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // ── App Logo Image ────────────────────────────────────────────
            Image(
                painter = painterResource(id = R.drawable.logo), // 👈 apna logo yahan rakh
                contentDescription = "StoreLink Logo",
                modifier = Modifier
                    .size(160.dp)
                    .scale(logoScale.value)        // animated scale
                    .alpha(logoAlpha.value),       // animated opacity
                contentScale = ContentScale.Fit
            )

            // ── Brand Name: "Store" + "Link" ─────────────────────────────
            // Offset Y se slide-up effect milta hai
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = NavyBlue)) {
                        append("Store")
                    }
                    withStyle(style = SpanStyle(color = BrandOrange)) {
                        append("Link")
                    }
                },
                fontSize   = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier   = Modifier
                    .offset(y = textOffsetY.value.dp)  // animated slide
                    .alpha(textAlpha.value)             // animated fade
            )

            // ── Divider Dots + Tagline ────────────────────────────────────
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.alpha(taglineAlpha.value)  // fades in together
            ) {
                // Small decorative orange dot separator (matches design)
                OrangeDivider()

                // Tagline text
                Text(
                    text       = "From Store to Doorstep",
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color      = SubtitleGray
                )
            }
        }

        // ── Bottom Illustration ────────────────────────────────────────────
        // Store → path → house delivery scene
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .alpha(illustrationAlpha.value)      // fades in last
                .padding(bottom = 60.dp)
        ) {
            BottomIllustration()
        }

        // ── Page Dots Indicator ───────────────────────────────────────────
        // Bottom center mein 3 dots (2nd dot active/orange)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment     = Alignment.CenterVertically
        ) {
            PageDot(isActive = false)
            PageDot(isActive = true)   // middle dot active
            PageDot(isActive = false)
        }
    }
}

// ─── Small Reusable Components ───────────────────────────────────────────────

/**
 * OrangeDivider
 * Design mein ek chota sa orange line + dot separator hai brand name ke neeche.
 */
@Composable
private fun OrangeDivider() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(2.dp)
                .background(BrandOrange)
        )
        Box(
            modifier = Modifier
                .size(5.dp)
                .clip(CircleShape)
                .background(BrandOrange)
        )
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(2.dp)
                .background(BrandOrange)
        )
    }
}

/**
 * PageDot
 * Bottom mein 3 dots — active wala bada aur orange hoga.
 *
 * @param isActive  kya yeh current page ka dot hai?
 */
@Composable
private fun PageDot(isActive: Boolean) {
    Box(
        modifier = Modifier
            .size(if (isActive) 10.dp else 7.dp)   // active dot slightly bigger
            .clip(CircleShape)
            .background(
                if (isActive) BrandOrange else Color(0xFFCCCCCC)
            )
    )
}


@Composable
private fun BottomIllustration() {
    // TODO: Replace with actual illustration asset jab ho
    // Image(
    //     painter = painterResource(id = R.drawable.ic_splash_illustration),
    //     contentDescription = null,
    //     modifier = Modifier.fillMaxWidth(),
    //     contentScale = ContentScale.FillWidth
    // )

    // ── Placeholder: simple row with text labels ───────────────────────────
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.Bottom
    ) {
        // Store icon placeholder
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(90.dp, 70.dp)
                    .background(Color(0xFFFFEDD5), shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("🏪", fontSize = 36.sp)
            }
        }

        // Location pin placeholder
        Text("📍", fontSize = 28.sp, modifier = Modifier.padding(bottom = 8.dp))

        // House icon placeholder
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(90.dp, 70.dp)
                    .background(Color(0xFFFFEDD5), shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("🏠", fontSize = 36.sp)
            }
        }
    }
}
