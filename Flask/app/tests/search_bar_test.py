import time
import unittest

from selenium import webdriver

class(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.get("http://localhost:5000")

    def testSearchBar(self):
        search = self.driver.find_element_by_name("search")
        search.send_keys("Ba")
        assert self.driver.find_element_by_name("Batman Begins") != None