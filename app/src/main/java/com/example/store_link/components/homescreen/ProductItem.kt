package com.example.storelink.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.R



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
import com.example.storelink.screen.Category
import com.example.storelink.screen.Product

@Composable
fun CategoriesRow(categories: List<Category>, onCategoryClick: (Category) -> Unit) {
    LazyRow(
        contentPadding        = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories) { category ->
            CategoryItem(category = category, onClick = { onCategoryClick(category) })
        }
    }
}

@Composable
fun CategoryItem(category: Category, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CardWhite)
            .border(1.dp, BorderGray, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter            = painterResource(id = category.imageRes),
            contentDescription = category.name,
            modifier           = Modifier.size(44.dp),
            contentScale       = ContentScale.Fit
        )
        Text(category.name, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = NavyBlue,
            maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

// ─── Component 6: Products Row ────────────────────────────────────────────────
@Composable
fun ProductsRow(
    products       : List<Product>,
    wishlistedIds  : List<Int>,
    onAddToCart    : (Product) -> Unit,
    onWishlistClick: (Int) -> Unit,
    onProductClick : (Product) -> Unit
) {
    LazyRow(
        contentPadding        = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            ProductCard(
                product         = product,
                isWishlisted    = wishlistedIds.contains(product.id),
                onAddToCart     = { onAddToCart(product) },
                onWishlistClick = { onWishlistClick(product.id) },
                onClick         = { onProductClick(product) }
            )
        }
    }
}

@Composable
fun ProductCard(
    product        : Product,
    isWishlisted   : Boolean,
    onAddToCart    : () -> Unit,
    onWishlistClick: () -> Unit,
    onClick        : () -> Unit
) {
    Column(
        modifier = Modifier
            .width(145.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(CardWhite)
            .border(1.dp, BorderGray, RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .padding(bottom = 12.dp)
    ) {
        // ── Image area with heart icon overlay ─────────────────────────────
        Box(
            modifier = Modifier.fillMaxWidth().height(130.dp).background(Color(0xFFF5F5F5))
        ) {
            Image(
                painter            = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier           = Modifier.fillMaxSize().padding(12.dp),
                contentScale       = ContentScale.Fit
            )
            Icon(
                imageVector        = if(isWishlisted) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Wishlist",
                tint               = if (isWishlisted) BrandOrange else HintGray,
                modifier           = Modifier
                    .size(20.dp)
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .clickable { onWishlistClick() }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ── Product info ───────────────────────────────────────────────────
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {

            Text(product.name, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = NavyBlue,
                maxLines = 2, overflow = TextOverflow.Ellipsis)

            Spacer(modifier = Modifier.height(4.dp))

            // ── Price row: current + strikethrough original ────────────────
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("₹${product.price}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
                Text("₹${product.originalPrice}", fontSize = 11.sp, color = HintGray,
                    textDecoration = TextDecoration.LineThrough)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text("${product.discountPct}% OFF", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DiscountGreen)

            Spacer(modifier = Modifier.height(10.dp))

            // ── Add to Cart button ─────────────────────────────────────────
            Button(
                onClick  = onAddToCart,
                modifier = Modifier.fillMaxWidth().height(36.dp),
                shape    = RoundedCornerShape(8.dp),
                colors   = ButtonDefaults.buttonColors(containerColor = BrandOrange, contentColor = Color.White),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.grocery_bag), contentDescription = null, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(4.dp))
                Text("Add to Cart", fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
