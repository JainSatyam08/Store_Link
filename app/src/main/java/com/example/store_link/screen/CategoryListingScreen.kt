package com.example.storelink.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.example.store_link.ui.theme.LightBg
import com.example.storelink.components.categorylist.FreeDeliveryBanner
import com.example.storelink.components.categorylist.ProductCountSortRow
import com.example.storelink.components.categorylist.ProductGrid
import com.example.storelink.components.categorylist.SearchFilterRow
import com.example.storelink.components.categorylist.SidebarColumn
import com.example.storelink.components.categorylist.TopBarSection
import com.example.storelink.components.homescreen.HomeBottomNav


/** Left sidebar ki ek category (Staples, Spices, etc.) */
data class SidebarCategory(
    val name    : String,
    val iconRes : Int
)

/** Grid mein dikhne wala ek product */
data class GroceryProduct(
    val id            : Int,
    val name          : String,
    val weight        : String,    // "5kg", "1L", "250g" etc — design mein name ke neeche
    val imageRes       : Int,       // 👈 yahan har product ki apni image lagegi (placeholder neeche)
    val price         : Int,
    val originalPrice : Int,
    val discountPct   : Int
)

/**
 * CategoryListingScreen
 *
 * "Groceries" jaisi category detail screen:
 *  - Top: back arrow + title + cart
 *  - Search bar + Filter button
 *  - Product count + Sort dropdown
 *  - Left: sidebar sub-categories (scrollable column)
 *  - Right: product grid (2 columns)
 *  - Free delivery banner
 *  - Bottom nav
 *
 * @param navController     navigation ke liye
 * @param categoryTitle      e.g. "Groceries" — agar reuse karna ho doosri categories ke liye
 * @param categorySubtitle   e.g. "All your daily needs in one place"
 */
@Composable
fun CategoryListingScreen(
    navController    : NavHostController,
    categoryTitle    : String = "Category/All Product",
    categorySubtitle : String = "All your daily needs in one place"
) {
    // ── Sidebar categories (static list) ────────────────────────────────────
    // NOTE: Har category ka apna icon hai — abhi placeholder android icon use kiya hai.
    //       👇 Yahan apne actual category icons (drawable) daalna:
    val sidebarCategories = remember {
        listOf(
            SidebarCategory("All",                  android.R.drawable.ic_menu_gallery), // 👈 basket icon yahan
            SidebarCategory("Staples",               android.R.drawable.ic_menu_gallery), // 👈 bag icon
            SidebarCategory("Pulses & Grains",        android.R.drawable.ic_menu_gallery), // 👈 bowl icon
            SidebarCategory("Spices & Masala",        android.R.drawable.ic_menu_gallery), // 👈 spice icon
            SidebarCategory("Oils & Ghee",            android.R.drawable.ic_menu_gallery), // 👈 bottle icon
            SidebarCategory("Snacks & Packaged Food", android.R.drawable.ic_menu_gallery), // 👈 snack pack icon
            SidebarCategory("Beverages",              android.R.drawable.ic_menu_gallery), // 👈 glass icon
            SidebarCategory("Breakfast & Dairy",      android.R.drawable.ic_menu_gallery), // 👈 milk bottle icon
            SidebarCategory("Personal Care",          android.R.drawable.ic_menu_gallery), // 👈 lotion bottle icon
            SidebarCategory("Household",              android.R.drawable.ic_menu_gallery)  // 👈 cleaning bottle icon
        )
    }

    // ── Product list (static) ────────────────────────────────────────────────
    // 👇 Har product ki apni real image yahan imageRes mein daalna (drawable resource)
    val products = remember {
        listOf(
            GroceryProduct(1, "Aashirvaad Atta",        "5kg",  android.R.drawable.ic_menu_gallery, 269, 325, 17), // 👈 atta image
            GroceryProduct(2, "Fortune Sunflower Oil",   "1L",   android.R.drawable.ic_menu_gallery, 129, 150, 14), // 👈 oil bottle image
            GroceryProduct(3, "Daawat Rozana Basmati Rice", "1kg", android.R.drawable.ic_menu_gallery, 112, 125, 10), // 👈 rice packet image
            GroceryProduct(4, "Tata Salt",               "1kg",  android.R.drawable.ic_menu_gallery,  20,  25, 20), // 👈 salt packet image
            GroceryProduct(5, "Red Label Tea",           "250g", android.R.drawable.ic_menu_gallery, 125, 160, 14), // 👈 tea packet image
            GroceryProduct(6, "Maggi 2-Minute Noodles",  "70g",  android.R.drawable.ic_menu_gallery,  14,  18, 22), // 👈 maggi image
            GroceryProduct(7, "Amul Taaza Milk",         "500ml", android.R.drawable.ic_menu_gallery, 27,  30, 10), // 👈 milk packet image
            GroceryProduct(8, "Nescafe Classic Coffee",  "100g", android.R.drawable.ic_menu_gallery, 199, 225, 12), // 👈 coffee jar image
            GroceryProduct(9, "Saffola Oats",            "400g", android.R.drawable.ic_menu_gallery,  89, 110, 19)  // 👈 oats packet image
        )
    }

    // ── Local state (no ViewModel) ───────────────────────────────────────────
    var searchQuery   by remember { mutableStateOf("") }
    var cartCount      by remember { mutableIntStateOf(3) }   // design mein already 3 dikha hai
    var selectedSidebar by remember { mutableStateOf("All") }  // currently selected left category
    var selectedTab    by remember { mutableIntStateOf(1) }    // bottom nav: "Categories" active

    Scaffold(
        bottomBar = {
            HomeBottomNav(
                selectedTab   = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        containerColor = LightBg
    ) { paddingValues ->

        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            // ── 1. Top Bar: back + title + cart ───────────────────────────
            TopBarSection(
                title       = categoryTitle,
                subtitle    = categorySubtitle,
                cartCount   = cartCount,
                onBackClick = { navController.navigate("home") },
                onCartClick = { navController.navigate("cart") }
            )

            // ── 2. Search + Filter Row ──────────────────────────────────────
            SearchFilterRow(
                query    = searchQuery,
                onChange = { searchQuery = it },
                onFilterClick = { /* TODO: open filter bottom sheet */ }
            )

            // ── 3. Product count + Sort ─────────────────────────────────────
            ProductCountSortRow(
                productCount = products.size * 13,   // design shows "120 Products" — dummy multiplier for demo
                onSortClick  = { /* TODO: open sort dropdown */ }
            )

            // ── 4. Main content: Sidebar (left) + Product Grid (right) ──────
            Row(modifier = Modifier.weight(1f).fillMaxWidth()) {

                // ── Left Sidebar ──────────────────────────────────────────
                SidebarColumn(
                    categories       = sidebarCategories,
                    selectedCategory = selectedSidebar,
                    onCategorySelected = { selectedSidebar = it },
                    modifier         = Modifier.width(110.dp)
                )

                // ── Right: Product Grid + Free delivery banner ─────────────
                Column(modifier = Modifier.weight(1f)) {

                    ProductGrid(
                        products    = products,
                        onAddToCart = { cartCount++ },
                         navController,
                        modifier    = Modifier.weight(1f)
                    )

                    // ── Free Delivery Banner ──────────────────────────────
                    FreeDeliveryBanner(onClick = { /* TODO: navigate to delivery info */ })
                }
            }
        }
    }
}
