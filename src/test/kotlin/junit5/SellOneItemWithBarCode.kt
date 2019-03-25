package junit5

import dev.codingart.learning.tdd.pos.Display
import dev.codingart.learning.tdd.pos.SaleController
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class SellOneItemWithBarCode {

    @Test
    fun `when barcode found then display the price`() {
        val display = Display()
        val priceByBarcode = mutableMapOf(
            "123456" to "$10.99",
            "123444" to "$5.99",
            "123455" to "$11.99"
        )
        val pos = SaleController(display, priceByBarcode)

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
        val pos = SaleController(display, mutableMapOf())
        val barCode = "123456"
        pos.onBarCode(barCode)
        display.lastText shouldBe "Barcode $barCode not found"
    }

    @Test
    fun `when barcode is blank then display error message`() {
        val display = Display()
        val pos = SaleController(display, mutableMapOf())

        pos.onBarCode("")
        display.lastText shouldBe "Invalid Barcode"
        pos.onBarCode("        ")
        display.lastText shouldBe "Invalid Barcode"
    }
}


