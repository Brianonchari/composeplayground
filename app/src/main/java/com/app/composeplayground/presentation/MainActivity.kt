package com.app.composeplayground.presentation

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.composeplayground.R
import com.app.composeplayground.presentation.coin_detail.CoinDetailScreen
import com.app.composeplayground.presentation.coin_list.CoinListScreen
import com.app.composeplayground.presentation.ui.theme.ComposeplaygroundTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeplaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
                    Navigation()
                }
            }
        }
    }

    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "splash_screen") {
            composable("splash_screen") {
                SplashScreen(navController = navController)
            }

            composable("main_screen") {
                Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            CoinListScreen(navController)
                        }
                        composable(
                            route = Screen.CoinDetailScreen.route + "/{coinId}"
                        ) {
                            CoinDetailScreen()
                        }
                    }
                    
                }
            }
        }
    }

    @Composable
    fun SplashScreen(navController: NavController) {
        val scale = remember { Animatable(0f) }
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.3f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                ))
            delay(3000L)
            navController.navigate("main_screen")
        }
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value)
            )
        }
    }
}

