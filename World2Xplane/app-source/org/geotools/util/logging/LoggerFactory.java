/*    */ package org.geotools.util.logging;
/*    */ 
/*    */ import java.util.logging.Logger;
/*    */ import org.geotools.util.WeakValueHashMap;
/*    */ 
/*    */ public abstract class LoggerFactory<L> {
/*    */   private final Class<L> loggerClass;
/*    */   
/*    */   private final WeakValueHashMap<String, Logger> loggers;
/*    */   
/*    */   protected LoggerFactory(Class<L> loggerClass) {
/* 58 */     this.loggerClass = loggerClass;
/* 59 */     this.loggers = new WeakValueHashMap();
/*    */   }
/*    */   
/*    */   public Logger getLogger(String name) {
/* 79 */     L target = getImplementation(name);
/* 80 */     if (target == null)
/* 81 */       return null; 
/* 83 */     synchronized (this.loggers) {
/* 84 */       Logger logger = (Logger)this.loggers.get(name);
/* 85 */       if (logger == null || !target.equals(unwrap(logger))) {
/* 86 */         logger = wrap(name, target);
/* 87 */         this.loggers.put(name, logger);
/*    */       } 
/* 89 */       return logger;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Class<L> getImplementationClass() {
/* 98 */     return this.loggerClass;
/*    */   }
/*    */   
/*    */   protected abstract L getImplementation(String paramString);
/*    */   
/*    */   protected abstract Logger wrap(String paramString, L paramL);
/*    */   
/*    */   protected abstract L unwrap(Logger paramLogger);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\logging\LoggerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */