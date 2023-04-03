/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.PathResolver;
/*     */ import org.java.plugin.registry.Extension;
/*     */ import org.java.plugin.registry.ExtensionPoint;
/*     */ import org.java.plugin.registry.Identity;
/*     */ import org.java.plugin.registry.IntegrityCheckReport;
/*     */ import org.java.plugin.registry.Library;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.registry.PluginPrerequisite;
/*     */ import org.java.plugin.util.IoUtil;
/*     */ import org.java.plugin.util.ResourceManager;
/*     */ 
/*     */ class IntegrityChecker implements IntegrityCheckReport {
/*  44 */   private static Log log = LogFactory.getLog(IntegrityChecker.class);
/*     */   
/*     */   private final PluginRegistryImpl registry;
/*     */   
/*  47 */   private List<IntegrityCheckReport.ReportItem> items = new LinkedList<IntegrityCheckReport.ReportItem>();
/*     */   
/*     */   private int errorsCount;
/*     */   
/*     */   private int warningsCount;
/*     */   
/*     */   IntegrityChecker(PluginRegistryImpl aRegistry, Collection<IntegrityCheckReport.ReportItem> anItems) {
/*  53 */     this.items = new LinkedList<IntegrityCheckReport.ReportItem>();
/*  54 */     this.registry = aRegistry;
/*  55 */     for (IntegrityCheckReport.ReportItem item : anItems) {
/*  56 */       switch (item.getSeverity()) {
/*     */         case WARNING:
/*  60 */           this.warningsCount++;
/*     */           break;
/*     */       } 
/*  66 */       this.items.add(item);
/*     */     } 
/*     */   }
/*     */   
/*     */   void doCheck(PathResolver pathResolver) {
/*  71 */     int count = 0;
/*  72 */     this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "pluginsCheckStart", null));
/*     */     try {
/*  75 */       for (PluginDescriptor descriptor : this.registry.getPluginDescriptors()) {
/*  76 */         PluginDescriptorImpl descr = (PluginDescriptorImpl)descriptor;
/*  77 */         count++;
/*  78 */         this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, descr, IntegrityCheckReport.Error.NO_ERROR, "pluginCheckStart", descr.getUniqueId()));
/*  81 */         checkPlugin(descr, pathResolver);
/*  82 */         this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, descr, IntegrityCheckReport.Error.NO_ERROR, "pluginCheckFinish", descr.getUniqueId()));
/*     */       } 
/*  86 */     } catch (Exception e) {
/*  87 */       log.error("integrity check failed for registry " + this.registry, e);
/*  88 */       this.errorsCount++;
/*  89 */       this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.ERROR, null, IntegrityCheckReport.Error.CHECKER_FAULT, "pluginsCheckError", e));
/*     */     } 
/*  92 */     this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "pluginsCheckFinish", Integer.valueOf(count)));
/*     */   }
/*     */   
/*     */   private void checkPlugin(PluginDescriptorImpl descr, PathResolver pathResolver) {
/*  99 */     int count = 0;
/* 100 */     this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, descr, IntegrityCheckReport.Error.NO_ERROR, "prerequisitesCheckStart", descr.getUniqueId()));
/* 102 */     for (PluginPrerequisite prerequisite : descr.getPrerequisites()) {
/* 103 */       PluginPrerequisiteImpl pre = (PluginPrerequisiteImpl)prerequisite;
/* 104 */       count++;
/* 105 */       if (!pre.isOptional() && !pre.matches()) {
/* 106 */         this.errorsCount++;
/* 107 */         this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.ERROR, descr, IntegrityCheckReport.Error.UNSATISFIED_PREREQUISITE, "unsatisfiedPrerequisite", new Object[] { pre.getPluginId(), descr.getUniqueId() }));
/*     */       } 
/*     */     } 
/* 113 */     this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, descr, IntegrityCheckReport.Error.NO_ERROR, "prerequisitesCheckFinish", new Object[] { Integer.valueOf(count), descr.getUniqueId() }));
/* 117 */     if (pathResolver != null) {
/* 118 */       count = 0;
/* 119 */       this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, descr, IntegrityCheckReport.Error.NO_ERROR, "librariesCheckStart", descr.getUniqueId()));
/* 121 */       for (Library library : descr.getLibraries()) {
/* 122 */         LibraryImpl lib = (LibraryImpl)library;
/* 123 */         count++;
/* 124 */         URL url = pathResolver.resolvePath(lib, lib.getPath());
/* 125 */         if (!IoUtil.isResourceExists(url)) {
/* 126 */           this.errorsCount++;
/* 127 */           this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.ERROR, lib, IntegrityCheckReport.Error.BAD_LIBRARY, "accesToResourceFailed", new Object[] { lib.getUniqueId(), descr.getUniqueId(), url }));
/*     */         } 
/*     */       } 
/* 133 */       this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, descr, IntegrityCheckReport.Error.NO_ERROR, "librariesCheckFinish", new Object[] { Integer.valueOf(count), descr.getUniqueId() }));
/*     */     } else {
/* 138 */       this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, descr, IntegrityCheckReport.Error.NO_ERROR, "librariesCheckSkip", descr.getUniqueId()));
/*     */     } 
/* 142 */     count = 0;
/* 143 */     this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, descr, IntegrityCheckReport.Error.NO_ERROR, "extPointsCheckStart", null));
/* 145 */     for (ExtensionPoint extensionPoint : descr.getExtensionPoints()) {
/* 146 */       count++;
/* 147 */       ExtensionPointImpl extPoint = (ExtensionPointImpl)extensionPoint;
/* 148 */       this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, extPoint, IntegrityCheckReport.Error.NO_ERROR, "extPointCheckStart", extPoint.getUniqueId()));
/* 151 */       Collection<IntegrityCheckReport.ReportItem> extPointItems = extPoint.validate();
/* 152 */       for (IntegrityCheckReport.ReportItem item : extPointItems) {
/* 153 */         switch (item.getSeverity()) {
/*     */           case ERROR:
/* 155 */             this.errorsCount++;
/*     */             break;
/*     */           case WARNING:
/* 158 */             this.warningsCount++;
/*     */             break;
/*     */         } 
/* 164 */         this.items.add(item);
/*     */       } 
/* 166 */       this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, extPoint, IntegrityCheckReport.Error.NO_ERROR, "extPointCheckFinish", extPoint.getUniqueId()));
/*     */     } 
/* 170 */     this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, descr, IntegrityCheckReport.Error.NO_ERROR, "extPointsCheckFinish", new Object[] { Integer.valueOf(count), descr.getUniqueId() }));
/* 174 */     count = 0;
/* 175 */     this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, descr, IntegrityCheckReport.Error.NO_ERROR, "extsCheckStart", null));
/* 177 */     for (Extension extension : descr.getExtensions()) {
/* 178 */       count++;
/* 179 */       ExtensionImpl ext = (ExtensionImpl)extension;
/* 180 */       this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, ext, IntegrityCheckReport.Error.NO_ERROR, "extCheckStart", ext.getUniqueId()));
/* 182 */       Collection<IntegrityCheckReport.ReportItem> extItems = ext.validate();
/* 183 */       for (IntegrityCheckReport.ReportItem item : extItems) {
/* 184 */         switch (item.getSeverity()) {
/*     */           case ERROR:
/* 186 */             this.errorsCount++;
/*     */             break;
/*     */           case WARNING:
/* 189 */             this.warningsCount++;
/*     */             break;
/*     */         } 
/* 195 */         this.items.add(item);
/*     */       } 
/* 197 */       this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, ext, IntegrityCheckReport.Error.NO_ERROR, "extCheckFinish", ext.getUniqueId()));
/*     */     } 
/* 200 */     this.items.add(new ReportItemImpl(IntegrityCheckReport.Severity.INFO, descr, IntegrityCheckReport.Error.NO_ERROR, "extsCheckFinish", new Object[] { Integer.valueOf(count), descr.getUniqueId() }));
/*     */   }
/*     */   
/*     */   public int countErrors() {
/* 209 */     return this.errorsCount;
/*     */   }
/*     */   
/*     */   public int countWarnings() {
/* 216 */     return this.warningsCount;
/*     */   }
/*     */   
/*     */   public Collection<IntegrityCheckReport.ReportItem> getItems() {
/* 223 */     return this.items;
/*     */   }
/*     */   
/*     */   static class ReportItemImpl implements IntegrityCheckReport.ReportItem {
/*     */     private final IntegrityCheckReport.Severity severity;
/*     */     
/*     */     private final Identity source;
/*     */     
/*     */     private final IntegrityCheckReport.Error code;
/*     */     
/*     */     private final String msg;
/*     */     
/*     */     private final Object data;
/*     */     
/*     */     ReportItemImpl(IntegrityCheckReport.Severity aSeverity, Identity aSource, IntegrityCheckReport.Error aCode, String aMsg, Object aData) {
/* 235 */       this.severity = aSeverity;
/* 236 */       this.source = aSource;
/* 237 */       this.code = aCode;
/* 238 */       this.msg = aMsg;
/* 239 */       this.data = aData;
/*     */     }
/*     */     
/*     */     public IntegrityCheckReport.Error getCode() {
/* 246 */       return this.code;
/*     */     }
/*     */     
/*     */     public String getMessage() {
/* 253 */       return ResourceManager.getMessage("org.java.plugin.registry.xml", this.msg, this.data);
/*     */     }
/*     */     
/*     */     public String getMessage(Locale locale) {
/* 262 */       return ResourceManager.getMessage("org.java.plugin.registry.xml", this.msg, locale, this.data);
/*     */     }
/*     */     
/*     */     public IntegrityCheckReport.Severity getSeverity() {
/* 270 */       return this.severity;
/*     */     }
/*     */     
/*     */     public Identity getSource() {
/* 277 */       return this.source;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\IntegrityChecker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */