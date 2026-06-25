package com.example.store_link.components.address

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
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.OtherIconBg
import com.example.store_link.ui.theme.OtherIconColor
import com.example.store_link.ui.theme.PeachBg
import com.example.store_link.ui.theme.WorkIconBg
import com.example.store_link.ui.theme.WorkIconColor

@Composable
fun AddressCard(
    address    : SavedAddress,
    isSelected : Boolean,
    onSelect   : () -> Unit,
    onMenuClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(if (isSelected) PeachBg else Color.White)
            .border(
                width = if (isSelected) 1.5.dp else 1.dp,
                color = if (isSelected) BrandOrange else BorderGray,
                shape = RoundedCornerShape(14.dp)
            )
            .clickable { onSelect() }
            .padding(16.dp)
    ) {
        // ── Top row: radio + icon + label/tag + menu ─────────────────────────
        Row(verticalAlignment = Alignment.Top) {

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

            // Type icon (Home/Work/Other — different bg color)
            AddressTypeIcon(iconType = address.iconType)

            Spacer(modifier = Modifier.width(12.dp))

            // Label + Default tag
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(address.label, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
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

            // 3-dot menu
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_more),
                contentDescription = "More options",
                tint = NavyBlue,
                modifier = Modifier.size(20.dp).clickable { onMenuClick() }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // ── Name + address (indented to align with label text) ──────────────
        Column(modifier = Modifier.padding(start = 42.dp)) {
            Text(address.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = NavyBlue)
            Spacer(modifier = Modifier.height(4.dp))
            Text(address.fullAddress, fontSize = 13.sp, color = DescGray, lineHeight = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_call),
                    contentDescription = null,
                    tint = HintGray,
                    modifier = Modifier.size(14.dp)
                )
                Text(address.phone, fontSize = 13.sp, color = DescGray)
            }
        }
    }
}

/**
 * AddressTypeIcon
 * Circle icon jiska color address type pe depend karta hai:
 *  - HOME  → peach bg + orange home icon
 *  - WORK  → light blue bg + blue briefcase icon
 *  - OTHER → light green bg + green pin icon
 */
@Composable
private fun AddressTypeIcon(iconType: AddressIconType) {
    val (bgColor, iconColor, iconRes) = when (iconType) {
        AddressIconType.HOME  -> Triple(Color(0xFFFFE0CC), BrandOrange, Icons.Filled.Home)
        AddressIconType.WORK  -> Triple(WorkIconBg,         WorkIconColor,  Icons.Filled.Work)
        AddressIconType.OTHER -> Triple(OtherIconBg,        OtherIconColor, Icons.Filled.LocationSearching)
    }

    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = iconRes,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun AddNewAddressCard(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(PeachBg)
            .border(1.5.dp, BrandOrange, RoundedCornerShape(14.dp))   // TODO: dashed effect ke liye Canvas use karo
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // "+" icon circle
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .border(1.5.dp, BrandOrange, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = BrandOrange)
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text("Add New Address", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = BrandOrange)
            Text("Add a new delivery address", fontSize = 13.sp, color = DescGray)
        }

        Icon(
            painter = painterResource(id = android.R.drawable.ic_media_play), // 👈 chevron right — replace with proper arrow icon
            contentDescription = null,
            tint = BrandOrange,
            modifier = Modifier.size(16.dp)
        )
    }
}
@Composable
fun SafeAndSecureNote() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_secure),
            contentDescription = null,
            tint = GreenTagText,
            modifier = Modifier.size(15.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text("Your addresses are safe and secure with us.", fontSize = 13.sp, color = HintGray)
    }
}