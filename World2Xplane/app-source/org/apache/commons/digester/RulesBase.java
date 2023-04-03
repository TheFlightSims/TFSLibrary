/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class RulesBase implements Rules {
/*  64 */   protected HashMap cache = new HashMap();
/*     */   
/*  70 */   protected Digester digester = null;
/*     */   
/*  78 */   protected String namespaceURI = null;
/*     */   
/*  85 */   protected ArrayList rules = new ArrayList();
/*     */   
/*     */   public Digester getDigester() {
/*  97 */     return this.digester;
/*     */   }
/*     */   
/*     */   public void setDigester(Digester digester) {
/* 109 */     this.digester = digester;
/* 110 */     Iterator items = this.rules.iterator();
/* 111 */     while (items.hasNext()) {
/* 112 */       Rule item = items.next();
/* 113 */       item.setDigester(digester);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getNamespaceURI() {
/* 125 */     return this.namespaceURI;
/*     */   }
/*     */   
/*     */   public void setNamespaceURI(String namespaceURI) {
/* 140 */     this.namespaceURI = namespaceURI;
/*     */   }
/*     */   
/*     */   public void add(String pattern, Rule rule) {
/* 156 */     int patternLength = pattern.length();
/* 157 */     if (patternLength > 1 && pattern.endsWith("/"))
/* 158 */       pattern = pattern.substring(0, patternLength - 1); 
/* 162 */     List list = (List)this.cache.get(pattern);
/* 163 */     if (list == null) {
/* 164 */       list = new ArrayList();
/* 165 */       this.cache.put(pattern, list);
/*     */     } 
/* 167 */     list.add(rule);
/* 168 */     this.rules.add(rule);
/* 169 */     if (this.digester != null)
/* 170 */       rule.setDigester(this.digester); 
/* 172 */     if (this.namespaceURI != null)
/* 173 */       rule.setNamespaceURI(this.namespaceURI); 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 184 */     this.cache.clear();
/* 185 */     this.rules.clear();
/*     */   }
/*     */   
/*     */   public List match(String pattern) {
/* 203 */     return match(null, pattern);
/*     */   }
/*     */   
/*     */   public List match(String namespaceURI, String pattern) {
/* 222 */     List rulesList = lookup(namespaceURI, pattern);
/* 223 */     if (rulesList == null || rulesList.size() < 1) {
/* 225 */       String longKey = "";
/* 226 */       Iterator keys = this.cache.keySet().iterator();
/* 227 */       while (keys.hasNext()) {
/* 228 */         String key = keys.next();
/* 229 */         if (key.startsWith("*/") && (
/* 230 */           pattern.equals(key.substring(2)) || pattern.endsWith(key.substring(1))))
/* 232 */           if (key.length() > longKey.length()) {
/* 234 */             rulesList = lookup(namespaceURI, key);
/* 235 */             longKey = key;
/*     */           }  
/*     */       } 
/*     */     } 
/* 241 */     if (rulesList == null)
/* 242 */       rulesList = new ArrayList(); 
/* 244 */     return rulesList;
/*     */   }
/*     */   
/*     */   public List rules() {
/* 258 */     return this.rules;
/*     */   }
/*     */   
/*     */   protected List lookup(String namespaceURI, String pattern) {
/* 278 */     List list = (List)this.cache.get(pattern);
/* 279 */     if (list == null)
/* 280 */       return null; 
/* 282 */     if (namespaceURI == null || namespaceURI.length() == 0)
/* 283 */       return list; 
/* 287 */     ArrayList results = new ArrayList();
/* 288 */     Iterator items = list.iterator();
/* 289 */     while (items.hasNext()) {
/* 290 */       Rule item = items.next();
/* 291 */       if (namespaceURI.equals(item.getNamespaceURI()) || item.getNamespaceURI() == null)
/* 293 */         results.add(item); 
/*     */     } 
/* 296 */     return results;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\RulesBase.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */