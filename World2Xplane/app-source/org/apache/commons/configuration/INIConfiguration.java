/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class INIConfiguration extends AbstractFileConfiguration {
/*     */   protected static final String COMMENT_CHARS = "#;";
/*     */   
/*     */   protected static final String SEPARATOR_CHARS = "=:";
/*     */   
/*     */   public INIConfiguration() {}
/*     */   
/*     */   public INIConfiguration(String filename) throws ConfigurationException {
/* 200 */     super(filename);
/*     */   }
/*     */   
/*     */   public INIConfiguration(File file) throws ConfigurationException {
/* 211 */     super(file);
/*     */   }
/*     */   
/*     */   public INIConfiguration(URL url) throws ConfigurationException {
/* 222 */     super(url);
/*     */   }
/*     */   
/*     */   public void save(Writer writer) throws ConfigurationException {
/* 234 */     PrintWriter out = new PrintWriter(writer);
/* 235 */     Iterator it = getSections().iterator();
/* 236 */     while (it.hasNext()) {
/* 238 */       String section = it.next();
/* 239 */       out.print("[");
/* 240 */       out.print(section);
/* 241 */       out.print("]");
/* 242 */       out.println();
/* 244 */       Configuration subset = subset(section);
/* 245 */       Iterator keys = subset.getKeys();
/* 246 */       while (keys.hasNext()) {
/* 248 */         String key = keys.next();
/* 249 */         Object value = subset.getProperty(key);
/* 250 */         if (value instanceof Collection) {
/* 252 */           Iterator values = ((Collection)value).iterator();
/* 253 */           while (values.hasNext()) {
/* 255 */             value = values.next();
/* 256 */             out.print(key);
/* 257 */             out.print(" = ");
/* 258 */             out.print(formatValue(value.toString()));
/* 259 */             out.println();
/*     */           } 
/*     */           continue;
/*     */         } 
/* 264 */         out.print(key);
/* 265 */         out.print(" = ");
/* 266 */         out.print(formatValue(value.toString()));
/* 267 */         out.println();
/*     */       } 
/* 271 */       out.println();
/*     */     } 
/* 274 */     out.flush();
/*     */   }
/*     */   
/*     */   public void load(Reader reader) throws ConfigurationException {
/*     */     try {
/* 290 */       BufferedReader bufferedReader = new BufferedReader(reader);
/* 291 */       String line = bufferedReader.readLine();
/* 292 */       String section = "";
/* 293 */       while (line != null) {
/* 295 */         line = line.trim();
/* 296 */         if (!isCommentLine(line))
/* 298 */           if (isSectionLine(line)) {
/* 300 */             section = line.substring(1, line.length() - 1) + ".";
/*     */           } else {
/* 304 */             String key = "";
/* 305 */             String value = "";
/* 306 */             int index = line.indexOf("=");
/* 307 */             if (index >= 0) {
/* 309 */               key = section + line.substring(0, index);
/* 310 */               value = parseValue(line.substring(index + 1));
/*     */             } else {
/* 314 */               index = line.indexOf(":");
/* 315 */               if (index >= 0) {
/* 317 */                 key = section + line.substring(0, index);
/* 318 */                 value = parseValue(line.substring(index + 1));
/*     */               } else {
/* 322 */                 key = section + line;
/*     */               } 
/*     */             } 
/* 325 */             addProperty(key.trim(), value);
/*     */           }  
/* 328 */         line = bufferedReader.readLine();
/*     */       } 
/* 331 */     } catch (IOException e) {
/* 333 */       throw new ConfigurationException("Unable to load the configuration", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String parseValue(String value) {
/* 349 */     value = value.trim();
/* 351 */     boolean quoted = (value.startsWith("\"") || value.startsWith("'"));
/* 352 */     boolean stop = false;
/* 353 */     boolean escape = false;
/* 355 */     char quote = quoted ? value.charAt(0) : Character.MIN_VALUE;
/* 357 */     int i = quoted ? 1 : 0;
/* 359 */     StringBuffer result = new StringBuffer();
/* 360 */     while (i < value.length() && !stop) {
/* 362 */       char c = value.charAt(i);
/* 364 */       if (quoted) {
/* 366 */         if ('\\' == c && !escape) {
/* 368 */           escape = true;
/* 370 */         } else if (!escape && quote == c) {
/* 372 */           stop = true;
/* 374 */         } else if (escape && quote == c) {
/* 376 */           escape = false;
/* 377 */           result.append(c);
/*     */         } else {
/* 381 */           if (escape) {
/* 383 */             escape = false;
/* 384 */             result.append('\\');
/*     */           } 
/* 387 */           result.append(c);
/*     */         } 
/* 392 */       } else if ("#;".indexOf(c) == -1) {
/* 394 */         result.append(c);
/*     */       } else {
/* 398 */         stop = true;
/*     */       } 
/* 402 */       i++;
/*     */     } 
/* 405 */     String v = result.toString();
/* 406 */     if (!quoted)
/* 408 */       v = v.trim(); 
/* 410 */     return v;
/*     */   }
/*     */   
/*     */   private String formatValue(String value) {
/* 418 */     boolean quoted = false;
/* 420 */     for (int i = 0; i < "#;".length() && !quoted; i++) {
/* 422 */       char c = "#;".charAt(i);
/* 423 */       if (value.indexOf(c) != -1)
/* 425 */         quoted = true; 
/*     */     } 
/* 429 */     if (quoted)
/* 431 */       return '"' + StringUtils.replace(value, "\"", "\\\"") + '"'; 
/* 435 */     return value;
/*     */   }
/*     */   
/*     */   protected boolean isCommentLine(String line) {
/* 448 */     if (line == null)
/* 450 */       return false; 
/* 453 */     return (line.length() < 1 || "#;".indexOf(line.charAt(0)) >= 0);
/*     */   }
/*     */   
/*     */   protected boolean isSectionLine(String line) {
/* 464 */     if (line == null)
/* 466 */       return false; 
/* 468 */     return (line.startsWith("[") && line.endsWith("]"));
/*     */   }
/*     */   
/*     */   public Set getSections() {
/* 479 */     Set sections = new TreeSet();
/* 481 */     Iterator keys = getKeys();
/* 482 */     while (keys.hasNext()) {
/* 484 */       String key = keys.next();
/* 485 */       int index = key.indexOf(".");
/* 486 */       if (index >= 0)
/* 488 */         sections.add(key.substring(0, index)); 
/*     */     } 
/* 492 */     return sections;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\INIConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */