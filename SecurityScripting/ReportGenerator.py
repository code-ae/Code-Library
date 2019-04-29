# Use this to implement fail-safe waiting; Javascript Version
# WebDriverWait wait = new WebDriverWait (driver, 30);
# wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]"))).click();

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

# browser = webdriver.Chrome()
# url = 'url'
# browser.get(url)
# time.sleep(3)
# username = browser.find_element_by_id("username")
# password = browser.find_element_by_id("password")

# user = "user"
# passw = "pass"
# username.send_keys(user)
# password.send_keys(passw)
# login_button = browser.find_element_by_xpath('//*[@id="sign-in"]')
# login_button.click()
# time.sleep(5)

# month_button = browser.find_element_by_xpath('//*[@id="whats-happening"]/div[3]/div/button[3]')
# month_button.click()
# time.sleep(3)
# fe_blocked_emails = browser.find_element_by_xpath('//*[@id="whats-happening"]/div[2]/div[1]/div/div/table/tbody/tr/td[1]/div[1]/div[1]/div[2]/span')
# fe_blocked_emails_value = str(fe_blocked_emails.get_attribute("textContent"))
# print("Total Blocked Emails - " + fe_blocked_emails_value + "\n")

# fe_mal_url = browser.find_element_by_xpath('//*[@id="whats-happening"]/div[2]/div[1]/div/div/table/tbody/tr/td[2]/div[2]/div[1]/div[2]/span')
# fe_mal_attachment = browser.find_element_by_xpath('//*[@id="whats-happening"]/div[2]/div[1]/div/div/table/tbody/tr/td[2]/div[3]/div[1]/div[2]/span')

# fe_mal_url_val = fe_mal_url.get_attribute("textContent")
# fe_mal_attachment_val = fe_mal_attachment.get_attribute("textContent")

# browser = webdriver.Chrome()
# url = 'url'
# browser.get(url)
# time.sleep(3)

# username = browser.find_element_by_id("email")
# user = "user"
# username.send_keys(user)
# first_button = browser.find_element_by_xpath('//*[@id="loginForm"]/button')
# first_button.click()
# time.sleep(3)

# password = browser.find_element_by_id("loginPassword")
# passw = "pass"
# password.send_keys(passw)
# second_button = browser.find_element_by_xpath('//*[@id="resetPasswordSubmitButton"]')
# second_button.click()
# time.sleep(3)

# reports_button = browser.find_element_by_xpath('//*[@id="application-navigation"]/ul/li[4]/a')
# reports_button.click()

# landscape_button = browser.find_element_by_xpath('//*[@id="tab-bar"]/ul/li[2]/a/span/div')
# landscape_button.click()

# timerange_dropbox = browser.find_element_by_xpath('//*[@id="tab-pane-container"]/div/div/div[1]/div[1]/div/div/div/div/div')
# timerange_dropbox.click()
# time.sleep(8)
# last30days_option = browser.find_element_by_xpath('//*[@id="menu-day-range"]/div[2]/ul/li[5]')
# last30days_option.click()
# time.sleep(10)

# messages_blocked_30days = browser.find_element_by_xpath('//*[@id="tab-pane-container"]/div/div/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]')
# messages_blocked_30days_val = messages_blocked_30days.get_attribute("textContent")

# time.sleep(5)
# threats = browser.find_elements_by_class_name('slice-label')
# counter = 0
# chart_object = ""
# chart_list = browser.find_elements_by_class_name('header')

# for chart in chart_list:
	# if chart.get_attribute('textContent') is "Top 10 Campaigns":
		# chart_object = chart
# print("Chart Object  -  " + str(chart_object))


