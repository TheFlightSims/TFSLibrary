/*    */ package org.apache.commons.collections.keyvalue;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public abstract class AbstractMapEntry extends AbstractKeyValue implements Map.Entry {
/*    */   protected AbstractMapEntry(Object key, Object value) {
/* 42 */     super(key, value);
/*    */   }
/*    */   
/*    */   public Object setValue(Object value) {
/* 57 */     Object answer = this.value;
/* 58 */     this.value = value;
/* 59 */     return answer;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 71 */     if (obj == this)
/* 72 */       return true; 
/* 74 */     if (!(obj instanceof Map.Entry))
/* 75 */       return false; 
/* 77 */     Map.Entry other = (Map.Entry)obj;
/* 78 */     return (((getKey() == null) ? (other.getKey() == null) : getKey().equals(other.getKey())) && ((getValue() == null) ? (other.getValue() == null) : getValue().equals(other.getValue())));
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 91 */     return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\keyvalue\AbstractMapEntry.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */