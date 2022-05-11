library android_package_manager;

import 'dart:async';

import 'package:flutter/services.dart';

import 'src/entities/entities.dart';
import 'src/entities/entities_impl.dart';

export 'src/entities/entities.dart';

class AndroidPackageManager {
  static const MethodChannel _channel = MethodChannel('android_package_manager',);

  AndroidPackageManager._();

  static const getMetaData = 0x00000080;
  static const getSharedLibraryFiles = 0x00000400;

  static const matchDisabledUntilUsedComponents = 0x00008000;
  static const matchSystemOnly = 0x00100000;
  static const matchUninstalledPackages = 0x00002000;
  static const matchAll = 0x00020000;
  static const matchApex = 0x40000000;

  static late final AndroidPackageManager _pm = AndroidPackageManager._();

  factory AndroidPackageManager() => _pm;

  Future<List<ApplicationInfo>?> getInstalledApplications({
    int flags = getMetaData,
  }) async {
    final installedApplicationsData = await _channel.invokeListMethod(
      "getInstalledApplications",
    );
    return installedApplicationsData?.map(
      (data) => ApplicationInfoImpl(
        Map<String, dynamic>.from(data,),
      ),
    ).toList(growable: false,);
  }
}
