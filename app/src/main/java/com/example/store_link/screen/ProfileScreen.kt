package com.example.store_link.screen


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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.store_link.R
import com.example.store_link.components.profile.AccountMenuRow
import com.example.store_link.components.profile.AddNewAddressRow
import com.example.store_link.components.profile.LogoutButton
import com.example.store_link.components.profile.ProfileAddressRow
import com.example.store_link.components.profile.ProfileTopBar
import com.example.store_link.components.profile.QuickStatsRow
import com.example.store_link.components.profile.UserInfoCard
import com.example.store_link.ui.theme.BorderGray
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.LightBg
import com.example.store_link.ui.theme.NavyBlue
import com.example.storelink.components.homescreen.HomeBottomNav

/** Profile ke 4 quick-stat icons: My Orders, Wishlist, Addresses, Coupons */
data class ProfileStat(
    val label: String,
    val count: Int,
    val iconRes: Int
)

/** Reuse same AddressIconType + SavedAddress models jo MyAddressesScreen mein bane the */
// (AddressIconType aur SavedAddress already defined hain MyAddressesScreen.kt mein —
//  agar alag file mein chahiye to import karna ya yahan se reuse karna)

/** Account section ka ek menu item: "Personal Information", "Change Password" etc */
data class AccountMenuItem(
    val label  : String,
    val iconRes: Int,
    val route  : String   // navigate karne ke liye route name
)


@Composable
fun ProfileScreen(navController: NavHostController) {

    // ── Static data (in future API/DB se aayega) ─────────────────────────────
    val profileStats = remember {
        listOf(
            ProfileStat("My Orders",  4, android.R.drawable.ic_menu_agenda),       // 👈 receipt icon
            ProfileStat("Wishlist",   3, android.R.drawable.ic_menu_today),         // 👈 heart icon — yahan placeholder hai, R.drawable.ic_heart_outline use karo
            ProfileStat("Addresses",  2, android.R.drawable.ic_menu_mylocation),    // 👈 pin icon
            ProfileStat("Coupons",    5, android.R.drawable.ic_menu_slideshow)      // 👈 coupon/ticket icon
        )
    }

    val savedAddresses = remember {
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

    val accountMenuItems = remember {
        listOf(
            AccountMenuItem("Personal Information", android.R.drawable.ic_menu_myplaces, "personal_info"), // 👈 person icon
            AccountMenuItem("Change Password",       android.R.drawable.ic_lock_lock,      "change_password"),
            AccountMenuItem("Help & Support",        android.R.drawable.ic_btn_speak_now,  "support"),       // 👈 headset icon
            AccountMenuItem("About StoreLink",       android.R.drawable.ic_dialog_info,    "about")
        )
    }

    // ── Local state (no ViewModel) ───────────────────────────────────────────
    var selectedTab by remember { mutableIntStateOf(4) }   // bottom nav: "Profile" active

    Scaffold(
        bottomBar = {
            HomeBottomNav(
                navController,
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

            // ── 1. Top Bar ────────────────────────────────────────────────────
            ProfileTopBar(onSettingsClick = { /* TODO: navController.navigate("settings") */ })

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                // ── 2. User Info Card ─────────────────────────────────────────
                UserInfoCard(
                    name     = "Satyam Jain",
                    email    = "satyamjain08@gmail.com",
                    phone    = "+91 98765 43210",
                    isVerified = true,
                    onClick  = { /* TODO: navController.navigate("personal_info") */ },
                    onAvatarClick = { /* TODO: open image picker */ }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ── 3. Quick Stats Row ────────────────────────────────────────
                QuickStatsRow(stats = profileStats)

                Spacer(modifier = Modifier.height(20.dp))

                // ── 4. My Addresses Section ────────────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    Text("My Addresses", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
                    Text(
                        "View All",
                        fontSize   = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color      = BrandOrange,
                        modifier   = Modifier.clickable { navController.navigate("my_addresses") }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    savedAddresses.forEach { address ->
                        ProfileAddressRow(
                            address = address,
                            onClick = { /* TODO: navController.navigate("edit_address/${address.id}") */ },
                            onMenuClick = { /* TODO: show edit/delete menu */ }
                        )
                    }

                    // ── Add New Address ──────────────────────────────────────────
                    AddNewAddressRow(onClick = { /* TODO: navController.navigate("add_address") */ })
                }

                Spacer(modifier = Modifier.height(20.dp))

                // ── 5. Account Section ─────────────────────────────────────────
                Text("Account", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderGray, RoundedCornerShape(14.dp))
                ) {
                    accountMenuItems.forEachIndexed { index, item ->
                        AccountMenuRow(
                            item    = item,
                            onClick = { /* TODO: navController.navigate(item.route) */ }
                        )
                        if (index != accountMenuItems.lastIndex) {
                            HorizontalDivider(color = BorderGray)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ── 6. Logout Button ───────────────────────────────────────────
                LogoutButton(
                    onClick = {
                        // TODO: actual logout logic (clear session, etc.)
                        navController.navigate("signin") {
                            popUpTo(0) { inclusive = true }   // pura backstack clear
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


// ─── Component 6: Account Menu Row ───────────────────────────────────────────
/**
 * AccountMenuRow
 * Single row: icon + label (left), chevron (right). Used for Account section.
 */

