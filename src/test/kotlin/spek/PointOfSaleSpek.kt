package spek

import domain.Catalogue
import domain.Display
import domain.SaleController
import io.kotlintest.shouldBe
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class PointOfSaleSpek : Spek({

    Feature("sell one item with barcode") {
        Scenario("the barcode is found") {

            val items= mutableMapOf<String, String>()
            val catalogue = Catalogue(items)
            val display = Display()
            val register = SaleController(display, catalogue)

            Given("an item with barcode and price") {
                items["123456"] = "$10.50"
            }

            When("receiving the barcode") {
                register.onBarCode("123456")
            }

            Then("the display should show the correct price") {
                display.lastText shouldBe "$10.50"
            }
        }
    }
})
