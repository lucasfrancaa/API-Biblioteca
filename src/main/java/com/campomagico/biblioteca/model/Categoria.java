package com.campomagico.biblioteca.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "É necessário digitar um nome")
    @Length(min = 3,  max = 50, message = "O campo 'nome' deve ter de 3 a 50 caracteres")
    private String nome;
    @NotEmpty(message = "É necessário digitar a descrição")
    @Length(min = 10, max = 500, message = "O campo 'descrição' deve ter entre 10 a 500 caracteres")
    private String descricao;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

	public Categoria(Object object, String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id.equals(categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
    
    
}
