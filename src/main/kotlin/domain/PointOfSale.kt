package domain

class Display {
    var lastText = "Hello"
}

class Catalogue(private val priceByBarcode: MutableMap<String, String>) {
    fun findPrice(barCode: String) = priceByBarcode[barCode]
}

class SaleController(private val display: Display,
                     private val catalogue: Catalogue) {

    fun onBarCode(barCode: String) {
        if (barCode.isBlank())
            display.lastText = "Invalid Barcode"
        else {
            val price = catalogue.findPrice(barCode)
            if (price != null) {
                display.lastText = price
            } else {
                display.lastText = "Barcode $barCode not found"
            }
        }

    }
}