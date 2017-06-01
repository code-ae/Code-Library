# UDP SERVER
import socket
import sys

# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
# Because it is a UDP socket, we do not need to wait for a connection

# Bind the socket to the port
server_address = ('localhost', 5432)

sock.bind(server_address) # Bind address to the socket.

data, address = sock.recvfrom(1024) # Address of sender, and data in var's


if data: # If client sent data
	data = data + "sent to Information Security Team" 		# add string to end
	sock.sendto(data, address)		     # and send it back to the same address
