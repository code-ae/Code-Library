#from bs4 import BeautifulSoup

# TroubleShooting

# IF PROGRAM CRASHES WITH BELOW ERROR 
# selenium.common.exceptions.NoSuchElementException: Message: no such element: Unable to locate element: <element name here>
# This means the program tried to look for the element but it wasn't there
# Ensure that the page loaded correctly, fast enough, usually sorts itself after a retry or two

# IF PROGRAM RETURNS INACCURATE DATA / DATA FROM WRONG CELL
# This can happen as a result of changes made to either relevant reports or the UI of the page in general
# You will need to find the call to 'find_element_by_xpath/id()' that returned the false data and follow this procedure
# 		Find the element you would like to be retrieved
#		Right click and select "Inspect"
#		Go to the the highlighted HTML that appears on the right and right click that block
#		If looking for XPath Value:
#			Hover over the "Copy" option and select "Copy by XPath"
#			Copy the desired cells XPath value as a parameter to find_element_by_xpath(<XPath goes here>)
#		If looking for cells ID:
#			Take the value after "id=" from the highlighted HTML block and copy it as parameter for find_element_by_id(<ID goes here>)

import urllib.request
import requests
import time

#added 4/17
import urllib.request
from selenium import webdriver
import time
import pandas as pd
from decimal import Decimal
import subprocess


print("Sending Weekly Vulnerability Report? (Y/N)")
response = input()
response = response.lower()

outlook_flag = 0
if response == str("y"):
	outlook_flag = 1

weeklyreport_file = open("weeklyreport.txt", "w+" )
results_file = open("results.txt", "w+" )

# SecurityCenter Login
browser = webdriver.Chrome()
url = url
browser.get(url)
time.sleep(7)
username = browser.find_element_by_id("username")
password = browser.find_element_by_id("password")

# user = user
# passw = pass
username.send_keys(user)
password.send_keys(passw)

submitButton = browser.find_element_by_xpath("/html/body/div[1]/div/div/div[1]/section/form/input[2]")

submitButton.click()
time.sleep(7)

critical_high_internal_ips = browser.find_element_by_xpath('//*[@id="matrix-cell-465-23"]')
critical_high_ips_with_total = critical_high_internal_ips.get_attribute("data-original-title")

critical_high_ips_with_total = critical_high_ips_with_total.split('/')
critical_high_ips_with_total[1] = critical_high_ips_with_total[1].strip(" ")

critical_and_high_ip_vals = critical_high_ips_with_total[0]
total_ips = critical_high_ips_with_total[1]

critical_high_percentage = int(critical_high_ips_with_total[0]) / int(critical_high_ips_with_total[1])
critical_high_percentage = "{:.2%}".format(critical_high_percentage)
print(critical_high_percentage)

critical_internal_ips = browser.find_element_by_xpath('//*[@id="matrix-cell-465-3"]')
total_internal_ips = browser.find_element_by_xpath('//*[@id="matrix-cell-465-13"]')
critical_internal_ip_val = critical_internal_ips.get_attribute("title")
total_internal_ip_val = total_internal_ips.get_attribute("title")
# internal percent val calculation
internal_percent_val = Decimal(int(critical_internal_ip_val)/int(total_internal_ip_val))
internal_percent_val = "{:.2%}".format(internal_percent_val)

critical_external_ips = browser.find_element_by_xpath('//*[@id="matrix-cell-465-4"]')
total_external_ips = browser.find_element_by_xpath('//*[@id="matrix-cell-465-14"]')
critical_external_ip_val = critical_external_ips.get_attribute("title")
total_external_ip_val = total_external_ips.get_attribute("title")
# external percent val calculation
external_percent_val = Decimal(int(critical_external_ip_val)/int(total_external_ip_val))
external_percent_val = "{:.2%}".format(external_percent_val)

over30days_critical_ips = browser.find_element_by_xpath('//*[@id="matrix-cell-464-3"]')
over30days_total_ips = browser.find_element_by_xpath('//*[@id="matrix-cell-464-7"]')
over30days_critical_ip_val = over30days_critical_ips.get_attribute("title")
over30days_total_ip_val = over30days_total_ips.get_attribute("title")
# over30days percent val calculation
over30days_percent_val = Decimal(int(over30days_critical_ip_val)/int(over30days_total_ip_val))
over30days_percent_val = "{:.2%}".format(over30days_percent_val)

weeklyreport_file.write("Security Metrics" + "\n\n")

if outlook_flag is 1:
	f = open("weeklyreport.txt", "r")
	weekly_report_contents = str(f.read())
	print(weekly_report_contents)
	# Email Recipient, Subject, and Body established here
	subprocess.call(['outlook.exe', '/c' , 'ipm.note', '/m', '<email_addresses_tosend>&subject=<subject>&body=<body>'])

