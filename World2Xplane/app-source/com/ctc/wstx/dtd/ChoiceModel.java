/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ChoiceModel extends ModelNode {
/*     */   final ModelNode[] mSubModels;
/*     */   
/*     */   boolean mNullable = false;
/*     */   
/*     */   BitSet mFirstPos;
/*     */   
/*     */   BitSet mLastPos;
/*     */   
/*     */   protected ChoiceModel(ModelNode[] subModels) {
/*  27 */     this.mSubModels = subModels;
/*  28 */     boolean nullable = false;
/*  29 */     for (int i = 0, len = subModels.length; i < len; i++) {
/*  30 */       if (subModels[i].isNullable()) {
/*  31 */         nullable = true;
/*     */         break;
/*     */       } 
/*     */     } 
/*  35 */     this.mNullable = nullable;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  46 */     StringBuffer sb = new StringBuffer();
/*  47 */     for (int i = 0; i < this.mSubModels.length; i++) {
/*  48 */       if (i > 0)
/*  49 */         sb.append(" | "); 
/*  51 */       sb.append(this.mSubModels[i].toString());
/*     */     } 
/*  53 */     sb.append(')');
/*  54 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public ModelNode cloneModel() {
/*  63 */     int len = this.mSubModels.length;
/*  64 */     ModelNode[] newModels = new ModelNode[len];
/*  65 */     for (int i = 0; i < len; i++)
/*  66 */       newModels[i] = this.mSubModels[i].cloneModel(); 
/*  68 */     return new ChoiceModel(newModels);
/*     */   }
/*     */   
/*     */   public boolean isNullable() {
/*  72 */     return this.mNullable;
/*     */   }
/*     */   
/*     */   public void indexTokens(List tokens) {
/*  78 */     for (int i = 0, len = this.mSubModels.length; i < len; i++)
/*  79 */       this.mSubModels[i].indexTokens(tokens); 
/*     */   }
/*     */   
/*     */   public void addFirstPos(BitSet firstPos) {
/*  84 */     if (this.mFirstPos == null) {
/*  85 */       this.mFirstPos = new BitSet();
/*  86 */       for (int i = 0, len = this.mSubModels.length; i < len; i++)
/*  87 */         this.mSubModels[i].addFirstPos(this.mFirstPos); 
/*     */     } 
/*  90 */     firstPos.or(this.mFirstPos);
/*     */   }
/*     */   
/*     */   public void addLastPos(BitSet lastPos) {
/*  94 */     if (this.mLastPos == null) {
/*  95 */       this.mLastPos = new BitSet();
/*  96 */       for (int i = 0, len = this.mSubModels.length; i < len; i++)
/*  97 */         this.mSubModels[i].addLastPos(this.mLastPos); 
/*     */     } 
/* 100 */     lastPos.or(this.mLastPos);
/*     */   }
/*     */   
/*     */   public void calcFollowPos(BitSet[] followPosSets) {
/* 106 */     for (int i = 0, len = this.mSubModels.length; i < len; i++)
/* 107 */       this.mSubModels[i].calcFollowPos(followPosSets); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\ChoiceModel.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */