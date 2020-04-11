#include<iostream>
#include<omp.h>
#include<string.h>
#include<cstring>
#include<fstream>
#define MAX_RLEN 50
using namespace std;

char *encode(char *src)
{
	int rlen;
	char count[MAX_RLEN];
	
	int len = strlen(src);
	char *dest = new char[2*len+1];
	
	int i,j=0,k;
	
	for(i =0;i<len;i++)
	{
		dest[j++] = src[i];
		
		rlen = 1;
		while(i+1 < len && src[i] == src[i+1])
		{
			rlen++;
			i++;
		}
		
		sprintf(count,"%d",rlen);
		
		for(k=0;*(count+k);k++,j++)
		{
			dest[j] = count[k];
		}
		
	}	
	//cout<<dest<<endl;
	dest[j] = '\0';
	return dest;
}

int main()
{
	
	ifstream file;
	file.open("a.txt",ios::binary);
	
	string response;
	char **inp = new char*[915000];
	int i=0;
	while(getline(file,response))
	{
		inp[i] = new char[strlen(response.c_str())+1];
		strcpy(inp[i],response.c_str());
		i++;
	}
	
	int j;
	double start = omp_get_wtime();
	
	#pragma omp parallel for
	for(j=0;j<i;j++)
	{
		inp[j] = encode(inp[j]);
		//cout<<endl<<j<<endl;
	}
	cout<<"\nParallel execution : "<<omp_get_wtime() - start<<endl;

	
		
	start = omp_get_wtime();
	for(j=0;j<i;j++)
	{
		inp[j]=encode(inp[j]);
	}
	cout<<"\nSerial execution : "<<omp_get_wtime() - start<<endl;
	
	
	return 0;
}
