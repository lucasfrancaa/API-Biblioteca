package com.campomagico.biblioteca.service;

import com.campomagico.biblioteca.model.Categoria;
import com.campomagico.biblioteca.model.Livro;
import com.campomagico.biblioteca.repository.LivroRepository;
import com.campomagico.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Long id){
        Optional<Livro> obj = livroRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", tipo: " + Livro.class.getName()));
    }

    public List<Livro> findAll(Long id_cat) {
        categoriaService.findById(id_cat);
        return livroRepository.findAllByCategoria(id_cat);
    }

    public Livro update(Long id, Livro obj) {
        Livro newObj = findById(id);
        updateData(newObj, obj);
        return livroRepository.save(newObj);
    }

    private void updateData(Livro newObj, Livro obj) {
        newObj.setTitulo((obj.getTitulo()));
        newObj.setAutor(obj.getAutor());
        newObj.setSinopse(obj.getSinopse());
    }

    public Livro create(Long id_cat, Livro obj) {
        obj.setId(null);
        Categoria categoria = categoriaService.findById(id_cat);
        obj.setCategoria(categoria);
        return livroRepository.save(obj);
    }

    public void delete(Long id) {
        Livro obj = findById(id);
        livroRepository.delete(obj);
    }
}
