/*    */ package com.ctc.wstx.util;
/*    */ 
/*    */ public final class DefaultXmlSymbolTable {
/* 32 */   static final SymbolTable sInstance = new SymbolTable(true, 128);
/*    */   
/* 35 */   static final String mNsPrefixXml = sInstance.findSymbol("xml");
/*    */   
/* 36 */   static final String mNsPrefixXmlns = sInstance.findSymbol("xmlns");
/*    */   
/*    */   static {
/* 45 */     sInstance.findSymbol("id");
/* 46 */     sInstance.findSymbol("name");
/* 50 */     sInstance.findSymbol("xsd");
/* 51 */     sInstance.findSymbol("xsi");
/* 53 */     sInstance.findSymbol("type");
/* 57 */     sInstance.findSymbol("soap");
/* 58 */     sInstance.findSymbol("SOAP-ENC");
/* 59 */     sInstance.findSymbol("SOAP-ENV");
/* 61 */     sInstance.findSymbol("Body");
/* 62 */     sInstance.findSymbol("Envelope");
/*    */   }
/*    */   
/*    */   public static SymbolTable getInstance() {
/* 76 */     return sInstance.makeChild();
/*    */   }
/*    */   
/*    */   public static String getXmlSymbol() {
/* 87 */     return mNsPrefixXml;
/*    */   }
/*    */   
/*    */   public static String getXmlnsSymbol() {
/* 91 */     return mNsPrefixXmlns;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\DefaultXmlSymbolTable.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */