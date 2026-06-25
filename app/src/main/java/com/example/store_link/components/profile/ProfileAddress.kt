package com.example.store_link.components.profile

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.screen.AddressIconType



import com.example.store_link.screen.SavedAddress
import com.example.store_link.ui.theme.BorderGray
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.DescGray
import com.example.store_link.ui.theme.GreenTag
import com.example.store_link.ui.theme.GreenTagText
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.OtherIconBg
import com.example.store_link.ui.theme.OtherIconColor
import com.example.store_link.ui.theme.PeachBg
import com.example.store_link.ui.theme.WorkIconBg
import com.example.store_link.ui.theme.WorkIconColor

@Composable
fun ProfileAddressRow(
    address    : SavedAddress,
    onClick    : () -> Unit,
    onMenuClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderGray, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            // Type icon (reusing same color logic as MyAddressesScreen)
            ProfileAddressTypeIcon(iconType = address.iconType)

            Spacer(modifier = Modifier.width(12.dp))

            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(address.label, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
                if (address.isDefault) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(GreenTag)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("Default", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = GreenTagText)
                    }
                }
            }

            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_more),
                contentDescription = "More options",
                tint = NavyBlue,
                modifier = Modifier.size(18.dp).clickable { onMenuClick() }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.padding(start = 48.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(address.fullAddress, fontSize = 12.sp, color = DescGray, lineHeight = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(address.phone, fontSize = 12.sp, color = DescGray)
            }
            Icon(
                painter = painterResource(id = android.R.drawable.ic_media_play),
                contentDescription = null,
                tint = HintGray,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}
@Composable
fun ProfileAddressTypeIcon(iconType: AddressIconType) {
    val (bgColor, iconColor, iconRes) = when (iconType) {
        AddressIconType.HOME  -> Triple(Color(0xFFFFE0CC), BrandOrange, Icons.Filled.Home)
        AddressIconType.WORK  -> Triple(WorkIconBg,         WorkIconColor,  Icons.Filled.Work)
        AddressIconType.OTHER -> Triple(OtherIconBg,        OtherIconColor, Icons.Filled.LocationSearching)
    }

    Box(
        modifier = Modifier.size(36.dp).clip(CircleShape).background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = iconRes, contentDescription = null, tint = iconColor, modifier = Modifier.size(18.dp))
    }
}

// ─── Component 5: Add New Address Row ────────────────────────────────────────
/**
 * AddNewAddressRow
 * Dashed-style peach row with "+" icon + "Add New Address" text.
 */
@Composable
fun AddNewAddressRow(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(PeachBg)
            .border(1.dp, BrandOrange, RoundedCornerShape(12.dp))   // TODO: dashed effect ke liye Canvas use karo
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_input_add),
            contentDescription = null,
            tint = BrandOrange,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Add New Address", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = BrandOrange)
    }
}
