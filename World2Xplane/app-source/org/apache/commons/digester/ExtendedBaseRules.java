/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ExtendedBaseRules extends RulesBase {
/* 178 */   private int counter = 0;
/*     */   
/* 188 */   private Map order = new HashMap();
/*     */   
/*     */   public void add(String pattern, Rule rule) {
/* 201 */     super.add(pattern, rule);
/* 202 */     this.counter++;
/* 203 */     this.order.put(rule, new Integer(this.counter));
/*     */   }
/*     */   
/*     */   public List match(String namespace, String pattern) {
/* 219 */     String parentPattern = "";
/* 220 */     int lastIndex = pattern.lastIndexOf('/');
/* 222 */     boolean hasParent = true;
/* 223 */     if (lastIndex == -1) {
/* 225 */       hasParent = false;
/*     */     } else {
/* 229 */       parentPattern = pattern.substring(0, lastIndex);
/*     */     } 
/* 235 */     List universalList = new ArrayList(this.counter);
/* 239 */     List tempList = (List)this.cache.get("!*");
/* 240 */     if (tempList != null)
/* 241 */       universalList.addAll(tempList); 
/* 246 */     tempList = (List)this.cache.get("!" + parentPattern + "/?");
/* 247 */     if (tempList != null)
/* 248 */       universalList.addAll(tempList); 
/* 255 */     boolean ignoreBasicMatches = false;
/* 259 */     List rulesList = (List)this.cache.get(pattern);
/* 260 */     if (rulesList != null) {
/* 263 */       ignoreBasicMatches = true;
/* 268 */     } else if (hasParent) {
/* 270 */       rulesList = (List)this.cache.get(parentPattern + "/?");
/* 271 */       if (rulesList != null) {
/* 274 */         ignoreBasicMatches = true;
/*     */       } else {
/* 279 */         rulesList = findExactAncesterMatch(pattern);
/* 280 */         if (rulesList != null)
/* 283 */           ignoreBasicMatches = true; 
/*     */       } 
/*     */     } 
/* 295 */     String longKey = "";
/* 296 */     int longKeyLength = 0;
/* 298 */     Iterator keys = this.cache.keySet().iterator();
/* 299 */     while (keys.hasNext()) {
/* 300 */       String key = keys.next();
/* 304 */       boolean isUniversal = key.startsWith("!");
/* 305 */       if (isUniversal)
/* 307 */         key = key.substring(1, key.length()); 
/* 312 */       boolean wildcardMatchStart = key.startsWith("*/");
/* 313 */       boolean wildcardMatchEnd = key.endsWith("/*");
/* 314 */       if (wildcardMatchStart || (isUniversal && wildcardMatchEnd)) {
/* 316 */         boolean parentMatched = false;
/* 317 */         boolean basicMatched = false;
/* 318 */         boolean ancesterMatched = false;
/* 320 */         boolean parentMatchEnd = key.endsWith("/?");
/* 321 */         if (parentMatchEnd) {
/* 323 */           parentMatched = parentMatch(key, pattern, parentPattern);
/* 325 */         } else if (wildcardMatchEnd) {
/* 327 */           if (wildcardMatchStart) {
/* 328 */             String patternBody = key.substring(2, key.length() - 2);
/* 329 */             if (pattern.endsWith(patternBody)) {
/* 330 */               ancesterMatched = true;
/*     */             } else {
/* 332 */               ancesterMatched = (pattern.indexOf(patternBody + "/") > -1);
/*     */             } 
/*     */           } else {
/* 335 */             String bodyPattern = key.substring(0, key.length() - 2);
/* 336 */             if (pattern.startsWith(bodyPattern)) {
/* 338 */               if (pattern.length() == bodyPattern.length()) {
/* 340 */                 ancesterMatched = true;
/*     */               } else {
/* 342 */                 ancesterMatched = (pattern.charAt(bodyPattern.length()) == '/');
/*     */               } 
/*     */             } else {
/* 345 */               ancesterMatched = false;
/*     */             } 
/*     */           } 
/*     */         } else {
/* 350 */           basicMatched = basicMatch(key, pattern);
/*     */         } 
/* 353 */         if (parentMatched || basicMatched || ancesterMatched) {
/* 354 */           if (isUniversal) {
/* 357 */             tempList = (List)this.cache.get("!" + key);
/* 358 */             if (tempList != null)
/* 359 */               universalList.addAll(tempList); 
/*     */             continue;
/*     */           } 
/* 363 */           if (!ignoreBasicMatches) {
/* 370 */             int keyLength = key.length();
/* 371 */             if (wildcardMatchStart)
/* 372 */               keyLength--; 
/* 374 */             if (wildcardMatchEnd) {
/* 375 */               keyLength--;
/* 376 */             } else if (parentMatchEnd) {
/* 377 */               keyLength--;
/*     */             } 
/* 380 */             if (keyLength > longKeyLength) {
/* 381 */               rulesList = (List)this.cache.get(key);
/* 382 */               longKey = key;
/* 383 */               longKeyLength = keyLength;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 394 */     if (rulesList == null)
/* 395 */       rulesList = (List)this.cache.get("*"); 
/* 399 */     if (rulesList != null)
/* 400 */       universalList.addAll(rulesList); 
/* 405 */     if (namespace != null) {
/* 407 */       Iterator it = universalList.iterator();
/* 408 */       while (it.hasNext()) {
/* 409 */         Rule rule = it.next();
/* 410 */         String ns_uri = rule.getNamespaceURI();
/* 411 */         if (ns_uri != null && !ns_uri.equals(namespace))
/* 412 */           it.remove(); 
/*     */       } 
/*     */     } 
/* 420 */     Collections.sort(universalList, new Comparator(this) {
/*     */           private final ExtendedBaseRules this$0;
/*     */           
/*     */           public int compare(Object o1, Object o2) throws ClassCastException {
/* 426 */             Integer i1 = (Integer)this.this$0.order.get(o1);
/* 427 */             Integer i2 = (Integer)this.this$0.order.get(o2);
/* 430 */             if (i1 == null) {
/* 431 */               if (i2 == null)
/* 433 */                 return 0; 
/* 437 */               return -1;
/*     */             } 
/* 440 */             if (i2 == null)
/* 441 */               return 1; 
/* 444 */             return i1.intValue() - i2.intValue();
/*     */           }
/*     */         });
/* 448 */     return universalList;
/*     */   }
/*     */   
/*     */   private boolean parentMatch(String key, String pattern, String parentPattern) {
/* 455 */     return parentPattern.endsWith(key.substring(1, key.length() - 2));
/*     */   }
/*     */   
/*     */   private boolean basicMatch(String key, String pattern) {
/* 463 */     return (pattern.equals(key.substring(2)) || pattern.endsWith(key.substring(1)));
/*     */   }
/*     */   
/*     */   private List findExactAncesterMatch(String parentPattern) {
/* 471 */     List matchingRules = null;
/* 472 */     int lastIndex = parentPattern.length();
/* 473 */     while (lastIndex-- > 0) {
/* 474 */       lastIndex = parentPattern.lastIndexOf('/', lastIndex);
/* 475 */       if (lastIndex > 0) {
/* 476 */         matchingRules = (List)this.cache.get(parentPattern.substring(0, lastIndex) + "/*");
/* 477 */         if (matchingRules != null)
/* 478 */           return matchingRules; 
/*     */       } 
/*     */     } 
/* 482 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\ExtendedBaseRules.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */