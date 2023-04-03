/*     */ package org.java.plugin.standard;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.security.AccessController;
/*     */ import java.security.CodeSource;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.ProtectionDomain;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.PathResolver;
/*     */ import org.java.plugin.PluginClassLoader;
/*     */ import org.java.plugin.PluginManager;
/*     */ import org.java.plugin.registry.Identity;
/*     */ import org.java.plugin.registry.Library;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.registry.PluginPrerequisite;
/*     */ import org.java.plugin.registry.PluginRegistry;
/*     */ import org.java.plugin.util.IoUtil;
/*     */ 
/*     */ public class StandardPluginClassLoader extends PluginClassLoader {
/*  63 */   static Log log = LogFactory.getLog(StandardPluginClassLoader.class);
/*     */   
/*     */   private static File libCacheFolder;
/*     */   
/*     */   private static boolean libCacheFolderInitialized = false;
/*     */   
/*     */   private PluginDescriptor[] publicImports;
/*     */   
/*     */   private PluginDescriptor[] privateImports;
/*     */   
/*     */   private PluginDescriptor[] reverseLookups;
/*     */   
/*     */   private PluginResourceLoader resourceLoader;
/*     */   
/*     */   private Map<String, ResourceFilter> resourceFilters;
/*     */   
/*     */   private Map<String, File> libraryCache;
/*     */   
/*     */   private boolean probeParentLoaderLast;
/*     */   
/*     */   private static URL getClassBaseUrl(Class<?> cls) {
/*  69 */     ProtectionDomain pd = cls.getProtectionDomain();
/*  70 */     if (pd != null) {
/*  71 */       CodeSource cs = pd.getCodeSource();
/*  72 */       if (cs != null)
/*  73 */         return cs.getLocation(); 
/*     */     } 
/*  76 */     return null;
/*     */   }
/*     */   
/*     */   private static URL[] getUrls(PluginManager manager, PluginDescriptor descr) {
/*  81 */     List<URL> result = new LinkedList<URL>();
/*  82 */     for (Library lib : descr.getLibraries()) {
/*  83 */       if (!lib.isCodeLibrary())
/*     */         continue; 
/*  86 */       result.add(manager.getPathResolver().resolvePath((Identity)lib, lib.getPath()));
/*     */     } 
/*  89 */     if (log.isDebugEnabled()) {
/*  90 */       StringBuilder buf = new StringBuilder();
/*  91 */       buf.append("Code URL's populated for plug-in " + descr + ":\r\n");
/*  93 */       for (URL element : result) {
/*  94 */         buf.append("\t");
/*  95 */         buf.append(element);
/*  96 */         buf.append("\r\n");
/*     */       } 
/*  98 */       log.debug(buf.toString());
/*     */     } 
/* 100 */     return result.<URL>toArray(new URL[result.size()]);
/*     */   }
/*     */   
/*     */   private static URL[] getUrls(PluginManager manager, PluginDescriptor descr, URL[] existingUrls) {
/* 105 */     List<URL> urls = Arrays.asList(existingUrls);
/* 106 */     List<URL> result = new LinkedList<URL>();
/* 107 */     for (Library lib : descr.getLibraries()) {
/* 108 */       if (!lib.isCodeLibrary())
/*     */         continue; 
/* 111 */       URL url = manager.getPathResolver().resolvePath((Identity)lib, lib.getPath());
/* 112 */       if (!urls.contains(url))
/* 113 */         result.add(url); 
/*     */     } 
/* 116 */     return result.<URL>toArray(new URL[result.size()]);
/*     */   }
/*     */   
/*     */   private static File getLibCacheFolder() {
/* 120 */     if (libCacheFolder != null)
/* 121 */       return libCacheFolderInitialized ? libCacheFolder : null; 
/* 123 */     synchronized (StandardPluginClassLoader.class) {
/* 124 */       libCacheFolder = new File(System.getProperty("java.io.tmpdir"), System.currentTimeMillis() + ".jpf-lib-cache");
/* 126 */       log.debug("libraries cache folder is " + libCacheFolder);
/* 127 */       File lockFile = new File(libCacheFolder, "lock");
/* 128 */       if (lockFile.exists()) {
/* 129 */         log.error("can't initialize libraries cache folder " + libCacheFolder + " as lock file indicates that it" + " is owned by another JPF instance");
/* 132 */         return null;
/*     */       } 
/* 134 */       if (libCacheFolder.exists()) {
/* 136 */         IoUtil.emptyFolder(libCacheFolder);
/*     */       } else {
/* 138 */         libCacheFolder.mkdirs();
/*     */       } 
/*     */       try {
/* 141 */         if (!lockFile.createNewFile()) {
/* 142 */           log.error("can't create lock file in JPF libraries cache folder " + libCacheFolder);
/* 144 */           return null;
/*     */         } 
/* 146 */       } catch (IOException ioe) {
/* 147 */         log.error("can't create lock file in JPF libraries cache folder " + libCacheFolder, ioe);
/* 149 */         return null;
/*     */       } 
/* 151 */       lockFile.deleteOnExit();
/* 152 */       libCacheFolder.deleteOnExit();
/* 153 */       libCacheFolderInitialized = true;
/*     */     } 
/* 155 */     return libCacheFolder;
/*     */   }
/*     */   
/*     */   public StandardPluginClassLoader(PluginManager aManager, PluginDescriptor descr, ClassLoader parent) {
/* 180 */     super(aManager, descr, getUrls(aManager, descr), parent);
/* 181 */     collectImports();
/* 182 */     this.resourceLoader = PluginResourceLoader.get(aManager, descr);
/* 183 */     collectFilters();
/* 184 */     this.libraryCache = new HashMap<String, File>();
/*     */   }
/*     */   
/*     */   protected void collectImports() {
/* 189 */     Map<String, PluginDescriptor> publicImportsMap = new HashMap<String, PluginDescriptor>();
/* 190 */     Map<String, PluginDescriptor> privateImportsMap = new HashMap<String, PluginDescriptor>();
/* 191 */     PluginRegistry registry = getPluginDescriptor().getRegistry();
/* 192 */     for (PluginPrerequisite pre : getPluginDescriptor().getPrerequisites()) {
/* 193 */       if (!pre.matches())
/*     */         continue; 
/* 196 */       PluginDescriptor preDescr = registry.getPluginDescriptor(pre.getPluginId());
/* 198 */       if (pre.isExported()) {
/* 199 */         publicImportsMap.put(preDescr.getId(), preDescr);
/*     */         continue;
/*     */       } 
/* 201 */       privateImportsMap.put(preDescr.getId(), preDescr);
/*     */     } 
/* 204 */     this.publicImports = (PluginDescriptor[])publicImportsMap.values().toArray((Object[])new PluginDescriptor[publicImportsMap.size()]);
/* 206 */     this.privateImports = (PluginDescriptor[])privateImportsMap.values().toArray((Object[])new PluginDescriptor[privateImportsMap.size()]);
/* 209 */     Map<String, PluginDescriptor> reverseLookupsMap = new HashMap<String, PluginDescriptor>();
/* 210 */     for (PluginDescriptor descr : registry.getPluginDescriptors()) {
/* 211 */       if (descr.equals(getPluginDescriptor()) || publicImportsMap.containsKey(descr.getId()) || privateImportsMap.containsKey(descr.getId()))
/*     */         continue; 
/* 216 */       for (PluginPrerequisite pre : descr.getPrerequisites()) {
/* 217 */         if (!pre.getPluginId().equals(getPluginDescriptor().getId()) || !pre.isReverseLookup())
/*     */           continue; 
/* 221 */         if (!pre.matches())
/*     */           continue; 
/* 224 */         reverseLookupsMap.put(descr.getId(), descr);
/*     */       } 
/*     */     } 
/* 228 */     this.reverseLookups = (PluginDescriptor[])reverseLookupsMap.values().toArray((Object[])new PluginDescriptor[reverseLookupsMap.size()]);
/*     */   }
/*     */   
/*     */   protected void collectFilters() {
/* 233 */     if (this.resourceFilters == null) {
/* 234 */       this.resourceFilters = new HashMap<String, ResourceFilter>();
/*     */     } else {
/* 236 */       this.resourceFilters.clear();
/*     */     } 
/* 238 */     for (Library lib : getPluginDescriptor().getLibraries())
/* 239 */       this.resourceFilters.put(getPluginManager().getPathResolver().resolvePath((Identity)lib, lib.getPath()).toExternalForm(), new ResourceFilter(lib)); 
/*     */   }
/*     */   
/*     */   protected void pluginsSetChanged() {
/* 251 */     URL[] newUrls = getUrls(getPluginManager(), getPluginDescriptor(), getURLs());
/* 253 */     for (URL element : newUrls)
/* 254 */       addURL(element); 
/* 256 */     if (log.isDebugEnabled()) {
/* 257 */       StringBuilder buf = new StringBuilder();
/* 258 */       buf.append("New code URL's populated for plug-in " + getPluginDescriptor() + ":\r\n");
/* 260 */       for (URL element : newUrls) {
/* 261 */         buf.append("\t");
/* 262 */         buf.append(element);
/* 263 */         buf.append("\r\n");
/*     */       } 
/* 265 */       log.debug(buf.toString());
/*     */     } 
/* 267 */     collectImports();
/* 269 */     this.resourceLoader = PluginResourceLoader.get(getPluginManager(), getPluginDescriptor());
/* 271 */     collectFilters();
/* 272 */     Set<Map.Entry<String, File>> entrySet = this.libraryCache.entrySet();
/* 273 */     Iterator<Map.Entry<String, File>> it = entrySet.iterator();
/* 274 */     while (it.hasNext()) {
/* 275 */       if (((Map.Entry)it.next()).getValue() == null)
/* 276 */         it.remove(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void dispose() {
/* 286 */     for (File file : this.libraryCache.values())
/* 287 */       file.delete(); 
/* 289 */     this.libraryCache.clear();
/* 290 */     this.resourceFilters.clear();
/* 291 */     this.privateImports = null;
/* 292 */     this.publicImports = null;
/* 293 */     this.resourceLoader = null;
/*     */   }
/*     */   
/*     */   protected void setProbeParentLoaderLast(boolean value) {
/* 297 */     this.probeParentLoaderLast = value;
/*     */   }
/*     */   
/*     */   protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
/*     */     Class<?> clazz;
/* 311 */     if (this.probeParentLoaderLast) {
/*     */       try {
/* 313 */         clazz = loadClass(name, resolve, this, (Set<String>)null);
/* 314 */       } catch (ClassNotFoundException cnfe) {
/* 315 */         clazz = getParent().loadClass(name);
/*     */       } 
/* 317 */       if (clazz == null)
/* 318 */         clazz = getParent().loadClass(name); 
/*     */     } else {
/*     */       try {
/* 322 */         clazz = getParent().loadClass(name);
/* 323 */       } catch (ClassNotFoundException cnfe) {
/* 324 */         clazz = loadClass(name, resolve, this, (Set<String>)null);
/*     */       } 
/*     */     } 
/* 327 */     if (clazz != null)
/* 328 */       return clazz; 
/* 330 */     throw new ClassNotFoundException(name);
/*     */   }
/*     */   
/*     */   private Class<?> loadClass(String name, boolean resolve, StandardPluginClassLoader requestor, Set<String> seenPlugins) throws ClassNotFoundException {
/* 340 */     Set<String> seen = seenPlugins;
/* 341 */     if (seen != null && seen.contains(getPluginDescriptor().getId()))
/* 342 */       return null; 
/* 344 */     if (this != requestor && !getPluginManager().isPluginActivated(getPluginDescriptor()) && !getPluginManager().isPluginActivating(getPluginDescriptor())) {
/* 348 */       String msg = "can't load class " + name + ", plug-in " + getPluginDescriptor() + " is not activated yet";
/* 350 */       log.warn(msg);
/* 351 */       throw new ClassNotFoundException(msg);
/*     */     } 
/* 353 */     Class<?> result = null;
/* 354 */     boolean debugEnabled = log.isDebugEnabled();
/* 355 */     synchronized (this) {
/* 356 */       result = findLoadedClass(name);
/* 357 */       if (result != null) {
/* 358 */         if (debugEnabled)
/* 359 */           log.debug("loadClass(...): found loaded class, class=" + result + ", this=" + this + ", requestor=" + requestor); 
/* 363 */         checkClassVisibility(result, requestor);
/* 367 */         return result;
/*     */       } 
/*     */       try {
/* 371 */         result = findClass(name);
/* 373 */       } catch (LinkageError le) {
/* 374 */         if (debugEnabled)
/* 375 */           log.debug("loadClass(...): class loading failed, name=" + name + ", this=" + this + ", requestor=" + requestor, le); 
/* 379 */         throw le;
/* 380 */       } catch (ClassNotFoundException cnfe) {}
/* 383 */       if (result != null) {
/* 384 */         if (debugEnabled)
/* 385 */           log.debug("loadClass(...): found class, class=" + result + ", this=" + this + ", requestor=" + requestor); 
/* 389 */         checkClassVisibility(result, requestor);
/* 390 */         if (resolve)
/* 391 */           resolveClass(result); 
/* 393 */         return result;
/*     */       } 
/*     */     } 
/* 396 */     if (seen == null)
/* 397 */       seen = new HashSet<String>(); 
/* 399 */     if (debugEnabled)
/* 400 */       log.debug("loadClass(...): class not found, name=" + name + ", this=" + this + ", requestor=" + requestor); 
/* 404 */     seen.add(getPluginDescriptor().getId());
/* 405 */     for (PluginDescriptor element : this.publicImports) {
/* 406 */       if (!seen.contains(element.getId())) {
/* 409 */         result = ((StandardPluginClassLoader)getPluginManager().getPluginClassLoader(element)).loadClass(name, resolve, requestor, seen);
/* 412 */         if (result != null)
/*     */           break; 
/*     */       } 
/*     */     } 
/* 419 */     if (this == requestor && result == null)
/* 420 */       for (PluginDescriptor element : this.privateImports) {
/* 421 */         if (!seen.contains(element.getId())) {
/* 424 */           result = ((StandardPluginClassLoader)getPluginManager().getPluginClassLoader(element)).loadClass(name, resolve, requestor, seen);
/* 427 */           if (result != null)
/*     */             break; 
/*     */         } 
/*     */       }  
/* 435 */     if (result == null)
/* 436 */       for (PluginDescriptor descr : this.reverseLookups) {
/* 437 */         if (!seen.contains(descr.getId()))
/* 440 */           if (getPluginManager().isPluginActivated(descr) || getPluginManager().isPluginActivating(descr)) {
/* 444 */             result = ((StandardPluginClassLoader)getPluginManager().getPluginClassLoader(descr)).loadClass(name, resolve, requestor, seen);
/* 447 */             if (result != null)
/*     */               break; 
/*     */           }  
/*     */       }  
/* 456 */     return result;
/*     */   }
/*     */   
/*     */   protected void checkClassVisibility(Class<?> cls, StandardPluginClassLoader requestor) throws ClassNotFoundException {
/* 467 */     if (this == requestor)
/*     */       return; 
/* 470 */     URL lib = getClassBaseUrl(cls);
/* 471 */     if (lib == null)
/*     */       return; 
/* 474 */     ClassLoader loader = cls.getClassLoader();
/* 475 */     if (!(loader instanceof StandardPluginClassLoader))
/*     */       return; 
/* 478 */     if (loader != this) {
/* 479 */       ((StandardPluginClassLoader)loader).checkClassVisibility(cls, requestor);
/*     */     } else {
/* 482 */       ResourceFilter filter = this.resourceFilters.get(lib.toExternalForm());
/* 483 */       if (filter == null) {
/* 484 */         log.warn("class not visible, no class filter found, lib=" + lib + ", class=" + cls + ", this=" + this + ", requestor=" + requestor);
/* 487 */         throw new ClassNotFoundException("class " + cls.getName() + " is not visible for plug-in " + requestor.getPluginDescriptor().getId() + ", no filter found for library " + lib);
/*     */       } 
/* 493 */       if (!filter.isClassVisible(cls.getName())) {
/* 494 */         log.warn("class not visible, lib=" + lib + ", class=" + cls + ", this=" + this + ", requestor=" + requestor);
/* 497 */         throw new ClassNotFoundException("class " + cls.getName() + " is not visible for plug-in " + requestor.getPluginDescriptor().getId());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String findLibrary(String name) {
/* 509 */     if (name == null || "".equals(name.trim()))
/* 510 */       return null; 
/* 512 */     if (log.isDebugEnabled())
/* 513 */       log.debug("findLibrary(String): name=" + name + ", this=" + this); 
/* 516 */     String libname = System.mapLibraryName(name);
/* 517 */     String result = null;
/* 518 */     PathResolver pathResolver = getPluginManager().getPathResolver();
/* 519 */     for (Library lib : getPluginDescriptor().getLibraries()) {
/* 520 */       if (lib.isCodeLibrary())
/*     */         continue; 
/* 523 */       URL libUrl = pathResolver.resolvePath((Identity)lib, lib.getPath() + libname);
/* 524 */       if (log.isDebugEnabled())
/* 525 */         log.debug("findLibrary(String): trying URL " + libUrl); 
/* 527 */       File libFile = IoUtil.url2file(libUrl);
/* 528 */       if (libFile != null) {
/* 529 */         if (log.isDebugEnabled())
/* 530 */           log.debug("findLibrary(String): URL " + libUrl + " resolved as local file " + libFile); 
/* 533 */         if (libFile.isFile()) {
/* 534 */           result = libFile.getAbsolutePath();
/*     */           break;
/*     */         } 
/*     */         continue;
/*     */       } 
/* 541 */       String libraryCacheKey = libUrl.toExternalForm();
/* 542 */       libFile = this.libraryCache.get(libraryCacheKey);
/* 543 */       if (libFile != null) {
/* 544 */         if (libFile.isFile()) {
/* 545 */           result = libFile.getAbsolutePath();
/*     */           break;
/*     */         } 
/* 548 */         this.libraryCache.remove(libraryCacheKey);
/*     */       } 
/* 550 */       if (this.libraryCache.containsKey(libraryCacheKey))
/*     */         break; 
/* 554 */       libFile = cacheLibrary(libUrl, libname);
/* 555 */       if (libFile != null) {
/* 556 */         result = libFile.getAbsolutePath();
/*     */         break;
/*     */       } 
/*     */     } 
/* 560 */     if (log.isDebugEnabled())
/* 561 */       log.debug("findLibrary(String): name=" + name + ", libname=" + libname + ", result=" + result + ", this=" + this); 
/* 566 */     return result;
/*     */   }
/*     */   
/*     */   protected synchronized File cacheLibrary(URL libUrl, String libname) {
/* 571 */     String libraryCacheKey = libUrl.toExternalForm();
/* 572 */     File result = this.libraryCache.get(libraryCacheKey);
/* 573 */     if (result != null)
/* 574 */       return result; 
/*     */     try {
/* 577 */       File cacheFolder = getLibCacheFolder();
/* 578 */       if (cacheFolder == null)
/* 579 */         throw new IOException("can't initialize libraries cache folder"); 
/* 581 */       File libCachePluginFolder = new File(cacheFolder, getPluginDescriptor().getUniqueId());
/* 583 */       if (!libCachePluginFolder.exists() && !libCachePluginFolder.mkdirs())
/* 585 */         throw new IOException("can't create cache folder " + libCachePluginFolder); 
/* 588 */       result = new File(libCachePluginFolder, libname);
/* 589 */       InputStream in = IoUtil.getResourceInputStream(libUrl);
/*     */       try {
/* 591 */         OutputStream out = new BufferedOutputStream(new FileOutputStream(result));
/*     */         try {
/* 594 */           IoUtil.copyStream(in, out, 512);
/*     */         } finally {
/* 596 */           out.close();
/*     */         } 
/*     */       } finally {
/* 599 */         in.close();
/*     */       } 
/* 601 */       if (log.isDebugEnabled())
/* 602 */         log.debug("library " + libname + " successfully cached from URL " + libUrl + " and saved to local file " + result); 
/* 606 */     } catch (IOException ioe) {
/* 607 */       log.error("can't cache library " + libname + " from URL " + libUrl, ioe);
/* 609 */       result = null;
/*     */     } 
/* 611 */     this.libraryCache.put(libraryCacheKey, result);
/* 612 */     return result;
/*     */   }
/*     */   
/*     */   public URL findResource(String name) {
/* 621 */     URL result = findResource(name, this, (Set<String>)null);
/* 623 */     return result;
/*     */   }
/*     */   
/*     */   public Enumeration<URL> findResources(String name) throws IOException {
/* 631 */     List<URL> result = new LinkedList<URL>();
/* 632 */     findResources(result, name, this, (Set<String>)null);
/* 633 */     return Collections.enumeration(result);
/*     */   }
/*     */   
/*     */   protected URL findResource(String name, StandardPluginClassLoader requestor, Set<String> seenPlugins) {
/* 643 */     Set<String> seen = seenPlugins;
/* 644 */     if (seen != null && seen.contains(getPluginDescriptor().getId()))
/* 645 */       return null; 
/* 647 */     URL result = super.findResource(name);
/* 648 */     if (result != null) {
/* 649 */       if (log.isDebugEnabled())
/* 650 */         log.debug("findResource(...): resource found in classpath, name=" + name + " URL=" + result + ", this=" + this + ", requestor=" + requestor); 
/* 655 */       if (isResourceVisible(name, result, requestor))
/* 656 */         return result; 
/* 658 */       return null;
/*     */     } 
/* 660 */     if (this.resourceLoader != null) {
/* 661 */       result = this.resourceLoader.findResource(name);
/* 662 */       if (result != null) {
/* 664 */         if (log.isDebugEnabled())
/* 665 */           log.debug("findResource(...): resource found in libraries, name=" + name + ", URL=" + result + ", this=" + this + ", requestor=" + requestor); 
/* 670 */         if (isResourceVisible(name, result, requestor))
/* 671 */           return result; 
/* 673 */         return null;
/*     */       } 
/*     */     } 
/* 676 */     if (seen == null)
/* 677 */       seen = new HashSet<String>(); 
/* 679 */     if (log.isDebugEnabled())
/* 680 */       log.debug("findResource(...): resource not found, name=" + name + ", this=" + this + ", requestor=" + requestor); 
/* 684 */     seen.add(getPluginDescriptor().getId());
/* 685 */     for (PluginDescriptor element : this.publicImports) {
/* 686 */       if (!seen.contains(element.getId())) {
/* 689 */         result = ((StandardPluginClassLoader)getPluginManager().getPluginClassLoader(element)).findResource(name, requestor, seen);
/* 692 */         if (result != null)
/*     */           break; 
/*     */       } 
/*     */     } 
/* 696 */     if (this == requestor && result == null)
/* 697 */       for (PluginDescriptor element : this.privateImports) {
/* 698 */         if (!seen.contains(element.getId())) {
/* 701 */           result = ((StandardPluginClassLoader)getPluginManager().getPluginClassLoader(element)).findResource(name, requestor, seen);
/* 704 */           if (result != null)
/*     */             break; 
/*     */         } 
/*     */       }  
/* 709 */     if (result == null)
/* 710 */       for (PluginDescriptor element : this.reverseLookups) {
/* 711 */         if (!seen.contains(element.getId())) {
/* 714 */           result = ((StandardPluginClassLoader)getPluginManager().getPluginClassLoader(element)).findResource(name, requestor, seen);
/* 717 */           if (result != null)
/*     */             break; 
/*     */         } 
/*     */       }  
/* 723 */     return result;
/*     */   }
/*     */   
/*     */   protected void findResources(List<URL> result, String name, StandardPluginClassLoader requestor, Set<String> seenPlugins) throws IOException {
/* 729 */     Set<String> seen = seenPlugins;
/* 730 */     if (seen != null && seen.contains(getPluginDescriptor().getId()))
/*     */       return; 
/* 734 */     Enumeration<URL> enm = super.findResources(name);
/* 735 */     while (enm.hasMoreElements()) {
/* 736 */       URL url = enm.nextElement();
/* 737 */       if (isResourceVisible(name, url, requestor))
/* 738 */         result.add(url); 
/*     */     } 
/* 741 */     if (this.resourceLoader != null) {
/* 742 */       enm = this.resourceLoader.findResources(name);
/* 743 */       while (enm.hasMoreElements()) {
/* 744 */         URL url = enm.nextElement();
/* 745 */         if (isResourceVisible(name, url, requestor))
/* 746 */           result.add(url); 
/*     */       } 
/*     */     } 
/* 750 */     if (seen == null)
/* 751 */       seen = new HashSet<String>(); 
/* 753 */     seen.add(getPluginDescriptor().getId());
/* 754 */     for (PluginDescriptor element : this.publicImports) {
/* 755 */       if (!seen.contains(element.getId()))
/* 758 */         ((StandardPluginClassLoader)getPluginManager().getPluginClassLoader(element)).findResources(result, name, requestor, seen); 
/*     */     } 
/* 762 */     if (this == requestor)
/* 763 */       for (PluginDescriptor element : this.privateImports) {
/* 764 */         if (!seen.contains(element.getId()))
/* 767 */           ((StandardPluginClassLoader)getPluginManager().getPluginClassLoader(element)).findResources(result, name, requestor, seen); 
/*     */       }  
/* 772 */     for (PluginDescriptor element : this.reverseLookups) {
/* 773 */       if (!seen.contains(element.getId()))
/* 776 */         ((StandardPluginClassLoader)getPluginManager().getPluginClassLoader(element)).findResources(result, name, requestor, seen); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isResourceVisible(String name, URL url, StandardPluginClassLoader requestor) {
/*     */     URL lib;
/* 788 */     if (this == requestor)
/* 789 */       return true; 
/*     */     try {
/* 793 */       String file = url.getFile();
/* 794 */       lib = new URL(url.getProtocol(), url.getHost(), file.substring(0, file.length() - name.length()));
/* 796 */     } catch (MalformedURLException mue) {
/* 797 */       log.error("can't get resource library URL", mue);
/* 798 */       return false;
/*     */     } 
/* 800 */     ResourceFilter filter = this.resourceFilters.get(lib.toExternalForm());
/* 801 */     if (filter == null) {
/* 802 */       log.warn("no resource filter found for library " + lib + ", name=" + name + ", URL=" + url + ", this=" + this + ", requestor=" + requestor);
/* 806 */       return false;
/*     */     } 
/* 808 */     if (!filter.isResourceVisible(name)) {
/* 809 */       log.warn("resource not visible, name=" + name + ", URL=" + url + ", this=" + this + ", requestor=" + requestor);
/* 812 */       return false;
/*     */     } 
/* 814 */     return true;
/*     */   }
/*     */   
/*     */   protected static final class ResourceFilter {
/*     */     private boolean isPublic;
/*     */     
/* 823 */     private final Set<String> entries = new HashSet<String>();
/*     */     
/*     */     protected ResourceFilter(Library lib) {
/* 824 */       for (String exportPrefix : lib.getExports()) {
/* 825 */         if ("*".equals(exportPrefix)) {
/* 826 */           this.isPublic = true;
/* 827 */           this.entries.clear();
/*     */           break;
/*     */         } 
/* 830 */         if (!lib.isCodeLibrary()) {
/* 831 */           exportPrefix = exportPrefix.replace('\\', '.').replace('/', '.');
/* 833 */           if (exportPrefix.startsWith("."))
/* 834 */             exportPrefix = exportPrefix.substring(1); 
/*     */         } 
/* 837 */         this.entries.add(exportPrefix);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected boolean isClassVisible(String className) {
/* 842 */       if (this.isPublic)
/* 843 */         return true; 
/* 845 */       if (this.entries.isEmpty())
/* 846 */         return false; 
/* 848 */       if (this.entries.contains(className))
/* 849 */         return true; 
/* 851 */       int p = className.lastIndexOf('.');
/* 852 */       if (p == -1)
/* 853 */         return false; 
/* 855 */       return this.entries.contains(className.substring(0, p) + ".*");
/*     */     }
/*     */     
/*     */     protected boolean isResourceVisible(String resPath) {
/* 860 */       if (this.isPublic)
/* 861 */         return true; 
/* 863 */       if (this.entries.isEmpty())
/* 864 */         return false; 
/* 867 */       String str = resPath.replace('\\', '.').replace('/', '.');
/* 868 */       if (str.startsWith("."))
/* 869 */         str = str.substring(1); 
/* 871 */       if (str.endsWith("."))
/* 872 */         str = str.substring(0, str.length() - 1); 
/* 874 */       return isClassVisible(str);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PluginResourceLoader extends URLClassLoader {
/* 879 */     private static Log logger = LogFactory.getLog(PluginResourceLoader.class);
/*     */     
/*     */     static PluginResourceLoader get(PluginManager manager, PluginDescriptor descr) {
/* 884 */       final List<URL> urls = new LinkedList<URL>();
/* 885 */       for (Library lib : descr.getLibraries()) {
/* 886 */         if (lib.isCodeLibrary())
/*     */           continue; 
/* 889 */         urls.add(manager.getPathResolver().resolvePath((Identity)lib, lib.getPath()));
/*     */       } 
/* 892 */       if (logger.isDebugEnabled()) {
/* 893 */         StringBuilder buf = new StringBuilder();
/* 894 */         buf.append("Resource URL's populated for plug-in " + descr + ":\r\n");
/* 896 */         for (URL url : urls) {
/* 897 */           buf.append("\t");
/* 898 */           buf.append(url);
/* 899 */           buf.append("\r\n");
/*     */         } 
/* 901 */         logger.trace(buf.toString());
/*     */       } 
/* 903 */       if (urls.isEmpty())
/* 904 */         return null; 
/* 910 */       return AccessController.<PluginResourceLoader>doPrivileged(new PrivilegedAction<PluginResourceLoader>() {
/*     */             public StandardPluginClassLoader.PluginResourceLoader run() {
/* 913 */               return new StandardPluginClassLoader.PluginResourceLoader((URL[])urls.toArray((Object[])new URL[urls.size()]));
/*     */             }
/*     */           });
/*     */     }
/*     */     
/*     */     PluginResourceLoader(URL[] urls) {
/* 927 */       super(urls);
/*     */     }
/*     */     
/*     */     protected Class<?> findClass(String name) throws ClassNotFoundException {
/* 936 */       throw new ClassNotFoundException(name);
/*     */     }
/*     */     
/*     */     protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
/* 945 */       throw new ClassNotFoundException(name);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\StandardPluginClassLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */