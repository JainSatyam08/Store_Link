package com.example.storelink.components.categorylist

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
import com.example.store_link.ui.theme.BorderGray
import com.example.store_link.ui.theme.FreeDeliveryBg
import com.example.store_link.ui.theme.FreeDeliveryGreen


@Composable
fun ProductCountSortRow(
    productCount: Int,
    onSortClick : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text("$productCount Products", fontSize = 13.sp, color = HintGray)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {

            // Sort dropdown (static text for now — wire to DropdownMenu when needed)
            OutlinedButton(
                onClick  = onSortClick,
                shape    = RoundedCornerShape(10.dp),
                border   = androidx.compose.foundation.BorderStroke(1.dp, BorderGray),
                colors   = ButtonDefaults.outlinedButtonColors(contentColor = NavyBlue),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text("Sort by: Popular", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = android.R.drawable.arrow_down_float),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
            }

            // Swap/sort icon button (the ↑↓ icon in design)
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .border(1.dp, BorderGray, RoundedCornerShape(10.dp))
                    .clickable { /* TODO: toggle sort order */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.SwapVert, // 👈 up-down arrows icon
                    contentDescription = "Sort order",
                    tint = NavyBlue,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}