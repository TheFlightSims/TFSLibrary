/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public final class ConfigImplUtil {
/*     */   static boolean equalsHandlingNull(Object a, Object b) {
/*  23 */     if (a == null && b != null)
/*  24 */       return false; 
/*  25 */     if (a != null && b == null)
/*  26 */       return false; 
/*  27 */     if (a == b)
/*  28 */       return true; 
/*  30 */     return a.equals(b);
/*     */   }
/*     */   
/*     */   public static String renderJsonString(String s) {
/*  38 */     StringBuilder sb = new StringBuilder();
/*  39 */     sb.append('"');
/*  40 */     for (int i = 0; i < s.length(); i++) {
/*  41 */       char c = s.charAt(i);
/*  42 */       switch (c) {
/*     */         case '"':
/*  44 */           sb.append("\\\"");
/*     */           break;
/*     */         case '\\':
/*  47 */           sb.append("\\\\");
/*     */           break;
/*     */         case '\n':
/*  50 */           sb.append("\\n");
/*     */           break;
/*     */         case '\b':
/*  53 */           sb.append("\\b");
/*     */           break;
/*     */         case '\f':
/*  56 */           sb.append("\\f");
/*     */           break;
/*     */         case '\r':
/*  59 */           sb.append("\\r");
/*     */           break;
/*     */         case '\t':
/*  62 */           sb.append("\\t");
/*     */           break;
/*     */         default:
/*  65 */           if (Character.isISOControl(c)) {
/*  66 */             sb.append(String.format("\\u%04x", new Object[] { Integer.valueOf(c) }));
/*     */             break;
/*     */           } 
/*  68 */           sb.append(c);
/*     */           break;
/*     */       } 
/*     */     } 
/*  71 */     sb.append('"');
/*  72 */     return sb.toString();
/*     */   }
/*     */   
/*     */   static String renderStringUnquotedIfPossible(String s) {
/*  78 */     if (s.length() == 0)
/*  79 */       return renderJsonString(s); 
/*  83 */     int first = s.codePointAt(0);
/*  84 */     if (Character.isDigit(first) || first == 45)
/*  85 */       return renderJsonString(s); 
/*  87 */     if (s.startsWith("include") || s.startsWith("true") || s.startsWith("false") || s.startsWith("null") || s.contains("//"))
/*  89 */       return renderJsonString(s); 
/*  92 */     for (int i = 0; i < s.length(); i++) {
/*  93 */       char c = s.charAt(i);
/*  94 */       if (!Character.isLetter(c) && !Character.isDigit(c) && c != '-')
/*  95 */         return renderJsonString(s); 
/*     */     } 
/*  98 */     return s;
/*     */   }
/*     */   
/*     */   static boolean isWhitespace(int codepoint) {
/* 102 */     switch (codepoint) {
/*     */       case 10:
/*     */       case 32:
/*     */       case 160:
/*     */       case 8199:
/*     */       case 8239:
/*     */       case 65279:
/* 114 */         return true;
/*     */     } 
/* 116 */     return Character.isWhitespace(codepoint);
/*     */   }
/*     */   
/*     */   public static String unicodeTrim(String s) {
/* 127 */     int length = s.length();
/* 128 */     if (length == 0)
/* 129 */       return s; 
/* 131 */     int start = 0;
/* 132 */     while (start < length) {
/* 133 */       char c = s.charAt(start);
/* 134 */       if (c == ' ' || c == '\n') {
/* 135 */         start++;
/*     */         continue;
/*     */       } 
/* 137 */       int cp = s.codePointAt(start);
/* 138 */       if (isWhitespace(cp))
/* 139 */         start += Character.charCount(cp); 
/*     */     } 
/* 145 */     int end = length;
/* 146 */     while (end > start) {
/*     */       int cp, delta;
/* 147 */       char c = s.charAt(end - 1);
/* 148 */       if (c == ' ' || c == '\n') {
/* 149 */         end--;
/*     */         continue;
/*     */       } 
/* 153 */       if (Character.isLowSurrogate(c)) {
/* 154 */         cp = s.codePointAt(end - 2);
/* 155 */         delta = 2;
/*     */       } else {
/* 157 */         cp = s.codePointAt(end - 1);
/* 158 */         delta = 1;
/*     */       } 
/* 160 */       if (isWhitespace(cp))
/* 161 */         end -= delta; 
/*     */     } 
/* 166 */     return s.substring(start, end);
/*     */   }
/*     */   
/*     */   public static ConfigException extractInitializerError(ExceptionInInitializerError e) {
/* 171 */     Throwable cause = e.getCause();
/* 172 */     if (cause != null && cause instanceof ConfigException)
/* 173 */       return (ConfigException)cause; 
/* 175 */     throw e;
/*     */   }
/*     */   
/*     */   static File urlToFile(URL url) {
/*     */     try {
/* 183 */       return new File(url.toURI());
/* 184 */     } catch (URISyntaxException e) {
/* 187 */       return new File(url.getPath());
/* 188 */     } catch (IllegalArgumentException e) {
/* 191 */       return new File(url.getPath());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String joinPath(String... elements) {
/* 200 */     return (new Path(elements)).render();
/*     */   }
/*     */   
/*     */   public static String joinPath(List<String> elements) {
/* 208 */     return joinPath(elements.<String>toArray(new String[0]));
/*     */   }
/*     */   
/*     */   public static List<String> splitPath(String path) {
/* 216 */     Path p = Path.newPath(path);
/* 217 */     List<String> elements = new ArrayList<String>();
/* 218 */     while (p != null) {
/* 219 */       elements.add(p.first());
/* 220 */       p = p.remainder();
/*     */     } 
/* 222 */     return elements;
/*     */   }
/*     */   
/*     */   public static ConfigOrigin readOrigin(ObjectInputStream in) throws IOException {
/* 226 */     return SerializedConfigValue.readOrigin(in, null);
/*     */   }
/*     */   
/*     */   public static void writeOrigin(ObjectOutputStream out, ConfigOrigin origin) throws IOException {
/* 230 */     SerializedConfigValue.writeOrigin(new DataOutputStream(out), (SimpleConfigOrigin)origin, null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigImplUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */