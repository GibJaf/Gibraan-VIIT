from selenium import webdriver

driver = webdriver.Firefox()

driver.set_page_load_timeout(10)
driver.get("https://gmail.com")

emailID = driver.find_element_by_id("identifierId")
emailID.send_keys("gibjaf@gmail.com")

