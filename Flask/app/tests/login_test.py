import time
import unittest

from selenium import webdriver
from selenium.webdriver.common.keys  import Keys


class loginAttempTest(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Firefox()
        cls.driver.get("http://localhost:5000")
        cls.driver.find_element_by_name("login").click()
        time.sleep(1)
        cls.driver.find_element_by_name("logout").click()
        time.sleep(1)

    def testCorrectAttempt(self):
        username = self.driver.find_element_by_name("Username")
        username.click()
        username.send_keys("alexander_hoare@homail.co.uk")
        password = self.driver.find_element_by_name("Password")
        password.send_keys("hunter2")
        password.submit()
        time.sleep(2)
        assert self.driver.title == "Quail Cinema"

    def testAIncorrectAttempt(self):
        username = self.driver.find_element_by_name("Username")
        username.click()
        username.send_keys("alexander_hoare@homail.co.uk")
        password = self.driver.find_element_by_name("Password")
        password.send_keys("hunter")
        password.submit()
        time.sleep(1)
        assert self.driver.find_element_by_name("message").text == "Incorrect Username/Password combination"

    @classmethod
    def tearDownClass(cls):
        cls.driver.close()

if __name__ == "__main__":
    unittest.main()

