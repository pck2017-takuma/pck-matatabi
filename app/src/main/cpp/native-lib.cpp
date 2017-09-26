#include <jni.h>
#include <string>
#include"search.h"
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_owner_matatabi_1search_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
   // std::string hello = "Hello from C++";
    std::string hello = test();
    return env->NewStringUTF(hello.c_str());
}
