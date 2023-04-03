/*     */ package org.codehaus.stax2.typed;
/*     */ 
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public class TypedXMLStreamException extends XMLStreamException {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   protected String mLexical;
/*     */   
/*     */   public TypedXMLStreamException(String lexical, String msg) {
/*  50 */     super(msg);
/*  51 */     this.mLexical = lexical;
/*     */   }
/*     */   
/*     */   public TypedXMLStreamException(String lexical, IllegalArgumentException rootCause) {
/*  62 */     super(rootCause);
/*  63 */     this.mLexical = lexical;
/*     */   }
/*     */   
/*     */   public TypedXMLStreamException(String lexical, String msg, IllegalArgumentException rootCause) {
/*  74 */     super(msg, rootCause);
/*  75 */     this.mLexical = lexical;
/*     */   }
/*     */   
/*     */   public TypedXMLStreamException(String lexical, String msg, Location location, IllegalArgumentException rootCause) {
/*  90 */     super(msg, location, rootCause);
/*  91 */     this.mLexical = lexical;
/*     */   }
/*     */   
/*     */   public TypedXMLStreamException(String lexical, String msg, Location location) {
/* 104 */     super(msg, location);
/* 105 */     this.mLexical = lexical;
/*     */   }
/*     */   
/*     */   public String getLexical() {
/* 116 */     return this.mLexical;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\typed\TypedXMLStreamException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */