package com.example

import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions


fun Barcode(){
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            com.google.mlkit.vision.barcode.common.Barcode.FORMAT_QR_CODE,
            com.google.mlkit.vision.barcode.common.Barcode.FORMAT_AZTEC
        )
        .build()
}