# chart_campaign1_name = browser.find_element_by_xpath('//*[@id="tab-pane-container"]/div/div/div[2]/div[2]/div[7]/div/div[2]/figure/svg/g/g[1]/g[1]/text')
# chart_campaign1_name = chart_campaign1_name.get_attribute('textContent')
# chart_campaign1_count = browser.find_element_by_xpath('//*[@id="tab-pane-container"]/div/div/div[2]/div[2]/div[7]/div/div[2]/figure/svg/g/g[3]/text')
# chart_campaign1_count = chart_campaign1_value.get_attribute('textContent')
# results_file.write('CHART VALUES\n' + chart_campaign1_count + " " + chart_campaign1_name )


# threat_value_list = []
# for i in range(0,21,2):
	# threat_value_list.append(str(threats[i].get_attribute('textContent')) + " " + str(threats[i+1].get_attribute('textContent')))

# for threat_value in threat_value_list:
	# if "message" in threat_value:
		# message_threat_val = (threat_value.split(" "))[0].replace(',', '')
		# print("Message Threats - " + str(message_threat_val))

	# if "attachment" in threat_value:
		# attachment_threat_val = (threat_value.split(" "))[0].replace(',', '')
		# print("Attachment Threats - " + str(attachment_threat_val))
		
	# if "hybrid" in threat_value:
		# hybrid_threat_val = (threat_value.split(" "))[0].replace(',', '')
		# print("Hybrid Threats - " + str(hybrid_threat_val))
	
	# if "URL" in threat_value:
		# url_threat_val = (threat_value.split(" "))[0].replace(',', '')
		# print("URL Threats - " + str(url_threat_val) )
		
	# if "spam" in threat_value:
		# spam_threat_val = (threat_value.split(" "))[0].replace(',', '')
		# print("Spam Threats - " + str(spam_threat_val))
	
	# if "phishing" in threat_value:
		# phish_threat_val = (threat_value.split(" "))[0].replace(',', '')
		# print("Phishing Threats - " + str(phish_threat_val))
	
	# if "malware" in threat_value:
		# malware_threat_val = (threat_value.split(" "))[0].replace(',', '')
		# print("Malware Threats - " + str(malware_threat_val))
	
	# if "impostor" in threat_value:
		# impostor_threat_val = (threat_value.split(" "))[0].replace(',', '')
		# print("Impostor Threats - " + str(impostor_threat_val))





# SecurityCenter Login
browser = webdriver.Chrome()
url = 'url'
browser.get(url)
time.sleep(6)
username = browser.find_element_by_id("username")
password = browser.find_element_by_id("password")

user = "user"
passw = "pass"
username.send_keys(user)
password.send_keys(passw)
submitButton = browser.find_element_by_xpath("/html/body/div[1]/div/div/div[1]/section/form/input[2]")
submitButton.click()
time.sleep(4)

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

# # Proofpoint Page
# browser = webdriver.Chrome()
# url = 'url
# browser.get(url)
# time.sleep(3)

# username = browser.find_element_by_id("user")
# password = browser.find_element_by_id("pass")

# user = "user"
# passw = "pass"
# username.send_keys(user)
# password.send_keys(passw)
# login_button = browser.find_element_by_id('login')
# login_button.click()
# time.sleep(20)


# report_b = browser.find_element_by_link_text('Logs and Reports')


# action = webdriver.common.action_chains.ActionChains(browser)
# action.move_to_element(report_b) # 5 down, 5 to the right
# action.click()
# action.perform()

# pp_report_button = browser.find_element_by_link_text('Logs and Reports')
# for element in pp_report_button:
	# print(element.get_attribute("textContent"))

# actions = ActionChains(browser)
# actions.move_to_element

# pp_report_button.click()
# time.sleep(3)
# report_view_button = browser.find_element_by_xpath('//*[@id="ITEMS-l_rv"]')
# report_view_button.click()
# time.sleep(3)

# dropdown = browser.find_element_by_xpath('//*[@id="period88"]')
# last30days_button = browser.find_element_by_xpath('//*[@id="period05"]/option[4]')
# time.sleep(4)

