package com.github.maikoncanuto.iubspringboot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseIUB<T extends Serializable> implements Serializable {

    private T data;

}
