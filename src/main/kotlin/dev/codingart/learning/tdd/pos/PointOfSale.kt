package dev.codingart.learning.tdd.pos

class Display {
    var lastText = "Hello"

    fun showPrice(price: String) {
        lastText = price
    }

    fun showBarcodeNotFoundMessage(barCode: String) {
        lastText = "Barcode $barCode not found"
    }

    fun showInvalidBarcodeMessage() {
        lastText = "Invalid Barcode"
    }
}

class SaleController(private val display: Display, private val priceByBarcode: MutableMap<String, String>) {

    fun onBarCode(barCode: String) {
        if (barCode.isBlank()) {
            display.showInvalidBarcodeMessage()
            return
        }

        if (priceByBarcode.containsKey(barCode)) {
            display.showPrice(findPrice(barCode))
        } else {
            display.showBarcodeNotFoundMessage(barCode)
        }
    }

    private fun findPrice(barCode: String): String {
        return priceByBarcode.getValue(barCode)
    }

}