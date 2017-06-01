# UDP Client
# Runs on port 5433
import socket
import sys

# Create a UDP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

server_address = ('localhost', 5432)
message = raw_input("Enter ticket name: ")

sent = sock.sendto(message, server_address)
data, server = sock.recvfrom(1000)

print data

sock.close()
