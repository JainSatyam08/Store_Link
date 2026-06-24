package com.example.storelink.components.homescreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.R
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
import com.example.store_link.ui.theme.DiscountText
import com.example.store_link.ui.theme.FreeDeliveryBg
import com.example.store_link.ui.theme.FreeDeliveryGreen
import com.example.store_link.ui.theme.SidebarBg

@Composable
fun FeaturesStrip(modifier: Modifier = Modifier) {
    val features = listOf(
        Triple(R.drawable.free_dilevery, "Free Delivery",   "On orders above ₹499"),
        Triple(R.drawable.secure_payment,   "Secure Payments", "100% secure transactions"),
        Triple(R.drawable.return_easy,   "Easy Returns",    "Hassle-free returns"),
        Triple(R.drawable.support,  "24/7 Support",    "We're here to help you")
    )

    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        features.forEach { (iconRes, title, subtitle) ->
            FeatureItem(iconRes = iconRes, title = title, subtitle = subtitle, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun FeatureItem(iconRes: Int, title: String, subtitle: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(4.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier.size(32.dp).clip(CircleShape).border(1.dp, BrandOrange, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = painterResource(id = iconRes), contentDescription = null, tint = BrandOrange, modifier = Modifier.size(16.dp))
        }
        Column {
            Text(title, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = NavyBlue, maxLines = 1)
            Text(subtitle, fontSize = 9.sp, color = HintGray, maxLines = 2, lineHeight = 12.sp)
        }
    }
}
