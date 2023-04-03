/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public final class Service {
/*     */   private static final String prefix = "META-INF/services/";
/*     */   
/*     */   private static void fail(Class service, String msg) throws ServiceConfigurationError {
/* 126 */     throw new ServiceConfigurationError(service.getName() + ": " + msg);
/*     */   }
/*     */   
/*     */   private static void fail(Class service, URL u, int line, String msg) throws ServiceConfigurationError {
/* 132 */     fail(service, u + ":" + line + ": " + msg);
/*     */   }
/*     */   
/*     */   private static int parseLine(Class service, URL u, BufferedReader r, int lc, List names, Set returned) throws IOException, ServiceConfigurationError {
/* 144 */     String ln = r.readLine();
/* 145 */     if (ln == null)
/* 146 */       return -1; 
/* 148 */     int ci = ln.indexOf('#');
/* 149 */     if (ci >= 0)
/* 149 */       ln = ln.substring(0, ci); 
/* 150 */     ln = ln.trim();
/* 151 */     int n = ln.length();
/* 152 */     if (n != 0) {
/* 153 */       if (ln.indexOf(' ') >= 0 || ln.indexOf('\t') >= 0)
/* 154 */         fail(service, u, lc, "Illegal configuration-file syntax"); 
/* 155 */       if (!Character.isJavaIdentifierStart(ln.charAt(0)))
/* 156 */         fail(service, u, lc, "Illegal provider-class name: " + ln); 
/* 157 */       for (int i = 1; i < n; i++) {
/* 158 */         char c = ln.charAt(i);
/* 159 */         if (!Character.isJavaIdentifierPart(c) && c != '.')
/* 160 */           fail(service, u, lc, "Illegal provider-class name: " + ln); 
/*     */       } 
/* 162 */       if (!returned.contains(ln)) {
/* 163 */         names.add(ln);
/* 164 */         returned.add(ln);
/*     */       } 
/*     */     } 
/* 167 */     return lc + 1;
/*     */   }
/*     */   
/*     */   private static Iterator parse(Class service, URL u, Set returned) throws ServiceConfigurationError {
/* 196 */     InputStream in = null;
/* 197 */     BufferedReader r = null;
/* 198 */     ArrayList names = new ArrayList();
/*     */     try {
/* 200 */       in = u.openStream();
/* 201 */       r = new BufferedReader(new InputStreamReader(in, "utf-8"));
/* 202 */       int lc = 1;
/* 203 */       while ((lc = parseLine(service, u, r, lc, names, returned)) >= 0);
/* 204 */     } catch (IOException x) {
/* 205 */       fail(service, ": " + x);
/*     */     } finally {
/*     */       try {
/* 208 */         if (r != null)
/* 208 */           r.close(); 
/* 209 */         if (in != null)
/* 209 */           in.close(); 
/* 210 */       } catch (IOException y) {
/* 211 */         fail(service, ": " + y);
/*     */       } 
/*     */     } 
/* 214 */     return names.iterator();
/*     */   }
/*     */   
/*     */   private static class LazyIterator implements Iterator {
/*     */     Class service;
/*     */     
/*     */     ClassLoader loader;
/*     */     
/* 225 */     Enumeration configs = null;
/*     */     
/* 226 */     Iterator pending = null;
/*     */     
/* 227 */     Set returned = new TreeSet();
/*     */     
/* 228 */     String nextName = null;
/*     */     
/*     */     private LazyIterator(Class service, ClassLoader loader) {
/* 231 */       this.service = service;
/* 232 */       this.loader = loader;
/*     */     }
/*     */     
/*     */     public boolean hasNext() throws ServiceConfigurationError {
/* 236 */       if (this.nextName != null)
/* 237 */         return true; 
/* 239 */       if (this.configs == null)
/*     */         try {
/* 241 */           String fullName = "META-INF/services/" + this.service.getName();
/* 242 */           if (this.loader == null) {
/* 243 */             this.configs = ClassLoader.getSystemResources(fullName);
/*     */           } else {
/* 245 */             this.configs = this.loader.getResources(fullName);
/*     */           } 
/* 246 */         } catch (IOException x) {
/* 247 */           Service.fail(this.service, ": " + x);
/*     */         }  
/* 250 */       while (this.pending == null || !this.pending.hasNext()) {
/* 251 */         if (!this.configs.hasMoreElements())
/* 252 */           return false; 
/* 254 */         this.pending = Service.parse(this.service, this.configs.nextElement(), this.returned);
/*     */       } 
/* 256 */       this.nextName = this.pending.next();
/* 257 */       return true;
/*     */     }
/*     */     
/*     */     public Object next() throws ServiceConfigurationError {
/* 261 */       if (!hasNext())
/* 262 */         throw new NoSuchElementException(); 
/* 264 */       String cn = this.nextName;
/* 265 */       this.nextName = null;
/*     */       try {
/* 267 */         return Class.forName(cn, true, this.loader).newInstance();
/* 268 */       } catch (ClassNotFoundException x) {
/* 269 */         Service.fail(this.service, "Provider " + cn + " not found");
/* 271 */       } catch (Exception x) {
/* 272 */         Service.fail(this.service, "Provider " + cn + " could not be instantiated: " + x);
/*     */       } 
/* 275 */       return null;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 279 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Iterator providers(Class service, ClassLoader loader) throws ServiceConfigurationError {
/* 326 */     return new LazyIterator(service, loader);
/*     */   }
/*     */   
/*     */   public static Iterator providers(Class service) throws ServiceConfigurationError {
/* 358 */     ClassLoader cl = Thread.currentThread().getContextClassLoader();
/* 359 */     return providers(service, cl);
/*     */   }
/*     */   
/*     */   public static Iterator installedProviders(Class service) throws ServiceConfigurationError {
/* 395 */     ClassLoader cl = ClassLoader.getSystemClassLoader();
/* 396 */     if (cl != null)
/* 396 */       cl = cl.getParent(); 
/* 397 */     return providers(service, cl);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\Service.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */