// screens/home_screen.dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../providers/auth_provider.dart';
import 'capture_photo_screen.dart';
import 'view_photos_screen.dart';
import 'login_screen.dart';

class HomeScreen extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    // Verificamos el estado del authProvider para navegar según corresponda
    final isAuthenticated = ref.watch(authProvider);

    // Si no está autenticado, lo redirigimos al login
    if (!isAuthenticated) {
      Future.microtask(() {
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(builder: (_) => LoginScreen()),
        );
      });
    }

    return Scaffold(
      appBar: AppBar(
        title: Text('Home'),
        actions: [
          IconButton(
            onPressed: () {
              ref.read(authProvider.notifier).state = false;

              // Navegar al LoginScreen cuando el estado es false
              Navigator.pushReplacement(
                context,
                MaterialPageRoute(builder: (_) => LoginScreen()),
              );
            },
            icon: Icon(Icons.logout),
          ),
        ],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () => Navigator.push(
                context,
                MaterialPageRoute(builder: (_) => CapturePhotoScreen()),
              ),
              child: Text('Capture Photo'),
            ),
            ElevatedButton(
              onPressed: () => Navigator.push(
                context,
                MaterialPageRoute(builder: (_) => ViewPhotosScreen()),
              ),
              child: Text('View Photos'),
            ),
          ],
        ),
      ),
    );
  }
}
