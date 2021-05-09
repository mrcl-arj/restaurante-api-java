package com.marcelo.restaurante.DTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcelo.restaurante.model.Usuario;

public class UsuarioDTO implements UserDetails{
	
    private final Long id;

    private final String email;

    @JsonIgnore
    private final String senha;
    
    private final Collection<? extends GrantedAuthority> perfis;
    
    public UsuarioDTO(Long integer, String email,
            String senha, Collection<? extends GrantedAuthority> perfis) {
		this.id = integer;
		this.email = email;
		this.senha = senha;
		this.perfis = perfis;
	}
    
    public static UsuarioDTO build(Usuario usuario) {
        List<GrantedAuthority> authorities = usuario.getPerfis().stream().map(perfil ->
                new SimpleGrantedAuthority(perfil.getNome().name())
        ).collect(Collectors.toList());

        return new UsuarioDTO(
        		usuario.getId(),
        		usuario.getEmail(),
        		usuario.getSenha(),
        		authorities
        );
    }


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.perfis;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Collection<? extends GrantedAuthority> getPerfis() {
		return perfis;
	}

}
