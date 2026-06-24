package com.example.storelink.screen



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.store_link.R

import com.example.store_link.ui.theme.HintGray
import com.example.store_link.ui.theme.LightBg
import com.example.storelink.components.cartorcheckout.CheckoutBottomBar
import com.example.storelink.components.cartorcheckout.CheckoutTopBar
import com.example.storelink.components.cartorcheckout.DeliveryAddressCard
import com.example.storelink.components.cartorcheckout.OrderSummaryCard
import com.example.storelink.components.cartorcheckout.PaymentMethodCard
import com.example.storelink.components.cartorcheckout.SafeSecureStrip
import com.example.storelink.components.cartorcheckout.StepIndicator

// ─── Brand Colors ─────────────────────────────────────────────────────────────


// ─── Simple Data Model (no ViewModel) ────────────────────────────────────────

/** Ek order item — checkout summary mein dikhne wala product */
data class CheckoutItem(
    val name    : String,
    val variant : String,   // "5kg", "1L" etc — image ke neeche wala text
    val imageRes: Int,      // 👈 yahan har product ki real image lagegi
    val price   : Int,
    val quantity: Int
)

/**
 * CheckoutScreen
 *
 * 3-step checkout flow ki pehli screen (Address step active):
 *  - Top bar: back, "Checkout" title, StoreLink logo
 *  - Step indicator: Address → Payment → Place Order
 *  - Delivery Address card
 *  - Order Summary (items + totals)
 *  - Payment Method selector
 *  - Safe & Secure strip
 *  - Sticky bottom: total + Place Order button
 *
 * @param navController  navigation ke liye
 */

@Composable
fun CheckoutScreen(navController: NavHostController) {

    // ── Static order items (in future cart se aayenge) ─────────────────────
    val checkoutItems = remember {
        listOf(
            CheckoutItem("Aashirvaad Atta 5kg",      "5kg", android.R.drawable.ic_menu_gallery, 269, 1), // 👈 atta image
            CheckoutItem("Fortune Sunflower Oil 1L", "1L",  android.R.drawable.ic_menu_gallery, 129, 1)  // 👈 oil image
        )
    }

    // ── Local state (no ViewModel) ───────────────────────────────────────────
    var selectedPaymentMethod by remember { mutableStateOf("COD") }   // currently only COD selected
    var isPlacingOrder         by remember { mutableStateOf(false) }

    // ── Derived totals (simple calculation, no ViewModel needed) ────────────
    val itemTotal       = checkoutItems.sumOf { it.price * it.quantity }
    val deliveryCharge   = 0          // FREE — design mein "FREE" dikh raha hai
    val packingCharge    = 20
    val totalAmount      = itemTotal + deliveryCharge + packingCharge

    Scaffold(
        // ── Sticky bottom: total amount + Place Order button ────────────────
        bottomBar = {
            CheckoutBottomBar(
                totalAmount   = totalAmount,
                isLoading     = isPlacingOrder,
                //onViewDetails = { /* TODO: expand price breakdown */ },
                onPlaceOrder  = {
                    isPlacingOrder = true
                    // TODO: Real order placement API call yahan aayegi.
                    // Abhi seedha order-confirmation pe navigate kar dete hain.
                    isPlacingOrder = false
                    /*navController.navigate("order_confirmation")*/
                }
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

            // ── 1. Top Bar ────────────────────────────────────────────────────
            CheckoutTopBar(onBackClick = { navController.navigate("home") })

            // ── 2. Step Indicator ────────────────────────────────────────────
            StepIndicator(currentStep = 1)   // 1 = Address active

            Spacer(modifier = Modifier.height(16.dp))

            // ── 3. Delivery Address Card ─────────────────────────────────────
            DeliveryAddressCard(
                name      = "Satyam Jain",
                address   = "123, Shakti Nagar, Near Central School,\nShivpuri, Madhya Pradesh - 473551",
                phone     = "+91 98765 43210",
                onChangeClick = { /* TODO: navController.navigate("select_address") */ },
                modifier  = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ── 4. Order Summary ──────────────────────────────────────────────
            OrderSummaryCard(
                items          = checkoutItems,
                itemTotal      = itemTotal,
                deliveryCharge = deliveryCharge,
                packingCharge  = packingCharge,
                totalAmount    = totalAmount,
                modifier       = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ── 5. Payment Method ─────────────────────────────────────────────
            PaymentMethodCard(
                selectedMethod = selectedPaymentMethod,
                onMethodSelected = { selectedPaymentMethod = it },
                modifier       = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ── 6. Safe & Secure Strip ────────────────────────────────────────
            SafeSecureStrip(modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

