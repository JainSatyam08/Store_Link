package com.example.store_link.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
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
import com.example.store_link.screen.AccountMenuItem
import com.example.store_link.ui.theme.HintGray

import com.example.store_link.ui.theme.LogoutBg
import com.example.store_link.ui.theme.LogoutRed
import com.example.store_link.ui.theme.NavyBlue

@Composable
fun AccountMenuRow(item: AccountMenuItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = null, tint = NavyBlue, modifier = Modifier.size(20.dp))
            Text(item.label, fontSize = 14.sp, color = NavyBlue)
        }
        Icon(
            painter = painterResource(id = android.R.drawable.ic_media_play),
            contentDescription = null,
            tint = HintGray,
            modifier = Modifier.size(14.dp)
        )
    }
}


@Composable
fun LogoutButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(LogoutBg)
            .clickable { onClick() }
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Logout, // 👈 logout icon — replace with proper one
            contentDescription = null,
            tint = LogoutRed,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Logout", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = LogoutRed)
    }
}

