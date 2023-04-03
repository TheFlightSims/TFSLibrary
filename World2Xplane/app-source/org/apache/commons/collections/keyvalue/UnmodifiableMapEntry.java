/*    */ package org.apache.commons.collections.keyvalue;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections.KeyValue;
/*    */ import org.apache.commons.collections.Unmodifiable;
/*    */ 
/*    */ public final class UnmodifiableMapEntry extends AbstractMapEntry implements Unmodifiable {
/*    */   public UnmodifiableMapEntry(Object key, Object value) {
/* 42 */     super(key, value);
/*    */   }
/*    */   
/*    */   public UnmodifiableMapEntry(KeyValue pair) {
/* 52 */     super(pair.getKey(), pair.getValue());
/*    */   }
/*    */   
/*    */   public UnmodifiableMapEntry(Map.Entry entry) {
/* 62 */     super(entry.getKey(), entry.getValue());
/*    */   }
/*    */   
/*    */   public Object setValue(Object value) {
/* 73 */     throw new UnsupportedOperationException("setValue() is not supported");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\keyvalue\UnmodifiableMapEntry.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */