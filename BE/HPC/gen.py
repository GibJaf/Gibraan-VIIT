#!/usr/bin/python3

import os


PATH = os.getcwd()+"/"


def main():
	try:
		num = 421028
	for i in range(27):
		path = os.path.join(PATH,str(num))
		print(path)
	
		# Make folder
		os.mkdir(path)

		# Put everything into folder
		
		num += 1
		
except OSError:
    print ("Creation of the directory %s failed" % path)
else:
    print ("Successfully created the directory %s " % path)

if __name__ == "__main__"
	main()
