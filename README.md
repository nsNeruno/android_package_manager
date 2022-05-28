# android_package_manager [![Pub](https://img.shields.io/pub/v/android_package_manager.svg)](https://pub.dartlang.org/packages/android_package_manager)

Provides access to Android's native PackageManager API to fetch various information, such as installed applications, packages, permissions, etc.

## About this Package
This plugin is purposed for Android use only. Allowing easier access to most commonly used methods of Android's [PackageManager](https://developer.android.com/reference/android/content/pm/PackageManager) API. Adapted a few methods from existing package [package_manager](https://pub.dev/packages/package_manager) which seems not to be maintained recently.

## Getting Started

Importing:
```dart
import package:android_package_manager/android_package_manager.dart;
```
Singleton access example:
```dart
final pm = AndroidPackageManager();
```

## Available Methods
Please see official documentation of [PackageManager](https://developer.android.com/reference/android/content/pm/PackageManager) API.  
**Disclaimer**: I have been trying to adapt most of the available methods. Done with some simple instrumentation tests.  
Tested methods can be found at
```dart
'example/integration_test/android_package_manager_test.dart'
```
Example Flutter app demonstrates the [getInstalledApplications](https://developer.android.com/reference/android/content/pm/PackageManager#getInstalledApplications(int)) method.

## TO-DOs
- Proper documentation
- DartDocs
- Adjusting some methods to be Android Tiramisu compatible (deprecating some methods)
- Documenting bitmask values
- Separating interfaces into a separate package (maybe)

## Issues
Feel free to file issues or any suggestions [here](https://github.com/nsNeruno/android_package_manager/issues)