package edu.calle.banca.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.calle.banca.entities.Cliente;
import edu.calle.banca.entities.Region;
import edu.calle.banca.requests.AddClienteRequest;
import edu.calle.banca.services.ClienteService;
import edu.calle.banca.services.RegionService;

import lombok.RequiredArgsConstructor;

/**
 * Clase de controlador para hacer peticiones web
 * sobre los clientes disponibles.
 * </br>
 * 
 * HTTP Responses Available
 * </br>
 * 200 OK --> Used when accepted or found items
 * </br>
 * 201 Created --> Used when items where created
 * </br>
 * 404 Not Found --> Not found the item
 * 
 * Running on http://localhost:8080/api/v1/clientes
 * 
 * 
 * @author <a href='https://rdani2005.works'>Danny Sequeira</a>
 * @since 03-04-2023
 * @version 1.0 SNAPSHOT
 * @see edu.calle.banca.entities.Region
 * @see edu.calle.banca.entities.Cliente
 * @see edu.calle.banca.repositories.RegionRepo
 * @see edu.calle.banca.services.RegionService
 * @see <a href='https://www.rfc-editor.org/rfc/rfc7231#section-6.3.3'>HTTP
 *      Status</a>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {
    private final ClienteService service; // Servicio de BD.
    private final RegionService regionService; // Usado para obtener regiones de la BD.

    /**
     * Enviar todos los clientes del sistema
     * 
     * @return Lista de clientes
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    /**
     * Encontrar un cliente solo por su id
     * 
     * @param id filtro
     * @return objeto cliente con la informacion
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable("id") int id) {

        Cliente cliente = service.getById(id); // Obtener cliente por id
        // Retornar respuesta, 404 en caso de no encontrase, 200 y objeto en caso de
        // encontrado.
        return cliente == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) // Caso no encontrado
                : new ResponseEntity<>(cliente, HttpStatus.OK); // Objeto encontrado
    }

    /**
     * Agregar un cliente nuevo
     * 
     * @param request informacion del cliente a agregar
     * @return Cliente nuevo con su informacion y codigo de HTTP
     */
    @PostMapping
    public ResponseEntity<Cliente> addCliente(@RequestBody AddClienteRequest request) {
        Region region = regionService.getById(request.getRegionId()); // Obtener region por su ID
        // ========= Crear el nuevo Cliente ===================
        Cliente cliente = Cliente.builder() // Metodo Builder
                .nombre(request.getNombre()) // Nombre
                .identificacion(request.getIdentificacion()) // Identificacion
                .telefono(request.getTelefono()) // Telefono
                .correo(request.getCorreo()) // Correo
                .fechaRegistro(new Date()) // Fecha de registro (Actual)
                .region(region) // Region
                .build(); // Termina de contruir el objeto
        return new ResponseEntity<>(
                service.AddCliente(cliente), // Agregalo a BD.
                HttpStatus.CREATED); // Codigo de respuesta
    }

    /**
     * Actualizar un cliente.
     * 
     * @param id      Cliente a actualizar
     * @param request informacion nueva
     * @return Cliente actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(
            @PathVariable("id") int id,
            @RequestBody AddClienteRequest request) {
        // Obtener la region a usar del cliente
        Region region = regionService.getById(request.getRegionId());
        Cliente cliente = service.getById(id); // Obtener el cliente por medio de su ID
        // ============== Actualizar todos los datos =========================
        cliente.setNombre(request.getNombre()); // Nombre
        cliente.setIdentificacion(request.getIdentificacion()); // Identificacion
        cliente.setTelefono(request.getTelefono()); // Telefono
        cliente.setCorreo(request.getCorreo()); // Correo
        cliente.setRegion(region); // Region

        return new ResponseEntity<>(
                service.UpdateCliente(cliente), // Actualizar la informacion
                HttpStatus.OK); // Codigo de Operacion
    }

    /**
     * Funcion para borrar un cliente.
     * @param id filtro de cliente
     * @return Response Entity con codigo de status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable("id") int id) {
        Cliente cliente = service.getById(id); // Obtener cliente a borrar
        service.RemoveCliente(cliente); // Borrar el cliente
        return new ResponseEntity<>(HttpStatus.OK); // Codigo de Operacion
    }
}
