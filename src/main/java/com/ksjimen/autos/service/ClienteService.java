package com.ksjimen.autos.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksjimen.autos.dto.RequestClienteDto;
import com.ksjimen.autos.dto.ResponseClienteDto;
import com.ksjimen.autos.exception.ClienteException;
import com.ksjimen.autos.model.Cliente;
import com.ksjimen.autos.repository.IClienteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {
	
	@Autowired
	private IClienteRepository clienteRepo;
	
	public ResponseClienteDto agregarClienteVenta(RequestClienteDto requestCliente) {
		try {
			Cliente clienteRespuesta = new Cliente();
			Cliente cliente = new Cliente();
			Optional<Cliente> clienteVenta = clienteRepo.findById(requestCliente.getIdCliente());
			if(!clienteVenta.isPresent()) {
				cliente.setIdCliente(0L);
			}
			cliente = clienteDtoToCliente(requestCliente);
			clienteRespuesta = clienteRepo.save(cliente);
			
			return ResponseClienteDto.builder()
					.documento(clienteRespuesta.getNumeroDocumento())
					.idCliene(clienteRespuesta.getIdCliente())
					.mensaje("Se agrego el cliente satisfactoriamente")
					.build();
			
		}catch (ClienteException e) {
			return ResponseClienteDto.builder()
				.mensaje(e.getMessage())
				.idCliene(null)
				.build();
		}
	}

	private Cliente clienteDtoToCliente(RequestClienteDto requestCliente) {
		return Cliente.builder()
				.tipoDocumento(requestCliente.getTipoIdentificacion())
				.numeroDocumento(requestCliente.getNumeroIdentificacion())
				.nommbre(requestCliente.getNombre())
				.apellido(requestCliente.getApellido())
				.estado_registro("A00S")
				.fecha_registro(new Date()).fecha_modificacion(new Date())
				.usuario_adicion(0).usuario_modificacion(0)
				.build();
	}

}
