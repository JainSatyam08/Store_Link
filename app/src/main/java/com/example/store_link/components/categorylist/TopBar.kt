package com.example.storelink.components.categorylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
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
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.DescGray
import com.example.store_link.ui.theme.GreenTag
import com.example.store_link.ui.theme.GreenTagText
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg
import com.example.store_link.ui.theme.BorderGray
import com.example.store_link.ui.theme.DiscountBg
import com.example.store_link.ui.theme.DiscountText
import com.example.store_link.ui.theme.FreeDeliveryBg
import com.example.store_link.ui.theme.FreeDeliveryGreen
import com.example.store_link.ui.theme.SidebarBg


@Composable
fun TopBarSection(
    title      : String,
    subtitle   : String,
    cartCount  : Int,
    onBackClick: () -> Unit,
    onCartClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Back arrow
        Icon(
            painter            = painterResource(id = android.R.drawable.ic_menu_revert),
            contentDescription = "Back",
            tint               = NavyBlue,
            modifier           = Modifier
                .size(24.dp)
                .clickable { onBackClick() }
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Title + subtitle
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            Text(subtitle, fontSize = 13.sp, color = HintGray)
        }

        // Cart icon with badge
        Box(modifier = Modifier.size(28.dp).clickable { onCartClick() }) {
            Icon(
                imageVector        = Icons.Filled.ShoppingCart,
                contentDescription = "Cart",
                tint               = NavyBlue,
                modifier           = Modifier.size(26.dp)
            )
            if (cartCount > 0) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(BrandOrange)
                        .align(Alignment.TopEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Text(cartCount.toString(), color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
