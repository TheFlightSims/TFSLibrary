/*    */ package org.postgresql.util;
/*    */ 
/*    */ import java.net.URL;
/*    */ import org.postgresql.Driver;
/*    */ 
/*    */ public class PSQLDriverVersion {
/*    */   public static final int buildNumber = 901;
/*    */   
/*    */   public static void main(String[] args) {
/* 27 */     URL url = Driver.class.getResource("/org/postgresql/Driver.class");
/* 28 */     System.out.println(Driver.getVersion());
/* 29 */     System.out.println("Found in: " + url);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\PSQLDriverVersion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */