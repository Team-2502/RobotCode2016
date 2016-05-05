import socket

UDP_IP = "10.0.0.35"
UDP_PORT = 21012
MESSAGE = "hello world"

print len(MESSAGE)
print "UDP target IP:", UDP_IP
print "UDP target port:", UDP_PORT
print "message:", MESSAGE

sock = socket.socket(socket.AF_INET, # Internet
socket.SOCK_DGRAM) # UDP

def sendData(keys, values) :
    if values[0] == False :
        return
    sendString = ""
    for i in range (len(keys)) :
        sendString += str(keys[i]) + ":" + str(values[i]) + ","
    
    sock.sendto(sendString, (UDP_IP, UDP_PORT))