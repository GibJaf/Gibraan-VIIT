""" 
    This is from the book TechKnowledge
    
    Expects input plaintext as a string of characters like "hello"
    Expects input key       as a string of 2 characters like "ok"
"""

# S boxe
sbox = [9, 4, 10, 11, 13, 1, 8, 5, 6, 2, 0, 3, 12, 14, 15, 7]


def convertNumToAsciiBit(x): # coverts decimal to binary
    y = ""
    for i in range(len(x)):
        val = ord(x[i])
        j = 7
        ans = ""
        while j >= 0:
            w = val // (pow(2, j))
            #print("w => ",w,"   2^j = ",(pow(2,j)),"    val = ",val)
            ans += str(w)
            val = val % (pow(2, j))
            j -= 1
        y += ans
    # print("From convertNumToAsciiBit y =>",y)
    return y # y is string of 16 characters of 0 and 1


def convertAsciiToChar(x): # converts ASCII value to char
    y = ""
    for i in range(0, len(x), 8):
        ans = 0
        for j in range(8):
            ans += int(x[i + j]) * pow(2, 7 - j)
        if i == len(x) - 8:
            if chr(ans) != '#':
                y += chr(ans)
        else:
            y += chr(ans)
    return y


def keyExpansion(key): # generates 2 round keys
    x = [key[:4], key[4:8], key[8:12], key[12:16]]
    for i in range(4): # binary to decimal for each nible
        x[i] = list(map(int, x[i]))
        #print(x)
        x[i] = x[i][0] * 8 + x[i][1] * 4 + x[i][2] * 2 + x[i][3]
        #print(x,'\n')
    keylist = [x[0], x[1], x[2], x[3]]
    #print("\nkeylist => ",keylist,'\n\n')
    for i in range(2):
        w2 = [0, 0, 0, 0]
        if i == 0:
            val = 8 # rcon for first round
        else:
            val = 3 # rcon for 2nd round
        w2[0] = keylist[4 * i] ^ val ^ (sbox[keylist[4 * i + 3]])        # Where did these formulae come from ?
        w2[1] = keylist[4 * i + 1] ^ 0 ^ (sbox[keylist[4 * i + 2]])      #
        w2[2] = w2[0] ^ keylist[4 * i + 2]                               #
        w2[3] = w2[1] ^ keylist[4 * i + 3]                               #
        keylist.append(w2[0])
        keylist.append(w2[1])
        keylist.append(w2[2])
        keylist.append(w2[3])
    #print("\n\nFrom KeyExpansion => ",keylist,"\n\n\n")
    return keylist # has all 3 sub-keys . It is list of 12 numbers ranging from 0 to 15


def getByteFromBit(x):# converts binary to bytes
    #print("getByteFromBit i/p => ",x)
    y = []
    i = 0
    while i < (len(x)):
        y.append(8 * x[i] + 4 * x[i + 1] + 2 * x[i + 2] + x[i + 3])
        i += 4
    #print("getByteFromBit o/p => ",y)
    return y


def mixCols(y): # applies Mix-Columns
    print("mixCols i/p  y => ",y)
    w = []
    for i in range(len(y)):
        val = y[i] * 4
        if val >= 32:
            val ^= 38
        if val >= 16:
            val ^= 19
        w.append(val)
    print("w => ",w)
    ans = [0, 0, 0, 0]
    ans[0] = y[0] ^ w[1]
    ans[1] = y[1] ^ w[0]
    ans[2] = y[2] ^ w[3]
    ans[3] = y[3] ^ w[2]
    print("mixCols o/p  ans => ",ans)
    return ans


def convertByteToBit(y): # converts byte value to binary
    cipher = []
    for i in range(len(y)):
        val = y[i]
        val1 = val // 8
        cipher.append(val1)
        val = val % 8
        val1 = val // 4
        cipher.append(val1)
        val = val % 4
        val1 = val // 2
        cipher.append(val1)
        val1 = val % 2
        cipher.append(val1)
    cipher = list(map(str, cipher))
    return "".join(cipher)


