package dev.codingart.learning.tdd.pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class SellOneItemTest {

    @ParameterizedTest(name = "barcode={0} -> price={1}")
    @CsvSource("123456, $9.99", "123444, $4.99", "123455, $1.99")
    fun `when barcode found then display the price`(barCode: String, price: String) {
        val display = Display()
        val priceByBarcode = mutableMapOf(
            "123456" to "$9.99",
            "123444" to "$4.99",
            "123455" to "$1.99"
        )
        val pos = SaleController(display, priceByBarcode)
        pos.onBarCode(barCode)
        display.lastText shouldBe price
    }

    @Test
    fun `when barcode not found then display 'not found' message`() {
        val display = Display()
        val pos = SaleController(display, mutableMapOf())
        val barCode = "123456"
        pos.onBarCode(barCode)
        display.lastText shouldBe "Barcode $barCode not found"
    }

    @ParameterizedTest
    @ValueSource(strings = [" ", "  ", " \n ", " \t "])
    fun `when barcode is blank then display error message`(barCode: String) {
        val display = Display()
        val pos = SaleController(display, mutableMapOf())

        pos.onBarCode(barCode)
        display.lastText shouldBe "Invalid Barcode"
    }
}


