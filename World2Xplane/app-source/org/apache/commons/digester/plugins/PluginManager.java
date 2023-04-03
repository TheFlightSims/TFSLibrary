/*     */ package org.apache.commons.digester.plugins;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.digester.Digester;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class PluginManager {
/*  42 */   private HashMap declarationsByClass = new HashMap();
/*     */   
/*  45 */   private HashMap declarationsById = new HashMap();
/*     */   
/*     */   private PluginManager parent;
/*     */   
/*     */   private PluginContext pluginContext;
/*     */   
/*     */   public PluginManager(PluginContext r) {
/*  60 */     this.pluginContext = r;
/*     */   }
/*     */   
/*     */   public PluginManager(PluginManager parent) {
/*  74 */     this.parent = parent;
/*  75 */     this.pluginContext = parent.pluginContext;
/*     */   }
/*     */   
/*     */   public void addDeclaration(Declaration decl) {
/*  90 */     Log log = LogUtils.getLogger(null);
/*  91 */     boolean debug = log.isDebugEnabled();
/*  93 */     Class pluginClass = decl.getPluginClass();
/*  94 */     String id = decl.getId();
/*  96 */     this.declarationsByClass.put(pluginClass.getName(), decl);
/*  98 */     if (id != null) {
/*  99 */       this.declarationsById.put(id, decl);
/* 100 */       if (debug)
/* 101 */         log.debug("Indexing plugin-id [" + id + "]" + " -> class [" + pluginClass.getName() + "]"); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Declaration getDeclarationByClass(String className) {
/* 113 */     Declaration decl = (Declaration)this.declarationsByClass.get(className);
/* 116 */     if (decl == null && this.parent != null)
/* 117 */       decl = this.parent.getDeclarationByClass(className); 
/* 120 */     return decl;
/*     */   }
/*     */   
/*     */   public Declaration getDeclarationById(String id) {
/* 131 */     Declaration decl = (Declaration)this.declarationsById.get(id);
/* 133 */     if (decl == null && this.parent != null)
/* 134 */       decl = this.parent.getDeclarationById(id); 
/* 137 */     return decl;
/*     */   }
/*     */   
/*     */   public RuleLoader findLoader(Digester digester, String id, Class pluginClass, Properties props) throws PluginException {
/* 155 */     Log log = LogUtils.getLogger(digester);
/* 156 */     boolean debug = log.isDebugEnabled();
/* 157 */     log.debug("scanning ruleFinders to locate loader..");
/* 159 */     List ruleFinders = this.pluginContext.getRuleFinders();
/* 160 */     RuleLoader ruleLoader = null;
/*     */     try {
/* 162 */       Iterator i = ruleFinders.iterator();
/* 163 */       while (i.hasNext() && ruleLoader == null) {
/* 165 */         RuleFinder finder = i.next();
/* 166 */         if (debug)
/* 167 */           log.debug("checking finder of type " + finder.getClass().getName()); 
/* 169 */         ruleLoader = finder.findLoader(digester, pluginClass, props);
/*     */       } 
/* 172 */     } catch (PluginException e) {
/* 173 */       throw new PluginException("Unable to locate plugin rules for plugin with id [" + id + "]" + ", and class [" + pluginClass.getName() + "]" + ":" + e.getMessage(), e.getCause());
/*     */     } 
/* 179 */     log.debug("scanned ruleFinders.");
/* 181 */     return ruleLoader;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\PluginManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */