package pe.edu.cibertec.dao;

import java.util.List;
import pe.edu.cibertec.bean.Cliente;

public interface DaoClientes {

    public List<Cliente> clientesQry();

    public String clientesIns(Cliente clientes);

    public String clientesDel(String idcliente);

    public Cliente clientesGet(String idcliente);

    public String clientesUpd(Cliente clientes);
}


