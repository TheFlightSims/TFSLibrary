/*    */ package com.ctc.wstx.dtd;
/*    */ 
/*    */ import java.util.BitSet;
/*    */ import java.util.List;
/*    */ 
/*    */ public class StarModel extends ModelNode {
/*    */   ModelNode mModel;
/*    */   
/*    */   public StarModel(ModelNode model) {
/* 22 */     this.mModel = model;
/*    */   }
/*    */   
/*    */   public ModelNode cloneModel() {
/* 36 */     return new StarModel(this.mModel.cloneModel());
/*    */   }
/*    */   
/*    */   public boolean isNullable() {
/* 40 */     return true;
/*    */   }
/*    */   
/*    */   public void indexTokens(List tokens) {
/* 44 */     this.mModel.indexTokens(tokens);
/*    */   }
/*    */   
/*    */   public void addFirstPos(BitSet pos) {
/* 48 */     this.mModel.addFirstPos(pos);
/*    */   }
/*    */   
/*    */   public void addLastPos(BitSet pos) {
/* 52 */     this.mModel.addLastPos(pos);
/*    */   }
/*    */   
/*    */   public void calcFollowPos(BitSet[] followPosSets) {
/* 58 */     this.mModel.calcFollowPos(followPosSets);
/* 64 */     BitSet foll = new BitSet();
/* 65 */     this.mModel.addFirstPos(foll);
/* 67 */     BitSet toAddTo = new BitSet();
/* 68 */     this.mModel.addLastPos(toAddTo);
/* 70 */     int ix = 0;
/* 71 */     while ((ix = toAddTo.nextSetBit(ix + 1)) >= 0)
/* 75 */       followPosSets[ix].or(foll); 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 80 */     return this.mModel.toString() + "*";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\StarModel.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */