/*    */ package com.ctc.wstx.dtd;
/*    */ 
/*    */ import com.ctc.wstx.util.PrefixedName;
/*    */ import java.util.BitSet;
/*    */ import java.util.List;
/*    */ 
/*    */ public final class TokenModel extends ModelNode {
/* 14 */   static final TokenModel NULL_TOKEN = new TokenModel(null);
/*    */   
/*    */   final PrefixedName mElemName;
/*    */   
/*    */   static {
/* 16 */     NULL_TOKEN.mTokenIndex = 0;
/*    */   }
/*    */   
/* 21 */   int mTokenIndex = -1;
/*    */   
/*    */   public TokenModel(PrefixedName elemName) {
/* 30 */     this.mElemName = elemName;
/*    */   }
/*    */   
/*    */   public static TokenModel getNullToken() {
/* 34 */     return NULL_TOKEN;
/*    */   }
/*    */   
/*    */   public PrefixedName getName() {
/* 43 */     return this.mElemName;
/*    */   }
/*    */   
/*    */   public ModelNode cloneModel() {
/* 50 */     return new TokenModel(this.mElemName);
/*    */   }
/*    */   
/*    */   public boolean isNullable() {
/* 54 */     return false;
/*    */   }
/*    */   
/*    */   public void indexTokens(List tokens) {
/* 62 */     if (this != NULL_TOKEN) {
/* 63 */       int index = tokens.size();
/* 64 */       this.mTokenIndex = index;
/* 65 */       tokens.add(this);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void addFirstPos(BitSet firstPos) {
/* 70 */     firstPos.set(this.mTokenIndex);
/*    */   }
/*    */   
/*    */   public void addLastPos(BitSet lastPos) {
/* 74 */     lastPos.set(this.mTokenIndex);
/*    */   }
/*    */   
/*    */   public void calcFollowPos(BitSet[] followPosSets) {}
/*    */   
/*    */   public String toString() {
/* 82 */     return (this.mElemName == null) ? "[null]" : this.mElemName.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\TokenModel.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */