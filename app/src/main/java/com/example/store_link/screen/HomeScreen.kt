package com.example.storelink.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.store_link.R
import com.example.store_link.components.homescreen.PromoBanner


import com.example.store_link.ui.theme.LightBg
import com.example.storelink.components.homescreen.CategoriesRow
import com.example.storelink.components.homescreen.FeaturesStrip
import com.example.storelink.components.homescreen.HomeBottomNav
import com.example.storelink.components.homescreen.HomeSearchBar
import com.example.storelink.components.homescreen.HomeTopBar
import com.example.storelink.components.homescreen.ProductsRow
import com.example.storelink.components.homescreen.SectionHeader

// ─── Simple Data Models (no ViewModel, just plain data classes) ──────────────
data class Product(
    val id           : Int,
    val name         : String,
    val imageRes     : Int,
    val price        : Int,
    val originalPrice: Int,
    val discountPct  : Int
)

data class Category(
    val name    : String,
    val imageRes: Int
)

/**
 * HomeScreen
 *
 * Main screen after login.
 *
 * @param nav navigation ke liye
 */
@Composable
fun HomeScreen(nav: NavHostController) {

    // ── Static lists ──────────────────────────────────────────────────────
    val categories = remember {
        listOf(
            Category("Groceries",     R.drawable.grocery),
            Category("Beverages",     R.drawable.beverages),
            Category("Personal Care", R.drawable.personal_care),
            Category("Household",     R.drawable.household),
            Category("Snacks",        R.drawable.snacks_item)
        )
    }

    val products = remember {
        listOf(
            Product(1, "Aashirvaad Atta 5kg",        android.R.drawable.ic_menu_gallery, 269, 325, 17),
            Product(2, "Fortune Sunflower Oil 1L",   android.R.drawable.ic_menu_gallery, 129, 150, 14),
            Product(3, "Tata Salt 1kg",              android.R.drawable.ic_menu_gallery,  20,  25, 14),
            Product(4, "Maggi 2-Minute Noodles 70g", android.R.drawable.ic_menu_gallery,  14,  18, 22)
        )
    }

    var cartCount   by remember { mutableIntStateOf(3) }
    var notifCount  by remember { mutableIntStateOf(2) }
    var searchQuery by remember { mutableStateOf("") }

    val wishlistedIds = remember { mutableStateListOf<Int>() }
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            HomeBottomNav(
                selectedTab   = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        containerColor = LightBg
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            // ── 1. Top Bar ─────────────────────────────────────────────────
            HomeTopBar(
                notifCount  = notifCount,
                cartCount   = cartCount,
                nav         = nav
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ── 2. Search Bar ──────────────────────────────────────────────
            HomeSearchBar(
                query    = searchQuery,
                onChange = { searchQuery = it },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ── 3. Promo Banner ─────────────────────────────────────────────
            PromoBanner(modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(20.dp))

            // ── 4. Categories ──────────────────────────────────────────────
            SectionHeader(
                title      = "Categories",
                actionText = "View All",
                onAction   = { nav.navigate("category_listing") },
                modifier   = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            CategoriesRow(
                categories      = categories,
                onCategoryClick = { nav.navigate("category_listing") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ── 5. Popular Products ────────────────────────────────────────
            SectionHeader(
                title      = "Popular Products",
                actionText = "View All",
                onAction   = { nav.navigate("category_listing") },
                modifier   = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            ProductsRow(
                products        = products,
                wishlistedIds   = wishlistedIds,
                onAddToCart     = { cartCount++ },
                onWishlistClick = { id ->
                    if (wishlistedIds.contains(id)) wishlistedIds.remove(id)
                    else wishlistedIds.add(id)
                },
                onProductClick  = { nav.navigate("product_detail") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ── 6. Features Strip ──────────────────────────────────────────
            FeaturesStrip(modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
