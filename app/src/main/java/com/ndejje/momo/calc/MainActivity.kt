package com.ndejje.momo.calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.ndejje.momo.calc.ui.theme.Momo_CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                }
            }
        }




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

/*
@Composable
fun HoistedAmountInput(
    amount: String,
    onAmountChange: (String) -> Unit,
    isError: Boolean = false
){
  TextField(
      value = amount,
      onValueChange = onAmountChange,
      label = {
          Text(
              stringResource(id = R.string.enter_amount)
          )
          if(isError){
              Text(
                  text= stringResource(id = R.string.error_numbers_only),
                  color = MaterialTheme.colorScheme.error,
                  style = MaterialTheme.typography.headlineSmall
              )
          }
      }
  )
}



 */
@Preview(showBackground = true)
@Composable
fun MomoCalcPreview() {
    Momo_CalculatorTheme {
        MaterialTheme(){
            BrokenInput()
        }

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