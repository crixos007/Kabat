import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../providers/photo_provider.dart';
import 'dart:io';

class ViewPhotosScreen extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final photos = ref.watch(photoProvider);

    return Scaffold(
      appBar: AppBar(title: Text('View Photos')),
      body: photos.isEmpty
          ? Center(child: Text('No photos available.'))
          : ListView.builder(
        itemCount: photos.length,
        itemBuilder: (context, index) {
          final photo = photos[index];
          return ListTile(
            leading: Image.file(File(photo.imagePath)),
            title: Text(photo.title),
            subtitle: Text(photo.description),
          );
        },
      ),
    );
  }
}
