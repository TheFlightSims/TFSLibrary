/*    */ package com.ctc.wstx.api;
/*    */ 
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ public interface EmptyElementHandler {
/*    */   boolean allowEmptyElement(String paramString1, String paramString2, String paramString3, boolean paramBoolean);
/*    */   
/*    */   public static class SetEmptyElementHandler implements EmptyElementHandler {
/*    */     protected final Set mEmptyElements;
/*    */     
/*    */     public SetEmptyElementHandler(Set emptyElements) {
/* 37 */       this.mEmptyElements = emptyElements;
/*    */     }
/*    */     
/*    */     public boolean allowEmptyElement(String prefix, String localName, String nsURI, boolean allowEmpty) {
/* 42 */       return this.mEmptyElements.contains(localName);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class HtmlEmptyElementHandler extends SetEmptyElementHandler {
/* 58 */     private static final HtmlEmptyElementHandler sInstance = new HtmlEmptyElementHandler();
/*    */     
/*    */     public static HtmlEmptyElementHandler getInstance() {
/* 60 */       return sInstance;
/*    */     }
/*    */     
/*    */     protected HtmlEmptyElementHandler() {
/* 64 */       super(new TreeSet(String.CASE_INSENSITIVE_ORDER));
/* 65 */       this.mEmptyElements.add("area");
/* 66 */       this.mEmptyElements.add("base");
/* 67 */       this.mEmptyElements.add("basefont");
/* 68 */       this.mEmptyElements.add("br");
/* 69 */       this.mEmptyElements.add("col");
/* 70 */       this.mEmptyElements.add("frame");
/* 71 */       this.mEmptyElements.add("hr");
/* 72 */       this.mEmptyElements.add("input");
/* 73 */       this.mEmptyElements.add("img");
/* 74 */       this.mEmptyElements.add("isindex");
/* 75 */       this.mEmptyElements.add("link");
/* 76 */       this.mEmptyElements.add("meta");
/* 77 */       this.mEmptyElements.add("param");
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\api\EmptyElementHandler.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */