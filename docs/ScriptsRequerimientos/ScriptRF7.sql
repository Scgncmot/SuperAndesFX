--USUAR CON LA BASE DE DATOS VACIA

--Promocion tipo pague n lleve m unidades para crema dental
--Producto ingresado
INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('10893f3ea', 'Crema Dental', 'Tubo','Colgate', '100','cm^3', '100 cm^3');
--Ingreso de la promocion
INSERT INTO PROMOCION(CODIGOPROMOCION, TIPOPROMOCION,FECHATERMINACION) VALUES ('p1',1,TO_DATE('2018/11/10','yyyy/mm/dd'));
INSERT INTO PAGUENUNIDADESLLEVEMPROMO (CODIGOPROMO,COMPRAUNIDADES,LLEVAUNIDADES) VALUES ('p1',5,7);
INSERT INTO PRODUCTOPROMOCION (CODIGOPROMOCION,CODIGOPRODUCTO) VALUES ('p1','10893f3ea');

--Promocion tipo porcentaje de descuento para Shampoo
--Producto ingresado
INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('fc9847d6', 'Shampoo','Botella','Pantene','500','cm3','500 cm3');
--Ingreso de la promocion
INSERT INTO PROMOCION(CODIGOPROMOCION, TIPOPROMOCION,FECHATERMINACION) VALUES ('p2',2,TO_DATE('2018/11/10','yyyy/mm/dd'));
INSERT INTO descporcentajepromo (CODIGOPROMO,porcentajedesc) VALUES ('p2',5);
INSERT INTO PRODUCTOPROMOCION (CODIGOPROMOCION,CODIGOPRODUCTO) VALUES ('p2','fc9847d6');

--Promocion tipo pague x cantidad lleve y cantidad para Crema dental
--Ingreso de la promocion
INSERT INTO PROMOCION(CODIGOPROMOCION, TIPOPROMOCION,FECHATERMINACION) VALUES ('p3',3,TO_DATE('2018/11/15','yyyy/mm/dd'));
INSERT INTO paguexcantidadlleveypromo (CODIGOPROMO,cantidadpaga,cantidadlleva) VALUES ('p3',200,250);
INSERT INTO PRODUCTOPROMOCION (CODIGOPROMOCION,CODIGOPRODUCTO) VALUES ('p3','10893f3ea');

--Promocion tipo pague 1  lleve 2do con desc para Shampoo
--Ingreso de la promocion
INSERT INTO PROMOCION(CODIGOPROMOCION, TIPOPROMOCION,FECHATERMINACION) VALUES ('p4',4,TO_DATE('2018/11/15','yyyy/mm/dd'));
INSERT INTO pague1lleve2condescpromo (CODIGOPROMO,porcentajedesc) VALUES ('p4',20);
INSERT INTO PRODUCTOPROMOCION (CODIGOPROMOCION,CODIGOPRODUCTO) VALUES ('p4','fc9847d6');

--Promocion tipo paquete de productos para Shampoo y crema dental
--Ingresa sucursal
INSERT INTO SUCURSAL (IDSUCURSAL,NOMBRE,SEGMENTACION,TAMANO,CIUDAD,DIRECCION) VALUES (1,'Galerias','Psicografica','Grande','Bucaramanga','Calle 45 # 22-31');
--Producto ingresado
INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('10893f3eb', 'Dentrifico + Shampoo', 'Tubo y caja','Colgate y Pantene', '300','cm^3', '300 cm^3');
--ingresa nuevo valor en la sucursal
INSERT INTO productoofrecidosucursal (idsucursal,codigobarras, preciounitario,preciounidadmedida,niveldereorden,cantidadrecompra) VALUES (1,'10893f3eb',20,1,40,20);
--Ingreso de la promocion
INSERT INTO PROMOCION(CODIGOPROMOCION, TIPOPROMOCION,FECHATERMINACION) VALUES ('p5',5,TO_DATE('2018/11/15','yyyy/mm/dd'));
INSERT INTO PRODUCTOPROMOCION (CODIGOPROMOCION,CODIGOPRODUCTO) VALUES ('p5','10893f3eb');


