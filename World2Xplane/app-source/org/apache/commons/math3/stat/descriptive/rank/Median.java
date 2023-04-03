/*    */ package org.apache.commons.math3.stat.descriptive.rank;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Median extends Percentile implements Serializable {
/*    */   private static final long serialVersionUID = -3961477041290915687L;
/*    */   
/*    */   public Median() {
/* 42 */     super(50.0D);
/*    */   }
/*    */   
/*    */   public Median(Median original) {
/* 52 */     super(original);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\rank\Median.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */