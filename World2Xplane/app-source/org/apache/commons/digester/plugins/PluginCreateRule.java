/*     */ package org.apache.commons.digester.plugins;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.digester.Rule;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class PluginCreateRule extends Rule implements InitializableRule {
/*  36 */   private String pluginClassAttrNs = null;
/*     */   
/*  37 */   private String pluginClassAttr = null;
/*     */   
/*  40 */   private String pluginIdAttrNs = null;
/*     */   
/*  41 */   private String pluginIdAttr = null;
/*     */   
/*     */   private String pattern;
/*     */   
/*  50 */   private Class baseClass = null;
/*     */   
/*     */   private Declaration defaultPlugin;
/*     */   
/*     */   private PluginConfigurationException initException;
/*     */   
/*     */   public PluginCreateRule(Class baseClass) {
/*  77 */     this.baseClass = baseClass;
/*     */   }
/*     */   
/*     */   public PluginCreateRule(Class baseClass, Class dfltPluginClass) {
/*  92 */     this.baseClass = baseClass;
/*  93 */     if (dfltPluginClass != null)
/*  94 */       this.defaultPlugin = new Declaration(dfltPluginClass); 
/*     */   }
/*     */   
/*     */   public PluginCreateRule(Class baseClass, Class dfltPluginClass, RuleLoader dfltPluginRuleLoader) {
/* 114 */     this.baseClass = baseClass;
/* 115 */     if (dfltPluginClass != null)
/* 116 */       this.defaultPlugin = new Declaration(dfltPluginClass, dfltPluginRuleLoader); 
/*     */   }
/*     */   
/*     */   public void setPluginClassAttribute(String namespaceUri, String attrName) {
/* 130 */     this.pluginClassAttrNs = namespaceUri;
/* 131 */     this.pluginClassAttr = attrName;
/*     */   }
/*     */   
/*     */   public void setPluginIdAttribute(String namespaceUri, String attrName) {
/* 141 */     this.pluginIdAttrNs = namespaceUri;
/* 142 */     this.pluginIdAttr = attrName;
/*     */   }
/*     */   
/*     */   public void postRegisterInit(String matchPattern) throws PluginConfigurationException {
/* 158 */     Log log = LogUtils.getLogger(this.digester);
/* 159 */     boolean debug = log.isDebugEnabled();
/* 160 */     if (debug)
/* 161 */       log.debug("PluginCreateRule.postRegisterInit: rule registered for pattern [" + matchPattern + "]"); 
/* 165 */     if (this.digester == null) {
/* 170 */       this.initException = new PluginConfigurationException("Invalid invocation of postRegisterInit: digester not set.");
/* 173 */       throw this.initException;
/*     */     } 
/* 176 */     if (this.pattern != null) {
/* 184 */       this.initException = new PluginConfigurationException("A single PluginCreateRule instance has been mapped to multiple patterns; this is not supported.");
/* 187 */       throw this.initException;
/*     */     } 
/* 190 */     if (matchPattern.indexOf('*') != -1) {
/* 202 */       this.initException = new PluginConfigurationException("A PluginCreateRule instance has been mapped to pattern [" + matchPattern + "]." + " This pattern includes a wildcard character." + " This is not supported by the plugin architecture.");
/* 207 */       throw this.initException;
/*     */     } 
/* 210 */     if (this.baseClass == null)
/* 211 */       this.baseClass = Object.class; 
/* 214 */     PluginRules rules = (PluginRules)this.digester.getRules();
/* 215 */     PluginManager pm = rules.getPluginManager();
/* 218 */     if (this.defaultPlugin != null) {
/* 219 */       if (!this.baseClass.isAssignableFrom(this.defaultPlugin.getPluginClass())) {
/* 220 */         this.initException = new PluginConfigurationException("Default class [" + this.defaultPlugin.getPluginClass().getName() + "] does not inherit from [" + this.baseClass.getName() + "].");
/* 225 */         throw this.initException;
/*     */       } 
/*     */       try {
/* 229 */         this.defaultPlugin.init(this.digester, pm);
/* 231 */       } catch (PluginException pwe) {
/* 233 */         throw new PluginConfigurationException(pwe.getMessage(), pwe.getCause());
/*     */       } 
/*     */     } 
/* 239 */     this.pattern = matchPattern;
/* 241 */     if (this.pluginClassAttr == null) {
/* 244 */       this.pluginClassAttrNs = rules.getPluginClassAttrNs();
/* 245 */       this.pluginClassAttr = rules.getPluginClassAttr();
/* 247 */       if (debug)
/* 248 */         log.debug("init: pluginClassAttr set to per-digester values [ns=" + this.pluginClassAttrNs + ", name=" + this.pluginClassAttr + "]"); 
/* 254 */     } else if (debug) {
/* 255 */       log.debug("init: pluginClassAttr set to rule-specific values [ns=" + this.pluginClassAttrNs + ", name=" + this.pluginClassAttr + "]");
/*     */     } 
/* 262 */     if (this.pluginIdAttr == null) {
/* 265 */       this.pluginIdAttrNs = rules.getPluginIdAttrNs();
/* 266 */       this.pluginIdAttr = rules.getPluginIdAttr();
/* 268 */       if (debug)
/* 269 */         log.debug("init: pluginIdAttr set to per-digester values [ns=" + this.pluginIdAttrNs + ", name=" + this.pluginIdAttr + "]"); 
/* 275 */     } else if (debug) {
/* 276 */       log.debug("init: pluginIdAttr set to rule-specific values [ns=" + this.pluginIdAttrNs + ", name=" + this.pluginIdAttr + "]");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void begin(String namespace, String name, Attributes attributes) throws Exception {
/*     */     String pluginClassName, pluginId;
/* 305 */     Log log = this.digester.getLogger();
/* 306 */     boolean debug = log.isDebugEnabled();
/* 307 */     if (debug)
/* 308 */       log.debug("PluginCreateRule.begin: pattern=[" + this.pattern + "]" + " match=[" + this.digester.getMatch() + "]"); 
/* 312 */     if (this.initException != null)
/* 315 */       throw this.initException; 
/* 319 */     PluginRules oldRules = (PluginRules)this.digester.getRules();
/* 320 */     PluginManager pluginManager = oldRules.getPluginManager();
/* 321 */     Declaration currDeclaration = null;
/* 324 */     if (this.pluginClassAttrNs == null) {
/* 332 */       pluginClassName = attributes.getValue(this.pluginClassAttr);
/*     */     } else {
/* 334 */       pluginClassName = attributes.getValue(this.pluginClassAttrNs, this.pluginClassAttr);
/*     */     } 
/* 339 */     if (this.pluginIdAttrNs == null) {
/* 340 */       pluginId = attributes.getValue(this.pluginIdAttr);
/*     */     } else {
/* 342 */       pluginId = attributes.getValue(this.pluginIdAttrNs, this.pluginIdAttr);
/*     */     } 
/* 346 */     if (pluginClassName != null) {
/* 353 */       currDeclaration = pluginManager.getDeclarationByClass(pluginClassName);
/* 356 */       if (currDeclaration == null) {
/* 357 */         currDeclaration = new Declaration(pluginClassName);
/*     */         try {
/* 359 */           currDeclaration.init(this.digester, pluginManager);
/* 360 */         } catch (PluginException pwe) {
/* 361 */           throw new PluginInvalidInputException(pwe.getMessage(), pwe.getCause());
/*     */         } 
/* 364 */         pluginManager.addDeclaration(currDeclaration);
/*     */       } 
/* 366 */     } else if (pluginId != null) {
/* 367 */       currDeclaration = pluginManager.getDeclarationById(pluginId);
/* 369 */       if (currDeclaration == null)
/* 370 */         throw new PluginInvalidInputException("Plugin id [" + pluginId + "] is not defined."); 
/* 373 */     } else if (this.defaultPlugin != null) {
/* 374 */       currDeclaration = this.defaultPlugin;
/*     */     } else {
/* 376 */       throw new PluginInvalidInputException("No plugin class specified for element " + this.pattern);
/*     */     } 
/* 382 */     Class pluginClass = currDeclaration.getPluginClass();
/* 384 */     String path = this.digester.getMatch();
/* 391 */     PluginRules newRules = new PluginRules(this.digester, path, oldRules, pluginClass);
/* 392 */     this.digester.setRules(newRules);
/* 394 */     if (debug)
/* 395 */       log.debug("PluginCreateRule.begin: installing new plugin: oldrules=" + oldRules.toString() + ", newrules=" + newRules.toString()); 
/* 401 */     currDeclaration.configure(this.digester, this.pattern);
/* 404 */     Object instance = pluginClass.newInstance();
/* 405 */     getDigester().push(instance);
/* 406 */     if (debug)
/* 407 */       log.debug("PluginCreateRule.begin: pattern=[" + this.pattern + "]" + " match=[" + this.digester.getMatch() + "]" + " pushed instance of plugin [" + pluginClass.getName() + "]"); 
/* 416 */     List rules = newRules.getDecoratedRules().match(namespace, path);
/* 417 */     fireBeginMethods(rules, namespace, name, attributes);
/*     */   }
/*     */   
/*     */   public void body(String namespace, String name, String text) throws Exception {
/* 441 */     String path = this.digester.getMatch();
/* 442 */     PluginRules newRules = (PluginRules)this.digester.getRules();
/* 443 */     List rules = newRules.getDecoratedRules().match(namespace, path);
/* 444 */     fireBodyMethods(rules, namespace, name, text);
/*     */   }
/*     */   
/*     */   public void end(String namespace, String name) throws Exception {
/* 463 */     String path = this.digester.getMatch();
/* 464 */     PluginRules newRules = (PluginRules)this.digester.getRules();
/* 465 */     List rules = newRules.getDecoratedRules().match(namespace, path);
/* 466 */     fireEndMethods(rules, namespace, name);
/* 470 */     this.digester.setRules(newRules.getParent());
/* 474 */     this.digester.pop();
/*     */   }
/*     */   
/*     */   public String getPattern() {
/* 490 */     return this.pattern;
/*     */   }
/*     */   
/*     */   public void fireBeginMethods(List rules, String namespace, String name, Attributes list) throws Exception {
/* 504 */     if (rules != null && rules.size() > 0) {
/* 505 */       Log log = this.digester.getLogger();
/* 506 */       boolean debug = log.isDebugEnabled();
/* 507 */       for (int i = 0; i < rules.size(); i++) {
/*     */         try {
/* 509 */           Rule rule = rules.get(i);
/* 510 */           if (debug)
/* 511 */             log.debug("  Fire begin() for " + rule); 
/* 513 */           rule.begin(namespace, name, list);
/* 514 */         } catch (Exception e) {
/* 515 */           throw this.digester.createSAXException(e);
/* 516 */         } catch (Error e) {
/* 517 */           throw e;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fireBodyMethods(List rules, String namespaceURI, String name, String text) throws Exception {
/* 533 */     if (rules != null && rules.size() > 0) {
/* 534 */       Log log = this.digester.getLogger();
/* 535 */       boolean debug = log.isDebugEnabled();
/* 536 */       for (int i = 0; i < rules.size(); i++) {
/*     */         try {
/* 538 */           Rule rule = rules.get(i);
/* 539 */           if (debug)
/* 540 */             log.debug("  Fire body() for " + rule); 
/* 542 */           rule.body(namespaceURI, name, text);
/* 543 */         } catch (Exception e) {
/* 544 */           throw this.digester.createSAXException(e);
/* 545 */         } catch (Error e) {
/* 546 */           throw e;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fireEndMethods(List rules, String namespaceURI, String name) throws Exception {
/* 563 */     if (rules != null) {
/* 564 */       Log log = this.digester.getLogger();
/* 565 */       boolean debug = log.isDebugEnabled();
/* 566 */       for (int i = 0; i < rules.size(); i++) {
/* 567 */         int j = rules.size() - i - 1;
/*     */         try {
/* 569 */           Rule rule = rules.get(j);
/* 570 */           if (debug)
/* 571 */             log.debug("  Fire end() for " + rule); 
/* 573 */           rule.end(namespaceURI, name);
/* 574 */         } catch (Exception e) {
/* 575 */           throw this.digester.createSAXException(e);
/* 576 */         } catch (Error e) {
/* 577 */           throw e;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\PluginCreateRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */