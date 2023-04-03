/*    */ package org.postgresql.translation;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Hashtable;
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class messages_nl extends ResourceBundle {
/*    */   private static final Hashtable table;
/*    */   
/*    */   static {
/*  6 */     Hashtable hashtable = new Hashtable();
/*  7 */     hashtable.put("", "Project-Id-Version: PostgreSQL JDBC Driver 8.0\nReport-Msgid-Bugs-To: \nPOT-Creation-Date: 2004-10-11 23:55-0700\nPO-Revision-Date: 2004-10-11 23:55-0700\nLast-Translator: Arnout Kuiper <ajkuiper@wxs.nl>\nLanguage-Team: Dutch <ajkuiper@wxs.nl>\nMIME-Version: 1.0\nContent-Type: text/plain; charset=UTF-8\nContent-Transfer-Encoding: 8bit\n");
/*  8 */     hashtable.put("Something unusual has occured to cause the driver to fail. Please report this exception.", "Iets ongewoons is opgetreden, wat deze driver doet falen. Rapporteer deze fout AUB: {0}");
/*  9 */     hashtable.put("This method is not yet implemented.", "Deze methode is nog niet geimplementeerd");
/* 10 */     hashtable.put("Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.", "Verbinding geweigerd. Controleer dat de hostnaam en poort correct zijn, en dat de postmaster is opgestart met de -i vlag, welke TCP/IP networking aanzet.");
/* 11 */     hashtable.put("The authentication type {0} is not supported. Check that you have configured the pg_hba.conf file to include the client's IP address or Subnet, and that it is using an authentication scheme supported by the driver.", "Het authenticatie type {0} wordt niet ondersteund. Controleer dat het IP adres of subnet van de client is geconfigureerd in de pg_hba.conf file, en dat het een authenticatie protocol gebruikt dat door de driver ondersteund wordt.");
/* 12 */     hashtable.put("An unexpected result was returned by a query.", "Een onverwacht resultaat werd teruggegeven door een query");
/* 13 */     hashtable.put("Fastpath call {0} - No result was returned and we expected an integer.", "Fastpath aanroep {0} - Geen resultaat werd teruggegeven, terwijl we een integer verwacht hadden.");
/* 14 */     hashtable.put("The fastpath function {0} is unknown.", "De fastpath functie {0} is onbekend.");
/* 15 */     hashtable.put("No results were returned by the query.", "Geen resultaten werden teruggegeven door de query.");
/* 16 */     hashtable.put("Unknown Types value.", "Onbekende Types waarde.");
/* 17 */     table = hashtable;
/*    */   }
/*    */   
/*    */   public Object handleGetObject(String paramString) throws MissingResourceException {
/* 20 */     return table.get(paramString);
/*    */   }
/*    */   
/*    */   public Enumeration getKeys() {
/* 23 */     return table.keys();
/*    */   }
/*    */   
/*    */   public ResourceBundle getParent() {
/* 26 */     return this.parent;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\translation\messages_nl.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */