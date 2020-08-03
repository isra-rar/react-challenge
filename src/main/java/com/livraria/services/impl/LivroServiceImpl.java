package com.livraria.services.impl;

import com.livraria.dto.LivroDTO;
import com.livraria.entities.Livro;
import com.livraria.mappers.LivroMapper;
import com.livraria.repositories.AutorRepository;
import com.livraria.repositories.LivroRepository;
import com.livraria.services.LivroService;
import com.livraria.services.excepctions.DataIntegrityException;
import com.livraria.services.excepctions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl extends GenericServiceImpl<LivroRepository, LivroMapper> implements LivroService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public LivroDTO getById(Long id) {
        Optional<Livro> obj = getRepository().findById(id);
        obj.orElseThrow(() -> new ObjectNotFoundException(
                "Livro não encontrado"));
        return getModelMapper().livroToLivroDTO(obj.get());
    }

    @Override
    public List<LivroDTO> getAll() {
        List<Livro> obj = getRepository().findAll();
        return getModelMapper().listLivroTOListLivroDTO(obj);
    }

    @Override
    public LivroDTO insert(LivroDTO objDto) {
        Livro livro;
        Livro obj = getModelMapper().livroDtoToLivro(objDto);
        obj.setId(null);
        if(autorRepository.findAutorByNome(obj.getAutor().getNome()) == null){
            livro = getRepository().save(obj);

        } else if (getRepository().existsLivroByTitulo(obj.getTitulo()) != null){
            throw new DataIntegrityException("Titulo já cadastrado");
        }
        else {
            obj.setAutor(autorRepository.findAutorByNome(obj.getAutor().getNome()));
            livro = getRepository().save(obj);
        }
        return getModelMapper().livroToLivroDTO(livro);
    }

    @Override
    public void update(LivroDTO objDto, Long id) {
        LivroDTO livroDTO = getById(id);
        updateData(livroDTO, objDto);
        Livro livro = getModelMapper().livroDtoToLivro(livroDTO);
        getRepository().save(livro);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        try {
            getRepository().deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um LIVRO que possua associações");
        }
    }

    @Override
    public Page<Livro> findPage(Integer page, Integer linesPorPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPorPage, Sort.Direction.valueOf(direction), orderBy);
        return getRepository().findByLivro(pageRequest);
    }

    private void updateData(LivroDTO newObj, LivroDTO obj) {
        newObj.setTitulo(obj.getTitulo());
        newObj.setCategoria(obj.getCategoria());
        newObj.setValor(obj.getValor());
        newObj.setQuantidade(obj.getQuantidade());
        newObj.setAutor(obj.getAutor());
    }
}
