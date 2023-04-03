/*    */ package org.apache.commons.configuration.event;
/*    */ 
/*    */ public class ConfigurationErrorEvent extends ConfigurationEvent {
/*    */   private static final long serialVersionUID = -7433184493062648409L;
/*    */   
/*    */   private Throwable cause;
/*    */   
/*    */   public ConfigurationErrorEvent(Object source, int type, String propertyName, Object propertyValue, Throwable cause) {
/* 80 */     super(source, type, propertyName, propertyValue, true);
/* 81 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public Throwable getCause() {
/* 92 */     return this.cause;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\event\ConfigurationErrorEvent.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */