/*     */ package org.apache.commons.configuration.event;
/*     */ 
/*     */ import java.util.EventObject;
/*     */ 
/*     */ public class ConfigurationEvent extends EventObject {
/*     */   private static final long serialVersionUID = 3277238219073504136L;
/*     */   
/*     */   private int type;
/*     */   
/*     */   private String propertyName;
/*     */   
/*     */   private Object propertyValue;
/*     */   
/*     */   private boolean beforeUpdate;
/*     */   
/*     */   public ConfigurationEvent(Object source, int type, String propertyName, Object propertyValue, boolean beforeUpdate) {
/* 110 */     super(source);
/* 111 */     this.type = type;
/* 112 */     this.propertyName = propertyName;
/* 113 */     this.propertyValue = propertyValue;
/* 114 */     this.beforeUpdate = beforeUpdate;
/*     */   }
/*     */   
/*     */   public String getPropertyName() {
/* 125 */     return this.propertyName;
/*     */   }
/*     */   
/*     */   public Object getPropertyValue() {
/* 135 */     return this.propertyValue;
/*     */   }
/*     */   
/*     */   public int getType() {
/* 146 */     return this.type;
/*     */   }
/*     */   
/*     */   public boolean isBeforeUpdate() {
/* 157 */     return this.beforeUpdate;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\event\ConfigurationEvent.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */