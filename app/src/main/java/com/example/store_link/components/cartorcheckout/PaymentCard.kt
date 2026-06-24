package com.example.storelink.components.cartorcheckout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.ui.theme.BrandOrange

import com.example.store_link.ui.theme.GreenTag
import com.example.store_link.ui.theme.GreenTagText
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg
import com.example.store_link.ui.theme.BorderGray

@Composable
fun PaymentMethodCard(
    selectedMethod   : String,
    onMethodSelected : (String) -> Unit,
    modifier         : Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, BorderGray, RoundedCornerShape(14.dp))
            .padding(16.dp)
    ) {
        Text("Payment Method", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
        Spacer(modifier = Modifier.height(12.dp))

        // ── COD Option ────────────────────────────────────────────────────────
        val isSelected = selectedMethod == "COD"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .border(1.5.dp, if (isSelected) BrandOrange else BorderGray, RoundedCornerShape(10.dp))
                .clickable { onMethodSelected("COD") }
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Radio button
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .border(2.dp, if (isSelected) BrandOrange else BorderGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(BrandOrange))
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Cash icon
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(PeachBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Money, // 👈 cash icon — replace with proper money icon
                    contentDescription = null,
                    tint = BrandOrange,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Title + subtitle
            Column(modifier = Modifier.weight(1f)) {
                Text("Cash on Delivery (COD)", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = NavyBlue)
                Text("Pay when your order is delivered", fontSize = 12.sp, color = HintGray)
            }

            // "Recommended" tag
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(GreenTag)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text("Recommended", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = GreenTagText)
            }

            Spacer(modifier = Modifier.width(6.dp))

            // Dropdown arrow (for future expand — e.g. more payment options)
           /* Icon(
                painter = painterResource(id = android.R.drawable.arrow_down_float),
                contentDescription = null,
                tint = HintGray,
                modifier = Modifier.size(14.dp)
            )*/
        }
    }
}
