import webbrowser
input_val = input("Enter the IOC to lookup: ")

# md5, sha256, sha1
if len(input_val) in [32, 64, 40]: 
	url = "https://www.virustotal.com/search/?query=" + input_val
	webbrowser.open(url,new=2, autoraise=True)
	
# domain name
elif "www." in input_val or "http" in input_val or ".com" in input_val or ".net" in input_val or ".org" in input_val or ".ru" in input_val or ".cn" in input_val:
	url = "https://urlquery.net/search?q=" + input_val
	webbrowser.open(url,new=2, autoraise=True)

# ip address
else:
	url = "https://www.talosintelligence.com/reputation_center/lookup?search=" + input_val
	webbrowser.open(url, new=1, autoraise=True)

	url = "https://www.virustotal.com/#/ip-address/" + input_val
	webbrowser.open(url,new=2, autoraise=True)
	
	url = "https://www.shodan.io/host/" + input_val
	webbrowser.open(url,new=2, autoraise=True)

	url = "https://otx.alienvault.com/browse/indicators?sort=indicator&q=" + input_val
	webbrowser.open(url,new=2, autoraise=True)

	url = "https://community.riskiq.com/search/" + input_val
	webbrowser.open(url,new=2, autoraise=True)

print("Reports launched!")
sleep(2)
exit()
