package com.shumidub.httplogger;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import okhttp3.Request;
import okhttp3.Response;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class HttpLogger implements IXposedHookLoadPackage {

    private static final String TAG = "XPOSED_LOGGER";
    private static final String TAG_OKHTTP = "XPOSED_LOGGER_OKHTTP";

        public void handleLoadPackage (XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

            Log.d(TAG, "handleLoadPackage: package - " + lpparam.packageName
                    + ", process -  " + lpparam.processName + ", appinfo - " + lpparam.appInfo);

            findAndHookMethod("okhttp3.Request.Builder", lpparam.classLoader, "build", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d(TAG_OKHTTP, "okhttp3.Request.Builder.build");
                    Request request= (Request) param.getResult();
                    Log.d(TAG_OKHTTP, request.toString());
                    Log.d(TAG_OKHTTP, param.getResult().getClass().toString());
                    Log.d(TAG_OKHTTP, param.getResult().toString());
                    Log.d(TAG_OKHTTP+"1", ((Request)param.getResult()).getClass().toString());
                    Log.d(TAG_OKHTTP+"2", ((Request)param.getResult()).toString());
                }
            });

            findAndHookMethod("okhttp3.Response.Builder", lpparam.classLoader, "build", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d(TAG_OKHTTP, "okhttp3.Response.Builder.build()");
                    Response response = (Response) param.getResult();
                    Log.d(TAG_OKHTTP, response.toString());
                    Log.d(TAG_OKHTTP, param.getResult().getClass().toString());
                    Log.d(TAG_OKHTTP, param.getResult().toString());
                    Log.d(TAG_OKHTTP+"3", ((Response)param.getResult()).getClass().toString());
                    Log.d(TAG_OKHTTP+"4", ((Response)param.getResult()).toString());
                }
            });

        }

}
