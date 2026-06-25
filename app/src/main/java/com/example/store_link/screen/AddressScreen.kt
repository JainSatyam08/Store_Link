package com.example.store_link.screen

import com.example.store_link.components.address.AddNewAddressCard
import com.example.store_link.components.address.AddressCard
import com.example.store_link.components.address.AddressesTopBar
import com.example.store_link.components.address.SafeAndSecureNote
import com.example.store_link.ui.theme.BrandOrange
import com.example.storelink.components.homescreen.HomeBottomNav



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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.store_link.R
import com.example.store_link.components.address.DeliveryPromoBanner
import com.example.store_link.ui.theme.NavyBlue

// ─── Brand Colors ─────────────────────────────────────────────────────────────


// ─── Simple Data Model (no ViewModel) ────────────────────────────────────────

/** Ek saved address ka poora data */
data class SavedAddress(
    val id        : Int,
    val label     : String,    // "Home", "Work", "Parents' Home"
    val isDefault : Boolean,
    val name      : String,
    val fullAddress: String,
    val phone     : String,
    val iconType  : AddressIconType
)

/** Address type ke hisaab se icon + color decide karne ke liye */
enum class AddressIconType { HOME, WORK, OTHER }

/**
 * MyAddressesScreen
 *
 * Saved addresses list with selection:
 *  - Top bar: back, "My Addresses" title, "Map View" link
 *  - Promo banner: "Deliver to the right place"
 *  - "Saved Addresses" header + Edit link
 *  - Address cards (radio select, default tag, 3-dot menu)
 *  - "Add New Address" dashed card
 *  - Safe & secure note
 *  - Bottom nav
 *
 * @param navController  navigation ke liye
 */
@Composable
fun MyAddressesScreen(navController: NavHostController) {

    // ── Static addresses list (in future DB/API se aayenge) ────────────────
    val addresses = remember {
        listOf(
            SavedAddress(
                id = 1, label = "Home", isDefault = true, name = "Satyam Jain",
                fullAddress = "123, Shakti Nagar, Near Central School,\nShivpuri, Madhya Pradesh - 473551",
                phone = "+91 98765 43210", iconType = AddressIconType.HOME
            ),
            SavedAddress(
                id = 2, label = "Work", isDefault = false, name = "Satyam Jain",
                fullAddress = "TechParki Building, 3rd Floor, Unit 305,\nMG Road, Gwalior, Madhya Pradesh - 474001",
                phone = "+91 98765 43210", iconType = AddressIconType.WORK
            ),
            SavedAddress(
                id = 3, label = "Parents' Home", isDefault = false, name = "Satyam Jain",
                fullAddress = "56, Gandhi Colony, Near Hanuman Mandir,\nGwalior, Madhya Pradesh - 474002",
                phone = "+91 98765 43210", iconType = AddressIconType.OTHER
            )
        )
    }

    // ── Local state (no ViewModel) ───────────────────────────────────────────
    var selectedAddressId by remember { mutableIntStateOf(1) }   // "Home" selected by default
    var selectedTab        by remember { mutableIntStateOf(4) }   // bottom nav: "Profile" active (design mein orange hai)

    Scaffold(
        bottomBar = {
            HomeBottomNav (
                navController,
                selectedTab   = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            // ── 1. Top Bar ────────────────────────────────────────────────────
            AddressesTopBar(
                onBackClick    = { navController.navigate("home") },
                onMapViewClick = { /* TODO: navController.navigate("map_view") */ }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ── 2. Promo Banner ───────────────────────────────────────────────
            DeliveryPromoBanner(modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(20.dp))

            // ── 3. Saved Addresses Header ────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically
            ) {
                Text("Saved Addresses", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
                Text(
                    "Edit",
                    fontSize   = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = BrandOrange,
                    modifier   = Modifier.clickable { /* TODO: enter edit mode */ }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ── 4. Address Cards ──────────────────────────────────────────────
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                addresses.forEach { address ->
                    AddressCard(
                        address    = address,
                        isSelected = address.id == selectedAddressId,
                        onSelect   = { selectedAddressId = address.id },
                        onMenuClick = { /* TODO: show edit/delete bottom sheet */ }
                    )
                }

                // ── 5. Add New Address Card ──────────────────────────────────
                AddNewAddressCard(
                    onClick = { /* TODO: navController.navigate("add_address") */ }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ── 6. Safe & Secure Note ─────────────────────────────────────────
            SafeAndSecureNote()

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

