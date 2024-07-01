// 30th of June 2024
package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier = Modifier) {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(0.01) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Stacks fields below each other
        Text("Unit Converter")
        Spacer(modifier = modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
            },
            label = { Text("Enter Value") }
        )

        Spacer(modifier = modifier.height(16.dp))
        Row {
            Column {
                // Input column
                // Using column instead of box so i can not only group but also constrain vertically
                // This ensures the dropdown menu actually drops down
                Button(onClick = { iExpanded=true }) {
                    // Input button
                    Text("Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                DropdownMenuItem(
                    text = { Text("Centimeters") },
                    onClick = {
                        iExpanded=false
                        inputUnit="Centimeters"
                        conversionFactor.doubleValue=0.01
                })
                DropdownMenuItem(text = { Text("Meters") }, onClick = { /*TODO*/ })
                DropdownMenuItem(text = { Text("Feet") }, onClick = { /*TODO*/ })
                DropdownMenuItem(text = { Text("Millimeters") }, onClick = { /*TODO*/ })
                }
            }
            Spacer(modifier = modifier.width(16.dp))
            Column {
                // Output column
                // Using column instead of box so i can not only group but also constrain vertically
                // This ensures the dropdown menu actually drops down
                Button(onClick = { oExpanded=true }) {
                    // Output button
                    Text("Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                DropdownMenuItem(text = { Text("Centimeters") }, onClick = { /*TODO*/ })
                DropdownMenuItem(text = { Text("Meters") }, onClick = { /*TODO*/ })
                DropdownMenuItem(text = { Text("Feet") }, onClick = { /*TODO*/ })
                DropdownMenuItem(text = { Text("Millimeters") }, onClick = { /*TODO*/ })
                }
            }
        }
        Spacer(modifier = modifier.height(16.dp))
        Text("Result:")
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}