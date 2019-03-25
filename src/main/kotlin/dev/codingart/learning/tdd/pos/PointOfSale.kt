package dev.codingart.learning.tdd.pos

class Display {
    var lastText = "Hello"
}

class SaleController(private val display: Display, private val priceByBarcode: MutableMap<String, String>) {

    fun onBarCode(barCode: String) {
        if (barCode.isBlank()) {
            display.lastText = "Invalid Barcode"
            return
        }
        val price = priceByBarcode[barCode]
        if (price != null) {
            display.lastText = price
        } else {
            display.lastText = "Barcode $barCode not found"
        }
    }
}