else:
	# FireEye EX Login
	browser = webdriver.Chrome()
	url = url
	browser.get(url)
	time.sleep(7)
	username = browser.find_element_by_id("username")
	password = browser.find_element_by_id("password")

	user = user
	passw = passw
	username.send_keys(user)
	password.send_keys(passw)
	login_button = browser.find_element_by_xpath('//*[@id="sign-in"]')
	login_button.click()
	time.sleep(7)

	month_button = browser.find_element_by_xpath('//*[@id="whats-happening"]/div[3]/div/button[3]')
	month_button.click()
	time.sleep(7)
	fe_blocked_emails = browser.find_element_by_xpath('//*[@id="whats-happening"]/div[2]/div[1]/div/div/table/tbody/tr/td[1]/div[2]/div[1]/div[2]/span')
	fe_blocked_emails_value = str(fe_blocked_emails.get_attribute("textContent"))
	print("FireEye - Total Blocked Emails - " + fe_blocked_emails_value + "\n")

	fe_mal_url = browser.find_element_by_xpath('//*[@id="whats-happening"]/div[2]/div[1]/div/div/table/tbody/tr/td[2]/div[3]/div[1]/div[2]/span')
	fe_mal_attachment = browser.find_element_by_xpath('//*[@id="whats-happening"]/div[2]/div[1]/div/div/table/tbody/tr/td[2]/div[4]/div[1]/div[2]/span')

	fe_mal_url_val = fe_mal_url.get_attribute("textContent")
	fe_mal_attachment_val = fe_mal_attachment.get_attribute("textContent")

	# Proofpoint TAP Login
	browser = webdriver.Chrome()
	url = url
	browser.get(url)
	time.sleep(7)

	username = browser.find_element_by_id("email")
	user = user
	username.send_keys(user)
	first_button = browser.find_element_by_xpath('//*[@id="loginForm"]/button')
	first_button.click()
	time.sleep(8)

	password = browser.find_element_by_id("loginPassword")
	passw = passw
	password.send_keys(passw)
	second_button = browser.find_element_by_xpath('//*[@id="resetPasswordSubmitButton"]')
	second_button.click()
	time.sleep(8)

	reports_button = browser.find_element_by_xpath('//*[@id="application-navigation"]/ul/li[4]/a')
	reports_button.click()
	time.sleep(8)
	landscape_button = browser.find_element_by_xpath('//*[@id="tab-bar"]/ul/li[2]/a/span/div')
	landscape_button.click()
	time.sleep(8)
	timerange_dropbox = browser.find_element_by_xpath('//*[@id="tab-pane-container"]/div/div/div[1]/div[1]/div/div/div/div/div')
	timerange_dropbox.click()
	time.sleep(8)
	last30days_option = browser.find_element_by_xpath('//*[@id="menu-day-range"]/div[2]/ul/li[5]')
	last30days_option.click()
	time.sleep(10)

	messages_blocked_30days = browser.find_element_by_xpath('//*[@id="tab-pane-container"]/div/div/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]')
	messages_blocked_30days_val = messages_blocked_30days.get_attribute("textContent")

	time.sleep(8)
	threats = browser.find_elements_by_class_name('slice-label')
	# counter = 0
	# chart_object = ""
	# chart_list = browser.find_elements_by_class_name('header')

	# for chart in chart_list:
		# if chart.get_attribute('textContent') is "Top 10 Campaigns":
			# chart_object = chart
	# print("Chart Object  -  " + str(chart_object))

	# time.sleep(5)
	# chart_campaign1_name = browser.find_element_by_xpath('//*[@id="tab-pane-container"]/div/div/div[2]/div[2]/div[7]/div/div[2]/figure/svg/g/g[1]/g[1]/text')
	# chart_campaign1_name = chart_campaign1_name.get_attribute('textContent')
	# chart_campaign1_count = browser.find_element_by_xpath('//*[@id="tab-pane-container"]/div/div/div[2]/div[2]/div[7]/div/div[2]/figure/svg/g/g[3]/text')
	# chart_campaign1_count = chart_campaign1_value.get_attribute('textContent')
	# results_file.write('CHART VALUES\n' + chart_campaign1_count + " " + chart_campaign1_name )


	threat_value_list = []
	for i in range(0,21,2):
		threat_value_list.append(str(threats[i].get_attribute('textContent')) + " " + str(threats[i+1].get_attribute('textContent')))

	for threat_value in threat_value_list:
		if "message" in threat_value:
			message_threat_val = (threat_value.split(" "))[0].replace(',', '')
			print("Message Threats - " + str(message_threat_val))

		if "attachment" in threat_value:
			attachment_threat_val = (threat_value.split(" "))[0].replace(',', '')
			print("Attachment Threats - " + str(attachment_threat_val))
			
		if "hybrid" in threat_value:
			hybrid_threat_val = (threat_value.split(" "))[0].replace(',', '')
			print("Hybrid Threats - " + str(hybrid_threat_val))
		
		if "URL" in threat_value:
			url_threat_val = (threat_value.split(" "))[0].replace(',', '')
			print("URL Threats - " + str(url_threat_val) )
			
		if "spam" in threat_value:
			spam_threat_val = (threat_value.split(" "))[0].replace(',', '')
			print("Spam Threats - " + str(spam_threat_val))
		
		if "phishing" in threat_value:
			phish_threat_val = (threat_value.split(" "))[0].replace(',', '')
			print("Phishing Threats - " + str(phish_threat_val))
		
		if "malware" in threat_value:
			malware_threat_val = (threat_value.split(" "))[0].replace(',', '')
			print("Malware Threats - " + str(malware_threat_val))
		
		if "impostor" in threat_value:
			impostor_threat_val = (threat_value.split(" "))[0].replace(',', '')
			print("Impostor Threats - " + str(impostor_threat_val))


	

	results_file.write("Everything")



results_file.close()
weeklyreport_file.close()






