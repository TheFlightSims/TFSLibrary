/*    */ package com.ctc.wstx.sr;
/*    */ 
/*    */ final class Element {
/*    */   protected String mLocalName;
/*    */   
/*    */   protected String mPrefix;
/*    */   
/*    */   protected String mNamespaceURI;
/*    */   
/*    */   protected String mDefaultNsURI;
/*    */   
/*    */   protected int mNsOffset;
/*    */   
/*    */   protected Element mParent;
/*    */   
/*    */   public Element(Element parent, int nsOffset, String prefix, String ln) {
/* 71 */     this.mParent = parent;
/* 72 */     this.mNsOffset = nsOffset;
/* 73 */     this.mPrefix = prefix;
/* 74 */     this.mLocalName = ln;
/*    */   }
/*    */   
/*    */   public void reset(Element parent, int nsOffset, String prefix, String ln) {
/* 79 */     this.mParent = parent;
/* 80 */     this.mNsOffset = nsOffset;
/* 81 */     this.mPrefix = prefix;
/* 82 */     this.mLocalName = ln;
/*    */   }
/*    */   
/*    */   public void relink(Element next) {
/* 90 */     this.mParent = next;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\Element.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */