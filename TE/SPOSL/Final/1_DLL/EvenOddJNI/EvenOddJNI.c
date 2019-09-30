#include<jni.h>
#include<stdio.h>
#include "EvenOddJNI.h"

JNIEXPORT void JNICALL Java_EvenOddJNI_Determine(JNIEnv *env , jobject thisObj , jint a){
	if(a%2 == 0)
		printf("%d is an even number \n",a);
	else
		printf("%d is an odd number \n",a);
	return;
}
