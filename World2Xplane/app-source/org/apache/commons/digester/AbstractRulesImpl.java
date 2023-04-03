/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class AbstractRulesImpl implements Rules {
/*     */   private Digester digester;
/*     */   
/*     */   private String namespaceURI;
/*     */   
/*     */   public Digester getDigester() {
/*  56 */     return this.digester;
/*     */   }
/*     */   
/*     */   public void setDigester(Digester digester) {
/*  65 */     this.digester = digester;
/*     */   }
/*     */   
/*     */   public String getNamespaceURI() {
/*  73 */     return this.namespaceURI;
/*     */   }
/*     */   
/*     */   public void setNamespaceURI(String namespaceURI) {
/*  85 */     this.namespaceURI = namespaceURI;
/*     */   }
/*     */   
/*     */   public void add(String pattern, Rule rule) {
/* 100 */     if (this.digester != null)
/* 101 */       rule.setDigester(this.digester); 
/* 104 */     if (this.namespaceURI != null)
/* 105 */       rule.setNamespaceURI(this.namespaceURI); 
/* 108 */     registerRule(pattern, rule);
/*     */   }
/*     */   
/*     */   protected abstract void registerRule(String paramString, Rule paramRule);
/*     */   
/*     */   public abstract void clear();
/*     */   
/*     */   public List match(String pattern) {
/* 140 */     return match(this.namespaceURI, pattern);
/*     */   }
/*     */   
/*     */   public abstract List match(String paramString1, String paramString2);
/*     */   
/*     */   public abstract List rules();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\AbstractRulesImpl.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */