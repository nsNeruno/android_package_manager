import '../base/package_info.dart';
import '../enums.dart';
import 'activity_info.dart';
import 'application_info.dart';
import 'configuration_info.dart';
import 'feature_info.dart';
import 'instrumentation_info.dart';
import 'permission_info.dart';
import 'provider_info.dart';
import 'service_info.dart';

class PackageInfoImpl extends PackageInfo {
  
  PackageInfoImpl(Map<String, dynamic> data,) : super(
    activities: data['activities'] is List
        ? (data['activities'] as List).whereType<Map<String, dynamic>>().map(
          (e) => ActivityInfoImpl(e,),
    ).toList(growable: false,)
        : null,
    applicationInfo: data['applicationInfo'] is Map<String, dynamic>
        ? ApplicationInfoImpl(data['applicationInfo'],)
        : null,
    configPreferences: data['configPreferences'] is List
        ? (data['configPreferences'] as List).whereType<Map<String, dynamic>>().map(
          (e) => ConfigurationInfoImpl(e,),
    ).toList(growable: false,)
        : null,
    featureGroups: data['featureGroups'] is List
        ? (data['featureGroups'] as List).whereType<Map<String, dynamic>>().map(
          (e) => FeatureGroupInfoImpl(e,),
    ).toList(growable: false,)
        : null,
    firstInstallTime: data['firstInstallTime'],
    gids: data['gids'],
    installLocation: _parseInstallLocation(data['installLocation'],),
    instrumentation: data['instrumentation'] is List
        ? (data['instrumentation'] as List).whereType<Map<String, dynamic>>().map(
          (e) => InstrumentationInfoImpl(e,),
    ).toList(growable: false,)
        : null,
    lastUpdateTime: data['lastUpdateTime'],
    packageName: data['packageName'],
    permissions: data['permissions'] is List
        ? (data['permissions'] as List).whereType<Map<String, dynamic>>().map(
          (e) => PermissionInfoImpl(e,),
    ).toList(growable: false,)
        : null,
    providers: data['providers'] is List
        ? (data['providers'] as List).whereType<Map<String, dynamic>>().map(
          (e) => ProviderInfoImpl(e,),
    ).toList(growable: false,)
        : null,
    receivers: data['receivers'] is List
        ? (data['receivers'] as List).whereType<Map<String, dynamic>>().map(
          (e) => ActivityInfoImpl(e,),
    ).toList(growable: false,)
        : null,
    reqFeatures: data['reqFeatures'] is List
        ? (data['reqFeatures'] as List).whereType<Map<String, dynamic>>().map(
          (e) => FeatureInfoImpl(e,),
    ).toList(growable: false,)
        : null,
    requestedPermissions: data['requestedPermissions'],
    requestedPermissionFlags: data['requestedPermissionFlags'],
    services: data['services'] is List
        ? (data['services'] as List).whereType<Map<String, dynamic>>().map(
          (e) => ServiceInfoImpl(e,),
    ).toList(growable: false,)
        : null,
    sharedUserId: data['sharedUserId'],
    sharedUserLabel: data['sharedUserLabel'],
    splitNames: data['splitNames']?.whereType<String>().toList(growable: false,),
    versionName: data['versionName'],
  );

  static AndroidInstallLocation _parseInstallLocation(dynamic data,) {
    if (data is int) {
      AndroidInstallLocation.values.asMap()[data + 1] ?? AndroidInstallLocation.unspecified;
    }
    return AndroidInstallLocation.unspecified;
  }
}