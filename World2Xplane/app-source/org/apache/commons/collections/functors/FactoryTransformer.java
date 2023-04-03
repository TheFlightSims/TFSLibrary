/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Factory;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public class FactoryTransformer implements Transformer, Serializable {
/*    */   private static final long serialVersionUID = -6817674502475353160L;
/*    */   
/*    */   private final Factory iFactory;
/*    */   
/*    */   public static Transformer getInstance(Factory factory) {
/* 48 */     if (factory == null)
/* 49 */       throw new IllegalArgumentException("Factory must not be null"); 
/* 51 */     return new FactoryTransformer(factory);
/*    */   }
/*    */   
/*    */   public FactoryTransformer(Factory factory) {
/* 62 */     this.iFactory = factory;
/*    */   }
/*    */   
/*    */   public Object transform(Object input) {
/* 73 */     return this.iFactory.create();
/*    */   }
/*    */   
/*    */   public Factory getFactory() {
/* 83 */     return this.iFactory;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\FactoryTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */