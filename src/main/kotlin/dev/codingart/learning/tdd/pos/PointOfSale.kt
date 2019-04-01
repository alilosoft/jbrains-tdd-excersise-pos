package dev.codingart.learning.tdd.pos

class Display {
    var message = "Hello"

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

class Catalogue(private val priceByBarcode: MutableMap<String, String>) {
    fun findPrice(barCode: String): String? {
        return priceByBarcode[barCode]
    }
}

class SaleController(
    private val display: Display,
    private val catalogue: Catalogue
) {

    fun onBarCode(barCode: String) {
        if (barCode.isBlank()) {
            display.showInvalidBarcodeMessage()
            return
        }

        val price = catalogue.findPrice(barCode)
        if (price != null) {
            display.showPrice(price)
        } else {
            display.showBarcodeNotFoundMessage(barCode)
        }
    }


}