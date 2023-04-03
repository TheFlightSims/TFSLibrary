/*    */ package com.ctc.wstx.sax;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public final class SAXProperty {
/*    */   public static final String STD_PROPERTY_PREFIX = "http://xml.org/sax/properties/";
/*    */   
/* 27 */   static final HashMap sInstances = new HashMap();
/*    */   
/* 31 */   public static final SAXProperty DECLARATION_HANDLER = new SAXProperty("declaration-handler");
/*    */   
/* 32 */   public static final SAXProperty DOCUMENT_XML_VERSION = new SAXProperty("document-xml-version");
/*    */   
/* 33 */   public static final SAXProperty DOM_NODE = new SAXProperty("dom-node");
/*    */   
/* 34 */   public static final SAXProperty LEXICAL_HANDLER = new SAXProperty("lexical-handler");
/*    */   
/* 35 */   static final SAXProperty XML_STRING = new SAXProperty("xml-string");
/*    */   
/*    */   private final String mSuffix;
/*    */   
/*    */   private SAXProperty(String suffix) {
/* 41 */     this.mSuffix = suffix;
/* 42 */     sInstances.put(suffix, this);
/*    */   }
/*    */   
/*    */   public static SAXProperty findByUri(String uri) {
/* 47 */     if (uri.startsWith("http://xml.org/sax/properties/"))
/* 48 */       return findBySuffix(uri.substring("http://xml.org/sax/properties/".length())); 
/* 50 */     return null;
/*    */   }
/*    */   
/*    */   public static SAXProperty findBySuffix(String suffix) {
/* 55 */     return (SAXProperty)sInstances.get(suffix);
/*    */   }
/*    */   
/*    */   public String getSuffix() {
/* 58 */     return this.mSuffix;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 60 */     return "http://xml.org/sax/properties/" + this.mSuffix;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sax\SAXProperty.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */