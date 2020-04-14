len = 5
inp = [1,0,1,0,0]
def main():
    andGate()
    andNotGate()
    notGate()
    xorGate()
    orGate()

def andGate():
    threshold=len
    sum = summation(inp)
    if sum>= threshold:
        print("And Gate activated y=1")
    else:
        print("Output from And Gate y=0")

def andNotGate():
    threshold=1
    sum=summation(inp)
    if sum>=threshold:
        print("And-Not Gate activated y=1")
    else:
        print("Output from And-Not Gate y=0")

def orGate():
    sum=summation(inp)
    if sum>=1:
        print("Output of OR gate y=1")
    else:
        print("Output of OR gate y=0")

def xorGate():
    sum= summation(inp)
    if sum>=1:
        print("Output of XOR gate y=1")
    else:
        print("Output of XOR gate y=0")

def notGate():
    threshold=0
    invert(inp)
    sum=summation(inp)
    if sum ==0:
        print("Output of NOT gate y=1")
    else:
        print("Output of NOT gate y=0")
    invert(inp)

def invert(inp):
    i=0;
    while i<len:
        if inp[i]==1:
            inp[i]=-1
        else:
            if inp[i]==-1:
                inp[i]=1
        i+=1

def summation(inp):
    i=0
    sum=0
    while i <len:
        sum+=inp[i]
        i+=1
    return sum

main()