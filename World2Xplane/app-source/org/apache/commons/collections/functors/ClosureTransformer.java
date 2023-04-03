/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Closure;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public class ClosureTransformer implements Transformer, Serializable {
/*    */   private static final long serialVersionUID = 478466901448617286L;
/*    */   
/*    */   private final Closure iClosure;
/*    */   
/*    */   public static Transformer getInstance(Closure closure) {
/* 49 */     if (closure == null)
/* 50 */       throw new IllegalArgumentException("Closure must not be null"); 
/* 52 */     return new ClosureTransformer(closure);
/*    */   }
/*    */   
/*    */   public ClosureTransformer(Closure closure) {
/* 63 */     this.iClosure = closure;
/*    */   }
/*    */   
/*    */   public Object transform(Object input) {
/* 73 */     this.iClosure.execute(input);
/* 74 */     return input;
/*    */   }
/*    */   
/*    */   public Closure getClosure() {
/* 84 */     return this.iClosure;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\ClosureTransformer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */