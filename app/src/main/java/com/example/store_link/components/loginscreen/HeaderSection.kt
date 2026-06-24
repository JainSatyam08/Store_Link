package com.example.storelink.components.loginscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.store_link.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.store_link.ui.theme.BannerBg
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.DescGray
import com.example.store_link.ui.theme.GreenTag
import com.example.store_link.ui.theme.GreenTagText
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg
import com.example.store_link.ui.theme.BorderGray
import com.example.store_link.ui.theme.CardWhite
import com.example.store_link.ui.theme.DiscountBg
import com.example.store_link.ui.theme.DiscountGreen
import com.example.store_link.ui.theme.DiscountText
import com.example.store_link.ui.theme.FreeDeliveryBg
import com.example.store_link.ui.theme.FreeDeliveryGreen
import com.example.store_link.ui.theme.SidebarBg
import com.example.store_link.ui.theme.SubtextGray
import com.example.store_link.ui.theme.WarmBg

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)        // screen ke ~40% height
            .background(WarmBg)
    ) {
        // Back arrow — top left
        Icon(
            painter           = painterResource(id = android.R.drawable.ic_menu_revert),
            contentDescription = "Back",
            tint              = NavyBlue,
            modifier          = Modifier
                .padding(16.dp)
                .size(24.dp)
                .align(Alignment.TopStart)
                //.clickable { onBack() }
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, start = 24.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ── Left: Logo + Brand Name + Tagline ─────────────────────────
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Logo image
                Image(
                    painter            = painterResource(id = R.drawable.logo),
                    contentDescription = "StoreLink Logo",
                    modifier           = Modifier.size(80.dp)
                )

                // "StoreLink" — two color text
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = NavyBlue)) { append("Store") }
                        withStyle(SpanStyle(color = BrandOrange)) { append("Link") }
                    },
                    fontSize   = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                // Tagline
                Text(
                    text     = "From Store to Doorstep",
                    fontSize = 12.sp,
                    color    = SubtextGray
                )

                // Orange divider (same as splash)
                OrangeDividerSmall()
            }

            // ── Right: Shopping Illustration ──────────────────────────────
            /*Image(
                painter            = painterResource(id = R.drawable.ic_login_illustration),
                contentDescription = null,
                modifier           = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(bottom = 16.dp)
            )*/
        }
    }
}

@Composable
fun OrangeDividerSmall() {
    Row(
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(Modifier.width(18.dp).height(2.dp).background(BrandOrange))
        Box(Modifier.size(4.dp).clip(androidx.compose.foundation.shape.CircleShape).background(BrandOrange))
        Box(Modifier.width(18.dp).height(2.dp).background(BrandOrange))
    }
}