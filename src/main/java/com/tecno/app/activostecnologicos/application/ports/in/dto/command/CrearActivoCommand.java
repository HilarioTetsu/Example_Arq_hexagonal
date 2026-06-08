package com.tecno.app.activostecnologicos.application.ports.in.dto.command;



import java.math.BigDecimal;

public record CrearActivoCommand(String numSerie, String marca, String modelo, BigDecimal costo, int categoriaId) {
}