# accepted_msgcount = browser.find_element_by_xpath('/html/body/table/tbody/tr/td[1]/form/table[3]/tbody/tr[2]/td[2]/table[5]/tbody/tr[2]/td[2]/table[3]/tbody/tr/td/table/tbody/tr[2]/td[2]')
# accepted_msgcount = accepted_msg_count.get_attribute('textContent')

# blocked_emailfirewall_msgcount = browser.find_element_by_xpath('/html/body/table/tbody/tr/td[1]/form/table[3]/tbody/tr[2]/td[2]/table[5]/tbody/tr[2]/td[2]/table[3]/tbody/tr/td/table/tbody/tr[3]/td[2]')
# blocked_emailfirewall_msgcount = blocked_emailfirewall_msgcount.get_attribute('textContent')

# blocked_others_msgcount = browser.find_element_by_xpath('/html/body/table/tbody/tr/td[1]/form/table[3]/tbody/tr[2]/td[2]/table[5]/tbody/tr[2]/td[2]/table[3]/tbody/tr/td/table/tbody/tr[4]/td[2]')
# blocked_others_msgcount = blocked_others_msgcount.get_attribute('textContent')

# blocked_spam_msgcount = browser.find_element_by_xpath('/html/body/table/tbody/tr/td[1]/form/table[3]/tbody/tr[2]/td[2]/table[5]/tbody/tr[2]/td[2]/table[3]/tbody/tr/td/table/tbody/tr[5]/td[2]')
# blocked_spam_msgcount = blocked_spam_msgcount.get_attribute('textContent')

# blocked_pdr_msgcount = browser.find_element_by_xpath('/html/body/table/tbody/tr/td[1]/form/table[3]/tbody/tr[2]/td[2]/table[5]/tbody/tr[2]/td[2]/table[3]/tbody/tr/td/table/tbody/tr[6]/td[2]')
# blocked_pdr_msgcount = blocked_pdr_msgcount.get_attribute('/html/body/table/tbody/tr/td[1]/form/table[3]/tbody/tr[2]/td[2]/table[5]/tbody/tr[2]/td[2]/table[3]/tbody/tr/td/table/tbody/tr[6]/td[2]')

# blocked_antivirus_msgcount = browser.find_element_by_xpath('/html/body/table/tbody/tr/td[1]/form/table[3]/tbody/tr[2]/td[2]/table[5]/tbody/tr[2]/td[2]/table[3]/tbody/tr/td/table/tbody/tr[7]/td[2]')
# blocked_antivirus_msgcount = blocked_antivirus_msgcount.get_attribute('textContent')

# blocked_0hour_msgcount = browser.find_element_by_xpath('/html/body/table/tbody/tr/td[1]/form/table[3]/tbody/tr[2]/td[2]/table[5]/tbody/tr[2]/td[2]/table[3]/tbody/tr/td/table/tbody/tr[8]/td[2]')
# blocked_0hour_msgcount = blocked_0hour_msgcount.get_attribute('textContent')

# totalblocked_msgcount = browser.find_element_by_xpath('/html/body/table/tbody/tr/td[1]/form/table[3]/tbody/tr[2]/td[2]/table[5]/tbody/tr[2]/td[2]/table[3]/tbody/tr/td/table/tbody/tr[9]/td[2]')
# totalblocked_msgcount = totalblocked_msgcount.get_attribute('textContent')

# results_file.write("Global Message Summary")
# results_file.write("Accepted Messages - " + str(accepted_msgcount) + "\n")
# results_file.write("Blocked: Email Firewall - " + str(blocked_emailfirewall_msgcount) + "\n")
# results_file.write("Blocked: Others - " + str(blocked_others_msgcount) + "\n")
# results_file.write("Blocked: Spam - " + str(blocked_spam_msgcount) + "\n")
# results_file.write("Blocked: PDR - " + str(blocked_pdr_msgcount) + "\n")
# results_file.write("Blocked: Anti Virus - " + str(blocked_antivirus_msgcount) + "\n")
# results_file.write("Blocked: Zero Hour - " + str(blocked_0hour_msgcount) + "\n")
# results_file.write("Blocked: Zero Hour - " + str(totalblocked_msgcount) + "\n")


