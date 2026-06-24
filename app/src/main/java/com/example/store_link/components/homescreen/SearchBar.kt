package com.example.storelink.components.homescreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun HomeSearchBar(query: String, onChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value         = query,
        onValueChange = onChange,
        placeholder   = { Text("Search for products...", color = HintGray, fontSize = 14.sp) },
        leadingIcon   = {
            Icon(imageVector = Icons.Outlined.Search, 
                contentDescription = null,
                tint = HintGray, 
                modifier = Modifier.size(20.dp))
        },
        trailingIcon  = {
            Icon(
                imageVector = Icons.Filled.FilterList, 
                contentDescription = "Filter",
                tint = HintGray, 
                modifier = Modifier.size(20.dp)
            )
        },
        singleLine    = true,
        modifier      = modifier.fillMaxWidth(),
        shape         = RoundedCornerShape(50.dp),
        colors        = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor    = BorderGray,
            focusedBorderColor      = BrandOrange,
            unfocusedContainerColor = CardWhite,
            focusedContainerColor   = CardWhite,
            cursorColor             = BrandOrange
        )
    )
}
