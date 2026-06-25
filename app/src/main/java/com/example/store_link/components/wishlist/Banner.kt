package com.example.store_link.components.wishlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.R
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg


@Composable
fun WishlistBanner(itemCount: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(PeachBg)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Heart icon circle
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(BrandOrange),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text("$itemCount Items in Wishlist", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            Spacer(modifier = Modifier.height(2.dp))
            Text("Move items to cart and shop your favorites!", fontSize = 12.sp, color = Color(0xFF555555))
        }

        // 👈 Yahan shopping bag illustration image lagegi (design mein right side)
        Image(
            painter            = painterResource(id = android.R.drawable.ic_menu_gallery), // 👈 replace with R.drawable.img_wishlist_bag
            contentDescription = "Wishlist illustration",
            modifier           = Modifier.size(56.dp),
            contentScale       = ContentScale.Fit
        )
    }
}
