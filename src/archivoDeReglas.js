expert.newRule("edad_adecuada")
    .ifGreater("edad", 17)
    .andLess("edad", 70)
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

expert.newRule("eres_empatico")
    .ifEquals("me_odias", "n")
    .thenEquals("eres_empatico", "s")
    .build();

expert.newRule("eres_empatico")
    .ifEquals("sabes_como_me_siento", "s")
    .thenEquals("eres_empatico", "s")
    .build();

expert.newRule("te_rindes_facilmente")
    .andEquals("aceptas_un_reto", "s")
    .andEquals("rindete", "n")
    .andEquals("vamos_rindete", "n")
    .andEquals("por_ultima_vez_rindete", "n")
    .thenEquals("te_rindes_facilmente", "n")
    .build();

expert.newRule("emocionalmente_estable")
    .ifEquals("te_rindes_facilmente", "n")
    .andEquals("eres_empatico","s")
    .thenEquals("emocionalmente_estable", "s")
    .build();

expert.newRule("emocionalmente_estable")
    .ifEquals("tienes_amigos", "s")
    .andEquals("te_quieres","s")
    .thenEquals("emocionalmente_estable", "s")
    .build();

expert.newRule("emprendedor")
    .ifEquals("tienes_negocio_propio", "s")
    .thenEquals("emprendedor", "s")
    .build();

expert.newRule("negocio_rentable")
    .ifEquals("tienes_negocio_propio", "s")
    .andEquals("tu_negocio_da_plata", "s")
    .andEquals("tu_negocio_tiene_futuro", "s")
    .andGreater("#_meses_antiguedad_negocio", 24)
    .thenEquals("negocio_rentable", "s")
    .build();

expert.newRule("ingresos_estables")
    .ifEquals("emprendedor", "s")
    .andEquals("emocionalmente_estable", "s")
    .andEquals("negocio_rentable", "s")
    .thenEquals("ingresos_estables", "s")
    .build();

expert.newRule("ingresos_estables")
    .ifEquals("trabajas_en_una_empresa", "s")
    .andGreater("#_meses_antiguedad_trabajo", 17)
    .andEquals("tienes_carta_de_recomendacion", "s")
    .thenEquals("ingresos_estables", "s")
    .build();

expert.newRule("ingresos_estables")
    .ifEquals("trabajas_en_una_empresa", "s")
    .andGreater("#_meses_antiguedad_trabajo", 17)
    .andEquals("te_gusta_tu_trabajo", "s")
    .thenEquals("ingresos_estables", "s")
    .build();

expert.newRule("ingresos_estables")
    .ifEquals("trabajas_en_una_empresa", "s")
    .andGreater("#_meses_antiguedad_trabajo", 17)
    .andEquals("tu_jefe_hablaria_bien_de_ti", "s")
    .thenEquals("ingresos_estables", "s")
    .build();

expert.newRule("ingresos_estables")
    .ifEquals("eres_inversor", "s")
    .andEquals("ganas_mucho_invirtiendo", "s")
    .andEquals("tienes_muchos_activos", "s")
    .andGreater("#_meses_antiguedad_invirtiendo", 24)
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

expert.newRule("esta_loco")
    .ifEquals("ves_fantasmas", "n")
    .ifEquals("escuchas_voces", "n")
    .thenEquals("esta_loco", "n")
    .build();

expert.newRule("es_inteligente")
    .ifEquals("1_+_1", 2)
    .andEquals("esta_loco", "n")
    .thenEquals("es_inteligente", "s")
    .build();

expert.newRule("apto_para_evaluacion")
    .ifEquals("apto_para_simulacion", "s")
    .andEquals("cuotas_pagables", "s")
    .andEquals("es_inteligente", "s")
    .thenEquals("apto_para_evaluacion", "s")
    .build();

expert.newRule("moroso")
    .ifEquals("prestamos_anteriores", "s")
    .andEquals("alguna_vez_te_has_atrasado", "n")
    .thenEquals("moroso", "n")
    .build();

expert.newRule("moroso")
    .ifEquals("prestamos_anteriores", "s")
    .andEquals("alguna_vez_te_has_atrasado", "s")
    .thenEquals("moroso", "s")
    .build();

expert.newRule("moroso")
    .ifEquals("prestamos_anteriores", "n")
    .thenEquals("moroso", "n")
    .build();

expert.newRule("deudas_aceptables")
    .ifEquals("moroso", "n")
    .thenEquals("deudas_aceptables", "s")
    .build()

