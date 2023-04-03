/*    */ package com.ctc.wstx.dtd;
/*    */ 
/*    */ import com.ctc.wstx.util.PrefixedName;
/*    */ 
/*    */ public final class SmallPrefixedNameSet extends PrefixedNameSet {
/*    */   final boolean mNsAware;
/*    */   
/*    */   final String[] mStrings;
/*    */   
/*    */   public SmallPrefixedNameSet(boolean nsAware, PrefixedName[] names) {
/* 33 */     this.mNsAware = nsAware;
/* 34 */     int len = names.length;
/* 35 */     if (len == 0)
/* 36 */       throw new IllegalStateException("Trying to construct empty PrefixedNameSet"); 
/* 38 */     this.mStrings = new String[nsAware ? (len + len) : len];
/* 39 */     for (int out = 0, in = 0; in < len; in++) {
/* 40 */       PrefixedName nk = names[in];
/* 41 */       if (nsAware)
/* 42 */         this.mStrings[out++] = nk.getPrefix(); 
/* 44 */       this.mStrings[out++] = nk.getLocalName();
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean hasMultiple() {
/* 49 */     return (this.mStrings.length > 1);
/*    */   }
/*    */   
/*    */   public boolean contains(PrefixedName name) {
/* 57 */     int len = this.mStrings.length;
/* 58 */     String ln = name.getLocalName();
/* 59 */     String[] strs = this.mStrings;
/* 61 */     if (this.mNsAware) {
/* 62 */       String prefix = name.getPrefix();
/* 63 */       if (strs[1] == ln && strs[0] == prefix)
/* 64 */         return true; 
/* 66 */       for (int i = 2; i < len; i += 2) {
/* 67 */         if (strs[i + 1] == ln && strs[i] == prefix)
/* 68 */           return true; 
/*    */       } 
/*    */     } else {
/* 72 */       if (strs[0] == ln)
/* 73 */         return true; 
/* 75 */       for (int i = 1; i < len; i++) {
/* 76 */         if (strs[i] == ln)
/* 77 */           return true; 
/*    */       } 
/*    */     } 
/* 82 */     return false;
/*    */   }
/*    */   
/*    */   public void appendNames(StringBuffer sb, String sep) {
/* 87 */     for (int i = 0; i < this.mStrings.length; ) {
/* 88 */       if (i > 0)
/* 89 */         sb.append(sep); 
/* 91 */       if (this.mNsAware) {
/* 92 */         String prefix = this.mStrings[i++];
/* 93 */         if (prefix != null) {
/* 94 */           sb.append(prefix);
/* 95 */           sb.append(':');
/*    */         } 
/*    */       } 
/* 98 */       sb.append(this.mStrings[i++]);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\SmallPrefixedNameSet.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */