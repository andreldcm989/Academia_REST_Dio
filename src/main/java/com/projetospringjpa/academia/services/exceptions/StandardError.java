package com.projetospringjpa.academia.services.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable{

    private Long timestamp;
    private Integer status;
    private String error;
    private String msg;
    private String path;
    
}
