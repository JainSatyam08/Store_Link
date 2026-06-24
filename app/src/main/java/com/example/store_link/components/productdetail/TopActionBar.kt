package com.example.storelink.components.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.R
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.NavyBlue

@Composable
fun TopActionBar(
    isWishlisted   : Boolean,
    cartCount      : Int,
    onBackClick    : () -> Unit,
    onWishlistClick: () -> Unit,
    onCartClick    : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        // Back arrow
        Icon(
            imageVector       = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            tint               = NavyBlue,
            modifier           = Modifier.size(24.dp).clickable { onBackClick() }
        )

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.CenterVertically) {

            // Wishlist heart (no badge)
            Icon(
                imageVector = if (isWishlisted) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Wishlist",
                tint               = if (isWishlisted) BrandOrange else NavyBlue,
                modifier           = Modifier.size(24.dp).clickable { onWishlistClick() }
            )

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
}