results_file.write("Critical Internal IP's - " + critical_internal_ip_val + "\n")
results_file.write("Total Internal IP's - " + total_internal_ip_val + "\n")
results_file.write("% of Internal Hosts w/ Critical Vulnerabilities - " + str(internal_percent_val) + "\n\n")
results_file.write("Critical External IP's - " + critical_external_ip_val + "\n")
results_file.write("Total External IP's - " + total_external_ip_val + "\n")
results_file.write("% of External Hosts w/ Critical Vulnerabilities - " + str(external_percent_val) + "\n")

results_file.write("# of Hosts w/ Critical Vulnerabilities Over 30 Days- " + str(over30days_critical_ip_val) + "\n")
results_file.write("Total # of Hosts - " + str(over30days_total_ip_val) + "\n")
results_file.write("% of Hosts w/ Critical Vulnerabilities Over 30 Days - " + str(over30days_percent_val) + "\n======================================================================\n\n")

# results_file.write("Threat Types By Message Volume - \n")
# results_file.write("Text Threats - " + message_threat_val + "\n")
# results_file.write("Hybrid Threats - " + str(hybrid_threat_val) + "\n")
# results_file.write("URL Threats - " + url_threat_val + "\n")
# results_file.write("Attachment Threats - " + attachment_threat_val + "\n")
# results_file.write("All Messages Blocked - " + str(messages_blocked_30days_val) + "\n\n")
# results_file.write("Threat Categories - \n")
# results_file.write("Spam Threats - " + spam_threat_val + "\n")
# results_file.write("Phishing Threats - " + phish_threat_val + "\n")
# results_file.write("Malware Threats - " + malware_threat_val + "\n")
# results_file.write("Imposter Threats - " + impostor_threat_val + "\n")
# all_threats_val = int(spam_threat_val) + int(phish_threat_val) + int(malware_threat_val) + int(impostor_threat_val)
# results_file.write("All Threats - " + str(all_threats_val) + "\n======================================================================\n")

# results_file.write("Total Blocked URL's - " + fe_mal_url_val + "\n")
# results_file.write("Total Blocked Attachments - " + fe_mal_attachment_val + "\n")

# results_file.write("======================================================================\n\n")
# results_file.write("Total Blocked Emails - " + fe_blocked_emails_value + "\n\n")

weeklyreport_file.write("Critical Internal IP's - " + critical_internal_ip_val + "\n")
weeklyreport_file.write("Total Internal IP's - " + total_internal_ip_val + "\n")
weeklyreport_file.write("% of Internal Hosts w/ Critical Vulnerabilities - " + str(internal_percent_val) + "\n\n")
weeklyreport_file.write("Critical External IP's - " + critical_external_ip_val + "\n")
weeklyreport_file.write("Total External IP's - " + total_external_ip_val + "\n")
weeklyreport_file.write("% of External Hosts w/ Critical Vulnerabilities - " + str(external_percent_val) + "\n\n")
weeklyreport_file.write("# of Hosts w/ Critical Vulnerabilities Over 30 Days - " + str(over30days_critical_ip_val) + "\n")
weeklyreport_file.write("Total # of Hosts - " + str(over30days_total_ip_val) + "\n")
weeklyreport_file.write("% of Hosts w/ Critical Vulnerabilities Over 30 Days - " + str(over30days_percent_val) + "\n")

results_file.close()
weeklyreport_file.close()

if outlook_flag is 1:
	f = open("weeklyreport.txt", "r")
	weekly_report_contents = str(f.read())
	print(weekly_report_contents)
	# Email Recipient, Subject, and Body established here
	subprocess.call(['outlook.exe', '/c' , 'ipm.note', '/m', '<email>&subject=<subject>&body=<body>'])




