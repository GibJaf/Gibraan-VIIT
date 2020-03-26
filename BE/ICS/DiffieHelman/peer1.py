import socket
import random

client = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
HOST_IP = '127.0.0.1'
PORT = 1234

p = 23
g = 5

private = random.randint(1,p)
public_client = (g**private)%p

client.connect((HOST_IP,PORT))
msg = client.recv(2048)
print(msg.decode())

print('Modulus : ',p,' sent to server')
print('Base : ',g,' sent to server')

print('Private : ',private)
print('Public Client Key : ',public_client,' sent to server')

msg = ','.join([str(p),str(g),str(public_client)])
client.send(msg.encode())

public_server = int(client.recv(1024).decode())
print('Public Server Key : ',public_server,'received from server')

key = (public_server**private)%p
print('Key : ',key)
client.close()
