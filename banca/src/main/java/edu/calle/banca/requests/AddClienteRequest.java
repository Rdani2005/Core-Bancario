package edu.calle.banca.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddClienteRequest {
    private String nombre; // Nombre.
    private String identificacion; // Cedula Cliente.
    private String telefono; // Contacto de Cliente.
    private String correo; // Correo del cliente.
    private Long regionId; // Id de region del cliente.
}
