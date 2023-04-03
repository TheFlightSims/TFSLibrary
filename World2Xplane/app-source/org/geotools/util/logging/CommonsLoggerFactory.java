/*    */ package org.geotools.util.logging;
/*    */ 
/*    */ import java.util.logging.Logger;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class CommonsLoggerFactory extends LoggerFactory<Log> {
/*    */   private static CommonsLoggerFactory factory;
/*    */   
/*    */   protected CommonsLoggerFactory() throws NoClassDefFoundError {
/* 48 */     super(Log.class);
/*    */   }
/*    */   
/*    */   public static synchronized CommonsLoggerFactory getInstance() throws NoClassDefFoundError {
/* 57 */     if (factory == null)
/* 58 */       factory = new CommonsLoggerFactory(); 
/* 60 */     return factory;
/*    */   }
/*    */   
/*    */   protected Log getImplementation(String name) {
/* 68 */     Log log = LogFactory.getLog(name);
/* 69 */     if (log instanceof org.apache.commons.logging.impl.Jdk14Logger)
/* 70 */       return null; 
/* 72 */     return log;
/*    */   }
/*    */   
/*    */   protected Logger wrap(String name, Log implementation) {
/* 79 */     return new CommonsLogger(name, implementation);
/*    */   }
/*    */   
/*    */   protected Log unwrap(Logger logger) {
/* 87 */     if (logger instanceof CommonsLogger)
/* 88 */       return ((CommonsLogger)logger).logger; 
/* 90 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\logging\CommonsLoggerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */