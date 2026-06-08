package com.tecno.app.activostecnologicos.application.ports.out;

public interface IPasswordHasher {
    String hash(String rawPassword);
}