package com.example.storelink.components.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storelink.screen.SizeOption
import com.example.store_link.ui.theme.BannerBg
import com.example.store_link.ui.theme.BrandOrange

import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg
import com.example.store_link.ui.theme.BorderGray
import com.example.store_link.ui.theme.CardWhite
import com.example.store_link.ui.theme.DiscountBg

import kotlin.collections.forEach

@Composable
fun SizeSelectorRow(
    sizeOptions   : List<SizeOption>,
    selectedSize  : String,
    onSizeSelected: (String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        sizeOptions.forEach { option ->
            SizeChip(
                option     = option,
                isSelected = option.label == selectedSize,
                onClick    = { onSizeSelected(option.label) },
                modifier   = Modifier.weight(1f)
            )
        }
    }
}

/**
 * SizeChip
 * Single size box: label + price, selected = orange border/bg.
 */
@Composable
fun SizeChip(
    option    : SizeOption,
    isSelected: Boolean,
    onClick   : () -> Unit,
    modifier  : Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) DiscountBg else Color.White)
            .border(
                width = if (isSelected) 1.5.dp else 1.dp,
                color = if (isSelected) BrandOrange else BorderGray,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            option.label,
            fontSize   = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color      = if (isSelected) BrandOrange else NavyBlue
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            "₹${option.price}",
            fontSize = 12.sp,
            color    = if (isSelected) BrandOrange else HintGray
        )
    }
}