expert.newRule("deudas_aceptables")
    .ifEquals("moroso", "s")
    .andLess("#_retrasos", 5)
    .andEquals("prometes_ser_buen_pagador", "s")
    .thenEquals("deudas_aceptables", "s")
    .build()

expert.newRule("deudas_aceptables")
    .ifEquals("moroso", "s")
    .andLess("#_retrasos", 5)
    .andEquals("prometes_ser_buen_pagador", "s")
    .thenEquals("deudas_aceptables", "s")
    .build()    

expert.newRule("buen_historial_crediticio")
    .ifEquals("deudas_aceptables", "s")
    .andEquals("has_sido_aval","n")
    .thenEquals("buen_historial_crediticio", "s")
    .build()

expert.newRule("buen_historial_crediticio")
    .ifEquals("deudas_aceptables", "s")
    .andEquals("has_sido_aval","s")
    .andEquals("el_avalado_es_deudor", "n")
    .thenEquals("buen_historial_crediticio", "s")
    .build()    

expert.newRule("buen_historial_crediticio")
    .ifEquals("deudas_aceptables", "s")
    .andEquals("has_sido_aval","s")
    .andEquals("el_avalado_es_deudor", "s")
    .andEquals("el_avalado_sigue_endeudado", "n")
    .thenEquals("buen_historial_crediticio", "s")
    .build()

expert.newRule("tienes_vicios")
    .ifEquals("vas_al_casino", "n")
    .andEquals("eres_comprador_compulsivo", "n")
    .thenEquals("tienes_vicios", "n")
    .build()

expert.newRule("esperanza_de_vida_larga")
    .ifLess("edad", "35")
    .andEquals("tienes_alguna_enfermedad_terminal", "n")
    .thenEquals("esperanza_de_vida_larga", "s")
    .build()

expert.newRule("esperanza_de_vida_larga")
    .ifLess("edad", "60")
    .andEquals("tienes_alguna_enfermedad_terminal", "n")
    .andEquals("te_duele_el_cuerpo_frecuentemente","n")
    .thenEquals("esperanza_de_vida_larga", "s")
    .build()

expert.newRule("esperanza_de_vida_larga")
    .ifLess("edad", "60")
    .andEquals("tienes_alguna_enfermedad_terminal", "n")
    .andEquals("te_duele_el_cuerpo_frecuentemente","s")
    .andEquals("te_has_hecho_un_chequeo","s")
    .andEquals("estas_completamente_sano","s")
    .thenEquals("esperanza_de_vida_larga", "s")
    .build()

expert.newRule("esperanza_de_vida_larga")
    .ifLess("edad", "70")
    .andEquals("tienes_alguna_enfermedad_terminal", "n")
    .andEquals("te_duele_el_cuerpo_frecuentemente","s")
    .andEquals("te_has_hecho_un_chequeo","s")
    .andEquals("estas_completamente_sano","s")
    .andEquals("te_chequeas_regularmente","s")
    .thenEquals("esperanza_de_vida_larga", "s")
    .build()

expert.newRule("posible_buen_pagador")
    .ifEquals("tienes_propiedades_inmobiliarias", "s")
    .andGreater("#_de_tus_propiedades",2)
    .andEquals("tienes_vicios", "n")
    .andEquals("esperanza_de_vida_larga", "s")
    .thenEquals("posible_buen_pagador", "s")
    .build()
    
expert.newRule("posible_buen_pagador")
    .ifEquals("tienes_ahorros", "s")
    .andEquals("tus_ahorros_son_mas_de_30000", "s")
    .andEquals("tienes_vicios", "n")
    .andEquals("esperanza_de_vida_larga", "s")
    .thenEquals("posible_buen_pagador", "s")
    .build()

expert.newRule("documentos_correctos")
    .ifLess("dni", "100000000")
    .thenEquals("documentos_correctos", "s")
    .build();

expert.newRule("puede_aperturar_cuenta_corriente")
    .ifEquals("buen_historial_crediticio", "s")
    .andEquals("posible_buen_pagador", "s")
    .thenEquals("puede_aperturar_cuenta_corriente", "s")
    .build();

expert.newRule("puede_aperturar_cuenta_de_ahorros")
    .ifEquals("documentos_correctos", "s")
    .andEquals("edad_adecuada", "s")
    .thenEquals("puede_aperturar_cuenta_de_ahorros", "s")
    .build();

expert.newRule("pre_aprobado")
    .ifEquals("apto_para_evaluacion", "s")
    .andEquals("documentos_correctos", "s")
    .andEquals("buen_historial_crediticio", "s")
    .andEquals("posible_buen_pagador", "s")
    .thenEquals("pre_aprobado", "s")
    .build();