package com.example.storelink.components.cartorcheckout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
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
import com.example.store_link.ui.theme.DescGray
import com.example.store_link.ui.theme.GreenTag
import com.example.store_link.ui.theme.GreenTagText
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg
import com.example.store_link.ui.theme.BorderGray

@Composable
fun CheckoutTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Back arrow
        Icon(
           imageVector = Icons.Filled.ArrowBack,
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
            Text("Checkout", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = null,
                    tint = GreenTagText,
                    modifier = Modifier.size(13.dp)
                )
                Text("Secure checkout", fontSize = 13.sp, color = HintGray)
            }
        }

        // ── StoreLink logo + name (small, top right) ─────────────────────────
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Image(
                painter            = painterResource(id = R.drawable.logo), // 👈 small logo icon
                contentDescription = "StoreLink",
                modifier           = Modifier.size(28.dp)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = NavyBlue,    fontWeight = FontWeight.Bold)) { append("Store") }
                    withStyle(SpanStyle(color = BrandOrange, fontWeight = FontWeight.Bold)) { append("Link") }
                },
                fontSize = 16.sp
            )
        }
    }
}
