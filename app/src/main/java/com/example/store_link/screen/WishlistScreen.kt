package com.example.store_link.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.store_link.R
import com.example.store_link.components.wishlist.WishlistBanner
import com.example.store_link.components.wishlist.WishlistItemCard
import com.example.store_link.components.wishlist.WishlistTopBar
import com.example.store_link.components.wishlist.MoveAllToCartButton
import com.example.store_link.ui.theme.LightBg
import com.example.storelink.components.homescreen.HomeBottomNav

// ─── Brand Colors ─────────────────────────────────────────────────────────────


// ─── Simple Data Model (no ViewModel) ─────────────────────────────────────────

/** Ek wishlist item ka poora data */
data class WishlistItem(
    val id            : Int,
    val name          : String,
    val variant       : String,   // "5 kg", "1 L" etc
    val imageRes       : Int,      // 👈 yahan har product ki real image lagegi
    val price         : Int,
    val originalPrice  : Int,
    val discountPct    : Int,
    val isInStock      : Boolean = true
)

/**
 * MyWishlistScreen
 *
 * Saved wishlist items list:
 *  - Top bar: back, "My Wishlist" title, search + cart icons
 *  - Banner: "5 Items in Wishlist" with bag illustration
 *  - List of wishlist item cards (image, name, price, delete, add to cart)
 *  - "Move All to Cart" outlined button
 *  - Bottom nav (Wishlist tab active)
 *
 * @param navController  navigation ke liye
 */
@Composable
fun MyWishlistScreen(nav: NavHostController) {

    // ── Local mutable wishlist (no ViewModel) ───────────────────────────────
    // mutableStateListOf isliye use kiya taaki delete karne pe UI reactively update ho
    val wishlistItems = remember {
        mutableStateListOf(
            WishlistItem(1, "Aashirvaad Atta 5kg",        "5 kg", android.R.drawable.ic_menu_gallery, 269, 315, 15), // 👈 atta image
            WishlistItem(2, "Fortune Sunflower Oil 1L",   "1 L",  android.R.drawable.ic_menu_gallery, 129, 150, 14), // 👈 oil image
            WishlistItem(3, "Surf Excel Matic 2kg",       "2 kg", android.R.drawable.ic_menu_gallery, 245, 285, 14), // 👈 detergent image
            WishlistItem(4, "Red Label Tea 250g",         "250 g", android.R.drawable.ic_menu_gallery, 90, 110, 18), // 👈 tea image
            WishlistItem(5, "Harpic Toilet Cleaner 500ml", "500 ml", android.R.drawable.ic_menu_gallery, 78, 90, 13)  // 👈 cleaner image
        )
    }

    // ── Local state ───────────────────────────────────────────────────────────
    var cartCount   by remember { mutableIntStateOf(2) }   // design mein already 2 dikha hai
    var selectedTab by remember { mutableIntStateOf(3) }    // bottom nav: "Wishlist" active

    Scaffold(
        bottomBar = {
            HomeBottomNav(
                nav,
                selectedTab   = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        containerColor = LightBg
    ) { paddingValues ->

        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            // ── 1. Top Bar ────────────────────────────────────────────────────
            WishlistTopBar(
                cartCount     = cartCount,
                onBackClick   = { nav.navigate("home") },
                onSearchClick = { /* TODO: navigate to search */ },
                onCartClick   = { nav.navigate("cart") }
            )

            // ── 2. Banner ──────────────────────────────────────────────────────
            WishlistBanner(
                itemCount = wishlistItems.size,
                modifier  = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )

            // ── 3. Wishlist Items List (scrollable) ──────────────────────────
            LazyColumn(
                modifier              = Modifier.weight(1f),
                contentPadding        = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement   = Arrangement.spacedBy(12.dp)
            ) {
                items(wishlistItems, key = { it.id }) { item ->
                    WishlistItemCard(
                        item        = item,
                        onDelete    = { wishlistItems.remove(item) },   // remove from local list
                        onAddToCart = { cartCount++ }
                    )
                }

                // ── 4. Move All to Cart (last list item, scrolls with content) ──
                item {
                    MoveAllToCartButton(
                        onClick = {
                            cartCount += wishlistItems.size
                            // TODO: real "move all" logic — abhi sirf cart count badh raha hai
                        }
                    )
                }
            }
        }
    }
}


