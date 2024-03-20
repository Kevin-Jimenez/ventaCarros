package com.ksjimen.autos.model;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;
	private String usuario;
	private String contrasena;
	private String nombre;
	private String apellido;
	@Enumerated(EnumType.STRING)
	Roles rol;
	private Date fecha_registro;
	private Integer usuario_adicion;
	private Date fecha_modificacion;
	private Integer usuario_modificacion;
	private String estado_registro;
	@OneToMany(mappedBy = "usuario")
    private List<Venta> ventas;
}
