// screens/capture_photo_screen.dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:image_picker/image_picker.dart';
import '../models/photo.dart';
import '../providers/photo_provider.dart';
import 'dart:io';

class CapturePhotoScreen extends ConsumerWidget {
  final TextEditingController titleController = TextEditingController();
  final TextEditingController descriptionController = TextEditingController();
  String? imagePath;

  Future<void> pickImage() async {
    final picker = ImagePicker();
    final pickedFile = await picker.pickImage(source: ImageSource.camera);
    if (pickedFile != null) {
      imagePath = pickedFile.path;
    }
  }

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Scaffold(
      appBar: AppBar(title: Text('Capture Photo')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            ElevatedButton(
              onPressed: pickImage,
              child: Text('Take a Photo'),
            ),
            if (imagePath != null) Image.file(File(imagePath!)),
            TextField(
              controller: titleController,
              decoration: InputDecoration(labelText: 'Title'),
            ),
            TextField(
              controller: descriptionController,
              decoration: InputDecoration(labelText: 'Description'),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                if (titleController.text.isNotEmpty && descriptionController.text.isNotEmpty && imagePath != null) {
                  final photo = Photo(
                    title: titleController.text,
                    description: descriptionController.text,
                    imagePath: imagePath!,
                  );
                  // Usar ref.read() para acceder al notifier del provider
                  ref.read(photoProvider.notifier).addPhoto(photo);

                  // Backend service placeholder
                  // TODO: Send photo, title, and description to backend

                  Navigator.pop(context);
                } else {
                  ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(content: Text('Please fill all fields and take a photo!')),
                  );
                }
              },
              child: Text('Submit'),
            ),
          ],
        ),
      ),
    );
  }
}
