package com.example.telefonrehberi2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.telefonrehberi2.ui.theme.TelefonRehberi2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route

            TelefonRehberi2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = { if (currentRoute == "anasayfa") AppBar(navController)}
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {

                        NavHost(navController, startDestination = "anasayfa") {

                            composable("anasayfa"){  }
                            composable("kisi_ekle") {  }
                            composable("kisi_duzenle"){  }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppBar(navController: NavController) {

    FloatingActionButton(
        onClick = {navController.navigate("kisi_ekle")},
        containerColor = Color(0xFF1976D2),
        contentColor = Color.White
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "")
    }
}