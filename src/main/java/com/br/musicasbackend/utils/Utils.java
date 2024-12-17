package com.br.musicasbackend.utils;

import java.text.Normalizer;

public class Utils {
    public String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}
