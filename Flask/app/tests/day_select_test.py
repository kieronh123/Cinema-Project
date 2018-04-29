import unittest
import time

from selenium import webdriver

class DaySelectTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.get("http://localhost:5000")

    def tearDown(self):
        self.driver.close()

    def testSelectDay(self):
        self.driver.find_element_by_id("Wednesday").click()
        time.sleep(1)
        assert self.driver.find_element_by_id("Wednesday") != None


if __name__ == "__main__":
    unittest.main()

