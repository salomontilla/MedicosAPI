-- Eliminar columnas innecesarias
ALTER TABLE pacientes
DROP COLUMN distrito,
DROP COLUMN complemento,
DROP COLUMN numero;

-- Agregar nuevas columnas
ALTER TABLE pacientes
ADD COLUMN barrio VARCHAR(100);
