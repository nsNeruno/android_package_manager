class PackageItemInfo {

  PackageItemInfo(Map<String, dynamic> data,)
      : name = data['name'],
        packageName = data['packageName'],
        labelRes = data['labelRes'],
        nonLocalizedLabel = data['nonLocalizedLabel'],
        icon = data['icon'],
        banner = data['banner'],
        logo = data['logo'],
        metaData = _parseMetaData(data['metaData'],);

  static Map<String, dynamic>? _parseMetaData(dynamic data,) {
    if (data is Map) {
      return Map<String, dynamic>.from(data,);
    }
    return null;
  }

  final String? name;
  final String? packageName;
  final int? labelRes;
  final String? nonLocalizedLabel;
  final int? icon;
  final int? banner;
  final int? logo;
  final Map<String, dynamic>? metaData;
}