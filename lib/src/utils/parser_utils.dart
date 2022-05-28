List<T>? asTypedList<T>(dynamic data,) {
  if (data is List) {
    return data.whereType<T>().toList();
  }
  return null;
}

bool containsBit(int data, int bit,) {
  return data ^ bit == bit;
}