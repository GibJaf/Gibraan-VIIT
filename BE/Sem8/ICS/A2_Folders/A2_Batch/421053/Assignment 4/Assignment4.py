from decimal import Decimal 
  
def gcd(a,b): 
    if b==0: 
        return a 
    else: 
        return gcd(b,a%b)

p = int(input('Enter the value of p = ')) 
q = int(input('Enter the value of q = ')) 
no = int(input('Enter the value of text = ')) 
n = p*q 

t = (p-1)*(q-1) 

#Encryption key
for e in range(2,t): 
    if gcd(e,t)== 1: 
        break
  
#Decryption key
for i in range(1,10): 
    x = 1 + i*t 
    if x % e == 0: 
        d = int(x/e) 
        break

cipher = Decimal(0) 
cipher =pow(no,e) 
cipher_text = cipher % n 
  
decrypted = Decimal(0) 
decrypted = pow(cipher_text,d) 
decrypted_text = decrypted % n 
  
print('n = '+str(n)+' e = '+str(e)+' t = '+str(t)+' d = '+str(d)+' cipher text = '+str(cipher_text)+' decrypted text = '+str(decrypted_text)) 
