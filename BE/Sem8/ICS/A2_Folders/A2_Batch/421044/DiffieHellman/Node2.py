"""
CLIENT
1) Establish connection
2) Receive (q,alpha)
3) Generate Xb(Private key of client) randomly and < q
4) Calculate Yb(Public key of client)
5) Get Ya(Public key of server)
    6) Print common key for encryption generated acc. formula => Kb = Ya**Xb mod q
    """
import logging, random, socket, sys

class DiffieHellmanClient:
    def __init__(self,server_ip,server_port):
        ''' code to establish connectiion
            and configure logging '''
        try:
            self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.sock.connect((server_ip,server_port))
            logging.basicConfig(level=logging.DEBUG)
        except:
            print(sys.exc_info())
            self.sock.close()
        
    def ReceiveQandAlpha(self):
        data = self.sock.recv(1024).decode('utf-8')
        print("Q and Alpha received => ",data)
        temp_q , temp_alpha = data.split(',')
        self.q = int(temp_q)
        self.alpha = int(temp_alpha)

    def GeneratePrivateKey(self):
        self.Xb = random.randint(1,self.q-1)
        print("Generated Client Private Key => ",self.Xb)

    def CalculatePublicKey(self):
        self.Yb = self.alpha**self.Xb % self.q
        print("Calculated Client Public Key => ",self.Yb)

    def ReceiveServerPublicKey(self):
        self.Ya = int(self.sock.recv(1024).decode('utf-8'))
        print("Server Public Key received => ",self.Ya)

    def SendClientPublicKey(self):
        self.sock.sendall(str(self.Yb).encode())

    def CalculateCommonEncryptionKey(self):
        CommonKey = self.Ya ** self.Xb % self.q
        print("Calculated Common Encryption Key => ",CommonKey)
        
        
    def CloseConnection(self):
        self.sock.close()
        print("Connection terminated")



if __name__ == '__main__':
    client = DiffieHellmanClient('127.0.0.1',1234)
    client.ReceiveQandAlpha()
    client.GeneratePrivateKey()
    client.CalculatePublicKey()
    client.ReceiveServerPublicKey()
    client.SendClientPublicKey()
    client.CalculateCommonEncryptionKey()
    client.CloseConnection()

"""sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
HOST = '127.0.0.1'
PORT = 1234
sock.connect((HOST,PORT))
data = sock.recv(1024)
print("Data received => ",data)
sock.close()"""