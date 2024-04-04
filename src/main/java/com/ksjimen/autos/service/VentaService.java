package com.ksjimen.autos.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksjimen.autos.dto.RequestVentaDto;
import com.ksjimen.autos.dto.ResponseClienteDto;
import com.ksjimen.autos.dto.ResponseVentaDto;
import com.ksjimen.autos.exception.ClienteException;
import com.ksjimen.autos.exception.UsuarioException;
import com.ksjimen.autos.exception.ValidacionRequestException;
import com.ksjimen.autos.exception.VehiculoException;
import com.ksjimen.autos.exception.VentaExceptio;
import com.ksjimen.autos.model.Cliente;
import com.ksjimen.autos.model.Usuario;
import com.ksjimen.autos.model.Vehiculo;
import com.ksjimen.autos.model.Venta;
import com.ksjimen.autos.repository.IClienteRepository;
import com.ksjimen.autos.repository.IUsuarioRepository;
import com.ksjimen.autos.repository.IVehiculoRepository;
import com.ksjimen.autos.repository.IVentaRepository;
import com.ksjimen.autos.utils.EstadosVehiculo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VentaService {
	
	@Autowired
	private IVentaRepository ventaRepo;
	private IClienteRepository clienteRepo;
	private IUsuarioRepository usuarioRepo;
	private IVehiculoRepository vehiculoRepo;
	private ClienteService clienteServicio;
	private VehiculoService vehiculoService;

	public ResponseVentaDto registrarVenta(RequestVentaDto requestVenta) {
		try {
			validacionRequest(requestVenta);
			
			Usuario usuarioVenta = usuarioRepo.findById(requestVenta.getUsuario().getIdUsuario())
					.orElseThrow(() -> new UsuarioException("El usuario que realiza la venta no existe"));
			
			Vehiculo vehiculoVenta = vehiculoRepo.findById(requestVenta.getVehiculo().getIdVehiculo())
					.orElseThrow(() -> new VehiculoException("El vehiculo no existe"));
			
			/*Bloque insertar Cliente*/
			ResponseClienteDto clienteDto = clienteServicio.agregarClienteVenta(requestVenta.getCliente());
			Cliente clienteVenta = clienteRepo.findById(clienteDto.getIdCliene())
					.orElseThrow(() -> new ClienteException("Hubo un error obteniendo el cliente"));
			
			Venta ventaRegistro = mapRequestVehiculoToVehiculo(requestVenta);
			ventaRegistro.setUsuario(usuarioVenta);
			ventaRegistro.setVehiculo(vehiculoVenta);
			ventaRegistro.setCliente(clienteVenta);
			
			Venta ventaRespuesta = ventaRepo.save(ventaRegistro);
			
			if(ventaRespuesta.getIdVenta() > 0) {
				vehiculoService.actualizarVehiculoVenta(vehiculoVenta,ventaRespuesta.getValorVenta(), ventaRespuesta.getUsuario().getIdUsuario());
				//actualizaVentaVehiculo(ventaRespuesta.getVehiculo(), ventaRespuesta.getValorVenta());
			}
			
			return ResponseVentaDto.builder()
					.respuesta("Se registro la venta correctamente")
					.idVenta(ventaRespuesta.getIdVenta())
					.build();
			
		}catch (VentaExceptio e) {
			return ResponseVentaDto.builder()
					.respuesta("Se produjo un error creando la venta")
					.build();
		}
	}
	
	private void validacionRequest(RequestVentaDto requestVenta) {
		if(requestVenta == null || requestVenta.getCliente() == null || requestVenta.getVehiculo() == null) {
			throw new ValidacionRequestException("Hay un error en la peticion");
		}
		
	}

	private void actualizaVentaVehiculo(Vehiculo vehiculo, double valorVenta) {
		Vehiculo vehiculoVenta = Vehiculo.builder()
				.idVehiculo(vehiculo.getIdVehiculo())
				.id_estado(EstadosVehiculo.VENDIDO)
				.fecha_modificacion(new Date())
				.fecha_venta(new Date())
				.precio_venta(valorVenta)
				.build();
		vehiculoRepo.save(vehiculoVenta);
	}

	private Venta mapRequestVehiculoToVehiculo(RequestVentaDto requestVenta) {
		return Venta.builder()
				.descripcion(requestVenta.getDescripcion())
				.valorVenta(requestVenta.getValorVenta())
				.estado_registro("A00S")
				.fecha_registro(new Date()).fecha_modificacion(new Date())
				.usuario_adicion(0).usuario_modificacion(0)
				.build();
	}
	
	

}
