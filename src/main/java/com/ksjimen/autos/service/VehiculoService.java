package com.ksjimen.autos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksjimen.autos.dto.RequestVehiculoDto;
import com.ksjimen.autos.dto.ResponseImagenDto;
import com.ksjimen.autos.dto.ResponseVehiculoDto;
import com.ksjimen.autos.dto.VehiculosDTO;
import com.ksjimen.autos.exception.LineaException;
import com.ksjimen.autos.exception.MarcaException;
import com.ksjimen.autos.exception.TipoVehiculoException;
import com.ksjimen.autos.exception.ValidacionRequestException;
import com.ksjimen.autos.model.ImagenVehiculo;
import com.ksjimen.autos.model.LineaVehiculo;
import com.ksjimen.autos.model.MarcaVehiculo;
import com.ksjimen.autos.model.TipoVehiculo;
import com.ksjimen.autos.model.Vehiculo;
import com.ksjimen.autos.repository.IImagenRepoSitory;
import com.ksjimen.autos.repository.ILineaRepository;
import com.ksjimen.autos.repository.IMarcaVehiculo;
import com.ksjimen.autos.repository.ITipoVRepository;
import com.ksjimen.autos.repository.IVehiculoRepository;
import com.ksjimen.autos.utils.EstadosVehiculo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VehiculoService {
	
	private IVehiculoRepository vehiculoRepository;
	private IMarcaVehiculo marcaRepository;
	private ILineaRepository lineaRepository;
	private ITipoVRepository tipoRepo;
	private IImagenRepoSitory imagenRepo;
	
	
	public ResponseVehiculoDto agregar(RequestVehiculoDto requestVehiculo) {
		Vehiculo vehiculoRespuesta;
		try {
			validarRequestVehiculo(requestVehiculo);
			
			MarcaVehiculo marcaVehiculo = marcaRepository.findById(requestVehiculo.getIdMarca())
					.orElseThrow(() -> new MarcaException("La marca no existe."));
			
			LineaVehiculo lineaVehiculo = lineaRepository.findByIdLineaAndIdMarca(requestVehiculo.getIdLinea(), marcaVehiculo)
					.orElseThrow(() -> new LineaException("No existe linea de vehiculo para la marca de vehiculo."));
			
			TipoVehiculo tipoVehiculo = tipoRepo.findById(requestVehiculo.getIdTVehiculo())
					.orElseThrow(() -> new TipoVehiculoException("No existe el tipo de Vehiculo."));
			
			Vehiculo vehiculo = mapRequestVehiculoToVehiculo(requestVehiculo);
			vehiculo.setMarca(marcaVehiculo);
			vehiculo.setLinea(lineaVehiculo);
			vehiculo.setTipoVehiculo(tipoVehiculo);
			
			vehiculoRespuesta = vehiculoRepository.save(vehiculo);
			
			return ResponseVehiculoDto.builder()
					.respuesta("Registro creado Exitosamente.")
					.modelo(vehiculoRespuesta.getModelo())
					.idVehiculo(vehiculoRespuesta.getIdVehiculo())
					.build();
			
		}catch (MarcaException | LineaException e) {
			return ResponseVehiculoDto.builder()
                    .respuesta(e.getMessage())
                    .modelo(null)
                    .build();
		}
	}
	
	private void validarRequestVehiculo(RequestVehiculoDto request) {
		if(request == null || request.getIdLinea() == null || request.getIdMarca() == null) {
			throw new ValidacionRequestException("Solicitud no valida");
		}
	}
	
	private Vehiculo mapRequestVehiculoToVehiculo(RequestVehiculoDto requestVehiculo) {
		return Vehiculo.builder()
				.cilindraje(requestVehiculo.getCilindraje())
				.kilometraje(requestVehiculo.getKilometraje())
				.modelo(requestVehiculo.getModelo())
				.descripcion(requestVehiculo.getDescripcion())
				.fecha_venta(requestVehiculo.getFecha_venta())
				.id_estado(EstadosVehiculo.DISPONIBLE).precio(requestVehiculo.getPrecio())
				.precio_venta(requestVehiculo.getPrecio_venta()).fecha_registro(new Date())
				.fecha_modificacion(new Date())
				.usuario_adicion(0).usuario_modificacion(0)
				.estado_registro("A00S")
				.build();
	}

	public List<VehiculosDTO> listar() {
		List<VehiculosDTO> vehiculoResponse = new ArrayList<>();
		List<Vehiculo> vehiculos = vehiculoRepository.findAllByEstadoRegistroAndIdEstado();
		
		for(Vehiculo v: vehiculos) {
			List<ResponseImagenDto> imagenes = imagenesVehiculo(v.getIdVehiculo());
			VehiculosDTO vehiculo = VehiculosDTO.builder()
			.idVehiculo(v.getIdVehiculo())
			.cilindraje(v.getCilindraje())
			.kilometraje(v.getKilometraje())
			.modelo(v.getModelo())
			.descripcion(v.getDescripcion())
			.precio(v.getPrecio())
			.marca(v.getMarca())
			.linea(v.getLinea())
			.tipoVehiculo(v.getTipoVehiculo())
			.imagen(imagenes)
			.build();
			
			vehiculoResponse.add(vehiculo);
		}
		return vehiculoResponse;
	}
	
	private List<ResponseImagenDto> imagenesVehiculo(Long idVehiculo) {
		List<ImagenVehiculo> imagenVehiculo =  imagenRepo.findImagenesByVehiculoId(idVehiculo);
		List<ResponseImagenDto> imagenesDto = imagenVehiculo.stream()
				.map(imagen -> {
					ResponseImagenDto imagenResponse = new ResponseImagenDto();
					imagenResponse.setIdImagen(imagen.getIdImagen());
					imagenResponse.setRutaImagen(imagen.getRutaImagen());
					return imagenResponse;
				})
				.collect(Collectors.toList());
		return imagenesDto;
	}

	public void actualizarVehiculoVenta(Vehiculo vehiculoVenta, double valorVenta, Long idUsuario) {
		Vehiculo vehiculo = Vehiculo.builder()
			.idVehiculo(vehiculoVenta.getIdVehiculo())
			.cilindraje(vehiculoVenta.getCilindraje())
			.kilometraje(vehiculoVenta.getKilometraje())
			.modelo(vehiculoVenta.getModelo())
			.descripcion(vehiculoVenta.getDescripcion())
			.fecha_venta(vehiculoVenta.getFecha_venta())
			.id_estado(EstadosVehiculo.VENDIDO).precio(vehiculoVenta.getPrecio())
			.precio_venta(valorVenta)
			.fecha_registro(vehiculoVenta.getFecha_registro())
			.fecha_modificacion(new Date())
			.fecha_venta(new Date())
			.usuario_adicion(0)
			.usuario_modificacion(idUsuario.intValue())
			.estado_registro("A00S")
			.marca(vehiculoVenta.getMarca())
			.linea(vehiculoVenta.getLinea())
			.tipoVehiculo(vehiculoVenta.getTipoVehiculo())
			.build();
		vehiculoRepository.save(vehiculo);
		
	}

}
