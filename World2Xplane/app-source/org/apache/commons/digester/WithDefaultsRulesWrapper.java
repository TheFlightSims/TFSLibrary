/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class WithDefaultsRulesWrapper implements Rules {
/*     */   private Rules wrappedRules;
/*     */   
/*  57 */   private List defaultRules = new ArrayList();
/*     */   
/*  59 */   private List allRules = new ArrayList();
/*     */   
/*     */   public WithDefaultsRulesWrapper(Rules wrappedRules) {
/*  70 */     if (wrappedRules == null)
/*  71 */       throw new IllegalArgumentException("Wrapped rules must not be null"); 
/*  73 */     this.wrappedRules = wrappedRules;
/*     */   }
/*     */   
/*     */   public Digester getDigester() {
/*  80 */     return this.wrappedRules.getDigester();
/*     */   }
/*     */   
/*     */   public void setDigester(Digester digester) {
/*  85 */     this.wrappedRules.setDigester(digester);
/*  86 */     Iterator it = this.defaultRules.iterator();
/*  87 */     while (it.hasNext()) {
/*  88 */       Rule rule = it.next();
/*  89 */       rule.setDigester(digester);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getNamespaceURI() {
/*  95 */     return this.wrappedRules.getNamespaceURI();
/*     */   }
/*     */   
/*     */   public void setNamespaceURI(String namespaceURI) {
/* 100 */     this.wrappedRules.setNamespaceURI(namespaceURI);
/*     */   }
/*     */   
/*     */   public List getDefaults() {
/* 105 */     return this.defaultRules;
/*     */   }
/*     */   
/*     */   public List match(String pattern) {
/* 111 */     return match("", pattern);
/*     */   }
/*     */   
/*     */   public List match(String namespaceURI, String pattern) {
/* 120 */     List matches = this.wrappedRules.match(namespaceURI, pattern);
/* 121 */     if (matches == null || matches.isEmpty())
/* 123 */       return new ArrayList(this.defaultRules); 
/* 126 */     return matches;
/*     */   }
/*     */   
/*     */   public void addDefault(Rule rule) {
/* 132 */     if (this.wrappedRules.getDigester() != null)
/* 133 */       rule.setDigester(this.wrappedRules.getDigester()); 
/* 136 */     if (this.wrappedRules.getNamespaceURI() != null)
/* 137 */       rule.setNamespaceURI(this.wrappedRules.getNamespaceURI()); 
/* 140 */     this.defaultRules.add(rule);
/* 141 */     this.allRules.add(rule);
/*     */   }
/*     */   
/*     */   public List rules() {
/* 146 */     return this.allRules;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 151 */     this.wrappedRules.clear();
/* 152 */     this.allRules.clear();
/* 153 */     this.defaultRules.clear();
/*     */   }
/*     */   
/*     */   public void add(String pattern, Rule rule) {
/* 161 */     this.wrappedRules.add(pattern, rule);
/* 162 */     this.allRules.add(rule);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\WithDefaultsRulesWrapper.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */