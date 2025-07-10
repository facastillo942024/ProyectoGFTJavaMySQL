1. Obtener los nombres de los clientes que tienen inscrito algún producto disponible solo en
las sucursales que visitan =>

SELECT DISTINCT c.nombre
FROM cliente c
JOIN inscripcion i ON c.idCliente = i.idCliente
WHERE NOT EXISTS (
    SELECT 1
    FROM disponibilidad d
    WHERE d.idProducto = i.idProducto
    AND d.idSucursal NOT IN (
        SELECT v.idSucursal
        FROM visitan v
        WHERE v.idCliente = c.idCliente
    )
);

-- CONSULTA OPTIMIZADA SEGUN ENTREVISTA CON EL INGENIERO DIA 10 DE JULIO 10:30 AM =>
SELECT DISTINCT c.nombre
FROM cliente c 
JOIN inscripcion i ON c.idCliente = i.idCliente 
JOIN disponibilidad d ON i.idProducto = d.idProducto
LEFT JOIN visitan v ON d.idSucursal = v.idSucursal
AND v.idCliente = c.idCliente
GROUP BY c.idCliente, c.nombre, i.idProducto
HAVING COUNT(d.idSucursal) = COUNT(v.idSucursal);
--------------------------------------------------------------------------------------
 


=> ¿Qué hace esta consulta?

1. Recorre todos los clientes que tienen productos inscritos.

2. Por cada producto, verifica si hay alguna sucursal donde ese producto está disponible que el cliente NO haya visitado.

3. Si no existe ninguna (es decir, todas las sucursales con el producto han sido visitadas por el cliente), entonces se incluye el nombre del cliente.