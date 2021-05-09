package com.marcelo.restaurante.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.marcelo.restaurante.model.Usuario;
import com.marcelo.restaurante.repository.UsuarioRepository;

@Service
public class Util {
	
	@Autowired
	private static UsuarioRepository usuarioRepository;
	
    public static String formatarBigDecimalParaMoeda(BigDecimal valor) {
        DecimalFormat fmt = new DecimalFormat("#,###,##0.00");
        return fmt.format(valor);
    }
    
    public static Usuario buscaUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        return usuarioRepository.findByEmail(authentication.getName());
    }
}
