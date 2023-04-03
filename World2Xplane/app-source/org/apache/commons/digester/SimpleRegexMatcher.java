/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class SimpleRegexMatcher extends RegexMatcher {
/*  42 */   private static final Log baseLog = LogFactory.getLog(SimpleRegexMatcher.class);
/*     */   
/*  45 */   private Log log = baseLog;
/*     */   
/*     */   public Log getLog() {
/*  53 */     return this.log;
/*     */   }
/*     */   
/*     */   public void setLog(Log log) {
/*  60 */     this.log = log;
/*     */   }
/*     */   
/*     */   public boolean match(String basePattern, String regexPattern) {
/*  75 */     if (basePattern == null || regexPattern == null)
/*  76 */       return false; 
/*  78 */     return match(basePattern, regexPattern, 0, 0);
/*     */   }
/*     */   
/*     */   private boolean match(String basePattern, String regexPattern, int baseAt, int regexAt) {
/*     */     char nextRegex;
/*     */     int nextMatch;
/*  88 */     if (this.log.isTraceEnabled()) {
/*  89 */       this.log.trace("Base: " + basePattern);
/*  90 */       this.log.trace("Regex: " + regexPattern);
/*  91 */       this.log.trace("Base@" + baseAt);
/*  92 */       this.log.trace("Regex@" + regexAt);
/*     */     } 
/*  96 */     if (regexAt >= regexPattern.length()) {
/*  98 */       if (baseAt >= basePattern.length())
/* 100 */         return true; 
/* 103 */       return false;
/*     */     } 
/* 106 */     if (baseAt >= basePattern.length())
/* 108 */       return false; 
/* 113 */     char regexCurrent = regexPattern.charAt(regexAt);
/* 114 */     switch (regexCurrent) {
/*     */       case '*':
/* 118 */         if (++regexAt >= regexPattern.length())
/* 120 */           return true; 
/* 124 */         nextRegex = regexPattern.charAt(regexAt);
/* 125 */         if (this.log.isTraceEnabled())
/* 126 */           this.log.trace("Searching for next '" + nextRegex + "' char"); 
/* 128 */         nextMatch = basePattern.indexOf(nextRegex, baseAt);
/* 129 */         while (nextMatch != -1) {
/* 130 */           if (this.log.isTraceEnabled())
/* 131 */             this.log.trace("Trying '*' match@" + nextMatch); 
/* 133 */           if (match(basePattern, regexPattern, nextMatch, regexAt))
/* 134 */             return true; 
/* 136 */           nextMatch = basePattern.indexOf(nextRegex, nextMatch + 1);
/*     */         } 
/* 138 */         this.log.trace("No matches found.");
/* 139 */         return false;
/*     */       case '?':
/* 143 */         return match(basePattern, regexPattern, ++baseAt, ++regexAt);
/*     */     } 
/* 146 */     if (this.log.isTraceEnabled())
/* 147 */       this.log.trace("Camparing " + regexCurrent + " to " + basePattern.charAt(baseAt)); 
/* 149 */     if (regexCurrent == basePattern.charAt(baseAt))
/* 151 */       return match(basePattern, regexPattern, ++baseAt, ++regexAt); 
/* 153 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\SimpleRegexMatcher.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */