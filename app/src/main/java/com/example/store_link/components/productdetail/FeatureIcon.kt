package com.example.store_link.components.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.store_link.ui.theme.FreeDeliveryGreen
import com.example.store_link.ui.theme.IconCircleBg
import com.example.store_link.ui.theme.NavyBlue

@Composable
fun FeatureIconsRow() {
    val features = listOf(
        Triple(android.R.drawable.ic_secure, "100% Natural", "Natural"),                    // 👈 shield icon
        Triple(android.R.drawable.ic_menu_compass, "Source of", "Dietary Fiber"),            // 👈 fiber icon
        Triple(android.R.drawable.ic_delete, "No Maida", "Added"),                          // 👈 no-symbol icon
        Triple(android.R.drawable.ic_menu_myplaces, "Rich in", "Nutrients")                  // 👈 nutrient icon
    )

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        features.forEach { (iconRes, line1, line2) ->
            FeatureIconItem(iconRes = iconRes, line1 = line1, line2 = line2, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun FeatureIconItem(iconRes: Int, line1: String, line2: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(IconCircleBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = painterResource(id = iconRes), contentDescription = null, tint = FreeDeliveryGreen, modifier = Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(line1, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = NavyBlue, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        Text(line2, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = NavyBlue, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}
