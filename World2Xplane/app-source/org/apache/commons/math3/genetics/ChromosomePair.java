/*    */ package org.apache.commons.math3.genetics;
/*    */ 
/*    */ public class ChromosomePair {
/*    */   private final Chromosome first;
/*    */   
/*    */   private final Chromosome second;
/*    */   
/*    */   public ChromosomePair(Chromosome c1, Chromosome c2) {
/* 40 */     this.first = c1;
/* 41 */     this.second = c2;
/*    */   }
/*    */   
/*    */   public Chromosome getFirst() {
/* 50 */     return this.first;
/*    */   }
/*    */   
/*    */   public Chromosome getSecond() {
/* 59 */     return this.second;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 67 */     return String.format("(%s,%s)", new Object[] { getFirst(), getSecond() });
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\ChromosomePair.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */