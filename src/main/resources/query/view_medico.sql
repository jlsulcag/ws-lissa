SELECT
  b.id_medico,
  (
    (
      (
        (
          (a.nombres)::text || ' '::text
        ) ||
        (a.apellido_paterno)::text
      ) || ' '::text
    ) ||
    (a.apellido_materno)::text
  ) AS fullname,
  a.numero_documento_identidad,
  b.colegiatura,
  c.desc_especialidad,
  d.desc_tipo_medico,
  b.estado
FROM (((persona a
  JOIN medico b ON
    (
      (b.id_persona = a.id_persona)
    ))
  JOIN especialidad c ON
    (
      (b.id_especialidad = c.id_especialidad)
    ))
  JOIN tipo_medico d ON
    (
      (b.id_tipo_medico = d.id_tipo_medico)
    ))
