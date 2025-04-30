package com.example.appmovilesparcialuno

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appmovilesparcialuno.ui.theme.AppMovilesParcialUnoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Comprobante(
    navController: NavController,
    monto: String?,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val formattedMonto = getNumberWithCurrency("$monto".toFloat())
            Image(
                painter = painterResource(R.drawable.ic_task_completed),
                contentDescription = null
            )
            Text(
                text = "¡Éxito!",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 8.dp)
            )
            Text(
                text = "Has retirado $formattedMonto",
                fontSize = 16.sp
            )
            Button(
                onClick = {
                    navController.navigate("saldo")
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Seguir operando")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComprobantePreview() {
    val mockedMonto = "50000"
    AppMovilesParcialUnoTheme {
        Comprobante(
            rememberNavController(),
            mockedMonto
        )
    }
}