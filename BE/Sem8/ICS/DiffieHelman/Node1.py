"""
SERVER
1) Establish connection
2) Select q randomly from a list (not generated randomly from random.randint() cuz some numbers don't have a primitive root)
3) Compute primitive root alpha
4) Send (q,alpha)
5) Generate Xa(Private key of server) randomly and < q
6) Calculate Ya(Public key of server)
7) Get Yb(Public key of client)
8) Print common key for encryption generated acc. formula => Ka = Yb**Xa mod q
"""

"""
Troubleshooting tips
1) If there is any error , wait for a minute and try again
"""

import logging, random, socket, sys

class DiffieHellmanServer:
    def __init__(self,server_ip,server_port):
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        try:
            self.sock.bind((server_ip,server_port))
            print("Server started @ ",server_ip,":",server_port)
            self.sock.listen()
            self.conn, addr = self.sock.accept()
            print("Connected to  ",addr)
        except OSError:
            print("ERROR => ",sys.exc_info())
            self.CloseConnection()
        except TypeError:
            print("ERROR => ",sys.exc_info())
            self.CloseConnection()
            

    def QandAlpha(self):
        self.q = random.choice([11,23])
        self.alpha = self.CalculatePrimitiveRoot(self.q)
        msg = ','.join((str(self.q),str(self.alpha)))
        self.conn.sendall(msg.encode())

    def CalculatePrimitiveRoot(self,q):
        primitive_root_options = set()
        print("q selected => ",q)
        for i in range(1,q):
            mods = set()
            for j in range(1,q):
                mods.add(i**j%q)
                #print(i," op ",j," = ",i**j%q)
            #print(i," ",mods)
            #print()
            if len(mods) == q-1: #check if all q-1 numbers are there
                primitive_root_options.add(i)
        primitive_root_options = sorted(primitive_root_options)    
        #print("All possible primitive roots => ",primitive_root_options)
        return primitive_root_options[0]

    def GeneratePrivateKey(self):
        self.Xa = random.randint(1,self.q-1)
        print("Generated Server Private Key => ",self.Xa)

    def CalculatePublicKey(self):
        self.Ya = self.alpha**self.Xa % self.q
        print("Calculated Server Public Key => ",self.Ya) 

    def SendServerPublicKey(self):
        self.conn.sendall(str(self.Ya).encode())

    def ReceiveClientPublicKey(self):
        self.Yb = int(self.conn.recv(1024).decode('utf-8'))
        print("Client Public Key received => ",self.Yb)

    def CalculateCommonEncryptionKey(self):
        CommonKey = self.Yb ** self.Xa % self.q
        print("Calculated Common Encryption Key => ",CommonKey)

    def CloseConnection(self):
        self.sock.close()
        print("Connection terminated")

    
if __name__ == '__main__':
    server = DiffieHellmanServer('127.0.0.1',1234)
    server.QandAlpha()
    server.GeneratePrivateKey()
    server.CalculatePublicKey()
    server.SendServerPublicKey()
    server.ReceiveClientPublicKey()
    server.CalculateCommonEncryptionKey()
    server.CloseConnection()
    


""" IMPROVEMENTS
"""