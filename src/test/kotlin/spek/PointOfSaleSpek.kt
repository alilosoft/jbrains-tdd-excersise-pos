package spek

import dev.codingart.learning.tdd.pos.Display
import dev.codingart.learning.tdd.pos.SaleController
import io.kotlintest.matchers.maps.shouldNotContainKey
import io.kotlintest.matchers.string.shouldBeBlank
import io.kotlintest.shouldBe
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class PointOfSaleSpek : Spek({

    Feature("sell one item with barcode") {
        Scenario("barcode is found") {

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

        Scenario("barcode not found") {

            val priceByBarcode= mutableMapOf<String, String>()
            val display = Display()
            val saleController = SaleController(display, priceByBarcode)
            val barCode = "123456"
            Given("the barcode doesn't exist") {
                priceByBarcode.shouldNotContainKey(barCode)
            }

            When("receiving the barcode") {
                saleController.onBarCode(barCode)
            }

            Then("the display shows 'barcode not found' message") {
                display.lastText shouldBe "Barcode $barCode not found"
            }
        }

        Scenario("empty/blank barcode") {

            val priceByBarcode= mutableMapOf<String, String>()
            val display = Display()
            val saleController = SaleController(display, priceByBarcode)

            listOf("", "  ", " \n ", " \r ", " \t ").forEach {

                Given("the barcode is blank") {
                    it.shouldBeBlank()
                }

                When("receiving the barcode") {
                    saleController.onBarCode(it)
                }

                Then("the display shows 'invalid barcode' message") {
                    display.lastText shouldBe "Invalid Barcode"
                }

            }

        }
    }
})
