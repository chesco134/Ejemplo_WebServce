/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_webservice;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Alfonso 7
 */

public class Local {
    
    public void subir() throws JSONException{
    try {
            // TODO code application logic here
            String content = "{ \"votacion\": [ { \"institucion\": 86,  \"vhash\": \"1234567\", \"titulo\": \"Auscultación SSEIS UPIITA\", \"descripcion\": \"Auscultación para Subdirector de Servicios Educativos e Integración Social\", \"fecha_in\": \"2016-04-19 10:00:00\", \"fecha_fin\": \"2016-04-20 18:00:00\", \"matricula\": \"2334\", \"total_p\": 434, \"tasa_p\": \"18.59%\", \"estado\": \"Visible\", \"grupos\": { \"data\": [ { \"nombre\": \"Alumnos\", \"preguntas\": { \"data\": [ { \"titulo_pregunta\": \"Subdirector de Servicios Educativos e Integración Social\", \"opciones\": { \"data\": [ { \"name\": \"Lic. Miguel Ranferi Silva Millán\", \"y\": 286 }, { \"name\": \"M. en C. Álvaro Gordillo Sol\", \"y\": 62 }, { \"name\": \"M. en E. Maricruz Trejo Salazar\", \"y\": 86 } ] } } ] } } ] } } ] }";
            HttpURLConnection con = (HttpURLConnection) new URL("http://www.votacionesipn.com/WebServices/subir_local.php").openConnection();
            //HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:80/WebServices/Ejemplo1/ws1.php").openConnection();
            con.setDoOutput(true);
            DataOutputStream salida = new DataOutputStream(con.getOutputStream());
            salida.write(content.getBytes());
            salida.flush();
            int length;
            byte[] chunk = new byte[64];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataInputStream entrada = new DataInputStream(con.getInputStream());
            while((length = entrada.read(chunk)) != -1)
                baos.write(chunk,0,length);
            String response = URLDecoder.decode(baos.toString(), "utf8").trim();
            System.out.println("Server dijo (" + baos.size() + " bytes): " +response);
            JSONObject miJ = new JSONObject(response.substring(1, response.length()));
        switch (miJ.getString("operacion")) {
            case "Existe":
                System.out.println("Esta votación ya ha sido compartida en el sitio web. ");
                System.out.println("Puedes consultar el resultado en el siguiente enlace:");
                System.out.println(miJ.getString("link"));
                break;
            case "Correcto":
                System.out.println("La votación se ha compartido en el sitio web exitosamente. ");
                System.out.println("Puedes consultar el resultado en el siguiente enlace:");
                System.out.println(miJ.getString("link"));
                break;
            case "Error":
                System.out.println("Error al ejecutar query");
                break;
        }
            baos.close();
            con.disconnect();
            entrada.close();
            salida.close();
        }catch(IOException e){
            System.out.println("Error: "+e);
        }
    }
}
