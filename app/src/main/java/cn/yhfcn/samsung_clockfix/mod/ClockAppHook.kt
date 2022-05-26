package cn.yhfcn.samsung_clockfix.mod

import android.media.AudioManager
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class ClockAppHook: IXposedHookLoadPackage {
    @Throws(Throwable::class)
    override fun handleLoadPackage(lpParam: XC_LoadPackage.LoadPackageParam) {
        if (lpParam.packageName == "com.sec.android.app.clockpackage") {
            XposedHelpers.findAndHookMethod(AudioManager::class.java, "semIsRecordActive", Int::class.java, object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    param.result = false
                }
            })
            /**
             * @see android.media.AudioManager.getMode
             * @see android.media.AudioManager.MODE_CALL_SCREENING = 4
             */
            XposedHelpers.findAndHookMethod(AudioManager::class.java, "getMode", object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    param.result = 4
                }
            })
        }
    }
}