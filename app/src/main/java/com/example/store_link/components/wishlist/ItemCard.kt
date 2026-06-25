package com.example.store_link.components.wishlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.R

import com.example.store_link.screen.WishlistItem
import com.example.store_link.ui.theme.BorderGray
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.GreenTag
import com.example.store_link.ui.theme.GreenTagText
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.InStockGreen
import com.example.store_link.ui.theme.NavyBlue

@Composable
fun WishlistItemCard(
    item       : WishlistItem,
    onDelete   : () -> Unit,
    onAddToCart: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderGray, RoundedCornerShape(14.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // ── Product image ───────────────────────────────────────────────────
        // 👈 Yahan har product ki real image lagegi (item.imageRes)
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFF5F5F5))
        ) {
            Image(
                painter            = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier           = Modifier.fillMaxSize().padding(10.dp),
                contentScale       = ContentScale.Fit
            )
        }

        // ── Details column ────────────────────────────────────────────────────
        Column(modifier = Modifier.weight(1f)) {

            // Top row: name + delete icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(item.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = NavyBlue)
                    Text(item.variant, fontSize = 12.sp, color = HintGray)
                }

                // Delete (trash) icon
                Icon(
                    imageVector = Icons.Filled.Delete, // 👈 trash icon — replace with proper outline icon
                    contentDescription = "Remove from wishlist",
                    tint = HintGray,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onDelete() }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ── Price row ────────────────────────────────────────────────────────
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("₹${item.price}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = BrandOrange)
                Text("₹${item.originalPrice}", fontSize = 13.sp, color = HintGray, textDecoration = TextDecoration.LineThrough)
            }

            Spacer(modifier = Modifier.height(6.dp))

            // ── Discount tag + In Stock row ──────────────────────────────────────
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(GreenTag)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text("${item.discountPct}% OFF", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = GreenTagText)
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // In Stock indicator
            if (item.isInStock) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(InStockGreen))
                    Text("In Stock", fontSize = 12.sp, color = InStockGreen)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ── Add to Cart button (right aligned) ───────────────────────────────
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                OutlinedButton(
                    onClick  = onAddToCart,
                    shape    = RoundedCornerShape(8.dp),
                    border   = androidx.compose.foundation.BorderStroke(1.dp, BrandOrange),
                    colors   = ButtonDefaults.outlinedButtonColors(contentColor = BrandOrange),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Add to Cart", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
fun MoveAllToCartButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick  = onClick,
        modifier = Modifier.fillMaxWidth().height(48.dp).padding(top = 4.dp),
        shape    = RoundedCornerShape(10.dp),
        border   = androidx.compose.foundation.BorderStroke(1.dp, BrandOrange),
        colors   = ButtonDefaults.outlinedButtonColors(contentColor = BrandOrange)
    ) {
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Move All to Cart", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
    }
}