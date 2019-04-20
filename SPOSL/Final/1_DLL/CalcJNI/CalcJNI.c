#include<jni.h>
#include<stdio.h>
#include "CalcJNI.h"

JNIEXPORT void JNICALL Java_CalcJNI_doCalculate
  (JNIEnv *env, jobject thisObj, jint a, jint b, jchar ch)
  {
  	int n;
  	printf("First Number:%d",a);
  	printf("\nSecond Number:%d",b);
  	switch(ch)
  	{
  		case '+' : 	n=a+b;
  				printf("\nAddition:%d\n",n);
  				break;
  		case '-' : 	n=a-b;
  				printf("\nSubtraction:%d\n",n);
  				break;
  		case '*' : 	n=a*b;
  				printf("\nMultiplication:%d\n",n);
  				break;
  		case '/' : 	n=a/b;
  				printf("\nDivision:%d\n",n);
  				break;		
  		default : printf("\nError occured!\n");	
  	}
  	return;
  }
