import time
import unittest

from selenium import webdriver

class RegistrationAttempt(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.get("http://localhost:5000/register")

    def tearDown(self):
        self.driver.close()

    def testCorrectAttempt(self):
        username = self.driver.find_element_by_name("Username")
        username.send_keys("filmcritic101@gmail.co.uk")
        password = self.driver.find_element_by_name("Password")
        password.send_keys("ilovemovies")
        passwordConfirm = self.driver.find_element_by_name("Confirm Password")
        passwordConfirm.send_keys("ilovemovies")
        passwordConfirm.submit()
        time.sleep(1)
        print(self.driver.find_element_by_name("message").text)

        assert self.driver.find_element_by_name("message").text == "Registration Successful"



    def testNoUsernameAttempt(self):
        password = self.driver.find_element_by_name("Password")
        password.send_keys("ilovemovies")
        passwordConfirm = self.driver.find_element_by_name("Confirm Password")
        passwordConfirm.send_keys("ilovemovies")
        passwordConfirm.submit()
        time.sleep(1)
        print(self.driver.find_element_by_name("message").text)

        assert self.driver.find_element_by_name("message").text == "No username entered"


    def testDifferentPasswordAttempt(self):
        username = self.driver.find_element_by_name("Username")
        username.send_keys("filmcritic101@gmail.co.uk")
        password = self.driver.find_element_by_name("Password")
        password.send_keys("ilovemovies")
        passwordConfirm = self.driver.find_element_by_name("Confirm Password")
        passwordConfirm.send_keys("ilovemovie")
        passwordConfirm.submit()
        time.sleep(1)
        print(self.driver.find_element_by_name("message").text)

        assert self.driver.find_element_by_name("message").text == "Passwords do not match"




if __name__ == "__main__":
    unittest.main()