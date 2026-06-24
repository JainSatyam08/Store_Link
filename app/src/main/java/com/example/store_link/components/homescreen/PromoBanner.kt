package com.example.store_link.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.R
import com.example.store_link.ui.theme.BannerBg
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.DescGray
import com.example.store_link.ui.theme.GreenTag
import com.example.store_link.ui.theme.GreenTagText
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg
import com.example.store_link.ui.theme.BorderGray
import com.example.store_link.ui.theme.CardWhite
import com.example.store_link.ui.theme.DiscountBg
import com.example.store_link.ui.theme.DiscountGreen
import com.example.store_link.ui.theme.DiscountText
import com.example.store_link.ui.theme.FreeDeliveryBg
import com.example.store_link.ui.theme.FreeDeliveryGreen
import com.example.store_link.ui.theme.SidebarBg


@Composable
fun PromoBanner(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth().height(170.dp).clip(RoundedCornerShape(16.dp)).background(BannerBg)
        ) {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {

                // ── Left: Text content ─────────────────────────────────────
                Column(
                    modifier = Modifier.weight(1f).padding(start = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Fresh Groceries\nDelivered to\n")
                            withStyle(SpanStyle(color = BrandOrange, fontWeight = FontWeight.Bold)) {
                                append("Your Doorstep")
                            }
                        },
                        fontSize = 16.sp, fontWeight = FontWeight.Bold, color = NavyBlue, lineHeight = 22.sp
                    )
                    Text("Quality you can trust,\nSavings you'll love!", fontSize = 11.sp, color = HintGray, lineHeight = 16.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Button(
                        onClick  = { /* TODO: navigate to shop */ },
                        modifier = Modifier.height(34.dp),
                        shape    = RoundedCornerShape(8.dp),
                        colors   = ButtonDefaults.buttonColors(containerColor = BrandOrange, contentColor = Color.White),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                    ) {
                        Text("Shop Now", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

                // ── Right: Grocery bag image ───────────────────────────────
                Image(
                    painter            = painterResource(id = R.drawable.grocery_bag),
                    contentDescription = "Grocery bag",
                    modifier           = Modifier.weight(1f).fillMaxHeight(),
                    contentScale       = ContentScale.Crop
                )
            }
        }

        // ── Pager dots ──────────────────────────────────────────────────────
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .size(if (index == 0) 10.dp else 7.dp)
                        .clip(CircleShape)
                        .background(if (index == 0) BrandOrange else Color(0xFFCCCCCC))
                )
            }
        }
    }
}
