DROP DATABASE tecnostore_db;

CREATE DATABASE tecnostore_db;

USE tecnostore_db;

CREATE TABLE marca (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE celulares (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_marca INT NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    sistema_operativo VARCHAR(50) NOT NULL,
    gama ENUM("alta", "media", "baja") NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    FOREIGN KEY (id_marca) REFERENCES marca(id)
);

CREATE TABLE persona (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    identificacion VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    telefono VARCHAR(50) NOT NULL
);

CREATE TABLE clientes (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    FOREIGN KEY (id) REFERENCES persona(id)
);

CREATE TABLE empleados (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    FOREIGN KEY (id) REFERENCES persona(id)
);

CREATE TABLE ventas (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    total DOUBLE NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

CREATE TABLE detalle_ventas (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_venta INT NOT NULL,
    id_celular INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES ventas(id),
    FOREIGN KEY (id_celular) REFERENCES celulares(id)
);

INSERT INTO marca (nombre) VALUES 
('Samsung'),
('Apple'),
('Xiaomi'),
('Motorola'),
('Huawei');

INSERT INTO celulares (id_marca, modelo, sistema_operativo, gama, precio, stock) VALUES
(1, 'Galaxy S24', 'Android', 'alta', 4200000, 10),
(1, 'Galaxy A54', 'Android', 'media', 1800000, 8),
(2, 'iPhone 15 Pro', 'iOS', 'alta', 5500000, 5),
(2, 'iPhone 13', 'iOS', 'media', 3200000, 7),
(3, 'Redmi Note 13', 'Android', 'media', 1200000, 15),
(3, 'Redmi 12C', 'Android', 'baja', 700000, 20),
(4, 'Moto G84', 'Android', 'media', 1400000, 6),
(5, 'P60 Pro', 'HarmonyOS', 'alta', 3900000, 4);


INSERT INTO persona (nombre, identificacion, correo, telefono) VALUES
('Carlos Ramirez', '1001234567', 'carlos@gmail.com', '3001234567'),
('Laura Martinez', '1007654321', 'laura@gmail.com', '3019876543'),
('Andres Gomez', '1011111111', 'andres@gmail.com', '3025555555'),
('Sofia Torres', '1022222222', 'sofia@gmail.com', '3034444444'),
('Miguel Herrera', '1033333333', 'miguel@gmail.com', '3043333333');


INSERT INTO clientes (id) VALUES (1);
INSERT INTO clientes (id) VALUES (2);
INSERT INTO clientes (id) VALUES (3);


INSERT INTO empleados (id) VALUES (4);
INSERT INTO empleados (id) VALUES (5);


INSERT INTO ventas (id_cliente, fecha, total) VALUES
(1, '2025-02-01', 4998000),
(2, '2025-02-03', 1428000),
(3, '2025-02-05', 6545000);

INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES
(1, 1, 1, 4200000),
(1, 6, 2, 1400000),
(2, 7, 1, 1400000),
(3, 3, 1, 5500000),
(3, 2, 1, 1800000);


--Triggers

DELIMITER $$

CREATE TRIGGER calcular_subtotal
BEFORE INSERT ON detalle_ventas
FOR EACH ROW
BEGIN
    DECLARE precioCelular DOUBLE;

    SELECT precio INTO precioCelular
    FROM celulares
    WHERE id = NEW.id_celular;

    SET NEW.subtotal = precioCelular * NEW.cantidad;
END$$
DELIMITER ;

DELIMITER $$

CREATE TRIGGER actualizar_total_venta
AFTER INSERT ON detalle_ventas
FOR EACH ROW
BEGIN
    DECLARE sumaSubtotales DOUBLE;
    DECLARE totalConIVA DOUBLE;

    SELECT SUM(subtotal)
    INTO sumaSubtotales
    FROM detalle_ventas
    WHERE id_venta = NEW.id_venta;

    SET totalConIVA = sumaSubtotales + (sumaSubtotales * 0.19);

    UPDATE ventas
    SET total = totalConIVA
    WHERE id = NEW.id_venta;
END$$
DELIMITER ;

DELIMITER $$

CREATE TRIGGER reducir_stock
AFTER INSERT ON detalle_ventas
FOR EACH ROW
BEGIN
    UPDATE celulares
    SET stock = stock - NEW.cantidad
    WHERE id = NEW.id_celular;
END$$

DELIMITER ;

CREATE TRIGGER actualizar_venta_y_stock
AFTER DELETE ON detalle_ventas
FOR EACH ROW
BEGIN
    DECLARE sumaSubtotales DOUBLE;
    DECLARE totalConIVA DOUBLE;

    UPDATE celulares
    SET stock = stock + OLD.cantidad
    WHERE id = OLD.id_celular;

    SELECT IFNULL(SUM(subtotal), 0)
    INTO sumaSubtotales
    FROM detalle_ventas
    WHERE id_venta = OLD.id_venta;

    SET totalConIVA = sumaSubtotales + (sumaSubtotales * 0.19);

    UPDATE ventas
    SET total = totalConIVA
    WHERE id = OLD.id_venta;

END;





