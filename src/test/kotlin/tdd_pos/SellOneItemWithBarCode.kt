package tdd_pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class SellOneItemWithBarCode{

    @Test
    fun `when barcode found then display the price`() {
        val display = Display()
        val items = mapOf(
            "123456" to "$10.99",
            "123444" to "$5.99",
            "123455" to "$11.99"
        )
        val pos = SaleController(display, items)

        pos.onBarCode("123456")
        display.lastText shouldBe "$10.99"
        pos.onBarCode("123444")
        display.lastText shouldBe "$5.99"
        pos.onBarCode("123455")
        display.lastText shouldBe "$11.99"
    }

    @Test
    fun `when barcode not found then display 'not found' message`() {
        val display = Display()
        val pos = SaleController(display, emptyMap())
        val barCode = "123456"
        pos.onBarCode(barCode)
        display.lastText shouldBe "Barcode $barCode not found"
    }

    @Test
    fun `when barcode is blank then display error message`() {
        val display = Display()
        val pos = SaleController(display, emptyMap())

        pos.onBarCode("")
        display.lastText shouldBe "Invalid Barcode"
        pos.onBarCode("        ")
        display.lastText shouldBe "Invalid Barcode"
    }
}

class Display {
    var lastText = "Hello"
}

class SaleController(private val display: Display, private val items: Map<String, String>) {

    fun onBarCode(barCode: String) {
        if (barCode.isBlank())
            display.lastText = "Invalid Barcode"
        else
            display.lastText = items[barCode] ?: "Barcode $barCode not found"
    }
}
