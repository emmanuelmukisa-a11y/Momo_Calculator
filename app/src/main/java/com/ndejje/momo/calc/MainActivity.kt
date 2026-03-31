package com.ndejje.momo.calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoMoAppTheme  {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Scaffold(topBar = { MoMoTopBar() }) {
                        innerPadding ->
                        MomoCalcScreen(modifier = Modifier.padding(innerPadding))
                    }
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
    isError: Boolean = false,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
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
fun MomoCalcScreen(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("")}

    val numericAmount = amountInput.toDoubleOrNull()
    val isError = amountInput.isNotEmpty() && numericAmount == null
    val fee = (numericAmount ?: 0.0) * 0.03
    val formattedFee = "UGX %,.0f".format(fee)

    Column(modifier = Modifier.fillMaxSize()
        .padding(dimensionResource(R.dimen.screen_padding)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = stringResource(R.string.app_title),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))

        HoistedAmountInput(
            amount = amountInput,
            onAmountChange = { amountInput = it },
            isError = isError
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))

        Text(
            text = stringResource(R.string.fee_label, formattedFee),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoMoTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_title),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.ug_momo_symbol),
                contentDescription = "MoMo Logo",
                modifier = Modifier
                    .padding(start = dimensionResource(R.dimen.spacing_medium))
                    .height(32.dp)
                    .wrapContentWidth(),
                contentScale = ContentScale.Fit
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun MoMoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MomoTypography,
        shapes = MomoShapes,
        content = content

    )
}

@Preview(showBackground = true)
@Composable
fun MomoCalcPreview() {
    MaterialTheme{
        MomoCalcScreen()
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun PreviewLight(){
    MoMoAppTheme(darkTheme = false){
        MomoCalcScreen()

    }
}

@Preview()
@Composable
fun PreviewDark() {
    MoMoAppTheme(darkTheme = true){
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