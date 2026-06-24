package com.example.storelink.screen




import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.store_link.components.productdetail.FeatureIconsRow

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
import com.example.store_link.ui.theme.LightBg
import com.example.store_link.ui.theme.SidebarBg
import com.example.storelink.components.productdetail.BottomActionBar
import com.example.storelink.components.productdetail.FreeDeliveryStrip
import com.example.storelink.components.productdetail.PriceRow
import com.example.storelink.components.productdetail.ProductImageSection
import com.example.storelink.components.productdetail.ProductTitleSection
import com.example.storelink.components.productdetail.SizeSelectorRow
import com.example.storelink.components.productdetail.TopActionBar


/** Ek size variant — "1kg" @ ₹59, "5kg" @ ₹269 etc. */
data class SizeOption(
    val label: String,   // "5kg"
    val price: Int        // 269
)

/**
 * ProductDetailScreen
 *
 * Single product ki detail page:
 *  - Top bar: back, wishlist heart, cart
 *  - Product image with "Bestseller" tag + image counter + zoom icon
 *  - Name, rating, price + discount
 *  - Free delivery strip
 *  - Size selector (chips)
 *  - Description
 *  - Feature icons row (100% Natural, etc.)
 *  - Sticky bottom: quantity stepper + Add to Cart button
 *
 * @param navController  navigation ke liye
 */
@Composable
fun ProductDetailScreen(nav: NavHostController) {

    // ── Static size options (in future API se aa sakta hai) ────────────────
    val sizeOptions = remember {
        listOf(
            SizeOption("1kg", 59),
            SizeOption("2kg", 119),
            SizeOption("5kg", 269),
            SizeOption("10kg", 519)
        )
    }

    // ── Local state (no ViewModel) ───────────────────────────────────────────
    var selectedSize  by remember { mutableStateOf("5kg") }   // default selected jaisa design mein hai
    var quantity       by remember { mutableIntStateOf(1) }
    var isWishlisted   by remember { mutableStateOf(false) }
    var cartCount       by remember { mutableIntStateOf(3) }  // design mein already 3 dikha hai

    // Current price jo selected size se match kare
    val currentPrice = sizeOptions.firstOrNull { it.label == selectedSize }?.price ?: 269
    val originalPrice = 325   // 👈 yahan asli MRP backend se aayega; abhi static
    val discountPct   = 17    // 👈 yahan asli discount % backend se aayega

    Scaffold(
        // ── Sticky bottom bar: quantity + Add to Cart ───────────────────────
        bottomBar = {
            BottomActionBar(
                quantity        = quantity,
                onIncrease      = { quantity++ },
                onDecrease       = { if (quantity > 1) quantity-- },   // 1 se neeche na jaaye
                onAddToCart      = {
                    cartCount += quantity
                    // TODO: actual cart logic / navigate to cart confirmation
                }
            )
        },containerColor = LightBg
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            // ── 1. Top Bar ────────────────────────────────────────────────────
            TopActionBar(
                isWishlisted = isWishlisted,
                cartCount    = cartCount,
                onBackClick  = { nav.popBackStack() },
                onWishlistClick = { isWishlisted = !isWishlisted },
                onCartClick  = { nav.navigate("cart") }
            )

            // ── 2. Product Image Section ─────────────────────────────────────
            ProductImageSection(
                currentImageIndex = 1,
                totalImages       = 5
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                // ── 3. Product Name + Rating ─────────────────────────────────
                ProductTitleSection(
                    name          = "Aashirvaad Atta 5kg",   // 👈 selected size ke hisaab se yeh bhi update kar sakte ho
                    rating        = 4.6f,
                    ratingCount   = 2350,
                    boughtText    = "10K+ bought in past month"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // ── 4. Price + Discount ──────────────────────────────────────
                PriceRow(
                    price         = currentPrice,
                    originalPrice = originalPrice,
                    discountPct   = discountPct
                )

                Spacer(modifier = Modifier.height(12.dp))

                // ── 5. Free Delivery Strip ───────────────────────────────────
                FreeDeliveryStrip()

                Spacer(modifier = Modifier.height(20.dp))

                // ── 6. Size Selector ──────────────────────────────────────────
                Text("Select Size", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
                Spacer(modifier = Modifier.height(10.dp))
                SizeSelectorRow(
                    sizeOptions  = sizeOptions,
                    selectedSize = selectedSize,
                    onSizeSelected = { selectedSize = it }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ── 7. Product Description ───────────────────────────────────
                Text("Product Description", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    // 👈 yahan asli description backend/CMS se aayega
                    text = "Aashirvaad Superior MP Atta is made from the choicest MP wheat. " +
                            "It is 100% pure, natural and nutritious. Enjoy soft, fluffy rotis " +
                            "everyday with your family.",
                    fontSize   = 13.sp,
                    color      = Color(0xFF555555),
                    lineHeight = 19.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = BorderGray)
                Spacer(modifier = Modifier.height(16.dp))

                // ── 8. Feature Icons Row ─────────────────────────────────────
                FeatureIconsRow()

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}



