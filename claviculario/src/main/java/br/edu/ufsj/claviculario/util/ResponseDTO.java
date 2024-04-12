package br.edu.ufsj.claviculario.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO<T> {
    String message;
    T detail;
}