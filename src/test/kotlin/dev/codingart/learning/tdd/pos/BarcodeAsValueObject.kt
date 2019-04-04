package dev.codingart.learning.tdd.pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class BarcodeAsValueObject {
    @Test
    fun `barcode found`() {
        val display = Display()
        val catalog = Catalog()
        val register = Register(display, catalog)
        val barcode = Barcode("12345")
        catalog.addProduct(barcode, "$10.00")
        register.onBarCode(barcode)
        display.message shouldBe "$10.00"
    }

    @Test
    fun `another barcode found`() {
        val display = Display()
        val catalog = Catalog()
        val register = Register(display, catalog)
        val barcode = Barcode("23456")
        catalog.addProduct(barcode, "$5.00")
        register.onBarCode(barcode)
        display.message shouldBe "$5.00"
    }
}