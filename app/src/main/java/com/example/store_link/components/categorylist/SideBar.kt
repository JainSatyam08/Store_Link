package com.example.storelink.components.categorylist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.storelink.screen.SidebarCategory
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
fun SidebarColumn(
    categories         : List<SidebarCategory>,
    selectedCategory   : String,
    onCategorySelected : (String) -> Unit,
    modifier            : Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(SidebarBg)
    ) {
        items(categories) { category ->
            SidebarItem(
                category   = category,
                isSelected = category.name == selectedCategory,
                onClick    = { onCategorySelected(category.name) }
            )
        }
    }
}

/**
 * SidebarItem
 * Single row: icon + label. Selected state shows orange accent.
 */
@Composable
private fun SidebarItem(
    category  : SidebarCategory,
    isSelected: Boolean,
    onClick   : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) DiscountBg else Color.Transparent)
            .border(
                width = if (isSelected) 3.dp else 0.dp,
                color = if (isSelected) BrandOrange else Color.Transparent
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 14.dp),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 👈 Yahan har sidebar category ka apna icon image lagega (category.iconRes)
        Icon(
            painter            = painterResource(id = category.iconRes),
            contentDescription = category.name,
            tint               = if (isSelected) BrandOrange else HintGray,
            modifier           = Modifier.size(20.dp)
        )
        Text(
            text       = category.name,
            fontSize   = 12.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color      = if (isSelected) BrandOrange else NavyBlue,
            lineHeight = 15.sp
        )
    }
}
