package br.edu.ufsj.claviculario.util;

import java.sql.Timestamp;
import java.time.Instant;

public class Timestamps {
    public static Timestamp obterMomentoAtual() {
        return Timestamp.from(Instant.now());
    }
}
