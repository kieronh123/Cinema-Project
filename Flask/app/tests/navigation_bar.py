import time

from selenium import webdriver
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC

# Create a new instance of the Firefox driver
driver = webdriver.Firefox()

# go to the google home page
driver.get("http://localhost:5000")
loginLink = driver.find_element_by_name("login").click()
time.sleep(1)
if driver.title == "Quail Cinema - Log in":
    print('\033[92m Test passed : Log in page opened \x1b[0m')
else:
    print('\033[91m Test Failed : Log in page Could not be opened\x1b[0m')


loginLink = driver.find_element_by_name("register").click()
time.sleep(1)
# WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.NAME, "register")))
if driver.title == "Quail Cinema - Register":
    print('\033[92m Test passed : Log in page opened \x1b[0m')
else:
    print('\033[91m Test Failed : Log in page Could not be opened\x1b[0m')


loginLink = driver.find_element_by_name("home").click()
# WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.NAME, "seatselect")))
time.sleep(1)
if driver.title == "Quail Cinema":
    print('\033[92m Test passed : Log in page opened \x1b[0m')
else:
    print('\033[91m Test Failed : Log in page Could not be opened\x1b[0m')
