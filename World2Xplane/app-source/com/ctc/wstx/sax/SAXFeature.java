/*    */ package com.ctc.wstx.sax;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public final class SAXFeature {
/*    */   public static final String STD_FEATURE_PREFIX = "http://xml.org/sax/features/";
/*    */   
/* 31 */   static final HashMap sInstances = new HashMap();
/*    */   
/* 35 */   static final SAXFeature EXTERNAL_GENERAL_ENTITIES = new SAXFeature("external-general-entities");
/*    */   
/* 36 */   static final SAXFeature EXTERNAL_PARAMETER_ENTITIES = new SAXFeature("external-parameter-entities");
/*    */   
/* 37 */   static final SAXFeature IS_STANDALONE = new SAXFeature("is-standalone");
/*    */   
/* 38 */   static final SAXFeature LEXICAL_HANDLER_PARAMETER_ENTITIES = new SAXFeature("lexical-handler/parameter-entities");
/*    */   
/* 39 */   static final SAXFeature NAMESPACES = new SAXFeature("namespaces");
/*    */   
/* 40 */   static final SAXFeature NAMESPACE_PREFIXES = new SAXFeature("namespace-prefixes");
/*    */   
/* 41 */   static final SAXFeature RESOLVE_DTD_URIS = new SAXFeature("resolve-dtd-uris");
/*    */   
/* 42 */   static final SAXFeature STRING_INTERNING = new SAXFeature("string-interning");
/*    */   
/* 43 */   static final SAXFeature UNICODE_NORMALIZATION_CHECKING = new SAXFeature("unicode-normalization-checking");
/*    */   
/* 44 */   static final SAXFeature USE_ATTRIBUTES2 = new SAXFeature("use-attributes2");
/*    */   
/* 45 */   static final SAXFeature USE_LOCATOR2 = new SAXFeature("use-locator2");
/*    */   
/* 46 */   static final SAXFeature USE_ENTITY_RESOLVER2 = new SAXFeature("use-entity-resolver2");
/*    */   
/* 47 */   static final SAXFeature VALIDATION = new SAXFeature("validation");
/*    */   
/* 48 */   static final SAXFeature XMLNS_URIS = new SAXFeature("xmlns-uris");
/*    */   
/* 49 */   static final SAXFeature XML_1_1 = new SAXFeature("xml-1.1");
/*    */   
/*    */   private final String mSuffix;
/*    */   
/*    */   private SAXFeature(String suffix) {
/* 55 */     this.mSuffix = suffix;
/* 56 */     sInstances.put(suffix, this);
/*    */   }
/*    */   
/*    */   public static SAXFeature findByUri(String uri) {
/* 61 */     if (uri.startsWith("http://xml.org/sax/features/"))
/* 62 */       return findBySuffix(uri.substring("http://xml.org/sax/features/".length())); 
/* 64 */     return null;
/*    */   }
/*    */   
/*    */   public static SAXFeature findBySuffix(String suffix) {
/* 69 */     return (SAXFeature)sInstances.get(suffix);
/*    */   }
/*    */   
/*    */   public String getSuffix() {
/* 72 */     return this.mSuffix;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 74 */     return "http://xml.org/sax/features/" + this.mSuffix;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sax\SAXFeature.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */