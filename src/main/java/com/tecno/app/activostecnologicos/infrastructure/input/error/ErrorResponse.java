package com.tecno.app.activostecnologicos.infrastructure.input.error;

import java.time.LocalDateTime;

public record ErrorResponse( LocalDateTime timestamp,Integer status,String message){
}
