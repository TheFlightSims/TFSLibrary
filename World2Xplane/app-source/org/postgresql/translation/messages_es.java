/*    */ package org.postgresql.translation;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Hashtable;
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class messages_es extends ResourceBundle {
/*    */   private static final Hashtable table;
/*    */   
/*    */   static {
/*  6 */     Hashtable hashtable = new Hashtable();
/*  7 */     hashtable.put("", "Project-Id-Version: JDBC PostgreSQL Driver\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2004-10-22 13:45-0700\nPO-Revision-Date: 2004-10-22 16:51-0300\nLast-Translator: Diego Gil <diego@adminsa.com>\nLanguage-Team: \nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\nX-Poedit-Language: Spanish\n");
/*  8 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "Algo inusual ha ocurrido que provocó un fallo en el controlador. Por favor reporte esta excepción.");
/*  9 */     hashtable.put("This method is not yet implemented.", "Este método aún no ha sido implementado.");
/* 10 */     hashtable.put("No connection was able to be made for requested protocol {0}.", "No se pudo hacer una conexión para el protocolo solicitado {0}.");
/* 11 */     hashtable.put("Premature end of input stream, expected {0} bytes, but only read {1}.", "Final prematuro del flujo de entrada, se esperaban {0} bytes, pero solo se leyeron {1}.");
/* 12 */     hashtable.put("The driver does not support SSL.", "Este driver no soporta SSL.");
/* 13 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "Conexión rechazada. Verifique que el nombre del Host y el puerto sean correctos y que postmaster este aceptando conexiones TCP/IP.");
/* 14 */     hashtable.put("The connection attempt failed.", "El intento de conexión falló.");
/* 15 */     hashtable.put("The server does not support SSL.", "Este servidor no soporta SSL.");
/* 16 */     hashtable.put("An error occured while setting up the SSL connection.", "Ha ocorrido un error mientras se establecía la conexión SSL.");
/* 17 */     hashtable.put("Connection rejected: {0}.", "Conexión rechazada: {0}.");
/* 18 */     hashtable.put("The server requested password-based authentication, but no password was provided.", "El servidor requiere autenticación basada en contraseña, pero no se ha provisto ninguna contraseña.");
/* 19 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client's IP address or Subnet, and that it is using an authentication scheme supported by the driver.", "El tipo de autenticación {0} no es soportada. Verifique que ha configurado el archivo pg_hba.conf para incluir las direcciones IP de los clientes o la subred, y que está usando un esquema de autenticación soportado por el driver.");
/* 20 */     hashtable.put("Protocol error.  Session setup failed.", "Error de protocolo. Falló el inicio de la sesión.");
/* 21 */     hashtable.put("Backend start-up failed: {0}.", "Falló el arranque del Backend: {0}. ");
/* 22 */     hashtable.put("An unexpected result was returned by a query.", "Una consulta retornó un resultado inesperado.");
/* 23 */     hashtable.put("The column index is out of range: {0}, number of columns: {1}.", "El índice de la columna está fuera de rango: {0}, número de columnas: {1}.");
/* 24 */     hashtable.put("No value specified for parameter {0}.", "No se ha especificado un valor para el parámetro {0}.");
/* 25 */     hashtable.put("An I/O error occured while sending to the backend.", "Un error de E/S ha ocurrido mientras se enviaba al backend.");
/* 26 */     hashtable.put("Unknown Response Type {0}.", "Tipo de respuesta desconocida {0}.");
/* 27 */     hashtable.put("The array index is out of range: {0}, number of elements: {1}.", "El índice del arreglo esta fuera de rango: {0}, número de elementos: {1}.");
/* 28 */     hashtable.put("No results were returned by the query.", "La consulta no retornó ningún resultado.");
/* 29 */     hashtable.put("A result was returned when none was expected.", "Se retornó un resultado cuando no se esperaba ninguno.");
/* 30 */     hashtable.put("Failed to create object for: {0}.", "Fallo al crear objeto: {0}.");
/* 31 */     hashtable.put("The class {0} does not implement org.postgresql.util.PGobject.", "La clase {0} no implementa org.postgresql.util.PGobject.");
/* 32 */     hashtable.put("Server SQLState: {0}", "SQLState del servidor: {0}.");
/* 33 */     table = hashtable;
/*    */   }
/*    */   
/*    */   public Object handleGetObject(String paramString) throws MissingResourceException {
/* 36 */     return table.get(paramString);
/*    */   }
/*    */   
/*    */   public Enumeration getKeys() {
/* 39 */     return table.keys();
/*    */   }
/*    */   
/*    */   public ResourceBundle getParent() {
/* 42 */     return this.parent;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_es.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */