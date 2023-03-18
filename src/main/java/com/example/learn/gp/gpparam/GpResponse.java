package com.example.learn.gp.gpparam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpResponse {

    private int code;
    private String message;
    private Object data;


}
