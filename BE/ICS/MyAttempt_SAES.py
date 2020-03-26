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
        self.InvSBox = {
            "1001":"0000", # 9  => 0
            "0100":"0001", # 4  => 1
            "1010":"0010", # 10 => 2
            "1011":"0011", # 11 => 3
            "1101":"0100", # 13 => 4
            "0001":"0101", # 1  => 5
            "1000":"0110", # 8  => 6
            "0101":"0111", # 5  => 7
            "0110":"1000", # 6  => 8
            "0010":"1001", # 2  => 9
            "0000":"1010", # 0  => 10
            "0011":"1011", # 3  => 11
            "1100":"1100", # 12 => 12
            "1110":"1101", # 14 => 13
            "1111":"1110", # 15 => 14
            "0111":"1111", # 7  => 15
        }

    def encrypt(self,plaintext,key):
        self.key = key
        self.KeyExpansion()
        
        # Add Round Key0
        state = self.XOR(plaintext,self.Key0)

        # Round 1
        state = self.SBoxSub(state)       # Substitute nibbles
        state = self.ShiftRows(state)     # Shift rows
        state = self.MixColumns(state)    # MixColumns
        state = self.XOR(state,self.Key1) # Add Round Key1
        
        # Round 2
        state = self.SBoxSub(state)
        state = self.ShiftRows(state)
        state = self.XOR(state,self.Key2)
        
        return state # Now state contains the ciphertext


    def decrypt(self,ciphertext,key):

        # Round 2
        state = self.XOR(ciphertext,self.Key2)  # Add Round Key2
        state = self.InvShiftRows(state)        # Inverse Shift Rows
        state = self.InvSBoxSub(state)          # Inverse Substitute nibbles

        # Round 1
        state = self.InvMixColumns(state)
        print("state => ",state)


        






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

    def SBoxSub(self,input_str):
        ''' divide input into nibbles ie: 4 bits and perform SBox substitution '''
        # try catch to check that input length is multiple of 4
        try:
            length = len(input_str)
            if length % 4 != 0:
                raise ValueError
            else:
                result = ""
                for i in range(0,len(input_str),4):
                    result += self.SBox[input_str[i:i+4]] #       return self.SBox[word[0:4]] + self.SBox[word[4:8]]
                return result
        except ValueError:
            print("Length of input to SBoxSub() is not multiple of 4")
            return "----------------"


    def InvSBoxSub(self,state):
        ''' divide input into nibbles ie: 4 bits and perform InvSBox substitution '''
        # Exactly like SBoxSub() , only difference if the dictionary it is referencing ie: InvSBoxSub
        # try catch to check that input length is multiple of 4
        try:
            length = len(state)
            if length % 4 != 0:
                raise ValueError
            else:
                result = ""
                for i in range(0,len(state),4):
                    result += self.InvSBox[state[i:i+4]] #       return self.SBox[word[0:4]] + self.SBox[word[4:8]]
                return result
        except ValueError:
            print("Length of input to InvSBoxSub() is not multiple of 4")
            return "----------------"



    def ShiftRows(self,state):
        return state[0:4]+state[12:16]+state[8:12]+state[4:8]

    def InvShiftRows(self,state):
        return self.ShiftRows(state)


    def MixColumns(self,state):
        nibble1 = self.XOR(state[0:4],"1101")
        nibble2 = self.XOR(state[4:8],"1000")
        nibble3 = self.XOR(state[8:12],"1101")
        nibble4 = self.XOR(state[12:16],"1101")
        return nibble1+nibble2+nibble3+nibble4

    def InvMixColumns(self,state):
        ''' STUCK HERE '''
        pass
        


if __name__ == "__main__":
    plaintext = "1101011100101000" #16 character long string of 0 and 1s
    key = "0100101011110101" #16 character long string of 0 and 1s
    aes = AES()
    cipher = aes.encrypt(plaintext,key)
    #print("Cipher => ",cipher)
    
    clear = aes.decrypt(cipher,key)
    print("Plaintext => ",clear)

    #print(aes.SBoxSub("100111011101110"))
    
    #print(cipher)
   
