#include <jni.h>
#include <string>

#include "a.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_libserialport_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello;

//    hello = "Hello from C++";
    hello = getString();
    return env->NewStringUTF(hello.c_str());
}
