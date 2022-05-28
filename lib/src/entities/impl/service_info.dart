import '../base/service_info.dart';

class ServiceInfoImpl extends ServiceInfo {

  ServiceInfoImpl(Map<String, dynamic> data,) : super(
    permission: data['permission'],
    flags: data['flags'],
    foregroundServiceType: data['foregroundServiceType'],
    componentInfo: data,
  );
}