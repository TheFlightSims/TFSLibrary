/*    */ package com.ctc.wstx.dtd;
/*    */ 
/*    */ import com.ctc.wstx.util.PrefixedName;
/*    */ 
/*    */ public abstract class PrefixedNameSet {
/*    */   public abstract boolean hasMultiple();
/*    */   
/*    */   public abstract boolean contains(PrefixedName paramPrefixedName);
/*    */   
/*    */   public abstract void appendNames(StringBuffer paramStringBuffer, String paramString);
/*    */   
/*    */   public final String toString() {
/* 38 */     return toString(", ");
/*    */   }
/*    */   
/*    */   public final String toString(String sep) {
/* 42 */     StringBuffer sb = new StringBuffer();
/* 43 */     appendNames(sb, sep);
/* 44 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\PrefixedNameSet.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */