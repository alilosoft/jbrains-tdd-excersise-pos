package tdd_pos

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class SellOneItemWithBarCode{
    @Test
    fun `when barcode exist display the price`() {
        val pos = PointOfSale()
        val barCode = "123456"
        pos.onBarCode(barCode)

        val display = Display()
        display.lastPrice() shouldBe "$10.99"
    }
}

class Display {
    fun lastPrice(): String {
        return "$10.99"
    }

}

class PointOfSale {
    fun onBarCode(barCode: String) {

    }
}
