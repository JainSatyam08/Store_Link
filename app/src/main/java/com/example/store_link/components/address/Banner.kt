package com.example.store_link.components.address

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.DescGray
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg

@Composable
fun DeliveryPromoBanner(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(PeachBg)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // ── Pin icon circle ──────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFE0CC)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_mylocation), // 👈 pin icon
                contentDescription = null,
                tint = BrandOrange,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        // ── Text content ──────────────────────────────────────────────────────
        Column(modifier = Modifier.weight(1f)) {
            Text("Deliver to the right place", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Add or manage your delivery addresses for a smooth experience.",
                fontSize   = 12.sp,
                color      = DescGray,
                lineHeight = 16.sp
            )
        }

        // 👈 Yahan house + pin illustration image lagegi (design mein right side)
        Image(
            painter            = painterResource(id = android.R.drawable.ic_menu_gallery), // 👈 replace with R.drawable.img_house_illustration
            contentDescription = "Delivery illustration",
            modifier           = Modifier.size(64.dp),
            contentScale       = ContentScale.Fit
        )
    }
}
