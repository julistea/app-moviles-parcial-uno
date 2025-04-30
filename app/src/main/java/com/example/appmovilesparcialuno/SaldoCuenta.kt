package com.example.appmovilesparcialuno

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appmovilesparcialuno.ui.theme.AppMovilesParcialUnoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaldoCuenta(
    navController: NavController,
    saldo:  Float,
    onWithdrawal: (Float) -> Unit,
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
            AccountMoney(saldo)
            WithdrawInput(navController, saldo, onWithdrawal)
        }
    }
}

@Composable
fun AccountMoney (
    saldo: Float,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Saldo",
            fontSize = 16.sp
        )
        Text(
            text = getNumberWithCurrency(saldo),
            fontSize = 40.sp
        )
    }

}

@Composable
fun WithdrawInput(
    navController: NavController,
    saldo: Float,
    onWithdrawal: (Float) -> Unit
) {
    var inputRetiro by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Retirar dinero por",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = inputRetiro,
            onValueChange = {
                inputRetiro = it
            }
        )
        Button(
            onClick = {
                val isWithdrawAllowed = getIsWithdrawAllowed(saldo, inputRetiro)
                if (isWithdrawAllowed) {
                    navController.navigate("comprobante/${inputRetiro}")
                    val withdraw = inputRetiro.toFloat()
                    onWithdrawal(withdraw)
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Retirar")
        }
    }
}

fun getIsWithdrawAllowed(money: Float, moneyToWithdraw: String): Boolean {
    val withdraw = moneyToWithdraw.toFloatOrNull() ?: return false
    return money >= withdraw
}

@Preview(showBackground = true)
@Composable
private fun SaldoCuentaPreview() {
    val mockedSaldo = 500000f
    AppMovilesParcialUnoTheme {
        SaldoCuenta(
            rememberNavController(),
            mockedSaldo,
            { }
        )
    }
}