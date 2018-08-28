import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class HpptLogger implements IXposedHookLoadPackage {

    private static final String TAG = "XPOSED_LOGGER";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        findAndHookMethod("okhttp3.Request.Builder", lpparam.classLoader, "build", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {    }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d(TAG, "aokhttp3.Request.Builder.build()");
                Log.d(TAG, param.thisObject.toString());
            }
        });

        findAndHookMethod("okhttp3.Response.Builder", lpparam.classLoader, "build", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {    }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d(TAG, "aokhttp3.Response.Builder.build()");
                Log.d(TAG, param.thisObject.toString());
            }
        });

    }
}
