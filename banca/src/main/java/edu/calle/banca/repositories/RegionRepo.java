package edu.calle.banca.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.calle.banca.entities.Region;

/**
 * Esta interfaz se encarga de manejar toda la data correspondiente a BD.
 * Extiende de una interfaz padre, llamada CrudRepository, que se encarga del
 * manejo
 * de clases en SQL
 * 
 * Siguiendo el patron de diseño solid, una clase o interfaz se encargará de obtener info
 * de BD, la otra de pedirla, y la otra de enviarla o recibirla.
 * 
 * 
 * SOLID
 * 
 * @author <a href='https://rdani2005.works'>Danny Sequeira</a>
 * @since 03-04-2023
 * @version 1.0 SNAPSHOT
 * @see edu.calle.banca.entities.Region
 * @see edu.calle.banca.entities.Cliente
 */
@Repository
public interface RegionRepo extends CrudRepository<Region, Long> {

}
