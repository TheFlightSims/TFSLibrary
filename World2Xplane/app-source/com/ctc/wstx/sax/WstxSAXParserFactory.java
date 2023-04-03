/*     */ package com.ctc.wstx.sax;
/*     */ 
/*     */ import com.ctc.wstx.stax.WstxInputFactory;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ 
/*     */ public class WstxSAXParserFactory extends SAXParserFactory {
/*     */   protected final WstxInputFactory mStaxFactory;
/*     */   
/*     */   protected boolean mFeatNsPrefixes = false;
/*     */   
/*     */   public WstxSAXParserFactory() {
/*  47 */     this(new WstxInputFactory());
/*     */   }
/*     */   
/*     */   public WstxSAXParserFactory(WstxInputFactory f) {
/*  55 */     this.mStaxFactory = f;
/*  59 */     setNamespaceAware(true);
/*     */   }
/*     */   
/*     */   public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
/*  65 */     SAXFeature stdFeat = SAXFeature.findByUri(name);
/*  67 */     if (stdFeat == SAXFeature.EXTERNAL_GENERAL_ENTITIES)
/*  68 */       return this.mStaxFactory.getConfig().willSupportExternalEntities(); 
/*  69 */     if (stdFeat == SAXFeature.EXTERNAL_PARAMETER_ENTITIES)
/*  70 */       return this.mStaxFactory.getConfig().willSupportExternalEntities(); 
/*  71 */     if (stdFeat == SAXFeature.IS_STANDALONE)
/*  73 */       return false; 
/*  74 */     if (stdFeat == SAXFeature.LEXICAL_HANDLER_PARAMETER_ENTITIES)
/*  76 */       return false; 
/*  77 */     if (stdFeat == SAXFeature.NAMESPACES)
/*  78 */       return this.mStaxFactory.getConfig().willSupportNamespaces(); 
/*  79 */     if (stdFeat == SAXFeature.NAMESPACE_PREFIXES)
/*  80 */       return this.mFeatNsPrefixes; 
/*  81 */     if (stdFeat == SAXFeature.RESOLVE_DTD_URIS)
/*  83 */       return false; 
/*  84 */     if (stdFeat == SAXFeature.STRING_INTERNING)
/*  85 */       return this.mStaxFactory.getConfig().willInternNames(); 
/*  86 */     if (stdFeat == SAXFeature.UNICODE_NORMALIZATION_CHECKING)
/*  87 */       return false; 
/*  88 */     if (stdFeat == SAXFeature.USE_ATTRIBUTES2)
/*  89 */       return true; 
/*  90 */     if (stdFeat == SAXFeature.USE_LOCATOR2)
/*  91 */       return true; 
/*  92 */     if (stdFeat == SAXFeature.USE_ENTITY_RESOLVER2)
/*  93 */       return true; 
/*  94 */     if (stdFeat == SAXFeature.VALIDATION)
/*  95 */       return this.mStaxFactory.getConfig().willValidateWithDTD(); 
/*  96 */     if (stdFeat == SAXFeature.XMLNS_URIS)
/* 100 */       return true; 
/* 101 */     if (stdFeat == SAXFeature.XML_1_1)
/* 102 */       return true; 
/* 104 */     throw new SAXNotRecognizedException("Feature '" + name + "' not recognized");
/*     */   }
/*     */   
/*     */   public SAXParser newSAXParser() {
/* 110 */     return new WstxSAXParser(this.mStaxFactory, this.mFeatNsPrefixes);
/*     */   }
/*     */   
/*     */   public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
/* 116 */     boolean invalidValue = false;
/* 117 */     boolean readOnly = false;
/* 118 */     SAXFeature stdFeat = SAXFeature.findByUri(name);
/* 120 */     if (stdFeat == SAXFeature.EXTERNAL_GENERAL_ENTITIES) {
/* 121 */       this.mStaxFactory.getConfig().doSupportExternalEntities(value);
/* 122 */     } else if (stdFeat != SAXFeature.EXTERNAL_PARAMETER_ENTITIES) {
/* 124 */       if (stdFeat == SAXFeature.IS_STANDALONE) {
/* 125 */         readOnly = true;
/* 126 */       } else if (stdFeat != SAXFeature.LEXICAL_HANDLER_PARAMETER_ENTITIES) {
/* 128 */         if (stdFeat == SAXFeature.NAMESPACES) {
/* 129 */           this.mStaxFactory.getConfig().doSupportNamespaces(value);
/* 130 */         } else if (stdFeat == SAXFeature.NAMESPACE_PREFIXES) {
/* 131 */           this.mFeatNsPrefixes = value;
/* 132 */         } else if (stdFeat != SAXFeature.RESOLVE_DTD_URIS) {
/* 134 */           if (stdFeat == SAXFeature.STRING_INTERNING) {
/* 135 */             invalidValue = !value;
/* 136 */           } else if (stdFeat == SAXFeature.UNICODE_NORMALIZATION_CHECKING) {
/* 137 */             invalidValue = value;
/* 138 */           } else if (stdFeat == SAXFeature.USE_ATTRIBUTES2) {
/* 139 */             readOnly = true;
/* 140 */           } else if (stdFeat == SAXFeature.USE_LOCATOR2) {
/* 141 */             readOnly = true;
/* 142 */           } else if (stdFeat == SAXFeature.USE_ENTITY_RESOLVER2) {
/* 143 */             readOnly = true;
/* 144 */           } else if (stdFeat == SAXFeature.VALIDATION) {
/* 145 */             this.mStaxFactory.getConfig().doValidateWithDTD(value);
/* 146 */           } else if (stdFeat == SAXFeature.XMLNS_URIS) {
/* 147 */             invalidValue = !value;
/* 148 */           } else if (stdFeat == SAXFeature.XML_1_1) {
/* 149 */             readOnly = true;
/*     */           } else {
/* 151 */             throw new SAXNotRecognizedException("Feature '" + name + "' not recognized");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 155 */     if (readOnly)
/* 156 */       throw new SAXNotSupportedException("Feature '" + name + "' is read-only, can not be modified"); 
/* 158 */     if (invalidValue)
/* 159 */       throw new SAXNotSupportedException("Trying to set invalid value for feature '" + name + "', '" + value + "'"); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sax\WstxSAXParserFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */