package com.example.barcode

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.barcode.ui.theme.BarCodeTheme
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BarCodeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    Barcode(context = context)
                }
            }
        }
    }
}

@Composable
private fun Barcode(context: Context) {
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_AZTEC
        )
        .enableAutoZoom() // available on 16.1.0 and higher
        .build()

    val scanner = GmsBarcodeScanning.getClient(context, options)

    scanner.startScan()
        .addOnSuccessListener { barcode ->
            val rawValue: String? = barcode.rawValue
            Toast.makeText(context, "Scan result: $rawValue", Toast.LENGTH_LONG).show()
        }
        .addOnCanceledListener {
            Toast.makeText(context, "Scan canceled", Toast.LENGTH_SHORT).show()
            // Task canceled
        }
        .addOnFailureListener { e ->
            e.printStackTrace()

            Toast.makeText(context, "Scan failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
}