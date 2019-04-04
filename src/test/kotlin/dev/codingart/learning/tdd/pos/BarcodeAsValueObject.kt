package dev.codingart.learning.tdd.pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class BarcodeAsValueObject {
    private val display = Display()
    private val catalog = Catalog()
    private val register = Register(display, catalog)

    @Test
    fun `barcode found`() {
        val barcode = Barcode("12345")
        catalog.addProduct(barcode, "$10.00")
        register.onBarCode(barcode)
        display.message shouldBe "$10.00"
    }

    @Test
    fun `another barcode found`() {
        val barcode = Barcode("23456")
        catalog.addProduct(barcode, "$5.00")
        register.onBarCode(barcode)
        display.message shouldBe "$5.00"
    }
}