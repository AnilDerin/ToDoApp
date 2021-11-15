package com.anilderinbay.to_doapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.anilderinbay.to_doapp.navigation.SetupNavigation
import com.anilderinbay.to_doapp.ui.theme.ToDoAppTheme
import com.anilderinbay.to_doapp.ui.theme.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

import kotlin.reflect.KProperty

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private lateinit var navController : NavHostController
    private val sharedViewModel : SharedViewModel by viewModels()


    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                navController = rememberNavController()
                SetupNavigation(
                    navController = navController,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}



