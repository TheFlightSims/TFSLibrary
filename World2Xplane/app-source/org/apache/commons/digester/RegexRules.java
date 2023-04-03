/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class RegexRules extends AbstractRulesImpl {
/*  41 */   private ArrayList registeredRules = new ArrayList();
/*     */   
/*     */   private RegexMatcher matcher;
/*     */   
/*     */   public RegexRules(RegexMatcher matcher) {
/*  54 */     setRegexMatcher(matcher);
/*     */   }
/*     */   
/*     */   public RegexMatcher getRegexMatcher() {
/*  63 */     return this.matcher;
/*     */   }
/*     */   
/*     */   public void setRegexMatcher(RegexMatcher matcher) {
/*  73 */     if (matcher == null)
/*  74 */       throw new IllegalArgumentException("RegexMatcher must not be null."); 
/*  76 */     this.matcher = matcher;
/*     */   }
/*     */   
/*     */   protected void registerRule(String pattern, Rule rule) {
/*  88 */     this.registeredRules.add(new RegisteredRule(this, pattern, rule));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  95 */     this.registeredRules.clear();
/*     */   }
/*     */   
/*     */   public List match(String namespaceURI, String pattern) {
/* 117 */     ArrayList rules = new ArrayList(this.registeredRules.size());
/* 118 */     Iterator it = this.registeredRules.iterator();
/* 119 */     while (it.hasNext()) {
/* 120 */       RegisteredRule next = it.next();
/* 121 */       if (this.matcher.match(pattern, next.pattern))
/* 122 */         rules.add(next.rule); 
/*     */     } 
/* 125 */     return rules;
/*     */   }
/*     */   
/*     */   public List rules() {
/* 137 */     ArrayList rules = new ArrayList(this.registeredRules.size());
/* 138 */     Iterator it = this.registeredRules.iterator();
/* 139 */     while (it.hasNext())
/* 140 */       rules.add(((RegisteredRule)it.next()).rule); 
/* 142 */     return rules;
/*     */   }
/*     */   
/*     */   private class RegisteredRule {
/*     */     String pattern;
/*     */     
/*     */     Rule rule;
/*     */     
/*     */     private final RegexRules this$0;
/*     */     
/*     */     RegisteredRule(RegexRules this$0, String pattern, Rule rule) {
/* 150 */       this.this$0 = this$0;
/* 151 */       this.pattern = pattern;
/* 152 */       this.rule = rule;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\RegexRules.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */