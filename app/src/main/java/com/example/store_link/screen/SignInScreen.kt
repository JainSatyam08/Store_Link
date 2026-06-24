package com.example.storelink.screen



import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.GMobiledata
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
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
import com.example.store_link.ui.theme.SidebarBg
import com.example.store_link.ui.theme.SubtextGray
import com.example.store_link.ui.theme.WarmBg
import com.example.storelink.components.loginscreen.HeaderSection
import com.example.storelink.viewmodel.LoginViewModel


/**
 * LoginScreen
 *
 * Design ke exactly jaisi screen:
 *  - Top: warm peach bg, logo left + shopping illustration right
 *  - Bottom: white rounded card with form
 *
 * @param viewModel      AuthViewModel (by default auto-created)
 * @param onLoginSuccess navigate to home screen
 * @param onRegisterClick navigate to signup screen
 * @param onBack         back arrow click
 */
@Composable
fun SignInScreen(
    nav: NavHostController,
    //onBack        : () -> Unit
) {
    // ── Collect states from ViewModel ──────────────────────────────────────
    val loginViewModel: LoginViewModel = viewModel()
    // ── Root: scrollable column (keyboard ke liye) ─────────────────────────
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmBg)
            .verticalScroll(rememberScrollState())
    ) {

        // ── Top Header Section (warm peach bg) ────────────────────────────
        HeaderSection()

        // ── Bottom Form Card (white, rounded top corners) ─────────────────
        // Yeh card top mein slightly overlap karta hai header pe
        FormCard(
            nav = nav,
            loginViewModel=loginViewModel
            /*email              = loginViewModel.email,
            password           = loginViewModel.password,
            onEmailChange      = loginViewModel.updateemail(it)
            onPasswordChange   = viewModel::onPasswordChange,
            onTogglePassword   = viewModel::togglePasswordVisibility,
            onLoginClick       = viewModel::login,
            onRegisterClick    = onRegisterClick*/
        )
    }
}

// ─── Component 1: Header ──────────────────────────────────────────────────────
/**
 * HeaderSection
 * Top wala peach background area:
 *   - Back arrow (top left)
 *   - Logo (left side)
 *   - Shopping illustration (right side)
 */


// ─── Component 2: Form Card ───────────────────────────────────────────────────
/**
 * FormCard
 * White card with rounded top corners overlapping the header.
 * Contains all login form elements.
 */
@Composable
fun FormCard(
    nav: NavHostController,
    loginViewModel: LoginViewModel
    /*email             : String,
    password          : String,
    isPasswordVisible : Boolean,
    authState         : AuthUiState,
    onEmailChange     : (String) -> Unit,
    onPasswordChange  : (String) -> Unit,
    onTogglePassword  : () -> Unit,
    onLoginClick      : () -> Unit,
    onRegisterClick   : () -> Unit*/
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-24).dp)           // header ke upar thoda overlap
            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
            .background(CardWhite)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {

            // ── Heading ───────────────────────────────────────────────────
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text       = "Welcome Back!",
                    fontSize   = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color      = NavyBlue
                )
                Text(
                    text     = "Login to continue shopping",
                    fontSize = 14.sp,
                    color    = SubtextGray
                )
            }

            // ── Error message (agar koi ho) ───────────────────────────────

            // ── Email Field ───────────────────────────────────────────────
            LabeledInputField(label = "Email") {
                StoreLinkTextField(
                    value         = loginViewModel.email,
                    onValueChange = {loginViewModel.updateemail(it)},
                    placeholder   = "Enter your email",
                    leadingIcon   = {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = "Password icon",
                            tint = HintGray
                        )
                    },
                    keyboardType  = KeyboardType.Email
                )
            }

            // ── Password Field ────────────────────────────────────────────
            LabeledInputField(label = "Password") {
                StoreLinkTextField(
                    value         = loginViewModel.password,
                    onValueChange = {loginViewModel.updatepassword(it)},
                    placeholder   = "Enter your password",
                    leadingIcon   = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "Password icon",
                            tint = HintGray
                        )
                    },
                    /*trailingIcon  = {
                        // Eye icon — toggle password visibility
                        Icon(
                            painter = painterResource(
                                /*id = if (isPasswordVisible) R.drawable.ic_eye_on
                                else R.drawable.ic_eye_off*/
                            ),
                            contentDescription = "Toggle password",
                            tint     = HintGray,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { onTogglePassword() }
                        )
                    },*/
                    /*visualTransformation = if (isPasswordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    keyboardType = KeyboardType.Password*/
                )
            }

            // ── Forgot Password ───────────────────────────────────────────
            Text(
                text      = "Forgot Password?",
                color     = BrandOrange,
                fontSize  = 13.sp,
                fontWeight = FontWeight.SemiBold,
                modifier  = Modifier
                    .fillMaxWidth()
                    .clickable { /* TODO: navigate to forgot password */ }
                    .wrapContentWidth(Alignment.End)    // right aligned
            )

            // ── Login Button ──────────────────────────────────────────────
            ContinueButton(
                onClick = {
                    loginViewModel.validatelogin()
                    if (loginViewModel.error.isEmpty()) {
                        nav.navigate("home")
                    }
                }
            )

            // ── OR Divider ────────────────────────────────────────────────
            OrDivider()

            // ── Google Sign In Button ─────────────────────────────────────
            OutlinedButton(
                onClick  = { /* TODO: Google sign in */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape    = RoundedCornerShape(12.dp),
                colors   = ButtonDefaults.outlinedButtonColors(
                    contentColor = NavyBlue
                ),
                border   = androidx.compose.foundation.BorderStroke(1.dp, BorderGray)
            ) {
                Row(
                    verticalAlignment      = Alignment.CenterVertically,
                    horizontalArrangement  = Arrangement.spacedBy(10.dp)
                ) {
                    // Google "G" logo
                    Icon(
                        imageVector = Icons.Outlined.GMobiledata,
                        contentDescription = "Password icon",
                        tint = HintGray
                    )
                    Text(
                        text       = "Continue with Google",
                        fontSize   = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // ── Register Link ─────────────────────────────────────────────
            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment     = Alignment.CenterVertically
            ) {
                Text(
                    text     = "Don't have an account? ",
                    fontSize = 14.sp,
                    color    = SubtextGray
                )
                Text(
                    text       = "Register",
                    fontSize   = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color      = BrandOrange,
                    //modifier   = Modifier.clickable { onRegisterClick() }
                )
            }
        }
    }
}

// ─── Reusable Small Components ────────────────────────────────────────────────

/**
 * LabeledInputField
 * Label text upar, content (TextField) neeche.
 * Generic hai — email aur password dono ke liye use hota hai.
 */

@Composable
fun ContinueButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape    = RoundedCornerShape(12.dp),
        colors   = ButtonDefaults.buttonColors(
            containerColor = BrandOrange,
            contentColor   = Color.White
        )
    ) {
        Text(
            text = "Continue",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,  // Slightly bold — button text ke liye appropriate
            color = Color.White                // White text on green background
        )
    }
}
@Composable
private fun LabeledInputField(
    label  : String,
    content: @Composable () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text       = label,
            fontSize   = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color      = NavyBlue
        )
        content()
    }
}

/**
 * StoreLinkTextField
 * App ka custom styled text field.
 * Rounded border, leading icon, optional trailing icon.
 */
@Composable
private fun StoreLinkTextField(
    value               : String,
    onValueChange       : (String) -> Unit,
    placeholder         : String,
    leadingIcon         : @Composable () -> Unit,
    trailingIcon        : (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType        : KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value         = value,
        onValueChange = onValueChange,
        placeholder   = {
            Text(text = placeholder, color = HintGray, fontSize = 14.sp)
        },
        leadingIcon   = leadingIcon,
        trailingIcon  = trailingIcon,
        singleLine    = true,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier      = Modifier.fillMaxWidth(),
        shape         = RoundedCornerShape(12.dp),
        colors        = OutlinedTextFieldDefaults.colors(
            focusedBorderColor   = BrandOrange,
            unfocusedBorderColor = BorderGray,
            focusedContainerColor   = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor          = BrandOrange
        )
    )
}

/**
 * OrDivider
 * Horizontal lines with "OR" text in center — separates login from Google.
 */
@Composable
private fun OrDivider() {
    Row(
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier              = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = BorderGray)
        Text(text = "OR", color = HintGray, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        HorizontalDivider(modifier = Modifier.weight(1f), color = BorderGray)
    }
}

/**
 * OrangeDividerSmall
 * Wahi splash screen wala line•dot•line divider, smaller version.
 */
