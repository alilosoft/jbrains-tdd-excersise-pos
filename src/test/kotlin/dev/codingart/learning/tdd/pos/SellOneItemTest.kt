package dev.codingart.learning.tdd.pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class SellOneItemTest {

    private fun emptyCatalogue() = Catalogue(mutableMapOf())

    @ParameterizedTest(name = "barcode={0} -> price={1}")
    @CsvSource("123456, $9.99", "123444, $4.99", "123455, $1.99")
    fun `when barcode found then display the price`(barCode: String, price: String) {
        val display = Display()
        val priceByBarcode = mutableMapOf(
            "123456" to "$9.99",
            "123444" to "$4.99",
            "123455" to "$1.99"
        )
        val catalogue = Catalogue(priceByBarcode)
        val pos = SaleController(display, catalogue)
        pos.onBarCode(barCode)
        display.message shouldBe price
    }

    @Test
    fun `when barcode not found then display 'not found' message`() {
        val display = Display()
        val pos = SaleController(display, emptyCatalogue())
        val barCode = "123456"
        pos.onBarCode(barCode)
        display.message shouldBe "Barcode $barCode not found"
    }



    @ParameterizedTest
    @ValueSource(strings = [" ", "  ", " \n ", " \t "])
    fun `when barcode is blank then display error message`(barCode: String) {
        val display = Display()
        val pos = SaleController(display, emptyCatalogue())

        pos.onBarCode(barCode)
        display.message shouldBe "Invalid Barcode"
    }
}


