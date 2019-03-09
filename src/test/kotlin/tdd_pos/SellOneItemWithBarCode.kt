package tdd_pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class SellOneItemWithBarCode{

    val display = Display()
    val pos = SaleController(display)

    @Test
    fun `barcode found`() {
        pos.onBarCode("123456")
        display.lastText shouldBe "$10.99"
    }

    @Test
    fun `different barcode, different price`() {
        pos.onBarCode("123444")
        display.lastText shouldBe "$5.99"

        pos.onBarCode("123455")
        display.lastText shouldBe "$11.99"
    }

    @Test
    fun `barcode not found`() {
        pos.onBarCode("99999")
        display.lastText shouldBe "Barcode 99999 not found"
    }

    @Test
    fun `blank barcode`() {
        pos.onBarCode("")
        display.lastText shouldBe "Invalid Barcode"
        pos.onBarCode("    ")
        display.lastText shouldBe "Invalid Barcode"
    }
}

class Display {
    var lastText = "Hello"
}

class SaleController(private val display: Display) {

    private val items = mapOf(
        "123456" to "$10.99",
        "123444" to "$5.99",
        "123455" to "$11.99"
    )

    fun onBarCode(barCode: String) {
        if (barCode.isBlank())
            display.lastText = "Invalid Barcode"
        else
            display.lastText = items[barCode] ?: "Barcode $barCode not found"
    }
}
