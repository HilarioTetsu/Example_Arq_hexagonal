package com.tecno.app.activostecnologicos.application.ports.in.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ActivoResponse(UUID id, String folio, String numSerie, String marca, String modelo,
                             String estado, BigDecimal costo, LocalDateTime fechaIngreso, String categoria) {
}
