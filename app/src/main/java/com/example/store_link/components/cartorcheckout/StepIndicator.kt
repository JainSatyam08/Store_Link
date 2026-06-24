package com.example.storelink.components.cartorcheckout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store_link.ui.theme.BrandOrange

import com.example.store_link.ui.theme.HintGray

import com.example.store_link.ui.theme.BorderGray
@Composable
fun StepIndicator(currentStep: Int) {
    val steps = listOf("Address", "Payment", "Place Order")

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        steps.forEachIndexed { index, label ->
            val stepNumber = index + 1
            val isActive   = stepNumber <= currentStep

            // Step circle + label
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, if (isActive) BrandOrange else BorderGray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "$stepNumber",
                        fontSize   = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color      = if (isActive) BrandOrange else HintGray
                    )
                }
                Text(
                    label,
                    fontSize   = 13.sp,
                    fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal,
                    color      = if (isActive) BrandOrange else HintGray
                )
            }

            // Connecting line (skip after last step)
            if (index != steps.lastIndex) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .padding(horizontal = 8.dp)
                        .background(BorderGray)
                )
            }
        }
    }
}