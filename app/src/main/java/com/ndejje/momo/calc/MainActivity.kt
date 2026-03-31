package com.ndejje.momo.calc

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ndejje.momo.calc.ui.theme.Momo_CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(typography = MomoTypography) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MomoCalcScreen()
                }

            }

                }
            }
        }



/*
@Composable
fun BrokenInput() {
    var amount = ""

    TextField(
        value = amount,
        onValueChange= { amount = it },
        label= {
            Text(stringResource(R.string.enter_amount))
        }
    )
}

@Composable
fun InternalStateInput(){
    var amount by remember { mutableStateOf( value = "")}

    TextField(
        value = amount,
        onValueChange = { amount = it },
        label = {
            Text(
                stringResource(R.string.enter_amount)
            )
        }
    )

}
*/

@Composable
fun HoistedAmountInput(
    amount: String,
    onAmountChange: (String) -> Unit,
    isError: Boolean = false
){
    Column {
        TextField(
            value = amount,
            onValueChange = onAmountChange,
            label = {
                Text(
                    stringResource(id = R.string.enter_amount)
                )
                if (isError) {
                    Text(
                        text = stringResource(id = R.string.error_numbers_only),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        )
    }
}

@Composable
fun MomoCalcScreen() {
    var amountInput by remember { mutableStateOf("")}

    val numericAmount = amountInput.toDoubleOrNull()
    val isError = amountInput.isNotEmpty() && numericAmount == null
    val fee = (numericAmount ?: 0.0) * 0.03
    val formattedFee = "UGX %,.0f".format(fee)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.app_title),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        HoistedAmountInput(
            amount = amountInput,
            onAmountChange = { amountInput = it },
            isError = isError
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.fee_label, formattedFee),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MomoCalcPreview() {
    MaterialTheme{
        MomoCalcScreen()
    }
}






/*
@Preview(showBackground = true)
@Composable
fun PreviewEmptyInput() {
    MaterialTheme(){
            HoistedAmountInput("", {})
        }

    }

@Preview(showBackground = true)
@Composable
fun PreviewFilled() {
    MaterialTheme(){
        HoistedAmountInput("100,000,000", {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewError() {
    MaterialTheme(){
        HoistedAmountInput("abc", {}, true)
    }
}


@Preview(showBackground = true)
@Composable
fun BrokenInputPreview() {
    MaterialTheme() {
        BrokenInput()
    }
}

@Preview(showBackground = true)
@Composable
fun InternalStateInputPreview() {
    MaterialTheme() {
        InternalStateInput()
    }
}

*/