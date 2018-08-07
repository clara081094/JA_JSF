/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable{
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void salir() throws IOException{
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        session.invalidate();
        context.getExternalContext().redirect("login.xhtml");
    }
    
    public String autenticar(){
        
        boolean resultado=false;
        
        Calendar ca = Calendar.getInstance();
        Integer hora=ca.get(Calendar.HOUR_OF_DAY);
        Integer minutos=ca.get(Calendar.MINUTE);
        
        ResourceBundle rb=
                ResourceBundle.getBundle("pe.edu.cibertec.recursos.mensajes",
                 FacesContext.getCurrentInstance().getViewRoot().getLocale());
        
        if((hora==20&&minutos>0)||
                (hora>20) || (hora<9) )
        {
            FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,
                   rb.getString("horario_login_incorrecto"),
                   rb.getString("horario_login_incorrecto_detail"));
            FacesContext.getCurrentInstance().addMessage(null, fm);
            
            return null;
        }
        
        if(username.equals("admin") && 
                password.equals("admin"))
            resultado=true;
        
        //si servicio responde error por credenciales incorrectas
        if(!resultado){
         FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,
                   rb.getString("validacion_login_incorrecto"),
                   rb.getString("validacion_login_incorrecto_detail"));
         FacesContext.getCurrentInstance().addMessage(null, fm);
            
            return null;
            
        }else{
            //si servicio responde ok
            password=null;
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            httpSession.setAttribute("userObject", username);
            return "principal";
        }
        
    }
}
