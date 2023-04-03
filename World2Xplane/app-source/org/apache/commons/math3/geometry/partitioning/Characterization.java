/*    */ package org.apache.commons.math3.geometry.partitioning;
/*    */ 
/*    */ import org.apache.commons.math3.geometry.Space;
/*    */ 
/*    */ class Characterization<S extends Space> {
/* 37 */   private SubHyperplane<S> in = null;
/*    */   
/* 38 */   private SubHyperplane<S> out = null;
/*    */   
/*    */   public boolean hasIn() {
/* 45 */     return (this.in != null && !this.in.isEmpty());
/*    */   }
/*    */   
/*    */   public SubHyperplane<S> getIn() {
/* 52 */     return this.in;
/*    */   }
/*    */   
/*    */   public boolean hasOut() {
/* 59 */     return (this.out != null && !this.out.isEmpty());
/*    */   }
/*    */   
/*    */   public SubHyperplane<S> getOut() {
/* 66 */     return this.out;
/*    */   }
/*    */   
/*    */   public void add(SubHyperplane<S> sub, boolean inside) {
/* 75 */     if (inside) {
/* 76 */       if (this.in == null) {
/* 77 */         this.in = sub;
/*    */       } else {
/* 79 */         this.in = this.in.reunite(sub);
/*    */       } 
/* 82 */     } else if (this.out == null) {
/* 83 */       this.out = sub;
/*    */     } else {
/* 85 */       this.out = this.out.reunite(sub);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\Characterization.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */