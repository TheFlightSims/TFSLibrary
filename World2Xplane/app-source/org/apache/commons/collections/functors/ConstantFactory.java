/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Factory;
/*    */ 
/*    */ public class ConstantFactory implements Factory, Serializable {
/*    */   private static final long serialVersionUID = -3520677225766901240L;
/*    */   
/* 41 */   public static final Factory NULL_INSTANCE = new ConstantFactory(null);
/*    */   
/*    */   private final Object iConstant;
/*    */   
/*    */   public static Factory getInstance(Object constantToReturn) {
/* 53 */     if (constantToReturn == null)
/* 54 */       return NULL_INSTANCE; 
/* 56 */     return new ConstantFactory(constantToReturn);
/*    */   }
/*    */   
/*    */   public ConstantFactory(Object constantToReturn) {
/* 67 */     this.iConstant = constantToReturn;
/*    */   }
/*    */   
/*    */   public Object create() {
/* 76 */     return this.iConstant;
/*    */   }
/*    */   
/*    */   public Object getConstant() {
/* 86 */     return this.iConstant;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\ConstantFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */