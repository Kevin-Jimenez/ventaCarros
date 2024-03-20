package com.ksjimen.autos.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksjimen.autos.dto.RequestUsuarioDto;
import com.ksjimen.autos.dto.ResponseUsuarioDto;
import com.ksjimen.autos.model.Roles;
import com.ksjimen.autos.model.Usuario;
import com.ksjimen.autos.repository.IUsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
	
	@Autowired
	private final IUsuarioRepository usuarioRepository;
	
	
	public ResponseUsuarioDto agregar(RequestUsuarioDto requestUsuario) {
		
		Usuario usuario = Usuario.builder()
				.usuario(requestUsuario.getUsuario())
				.contrasena(requestUsuario.getContrasena())
				.nombre(requestUsuario.getNombre())
				.apellido(requestUsuario.getApellido())
				.fecha_registro(new Date())
				.fecha_modificacion(new Date())
				.usuario_adicion(0)
				.usuario_modificacion(0)
				.estado_registro("A00S")
				.rol(Roles.ADMIN)
				.build();
		
		Usuario response = usuarioRepository.save(usuario);
				
		
		return ResponseUsuarioDto.builder()
				.nombre(response.getNombre())
				.apellido(response.getApellido())
				.build();
	}

}
