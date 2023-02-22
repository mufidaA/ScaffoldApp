package com.example.myapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import com.example.myapp.ui.theme.MyAppTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                    ScaffoldApp()
            }
        }
    }
}
@Composable
fun MainTopBar(title: String, navController: NavController ) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {Text("My app") },
        actions = {
            IconButton(
                onClick = {expanded = !expanded}
            ){
                Icon (Icons.Filled. Menu, contentDescription = null)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

                DropdownMenuItem(onClick = { navController.navigate("info") }) {
                    Text(text = "info")
                }
                DropdownMenuItem(onClick = { navController.navigate("settings") }) {
                    Text(text = "settings")
                }
            }

        },

    )
}
@Composable
fun ScreenTopBar(title: String, navController: NavController ) {
    TopAppBar(
        title = {Text("My app") },
       navigationIcon = {
            IconButton(
                onClick = { navController.navigate("info") }
            ){
                Icon (Icons.Filled.ArrowBack, contentDescription = null)
            }
        },

        )
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController ) {
    Scaffold (
        topBar = {MainTopBar("My app",navController )},
        content =   { Text(text= "content for home screen")},
    )
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InfoScreen(navController: NavController ) {
    Scaffold (
        topBar = {MainTopBar("Info",navController )},
        content =   { Text(text= "content for info screen")},
    )
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController ) {
    Scaffold (
        topBar = {MainTopBar("settings",navController )},
        content =   { Text(text= "content for settings screen")},
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldApp() {
    val  navController = rememberNavController()
    NavHost (
       navController = navController,
        startDestination =   "home"
    ){
        composable(route = "home") { MainScreen(navController)}
        composable(route = "info") { InfoScreen(navController)}
        composable(route = "settings") {SettingsScreen(navController)}
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        ScaffoldApp()
    }
}