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
public class Global {
            
    public void subir() throws JSONException{
    try {
            String content = "{ \"votacion\": [ {\"titulo\": \"VOTACION CON WEB SERVICES 21\",  \"vhash\": \"1234567\", \"descripcion\": \"Ejemplo descripción 1\", \"fecha_in\": \"2015-02-01 10:04:21\", \"fecha_fin\": \"2015-02-11 14:09:45\", \"matricula\": \"Sin información\", \"total_p\": 879, \"tasa_p\": \"Sin información\", \"estado\": \"Visible\", \"grupos\": { \"data\": [ { \"nombre\": \"Alumnos\", \"preguntas\": { \"data\": [ { \"titulo_pregunta\": \"Titulo Pregunta 1\", \"opciones\": { \"data\": [ { \"name\": \"Titulo Opcion 1\", \"y\": 100 } ] } }, { \"titulo_pregunta\": \"Titulo Pregunta 2\", \"opciones\": { \"data\": [ { \"name\": \"Titulo Opcion 1\", \"y\": 60 }, { \"name\": \"Titulo Opcion 2\", \"y\": 40 } ] } }, { \"titulo_pregunta\": \"Titulo Pregunta 3\", \"opciones\": { \"data\": [ { \"name\": \"Titulo Opcion 1\", \"y\": 40 }, { \"name\": \"Titulo Opcion 2\", \"y\": 40 }, { \"name\": \"Titulo Opcion 3\", \"y\": 20 } ] } }, { \"titulo_pregunta\": \"Titulo Pregunta 4\", \"opciones\": { \"data\": [ { \"name\": \"Titulo Opcion 1\", \"y\": 30 }, { \"name\": \"Titulo Opcion 2\", \"y\": 25 }, { \"name\": \"Titulo Opcion 3\", \"y\": 15 }, { \"name\": \"Titulo Opcion 4\", \"y\": 30 } ] } } ] } }, { \"nombre\": \"Profesores\", \"preguntas\": { \"data\": [ { \"titulo_pregunta\": \"Titulo Pregunta 1\", \"opciones\": { \"data\": [ { \"name\": \"Titulo Opcion 1\", \"y\": 100 } ] } }, { \"titulo_pregunta\": \"Titulo Pregunta 2\", \"opciones\": { \"data\": [ { \"name\": \"Titulo Opcion 1\", \"y\": 40 }, { \"name\": \"Titulo Opcion 2\", \"y\": 60 } ] } }, { \"titulo_pregunta\": \"Titulo Pregunta 3\", \"opciones\": { \"data\": [ { \"name\": \"Titulo Opcion 1\", \"y\": 20 }, { \"name\": \"Titulo Opcion 2\", \"y\": 40 }, { \"name\": \"Titulo Opcion 3\", \"y\": 40 } ] } }, { \"titulo_pregunta\": \"Titulo Pregunta 4\", \"opciones\": { \"data\": [ { \"name\": \"Titulo Opcion 1\", \"y\": 30 }, { \"name\": \"Titulo Opcion 2\", \"y\": 15 }, { \"name\": \"Titulo Opcion 3\", \"y\": 25 }, { \"name\": \"Titulo Opcion 4\", \"y\": 30 }, { \"name\": \"Titulo Opcion 5\", \"y\": 30 }, { \"name\": \"Titulo Opcion 6\", \"y\": 15 }, { \"name\": \"Titulo Opcion 7\", \"y\": 25 }, { \"name\": \"Titulo Opcion 8\", \"y\": 30 }, { \"name\": \"Titulo Opcion 9\", \"y\": 30 }, { \"name\": \"Titulo Opcion 10\", \"y\": 15 }, { \"name\": \"Titulo Opcion 11\", \"y\": 25 }, { \"name\": \"Titulo Opcion 12\", \"y\": 30 } ] } } ] } } ] } } ] }";
            HttpURLConnection con = (HttpURLConnection) new URL("http://www.votacionesipn.com/WebServices/subir_global.php").openConnection();
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
                System.out.println("Puedes consultar el resultado global en el siguiente enlace:");
                System.out.println(miJ.getString("link"));
                System.out.println(miJ.getString("idVotacion"));
                break;
            case "Correcto":
                System.out.println("La votación se ha compartido en el sitio web exitosamente. ");
                System.out.println("Puedes consultar el resultado global en el siguiente enlace:");
                System.out.println(miJ.getString("link"));
                System.out.println(miJ.getString("idVotacion"));
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
