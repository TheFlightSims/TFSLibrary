/*    */ package org.geotools.util.logging;
/*    */ 
/*    */ import java.util.logging.Logger;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class Log4JLoggerFactory extends LoggerFactory<Logger> {
/*    */   private static Log4JLoggerFactory factory;
/*    */   
/*    */   protected Log4JLoggerFactory() throws NoClassDefFoundError {
/* 45 */     super(Logger.class);
/*    */   }
/*    */   
/*    */   public static synchronized Log4JLoggerFactory getInstance() throws NoClassDefFoundError {
/* 54 */     if (factory == null)
/* 55 */       factory = new Log4JLoggerFactory(); 
/* 57 */     return factory;
/*    */   }
/*    */   
/*    */   protected Logger getImplementation(String name) {
/* 65 */     return Logger.getLogger(name);
/*    */   }
/*    */   
/*    */   protected Logger wrap(String name, Logger implementation) {
/* 72 */     return new Log4JLogger(name, implementation);
/*    */   }
/*    */   
/*    */   protected Logger unwrap(Logger logger) {
/* 80 */     if (logger instanceof Log4JLogger)
/* 81 */       return ((Log4JLogger)logger).logger; 
/* 83 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\logging\Log4JLoggerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */