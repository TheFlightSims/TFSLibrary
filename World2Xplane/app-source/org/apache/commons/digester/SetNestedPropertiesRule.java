/*     */ package org.apache.commons.digester;
/*     */ 
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.commons.beanutils.DynaBean;
/*     */ import org.apache.commons.beanutils.DynaProperty;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class SetNestedPropertiesRule extends Rule {
/* 104 */   private Log log = null;
/*     */   
/*     */   private boolean trimData = true;
/*     */   
/*     */   private boolean allowUnknownChildElements = false;
/*     */   
/* 109 */   private HashMap elementNames = new HashMap();
/*     */   
/*     */   public SetNestedPropertiesRule(String elementName, String propertyName) {
/* 139 */     this.elementNames.put(elementName, propertyName);
/*     */   }
/*     */   
/*     */   public SetNestedPropertiesRule(String[] elementNames, String[] propertyNames) {
/* 184 */     for (int i = 0, size = elementNames.length; i < size; i++) {
/* 185 */       String propName = null;
/* 186 */       if (i < propertyNames.length)
/* 187 */         propName = propertyNames[i]; 
/* 190 */       this.elementNames.put(elementNames[i], propName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDigester(Digester digester) {
/* 198 */     super.setDigester(digester);
/* 199 */     this.log = digester.getLogger();
/*     */   }
/*     */   
/*     */   public void setTrimData(boolean trimData) {
/* 208 */     this.trimData = trimData;
/*     */   }
/*     */   
/*     */   public boolean getTrimData() {
/* 213 */     return this.trimData;
/*     */   }
/*     */   
/*     */   public void setAllowUnknownChildElements(boolean allowUnknownChildElements) {
/* 231 */     this.allowUnknownChildElements = allowUnknownChildElements;
/*     */   }
/*     */   
/*     */   public boolean getAllowUnknownChildElements() {
/* 236 */     return this.allowUnknownChildElements;
/*     */   }
/*     */   
/*     */   public void begin(String namespace, String name, Attributes attributes) throws Exception {
/* 248 */     Rules oldRules = this.digester.getRules();
/* 249 */     AnyChildRule anyChildRule = new AnyChildRule();
/* 250 */     anyChildRule.setDigester(this.digester);
/* 251 */     AnyChildRules newRules = new AnyChildRules(this, anyChildRule);
/* 252 */     newRules.init(this.digester.getMatch() + "/", oldRules);
/* 253 */     this.digester.setRules(newRules);
/*     */   }
/*     */   
/*     */   public void body(String bodyText) throws Exception {
/* 262 */     AnyChildRules newRules = (AnyChildRules)this.digester.getRules();
/* 263 */     this.digester.setRules(newRules.getOldRules());
/*     */   }
/*     */   
/*     */   public void addAlias(String elementName, String propertyName) {
/* 275 */     this.elementNames.put(elementName, propertyName);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 282 */     StringBuffer sb = new StringBuffer("SetNestedPropertiesRule[");
/* 283 */     sb.append("allowUnknownChildElements=");
/* 284 */     sb.append(this.allowUnknownChildElements);
/* 285 */     sb.append(", trimData=");
/* 286 */     sb.append(this.trimData);
/* 287 */     sb.append(", elementNames=");
/* 288 */     sb.append(this.elementNames);
/* 289 */     sb.append("]");
/* 290 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public SetNestedPropertiesRule() {}
/*     */   
/*     */   private class AnyChildRules implements Rules {
/*     */     private String matchPrefix;
/*     */     
/*     */     private Rules decoratedRules;
/*     */     
/*     */     private ArrayList rules;
/*     */     
/*     */     private SetNestedPropertiesRule.AnyChildRule rule;
/*     */     
/*     */     private final SetNestedPropertiesRule this$0;
/*     */     
/*     */     public AnyChildRules(SetNestedPropertiesRule this$0, SetNestedPropertiesRule.AnyChildRule rule) {
/* 303 */       this.this$0 = this$0;
/*     */       this.matchPrefix = null;
/*     */       this.decoratedRules = null;
/*     */       this.rules = new ArrayList(1);
/* 304 */       this.rule = rule;
/* 305 */       this.rules.add(rule);
/*     */     }
/*     */     
/*     */     public Digester getDigester() {
/* 308 */       return null;
/*     */     }
/*     */     
/*     */     public void setDigester(Digester digester) {}
/*     */     
/*     */     public String getNamespaceURI() {
/* 310 */       return null;
/*     */     }
/*     */     
/*     */     public void setNamespaceURI(String namespaceURI) {}
/*     */     
/*     */     public void add(String pattern, Rule rule) {}
/*     */     
/*     */     public void clear() {}
/*     */     
/*     */     public List match(String matchPath) {
/* 316 */       return match(null, matchPath);
/*     */     }
/*     */     
/*     */     public List match(String namespaceURI, String matchPath) {
/* 320 */       List match = this.decoratedRules.match(namespaceURI, matchPath);
/* 322 */       if (matchPath.startsWith(this.matchPrefix) && matchPath.indexOf('/', this.matchPrefix.length()) == -1) {
/* 330 */         if (match == null || match.size() == 0)
/* 335 */           return this.rules; 
/* 344 */         LinkedList newMatch = new LinkedList(match);
/* 345 */         newMatch.addLast(this.rule);
/* 346 */         return newMatch;
/*     */       } 
/* 350 */       return match;
/*     */     }
/*     */     
/*     */     public List rules() {
/* 369 */       this.this$0.log.debug("AnyChildRules.rules invoked.");
/* 370 */       return this.decoratedRules.rules();
/*     */     }
/*     */     
/*     */     public void init(String prefix, Rules rules) {
/* 374 */       this.matchPrefix = prefix;
/* 375 */       this.decoratedRules = rules;
/*     */     }
/*     */     
/*     */     public Rules getOldRules() {
/* 379 */       return this.decoratedRules;
/*     */     }
/*     */   }
/*     */   
/*     */   private class AnyChildRule extends Rule {
/*     */     private String currChildNamespaceURI;
/*     */     
/*     */     private String currChildElementName;
/*     */     
/*     */     private final SetNestedPropertiesRule this$0;
/*     */     
/*     */     private AnyChildRule(SetNestedPropertiesRule this$0) {
/* 383 */       SetNestedPropertiesRule.this = SetNestedPropertiesRule.this;
/* 384 */       this.currChildNamespaceURI = null;
/* 385 */       this.currChildElementName = null;
/*     */     }
/*     */     
/*     */     public void begin(String namespaceURI, String name, Attributes attributes) throws Exception {
/* 390 */       this.currChildNamespaceURI = namespaceURI;
/* 391 */       this.currChildElementName = name;
/*     */     }
/*     */     
/*     */     public void body(String value) throws Exception {
/* 395 */       String propName = this.currChildElementName;
/* 396 */       if (SetNestedPropertiesRule.this.elementNames.containsKey(this.currChildElementName)) {
/* 398 */         propName = (String)SetNestedPropertiesRule.this.elementNames.get(this.currChildElementName);
/* 399 */         if (propName == null)
/*     */           return; 
/*     */       } 
/* 405 */       boolean debug = SetNestedPropertiesRule.this.log.isDebugEnabled();
/* 407 */       if (debug)
/* 408 */         SetNestedPropertiesRule.this.log.debug("[SetNestedPropertiesRule]{" + this.digester.match + "} Setting property '" + propName + "' to '" + value + "'"); 
/* 414 */       Object top = this.digester.peek();
/* 415 */       if (debug)
/* 416 */         if (top != null) {
/* 417 */           SetNestedPropertiesRule.this.log.debug("[SetNestedPropertiesRule]{" + this.digester.match + "} Set " + top.getClass().getName() + " properties");
/*     */         } else {
/* 421 */           SetNestedPropertiesRule.this.log.debug("[SetPropertiesRule]{" + this.digester.match + "} Set NULL properties");
/*     */         }  
/* 426 */       if (SetNestedPropertiesRule.this.trimData)
/* 427 */         value = value.trim(); 
/* 430 */       if (!SetNestedPropertiesRule.this.allowUnknownChildElements)
/* 433 */         if (top instanceof DynaBean) {
/* 434 */           DynaProperty desc = ((DynaBean)top).getDynaClass().getDynaProperty(propName);
/* 436 */           if (desc == null)
/* 437 */             throw new NoSuchMethodException("Bean has no property named " + propName); 
/*     */         } else {
/* 441 */           PropertyDescriptor desc = PropertyUtils.getPropertyDescriptor(top, propName);
/* 443 */           if (desc == null)
/* 444 */             throw new NoSuchMethodException("Bean has no property named " + propName); 
/*     */         }  
/*     */       try {
/* 452 */         BeanUtils.setProperty(top, propName, value);
/* 454 */       } catch (NullPointerException e) {
/* 455 */         SetNestedPropertiesRule.this.log.error("NullPointerException: top=" + top + ",propName=" + propName + ",value=" + value + "!");
/* 457 */         throw e;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void end(String namespace, String name) throws Exception {
/* 462 */       this.currChildElementName = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\SetNestedPropertiesRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */