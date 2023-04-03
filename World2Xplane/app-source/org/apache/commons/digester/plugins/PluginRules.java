/*     */ package org.apache.commons.digester.plugins;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.digester.Digester;
/*     */ import org.apache.commons.digester.Rule;
/*     */ import org.apache.commons.digester.Rules;
/*     */ import org.apache.commons.digester.RulesBase;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class PluginRules implements Rules {
/*  57 */   protected Digester digester = null;
/*     */   
/*     */   private RulesFactory rulesFactory;
/*     */   
/*     */   private Rules decoratedRules;
/*     */   
/*     */   private PluginManager pluginManager;
/*     */   
/*  78 */   private String mountPoint = null;
/*     */   
/*  84 */   private PluginRules parent = null;
/*     */   
/*  90 */   private PluginContext pluginContext = null;
/*     */   
/*     */   public PluginRules() {
/* 100 */     this((Rules)new RulesBase());
/*     */   }
/*     */   
/*     */   public PluginRules(Rules decoratedRules) {
/* 108 */     this.decoratedRules = decoratedRules;
/* 110 */     this.pluginContext = new PluginContext();
/* 111 */     this.pluginManager = new PluginManager(this.pluginContext);
/*     */   }
/*     */   
/*     */   PluginRules(Digester digester, String mountPoint, PluginRules parent, Class pluginClass) throws PluginException {
/* 140 */     this.digester = digester;
/* 141 */     this.mountPoint = mountPoint;
/* 142 */     this.parent = parent;
/* 143 */     this.rulesFactory = parent.rulesFactory;
/* 145 */     if (this.rulesFactory == null) {
/* 146 */       this.decoratedRules = (Rules)new RulesBase();
/*     */     } else {
/* 148 */       this.decoratedRules = this.rulesFactory.newRules(digester, pluginClass);
/*     */     } 
/* 151 */     this.pluginContext = parent.pluginContext;
/* 152 */     this.pluginManager = new PluginManager(parent.pluginManager);
/*     */   }
/*     */   
/*     */   public Rules getParent() {
/* 161 */     return this.parent;
/*     */   }
/*     */   
/*     */   public Digester getDigester() {
/* 168 */     return this.digester;
/*     */   }
/*     */   
/*     */   public void setDigester(Digester digester) {
/* 177 */     this.digester = digester;
/* 178 */     this.decoratedRules.setDigester(digester);
/*     */   }
/*     */   
/*     */   public String getNamespaceURI() {
/* 186 */     return this.decoratedRules.getNamespaceURI();
/*     */   }
/*     */   
/*     */   public void setNamespaceURI(String namespaceURI) {
/* 198 */     this.decoratedRules.setNamespaceURI(namespaceURI);
/*     */   }
/*     */   
/*     */   public PluginManager getPluginManager() {
/* 207 */     return this.pluginManager;
/*     */   }
/*     */   
/*     */   public List getRuleFinders() {
/* 214 */     return this.pluginContext.getRuleFinders();
/*     */   }
/*     */   
/*     */   public void setRuleFinders(List ruleFinders) {
/* 221 */     this.pluginContext.setRuleFinders(ruleFinders);
/*     */   }
/*     */   
/*     */   public RulesFactory getRulesFactory() {
/* 228 */     return this.rulesFactory;
/*     */   }
/*     */   
/*     */   public void setRulesFactory(RulesFactory factory) {
/* 236 */     this.rulesFactory = factory;
/*     */   }
/*     */   
/*     */   Rules getDecoratedRules() {
/* 247 */     return this.decoratedRules;
/*     */   }
/*     */   
/*     */   public List rules() {
/* 260 */     return this.decoratedRules.rules();
/*     */   }
/*     */   
/*     */   public void add(String pattern, Rule rule) {
/* 272 */     Log log = LogUtils.getLogger(this.digester);
/* 273 */     boolean debug = log.isDebugEnabled();
/* 275 */     if (debug)
/* 276 */       log.debug("add entry: mapping pattern [" + pattern + "]" + " to rule of type [" + rule.getClass().getName() + "]"); 
/* 281 */     if (pattern.startsWith("/"))
/* 283 */       pattern = pattern.substring(1); 
/* 286 */     if (this.mountPoint != null && 
/* 287 */       !pattern.equals(this.mountPoint) && !pattern.startsWith(this.mountPoint + "/")) {
/* 296 */       log.warn("An attempt was made to add a rule with a pattern thatis not at or below the mountpoint of the current PluginRules object. Rule pattern: " + pattern + ", mountpoint: " + this.mountPoint + ", rule type: " + rule.getClass().getName());
/*     */       return;
/*     */     } 
/* 307 */     this.decoratedRules.add(pattern, rule);
/* 309 */     if (rule instanceof InitializableRule)
/*     */       try {
/* 311 */         ((InitializableRule)rule).postRegisterInit(pattern);
/* 312 */       } catch (PluginConfigurationException e) {
/* 318 */         if (debug)
/* 319 */           log.debug("Rule initialisation failed", e); 
/*     */         return;
/*     */       }  
/* 326 */     if (debug)
/* 327 */       log.debug("add exit: mapped pattern [" + pattern + "]" + " to rule of type [" + rule.getClass().getName() + "]"); 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 336 */     this.decoratedRules.clear();
/*     */   }
/*     */   
/*     */   public List match(String path) {
/* 351 */     return match(null, path);
/*     */   }
/*     */   
/*     */   public List match(String namespaceURI, String path) {
/*     */     List matches;
/* 366 */     Log log = LogUtils.getLogger(this.digester);
/* 367 */     boolean debug = log.isDebugEnabled();
/* 369 */     if (debug)
/* 370 */       log.debug("Matching path [" + path + "] on rules object " + toString()); 
/* 376 */     if (this.mountPoint != null && path.length() <= this.mountPoint.length()) {
/* 378 */       if (debug)
/* 379 */         log.debug("Path [" + path + "] delegated to parent."); 
/* 383 */       matches = this.parent.match(namespaceURI, path);
/*     */     } else {
/* 391 */       log.debug("delegating to decorated rules.");
/* 392 */       matches = this.decoratedRules.match(namespaceURI, path);
/*     */     } 
/* 395 */     return matches;
/*     */   }
/*     */   
/*     */   public void setPluginClassAttribute(String namespaceUri, String attrName) {
/* 401 */     this.pluginContext.setPluginClassAttribute(namespaceUri, attrName);
/*     */   }
/*     */   
/*     */   public void setPluginIdAttribute(String namespaceUri, String attrName) {
/* 407 */     this.pluginContext.setPluginIdAttribute(namespaceUri, attrName);
/*     */   }
/*     */   
/*     */   public String getPluginClassAttrNs() {
/* 412 */     return this.pluginContext.getPluginClassAttrNs();
/*     */   }
/*     */   
/*     */   public String getPluginClassAttr() {
/* 417 */     return this.pluginContext.getPluginClassAttr();
/*     */   }
/*     */   
/*     */   public String getPluginIdAttrNs() {
/* 422 */     return this.pluginContext.getPluginIdAttrNs();
/*     */   }
/*     */   
/*     */   public String getPluginIdAttr() {
/* 427 */     return this.pluginContext.getPluginIdAttr();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\PluginRules.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */