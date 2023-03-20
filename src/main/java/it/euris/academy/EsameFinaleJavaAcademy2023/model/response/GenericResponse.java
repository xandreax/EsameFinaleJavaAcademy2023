package it.euris.academy.EsameFinaleJavaAcademy2023.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {
    private T body;
    private String errorMsg;
}
