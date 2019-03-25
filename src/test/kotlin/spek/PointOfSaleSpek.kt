package spek

import dev.codingart.learning.tdd.pos.Display
import dev.codingart.learning.tdd.pos.SaleController
import io.kotlintest.shouldBe
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class PointOfSaleSpek : Spek({

    Feature("sell one item with barcode") {
        Scenario("the barcode is found") {

            val priceByBarcode= mutableMapOf<String, String>()
            val display = Display()
            val saleController = SaleController(display, priceByBarcode)

            Given("a barcode exist with a price") {
                priceByBarcode["123456"] = "$10.50"
            }

            When("receiving the barcode") {
                saleController.onBarCode("123456")
            }

            Then("the display should show the correct price") {
                display.lastText shouldBe "$10.50"
            }
        }
    }
})
