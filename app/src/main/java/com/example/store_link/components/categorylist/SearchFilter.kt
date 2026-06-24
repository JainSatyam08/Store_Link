package com.example.storelink.components.categorylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.store_link.ui.theme.BannerBg
import com.example.store_link.ui.theme.BrandOrange

import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg
import com.example.store_link.ui.theme.BorderGray

@Composable
fun SearchFilterRow(
    query        : String,
    onChange     : (String) -> Unit,
    onFilterClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment     = Alignment.CenterVertically
    ) {
        // Search field
        OutlinedTextField(
            value         = query,
            onValueChange = onChange,
            placeholder   = { Text("Search in Groceries...", color = HintGray, fontSize = 14.sp) },
            leadingIcon   = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null,
                    tint = HintGray, modifier = Modifier.size(20.dp))
            },
            singleLine    = true,
            modifier      = Modifier.weight(1f).height(52.dp),
            shape         = RoundedCornerShape(12.dp),
            colors        = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor    = BorderGray,
                focusedBorderColor      = BrandOrange,
                unfocusedContainerColor = Color.White,
                focusedContainerColor   = Color.White
            )
        )

        // Filter button
        OutlinedButton(
            onClick  = onFilterClick,
            modifier = Modifier.height(52.dp),
            shape    = RoundedCornerShape(12.dp),
            colors   = ButtonDefaults.outlinedButtonColors(contentColor = NavyBlue),
            border   = androidx.compose.foundation.BorderStroke(1.dp, BorderGray),
            contentPadding = PaddingValues(horizontal = 14.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.FilterAlt,
                contentDescription = null,
                modifier           = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text("Filter", fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}
