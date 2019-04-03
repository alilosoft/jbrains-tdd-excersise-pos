package dev.codingart.learning.tdd.pos

class Display {
    var message = "Hello"

    // SMELL (primitive obsession): representing the price with string?!!!
    fun showPrice(price: String) {
        message = price
    }

    fun showBarcodeNotFoundMessage(barCode: String) {
        message = "Barcode $barCode not found"
    }

    fun showInvalidBarcodeMessage() {
        message = "Invalid Barcode"
    }
}

class Catalog(private val priceByBarcode: MutableMap<String, String> = mutableMapOf()) {
    // SMELL (primitive obsession): return the price as String
    fun findPrice(barCode: String) = priceByBarcode[barCode] //?: throw IllegalArgumentException("Barcode not found")
}

class Register(
    private val display: Display,
    private val catalog: Catalog
) {

    fun onBarCode(barCode: String) {
        // SMELL: A path that doesn't need a collaborator (catalog),
        // maybe this logic (barcode validation doesn't belong here)
        if (barCode.isBlank()) {
            display.showInvalidBarcodeMessage()
            return
        }

        val price = catalog.findPrice(barCode)
        if (price != null) {
            display.showPrice(price)
        } else {
            display.showBarcodeNotFoundMessage(barCode)
        }
    }

    fun onBarCode(barCode: Any) {
        when (barCode) {
            "12345" -> display.showPrice("$10.00")
            "23456" -> display.showPrice("$5.00")
            "" -> display.message = "Invalid barcode"
            else -> display.message = "Barcode $barCode not found"
        }
    }
}

class Barcode(barcode: String)