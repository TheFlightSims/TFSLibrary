/*    */ package org.slf4j.helpers;
/*    */ 
/*    */ import java.io.ObjectStreamException;
/*    */ import java.io.Serializable;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ abstract class NamedLoggerBase implements Logger, Serializable {
/*    */   private static final long serialVersionUID = 7535258609338176893L;
/*    */   
/*    */   protected String name;
/*    */   
/*    */   public String getName() {
/* 23 */     return this.name;
/*    */   }
/*    */   
/*    */   protected Object readResolve() throws ObjectStreamException {
/* 43 */     return LoggerFactory.getLogger(getName());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\slf4j\helpers\NamedLoggerBase.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */