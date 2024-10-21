CREATE TABLE juego (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    genero VARCHAR(120) NOT NULL,
    compania VARCHAR(150) NOT NULL,
    plataforma VARCHAR(100) NOT NULL,
    precio DECIMAL(5,2) NOT NULL,
    descargas INT NOT NULL,
    fecha_lanzamiento DATE,
    tipo ENUM('BASE', 'EXPANSION', 'DLC') DEFAULT 'BASE',
    completado BOOLEAN NOT NULL DEFAULT false
);
