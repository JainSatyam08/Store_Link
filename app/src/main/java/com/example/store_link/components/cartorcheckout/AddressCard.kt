package com.example.storelink.components.cartorcheckout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
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
import com.example.store_link.ui.theme.DescGray
import com.example.store_link.ui.theme.GreenTag
import com.example.store_link.ui.theme.GreenTagText
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg


@Composable
fun DeliveryAddressCard(
    name         : String,
    address      : String,
    phone        : String,
    onChangeClick: () -> Unit,
    modifier     : Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(PeachBg)
            .padding(16.dp)
    ) {
        // ── Header row: title + Change link ─────────────────────────────────
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.CenterVertically
        ) {
            Text("Delivery Address", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            Text(
                "Change",
                fontSize   = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color      = BrandOrange,
                modifier   = Modifier.clickable { onChangeClick() }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // ── Address content: home icon + details ────────────────────────────
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            // Home icon box
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, BrandOrange, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.house), // 👈 home icon — replace with proper one
                    contentDescription = "Home",
                    tint = BrandOrange,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column {
                // "Home" label + "Default" tag
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Home", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(GreenTag)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("Default", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = GreenTagText)
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = NavyBlue)
                Spacer(modifier = Modifier.height(2.dp))
                Text(address, fontSize = 13.sp, color = DescGray, lineHeight = 18.sp)

                Spacer(modifier = Modifier.height(8.dp))

                // Phone row
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = null,
                        tint = HintGray,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(phone, fontSize = 13.sp, color = DescGray)
                }
            }
        }
    }
}
