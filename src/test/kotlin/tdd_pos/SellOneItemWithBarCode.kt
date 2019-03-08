package tdd_pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class SellOneItemWithBarCode{

    val display = Display()
    val pos = PointOfSale(display)

    @Test
    fun `display the correct price for existing barcode`() {
        pos.onBarCode("123456")
        display.lastText shouldBe "$10.99"

        pos.onBarCode("123444")
        display.lastText shouldBe "$5.99"

        pos.onBarCode("123455")
        display.lastText shouldBe "$11.99"
    }

    @Test
    fun `display 'Unknown Barcode' for non existing barcode`() {
        pos.onBarCode("unknown")
        display.lastText shouldBe "Unknown Barcode"
    }

    @Test
    fun `display 'Invalid Barcode' for blank barcode`() {
        pos.onBarCode("")
        display.lastText shouldBe "Invalid Barcode"
        pos.onBarCode("    ")
        display.lastText shouldBe "Invalid Barcode"
    }
}

class Display {
    var lastText = "Hello"
}

class PointOfSale(private val display: Display) {

    private val items = mapOf(
        "123456" to "$10.99",
        "123444" to "$5.99",
        "123455" to "$11.99"
    )

    fun onBarCode(barCode: String) {
        if (barCode.isBlank())
            display.lastText = "Invalid Barcode"
        else
            display.lastText = items[barCode] ?: "Unknown Barcode"
    }
}
