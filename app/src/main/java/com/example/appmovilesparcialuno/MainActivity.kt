package com.example.appmovilesparcialuno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHost

import com.example.appmovilesparcialuno.ui.theme.AppMovilesParcialUnoTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMovilesParcialUnoTheme {
                val navController = rememberNavController()
                var saldo by remember { mutableFloatStateOf(250000f) }
                var lastWithdraw by remember { mutableFloatStateOf(0f) }

                NavHost(
                    navController = navController,
                    startDestination = "saldo"
                ) {
                    composable("saldo") {
                        SaldoCuenta(
                            navController = navController,
                            saldo = saldo,
                            onWithdrawal = { withdrawal ->
                                saldo -= withdrawal
                                lastWithdraw = withdrawal
                            }
                        )
                    }
                    composable("comprobante/{monto}") { backStackEntry ->
                        val monto = backStackEntry.arguments?.getString("monto")
                        Comprobante(
                            navController = navController,
                            monto = monto
                        )
                    }
                }
            }
        }
    }
}