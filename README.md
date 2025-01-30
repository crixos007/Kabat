# Kabat

# Back End
ejecutar el jar con java - jar PhotosApi-1.0-SNAPSHOT.jar
la documentacion de los metodos se puede ver en http://localhost:8000/swagger-ui/index.html#/

# Front
ejecutar la clase main.dart en android studio o Visual Studio Code

# BD posgresql
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
	pass VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE photos (
    id SERIAL PRIMARY KEY,
    urlPhoto VARCHAR(100) NOT NULL,
    titulo VARCHAR(100) UNIQUE NOT NULL,
	descripcion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
