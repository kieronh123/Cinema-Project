import time
import unittest

from selenium import webdriver

class PaymentTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.get("http://localhost:5000/payment/adult")


    def tearDown(self):
        self.driver.close()

    # def testCorrectAttempt(self):
    #     name = self.driver.find_element_by_name("Name")
    #     name.send_keys("name")
    #
    #     cardNumber = self.driver.find_element_by_name("Card Number")
    #     cardNumber.send_keys("8857467364736477")
    #
    #     expiryDate = self.driver.find_element_by_name("Expiry Date")
    #     expiryDate.send_keys("12/28")
    #
    #     securityCode = self.driver.find_element_by_name("Security Code")
    #     securityCode.send_keys("3453")
    #     securityCode.submit()
    #     time.sleep(1)
    #
    #     assert self.driver.find_element_by_name("message").text == "Payment Confirmed"


    def testNoName(self):
        cardNumber = self.driver.find_element_by_name("Card Number")
        cardNumber.send_keys("8857467364736477")

        expiryDate = self.driver.find_element_by_name("Expiry Date")
        expiryDate.send_keys("12/28")

        securityCode = self.driver.find_element_by_name("Security Code")
        securityCode.send_keys("3453")
        securityCode.submit()
        time.sleep(1)

        assert self.driver.find_element_by_name("message").text == "A name must be entered"


    # def testIncorrectNumberAttempt(self):
    #     name = self.driver.find_element_by_name("Name")
    #     name.send_keys("name")
    #
    #     cardNumber = self.driver.find_element_by_name("Card Number")
    #     cardNumber.send_keys("88574673647364778888")
    #
    #     expiryDate = self.driver.find_element_by_name("Expiry Date")
    #     expiryDate.send_keys("12/28")
    #
    #     securityCode = self.driver.find_element_by_name("Security Code")
    #     securityCode.send_keys("3453")
    #     securityCode.submit()
    #     time.sleep(1)
    #
    #     assert self.driver.find_element_by_name("message").text == "Card number must be less than 19 digits"
    #
    #
    # def testIncorrectDateAttempt(self):
    #     name = self.driver.find_element_by_name("Name")
    #     name.send_keys("name")
    #
    #     cardNumber = self.driver.find_element_by_name("Card Number")
    #     cardNumber.send_keys("8857467364736477")
    #
    #     expiryDate = self.driver.find_element_by_name("Expiry Date")
    #     expiryDate.send_keys("123/28")
    #
    #     securityCode = self.driver.find_element_by_name("Security Code")
    #     securityCode.send_keys("3453")
    #     securityCode.submit()
    #     time.sleep(1)
    #
    #     assert self.driver.find_element_by_name("message").text == "Expiry date was not in the correct format"
    #
    # def testIncorrectCodeAttempt(self):
    #     name = self.driver.find_element_by_name("Name")
    #     name.send_keys("name")
    #
    #     cardNumber = self.driver.find_element_by_name("Card Number")
    #     cardNumber.send_keys("8857467364736477")
    #
    #     expiryDate = self.driver.find_element_by_name("Expiry Date")
    #     expiryDate.send_keys("12/28")
    #
    #     securityCode = self.driver.find_element_by_name("Security Code")
    #     securityCode.send_keys("35544")
    #     securityCode.submit()
    #     time.sleep(1)
    #
    #     assert self.driver.find_element_by_name("message").text == "Security code was not in the correct format"

if __name__ == "__main__":
    unittest.main()