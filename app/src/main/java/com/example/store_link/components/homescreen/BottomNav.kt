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
import androidx.navigation.NavHostController
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.CardWhite

@Composable
fun HomeBottomNav(nav: NavHostController,
                  selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf(
        Triple(Icons.Filled.Home,       "Home", "home"),
        Triple(Icons.Filled.Category, "Categories", "category_listing"),
        Triple(Icons.Filled.ShoppingBag,     "Orders",""),
        Triple(Icons.Filled.Favorite,   "Wishlist","wishlist"),
        Triple(Icons.Rounded.Person2,    "Profile","profile")
    )

    NavigationBar(containerColor = CardWhite, tonalElevation = 8.dp) {
        tabs.forEachIndexed { index, (icon, label, route) ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick  = {
                    onTabSelected(index)
                    nav.navigate(route){
                               // Same tab dobara dobara stack mein na chadhe
                        launchSingleTop = true
                               // Home ko backstack mein rakho taaki back press pe seedha exit na ho
                        popUpTo("home") { saveState = true }
                        restoreState = true
                    }
                           }
                ,
                icon     = {
                    Icon(imageVector =  icon, contentDescription = label, modifier = Modifier.size(22.dp))
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