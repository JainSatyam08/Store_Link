package com.example.store_link.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.R
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.NavyBlue


@Composable
fun ProfileTopBar(onSettingsClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Image(
                painter            = painterResource(id = R.drawable.logo),
                contentDescription = "StoreLink",
                modifier           = Modifier.size(32.dp)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = NavyBlue,    fontWeight = FontWeight.Bold)) { append("Store") }
                    withStyle(SpanStyle(color = BrandOrange, fontWeight = FontWeight.Bold)) { append("Link") }
                },
                fontSize = 20.sp
            )
        }

        Icon(
            painter            = painterResource(id = android.R.drawable.ic_menu_manage), // 👈 settings gear icon — replace
            contentDescription = "Settings",
            tint               = NavyBlue,
            modifier           = Modifier.size(24.dp).clickable { onSettingsClick() }
        )
    }
}
