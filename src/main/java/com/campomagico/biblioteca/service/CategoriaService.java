package com.campomagico.biblioteca.service;

import com.campomagico.biblioteca.dto.CategoriaDTO;
import com.campomagico.biblioteca.model.Categoria;
import com.campomagico.biblioteca.repository.CategoriaRepository;
import com.campomagico.biblioteca.service.exceptions.DataIntegrityViolationException;
import com.campomagico.biblioteca.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria findById(Long id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", tipo: " + Categoria.class.getName()));
    }
        public List<Categoria> findAll(){
            return (List<Categoria>) categoriaRepository.findAll();
    }

    public Categoria create (Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Long id, CategoriaDTO categoriaDTO) {
        Categoria obj = findById(id);
        obj.setNome(categoriaDTO.getNome());
        obj.setDescricao(categoriaDTO.getDescricao());
        return categoriaRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        categoriaRepository.deleteById(id);

        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new com.campomagico.biblioteca.service.exceptions.DataIntegrityViolationException("Ops, a categoria não pode ser deletada, existe livros associados.");
        }
    }
}
