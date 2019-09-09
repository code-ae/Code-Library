def GetBook(name): # @parameter: name - teamname to extract alerts of 		
		data_to_add = [][] # return value, rows to be written
		add_index=0 # iterating index
		for row in reader: # for every row in csv
			
			if row[6] == name # if requested team name is found
				data_to_add[add_index] = row # add current row to list
				add_index += 1 # move to next empty slot in list
				
			if row[6] != name 
				return data_to_add

def GetTeamNames(file): #@parameter: file, Evident report to divide
	unique_list= [] # list of unique team names
	for row in reader: 
		name = row[6] # save team_name for parsing
		if name not in unique_list: # if name in current row is not saved in namelist
			unique_list.append(name) # save it
	
	return unique_list
	
import csv		 # @return: data_to_add - list of rows to write to book
	with open(sys.argv[1], 'r') as file:
		reader = csv.reader(file)
		reader.fieldnames = ["Event Count", "Event First Seen",	"Current Status	Resource", "Evident Alert",	"Email", "Signature Identifier", "External Account Name", "External Account ID", "Region", "Severity", "Remediation Time", "Signature Resolution", "TotalEvidentCount"]
		header = reader.fieldnames
		
		unique_names = GetTeamNames(sys.argv[1])
		
	# now we need to run a name through GetBook, take the returned rows
    # and write it to a newly created book	
		u_index=0 # iterates through different teamnames to grab from book
		for i in range(0,unique_list.len()) # for every teamname in report
			
			data_to_write = GetBook(unique_names[u_index]) # get rows to write
			u_index += 1
			with open(unique_names[unique_index], 'w') as file2: # create new file
				writer= csv.writer(file2, delimiter=',')
				writer.writerow(header)							# write the header	
				writer.writerows(data_to_write)					# and then the rows

