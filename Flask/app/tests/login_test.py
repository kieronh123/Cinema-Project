import time
import unittest

from selenium import webdriver
from selenium.webdriver.common.keys  import Keys


class loginAttempTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()

    def testCorrectAttempt(self):
        self.driver.get("http://localhost:5000/login")
        username =  self.driver.find_element_by_name("Username")
        username.click()
        username.send_keys("alexander_hoare@homail.co.uk")
        password = self.driver.find_element_by_name("Password")
        password.send_keys("hunter2")
        password.submit()
        time.sleep(1)
        assert self.driver.title == "Quail Cinema"

    def testIncorrectAttempt(self):
        self.driver.get("http://localhost:5000/login")
        username = self.driver.find_element_by_name("Username")
        username.click()
        username.send_keys("alexander_hoare@homail.co.uk")
        password = self.driver.find_element_by_name("Password")
        password.send_keys("hunter")
        password.submit()
        time.sleep(1)
        assert self.driver.find_element_by_name("message").text == "Incorrect Username/Password combination"

    def tearDown(self):
        self.driver.close()

if __name__ == "__main__":
    unittest.main()

