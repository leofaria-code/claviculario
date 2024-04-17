package br.edu.ufsj.claviculario.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO<T> {
    String message;
    T detail;
}