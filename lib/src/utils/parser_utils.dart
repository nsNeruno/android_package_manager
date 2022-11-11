List<T>? asTypedList<T>(dynamic data,) {
  if (data is List) {
    return data.whereType<T>().toList();
  }
  return null;
}

List<T>? safeListParse<T>(dynamic data, T Function(Map<String, dynamic> data) mapper, {
  bool growable = false,
}) {
  if (data is List) {
    final mapped = <T>[];
    for (var e in data) {
      final map = safeInferAsMap(e,);
      if (map != null) {
        mapped.add(mapper(map),);
      }
    }
    return mapped.toList(growable: growable,);
  }
  return null;
}

bool containsBit(int data, int bit,) {
  return data ^ bit == bit;
}

Map<String, dynamic>? safeInferAsMap(dynamic data) {
  if (data is Map) {
    final copy = <String, dynamic>{};
    data.forEach(
      (key, value) {
        if (key is String) {
          copy[key] = value;
        }
      },
    );
    return copy;
  }
  return null;
}

T? safeMapParse<T>(dynamic data, T Function(Map<String, dynamic> data) parser,) {
  final map = safeInferAsMap(data,);
  if (map != null) {
    return parser(map,);
  }
  return null;
}