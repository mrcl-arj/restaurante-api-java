package com.marcelo.restaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcelo.restaurante.DTO.ClienteDTO;
import com.marcelo.restaurante.model.Cliente;
import com.marcelo.restaurante.model.Usuario;
import com.marcelo.restaurante.repository.ClienteRepository;
import com.marcelo.restaurante.repository.UsuarioRepository;
import com.marcelo.restaurante.util.ResultadoRequisicao;

@Service
public class ClienteService {
    @Autowired
    PasswordEncoder senhaEncoder;
	
	@Autowired
    private ClienteRepository clienteRepository;
	
    @Autowired
    private UsuarioRepository usuarioRepository;

	public ResultadoRequisicao registrar(ClienteDTO clienteDTO) {
		ResultadoRequisicao resultado = new ResultadoRequisicao();
		try {
			Usuario usuario = new Usuario();
			Cliente cliente = new Cliente();
			
		    if(usuarioRepository.findUserByEmail(clienteDTO.getEmail()) != null) {
	    		resultado.setStatus(HttpStatus.BAD_REQUEST);
	    		resultado.setMensagem("Este email ja esta cadastrado!");
				return resultado;
		    }
		    
			usuario.setEmail(clienteDTO.getEmail());
			usuario.setSenha(senhaEncoder.encode(clienteDTO.getSenha()));
			usuario = usuarioRepository.save(usuario);
			
			cliente.setUsuario(usuario);
			cliente.setNome(clienteDTO.getNome());
			cliente.setTelefone(clienteDTO.getTelefone());
			cliente.setNascimento(clienteDTO.getNascimento());
			clienteRepository.save(cliente);
    		
    		resultado.setStatus(HttpStatus.CREATED);
    		resultado.setMensagem("Cliente cadastrado com sucesso");
			return resultado;
		} catch (Exception e) {
			resultado.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			resultado.setMensagem("Erro ao realizar operaçao");
			return resultado;
		}
	}

}
