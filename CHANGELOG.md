## 0.3.0

* Raised Target SDK Version to `33` (Android 13 | Codename: `TIRAMISU`)
* Added access to `canPackageQuery` method which is exclusive to Android 13
* Added support for non-deprecated alternatives of some APIs on Android 13
* Raised plugin's Gradle version to `7.2.2`

## 0.2.0

* Fixed `getInstalledPackages` results having null `applicationInfo` (reported issue [here](https://github.com/nsNeruno/android_package_manager/issues/1) | credits to [@pablo8899](https://github.com/pablo8899))
* Optimized platform Map data parsing from Android to Dart
* Updated example's integration test to include check for `applicationInfo` existence after 
querying for packages
* Bumped minimum Dart SDK to `2.18.0`
* Updated target SDK of `example` app to `33`

## 0.1.0

* Initial Release
