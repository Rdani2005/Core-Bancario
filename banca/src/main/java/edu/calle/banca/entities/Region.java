package edu.calle.banca.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase diseñada para manejo de datos de las Regiones
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
@Table(name = "region") // Tabla en BD.
public class Region {
    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrementable en BD
    @Column(unique = true, nullable = false) // Unico y no puede ser nulo
    private Long id; // Identificador de la entidad.
    private String nombre; // Nombre de la region.

    @JsonIgnore
    @OneToMany(mappedBy = "region", cascade = { CascadeType.PERSIST,
            CascadeType.PERSIST, CascadeType.PERSIST,
            CascadeType.PERSIST }) // Definicion de Relacion M:1
    private List<Cliente> contacts; // Clientes con sus regiones
}
