package com.livraria.services.impl;

import com.livraria.controllers.GenericController;
import com.livraria.dto.AutorDTO;
import com.livraria.dto.ClienteDTO;
import com.livraria.dto.LivroDTO;
import com.livraria.entities.Autor;
import com.livraria.entities.Cliente;
import com.livraria.entities.Livro;
import com.livraria.mappers.AutorMapper;
import com.livraria.mappers.LivroMapper;
import com.livraria.repositories.AutorRepository;
import com.livraria.repositories.LivroRepository;
import com.livraria.services.AutorService;
import com.livraria.services.excepctions.DataIntegrityException;
import com.livraria.services.excepctions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl extends GenericServiceImpl<AutorRepository, AutorMapper> implements AutorService {


    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroMapper livroMapper;

    @Override
    public AutorDTO getById(Long id) {
        Optional<Autor> obj = getRepository().findById(id);
        obj.orElseThrow(() -> new ObjectNotFoundException(
                "Autor não encontrado"));
        return getModelMapper().autorToAutorDTO(obj.get());
    }

    @Override
    public List<AutorDTO> getAll() {
        List<Autor> obj = getRepository().findAll();
        return getModelMapper().listAutorTOListAutorDTO(obj);
    }

    @Override
    public AutorDTO insert(AutorDTO objDto) {
        Autor obj = getModelMapper().autorDtoToAutor(objDto);
        obj.setId(null);
        Autor autor = getRepository().save(obj);
        return getModelMapper().autorToAutorDTO(autor);
    }

    @Override
    public void update(AutorDTO objDto, Long id) {
        AutorDTO autorDTO = getById(id);
        updateData(autorDTO, objDto);
        Autor auto = getModelMapper().autorDtoToAutor(autorDTO);
        getRepository().save(auto);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        try {
            getRepository().deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um AUTOR que possua associações");
        }
    }

    @Override
    public Page<Autor> findPage(Integer page, Integer linesPorPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPorPage, Sort.Direction.valueOf(direction), orderBy);
        return getRepository().findByAutor(pageRequest);
    }

    @Override
    public List<LivroDTO> getLivrosByAutor(Long id) {
        List<Livro> livros = livroRepository.findLivrosByAutor(id);
        return livroMapper.listLivroTOListLivroDTO(livros);
    }


    private void updateData(AutorDTO newObj, AutorDTO obj) {
        newObj.setNome(obj.getNome());
    }

}
