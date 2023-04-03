/*    */ package org.geotools.resources;
/*    */ 
/*    */ import java.sql.Driver;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.LogRecord;
/*    */ import org.geotools.resources.i18n.Loggings;
/*    */ 
/*    */ public final class JDBC {
/* 44 */   private static final Set<String> DRIVERS = new HashSet<String>();
/*    */   
/*    */   public static LogRecord loadDriver(String driver) {
/* 66 */     LogRecord log = null;
/* 67 */     if (driver != null)
/* 68 */       synchronized (DRIVERS) {
/* 69 */         if (!DRIVERS.contains(driver))
/*    */           try {
/* 71 */             Driver d = (Driver)Class.forName(driver).newInstance();
/* 72 */             log = Loggings.format(Level.CONFIG, 26, driver, Integer.valueOf(d.getMajorVersion()), Integer.valueOf(d.getMinorVersion()));
/* 74 */             DRIVERS.add(driver);
/* 75 */           } catch (Exception exception) {
/* 76 */             log = new LogRecord(Level.WARNING, exception.toString());
/*    */           }  
/*    */       }  
/* 81 */     return log;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\JDBC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */