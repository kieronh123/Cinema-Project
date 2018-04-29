# coding=utf-8
import unittest
import time

from selenium import webdriver
from selenium.webdriver.support.ui import Select


class VipTicketTypeTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.get("http://localhost:5000/seatselect/1")

    def tearDown(self):
        self.driver.close()

    def testSenior(self):
        self.driver.find_element_by_name("(3, 1)").click()
        ticketType = Select(self.driver.find_element_by_name("selectTicket"))
        ticketType.select_by_value("Senior")

        self.driver.find_element_by_name("Confirm").click()
        time.sleep(1)

        assert self.driver.find_element_by_id("Ticket Type").text == "Ticket Type: Senior"
        assert self.driver.find_element_by_id("Price").text == u"Total: £6"

    def testChild(self):
        self.driver.find_element_by_name("(3, 1)").click()
        ticketType = Select(self.driver.find_element_by_name("selectTicket"))
        ticketType.select_by_value("Child")

        self.driver.find_element_by_name("Confirm").click()
        time.sleep(1)

        assert self.driver.find_element_by_id("Ticket Type").text == "Ticket Type: Child"
        assert self.driver.find_element_by_id("Price").text == u"Total: £6"

    def testAdult(self):
        self.driver.find_element_by_name("(3, 1)").click()
        ticketType = Select(self.driver.find_element_by_name("selectTicket"))
        ticketType.select_by_value("Adult")

        self.driver.find_element_by_name("Confirm").click()
        time.sleep(1)

        assert self.driver.find_element_by_id("Ticket Type").text == "Ticket Type: Adult"
        assert self.driver.find_element_by_id("Price").text == u"Total: £12"

if __name__ == "__main__":
    unittest.main()