#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_kcstation_js4android_MainActivity_getMsgFromJni(JNIEnv *env, jobject instance) {

    // TODO

    return (*env)->NewStringUTF(env, "hard time has gone");
}