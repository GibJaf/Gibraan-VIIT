#include<jni.h>
#include<stdio.h>
#include "CalcJNI.h"

JNIEXPORT void JNICALL Java_CalcJNI_doCalculate
 (JNIEnv *env , jobject thisObj , jint a , jint b , jchar ch){
    int n;
    switch(ch){
        case '+' : n = a+b;
                   printf(" Result = %d\n",n);
                    break;
        case '-' : n = a-b;
                    printf(" Result = %d\n",n);
                    break;
        case '*' : n = a*b;
                    printf(" Result = %d\n",n);
                    break;
        case '/' : n = a/b;
                    printf(" Result = %d\n",n);
                    break;
        default : printf(" Error occured !");
    }

 }
