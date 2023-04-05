package edu.calle.banca.services;

import java.util.List;

import edu.calle.banca.repositories.ClientesRepo;
import edu.calle.banca.entities.Cliente;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * Clase destinada para realizar todo el CRUD System de los objetos Cliente.
 * 
 * @author <a href='https://rdani2005.works'>Danny Sequeira</a>
 * @since 03-04-2023
 * @version 1.0 SNAPSHOT
 * @see edu.calle.banca.entities.Region
 * @see edu.calle.banca.entities.Cliente
 * @see edu.calle.banca.repositories.RegionRepo
 */
@Service // Indicar que es un servicio
@RequiredArgsConstructor // Inicializar todo lo requerido
public class ClienteService {

    private final ClientesRepo repo; // Repo de clientes

    /**
     * Obtener todos los clientes registrados en BD.
     * @return lista de todos los clientes
     */
    public List<Cliente> getAll() {
        return (List<Cliente>) repo.findAll(); // Devolver la lista con un cast
    }

    /**
     * Obtener unico cliente por BD.
     * @param id filtro
     * @return un unico cliente en BD
     */
    public Cliente getById(int id) {
        return repo.findById(id).get(); // Obtener unico cliente
    }

    /**
     * Agregar un cliente en BD
     * @param cliente cliente a agregar
     * @return nuevo cliente
     */
    public Cliente AddCliente(Cliente cliente) {
        return repo.save(cliente); // Guardar cliente
    }

    /**
     * Actualizar un cliente en bd
     * @param cliente info a actualizar.
     * @return info actualizada
     */
    public Cliente UpdateCliente(Cliente cliente) {
        return repo.save(cliente); // Guardar cambios
    }

    /**
     * Eliminar un cliente de BD
     * @param cliente cliente a eliminar
     */
    public void RemoveCliente(Cliente cliente) {
        repo.delete(cliente); // Eliminar
    }
}
