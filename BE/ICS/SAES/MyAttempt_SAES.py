#!/bin/bash
"""
eg:
Plaintext  => genius
Key        => ok
Cipher     => <some gibberish>
Plaintext  => genius

The comments have been left intentionally to help you debug and understand the code.
Can also use the SAES_explanation PDFs to aid understanding.

The MixColumn operation in this program is according to SAES_explanation2.pdf

The entire program is written in an attempt to be understood and used easily.
If you come across any vulns let me know through an issue in this repo.
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
        if len(plaintext)%2 != 0: # to ensure length of plaintext is even because key is of length 2 characters
            plaintext += '#'

        binary_plain = self.ConvertASCIItoBinary(plaintext)
        binary_key   = self.ConvertASCIItoBinary(key)
        #print("binary plaintext => ",binary_plain)
        #print("binary key       => ",binary_key)
        

        self.key = binary_key
        self.KeyExpansion()
        
        binary_cipher = ""
        for i in range(0,len(binary_plain),16):
            # Add Round Key0
            state = self.XOR(binary_plain[i:i+16],self.Key0)

            # Round 1
            state = self.SBoxSub(state)       # Substitute nibbles
            state = self.ShiftRows(state)     # Shift rows
            #print("state before MixColumns => ",state)
            state = self.MixColumns(state)    # MixColumns
            #print("state after MixColumns  => ",state)
            state = self.XOR(state,self.Key1) # Add Round Key1
        
            # Round 2
            state = self.SBoxSub(state)
            state = self.ShiftRows(state)
            state = self.XOR(state,self.Key2) # Now state contains the ciphertext
            binary_cipher += state
        ciphertext = self.ConvertBinaryToASCII(binary_cipher)
        return ciphertext



    def decrypt(self,ciphertext,key):

        # binary_key = self.ConvertASCIItoBinary(key) 
        # Not required cuz Keys saved to object at the time of encryption 

        binary_cipher = self.ConvertASCIItoBinary(ciphertext)

        binary_clear = ""
        for i in range(0,len(binary_cipher),16):
            # Round 2
            state = self.XOR(binary_cipher[i:i+16],self.Key2)  # Add Round Key2
            state = self.InvShiftRows(state)        # Inverse Shift Rows
            state = self.InvSBoxSub(state)          # Inverse Substitute nibbles

            # Round 1
            state = self.XOR(state,self.Key1)
            #print("state after  AddRoundKey 1 => ",state)
            #print("state before InvMixColumns => ",state)
            state = self.InvMixColumns(state)
            #print("state after  InvMixColumns => ",state)
            state = self.InvShiftRows(state)
            #print("state after  InvShiftRows  => ",state)
            state = self.InvSBoxSub(state)
            #print("state after  InvSBoxSub    => ",state)
        
            # Add RoundKey 0
            state = self.XOR(state,self.Key0) # Now state contains plaintext as a binary string """

            binary_clear += state
        #print("state after  AddRoundKey 0 => ",state)
        cleartext = self.ConvertBinaryToASCII(binary_clear)
        if(cleartext.endswith("#")):
            cleartext = cleartext[:-1]
        return cleartext



    def ConvertASCIItoBinary(self,word):
        ''' ascii -> decimal -> binary '''
        binary = ""
        for letter in word:
            x = bin(ord(letter)).replace("0b","")
            t = 8 - len(x)
            x = '0'*t + x #prepend with 0s to make length=8
            binary += x
        return binary

    def ConvertBinaryToASCII(self,binary):
        ''' binary -> decimal -> ascii '''
        ascii = ""
        for i in range(0,len(binary),8):
            ascii += chr(int(binary[i:i+8],2))
        return ascii
            
        
    


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
        # ensure length of all args is same using try catch)
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
        # Exactly like SBoxSub() , only difference if the dictionary it is referencing InvSBoxSub
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
        return self.MixColumns(state) # Because doing XOR again reverses the operation
        


if __name__ == "__main__":
    print("RULES => ")
    print("1) Plaintext => Any combination of a-z , A-Z and 0-9 of any length.")
    print("2) Key       => Any combination of a-z , A-Z and 0-9 of length 2 characters only.")
    print()
    plaintext = input("Enter plaintext : ")
    key       = input("Enter key       : ")
    aes = AES()
    cipher = aes.encrypt(plaintext,key)
    print("Ciphertext after encryption => '",cipher,"'")
    clear = aes.decrypt(cipher,key)
    print("Plaintext after decryption  => ",clear)