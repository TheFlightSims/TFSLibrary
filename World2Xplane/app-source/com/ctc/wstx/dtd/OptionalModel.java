/*    */ package com.ctc.wstx.dtd;
/*    */ 
/*    */ import java.util.BitSet;
/*    */ import java.util.List;
/*    */ 
/*    */ public class OptionalModel extends ModelNode {
/*    */   ModelNode mModel;
/*    */   
/*    */   public OptionalModel(ModelNode model) {
/* 23 */     this.mModel = model;
/*    */   }
/*    */   
/*    */   public ModelNode cloneModel() {
/* 37 */     return new OptionalModel(this.mModel.cloneModel());
/*    */   }
/*    */   
/*    */   public boolean isNullable() {
/* 41 */     return true;
/*    */   }
/*    */   
/*    */   public void indexTokens(List tokens) {
/* 45 */     this.mModel.indexTokens(tokens);
/*    */   }
/*    */   
/*    */   public void addFirstPos(BitSet pos) {
/* 49 */     this.mModel.addFirstPos(pos);
/*    */   }
/*    */   
/*    */   public void addLastPos(BitSet pos) {
/* 53 */     this.mModel.addLastPos(pos);
/*    */   }
/*    */   
/*    */   public void calcFollowPos(BitSet[] followPosSets) {
/* 59 */     this.mModel.calcFollowPos(followPosSets);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 63 */     return this.mModel + "[?]";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\OptionalModel.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */