package com.example.storelink.components.productdetail

import com.example.store_link.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.ui.theme.BestsellerGreen

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
import com.example.store_link.ui.theme.RatingGreen
import com.example.store_link.ui.theme.SidebarBg

@Composable
fun ProductImageSection(
    currentImageIndex: Int,
    totalImages       : Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
            .background(Color(0xFFF5F5F5))
    ) {
        // 👈 Asli product image yahan daalna — R.drawable.img_aashirvaad_atta_5kg
        Image(
            painter            = painterResource(id = R.drawable.grocery), // 👈 placeholder, replace karo
            contentDescription = "Product Image",
            modifier           = Modifier.fillMaxSize(),
            contentScale       = ContentScale.Crop
        )

        // ── "Bestseller" badge ────────────────────────────────────────────
        Box(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .clip(RoundedCornerShape(20.dp))
                .background(BestsellerGreen)
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text("Bestseller", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }

        // ── Image counter "1/5" ──────────────────────────────────────────
        Box(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)
                .clip(CircleShape)
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text("$currentImageIndex/$totalImages", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = NavyBlue)
        }

        // ── Zoom icon ──────────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { /* TODO: open fullscreen zoom view */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.ZoomIn,
                contentDescription = "Zoom",
                tint = NavyBlue,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
@Composable
fun ProductTitleSection(
    name       : String,
    rating     : Float,
    ratingCount: Int,
    boughtText : String
) {
    Column {
        Text(name, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
        Spacer(modifier = Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = RatingGreen,
                modifier = Modifier.size(16.dp)
            )
            Text("$rating", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = NavyBlue)
            Text("(${formatCount(ratingCount)} ratings)", fontSize = 13.sp, color = HintGray)
            Text("|", fontSize = 13.sp, color = HintGray)
            Text(boughtText, fontSize = 13.sp, color = HintGray)
        }
    }
}
private fun formatCount(count: Int): String {
    return "%,d".format(count)
}

@Composable
fun PriceRow(price: Int, originalPrice: Int, discountPct: Int) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("₹$price", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
        Text("₹$originalPrice", fontSize = 16.sp, color = HintGray, textDecoration = TextDecoration.LineThrough)
        Box(
            modifier = Modifier
                .background(DiscountBg, RoundedCornerShape(6.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text("$discountPct% OFF", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = DiscountText)
        }
    }
}
