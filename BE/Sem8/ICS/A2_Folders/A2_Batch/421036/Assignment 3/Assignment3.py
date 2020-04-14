def power(q, secretKey, p):
	return pow(q, secretKey) % p;

if __name__ == '__main__':
	
	P = int(input("Enter the first prime number P by ALICE : "))
	Q = int(input("Enter the second prime number Q by BOB: "))

	a = int(input("Enter the secret integer a by ALICE : "))
	R = power(Q, a, P)
	print("ALICE calculates R : " + str(R));

	b = int(input("Enter the secret integer b by BOB : "))
	S = power(Q, b, P)
	print("BOB calculates S : " + str(S));


	print("Sharing each other R and S.")

	RK = power(S, a, P)
	SK = power(R, b, P)

	print("ALICE's secret key : " + str(RK))
	print("BOB's secret key : " + str(SK))

	if RK == SK:
		print("ALICE and BOB can agree on communication.");
	else :
		print("ALICE and BOB can not agree on communication.")