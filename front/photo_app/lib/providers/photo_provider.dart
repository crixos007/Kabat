import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../models/photo.dart';

final photoProvider = StateNotifierProvider<PhotoNotifier, List<Photo>>((ref) => PhotoNotifier());

class PhotoNotifier extends StateNotifier<List<Photo>> {
  PhotoNotifier() : super([]);

  void addPhoto(Photo photo) {
    state = [...state, photo];
  }
}
