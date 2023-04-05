package edu.calle.banca.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase diseñada para manejo de info de los clientes en BD
 * 
 * @author <a href='https://rdani2005.works'>Danny Sequeira</a>
 * @since 03-04-2023
 * @version 1.0 SNAPSHOT
 * @see edu.calle.banca.entities.Region
 * @see edu.calle.banca.entities.Cliente
 * @see edu.calle.banca.repositories.RegionRepo
 */
@Data // Clase de datos
@Builder // Metodos builder de clases
@Getter // Todos los metodos getter
@Setter // Todos los metodos setter requeridos
@AllArgsConstructor // Todos los argumentos dentro de constructores
@NoArgsConstructor // Sin Argumentos en constructores
@Entity // Entidad en BD.
@Table(name = "clientes") // Tabla en BD.
public class Cliente {
    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrementable en BD
    @Column(unique = true, nullable = false) // Unico y no puede ser nulo
    private int id; // Indentificador.
    private String nombre; // Nombre.
    private String identificacion; // Cedula Cliente.
    private String telefono; // Contacto de Cliente.
    private String correo; // Correo del cliente.
    private Date fechaRegistro; // Fecha de registro del cliente.
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.PERSIST })
    @JoinColumn(name = "region_id") // Declaracion de relacion 1:M
    private Region region; // country
}
