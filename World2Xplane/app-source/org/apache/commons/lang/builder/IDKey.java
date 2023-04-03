/*    */ package org.apache.commons.lang.builder;
/*    */ 
/*    */ final class IDKey {
/*    */   private final Object value;
/*    */   
/*    */   private final int id;
/*    */   
/*    */   public IDKey(Object _value) {
/* 42 */     this.id = System.identityHashCode(_value);
/* 46 */     this.value = _value;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 54 */     return this.id;
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 63 */     if (!(other instanceof IDKey))
/* 64 */       return false; 
/* 66 */     IDKey idKey = (IDKey)other;
/* 67 */     if (this.id != idKey.id)
/* 68 */       return false; 
/* 71 */     return (this.value == idKey.value);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\builder\IDKey.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */