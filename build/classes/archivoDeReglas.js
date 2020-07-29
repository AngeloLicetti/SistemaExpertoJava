expert.newRule("edad_adecuada")
    .ifGreater("#_edad", 17)
    .andLess("#_edad", 70)
    .thenEquals("edad_adecuada", "s")
    .build();
    
expert.newRule("deudas_vigentes")
    .ifEquals("prestamos_anteriores", "n")
    .thenEquals("deudas_vigentes", "n")
    .build();

expert.newRule("deudas_vigentes")
    .ifEquals("prestamos_anteriores", "s")
    .andEquals("prestamos_pagados", "s")
    .thenEquals("deudas_vigentes", "n")
    .build();

expert.newRule("es_empatico")
    .ifEquals("me_odias", "n")
    .thenEquals("es_empatico", "s")
    .build();

expert.newRule("es_empatico")
    .ifEquals("sabes_como_me_siento", "s")
    .thenEquals("es_empatico", "s")
    .build();

expert.newRule("emocionalmente_estable")
    .ifEquals("se_rinde_facilmente", "n")
    .andEquals("es_empatico","s")
    .thenEquals("emocionalmente_estable", "s")
    .build();

expert.newRule("ingresos_estables")
    .ifEquals("emprendedor", "s")
    .andEquals("emocionalmente_estable", "s")
    .andEquals("empresa_rentable", "s")
    .thenEquals("ingresos_estables", "s")
    .build();

expert.newRule("ingresos_estables")
    .ifEquals("trabajador_de_empresa", "s")
    .andGreater("#_meses_antiguedad_trabajo", 17)
    .thenEquals("ingresos_estables", "s")
    .build();

expert.newRule("apto_para_simulacion")
    .ifEquals("edad_adecuada", "s")
    .andEquals("deudas_vigentes", "n")
    .andEquals("ingresos_estables", "s")
    .thenEquals("apto_para_simulacion", "s")
    .build();

expert.newRule("cuotas_pagables")
    .ifEquals("puede_pagar", "s")
    .thenEquals("cuotas_pagables", "s")
    .build();

expert.newRule("apto_para_evaluacion")
    .ifEquals("apto_para_simulacion", "s")
    .andEquals("cuotas_pagables", "s")
    .thenEquals("apto_para_evaluacion", "s")
    .build();

expert.newRule("moroso")
    .ifEquals("prestamos_anteriores", "s")
    .andEquals("pagador_puntual", "s")
    .thenEquals("moroso", "n")
    .build();

expert.newRule("moroso")
    .ifEquals("prestamos_anteriores", "s")
    .andEquals("pagador_puntual", "n")
    .thenEquals("moroso", "s")
    .build();

expert.newRule("moroso")
    .ifEquals("prestamos_anteriores", "n")
    .thenEquals("moroso", "n")
    .build();

expert.newRule("pre_aprobado")
    .ifEquals("apto_para_evaluacion", "s")
    .andEquals("documentos_correctos", "s")
    .andEquals("moroso", "s")
    .andLess("#_retrasos", 5)
    .thenEquals("pre_aprobado", "s")
    .build();

expert.newRule("pre_aprobado")
    .ifEquals("apto_para_evaluacion", "s")
    .andEquals("documentos_correctos", "s")
    .andEquals("moroso", "n")
    .thenEquals("pre_aprobado", "s")
    .build();