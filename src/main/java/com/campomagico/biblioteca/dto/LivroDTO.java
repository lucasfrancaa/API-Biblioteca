package com.campomagico.biblioteca.dto;

import com.campomagico.biblioteca.model.Livro;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class LivroDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String titulo;

    public LivroDTO(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public LivroDTO(Livro obj) {
    }
}
