/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public abstract class Rule {
/*     */   protected Digester digester;
/*     */   
/*     */   protected String namespaceURI;
/*     */   
/*     */   public Rule(Digester digester) {
/*  85 */     this.digester = null;
/*  91 */     this.namespaceURI = null;
/*     */     setDigester(digester);
/*     */   }
/*     */   
/*     */   public Rule() {
/*     */     this.digester = null;
/*  91 */     this.namespaceURI = null;
/*     */   }
/*     */   
/*     */   public Digester getDigester() {
/* 102 */     return this.digester;
/*     */   }
/*     */   
/*     */   public void setDigester(Digester digester) {
/* 111 */     this.digester = digester;
/*     */   }
/*     */   
/*     */   public String getNamespaceURI() {
/* 120 */     return this.namespaceURI;
/*     */   }
/*     */   
/*     */   public void setNamespaceURI(String namespaceURI) {
/* 133 */     this.namespaceURI = namespaceURI;
/*     */   }
/*     */   
/*     */   public void begin(Attributes attributes) throws Exception {}
/*     */   
/*     */   public void begin(String namespace, String name, Attributes attributes) throws Exception {
/* 175 */     begin(attributes);
/*     */   }
/*     */   
/*     */   public void body(String text) throws Exception {}
/*     */   
/*     */   public void body(String namespace, String name, String text) throws Exception {
/* 217 */     body(text);
/*     */   }
/*     */   
/*     */   public void end() throws Exception {}
/*     */   
/*     */   public void end(String namespace, String name) throws Exception {
/* 253 */     end();
/*     */   }
/*     */   
/*     */   public void finish() throws Exception {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\Rule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */