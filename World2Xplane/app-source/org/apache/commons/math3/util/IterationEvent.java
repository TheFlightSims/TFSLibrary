/*    */ package org.apache.commons.math3.util;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ 
/*    */ public class IterationEvent extends EventObject {
/*    */   private static final long serialVersionUID = 20120128L;
/*    */   
/*    */   private final int iterations;
/*    */   
/*    */   public IterationEvent(Object source, int iterations) {
/* 43 */     super(source);
/* 44 */     this.iterations = iterations;
/*    */   }
/*    */   
/*    */   public int getIterations() {
/* 54 */     return this.iterations;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\IterationEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */