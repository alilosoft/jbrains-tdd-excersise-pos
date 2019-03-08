package tdd_pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class SellOneItemWithBarCode{
    @Test
    fun `when barcode exist display the price`() {
        val display = Display()
        val pos = PointOfSale(display)

        pos.onBarCode("123456")
        display.lastText shouldBe "$10.99"

        pos.onBarCode("123444")
        display.lastText shouldBe "$5.99"

        pos.onBarCode("123455")
        display.lastText shouldBe "$11.99"
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
        display.lastText = items[barCode] ?: "Unknown"
    }
}
