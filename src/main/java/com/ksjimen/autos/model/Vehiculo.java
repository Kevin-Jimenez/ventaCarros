package com.ksjimen.autos.model;

import java.time.Year;
import java.util.Date;
import java.util.List;

import com.ksjimen.autos.utils.EstadosVehiculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehiculo")
public class Vehiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vehiculo")
	private Long idVehiculo;
	private String cilindraje;
	private String kilometraje;
	private Year modelo;
	private String descripcion;
	private Date fecha_venta;
	@Enumerated(EnumType.STRING)
	EstadosVehiculo id_estado;
	private double precio;
	private double precio_venta;
	private Date fecha_registro;
	private Integer usuario_adicion;
	private Date fecha_modificacion;
	private Integer usuario_modificacion;
	private String estado_registro;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "marca_id", referencedColumnName = "id_marca")
		})
	private MarcaVehiculo marca;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "linea_id", referencedColumnName = "id_linea")
		})
	private LineaVehiculo linea;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "id_tipo_vehiculo", referencedColumnName = "id_tipo")
	})
	private TipoVehiculo tipoVehiculo;
	
	@OneToMany(mappedBy = "vehiculo")
    private List<Venta> ventas;
	

}
