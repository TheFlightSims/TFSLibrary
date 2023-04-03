/*    */ package org.apache.commons.collections.keyvalue;
/*    */ 
/*    */ import org.apache.commons.collections.KeyValue;
/*    */ 
/*    */ public abstract class AbstractKeyValue implements KeyValue {
/*    */   protected Object key;
/*    */   
/*    */   protected Object value;
/*    */   
/*    */   protected AbstractKeyValue(Object key, Object value) {
/* 48 */     this.key = key;
/* 49 */     this.value = value;
/*    */   }
/*    */   
/*    */   public Object getKey() {
/* 58 */     return this.key;
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 67 */     return this.value;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 76 */     return getKey() + '=' + getValue();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\keyvalue\AbstractKeyValue.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */