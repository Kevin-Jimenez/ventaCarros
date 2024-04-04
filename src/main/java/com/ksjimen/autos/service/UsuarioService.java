package com.ksjimen.autos.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksjimen.autos.dto.RequestUsuarioDto;
import com.ksjimen.autos.dto.ResponseUsuarioDto;
import com.ksjimen.autos.exception.UsuarioException;
import com.ksjimen.autos.exception.ValidacionRequestException;
import com.ksjimen.autos.model.Roles;
import com.ksjimen.autos.model.Usuario;
import com.ksjimen.autos.repository.IUsuarioRepository;

import lombok.AllArgsConstructor;
import lombok.val;

@Service
@AllArgsConstructor
public class UsuarioService {
	
	@Autowired
	private final IUsuarioRepository usuarioRepository;
	
	
	public ResponseUsuarioDto agregar(RequestUsuarioDto requestUsuario) {
		try {
			validarRequestUsuario(requestUsuario);
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
					.rol(Roles.SUPERADMIN)
					.build();
			
			Usuario response = usuarioRepository.save(usuario);
					
			
			return ResponseUsuarioDto.builder()
					.nombre(response.getNombre())
					.apellido(response.getApellido())
					.build();
		}catch (UsuarioException e) {
			return ResponseUsuarioDto.builder()
					.nombre(e.getMessage())
					.apellido(null)
					.build();
		}
		
	}
	
	public ResponseUsuarioDto actualizar(RequestUsuarioDto requestUsuario) {
		try {
			validarRequestUsuario(requestUsuario);
			Usuario usuarioActualizar = usuarioRepository.findById(requestUsuario.getIdUsuario())
					.orElseThrow(() -> new UsuarioException("El usuario no existe, por favor intente nuevamente"));
			
			Usuario usuario = Usuario.builder()
					.idUsuario(requestUsuario.getIdUsuario())
					.usuario(requestUsuario.getUsuario())
					.contrasena(requestUsuario.getContrasena())
					.nombre(requestUsuario.getNombre())
					.apellido(requestUsuario.getApellido())
					.fecha_modificacion(new Date())
					.fecha_registro(usuarioActualizar.getFecha_registro())
					.usuario_adicion(0)
					.usuario_modificacion(requestUsuario.getIdUsuario().intValue())
					.estado_registro("A00S")
					.rol(usuarioActualizar.getRol())
					.build();
			
			Usuario response = usuarioRepository.save(usuario);
					
			
			return ResponseUsuarioDto.builder()
					.nombre(response.getNombre())
					.apellido(response.getApellido())
					.build();
		}catch (UsuarioException e) {
			return ResponseUsuarioDto.builder()
					.nombre(e.getMessage())
					.apellido(null)
					.build();
		}
		
	}
	
	
	private void validarRequestUsuario(RequestUsuarioDto requestUsuario) {
		if(requestUsuario == null || requestUsuario.getUsuario() == null || requestUsuario.getContrasena() == null ) {
			throw new ValidacionRequestException("Solicitud no valida");
		}
	}
	

}
