import time
import unittest

from selenium import webdriver

class SearchBarTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.get("http://localhost:5000")

    def tearDown(self):
        self.driver.close()

    def testSearchBar(self):
        search = self.driver.find_element_by_name("search")
        search.send_keys("Ba")
        assert self.driver.find_element_by_id("Batman Begins") != None

if __name__ == "__main__":
    unittest.main()