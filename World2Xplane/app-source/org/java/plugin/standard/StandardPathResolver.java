/*     */ package org.java.plugin.standard;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.PathResolver;
/*     */ import org.java.plugin.registry.Identity;
/*     */ import org.java.plugin.registry.PluginElement;
/*     */ import org.java.plugin.util.ExtendedProperties;
/*     */ import org.java.plugin.util.IoUtil;
/*     */ 
/*     */ public class StandardPathResolver implements PathResolver {
/*  45 */   protected Log log = LogFactory.getLog(getClass());
/*     */   
/*  46 */   private Map<String, URL> urlMap = new HashMap<String, URL>();
/*     */   
/*     */   public void registerContext(Identity idt, URL url) {
/*  55 */     if (!(idt instanceof org.java.plugin.registry.PluginDescriptor) && !(idt instanceof org.java.plugin.registry.PluginFragment))
/*  57 */       throw new IllegalArgumentException("unsupported identity class " + idt.getClass().getName()); 
/*  61 */     URL oldUrl = this.urlMap.put(idt.getId(), url);
/*  62 */     if (oldUrl != null) {
/*  63 */       this.log.warn("old context URL " + oldUrl + " has been replaced with new " + url + " for " + idt + " with key " + idt.getId());
/*  68 */     } else if (this.log.isDebugEnabled()) {
/*  69 */       this.log.debug("context URL " + url + " registered for " + idt + " with key " + idt.getId());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unregisterContext(String id) {
/*  80 */     URL url = this.urlMap.remove(id);
/*  81 */     if (url == null) {
/*  82 */       this.log.warn("no context was registered with key " + id);
/*  84 */     } else if (this.log.isDebugEnabled()) {
/*  85 */       this.log.debug("context URL " + url + " un-registered for key " + id);
/*     */     } 
/*     */   }
/*     */   
/*     */   public URL resolvePath(Identity identity, String path) {
/*     */     URL baseUrl;
/*  97 */     if (identity instanceof org.java.plugin.registry.PluginDescriptor || identity instanceof org.java.plugin.registry.PluginFragment) {
/*  99 */       baseUrl = getRegisteredContext(identity.getId());
/* 100 */     } else if (identity instanceof PluginElement) {
/* 101 */       PluginElement<?> element = (PluginElement)identity;
/* 102 */       if (element.getDeclaringPluginFragment() != null) {
/* 103 */         baseUrl = getRegisteredContext(element.getDeclaringPluginFragment().getId());
/*     */       } else {
/* 106 */         baseUrl = getRegisteredContext(element.getDeclaringPluginDescriptor().getId());
/*     */       } 
/*     */     } else {
/* 110 */       throw new IllegalArgumentException("unknown identity class " + identity.getClass().getName());
/*     */     } 
/* 113 */     return resolvePath(baseUrl, path);
/*     */   }
/*     */   
/*     */   public URL getRegisteredContext(String id) {
/* 120 */     URL result = this.urlMap.get(id);
/* 121 */     if (result == null)
/* 122 */       throw new IllegalArgumentException("unknown plug-in or plug-in fragment ID - " + id); 
/* 125 */     return result;
/*     */   }
/*     */   
/*     */   public boolean isContextRegistered(String id) {
/* 132 */     return this.urlMap.containsKey(id);
/*     */   }
/*     */   
/*     */   protected URL resolvePath(URL baseUrl, String path) {
/*     */     try {
/* 143 */       if ("".equals(path) || "/".equals(path))
/* 144 */         return maybeJarUrl(baseUrl); 
/* 146 */       return maybeJarUrl(new URL(maybeJarUrl(baseUrl), path));
/* 147 */     } catch (MalformedURLException mue) {
/* 148 */       this.log.error("can't create URL in context of " + baseUrl + " and path " + path, mue);
/* 150 */       throw new IllegalArgumentException("path " + path + " in context of " + baseUrl + " cause creation of malformed URL");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected URL maybeJarUrl(URL url) throws MalformedURLException {
/* 157 */     if ("jar".equalsIgnoreCase(url.getProtocol()))
/* 158 */       return url; 
/* 160 */     File file = IoUtil.url2file(url);
/* 161 */     if (file == null || !file.isFile())
/* 162 */       return url; 
/* 164 */     String fileName = file.getName().toLowerCase(Locale.getDefault());
/* 166 */     if (fileName.endsWith(".jar") || fileName.endsWith(".zip"))
/* 168 */       return new URL("jar:" + IoUtil.file2url(file).toExternalForm() + "!/"); 
/* 172 */     return url;
/*     */   }
/*     */   
/*     */   public void configure(ExtendedProperties config) throws Exception {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\StandardPathResolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */