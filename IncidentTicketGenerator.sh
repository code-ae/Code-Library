#!/bin/bash

DATE=`/bin/date +date_%d-%m-%y_time_%H-%M-%S`

tikdir="tickets"

ip_a="n/a"
mac_u="n/a"
usr="n/a"
emp="n/a"

####################
# OPTION HANDLING#
####################

while getopts ":i:hm:u:n:" OPTION; do
	case $OPTION in
	
	h )
		echo "###############################"
		echo "TICKET GENERATOR, LOGGER, AND NOTIFICATION SYSTEM## "
		echo ""
		echo "Usage: ./IncidentTicketGen.sh [OPTIONS]"
		echo "Ticket name and description inputted at runtime"
		echo " Options:    " 
		echo " -i [IP_ADDRESS]" 
		echo " -m [MACHINE]"
		echo " -u [NAME_USER]"
		echo " -n [FULL_NAME]"
		echo "###############################"
  		exit 1
		;;
	i ) 
		# echo "IP ADDRESS: $OPTARG"
		 ip_a=$OPTARG
		;;
	m )
		# echo "Machine: $OPTARG"
		mac_u=$OPTARG
		;;
	u )
		# echo "USERNAME : $OPTARG"
		 usr=$OPTARG
		;;
	n)
		# echo "EMPLOYEE NAME: $OPTARG"
		emp=$OPTARG
		;;	
	* )
		echo "Incorrect Options, use -h option for help menu"
		exit 1
		;;
	esac
done

read -p "Name your ticket: " tikname
printf  "#######################\n" >> "$tikdir/$tikname.txt"
printf "SECURITY INCIDENT\n" >> "$tikdir/$tikname.txt"
printf "$DATE \n" >> "$tikdir/$tikname.txt"
printf "######################\n" >> "$tikdir/$tikname.txt"
echo >> "$tikdir/$tikname.txt"

 #################
 # Program Output##
 #################
 
read -p "Describe the security issue..... :" desc

if [ "$emp" !=  "n/a" ]; then
	printf "EMPLOYEE NAME: $emp\n" >> "$tikdir/$tikname.txt"
fi
if [ "$ip_a" !=  "n/a" ]; then
	printf "IP ADDRESS: $ip_a\n" >> "$tikdir/$tikname.txt"
fi
if [ "$mac_u" !=  "n/a" ]; then
	printf "MACHINE : $mac_u\n" >> "$tikdir/$tikname.txt"
fi
if [ "$usr" !=  "n/a" ]; then
	printf "USERNAME : $usr\n" >> "$tikdir/$tikname.txt"
fi
printf "\nDescription: $desc\n \n
Please perform scans with \n 
- Antivirus Scanner 
- MalwareBytes Anti-Malware
- MalwareBytes AdwCleaner ( http://www.majorgeeks.com/files/details/adwcleaner.html.html )

and return log files when complete.

Note: Adwcleaner will restart computer upon completion. Please ensure no unsaved work is present on workstation.

Thank you.\n" >> "$tikdir/$tikname.txt"

printf "\nSecurity Incident - IP Address: $ip_a - Machine Name: $mac_u" >> "$tikdir/$tikname.txt"

# ########################       
# TICKET ASSEMBLED HERE
# ########################

echo "Ticket generated to $tikdir/$tikname.txt"


#########################
# Logging Occurs Here
#########################

echo '##########' >> tiklog.txt
printf "$DATE \n" >> tiklog.txt
if [ "$emp" !=  "n/a" ]; then
	printf "EMPLOYEE NAME: $emp\n" >> tiklog.txt
fi
if [ "$ip_a" !=  "n/a" ]; then
	printf "IP ADDRESS: $ip_a\n" >> tiklog.txt
fi
if [ "$mac_u" !=  "n/a" ]; then
	printf "MACHINE : $mac_u\n" >> tiklog.txt
fi
if [ "$usr" !=  "n/a" ]; then
	printf "USERNAME : $usr\n" >> tiklog.txt
fi
echo '##########' >> tiklog.txt
echo "Ticket logged to tiklog.txt"

##################################
# Email notification sent to Security Team
##################################

mailx -s "Security Incident: IP - $ip_a --- Machine: $mac_u" + email_address < "$tikdir/$tikname.txt" 
echo "Email sent to Information Security Team"
