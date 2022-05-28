import '../base/provider_info.dart';

class ProviderInfoImpl extends ProviderInfo {

  ProviderInfoImpl(Map<String, dynamic> data,): super(
    authority: data['authority'],
    readPermission: data['readPermission'],
    writePermission: data['writePermission'],
    grantUriPermission: data['grantUriPermission'] ?? false,
    multiProcess: data['multiprocess'] ?? false,
    initOrder: data['initOrder'],
    flags: data['flags'],
    forceUriPermissions: data['forceUriPermissions'] ?? false,
    componentInfo: data,
  );
}