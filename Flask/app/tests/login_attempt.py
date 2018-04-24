import time

from selenium import webdriver

# open login page
driver = webdriver.Firefox()
driver.get('localhost:5000/login')

driver.find_element_by_Name("Username").

