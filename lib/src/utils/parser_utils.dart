List<T>? asTypedList<T>(dynamic data,) {
  if (data is List) {
    return data.whereType<T>().toList();
  }
  return null;
}