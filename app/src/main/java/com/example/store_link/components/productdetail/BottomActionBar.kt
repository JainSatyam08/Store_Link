package com.example.storelink.components.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.store_link.ui.theme.BrandOrange

import com.example.store_link.ui.theme.NavyBlue

import com.example.store_link.ui.theme.BorderGray

@Composable
fun BottomActionBar(
    quantity   : Int,
    onIncrease : () -> Unit,
    onDecrease : () -> Unit,
    onAddToCart: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(width = 1.dp, color = BorderGray)   // top border separator
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment     = Alignment.CenterVertically
    ) {

        // ── Quantity Stepper ─────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .border(1.dp, BorderGray, RoundedCornerShape(10.dp))
                .height(52.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Minus button
            Box(
                modifier = Modifier.width(44.dp).fillMaxHeight().clickable { onDecrease() },
                contentAlignment = Alignment.Center
            ) {
                Text("−", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            }
            // Current quantity
            Box(modifier = Modifier.width(36.dp), contentAlignment = Alignment.Center) {
                Text("$quantity", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = NavyBlue)
            }
            // Plus button
            Box(
                modifier = Modifier.width(44.dp).fillMaxHeight().clickable { onIncrease() },
                contentAlignment = Alignment.Center
            ) {
                Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            }
        }

        // ── Add to Cart Button ───────────────────────────────────────────────
        Button(
            onClick  = onAddToCart,
            modifier = Modifier.weight(1f).height(52.dp),
            shape    = RoundedCornerShape(10.dp),
            colors   = ButtonDefaults.buttonColors(containerColor = BrandOrange, contentColor = Color.White)
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add to Cart", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
