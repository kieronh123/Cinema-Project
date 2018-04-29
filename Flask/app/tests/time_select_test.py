import unittest
import time

from selenium import webdriver
from selenium.webdriver.support.ui import Select

class TimeSelect(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.get("http://localhost:5000")

    def tearDown(self):
        self.driver.close()

    def testTimeSelect(self):
        self.driver.find_element_by_name("time").click()
        time.sleep(1)
        assert self.driver.title == "Quail Cinema - Select Seat"

if __name__ == "__main__":
    unittest.main()