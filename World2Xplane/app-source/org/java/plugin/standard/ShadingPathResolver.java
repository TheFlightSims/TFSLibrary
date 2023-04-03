/*     */ package org.java.plugin.standard;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.java.plugin.registry.Identity;
/*     */ import org.java.plugin.registry.Library;
/*     */ import org.java.plugin.registry.PluginAttribute;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.registry.PluginElement;
/*     */ import org.java.plugin.registry.PluginFragment;
/*     */ import org.java.plugin.registry.UniqueIdentity;
/*     */ import org.java.plugin.util.ExtendedProperties;
/*     */ 
/*     */ public class ShadingPathResolver extends StandardPathResolver {
/*     */   private static final String UNPACK_MODE_ALWAIS = "always";
/*     */   
/*     */   private static final String UNPACK_MODE_NEVER = "never";
/*     */   
/*     */   private static final String UNPACK_MODE_SMART = "smart";
/*     */   
/*     */   private File shadowFolder;
/*     */   
/*     */   private String unpackMode;
/*     */   
/* 107 */   private Map<String, URL> shadowUrlMap = new HashMap<String, URL>();
/*     */   
/* 108 */   private Map<String, Boolean> unpackModeMap = new HashMap<String, Boolean>();
/*     */   
/*     */   private ShadowDataController controller;
/*     */   
/*     */   public synchronized void configure(ExtendedProperties config) throws Exception {
/* 117 */     super.configure(config);
/* 118 */     String folder = config.getProperty("shadowFolder");
/* 119 */     if (folder != null && folder.length() > 0)
/*     */       try {
/* 121 */         this.shadowFolder = (new File(folder)).getCanonicalFile();
/* 122 */       } catch (IOException ioe) {
/* 123 */         this.log.warn("failed initializing shadow folder " + folder + ", falling back to the default folder", ioe);
/*     */       }  
/* 127 */     if (this.shadowFolder == null)
/* 128 */       this.shadowFolder = new File(System.getProperty("java.io.tmpdir"), ".jpf-shadow"); 
/* 131 */     this.log.debug("shadow folder is " + this.shadowFolder);
/* 132 */     if (!this.shadowFolder.exists())
/* 133 */       this.shadowFolder.mkdirs(); 
/* 135 */     this.unpackMode = config.getProperty("unpackMode", "smart");
/* 136 */     this.log.debug("unpack mode parameter value is " + this.unpackMode);
/* 137 */     this.controller = ShadowDataController.init(this.shadowFolder, buildFileFilter(config));
/* 139 */     this.log.info("configured, shadow folder is " + this.shadowFolder);
/*     */   }
/*     */   
/*     */   private FileFilter buildFileFilter(ExtendedProperties config) {
/*     */     FileFilter includesFilter, excludesFilter;
/* 144 */     String patterns = config.getProperty("includes");
/* 145 */     if (patterns != null && patterns.trim().length() > 0) {
/* 146 */       includesFilter = new RegexpFileFilter(patterns);
/*     */     } else {
/* 148 */       includesFilter = null;
/*     */     } 
/* 151 */     patterns = config.getProperty("excludes");
/* 152 */     if (patterns != null && patterns.trim().length() > 0) {
/* 153 */       excludesFilter = new RegexpFileFilter(patterns);
/*     */     } else {
/* 155 */       excludesFilter = null;
/*     */     } 
/* 157 */     if (excludesFilter == null && includesFilter == null)
/* 158 */       return null; 
/* 160 */     return new CombinedFileFilter(includesFilter, excludesFilter);
/*     */   }
/*     */   
/*     */   public void registerContext(Identity idt, URL url) {
/*     */     Boolean mode;
/* 169 */     super.registerContext(idt, url);
/* 171 */     if ("always".equalsIgnoreCase(this.unpackMode)) {
/* 172 */       mode = Boolean.TRUE;
/* 173 */     } else if ("never".equalsIgnoreCase(this.unpackMode)) {
/* 174 */       mode = Boolean.FALSE;
/*     */     } else {
/* 176 */       PluginDescriptor descr = null;
/* 177 */       PluginFragment fragment = null;
/* 178 */       if (idt instanceof PluginDescriptor) {
/* 179 */         descr = (PluginDescriptor)idt;
/* 180 */       } else if (idt instanceof PluginFragment) {
/* 181 */         fragment = (PluginFragment)idt;
/* 182 */         descr = fragment.getRegistry().getPluginDescriptor(fragment.getPluginId());
/* 184 */       } else if (idt instanceof PluginElement) {
/* 185 */         PluginElement<?> element = (PluginElement)idt;
/* 186 */         descr = element.getDeclaringPluginDescriptor();
/* 187 */         fragment = element.getDeclaringPluginFragment();
/*     */       } else {
/* 189 */         throw new IllegalArgumentException("unknown identity class " + idt.getClass().getName());
/*     */       } 
/* 192 */       mode = getUnpackMode(descr, fragment);
/*     */     } 
/* 194 */     this.log.debug("unpack mode for " + idt + " is " + mode);
/* 195 */     this.unpackModeMap.put(idt.getId(), mode);
/*     */   }
/*     */   
/*     */   private Boolean getUnpackMode(PluginDescriptor descr, PluginFragment fragment) {
/* 201 */     Iterator<PluginAttribute> i$ = filterCollection(descr.getAttributes("unpack"), fragment).iterator();
/* 201 */     if (i$.hasNext()) {
/* 201 */       PluginAttribute attr = i$.next();
/* 202 */       return Boolean.valueOf("false".equalsIgnoreCase(attr.getValue()));
/*     */     } 
/* 205 */     for (Library lib : filterCollection(descr.getLibraries(), fragment)) {
/* 206 */       if (lib.isCodeLibrary() && (lib.getPath().toLowerCase(Locale.getDefault()).endsWith(".jar") || lib.getPath().toLowerCase(Locale.getDefault()).endsWith(".zip")))
/* 210 */         return Boolean.TRUE; 
/*     */     } 
/* 213 */     return Boolean.FALSE;
/*     */   }
/*     */   
/*     */   private <T extends PluginElement<?>> Collection<T> filterCollection(Collection<T> coll, PluginFragment fragment) {
/* 218 */     if (fragment == null)
/* 219 */       return coll; 
/* 221 */     LinkedList<T> result = new LinkedList<T>();
/* 222 */     for (PluginElement pluginElement : coll) {
/* 223 */       if (fragment.equals(pluginElement.getDeclaringPluginFragment()))
/* 224 */         result.add((T)pluginElement); 
/*     */     } 
/* 227 */     return result;
/*     */   }
/*     */   
/*     */   public void unregisterContext(String id) {
/* 236 */     this.shadowUrlMap.remove(id);
/* 237 */     this.unpackModeMap.remove(id);
/* 238 */     super.unregisterContext(id);
/*     */   }
/*     */   
/*     */   public URL resolvePath(Identity idt, String path) {
/*     */     URL baseUrl;
/* 248 */     if (idt instanceof PluginDescriptor) {
/* 249 */       baseUrl = getBaseUrl((UniqueIdentity)idt);
/* 250 */     } else if (idt instanceof PluginFragment) {
/* 251 */       baseUrl = getBaseUrl((UniqueIdentity)idt);
/* 252 */     } else if (idt instanceof PluginElement) {
/* 253 */       PluginElement<?> element = (PluginElement)idt;
/* 254 */       if (element.getDeclaringPluginFragment() != null) {
/* 255 */         baseUrl = getBaseUrl((UniqueIdentity)element.getDeclaringPluginFragment());
/*     */       } else {
/* 258 */         baseUrl = getBaseUrl((UniqueIdentity)element.getDeclaringPluginDescriptor());
/*     */       } 
/*     */     } else {
/* 262 */       throw new IllegalArgumentException("unknown identity class " + idt.getClass().getName());
/*     */     } 
/* 265 */     return resolvePath(baseUrl, path);
/*     */   }
/*     */   
/*     */   protected synchronized URL getBaseUrl(UniqueIdentity uid) {
/* 269 */     URL result = this.shadowUrlMap.get(uid.getId());
/* 270 */     if (result != null)
/* 271 */       return result; 
/* 273 */     result = this.controller.shadowResource(getRegisteredContext(uid.getId()), uid.getUniqueId(), ((Boolean)this.unpackModeMap.get(uid.getId())).booleanValue());
/* 276 */     this.shadowUrlMap.put(uid.getId(), result);
/* 277 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\ShadingPathResolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */