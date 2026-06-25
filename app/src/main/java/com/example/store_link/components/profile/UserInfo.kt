package com.example.store_link.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.screen.ProfileStat

import com.example.store_link.ui.theme.BorderGray
import com.example.store_link.ui.theme.BrandOrange
import com.example.store_link.ui.theme.DescGray
import com.example.store_link.ui.theme.GreenTag
import com.example.store_link.ui.theme.GreenTagText
import com.example.store_link.ui.theme.HintGray

import com.example.store_link.ui.theme.LightBg
import com.example.store_link.ui.theme.NavyBlue
import com.example.store_link.ui.theme.PeachBg
import kotlin.collections.forEach

@Composable
fun UserInfoCard(
    name         : String,
    email        : String,
    phone        : String,
    isVerified   : Boolean,
    onClick      : () -> Unit,
    onAvatarClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(LightBg)
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // ── Avatar with camera overlay ───────────────────────────────────────
        Box {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(PeachBg),
                contentAlignment = Alignment.Center
            ) {
                // 👈 Yahan agar user ki profile photo ho to woh lagegi, abhi placeholder icon
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                    contentDescription = "Avatar",
                    tint = BrandOrange,
                    modifier = Modifier.size(32.dp)
                )
            }

            // Camera icon (bottom-right of avatar) — photo change karne ke liye
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(NavyBlue)
                    .clickable { onAvatarClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_camera),
                    contentDescription = "Change photo",
                    tint = Color.White,
                    modifier = Modifier.size(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(14.dp))

        // ── Name, email, verified tag, phone ─────────────────────────────────
        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(email, fontSize = 13.sp, color = HintGray)
                if (isVerified) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(GreenTag)
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.checkbox_on_background),
                            contentDescription = null,
                            tint = GreenTagText,
                            modifier = Modifier.size(10.dp)
                        )
                        Text("Verified", fontSize = 10.sp, fontWeight = FontWeight.Medium, color = GreenTagText)
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_call),
                    contentDescription = null,
                    tint = HintGray,
                    modifier = Modifier.size(13.dp)
                )
                Text(phone, fontSize = 13.sp, color = DescGray)
            }
        }

        Icon(
            painter = painterResource(id = android.R.drawable.ic_media_play),
            contentDescription = null,
            tint = HintGray,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun QuickStatsRow(stats: List<ProfileStat>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderGray, RoundedCornerShape(14.dp))
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        stats.forEach { stat ->
            ProfileStatItem(stat = stat)
        }
    }
}

@Composable
fun ProfileStatItem(stat: ProfileStat) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(PeachBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(id = stat.iconRes), contentDescription = stat.label, tint = BrandOrange, modifier = Modifier.size(20.dp))
            }
            // Count badge (top-right of icon)
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .background(BrandOrange),
                contentAlignment = Alignment.Center
            ) {
                Text(stat.count.toString(), color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(stat.label, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = NavyBlue)
    }
}
