package com.marcelo.restaurante.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcelo.restaurante.DTO.GestorDTO;
import com.marcelo.restaurante.enums.PerfilNome;
import com.marcelo.restaurante.model.Gestor;
import com.marcelo.restaurante.model.Perfil;
import com.marcelo.restaurante.model.Usuario;
import com.marcelo.restaurante.repository.GestorRepository;
import com.marcelo.restaurante.repository.PerfilRepository;
import com.marcelo.restaurante.repository.UsuarioRepository;
import com.marcelo.restaurante.util.ResultadoRequisicao;

@Service
public class GestorService {
    @Autowired
    PasswordEncoder senhaEncoder;
	
	@Autowired
    private GestorRepository gestorRepository;
	
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PerfilRepository perfilRepository;

	public ResultadoRequisicao registrar(GestorDTO gestorDTO) {
		try {
			Usuario usuario = new Usuario();
			Gestor gestor = new Gestor();
			
		    if(!gestorRepository.findAll().isEmpty()) {
	    		ResultadoRequisicao resultado = new ResultadoRequisicao();
	    		resultado.setStatus(HttpStatus.BAD_REQUEST);
	    		resultado.setMensagem("Ja existe um gestor cadastrado!");
				return resultado;
		    }
		    
		    Set<Perfil> perfis = new HashSet<>();
		    Perfil perfil = perfilRepository.findByNome(PerfilNome.valueOf(gestorDTO.getPerfil()) ).get();
		    perfis.add(perfil);
		    
			usuario.setEmail(gestorDTO.getEmail());
			usuario.setSenha(senhaEncoder.encode(gestorDTO.getSenha()));
			usuario.setPerfis(perfis);
			usuario = usuarioRepository.save(usuario);
			
    		gestor.setUsuario(usuario);
    		gestor.setNomeEstabelecimento(gestorDTO.getNomeEstabelecimento());
    		gestorRepository.save(gestor);
			

    		ResultadoRequisicao resultado = new ResultadoRequisicao();
    		resultado.setStatus(HttpStatus.CREATED);
    		resultado.setMensagem("Gestor cadastrado com sucesso");
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			ResultadoRequisicao resultado = new ResultadoRequisicao();
			resultado.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resultado.setMensagem("Erro ao realizar operaçao");
			return resultado;
		}
	}
	
}
