/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_webservice;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;




/**
 *
 * @author Alfonso 7
 */
public class Ejemplo_WebService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        try {
            /*
            * Subir Votacion Local
            */
            //Local local = new Local();
            //local.subir();
            /*
            * Subir Votacion Global
            */
            //Global global = new Global();
            //global.subir();
            /*
            * Subir Votacion Global por institucion
            */
            Local_Global loc_glob = new Local_Global();
            loc_glob.subir();
            
        } catch (JSONException ex) {
            Logger.getLogger(Ejemplo_WebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
