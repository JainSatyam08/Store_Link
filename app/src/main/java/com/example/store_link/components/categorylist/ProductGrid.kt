package com.example.storelink.components.categorylist

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.store_link.R
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
import com.example.storelink.screen.GroceryProduct


@Composable
fun ProductGrid(
    products      : List<GroceryProduct>,
    onAddToCart   : (GroceryProduct) -> Unit,
    nav: NavHostController,
    //onProductClick: (GroceryProduct) -> Unit,
    modifier      : Modifier = Modifier
) {
    LazyVerticalGrid(
        columns               = GridCells.Fixed(2),
        modifier              = modifier.fillMaxSize(),
        contentPadding        = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement   = Arrangement.spacedBy(10.dp)
    ) {
        items(products) { product ->
            GroceryProductCard(
                product     = product,
                onAddToCart = { onAddToCart(product) },
                onClick     = { nav.navigate("product_detail") }
            )
        }
    }
}

/**
 * GroceryProductCard
 * Single grid item:
 *  - Product image + heart icon
 *  - Name, weight
 *  - Price + strikethrough + discount
 *  - "Add" button
 */
@Composable
private fun GroceryProductCard(
    product    : GroceryProduct,
    onAddToCart: () -> Unit,
    onClick    : () -> Unit
) {
    // Local wishlist toggle (no ViewModel — sirf is card ke andar)
    var isWishlisted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderGray, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        // ── Image + heart icon ─────────────────────────────────────────────
        Box(modifier = Modifier.fillMaxWidth().height(110.dp)) {
            // 👈 IMPORTANT: Yahan product.imageRes use ho raha hai —
            //    har product ke liye apni real image drawable mein daalo
            //    (e.g. R.drawable.img_aashirvaad_atta, R.drawable.img_fortune_oil, etc.)
            Image(
                painter            = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier           = Modifier.fillMaxSize(),
                contentScale       = ContentScale.Fit
            )
            Icon(
                imageVector = if (isWishlisted) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Wishlist",
                tint               = if (isWishlisted) BrandOrange else HintGray,
                modifier           = Modifier
                    .size(18.dp)
                    .align(Alignment.TopEnd)
                    .clickable { isWishlisted = !isWishlisted }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ── Name + weight ───────────────────────────────────────────────────
        Text(product.name, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = NavyBlue,
            maxLines = 2, overflow = TextOverflow.Ellipsis)
        Text(product.weight, fontSize = 11.sp, color = HintGray)

        Spacer(modifier = Modifier.height(6.dp))

        // ── Price row ────────────────────────────────────────────────────────
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("₹${product.price}", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            Text("₹${product.originalPrice}", fontSize = 12.sp, color = HintGray,
                textDecoration = TextDecoration.LineThrough)
        }

        Spacer(modifier = Modifier.height(4.dp))

        // ── Discount badge ───────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .background(DiscountBg, RoundedCornerShape(4.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text("${product.discountPct}% OFF", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DiscountText)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ── Add button (outlined, matches design's lighter look) ────────────
        OutlinedButton(
            onClick  = onAddToCart,
            modifier = Modifier.fillMaxWidth().height(34.dp),
            shape    = RoundedCornerShape(8.dp),
            colors   = ButtonDefaults.outlinedButtonColors(contentColor = BrandOrange),
            border   = androidx.compose.foundation.BorderStroke(1.dp, BrandOrange),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Add", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
