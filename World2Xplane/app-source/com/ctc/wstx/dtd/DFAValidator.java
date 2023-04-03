/*    */ package com.ctc.wstx.dtd;
/*    */ 
/*    */ import com.ctc.wstx.util.PrefixedName;
/*    */ import com.ctc.wstx.util.StringUtil;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ public final class DFAValidator extends StructValidator {
/*    */   DFAState mState;
/*    */   
/*    */   public DFAValidator(DFAState initialState) {
/* 37 */     this.mState = initialState;
/*    */   }
/*    */   
/*    */   public StructValidator newInstance() {
/* 41 */     return new DFAValidator(this.mState);
/*    */   }
/*    */   
/*    */   public String tryToValidate(PrefixedName elemName) {
/* 47 */     DFAState next = this.mState.findNext(elemName);
/* 49 */     if (next == null) {
/* 51 */       TreeSet names = this.mState.getNextNames();
/* 52 */       if (names.size() == 0)
/* 53 */         return "Expected $END"; 
/* 57 */       if (this.mState.isAcceptingState())
/* 58 */         return "Expected <" + StringUtil.concatEntries(names, ">, <", null) + "> or $END"; 
/* 60 */       return "Expected <" + StringUtil.concatEntries(names, ">, <", "> or <") + ">";
/*    */     } 
/* 64 */     this.mState = next;
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   public String fullyValid() {
/* 70 */     if (this.mState.isAcceptingState())
/* 71 */       return null; 
/* 73 */     TreeSet names = this.mState.getNextNames();
/* 74 */     return "Expected <" + StringUtil.concatEntries(names, ">, <", "> or <") + ">";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DFAValidator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */