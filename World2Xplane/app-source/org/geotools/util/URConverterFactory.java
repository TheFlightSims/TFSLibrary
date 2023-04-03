/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import org.geotools.factory.Hints;
/*     */ 
/*     */ public class URConverterFactory implements ConverterFactory {
/*  48 */   public static final Converter StringToURL = new Converter() {
/*     */       public <T> T convert(Object source, Class<T> target) throws Exception {
/*  50 */         String s = (String)source;
/*     */         try {
/*  52 */           return (T)new URL(s);
/*  54 */         } catch (MalformedURLException e1) {
/*  55 */           File f = new File(s);
/*     */           try {
/*  57 */             return (T)f.toURI().toURL();
/*  59 */           } catch (MalformedURLException e2) {
/*  62 */             return null;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     };
/*     */   
/*  67 */   public static final Converter StringToURI = new Converter() {
/*     */       public <T> T convert(Object source, Class<T> target) throws Exception {
/*  70 */         String s = (String)source;
/*     */         try {
/*  72 */           return (T)new URI(s);
/*  74 */         } catch (URISyntaxException e1) {
/*  75 */           File f = new File(s);
/*     */           try {
/*  77 */             return (T)f.toURI();
/*  79 */           } catch (Exception e2) {
/*  82 */             return null;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     };
/*     */   
/*  87 */   public static final Converter URLToURI = new Converter() {
/*     */       public <T> T convert(Object source, Class<T> target) throws Exception {
/*  90 */         URL url = (URL)source;
/*  91 */         return (T)url.toURI();
/*     */       }
/*     */     };
/*     */   
/*  95 */   public static final Converter URIToURL = new Converter() {
/*     */       public <T> T convert(Object source, Class<T> target) throws Exception {
/*  98 */         URI uri = (URI)source;
/*  99 */         return (T)uri.toURL();
/*     */       }
/*     */     };
/*     */   
/*     */   public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
/* 105 */     if (String.class.equals(source)) {
/* 106 */       if (URL.class.equals(target))
/* 107 */         return StringToURL; 
/* 109 */       if (URI.class.equals(target))
/* 110 */         return StringToURI; 
/*     */     } 
/* 114 */     if (URL.class.equals(source) && URI.class.equals(target))
/* 115 */       return URLToURI; 
/* 117 */     if (URI.class.equals(source) && URL.class.equals(target))
/* 118 */       return URIToURL; 
/* 121 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\URConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */