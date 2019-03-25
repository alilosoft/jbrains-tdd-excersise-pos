package dev.codingart.learning.tdd.pos

class Display {
    var lastText = "Hello"
}

class SaleController(private val display: Display, private val priceByBarcode: MutableMap<String, String>) {

    fun onBarCode(barCode: String) {
        if (barCode.isBlank()) {
            showInvalidBarcodeMessage()
            return
        }

        if (priceByBarcode.containsKey(barCode)) {
            showProductPrice(barCode)
        } else {
            showBarcodeNotFoundMessage(barCode)
        }
    }

    private fun showProductPrice(barCode: String) {
        display.lastText = priceByBarcode.getValue(barCode)
    }

    private fun showBarcodeNotFoundMessage(barCode: String) {
        display.lastText = "Barcode $barCode not found"
    }

    private fun showInvalidBarcodeMessage() {
        display.lastText = "Invalid Barcode"
    }
}