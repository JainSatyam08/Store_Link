package com.example.storelink.components.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
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


@Composable
fun HomeTopBar(
    notifCount : Int,
    cartCount  : Int,
    nav: NavHostController,
    //onCartClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardWhite)
            .padding(bottom = 16.dp, start = 12.dp, end = 12.dp),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // ── Left: Hamburger + Brand Name ───────────────────────────────────
        Row(
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu",
                tint               = NavyBlue,
                modifier           = Modifier.size(22.dp)
            )
            Column {
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = NavyBlue,    fontWeight = FontWeight.Bold)) { append("Store") }
                        withStyle(SpanStyle(color = BrandOrange, fontWeight = FontWeight.Bold)) { append("Link") }
                    },
                    fontSize = 20.sp
                )
                Text("From Store to Doorstep", fontSize = 10.sp, color = HintGray)
            }
        }

        // ── Right: Notification + Cart icons with badge ────────────────────
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            BadgeIcon(iconRes = Icons.Filled.Notifications, count = notifCount, onClick = {nav.navigate("notification")})
            BadgeIcon(iconRes = Icons.Filled.ShoppingCart, count = cartCount,  onClick = {nav.navigate("cart")})
        }
    }
}

@Composable
fun BadgeIcon(iconRes: ImageVector, count: Int, onClick: () -> Unit) {
    Box(modifier = Modifier.size(36.dp).clickable { onClick() }) {
        Icon(
            imageVector = iconRes,
            contentDescription = null,
            tint               = NavyBlue,
            modifier           = Modifier.size(24.dp).align(Alignment.Center)
        )
        if (count > 0) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(BrandOrange)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Text(count.toString(), color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold,
                    )
            }
        }
    }
}
