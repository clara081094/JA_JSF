/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import pe.edu.cibertec.bean.Cliente;
import pe.edu.cibertec.dao.DaoClientes;
import pe.edu.cibertec.dao.impl.DaoClientesImpl;

/**
 *
 * @author portatil
 */
@ManagedBean(name = "clienteBean")
@RequestScoped
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
        listaClientes = new ArrayList<Cliente>();
        listaClientes = clienteDao.clientesQry();
        return "lista";
    }

    public String modificar(Cliente cliente) {
        System.out.println("Esta en modificar");
        clienteDao = new DaoClientesImpl();
        FacesContext context = FacesContext.getCurrentInstance();
        Object value = context.getExternalContext().getRequestMap().get("codigo");
        clienteDao.clientesUpd(cliente);
        return this.listar();
    }

    public String eliminar() {
        System.out.println("Esta en eliminar");
        clienteDao = new DaoClientesImpl();
        FacesContext context = FacesContext.getCurrentInstance();
        Object value = context.getExternalContext().getRequestMap().get("codigo");
        clienteDao.clientesDel(value.toString());
        return this.listar();
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
