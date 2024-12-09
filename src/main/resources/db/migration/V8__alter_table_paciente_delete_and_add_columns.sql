-- Eliminar columnas innecesarias
ALTER TABLE paciente
DROP COLUMN distrito,
DROP COLUMN complemento,
DROP COLUMN numero;

-- Agregar nuevas columnas
ALTER TABLE paciente
ADD COLUMN barrio VARCHAR(100);
