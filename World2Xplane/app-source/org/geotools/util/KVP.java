/*    */ package org.geotools.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ 
/*    */ public class KVP extends LinkedHashMap<String, Object> {
/*    */   private static final long serialVersionUID = -387821381125137128L;
/*    */   
/*    */   public KVP(Object... pairs) {
/* 32 */     if ((pairs.length & 0x1) != 0)
/* 33 */       throw new IllegalArgumentException("Pairs was not an even number"); 
/* 35 */     for (int i = 0; i < pairs.length; i += 2) {
/* 36 */       String key = (String)pairs[i];
/* 37 */       Object value = pairs[i + 1];
/* 38 */       add(key, value);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void add(String key, Object value) {
/* 49 */     if (containsKey(key)) {
/* 50 */       Object existing = get(key);
/* 51 */       if (existing instanceof List) {
/* 52 */         List<Object> list = (List<Object>)existing;
/* 53 */         list.add(value);
/*    */       } else {
/* 56 */         List<Object> list = new ArrayList();
/* 57 */         list.add(existing);
/* 58 */         list.add(value);
/* 59 */         put(key, list);
/*    */       } 
/*    */     } else {
/* 63 */       put(key, value);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\KVP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */