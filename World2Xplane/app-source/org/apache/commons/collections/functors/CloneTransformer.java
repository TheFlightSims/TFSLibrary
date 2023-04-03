/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public class CloneTransformer implements Transformer, Serializable {
/*    */   private static final long serialVersionUID = -8188742709499652567L;
/*    */   
/* 39 */   public static final Transformer INSTANCE = new CloneTransformer();
/*    */   
/*    */   public static Transformer getInstance() {
/* 48 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public Object transform(Object input) {
/* 65 */     if (input == null)
/* 66 */       return null; 
/* 68 */     return PrototypeFactory.getInstance(input).create();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\CloneTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */