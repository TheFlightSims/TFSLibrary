/*    */ package org.geotools.util;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Set;
/*    */ 
/*    */ final class NullObjectCache implements ObjectCache {
/* 36 */   public static final NullObjectCache INSTANCE = new NullObjectCache();
/*    */   
/*    */   public void clear() {}
/*    */   
/*    */   public Object get(Object key) {
/* 54 */     return null;
/*    */   }
/*    */   
/*    */   public Object peek(Object key) {
/* 61 */     return null;
/*    */   }
/*    */   
/*    */   public void put(Object key, Object object) {}
/*    */   
/*    */   public boolean containsKey(Object key) {
/* 74 */     return false;
/*    */   }
/*    */   
/*    */   public void writeLock(Object key) {}
/*    */   
/*    */   public void writeUnLock(Object key) {}
/*    */   
/*    */   public Set<Object> getKeys() {
/* 93 */     return Collections.emptySet();
/*    */   }
/*    */   
/*    */   public void remove(Object key) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\NullObjectCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */