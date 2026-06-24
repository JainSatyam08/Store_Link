package com.example.storelink.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.storelink.screen.CategoryListingScreen
import com.example.storelink.screen.CheckoutScreen
import com.example.storelink.screen.HomeScreen
import com.example.storelink.screen.ProductDetailScreen
import com.example.storelink.screen.SignInScreen
import com.example.storelink.screen.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(
        navController=navController,
        startDestination = "splash"
    ){
        composable("splash"){
            SplashScreen(navController)
        }
        composable("signin"){
            SignInScreen(navController)
        }
        composable("home"){
            HomeScreen(navController)
        }
        composable("category_listing"){
            CategoryListingScreen(navController)
        }
        composable ("product_detail"){
            ProductDetailScreen(navController)
        }
        composable("cart") {
            CheckoutScreen(navController)
        }
    }
}
