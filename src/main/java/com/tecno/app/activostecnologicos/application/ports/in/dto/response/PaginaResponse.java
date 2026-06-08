package com.tecno.app.activostecnologicos.application.ports.in.dto.response;

import java.util.List;

public record PaginaResponse<T>(
        List<T> contenido,
        int numeroPagina,
        int tamano,
        long totalElementos,
        int totalPaginas
) {}
