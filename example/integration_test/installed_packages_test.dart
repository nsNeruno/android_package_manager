import 'package:android_package_manager/android_package_manager.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';

void main() {
  IntegrationTestWidgetsFlutterBinding.ensureInitialized();

  test(
    "getInstalledPackages",
    () async {
      final pm = AndroidPackageManager();
      final packages = await pm.getInstalledPackages(
        flags: PackageInfoFlags(
          {
            PMFlag.getMetaData,
            PMFlag.getPermissions,
            PMFlag.getReceivers,
            PMFlag.getServices,
            PMFlag.getProviders,
          },
        ),
      );
      print("BEGIN: getInstalledPackages",);
      print(
        packages?.map(
          (e) {
            e.activities?.forEach(
                  (activity) {
                activity.getIcon().then(
                      (aIcon) {
                    print(
                      'Package ${e.packageName} Icon: ${aIcon?.length} bytes',
                    );
                  },
                );
                activity.getLogo().then(
                        (aLogo) {
                      print(
                        'Package ${e.packageName} Logo: ${aLogo?.length} bytes',
                      );
                    }
                );
              },
            );
            return "${e.packageName} | (${e.applicationInfo != null})\n\tAppInfo.name: ${e.applicationInfo?.name}\n\tRequested Permissions: ${e.requestedPermissions}";
          },
        ).join("\n",),
      );
      print("END: getInstalledPackages",);
      expect(packages != null, true,);
    },
  );
}