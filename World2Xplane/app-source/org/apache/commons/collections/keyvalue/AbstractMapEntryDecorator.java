/*    */ package org.apache.commons.collections.keyvalue;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections.KeyValue;
/*    */ 
/*    */ public abstract class AbstractMapEntryDecorator implements Map.Entry, KeyValue {
/*    */   protected final Map.Entry entry;
/*    */   
/*    */   public AbstractMapEntryDecorator(Map.Entry entry) {
/* 44 */     if (entry == null)
/* 45 */       throw new IllegalArgumentException("Map Entry must not be null"); 
/* 47 */     this.entry = entry;
/*    */   }
/*    */   
/*    */   protected Map.Entry getMapEntry() {
/* 56 */     return this.entry;
/*    */   }
/*    */   
/*    */   public Object getKey() {
/* 61 */     return this.entry.getKey();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 65 */     return this.entry.getValue();
/*    */   }
/*    */   
/*    */   public Object setValue(Object object) {
/* 69 */     return this.entry.setValue(object);
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 73 */     if (object == this)
/* 74 */       return true; 
/* 76 */     return this.entry.equals(object);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 80 */     return this.entry.hashCode();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 84 */     return this.entry.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\keyvalue\AbstractMapEntryDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */