/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.softdirex.enubase.utils;

import cl.softdirex.enubase.dao.Dao;
import cl.softdirex.enubase.entities.Cliente;
import cl.softdirex.enubase.entities.Inventario;
import cl.softdirex.enubase.entities.Item;
import cl.softdirex.enubase.entities.Proveedor;
import cl.softdirex.enubase.entities.Venta;
import cl.softdirex.enubase.view.notifications.OptionPane;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author jlleivas
 */
public class XlsUtils {
    private static Dao load = new Dao();
    private static List<Object> proveedores = load.listar("0", new Proveedor());
    
    private static List<String> clasificaciones = PropertiesUtils.getClasificaciones();
    private static List<String> unidades = PropertiesUtils.getUnidades();
    
    public static void generarExcelRespaldo(String[][] entrada, String name) throws WriteException{
        try {
            WorkbookSettings conf = new WorkbookSettings();
            conf.setEncoding("ISO-8859-1");
        
            WritableWorkbook woorbook = Workbook.createWorkbook(new File(DirectoryUtils.getFilesPath()+File.separator+"RSP"+File.separator+name+".xls"),conf);
            
            WritableSheet sheet = woorbook.createSheet("Resultado", 0);
            
            WritableFont h = new WritableFont(WritableFont.TIMES, 10,WritableFont.NO_BOLD);
            WritableCellFormat hformat = new WritableCellFormat(h);
            
            WritableFont h2 = new WritableFont(WritableFont.TIMES, 12,WritableFont.BOLD);
            WritableCellFormat hformat2 = new WritableCellFormat(h2);
            
            for(int i =0; i < entrada.length; i++){//filas
                for(int j=0; j < entrada[i].length;j++){//columnas
                    try {
                        if(i==0 || i==5){
                            sheet.addCell(new jxl.write.Label(j, i, entrada[i][j],hformat));
                        }else{
                            sheet.addCell(new jxl.write.Label(j, i, entrada[i][j],hformat));
                        }
                        
                    } catch (WriteException ex) {
                        Logger.getLogger(XlsUtils.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            woorbook.write();
            try {
                woorbook.close();
            } catch (WriteException ex) {
                Logger.getLogger(XlsUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(XlsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean saveDebtMails(){
        Dao load = new Dao();
        List<Object> lista = load.listar("morosos", new Cliente());
        if(lista.size()<1){
            OptionPane.showMsg("Sin datos", "La exportación no se pudo ejecutar: \n"
                    + "No existen registros para guardar.",2);
            return false;
        }
        return createXslInput(lista);
    }
    
    public static boolean saveUnDeliveredMails(){
        Dao load = new Dao();
        List<Object> lista = load.listar("retirar", new Cliente());
        if(lista.size()<1){
            OptionPane.showMsg("Sin datos", "La exportación no se pudo ejecutar: \n"
                    + "No existen registros para guardar.",2);
            return false;
        }
        return createXslInput(lista);
    }
    
    public static void saveInventaryLowStock(){
        Dao load = new Dao();
        Inventario local = null;
        try {
            local = ((Inventario)load.get(GlobalValuesVariables.getInventarioName(), 0, new Inventario()));
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            local = new Inventario();
        }
        local = (local!=null)?local:new Inventario();
        GlobalValuesVariables.setInventaryChooser(local.getId());//Se prepara el id del inventario que se usara en la consulta a la base de datos
        List<Object> lista = load.listar(GlobalValuesVariables.getSqlLowStock(),new Item());
        GlobalValuesVariables.setInventaryChooser(0);//Se reinicia el id temporal del inventario seleccionado
        JFileChooser archivo = new JFileChooser();
        if(lista.size()<1){
            OptionPane.showMsg("No se puede generar archivo", "No existen productos con stock bajo para generar la orden de compra.", 2);
            return;
        }
        exportExcelItems(lista, archivo);
    }
    
    private static void exportExcelItems(List<Object> lista, JFileChooser archivo){
        int resp = archivo.showSaveDialog(archivo);
        if(resp == JFileChooser.APPROVE_OPTION){
            if(lista.size()>0){
                //[filas][columnas]
                int columnas = 15;
                int filas = lista.size()+columnas;
                String [][] entrada = new String[filas][columnas];
                entrada[0][1] = "Registro de inventario generado el "+GV.dateToString(new Date(), "dd/mm/yyyy")+"";
                entrada[1][0] = "Empresa:";
                entrada[1][1] = GlobalValuesVariables.getCompanyName();
                entrada[2][0] = "Sistema:";
                entrada[2][1] = GlobalValuesVariables.getProjectName()+" "+GlobalValuesVariables.getVersion();
                entrada[3][0] = "Soporte:";
                entrada[3][1] = "www.softdirex.cl";
                entrada[5][0] = "Codigo";
                entrada[5][1] = "Descripción";
                entrada[5][2] = "Unidad";
                entrada[5][3] = "Clasificación";
                entrada[5][4] = "Proveedor";
                entrada[5][5] = "Contacto";
                entrada[5][6] = "Precio compra";
                entrada[5][7] = "Iva";
                entrada[5][8] = "Total";
                entrada[5][9] = "% Utilidad";
                entrada[5][10] = "$ Utilidad";
                entrada[5][11] = "Neto precio venta";
                entrada[5][12] = "Precio venta con iva";
                entrada[5][13] = "Stock minimo";
                entrada[5][14] = "Stock";
                int contFilas = 6;
                for (Object onject : lista) {
                    Item temp = (Item)onject;
                    for(int i = 0;i< columnas; i++){
                        if(i==0)
                            entrada[contFilas][i] = temp.getCod();
                        else if(i == 1)
                            entrada[contFilas][i] = temp.getDescripcion();
                        else if(i == 2){
                            String unidad = unidades.get(temp.getTipo()-1);
                            entrada[contFilas][i] = unidad;
                        }
                        else if(i == 3){
                            String clasificacion = GV.getStr(clasificaciones.get(temp.getClasificacion()-1));
                            clasificacion = (clasificacion.isEmpty())?"No asignada":clasificacion;
                            entrada[contFilas][i] = clasificacion;
                        }
                        else if(i == 4){
                            String proveedor = "Sin registrar";
                            Object proTemp = GV.searchInList(temp.getIdProveedor(), proveedores, new Proveedor());
                            proveedor = (proTemp != null)?(((Proveedor)proTemp).getNombre()):proveedor;
                            entrada[contFilas][i] = proveedor;
                        }
                        else if(i == 5){
                            String contacto = "";
                            Object proTemp = GV.searchInList(temp.getIdProveedor(), proveedores, new Proveedor());
                            contacto = (proTemp != null)?(((Proveedor)proTemp).getTelefono()):contacto;
                            contacto = (proTemp != null && contacto.isEmpty())?(((Proveedor)proTemp).getEmail()):contacto;
                            contacto = (proTemp != null && contacto.isEmpty())?(((Proveedor)proTemp).getWeb()):contacto;
                            contacto = (contacto.isEmpty())?"Sin registrar":contacto;
                            entrada[contFilas][i] = contacto;
                        }
                        else if(i == 6)
                            entrada[contFilas][i] = GV.strToPrice(temp.getPrecioRef());
                        else if(i == 7){
                            if(temp.getPrecioRef()>0){
                                double iva = temp.getPrecioRef()*PropertiesUtils.getIVA()/100.0;
                                entrada[contFilas][i] = GV.strToPrice((int)iva);
                            }else{
                                entrada[contFilas][i] = "Imposible calcular";
                            }
                        }
                        else if(i == 8){
                            if(temp.getPrecioRef()>0){
                                double iva = temp.getPrecioRef()*PropertiesUtils.getIVA()/100.0;
                                double total = temp.getPrecioRef()+iva;
                                entrada[contFilas][i] = GV.strToPrice((int)total);
                            }else{
                                entrada[contFilas][i] = "Imposible calcular";
                            }  
                        }
                        else if(i == 9){
                            if(temp.getPrecioRef()>0){
                                double iva = temp.getPrecioRef()*PropertiesUtils.getIVA()/100.0;
                                double total = temp.getPrecioRef()+iva;
                                int totalPrecio = temp.getPrecioAct();
                                double precioUtilidad = totalPrecio-total;
                                double utilidad = (precioUtilidad/total)/0.01;
                                entrada[contFilas][i] = (int)utilidad+"%";
                            }else{
                                entrada[contFilas][i] = "Imposible calcular";
                            }
                        }
                        else if(i == 10){
                            if(temp.getPrecioRef()>0){
                                double iva = temp.getPrecioRef()*PropertiesUtils.getIVA()/100.0;
                                double total = temp.getPrecioRef()+iva;
                                int totalPrecio = temp.getPrecioAct();
                                double precioUtilidad = totalPrecio-total;
                                entrada[contFilas][i] = GV.strToPrice((int)precioUtilidad);
                            }else{
                                entrada[contFilas][i] = "Imposible calcular";
                            }
                        }
                        else if(i == 11)
                            entrada[contFilas][i] = GV.strToPrice(temp.getPrecioAct());
                        else if(i==12){
                            if(temp.getPrecioAct()>0){
                                double iva = temp.getPrecioAct()*PropertiesUtils.getIVA()/100.0;
                                entrada[contFilas][i] = GV.strToPrice(temp.getPrecioAct()+((int)iva));
                            }else{
                                entrada[contFilas][i] = "Imposible calcular";
                            }
                        }
                        else if(i == 13)
                            entrada[contFilas][i] = ""+temp.getStockMin();
                        else if(i == 14)
                            entrada[contFilas][i] = ""+temp.getStock();
                        
                    }
                    contFilas++;
                }
                String ruta = String.valueOf(archivo.getSelectedFile().toString())+".xls";
                generarExcel(entrada, ruta);
                msgDone();
                return;
            }
        }else if(resp == JFileChooser.CANCEL_OPTION){
            msgRejected();
        }
        return;
    }
    
    public static void saveInventary(){
        Dao load = new Dao();
        if(GlobalValuesVariables.getInventaryChooser()==0){
            OptionPane.showMsg("No se pudo realizar la operación", "Los datos no han sido ingresados correctamente\n"
                    + "Debe seleccionar un invntario", 1);
            return;
        }
        List<Object> lista = load.listar("0", new Item());
        JFileChooser archivo = new JFileChooser();
        if(lista.size()<1){
            OptionPane.showMsg("No existen registros para cargar", "No es posible generar un nuevo archivo,\n"
                    + "no existen productos registrados.",2);
            return;
        }
        exportExcelItems(lista, archivo);
    }
    
    public static boolean saveAllMails() {
        Dao load = new Dao();
        List<Object> lista = load.listar("0", new Cliente());
        if(lista.size()<1){
            OptionPane.showMsg("Sin datos", "La exportación no se pudo ejecutar: \n"
                    + "No existen registros para guardar.",2);
            return false;
        }
        return createXslInput(lista);
    }
    
    public static boolean createXslInput(List<Object> lista){
        String sexo = "Sin registrar";
        JFileChooser archivo = new JFileChooser();
        int resp = archivo.showSaveDialog(archivo);
        if(resp == JFileChooser.APPROVE_OPTION){
            if(lista.size()>0){
                //[filas][columnas]
                int filas = lista.size()+6;
                String [][] entrada = new String[filas][7];
                entrada[0][1] = "Documento generado el "+GV.dateToString(new Date(), "dd de mm del yyyy").replaceFirst("de", "del")+"";
                entrada[1][0] = "Empresa:";
                entrada[1][1] = GlobalValuesVariables.getCompanyName();
                entrada[2][0] = "Sistema:";
                entrada[2][1] = GlobalValuesVariables.getProjectName();
                entrada[3][0] = "Soporte:";
                entrada[3][1] = "www.softdirex.cl";
                entrada[5][0] = "Rut";
                entrada[5][1] = "Nombre";
                entrada[5][2] = "Comuna";
                entrada[5][3] = "Ciudad";
                entrada[5][4] = "Sexo";
                entrada[5][5] = "Teléfono";
                entrada[5][6] = "Correo";
                int contFilas = 6;
                for (Object obj : lista) {
                    Cliente temp = (Cliente)obj;
                    for(int i = 0;i< 7; i++){
                        if(i==0)
                            entrada[contFilas][i] = temp.getCod();
                        else if(i == 1)
                            entrada[contFilas][i] = temp.getNombre();
                        else if(i == 2)
                            entrada[contFilas][i] = temp.getComuna();
                        else if(i == 3)
                            entrada[contFilas][i] = temp.getCiudad();
                        else if(i == 4){
                            if(temp.getSexo()==0)
                                sexo="Sin registrar";
                            if(temp.getSexo()==1)
                                sexo="Femenino";
                            if(temp.getSexo()==2)
                                sexo="Masculino";
                            entrada[contFilas][i] = sexo;
                        }
                        else if(i == 5)
                            entrada[contFilas][i] = (temp.getTelefono1().isEmpty())?temp.getTelefono2():temp.getTelefono1();
                        else if(i == 6)
                            entrada[contFilas][i] = temp.getEmail();
                    }
                    contFilas++;
                }
                String ruta = String.valueOf(archivo.getSelectedFile().toString())+"-["+GV.dateToString(new Date(), "dd-mm-yyyy")+"].xls";
                generarExcel(entrada, ruta);
                msgDone();
                return true;
            }
        }else if(resp == JFileChooser.CANCEL_OPTION){
            msgRejected();
        }
        return false;
    }

    private static void msgRejected() {
        OptionPane.showMsg("No se generó el archivo", "La operacion ha sido cancelada.",2);
    }
    
    public static boolean exportarVentasAExcel(List<Object> lista) {
        JFileChooser archivo = new JFileChooser();
        int resp;
        List<Object> ventas = new ArrayList<>();
        for (Object object : lista) {
            if(((Venta)object).getEstado() > 0){
                ventas.add(((Venta)object));
            }
        }
        if(ventas.size()<1){
            OptionPane.showMsg("No se pudo generar archivo", "No existen registros para guardar, Genere una lista de datos válidos.",2);
            return false;
        }
        resp = archivo.showSaveDialog(archivo);
        if(resp == JFileChooser.APPROVE_OPTION){
            if(ventas.size()>0){
                //[filas][columnas]
                int filas = ventas.size()+6;
                int columns = 19;
                String [][] entrada = new String[filas][columns];
                entrada[0][1] = "Documento generado el "+GV.dateToString(new Date(), "dd/mm/yyyy")+"";
                entrada[1][0] = "Empresa:";
                entrada[1][1] = GlobalValuesVariables.getCompanyName();
                entrada[2][0] = "Sistema:";
                entrada[2][1] = GlobalValuesVariables.getProjectName();
                entrada[3][0] = "Soporte:";
                entrada[3][1] = "www.softdirex.cl";
                
                entrada[4][14] = "Datos";
                entrada[4][15] = "de";
                entrada[4][16] = "entrega";
                
                entrada[5][0] = "Fecha";
                entrada[5][1] = "Folio";
                entrada[5][2] = "Rut";
                entrada[5][3] = "Nombre";
                entrada[5][4] = "Dirección";
                entrada[5][5] = "Teléfono";
                entrada[5][6] = "Teléfono";
                entrada[5][7] = "Comuna";
                entrada[5][8] = "Ciudad";
                entrada[5][9] = "Precio";
                entrada[5][10] = "Descuento";
                entrada[5][11] = "Total";
                entrada[5][12] = "Abono";
                entrada[5][13] = "Saldo";
                entrada[5][14] = "Fecha";
                entrada[5][15] = "Hora";
                entrada[5][16] = "Lugar";
                entrada[5][17] = "Vendedor";
                int contFilas = 6;
                for (Object object : ventas) {
                    Venta temp = (Venta)object;
                    int precio = temp.getValorTotal();
                    temp.setValorTotal(temp.getValorTotal()-temp.getDescuento());
                    
                    for(int i = 0;i< columns; i++){
                        if(i==0)
                            entrada[contFilas][i] = ""+temp.getFecha();
                        else if(i == 1)
                            entrada[contFilas][i] = ""+temp.getCod();
                        else if(i == 2)
                            entrada[contFilas][i] = temp.getCliente().getCod();
                        else if(i == 3)
                            entrada[contFilas][i] = temp.getCliente().getNombre();
                        else if(i == 4)
                            entrada[contFilas][i] = temp.getCliente().getDireccion();
                        else if(i == 5)
                            entrada[contFilas][i] = temp.getCliente().getTelefono1();
                        else if(i == 6)
                            entrada[contFilas][i] = temp.getCliente().getTelefono2();
                        else if(i == 7)
                            entrada[contFilas][i] = temp.getCliente().getComuna();
                        else if(i == 8)
                            entrada[contFilas][i] = temp.getCliente().getCiudad();
                        else if(i == 9)
                            entrada[contFilas][i] = ""+precio;
                        else if(i == 10)
                            entrada[contFilas][i] = ""+temp.getDescuento();
                        else if(i == 11)
                            entrada[contFilas][i] = ""+temp.getValorTotal();
                        else if(i == 12)
                            entrada[contFilas][i] = ""+(temp.getValorTotal()-temp.getSaldo());
                        else if(i == 13)
                            entrada[contFilas][i] = ""+temp.getSaldo();
                        else if(i == 14)
                            entrada[contFilas][i] = ""+temp.getFechaEntrega();
                        else if(i == 15)
                            entrada[contFilas][i] = temp.getHoraEntrega();
                        else if(i == 16)
                            entrada[contFilas][i] = temp.getLugarEntrega();
                        else if(i == 17)
                            entrada[contFilas][i] = temp.getVendedor().getNombre();
                    }
                    contFilas++;
                }
                String ruta = String.valueOf(archivo.getSelectedFile().toString())+".xls";
                if(ruta.startsWith(".xls")){
                    ruta = "Report"+GV.dateToString(new Date(), "dd-mm-yyyy")+ruta;
                }
                generarExcel(entrada, ruta);
                msgDone();
                return true;
            }
        }else if(resp == JFileChooser.CANCEL_OPTION){
            msgRejected();
        }
        return false;
    }

    
    public static void generarExcel(String[][] entrada, String ruta){
        try {
            WorkbookSettings conf = new WorkbookSettings();
            conf.setEncoding("ISO-8859-1");
        
            WritableWorkbook woorbook = Workbook.createWorkbook(new File(ruta),conf);
            
            WritableSheet sheet = woorbook.createSheet("Resultado", 0);
            
            WritableFont h = new WritableFont(WritableFont.TIMES, 10,WritableFont.NO_BOLD);
            WritableCellFormat hformat = new WritableCellFormat(h);
            
            WritableFont h2 = new WritableFont(WritableFont.TIMES, 12,WritableFont.BOLD);
            WritableCellFormat hformat2 = new WritableCellFormat(h2);
            
            for(int i =0; i < entrada.length; i++){//filas
                for(int j=0; j < entrada[i].length;j++){//columnas
                    try {
                        if(i==0 || i==5){
                            sheet.addCell(new jxl.write.Label(j, i, entrada[i][j],hformat2));
                        }else{
                            sheet.addCell(new jxl.write.Label(j, i, entrada[i][j],hformat));
                        }
                        
                    } catch (WriteException ex) {
                        OptionPane.showMsg("Error inesperado", "Se generó un problema al crear las celdas de la planilla\n"
                                + "Detalle:"+ex, 3);
                    }
                }
            }
            
            woorbook.write();
            try {
                woorbook.close();
            } catch (WriteException ex) {
                OptionPane.showMsg("Error inesperado", "Se generó un problema al intentar cerrar libro de trabajo\n"
                                + "Detalle:"+ex, 3);
            }
            
        } catch (IOException ex) {
            OptionPane.showMsg("Error inesperado", "Se generó un problema al crear planilla Excel\n"
                                + "Detalle:"+ex, 3);
        }
    }
    
    private static void msgDone(){
        OptionPane.showMsg("Generación exitosa", "Archivo creado con exito.",1);
    }
}
