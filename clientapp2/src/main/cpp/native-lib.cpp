#include <jni.h>
#include <string>
#include <stdlib.h>
#include <android/log.h>


#define TAG "myDemo-jni" // 这个是自定义的LOG的标识
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型




extern "C"
JNIEXPORT void JNICALL
Java_com_example_clientapp2_MainActivity_test1(JNIEnv *env, jobject instance) {

    // TODO

}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_clientapp2_MainActivity_jniFormC(JNIEnv *env, jobject instance) {
    std::string hello = "Hello from C++";
    env->NewStringUTF("");
    return env->NewStringUTF(hello.c_str());
}