/*     */ package org.java.plugin;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.registry.PluginRegistry;
/*     */ import org.java.plugin.standard.StandardObjectFactory;
/*     */ import org.java.plugin.util.ExtendedProperties;
/*     */ import org.java.plugin.util.IoUtil;
/*     */ 
/*     */ public abstract class ObjectFactory {
/*     */   public static ObjectFactory newInstance() {
/*  51 */     return newInstance(null);
/*     */   }
/*     */   
/*     */   public static ObjectFactory newInstance(ExtendedProperties config) {
/*     */     ExtendedProperties props;
/*     */     ObjectFactory result;
/*  80 */     Log log = LogFactory.getLog(ObjectFactory.class);
/*  81 */     ClassLoader cl = Thread.currentThread().getContextClassLoader();
/*  82 */     if (cl == null)
/*  83 */       cl = ObjectFactory.class.getClassLoader(); 
/*  86 */     if (config != null) {
/*  87 */       props = config;
/*     */     } else {
/*  89 */       props = loadProperties(cl);
/*     */     } 
/*  91 */     String className = findProperty(cl, props);
/*     */     try {
/*  94 */       if (className == null)
/*  95 */         className = "org.java.plugin.standard.StandardObjectFactory"; 
/*  97 */       result = (ObjectFactory)loadClass(cl, className).newInstance();
/*  98 */     } catch (ClassNotFoundException cnfe) {
/*  99 */       log.fatal("failed instantiating object factory " + className, cnfe);
/* 101 */       throw new Error("failed instantiating object factory " + className, cnfe);
/* 103 */     } catch (IllegalAccessException iae) {
/* 104 */       log.fatal("failed instantiating object factory " + className, iae);
/* 106 */       throw new Error("failed instantiating object factory " + className, iae);
/* 108 */     } catch (SecurityException se) {
/* 109 */       log.fatal("failed instantiating object factory " + className, se);
/* 111 */       throw new Error("failed instantiating object factory " + className, se);
/* 113 */     } catch (InstantiationException ie) {
/* 114 */       log.fatal("failed instantiating object factory " + className, ie);
/* 116 */       throw new Error("failed instantiating object factory " + className, ie);
/*     */     } 
/* 119 */     result.configure(props);
/* 120 */     log.debug("object factory instance created - " + result);
/* 121 */     return result;
/*     */   }
/*     */   
/*     */   private static Class<?> loadClass(ClassLoader cl, String className) throws ClassNotFoundException {
/* 126 */     if (cl != null)
/*     */       try {
/* 128 */         return cl.loadClass(className);
/* 129 */       } catch (ClassNotFoundException cnfe) {} 
/* 133 */     ClassLoader cl2 = ObjectFactory.class.getClassLoader();
/* 134 */     if (cl2 != null)
/*     */       try {
/* 136 */         return cl2.loadClass(className);
/* 137 */       } catch (ClassNotFoundException cnfe) {} 
/* 141 */     return ClassLoader.getSystemClassLoader().loadClass(className);
/*     */   }
/*     */   
/*     */   private static ExtendedProperties loadProperties(ClassLoader cl) {
/* 145 */     Log log = LogFactory.getLog(ObjectFactory.class);
/* 146 */     File file = new File(System.getProperty("java.home") + File.separator + "lib" + File.separator + "jpf.properties");
/* 149 */     URL url = null;
/* 150 */     if (file.canRead())
/*     */       try {
/* 152 */         url = IoUtil.file2url(file);
/* 153 */       } catch (MalformedURLException mue) {
/* 154 */         log.error("failed converting file " + file + " to URL", mue);
/*     */       }  
/* 158 */     if (url == null) {
/* 159 */       if (cl != null) {
/* 160 */         url = cl.getResource("jpf.properties");
/* 161 */         if (url == null)
/* 162 */           url = ClassLoader.getSystemResource("jpf.properties"); 
/*     */       } else {
/* 165 */         url = ClassLoader.getSystemResource("jpf.properties");
/*     */       } 
/* 167 */       if (url == null) {
/* 168 */         log.debug("no jpf.properties file found in ${java.home}/lib (" + file + ") nor in CLASSPATH, using standard properties");
/* 171 */         url = StandardObjectFactory.class.getResource("jpf.properties");
/*     */       } 
/*     */     } 
/*     */     try {
/* 175 */       InputStream strm = IoUtil.getResourceInputStream(url);
/*     */       try {
/* 177 */         ExtendedProperties props = new ExtendedProperties();
/* 178 */         props.load(strm);
/* 179 */         log.debug("loaded jpf.properties from " + url);
/* 180 */         return props;
/*     */       } finally {
/*     */         try {
/* 183 */           strm.close();
/* 184 */         } catch (IOException ioe) {}
/*     */       } 
/* 188 */     } catch (Exception e) {
/* 189 */       log.error("failed loading jpf.properties from CLASSPATH", e);
/* 192 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String findProperty(ClassLoader cl, ExtendedProperties props) {
/*     */     InputStream strm;
/* 197 */     Log log = LogFactory.getLog(ObjectFactory.class);
/* 198 */     String name = ObjectFactory.class.getName();
/* 199 */     String result = System.getProperty(name);
/* 200 */     if (result != null) {
/* 201 */       log.debug("property " + name + " found as system property");
/* 203 */       return result;
/*     */     } 
/* 205 */     if (props != null) {
/* 206 */       result = props.getProperty(name);
/* 207 */       if (result != null) {
/* 208 */         log.debug("property " + name + " found in properties file");
/* 210 */         return result;
/*     */       } 
/*     */     } 
/* 213 */     String serviceId = "META-INF/services/" + ObjectFactory.class.getName();
/* 216 */     if (cl == null) {
/* 217 */       strm = ClassLoader.getSystemResourceAsStream(serviceId);
/*     */     } else {
/* 219 */       strm = cl.getResourceAsStream(serviceId);
/*     */     } 
/* 221 */     if (strm != null)
/*     */       try {
/* 223 */         BufferedReader reader = new BufferedReader(new InputStreamReader(strm, "UTF-8"));
/*     */         try {
/* 226 */           result = reader.readLine();
/*     */         } finally {
/*     */           try {
/* 229 */             reader.close();
/* 230 */           } catch (IOException ioe) {}
/*     */         } 
/* 234 */       } catch (IOException ioe) {
/*     */         try {
/* 236 */           strm.close();
/* 237 */         } catch (IOException ioe2) {}
/*     */       }  
/* 242 */     if (result != null) {
/* 243 */       log.debug("property " + name + " found as service");
/* 245 */       return result;
/*     */     } 
/* 247 */     log.debug("no property " + name + " found");
/* 249 */     return result;
/*     */   }
/*     */   
/*     */   protected abstract void configure(ExtendedProperties paramExtendedProperties);
/*     */   
/*     */   public final PluginManager createManager() {
/* 271 */     return createManager(createRegistry(), createPathResolver());
/*     */   }
/*     */   
/*     */   public abstract PluginManager createManager(PluginRegistry paramPluginRegistry, PathResolver paramPathResolver);
/*     */   
/*     */   public abstract PluginRegistry createRegistry();
/*     */   
/*     */   public abstract PathResolver createPathResolver();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\ObjectFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */