package dev.codingart.learning.tdd.pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class SellOneItemTest {

    private val display = Display()
    private val emptyCatalogue = Catalog()
    private val catalogue by lazy {
        Catalog(
            mutableMapOf(
                "123456" to "$9.99",
                "123444" to "$4.99",
                "123455" to "$1.99"
            )
        )
    }
    private val saleController = SaleController(display, catalogue)

    @ParameterizedTest(name = "barcode={0} -> price={1}")
    @CsvSource("123456, $9.99", "123444, $4.99", "123455, $1.99")
    fun `when barcode found then display the price`(barCode: String, price: String) {
        saleController.onBarCode(barCode)
        display.message shouldBe price
    }

    @Test
    fun `when barcode not found then display 'not found' message`() {
        val barCode = "99999"
        saleController.onBarCode(barCode)
        display.message shouldBe "Barcode $barCode not found"
    }


    @ParameterizedTest
    @ValueSource(strings = [" ", " \n ", " \t "])
    fun `when barcode is blank then display error message`(barCode: String) {
        val pos = SaleController(display, emptyCatalogue)
        pos.onBarCode(barCode)
        display.message shouldBe "Invalid Barcode"
    }
}


