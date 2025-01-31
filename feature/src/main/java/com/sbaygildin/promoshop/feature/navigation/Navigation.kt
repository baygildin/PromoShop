package com.sbaygildin.promoshop.feature.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sbaygildin.promoshop.feature.promo.PromoScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "promoscreen") {


        composable("promoscreen") {
            PromoScreen(
                viewModel = hiltViewModel(),
            )
        }
    }
}

