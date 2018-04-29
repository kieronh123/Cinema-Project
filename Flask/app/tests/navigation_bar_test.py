import time
import unittest

from selenium import webdriver


class NavBarTest(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.get("http://localhost:5000")

    def testLogin(self):
        # check login link
        self.driver.find_element_by_name("login").click()
        time.sleep(1)
        assert self.driver.title == "Quail Cinema - Log in"

    def testRegister(self):
        # check login link
        self.driver.find_element_by_name("register").click()
        time.sleep(1)
        assert self.driver.title == "Quail Cinema - Log in"

    def testHome(self):
        # check login link
        self.driver.find_element_by_name("home").click()
        time.sleep(1)
        assert self.driver.title == "Quail Cinema"

    def tearDown(self):
        self.driver.close()

if __name__ == '__main__':
    unittest.main()

# # Create a new instance of the Firefox driver
# driver = webdriver.Firefox()
#
# #open home page
# driver.get("http://localhost:5000")
#
# #check login link
# driver.find_element_by_name("login").click()
# time.sleep(1)
# assert driver.title == "Quail Cinema - Log in"
# if driver.title == "Quail Cinema - Log in":
#     print('\033[92m Test passed : Log in page opened \x1b[0m')
# else:
#     print('\033[91m Test Failed : Log in page Could not be opened\x1b[0m')
#
# #check register link
# driver.find_element_by_name("register").click()
# time.sleep(1)
# # WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.NAME, "register")))
# if driver.title == "Quail Cinema - Register":
#     print('\033[92m Test passed : Log in page opened \x1b[0m')
# else:
#     print('\033[91m Test Failed : Log in page Could not be opened\x1b[0m')
#
# #check home link
# driver.find_element_by_name("home").click()
# # WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.NAME, "seatselect")))
# time.sleep(1)
# if driver.title == "Quail Cinema":
#     print('\033[92m Test passed : Log in page opened \x1b[0m')
# else:
#     print('\033[91m Test Failed : Log in page Could not be opened\x1b[0m')
