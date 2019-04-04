package dev.codingart.learning.tdd.pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class BarcodeAsValueObject {
    @Test
    fun `barcode found`() {
        val display = Display()
        val register = Register(display, Catalog())
        val barcode = Barcode("12345")
        register.onBarCode(barcode)
        display.message shouldBe "$10.00"
    }

}