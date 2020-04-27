#!/bin/bash

InputPerm = [2,6,3,1,4,8,5,7]
FinalPerm = [4,1,3,5,7,2,8,6]

EPtable = [4,1,2,3,2,3,4,1]
S0 = [[1,0,3,2],[3,2,1,0],[0,2,1,3],[3,1,3,2]]
S1 = [[0,1,2,3],[2,0,1,3],[3,0,1,0],[2,1,0,3]]
P4table = [2,4,3,1]

P10Table = [3,5,2,7,4,10,1,9,8,6]
P8Table = [6,3,7,4,8,5,10,9]


def permuteKey(key,table):
    output = ""
    for i in table:
        output += key[i-1]
    return output

def LeftShift(key,n):
    ''' Left shift the key received by n positions'''
    output = key[n:len(key)]
    output += key[0:n]
    return output



def keyGen(key):
    P10 = permuteKey(key,P10Table)
    s1 = LeftShift(P10[0:5],1)
    s2 = LeftShift(P10[5:10],1)
    print("s1 => ",s1)
    print("s2 => ",s2)
    print("s1 + s2 =>",s1+s2)
    K1 = permuteKey(s1+s2,P8Table)
    print("K1 => ",K1)
    s3 = LeftShift(s1,2)
    s4 = LeftShift(s2,2)
    K2 = permuteKey(s3+s4,P8Table)
    return K1,K2


def fk_cycle(p1,p2,K):
    ''' fk block of operations. Same steps but different values '''
    EP = permuteKey(p2,EPtable)
    xor_result = XOR(EP,K)
    left = xor_result[0:4]
    right = xor_result[4:8]
    s0 = MatrixOperation(left,S0)
    s1 = MatrixOperation(right,S1)
    P4 = permuteKey(s0+s1,P4table)
    result = XOR(p1,P4)
    return result,p2
    

def XOR(str1,str2):
    ''' taking length because it is being used for both 8bit and 4bit XOR '''
    result = ""
    for i in range(len(str1)):
        result += str(int(str1[i]) ^ int(str2[i]))
    return result


def MatrixOperation(part, S_Matrix):
    row = part[0]+part[3]
    column = part[1]+part[2]
    convert_int = {
        "00":0,
        "01":1,
        "10":2,
        "11":3,
    }
    row_num = convert_int.get(row)
    column_num = convert_int.get(column)
    result_int = S_Matrix[row_num][column_num]
    convert_str = {
        0:"00",
        1:"01",
        2:"10",
        3:"11",
    }
    result = convert_str.get(result_int)
    return result

def encrypt(plaintext):
    IP = permuteKey(plaintext,InputPerm)
    print("Plain => ",plaintext)
    print("IP    => ",IP)
    p1,p2 = fk_cycle(IP[0:4],IP[4:8],K1)
    p3,p4 = fk_cycle(p2,p1,K2)
    IP_inv = permuteKey(p3+p4,FinalPerm) 
    return IP_inv


def decrypt(ciphertext):
    IP = permuteKey(ciphertext,InputPerm)
    p1,p2 = fk_cycle(IP[0:4],IP[4:8],K2)
    p3,p4 = fk_cycle(p2,p1,K1)
    IP_inv = permuteKey(p3+p4,FinalPerm)
    return IP_inv

if __name__ == "__main__":
    print("Note : Enter plaintext and key as strings of 0s and 1s eg: 01110010")
    plaintext = "01110010" # input("Enter plaintext (length 8 bits) : ") # sample plaintext => "01110010"
    """ If input greater than 8 bits , divide it into 8 bit blocks and encrypt each block """
    key = "1010000010" #input("Enter key (length 10 bits): ") # sample key => "1010000010"
    K1 , K2 = keyGen(key)
    ciphertext = encrypt(plaintext)
    print("encrypted ciphertext => ",ciphertext)
    plain_decrypt = decrypt(ciphertext)
    print("decrypted plaintext =>  ",plain_decrypt)

""" Features to improve => 

This is a simulation . Not the real S-DES at bit level.
1) Handle plaintext of size greater than 8 bits
2) Check if key can be of length other than 10 bits """