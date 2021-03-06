/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.softdirex.enubase.sync;

import cl.softdirex.enubase.entities.Despacho;
import cl.softdirex.enubase.entities.HistorialPago;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sdx
 */
public interface InterfaceSyncVenta {
    public boolean add(Object object);
    public boolean update(Object object);
    public Despacho getLastDespacho(String idVenta);
    public ArrayList<HistorialPago> getPagos(String idVenta);
    public String getId(String strParam, int intParam,Object type);
    public ArrayList<Object> listar(String idParam, Object type);
    public ArrayList<Object> listar(Date param, Object type);
    public ArrayList<Object> getDetalles(String idVenta);
}