def mult(x, y):
    val = x * y
    if y == 2:
        if val >= 32:
            val ^= 38
        if val >= 16:
            val ^= 19
    else:
        val = x * 8
        if val >= 64:
            val ^= 76
        if val >= 32:
            val ^= 38
        if val >= 16:
            val ^= 19
        val ^= x
    return val


def inverseMixCols(y): # applies inverse Mix-Columns
    w = [0, 0, 0, 0]
    w[0] = mult(y[0], 9) ^ mult(y[1], 2)
    w[1] = mult(y[1], 9) ^ mult(y[0], 2)
    w[2] = mult(y[2], 9) ^ mult(y[3], 2)
    w[3] = mult(y[3], 9) ^ mult(y[2], 2)
    return w


def aesDecrypt(y, keylist): # applies Decryption Algorithm
    j = 2
    for i in range(len(y)):
        y[i] ^= keylist[4 * j + i]
    j = 1
    while j >= 0:
        y[1], y[3] = y[3], y[1]
        for i in range(len(y)):
            y[i] = sbox.index(y[i])
        for i in range(len(y)):
            y[i] ^= keylist[4 * j + i]
        if j != 0:
            y = inverseMixCols(y)
        j -= 1
    return convertByteToBit(y)


def aesEncrypt(y, keylist): # applies Encryption Algorithm
    print("aesEncrypt i/p => \ny = ",y,"\nkeylist = ",keylist,"\n")

    print("Add RoundKey before Round 1 and 2")
    for i in range(len(y)): # Add Round Key before Round 1 and 2 
        y[i] ^= keylist[i % 4] # Probably %4 not required cuz len(y) will be 4
    print("y => ",y)
    print()


    for i in range(1, 3):   # Rounds 1 and 2
        print("\nRound",i)
        for j in range(len(y)):
            y[j] = sbox[y[j]]
        print("y => ",y," <= Substitute Bytes")
        y[1], y[3] = y[3], y[1]  # Shift Row step
        print("y => ",y," <= Shift Rows")
        if i != 2:               # Executed only in 1st round
            y = mixCols(y)
        for j in range(len(y)):
            y[j] = y[j] ^ keylist[4 * i + j] # Add Round Key 2
        print("y => ",y," <= Add Round Key",i)
    print("aesEncrypt o/p =>    y = ",y)
    return convertByteToBit(y)


if __name__ == "__main__":
    #print("Note : Enter strings of character inputs eg : hello etc . Each character is considered to be of 1 byte")
    x = "he" # input("Enter the plaintext : ") # any length char input
    key = "ok" #input("Enter the key : ") # char input of length 2
    if len(key) != 2:
        print("BAD KEY : Should be 16 bits ")
        exit(0)
    key = convertNumToAsciiBit(key)
    print("key => ",key)
    keylist = keyExpansion(key)
    
    #Basically making plaintext of even no. of characters by appending with character '#'
    #Don't know reason for choosing '#'
    if len(x) % 2 != 0:
        x += '#'  # filler - #
    x = convertNumToAsciiBit(x)
    print("x =>   ",x)
    x = list(map(int, x))
    #print("x => ",x)
    i = 0
    cipher = ""
    #print("length of x => ",len(x))
    while i < len(x) - 1: #  Probably the -1 is useless . Anyway , there is much better way to write this loop
        y = getByteFromBit(x[i:i + 16])
        cipher += aesEncrypt(y, keylist)
        i += 16
    print("Cipher text after encryption is : ")
    print(cipher)
    print(convertAsciiToChar(cipher)) 
    x = list(map(int, cipher))
    i = 0
    plaintext = ""
    while i < len(x) - 1:
        y = getByteFromBit(x[i:i + 16])
        plaintext += aesDecrypt(y, keylist)
        i += 16
    print("Plain text after decryption is : ")
    print(plaintext)
    print(convertAsciiToChar(plaintext))
