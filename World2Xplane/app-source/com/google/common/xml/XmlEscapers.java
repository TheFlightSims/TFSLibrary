/*     */ package com.google.common.xml;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.annotations.GwtCompatible;
/*     */ import com.google.common.escape.Escaper;
/*     */ import com.google.common.escape.Escapers;
/*     */ 
/*     */ @Beta
/*     */ @GwtCompatible
/*     */ public class XmlEscapers {
/*     */   private static final char MIN_ASCII_CONTROL_CHAR = '\000';
/*     */   
/*     */   private static final char MAX_ASCII_CONTROL_CHAR = '\037';
/*     */   
/*     */   private static final Escaper XML_ESCAPER;
/*     */   
/*     */   private static final Escaper XML_CONTENT_ESCAPER;
/*     */   
/*     */   private static final Escaper XML_ATTRIBUTE_ESCAPER;
/*     */   
/*     */   public static Escaper xmlContentEscaper() {
/*  87 */     return XML_CONTENT_ESCAPER;
/*     */   }
/*     */   
/*     */   public static Escaper xmlAttributeEscaper() {
/* 108 */     return XML_ATTRIBUTE_ESCAPER;
/*     */   }
/*     */   
/*     */   static {
/* 115 */     Escapers.Builder builder = Escapers.builder();
/* 120 */     builder.setSafeRange(false, '￿');
/* 122 */     builder.setUnsafeReplacement("");
/* 129 */     for (char c = Character.MIN_VALUE; c <= '\037'; c = (char)(c + 1)) {
/* 130 */       if (c != '\t' && c != '\n' && c != '\r')
/* 131 */         builder.addEscape(c, ""); 
/*     */     } 
/* 137 */     builder.addEscape('&', "&amp;");
/* 138 */     builder.addEscape('<', "&lt;");
/* 139 */     builder.addEscape('>', "&gt;");
/* 140 */     XML_CONTENT_ESCAPER = builder.build();
/* 141 */     builder.addEscape('\'', "&apos;");
/* 142 */     builder.addEscape('"', "&quot;");
/* 143 */     XML_ESCAPER = builder.build();
/* 144 */     builder.addEscape('\t', "&#x9;");
/* 145 */     builder.addEscape('\n', "&#xA;");
/* 146 */     builder.addEscape('\r', "&#xD;");
/* 147 */     XML_ATTRIBUTE_ESCAPER = builder.build();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\xml\XmlEscapers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */