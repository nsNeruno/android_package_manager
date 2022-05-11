package lab.neruno.android_package_manager

import android.content.pm.*
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.RequiresApi

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.LOLLIPOP_MR1)
private fun isAtLeastAndroid22(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
}

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

fun ActivityInfo.toMap(): Map<String, Any?> {
    val baseMap = mutableMapOf<String, Any?>(
        "configChanges" to configChanges,
        "documentLaunchMode" to documentLaunchMode,
        "flags" to flags,
        "launchMode" to launchMode,
        "maxRecents" to maxRecents,
        "parentActivityName" to parentActivityName,
        "permission" to permission,
        "persistableMode" to persistableMode,
        "screenOrientation" to screenOrientation,
        "softInputMode" to softInputMode,
        "targetActivity" to targetActivity,
        "taskAffinity" to taskAffinity,
        "theme" to theme,
        "themeResource" to themeResource,
        "uiOptions" to uiOptions,
    )
    if (isAtLeastAndroid24()) {
        baseMap["windowLayout"] = windowLayout.toMap()
    }
    if (isAtLeastAndroid26()) {
        baseMap["colorMode"] = colorMode
    }
    return baseMap
}

fun ApplicationInfo.toMap(): Map<String, Any?> {
    val baseMap = mutableMapOf(
        "packageName" to packageName,
        "name" to name,
        "backupAgentName" to backupAgentName,
        "className" to className,
        "compatibleWidthLimitDp" to compatibleWidthLimitDp,
        "dataDir" to dataDir,
        "descriptionRes" to descriptionRes,
        "enabled" to enabled,
        "flags" to flags,
        "largestWidthLimitDp" to largestWidthLimitDp,
        "manageSpaceActivityName" to manageSpaceActivityName,
        "nativeLibraryDir" to nativeLibraryDir,
        "permission" to permission,
        "processName" to processName,
        "publicSourceDir" to publicSourceDir,
        "requiresSmallestWidthDp" to requiresSmallestWidthDp,
        "sharedLibraryFiles" to sharedLibraryFiles?.toList(),
        "sourceDir" to sourceDir,
        "splitPublicSourceDirs" to splitPublicSourceDirs?.toList(),
        "splitSourceDirs" to splitSourceDirs?.toList(),
        "targetSdkVersion" to targetSdkVersion,
        "taskAffinity" to taskAffinity,
        "theme" to theme,
        "uiOptions" to uiOptions,
        "uid" to uid
    )

    if (isAtLeastAndroid24()) {
        baseMap += mapOf(
            "deviceProtectedDataDir" to deviceProtectedDataDir,
            "minSdkVersion" to minSdkVersion
        )
    }

    if (isAtLeastAndroid26()) {
        baseMap += mapOf(
            "category" to category,
            "splitNames" to splitNames?.toList(),
            "storageUuid" to storageUuid.toString()
        )
    }

    if (isAtLeastAndroid27()) {
        baseMap += mapOf(
            "isVirtualPreload" to isVirtualPreload
        )
    }

    if (isAtLeastAndroid28()) {
        baseMap += mapOf(
            "appComponentFactory" to appComponentFactory,
        )
    }

    if (isAtLeastAndroid29()) {
        baseMap += mapOf(
            "isProfileableByShell" to isProfileableByShell,
            "isResourceOverlay" to isResourceOverlay
        )
    }

    if (isAtLeastAndroid30()) {
        baseMap += mapOf(
            "gwpAsanMode" to gwpAsanMode
        )
    }

    if (isAtLeastAndroid31()) {
        baseMap += mapOf(
            "compileSdkVersion" to compileSdkVersion,
            "compileSdkVersionCodename" to compileSdkVersionCodename,
            "isProfileable" to isProfileable,
            "memtagMode" to memtagMode,
            "nativeHeapZeroInitialized" to nativeHeapZeroInitialized,
            "requestRawExternalStorageAccess" to requestRawExternalStorageAccess,
            "areAttributionsUserVisible" to areAttributionsUserVisible()
        )
    }

    return baseMap.toMap()
}

@RequiresApi(Build.VERSION_CODES.S)
fun Attribution.toMap(): Map<String, Any?> = mapOf(
    "label" to label,
    "tag" to tag
)

fun ConfigurationInfo.toMap(): Map<String, Any?> = mapOf(
    "glEsVersion" to glEsVersion,
    "reqGlEsVersion" to reqGlEsVersion,
    "reqInputFeatures" to reqInputFeatures,
    "reqKeyboardType" to reqKeyboardType,
    "reqNavigation" to reqNavigation,
    "reqTouchScreen" to reqTouchScreen
)

fun FeatureInfo.toMap(): Map<String, Any?> = mapOf(
    "flags" to flags,
    "glEsVersion" to glEsVersion,
    "name" to name,
    "reqGlEsVersion" to reqGlEsVersion,
    "version" to if (isAtLeastAndroid24()) version else null,
)

@RequiresApi(Build.VERSION_CODES.R)
fun InstallSourceInfo.toMap(): Map<String, Any?> = mapOf(
    "initiatingPackageName" to initiatingPackageName,
    "installingPackageName" to installingPackageName,
    "originatingPackageName" to originatingPackageName,
    "initiatingPackageSigningInfo" to initiatingPackageSigningInfo?.run {
        mapOf(
            "apkContentSigners" to apkContentsSigners.toList().map {
                it.toByteArray()
            },
            "signingCertificateHistory" to signingCertificateHistory.toList().map {
                it.toByteArray()
            },
            "hasMultipleSigners" to hasMultipleSigners(),
            "hasPastSigningCertificates" to hasPastSigningCertificates()
        )
    }
)

fun InstrumentationInfo.toMap(): Map<String, Any?> {
    val baseMap = mutableMapOf(
        "dataDir" to dataDir,
        "functionalTest" to functionalTest,
        "handleProfiling" to handleProfiling,
        "publicSourceDir" to publicSourceDir,
        "sourceDir" to sourceDir,
        "splitPublicSourceDirs" to splitPublicSourceDirs.toList(),
        "splitSourceDirs" to splitSourceDirs.toList(),
        "targetPackage" to targetPackage,
    )

    if (isAtLeastAndroid26()) {
        baseMap += mapOf(
            "splitNames" to splitNames.toList(),
            "targetProcesses" to targetProcesses
        )
    }
    return baseMap
}

@RequiresApi(Build.VERSION_CODES.Q)
fun ModuleInfo.toMap(): Map<String, Any?> = mapOf(
    "isHidden" to isHidden,
    "name" to name.toString(),
    "packageName" to packageName
)

fun PackageInfo.toMap(): Map<String, Any?> {
    val baseMap = mutableMapOf(
        "activities" to activities.toList().map {
            it.toMap()
        },
        "applicationInfo" to applicationInfo.toMap(),
        "configPreferences" to configPreferences.map {
            it.toMap()
        },
        "featureGroups" to featureGroups.toList().map {
            it.features.toList().map {
                it.toMap()
            }
        },
        "firstInstallTime" to firstInstallTime,
        "gids" to gids.toList(),
        "installLocation" to installLocation,
        "instrumentation" to instrumentation.map {
            it.toMap()
        },
        "lastUpdateTime" to lastUpdateTime,
        "packageName" to packageName,
        "permissions" to permissions.map {
            it.toMap()
        },
        "providers" to providers.map {
            it.toMap()
        },
        "receivers" to receivers.map {
            it.toMap()
        },
        "reqFeatures" to reqFeatures.map {
            it.toMap()
        },
        "requestedPermissions" to requestedPermissions.toList(),
        "requestedPermissionsFlags" to requestedPermissionsFlags.toList(),
        "services" to services.map {
            it.toMap()
        },
        "sharedUserId" to sharedUserId,
        "sharedUserLabel" to sharedUserLabel,
        "splitNames" to splitNames.toList(),
        "versionName" to versionName
    )

    if (isAtLeastAndroid22()) {
        baseMap += mapOf(
            "baseRevisionCode" to baseRevisionCode,
            "splitRevisionCodes" to splitRevisionCodes
        )
    }

    if (isAtLeastAndroid28()) {
        baseMap += mapOf(
            "longVersionCode" to longVersionCode,
            "signingInfo" to signingInfo.toMap()
        )
    }

    if (isAtLeastAndroid29()) {
        baseMap += Pair("isApex", isApex)
    }

    if (isAtLeastAndroid31()) {
        baseMap += Pair(
            "attributions",
            attributions?.map {
                it.toMap()
            }
        )
    }

    return baseMap
}

fun PermissionGroupInfo.toMap(): Map<String, Any?> = mapOf(
    "descriptionRes" to descriptionRes,
    "flags" to flags,
    "nonLocalizedDescription" to nonLocalizedDescription,
    "priority" to priority
)

fun PermissionInfo.toMap(): Map<String, Any?> {
    val baseResult = mutableMapOf(
        "descriptionRes" to this.descriptionRes,
        "flags" to this.flags,
        "group" to this.group,
        "nonLocalizedDescription" to this.nonLocalizedDescription
    )

    if (isAtLeastAndroid28()) {
        baseResult += mapOf(
            "protection" to this.protection,
            "protectionFlags" to this.protectionFlags
        )
    }
    return baseResult
}

fun ProviderInfo.toMap(): Map<String, Any?> {
    val baseMap = mutableMapOf(
        "authority" to authority,
        "flags" to flags,
        "grantUriPermissions" to grantUriPermissions,
        "initOrder" to initOrder,
        "multiprocess" to multiprocess,
        "pathPermissions" to pathPermissions,
        "readPermission" to readPermission,
        "uriPermissionPatterns" to uriPermissionPatterns,
        "writePermission" to writePermission
    )
    if (isAtLeastAndroid29()) {
        baseMap += mapOf(
            "forceUriPermissions" to forceUriPermissions
        )
    }

    return baseMap
}

fun ServiceInfo.toMap(): Map<String, Any?> = mapOf(
    "flags" to flags,
    "permission" to permission,
    "foregroundServiceType" to if (isAtLeastAndroid29()) foregroundServiceType else null
)

@RequiresApi(Build.VERSION_CODES.P)
fun SigningInfo.toMap(): Map<String, Any?> = mapOf(
    "signingCertificateHistory" to signingCertificateHistory.map {
        it.toByteArray()
    },
    "apkContentsSigners" to apkContentsSigners.map {
        it.toByteArray()
    },
    "hasPastSigningCertificates" to hasPastSigningCertificates(),
    "hasMultipleSigners" to hasMultipleSigners()
)

@RequiresApi(Build.VERSION_CODES.N)
fun ActivityInfo.WindowLayout.toMap(): Map<String, Any?> = mapOf(
    "gravity" to gravity,
    "height" to height,
    "heightFraction" to heightFraction,
    "minHeight" to minHeight,
    "minWidth" to minWidth,
    "width" to width,
    "widthFraction" to widthFraction
)