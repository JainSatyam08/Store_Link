package com.example.store_link.components.address

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.NavyBlue


@Composable
fun AddressesTopBar(
    onBackClick   : () -> Unit,
    onMapViewClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Back arrow
        Icon(
            painter            = painterResource(id = android.R.drawable.ic_menu_revert),
            contentDescription = "Back",
            tint               = NavyBlue,
            modifier           = Modifier
                .size(24.dp)
                .padding(top = 4.dp)
                .clickable { onBackClick() }
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Title + subtitle
        Column(modifier = Modifier.weight(1f)) {
            Text("My Addresses", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            Text("Choose a delivery address", fontSize = 13.sp, color = HintGray)
        }

        // ── Map View link ────────────────────────────────────────────────────
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(top = 6.dp)
                .clickable { onMapViewClick() }
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_mylocation), // 👈 location pin icon — replace with proper one
                contentDescription = null,
                tint = BrandOrange,
                modifier = Modifier.size(16.dp)
            )
            Text("Map View", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = BrandOrange)
        }
    }
}
