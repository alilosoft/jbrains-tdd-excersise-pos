package dev.codingart.learning.tdd.pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class BarcodeOfTypeAny {
    private val display = Display()
    private val catalog = Catalog()
    private val register = SaleController(display, catalog)

    @Test
    fun `product found`() {
        val barCode1: Any = "12345" // kotlin.Any is the equivalent of java.Object
        register.onBarCode(barCode1)
        display.message shouldBe "$10.00"
        val barCode2: Any = "23456"
        register.onBarCode(barCode2)
        display.message shouldBe "$5.00"
    }

    @Test
    fun `product not found`() {
        val barcode: Any = "99999"
        register.onBarCode(barcode)
        display.message shouldBe "Barcode $barcode not found"
    }

    @Test
    fun `empty barcode`() {
        val barcode = ""
        register.onBarCode(barcode)
        display.message shouldBe "Invalid Barcode"
    }
}