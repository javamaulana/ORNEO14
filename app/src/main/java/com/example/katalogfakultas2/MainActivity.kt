package com.example.katalogfakultas2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.katalogfakultas2.ui.theme.screen.AboutScreen
import com.example.katalogfakultas2.ui.theme.screen.DetailScreen
import com.example.katalogfakultas2.ui.theme.screen.HomeScreen
import com.example.katalogfakultas2.ui.theme.screen.LoginScreen
import com.example.katalogfakultas2.ui.theme.KatalogFakultas2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KatalogFakultas2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable(
            route = "detail/{fakultasId}",
            arguments = listOf(navArgument("fakultasId") {
                type = NavType.IntType
                nullable = false
            })
        ) { backStackEntry ->
            val fakultasId = backStackEntry.arguments?.getInt("fakultasId")
            if (fakultasId != null) {
                DetailScreen(navController = navController, fakultasId = fakultasId)
            } else {
                navController.popBackStack()
            }
        }
        composable("about") {
            AboutScreen(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KatalogFakultas2Theme {
        AppNavigation()
    }
}