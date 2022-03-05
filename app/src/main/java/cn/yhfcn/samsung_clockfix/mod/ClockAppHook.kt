package cn.yhfcn.samsung_clockfix.mod

import android.content.Context
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class ClockAppHook: IXposedHookLoadPackage {
    @Throws(Throwable::class)
    override fun handleLoadPackage(lpParam: XC_LoadPackage.LoadPackageParam) {
        if (lpParam.packageName == "com.sec.android.app.clockpackage") {
            val clazz = lpParam.classLoader.loadClass("com.sec.android.app.clockpackage.common.util.StateUtils")

            XposedHelpers.findAndHookMethod(
                clazz,
                "isInCall",
                Context::class.java,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        super.beforeHookedMethod(param)
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        param.result = false
                        super.afterHookedMethod(param)
                    }
                })

            XposedHelpers.findAndHookMethod(
                clazz,
                "isInVoipCall",
                Context::class.java,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        super.beforeHookedMethod(param)
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        param.result = false
                        super.afterHookedMethod(param)
                    }
                })
        }
    }
}