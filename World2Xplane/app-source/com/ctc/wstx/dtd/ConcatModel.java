/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ConcatModel extends ModelNode {
/*     */   ModelNode mLeftModel;
/*     */   
/*     */   ModelNode mRightModel;
/*     */   
/*     */   final boolean mNullable;
/*     */   
/*     */   BitSet mFirstPos;
/*     */   
/*     */   BitSet mLastPos;
/*     */   
/*     */   public ConcatModel(ModelNode left, ModelNode right) {
/*  28 */     this.mLeftModel = left;
/*  29 */     this.mRightModel = right;
/*  30 */     this.mNullable = (this.mLeftModel.isNullable() && this.mRightModel.isNullable());
/*     */   }
/*     */   
/*     */   public ModelNode cloneModel() {
/*  44 */     return new ConcatModel(this.mLeftModel.cloneModel(), this.mRightModel.cloneModel());
/*     */   }
/*     */   
/*     */   public boolean isNullable() {
/*  48 */     return this.mNullable;
/*     */   }
/*     */   
/*     */   public void indexTokens(List tokens) {
/*  53 */     this.mLeftModel.indexTokens(tokens);
/*  54 */     this.mRightModel.indexTokens(tokens);
/*     */   }
/*     */   
/*     */   public void addFirstPos(BitSet pos) {
/*  58 */     if (this.mFirstPos == null) {
/*  59 */       this.mFirstPos = new BitSet();
/*  60 */       this.mLeftModel.addFirstPos(this.mFirstPos);
/*  61 */       if (this.mLeftModel.isNullable())
/*  62 */         this.mRightModel.addFirstPos(this.mFirstPos); 
/*     */     } 
/*  65 */     pos.or(this.mFirstPos);
/*     */   }
/*     */   
/*     */   public void addLastPos(BitSet pos) {
/*  69 */     if (this.mLastPos == null) {
/*  70 */       this.mLastPos = new BitSet();
/*  71 */       this.mRightModel.addLastPos(this.mLastPos);
/*  72 */       if (this.mRightModel.isNullable())
/*  73 */         this.mLeftModel.addLastPos(this.mLastPos); 
/*     */     } 
/*  76 */     pos.or(this.mLastPos);
/*     */   }
/*     */   
/*     */   public void calcFollowPos(BitSet[] followPosSets) {
/*  82 */     this.mLeftModel.calcFollowPos(followPosSets);
/*  83 */     this.mRightModel.calcFollowPos(followPosSets);
/*  89 */     BitSet foll = new BitSet();
/*  90 */     this.mRightModel.addFirstPos(foll);
/*  92 */     BitSet toAddTo = new BitSet();
/*  93 */     this.mLeftModel.addLastPos(toAddTo);
/*  95 */     int ix = 0;
/*  96 */     while ((ix = toAddTo.nextSetBit(ix + 1)) >= 0)
/* 100 */       followPosSets[ix].or(foll); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 106 */     StringBuffer sb = new StringBuffer();
/* 107 */     sb.append('(');
/* 108 */     sb.append(this.mLeftModel.toString());
/* 109 */     sb.append(", ");
/* 110 */     sb.append(this.mRightModel.toString());
/* 111 */     sb.append(')');
/* 112 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\ConcatModel.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */