/*     */ package org.java.plugin;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.net.URLStreamHandlerFactory;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ 
/*     */ public abstract class PluginClassLoader extends URLClassLoader {
/*     */   private final PluginManager manager;
/*     */   
/*     */   private final PluginDescriptor descriptor;
/*     */   
/*     */   protected PluginClassLoader(PluginManager aManager, PluginDescriptor descr, URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
/*  50 */     super(urls, parent, factory);
/*  51 */     this.manager = aManager;
/*  52 */     this.descriptor = descr;
/*     */   }
/*     */   
/*     */   protected PluginClassLoader(PluginManager aManager, PluginDescriptor descr, URL[] urls, ClassLoader parent) {
/*  65 */     super(urls, parent);
/*  66 */     this.manager = aManager;
/*  67 */     this.descriptor = descr;
/*     */   }
/*     */   
/*     */   protected PluginClassLoader(PluginManager aManager, PluginDescriptor descr, URL[] urls) {
/*  78 */     super(urls);
/*  79 */     this.manager = aManager;
/*  80 */     this.descriptor = descr;
/*     */   }
/*     */   
/*     */   public PluginManager getPluginManager() {
/*  87 */     return this.manager;
/*     */   }
/*     */   
/*     */   public PluginDescriptor getPluginDescriptor() {
/*  94 */     return this.descriptor;
/*     */   }
/*     */   
/*     */   protected abstract void dispose();
/*     */   
/*     */   protected abstract void pluginsSetChanged();
/*     */   
/*     */   public String toString() {
/* 112 */     return "{PluginClassLoader: uid=" + System.identityHashCode(this) + "; " + this.descriptor + "}";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\PluginClassLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */