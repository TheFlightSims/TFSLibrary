/*    */ package org.postgresql.core;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.sql.DriverManager;
/*    */ import java.text.FieldPosition;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ public final class Logger {
/* 28 */   private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS ");
/*    */   
/* 29 */   private final FieldPosition dummyPosition = new FieldPosition(0);
/*    */   
/* 30 */   private final StringBuffer buffer = new StringBuffer();
/*    */   
/*    */   private final String connectionIDString;
/*    */   
/* 33 */   private int level = 0;
/*    */   
/*    */   public Logger() {
/* 36 */     this.connectionIDString = "(driver) ";
/*    */   }
/*    */   
/*    */   public Logger(int connectionID) {
/* 40 */     this.connectionIDString = "(" + connectionID + ") ";
/*    */   }
/*    */   
/*    */   public void setLogLevel(int level) {
/* 44 */     this.level = level;
/*    */   }
/*    */   
/*    */   public int getLogLevel() {
/* 48 */     return this.level;
/*    */   }
/*    */   
/*    */   public boolean logDebug() {
/* 52 */     return (this.level >= 2);
/*    */   }
/*    */   
/*    */   public boolean logInfo() {
/* 56 */     return (this.level >= 1);
/*    */   }
/*    */   
/*    */   public void debug(String str) {
/* 60 */     debug(str, null);
/*    */   }
/*    */   
/*    */   public void debug(String str, Throwable t) {
/* 64 */     if (logDebug())
/* 65 */       log(str, t); 
/*    */   }
/*    */   
/*    */   public void info(String str) {
/* 69 */     info(str, null);
/*    */   }
/*    */   
/*    */   public void info(String str, Throwable t) {
/* 73 */     if (logInfo())
/* 74 */       log(str, t); 
/*    */   }
/*    */   
/*    */   public void log(String str, Throwable t) {
/* 78 */     PrintWriter writer = DriverManager.getLogWriter();
/* 79 */     if (writer == null)
/*    */       return; 
/* 82 */     synchronized (this) {
/* 83 */       this.buffer.setLength(0);
/* 84 */       this.dateFormat.format(new Date(), this.buffer, this.dummyPosition);
/* 85 */       this.buffer.append(this.connectionIDString);
/* 86 */       this.buffer.append(str);
/* 90 */       synchronized (writer) {
/* 91 */         writer.println(this.buffer.toString());
/* 92 */         if (t != null)
/* 93 */           t.printStackTrace(writer); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\Logger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */