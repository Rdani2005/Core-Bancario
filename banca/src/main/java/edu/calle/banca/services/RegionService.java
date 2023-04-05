package edu.calle.banca.services;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.calle.banca.entities.Region;
import edu.calle.banca.repositories.RegionRepo;
import lombok.RequiredArgsConstructor;


/**
 * Clase destinada para realizar todo el CRUD System de los objetos Region.
 * 
 * @author <a href='https://rdani2005.works'>Danny Sequeira</a>
 * @since 03-04-2023
 * @version 1.0 SNAPSHOT
 * @see edu.calle.banca.entities.Region
 * @see edu.calle.banca.entities.Cliente
 * @see edu.calle.banca.repositories.RegionRepo
 */
@RequiredArgsConstructor // Indicar a SpringBoot que inicie todos los repositorios
@Service // Indica que es un servicio
public class RegionService {
    private final RegionRepo repo; // Instanciar el repositorio

    /**
     * Listar todas las regiones disponibles
     * @return TOdas las regiones disponibles en BD.
     */
    public List<Region> getAll() {
        return (List<Region>) repo.findAll();
    }

    /**
     * Retorna una region en especifica
     * @param id filtra la region
     * @return Region filtrada
     */
    public Region getById(Long id) {
        return repo.findById(id).get();
    }

    /**
     * Agrega una region en especifico
     * @param region Region a agregar
     * @return Region agregada
     */
    public Region addRegion(Region region) {
        return repo.save(region);
    }

    /**
     * Actualiza la informacion de una region
     * @param region region con info actualizada
     * @return Region actualizada
     */
    public Region updateRegion(Region region) {
        return repo.save(region);
    }

    /**
     * Borra una region
     * @param region region a borrar.
     */
    public void deleteRegion(Region region) {
        repo.delete(region);
    }
}
