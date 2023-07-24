/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luv2code.jsf.jdbc;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author lacorrea
 */
@ManagedBean
@SessionScoped
public class TipoRepuestoBean implements Serializable{

    private TipoRepuesto objeto;
    private TipoRepuestoDbUtil tipoRepuestoDbUtil;
    private List<TipoRepuesto> tiporepuestos;
    private int tipoAccion = 1;
    
    public TipoRepuestoBean() throws Exception{
        reinstanciar();
        tiporepuestos = new java.util.LinkedList<TipoRepuesto>();
        tipoRepuestoDbUtil = TipoRepuestoDbUtil.getInstance();
	}
    
    public List<TipoRepuesto> getLista() throws Exception{
        return tipoRepuestoDbUtil.getTipoRepuestos();
    }

    public TipoRepuesto getObjeto() {
        return objeto;
    }

    public void setObjeto(TipoRepuesto objeto) {
        this.objeto = objeto;
    }

    public int getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(int tipoAccion) {
        this.tipoAccion = tipoAccion;
    }
    
    public void reinstanciar(){
        //Crear
        objeto = new TipoRepuesto();
    }
    
    public void seleccionarItem(int pkId) throws Exception {
    	objeto = tipoRepuestoDbUtil.getTipoRepuesto(pkId);
    }
    
    public void procesar() throws Exception{
        if(tipoAccion == 1) {
        	//Crear
            tipoRepuestoDbUtil.addTipoRepuesto(objeto);
        }else {
        	//Actualizar
            tipoRepuestoDbUtil.actualizarTipoRepuesto(objeto);
        }
    }
    
}
