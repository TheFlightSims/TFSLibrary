/*     */ package org.apache.commons.digester.plugins;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.digester.plugins.strategies.FinderFromClass;
/*     */ import org.apache.commons.digester.plugins.strategies.FinderFromDfltClass;
/*     */ import org.apache.commons.digester.plugins.strategies.FinderFromDfltMethod;
/*     */ import org.apache.commons.digester.plugins.strategies.FinderFromDfltResource;
/*     */ import org.apache.commons.digester.plugins.strategies.FinderFromFile;
/*     */ import org.apache.commons.digester.plugins.strategies.FinderFromMethod;
/*     */ import org.apache.commons.digester.plugins.strategies.FinderFromResource;
/*     */ import org.apache.commons.digester.plugins.strategies.FinderSetProperties;
/*     */ 
/*     */ public class PluginContext {
/*  54 */   public final String DFLT_PLUGIN_CLASS_ATTR_NS = null;
/*     */   
/*  55 */   public final String DFLT_PLUGIN_CLASS_ATTR = "plugin-class";
/*     */   
/*  59 */   public final String DFLT_PLUGIN_ID_ATTR_NS = null;
/*     */   
/*  60 */   public final String DFLT_PLUGIN_ID_ATTR = "plugin-id";
/*     */   
/*  63 */   private String pluginClassAttrNs = this.DFLT_PLUGIN_CLASS_ATTR_NS;
/*     */   
/*  66 */   private String pluginClassAttr = "plugin-class";
/*     */   
/*  69 */   private String pluginIdAttrNs = this.DFLT_PLUGIN_ID_ATTR_NS;
/*     */   
/*  72 */   private String pluginIdAttr = "plugin-id";
/*     */   
/*     */   private List ruleFinders;
/*     */   
/*     */   public List getRuleFinders() {
/*  98 */     if (this.ruleFinders == null) {
/* 102 */       this.ruleFinders = new LinkedList();
/* 103 */       this.ruleFinders.add(new FinderFromFile());
/* 104 */       this.ruleFinders.add(new FinderFromResource());
/* 105 */       this.ruleFinders.add(new FinderFromClass());
/* 106 */       this.ruleFinders.add(new FinderFromMethod());
/* 107 */       this.ruleFinders.add(new FinderFromDfltMethod());
/* 108 */       this.ruleFinders.add(new FinderFromDfltClass());
/* 109 */       this.ruleFinders.add(new FinderFromDfltResource());
/* 110 */       this.ruleFinders.add(new FinderFromDfltResource(".xml"));
/* 111 */       this.ruleFinders.add(new FinderSetProperties());
/*     */     } 
/* 113 */     return this.ruleFinders;
/*     */   }
/*     */   
/*     */   public void setRuleFinders(List ruleFinders) {
/* 127 */     this.ruleFinders = ruleFinders;
/*     */   }
/*     */   
/*     */   public void setPluginClassAttribute(String namespaceUri, String attrName) {
/* 165 */     this.pluginClassAttrNs = namespaceUri;
/* 166 */     this.pluginClassAttr = attrName;
/*     */   }
/*     */   
/*     */   public void setPluginIdAttribute(String namespaceUri, String attrName) {
/* 204 */     this.pluginIdAttrNs = namespaceUri;
/* 205 */     this.pluginIdAttr = attrName;
/*     */   }
/*     */   
/*     */   public String getPluginClassAttrNs() {
/* 215 */     return this.pluginClassAttrNs;
/*     */   }
/*     */   
/*     */   public String getPluginClassAttr() {
/* 225 */     return this.pluginClassAttr;
/*     */   }
/*     */   
/*     */   public String getPluginIdAttrNs() {
/* 235 */     return this.pluginIdAttrNs;
/*     */   }
/*     */   
/*     */   public String getPluginIdAttr() {
/* 245 */     return this.pluginIdAttr;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\PluginContext.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */