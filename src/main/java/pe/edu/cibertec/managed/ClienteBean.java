/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import pe.edu.cibertec.bean.Cliente;
import pe.edu.cibertec.dao.DaoClientes;
import pe.edu.cibertec.dao.impl.DaoClientesImpl;

/**
 *
 * @author portatil
 */

@ManagedBean(name = "clienteBean")
@ApplicationScoped
public class ClienteBean {
    
    private List<Cliente> listaClientes;
    private Cliente cliente = new Cliente();
    private String mensaje;

    private DaoClientes clienteDao;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public String registrar() {
        String resultado;
        clienteDao = new DaoClientesImpl();
        resultado = clienteDao.clientesIns(cliente);
        return this.listar();
    }

    public String listar() {
        clienteDao = new DaoClientesImpl();
        listaClientes = clienteDao.clientesQry();
        return "listado";
    }
    
    public String nuevo_url(){
        cliente= new Cliente();
        return "nuevo";
    }
    
    public String modificar_url() {
        
        clienteDao = new DaoClientesImpl();
        clienteDao.clientesUpd(cliente);
        return this.listar();
    }

    public String modificar(Cliente clienti) {
        
        clienteDao = new DaoClientesImpl();
        cliente=new Cliente();
        cliente=clienteDao.clientesGet(clienti.getCodigocliente().toString());
        return "modificar";
    }

    public String eliminar(String idcliente) {   
        
        clienteDao = new DaoClientesImpl();
        if(idcliente!=null)
        {clienteDao.clientesDel(idcliente);}
        return this.listar();
        
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    
    

}
