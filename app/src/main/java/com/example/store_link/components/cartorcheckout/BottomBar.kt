package com.example.storelink.components.cartorcheckout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.ui.theme.BorderGray
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.NavyBlue


@Composable
fun CheckoutBottomBar(
    totalAmount  : Int,
    isLoading    : Boolean,
    //onViewDetails: () -> Unit,
    onPlaceOrder : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(width = 1.dp, color = BorderGray)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // ── Left: Total + view details ──────────────────────────────────────
        Column(modifier = Modifier.weight(0.7f)) {
            Text("₹$totalAmount", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = NavyBlue)

        }

        // ── Right: Place Order button ───────────────────────────────────────
        Button(
            onClick  = onPlaceOrder,
            enabled  = !isLoading,
            modifier = Modifier.weight(1.3f).height(54.dp),
            shape    = RoundedCornerShape(10.dp),
            colors   = ButtonDefaults.buttonColors(containerColor = BrandOrange, contentColor = Color.White)
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Place Order", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    Text("Pay on Delivery (COD)", fontSize = 10.sp)
                }
            }
        }
    }
}
