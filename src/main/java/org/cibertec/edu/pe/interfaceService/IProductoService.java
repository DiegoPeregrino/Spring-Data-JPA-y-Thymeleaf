package org.cibertec.edu.pe.interfaceService;

import java.util.List;
import java.util.Optional;
import org.cibertec.edu.pe.modelo.Producto;

public interface IProductoService {
    List<Producto> Listado();
    Optional<Producto> Buscar(int id);
    void Insertar(Producto p);
    void Modificar(Producto p);
    void Suprimir(int id);
}
