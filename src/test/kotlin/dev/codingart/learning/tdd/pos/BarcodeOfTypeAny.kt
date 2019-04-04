package dev.codingart.learning.tdd.pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class BarcodeOfTypeAny {

    private val display = Display()
    private val catalog = Catalog()
    private val register = Register(display, catalog)

    @Test
    fun `product found`() {
        val barCode = Any() // kotlin.Any is the equivalent of java.Object
        catalog.addProduct(barCode, "$10.00")
        register.onBarCode(barCode)
        display.message shouldBe "$10.00"
    }

    @Test
    fun `another product found`() {
        val barCode = Any()
        catalog.addProduct(barCode, "$5.00")
        register.onBarCode(barCode)
        display.message shouldBe "$5.00"
    }

    @Test
    fun `product not found`() {
        val barcode = Any()
        register.onBarCode(barcode)
        display.message shouldBe "Barcode $barcode not found"
    }

    @Test
    fun `invalid barcode`() {
        val barcode = Any()
        register.onBarCode(barcode)
        //display.message shouldBe "Invalid Barcode"
    }
}