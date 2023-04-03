/*    */ package org.apache.commons.collections.keyvalue;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections.KeyValue;
/*    */ 
/*    */ public final class DefaultMapEntry extends AbstractMapEntry {
/*    */   public DefaultMapEntry(Object key, Object value) {
/* 44 */     super(key, value);
/*    */   }
/*    */   
/*    */   public DefaultMapEntry(KeyValue pair) {
/* 54 */     super(pair.getKey(), pair.getValue());
/*    */   }
/*    */   
/*    */   public DefaultMapEntry(Map.Entry entry) {
/* 64 */     super(entry.getKey(), entry.getValue());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\keyvalue\DefaultMapEntry.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */