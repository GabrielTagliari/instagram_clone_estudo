package com.parse.starter.utils;

import java.util.HashMap;

/**
 * Created by gabriel on 8/6/17.
 */

public class ParseErros {

    private HashMap<Integer, String> erros;

    public ParseErros() {
        this.erros = new HashMap<>();
        this.erros.put(201, "Preencher a senha");
        this.erros.put(202, "Usuário já existe, escolha outro nome de usuário");
    }

    public String getErro(int codErro) {
        return this.erros.get(codErro);
    }
}
