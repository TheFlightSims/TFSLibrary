/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public final class MapTransformer implements Transformer, Serializable {
/*    */   private static final long serialVersionUID = 862391807045468939L;
/*    */   
/*    */   private final Map iMap;
/*    */   
/*    */   public static Transformer getInstance(Map map) {
/* 50 */     if (map == null)
/* 51 */       return ConstantTransformer.NULL_INSTANCE; 
/* 53 */     return new MapTransformer(map);
/*    */   }
/*    */   
/*    */   private MapTransformer(Map map) {
/* 64 */     this.iMap = map;
/*    */   }
/*    */   
/*    */   public Object transform(Object input) {
/* 74 */     return this.iMap.get(input);
/*    */   }
/*    */   
/*    */   public Map getMap() {
/* 84 */     return this.iMap;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\MapTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */