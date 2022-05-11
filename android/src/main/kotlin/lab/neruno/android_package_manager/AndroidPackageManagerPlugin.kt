package lab.neruno.android_package_manager

import android.content.ComponentName
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.io.ByteArrayOutputStream
import java.lang.IllegalArgumentException

/** AndroidPackageManagerPlugin */
class AndroidPackageManagerPlugin: FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel : MethodChannel

    private lateinit var packageManager: PackageManager

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N)
    private fun isAtLeastAndroid24(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
    private fun isAtLeastAndroid26(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O_MR1)
    private fun isAtLeastAndroid27(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
    private fun isAtLeastAndroid28(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    private fun isAtLeastAndroid29(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    private fun isAtLeastAndroid30(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    private fun isAtLeastAndroid31(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S_V2)
    private fun isAtLeastAndroid32(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2
    }

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "android_package_manager")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "addPermission" -> {
                // TODO: Find way to obtain PermissionInfo instances somewhere
                result.success(null)
            }
            "addWhitelistedRestrictedPermission" -> addWhitelistedRestrictedPermission(call, result)
            "canPackageQuery" -> {
                // TODO: Raise targetSdk to 32 (Tiramisu)
                result.success(null)
            }
            "canRequestPackageInstalls" -> canRequestPackageInstalls(result)
            "canonicalToCurrentPackageNames" -> canonicalToCurrentPackageNames(call, result)
            "checkPermission" -> checkPermission(call, result)
            "checkSignatures" -> checkSignatures(call, result)
            "clearInstantAppCookie" -> clearInstantAppCookie(result)
            "currentToCanonicalPackageNames" -> currentToCanonicalPackageNames(call, result)
            "extendVerificationTimeout" -> {
                // TODO: Research required
                result.success(null)
            }
            "getActivityBanner" -> getActivityBanner(call, result)
            "getActivityIcon" -> getActivityIcon(call, result)
            "getActivityInfo" -> {
                // TODO: Raise targetSdk to 32 (Tiramisu)
                result.success(null)
            }
            "getActivityLogo" -> getActivityLogo(call, result)
            "getAllPermissionGroups" -> getAllPermissionGroups(result)
            "getApplicationBanner" -> getApplicationBanner(call, result)
            "getApplicationEnabledSetting" -> getApplicationEnabledSetting(call, result)
            "getApplicationIcon" -> getApplicationIcon(call, result)
            "getApplicationLabel" -> {
                // TODO: Research for [ActivityInfo] required
                result.success(null)
            }
            "getBackgroundPermissionOptionLabel" -> getBackgroundPermissionOptionLabel(result)
            "getChangedPackages" -> getChangedPackages(call, result)
            "getComponentEnabledSetting" -> getComponentEnabledSetting(call, result)
            "getDefaultActivityIcon" -> getDefaultActivityIcon(result)
            "getDrawable" -> {
                // TODO: Research or refer other drawable fetching plugins
                result.success(null)
            }
            "getInstallSourceInfo" -> getInstallSourceInfo(call, result)
            "getInstalledApplications" -> getInstalledApplications(call, result)
            "getInstalledModules" -> getInstalledModules(result)
            "getInstalledPackages" -> getInstalledPackages(call, result)
            "getInstantAppCookie" -> getInstantAppCookie(result)
            "getInstantAppCookieMaxBytes" -> getInstantAppCookieMaxBytes(result)
            "getPermissionInfo" -> getPermissionInfo(call, result)
            else -> result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        packageManager = binding.activity.packageManager
    }

    override fun onDetachedFromActivityForConfigChanges() {}

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {}

    override fun onDetachedFromActivity() {}

    private fun addWhitelistedRestrictedPermission(call: MethodCall, result: Result) {
        if (!isAtLeastAndroid29()) {
            result.error("UnsupportedException", "Requires at least Android 29 (Q)", null)
            return
        }
        val packageName = call.argument<String>("packageName")
        val permName = call.argument<String>("permName")
        val whitelistFlags = call.argument<Int>("whitelistFlags")
        if (packageName == null || permName == null || whitelistFlags == null) {
            result.success(null)
            return
        }
        val allPermissionFlags = listOf(
            PackageManager.FLAG_PERMISSION_WHITELIST_INSTALLER,
            PackageManager.FLAG_PERMISSION_WHITELIST_SYSTEM,
            PackageManager.FLAG_PERMISSION_WHITELIST_UPGRADE
        )
        if (!allPermissionFlags.contains(whitelistFlags)) {
            result.success(null)
            return
        }
        packageManager.addWhitelistedRestrictedPermission(packageName, permName, whitelistFlags)
        result.success(null)
    }

    private fun canRequestPackageInstalls(result: Result) {
        if (!isAtLeastAndroid26()) {
            result.success(false)
            return
        }
        result.success(packageManager.canRequestPackageInstalls())
    }

    private fun canonicalToCurrentPackageNames(call: MethodCall, result: Result) {
        val packageNames = call.argument<List<String>>("packageNames")?.toTypedArray()
        if (packageNames == null) {
            result.success(null)
            return
        }
        result.success(
            packageManager.canonicalToCurrentPackageNames(packageNames)
        )
    }

    private fun checkPermission(call: MethodCall, result: Result) {
        val permName = call.argument<String>("permName")
        val packageName = call.argument<String>("packageName")
        if (permName == null || packageName == null) {
            result.success(null)
            return
        }
        result.success(
            packageManager.checkPermission(permName, packageName)
        )
    }

    private fun checkSignatures(call: MethodCall, result: Result) {
        val packageName1 = call.argument<String>("packageName1")
        val packageName2 = call.argument<String>("packageName2")
        if (packageName1 == null || packageName2 == null) {
            result.success(PackageManager.SIGNATURE_UNKNOWN_PACKAGE)
            return
        }
        result.success(
            packageManager.checkSignatures(packageName1, packageName2)
        )
    }

    private fun clearInstantAppCookie(result: Result) {
        if (isAtLeastAndroid26()) {
            packageManager.clearInstantAppCookie()
        }
        result.success(null)
    }

    private fun currentToCanonicalPackageNames(call: MethodCall, result: Result) {
        val packageNames = call.argument<List<String>>("packageNames")?.toTypedArray()
        if (packageNames == null) {
            result.success(null)
            return
        }
        result.success(
            packageManager.currentToCanonicalPackageNames(packageNames)
        )
    }

    private fun getActivityBanner(call: MethodCall, result: Result) {
        val pkg = call.argument<String>("pkg")
        val cls = call.argument<String>("cls")
        if (pkg == null || cls == null) {
            result.success(null)
            return
        }
        try {
            val activityName = ComponentName(pkg, cls)
            sendDrawableResult(packageManager.getActivityBanner(activityName), result)
        } catch (ex: PackageManager.NameNotFoundException) {
            result.error(ex.javaClass.name, ex.message, null)
        }
    }

    private fun getActivityIcon(call: MethodCall, result: Result) {
        val pkg = call.argument<String>("pkg")
        val cls = call.argument<String>("cls")
        if (pkg == null || cls == null) {
            result.success(null)
            return
        }
        try {
            val activityName = ComponentName(pkg, cls)
            sendDrawableResult(packageManager.getActivityIcon(activityName), result)
        } catch (ex: PackageManager.NameNotFoundException) {
            result.error(ex.javaClass.name, ex.message, null)
        }
    }

    private fun getActivityLogo(call: MethodCall, result: Result) {
        val pkg = call.argument<String>("pkg")
        val cls = call.argument<String>("cls")
        if (pkg == null || cls == null) {
            result.success(null)
            return
        }
        try {
            val activityName = ComponentName(pkg, cls)
            sendDrawableResult(packageManager.getActivityLogo(activityName), result)
        } catch (ex: PackageManager.NameNotFoundException) {
            result.error(ex.javaClass.name, ex.message, null)
        }
    }

    private fun getAllPermissionGroups(result: Result) {
        val permissionGroups = packageManager.getAllPermissionGroups(PackageManager.GET_META_DATA)
        permissionGroups.map {
            it.toMap()
        }.run {
            result.success(this)
        }
    }

    private fun getApplicationBanner(call: MethodCall, result: Result) {
        val packageName = call.argument<String>("packageName")
        if (packageName == null) {
            result.success(null)
            return
        }
        try {
            sendDrawableResult(packageManager.getApplicationBanner(packageName), result)
        } catch (ex: PackageManager.NameNotFoundException) {
            result.error(ex.javaClass.name, ex.message, null)
        }
    }

    private fun getApplicationEnabledSetting(call: MethodCall, result: Result) {
        val packageName = call.argument<String>("packageName")
        if (packageName == null) {
            result.success(null)
            return
        }
        try {
            result.success(
                packageManager.getApplicationEnabledSetting(packageName)
            )
        } catch (ex: IllegalArgumentException) {
            result.error(ex.javaClass.name, ex.message, null)
        }
    }

    private fun getApplicationIcon(call: MethodCall, result: Result) {
        val packageName = call.argument<String>("packageName")
        if (packageName == null) {
            result.success(null)
            return
        }
        try {
            sendDrawableResult(packageManager.getApplicationIcon(packageName), result)
        } catch (ex: PackageManager.NameNotFoundException) {
            result.error(ex.javaClass.name, ex.message, null)
        }
    }

    private fun getBackgroundPermissionOptionLabel(result: Result) {
        if (!isAtLeastAndroid30()) {
            result.success(null)
            return
        }
        result.success(packageManager.backgroundPermissionOptionLabel.toString())
    }

    private fun getChangedPackages(call: MethodCall, result: Result) {
        if (!isAtLeastAndroid26()) {
            result.success(null)
            return
        }
        val sequenceNumber = call.argument<Int>("sequenceNumber")
        if (sequenceNumber == null) {
            result.success(null)
            return
        }
        result.success(packageManager.getChangedPackages(sequenceNumber))
    }

    private fun getComponentEnabledSetting(call: MethodCall, result: Result) {
        val pkg = call.argument<String>("pkg")
        val cls = call.argument<String>("cls")
        if (pkg == null || cls == null) {
            result.success(null)
            return
        }
        val componentName = ComponentName(pkg, cls)
        packageManager.getComponentEnabledSetting(componentName)
    }

    private fun getDefaultActivityIcon(result: Result) {
        sendDrawableResult(packageManager.defaultActivityIcon, result)
    }

    private fun getInstallSourceInfo(call: MethodCall, result: Result) {
        if (!isAtLeastAndroid30()) {
            result.success(null)
            return
        }
        val packageName = call.argument<String>("packageName")
        if (packageName == null) {
            result.success(null)
            return
        }
        try {
            packageManager.getInstallSourceInfo(packageName).run {
                result.success(
                    this.toMap()
                )
            }
        } catch (ex: PackageManager.NameNotFoundException) {
            result.error(ex.javaClass.name, ex.message, null)
        }
    }

    private fun getInstalledApplications(call: MethodCall, result: Result) {
        val flags = call.argument<Int>("flags") ?: PackageManager.GET_META_DATA
        val baseFlags = mutableListOf(
            PackageManager.GET_META_DATA,
            PackageManager.GET_SHARED_LIBRARY_FILES
        )
        if (isAtLeastAndroid24()) {
            baseFlags += listOf(
                PackageManager.MATCH_DISABLED_UNTIL_USED_COMPONENTS,
                PackageManager.MATCH_SYSTEM_ONLY,
                PackageManager.MATCH_UNINSTALLED_PACKAGES
            )

            if (isAtLeastAndroid29()) {
                baseFlags += listOf(
                    PackageManager.MATCH_APEX
                )
            }
        }
        if (baseFlags.contains(flags)) {
            packageManager.getInstalledApplications(flags).run {
                this.map {
                    it.toMap()
                }
            }.run {
                result.success(this)
            }
        } else {
            result.success(null)
        }
    }

    private fun getInstalledModules(result: Result) {
        if (!isAtLeastAndroid29()) {
            result.success(null)
            return
        }
        result.success(
            packageManager.getInstalledModules(PackageManager.MATCH_ALL).map {
                it.toMap()
            }
        )
    }

    private fun getInstalledPackages(call: MethodCall, result: Result) {
        val flags = call.argument<Int>("flags") ?: PackageManager.GET_META_DATA
        val checkList = mutableListOf(
            PackageManager.GET_ACTIVITIES,
            PackageManager.GET_CONFIGURATIONS,
            PackageManager.GET_GIDS,
            PackageManager.GET_INSTRUMENTATION,
            PackageManager.GET_META_DATA,
            PackageManager.GET_PERMISSIONS,
            PackageManager.GET_PROVIDERS,
            PackageManager.GET_RECEIVERS,
            PackageManager.GET_SERVICES,
            PackageManager.GET_SHARED_LIBRARY_FILES,
            PackageManager.GET_URI_PERMISSION_PATTERNS,
        )
        if (isAtLeastAndroid24()) {
            checkList + listOf(
                PackageManager.MATCH_UNINSTALLED_PACKAGES,
                PackageManager.MATCH_DISABLED_COMPONENTS,
                PackageManager.MATCH_DISABLED_UNTIL_USED_COMPONENTS,
                PackageManager.MATCH_SYSTEM_ONLY
            )
        }
        if (isAtLeastAndroid28()) {
            checkList.add(PackageManager.GET_SIGNING_CERTIFICATES)
        }
        if (isAtLeastAndroid29()) {
            checkList.add(PackageManager.MATCH_APEX)
        }
        if (isAtLeastAndroid31()) {
            checkList.add(PackageManager.GET_ATTRIBUTIONS)
        }
        if (!checkList.contains(flags)) {
            result.success(null)
            return
        }
        result.success(
            packageManager.getInstalledPackages(flags).map {
                it.toMap()
            }
        )
    }

    private fun getInstantAppCookie(result: Result) {
        if (!isAtLeastAndroid26()) {
            result.success(null)
            return
        }
        result.success(packageManager.instantAppCookie)
    }

    private fun getInstantAppCookieMaxBytes(result: Result) {
        if (!isAtLeastAndroid26()) {
            result.success(null)
            return
        }
        result.success(packageManager.instantAppCookieMaxBytes)
    }

    private fun getPermissionInfo(call: MethodCall, result: Result) {
        val permName = call.argument<String>("permName")
        if (permName == null) {
            result.success(null)
            return
        }
        try {
            val permissionInfo = packageManager.getPermissionInfo(permName, PackageManager.GET_META_DATA)
            with (permissionInfo) {
                val baseResult = mapOf(
                    "descriptionRes" to this.descriptionRes,
                    "flags" to this.flags,
                    "group" to this.group,
                    "nonLocalizedDescription" to this.nonLocalizedDescription
                )
                if (isAtLeastAndroid28()) {
                    result.success(
                        baseResult + mapOf(
                            "protection" to this.protection,
                            "protectionFlags" to this.protectionFlags
                        )
                    )
                } else {
                    result.success(baseResult)
                }
            }
        } catch (ex: PackageManager.NameNotFoundException) {
            result.error(ex.javaClass.name, ex.message, null)
        }
    }

    private fun sendDrawableResult(drawable: Drawable?, result: Result) {
        drawable?.let {
            (it as BitmapDrawable).bitmap.run {
                val imageBytes = ByteArrayOutputStream().use { o ->
                    this.compress(Bitmap.CompressFormat.PNG, 100, o)
                    o.toByteArray()
                }
                result.success(
                    imageBytes
                )
            }
        } ?: run {
            result.success(null)
        }
    }
}
