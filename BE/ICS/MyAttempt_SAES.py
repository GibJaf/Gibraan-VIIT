#!/bin/bash
"""
eg:
Input  => 0110 1111 0110 1011
Key    => 1010 0111 0011 1011
Cipher => 0000 0111 0011 1000

Problem => Expansion step of Key from 16 bits to 24 bits probably
"""


class AES:
    def __init__(self):
        self.SBox = {
            "0000":"1001", # 0  => 9
            "0001":"0100", # 1  => 4
            "0010":"1010", # 2  => 10
            "0011":"1011", # 3  => 11
            "0100":"1101", # 4  => 13
            "0101":"0001", # 5  => 1
            "0110":"1000", # 6  => 8
            "0111":"0101", # 7  => 5
            "1000":"0110", # 8  => 6
            "1001":"0010", # 9  => 2
            "1010":"0000", # 10 => 0
            "1011":"0011", # 11 => 3
            "1100":"1100", # 12 => 12
            "1101":"1110", # 13 => 14
            "1110":"1111", # 14 => 15
            "1111":"0111", # 15 => 7
        }

    def encrypt(self,plaintext,key):
        self.plaintext = plaintext
        self.key = key
        self.KeyExpansion()
        
        #Add Round Key0
        state = self.XOR(self.plaintext,self.Key0)
        print(state)


    def KeyExpansion(self):
        self.w0 , self.w1 = self.key[0:8],self.key[8:16]
        self.w2 = self.XOR(self.w0,"10000000",self.SBoxSub(self.RotateNibble(self.w1)))
        self.w3 = self.XOR(self.w2,self.w1)
        self.w4 = self.XOR(self.w2, "00110000" , self.SBoxSub("10000010"))
        self.w5 = self.XOR(self.w4, self.w3)
        
        self.Key0 = self.w0 + self.w1
        self.Key1 = self.w2 + self.w3
        self.Key2 = self.w4 + self.w5

        return None


    def XOR(*args):
        """ do xor on any number of arguments """
        # datatype of args is tuple
        # length of args is 1 more than the number of arguments specified cuz the first argument is the object "self". Can be seen using args[0]
        # ensure length of all args is same using try catch
        term_length = len(args[1])
        result = ""
        for i in range(term_length):
            temp = int(args[1][i])
            for j in range(2,len(args)):
                temp ^= int(args[j][i])
            result += str(temp)
        return result

    def RotateNibble(self,word):
        ''' rotate the nibbles ie: swap the nibbles '''
        return word[4:8]+word[0:4]

    def SBoxSub(self,word):
        return self.SBox[word[0:4]] + self.SBox[word[4:8]]





if __name__ == "__main__":
    plaintext = "1101011100101000" #16 character long string of 0 and 1s
    key = "0100101011110101" #16 character long string of 0 and 1s
    aes = AES()
    cipher = aes.encrypt(plaintext,key)
    aes.KeyExpansion()
    
    #print(cipher)
   
