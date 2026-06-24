package com.example.storelink.components.homescreen

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.example.store_link.ui.theme.DiscountText
import com.example.store_link.ui.theme.FreeDeliveryBg
import com.example.store_link.ui.theme.FreeDeliveryGreen
import com.example.store_link.ui.theme.SidebarBg



@Composable
fun HomeBottomNav(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf(
        Pair(Icons.Filled.Home,       "Home"),
        Pair(Icons.Filled.Category, "Categories"),
        Pair(Icons.Filled.ShoppingBag,     "Orders"),
        Pair(Icons.Filled.Favorite,   "Wishlist"),
        Pair(Icons.Rounded.Person2,    "Profile")
    )

    NavigationBar(containerColor = CardWhite, tonalElevation = 8.dp) {
        tabs.forEachIndexed { index, (iconRes, label) ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick  = { onTabSelected(index) },
                icon     = {
                    Icon(imageVector =  iconRes, contentDescription = label, modifier = Modifier.size(22.dp))
                },
                label    = { Text(label, fontSize = 10.sp) },
                colors   = NavigationBarItemDefaults.colors(
                    selectedIconColor   = BrandOrange,
                    selectedTextColor   = BrandOrange,
                    unselectedIconColor = HintGray,
                    unselectedTextColor = HintGray,
                    indicatorColor      = Color.Transparent
                )
            )
        }
    }
}