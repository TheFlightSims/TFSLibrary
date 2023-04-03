/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public class NOPTransformer implements Transformer, Serializable {
/*    */   private static final long serialVersionUID = 2133891748318574490L;
/*    */   
/* 37 */   public static final Transformer INSTANCE = new NOPTransformer();
/*    */   
/*    */   public static Transformer getInstance() {
/* 46 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public Object transform(Object input) {
/* 63 */     return input;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\NOPTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */