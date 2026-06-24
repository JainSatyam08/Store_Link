package com.example.storelink.components.cartorcheckout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import com.example.storelink.screen.CheckoutItem

import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.DescGray
import com.example.store_link.ui.theme.GreenTag
import com.example.store_link.ui.theme.GreenTagText
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg
import com.example.store_link.ui.theme.BorderGray

@Composable
fun OrderSummaryCard(
    items          : List<CheckoutItem>,
    itemTotal      : Int,
    deliveryCharge : Int,
    packingCharge  : Int,
    totalAmount    : Int,
    modifier       : Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, BorderGray, RoundedCornerShape(14.dp))
            .padding(16.dp)
    ) {
        Text("Order Summary", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
        Spacer(modifier = Modifier.height(12.dp))

        // ── Item rows ─────────────────────────────────────────────────────────
        items.forEachIndexed { index, item ->
            OrderSummaryItemRow(item = item)
            if (index != items.lastIndex) {
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = BorderGray)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))
        HorizontalDivider(color = BorderGray)
        Spacer(modifier = Modifier.height(14.dp))

        // ── Price breakdown rows ─────────────────────────────────────────────
        PriceBreakdownRow(label = "Item Total (${items.size} items)", value = "₹$itemTotal")
        Spacer(modifier = Modifier.height(8.dp))
        PriceBreakdownRow(
            label    = "Delivery Charges",
            value    = if (deliveryCharge == 0) "₹0" else "₹$deliveryCharge",
            tagText  = if (deliveryCharge == 0) "FREE" else null
        )
        Spacer(modifier = Modifier.height(8.dp))
        PriceBreakdownRow(label = "Packing Charges", value = "₹$packingCharge")

        Spacer(modifier = Modifier.height(14.dp))

        // ── Total Amount (highlighted) ───────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(PeachBg)
                .padding(horizontal = 14.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.CenterVertically
        ) {
            Text("Total Amount", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            Text("₹$totalAmount", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = BrandOrange)
        }
    }
}

/**
 * OrderSummaryItemRow
 * Single product row: image, name, variant, price, qty.
 */
@Composable
fun OrderSummaryItemRow(item: CheckoutItem) {
    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(12.dp)) {

        // 👈 Yahan har product ki real image lagegi (item.imageRes)
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF5F5F5))
        ) {
            Image(
                painter            = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier           = Modifier.fillMaxSize().padding(6.dp),
                contentScale       = ContentScale.Fit
            )
        }

        // Name + variant
        Column(modifier = Modifier.weight(1f)) {
            Text(item.name, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = NavyBlue)
            Spacer(modifier = Modifier.height(2.dp))
            Text(item.variant, fontSize = 12.sp, color = HintGray)
        }

        // Price + Qty (right aligned)
        Column(horizontalAlignment = Alignment.End) {
            Text("₹${item.price}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = NavyBlue)
            Spacer(modifier = Modifier.height(2.dp))
            Text("Qty: ${item.quantity}", fontSize = 12.sp, color = HintGray)
        }
    }
}
@Composable
fun PriceBreakdownRow(label: String, value: String, tagText: String? = null) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(label, fontSize = 14.sp, color = DescGray)
            if (tagText != null) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(GreenTag)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(tagText, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = GreenTagText)
                }
            }
        }
        Text(value, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = NavyBlue)
    }
}