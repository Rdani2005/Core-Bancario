package edu.calle.banca.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.calle.banca.entities.Region;
import edu.calle.banca.requests.AddRegionRequest;
import edu.calle.banca.services.RegionService;
import lombok.RequiredArgsConstructor;

/**
 * Clase de controlador para hacer peticiones web
 * sobre las regiones disponibles.
 * </br>
 * 
 * HTTP Responses Available
 * </br>
 * 200 OK --> Used when accepted or found items
 * </br>
 * 201 Created  --> Used when items where created
 * </br>
 * 404 Not Found --> Not found the item
 * 
 * Running on http://localhost:8080/api/v1/regiones
 * 
 * 
 * @author <a href='https://rdani2005.works'>Danny Sequeira</a>
 * @since 03-04-2023
 * @version 1.0 SNAPSHOT
 * @see edu.calle.banca.entities.Region
 * @see edu.calle.banca.entities.Cliente
 * @see edu.calle.banca.repositories.RegionRepo
 * @see edu.calle.banca.services.RegionService
 * @see <a href='https://www.rfc-editor.org/rfc/rfc7231#section-6.3.3'>HTTP Status</a>
 */
@RequiredArgsConstructor // Iniciar argumentos necesarios
@Controller // Declarar controlador
@RequestMapping("api/v1/regiones") // Request a consultar
public class RegionController {

    private final RegionService service; // Obtener y manejo de datos de BD.

    /**
     * Enviar al cliente todas las regiones disponibles
     * 
     * @return Lista de regiones
     */
    @GetMapping
    public ResponseEntity<List<Region>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.FOUND);
    }

    /**
     * Enviar al cliente una region especifica por id
     * 
     * @param id filtro
     * @return 404 si no se encuentra, Region si es encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<Region> getById(@PathVariable("id") Long id) {
        Region region = service.getById(id); // Obtener la region
        return region == null ? new ResponseEntity<>(
                HttpStatus.NOT_FOUND) // No se encontro la region
                : new ResponseEntity<>(
                        region,
                        HttpStatus.OK); // Region encontrada
    }

    /**
     * Agregar una nueva region a la BD.
     * 
     * @param request info desde cliente
     * @return nueva region
     */
    @PostMapping
    public ResponseEntity<Region> addRegion(
            @RequestBody AddRegionRequest request) {
        // Construir objeto de tipo Region para agregarlo a BD
        Region region = new Region();
        region.setNombre(request.getNombre());
        return new ResponseEntity<>(
                service.addRegion(region),
                HttpStatus.CREATED); // Agregado
    }

    /**
     * Actualizar datos en BD.
     * 
     * @param id      filtro de actualizacion.
     * @param request Nueva info.
     * @return Region actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(
            @PathVariable("id") Long id,
            @RequestBody AddRegionRequest request) {
        Region region = service.getById(id);
        region.setNombre(request.getNombre());
        return new ResponseEntity<>(service.updateRegion(region), HttpStatus.OK);
    }

    /**
     * Borrar datos en BD
     * 
     * @param id Filtro de servicio a borrar
     * @return Entity Response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTegion(@PathVariable("id") Long id) {
        service.deleteRegion(service.getById(id)); // Proceso de eliminacion
        return new ResponseEntity<>(HttpStatus.OK); // Retorno de respuesta
    }
}
