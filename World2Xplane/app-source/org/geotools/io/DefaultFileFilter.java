/*     */ package org.geotools.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.FilenameFilter;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.swing.filechooser.FileFilter;
/*     */ 
/*     */ public class DefaultFileFilter extends FileFilter implements FileFilter, FilenameFilter {
/*     */   private final String description;
/*     */   
/*     */   private final Pattern pattern;
/*     */   
/*     */   public DefaultFileFilter(String pattern) {
/*  56 */     this(pattern, (new File(pattern)).getName());
/*     */   }
/*     */   
/*     */   public DefaultFileFilter(String pattern, String description) {
/*  67 */     this.description = description.trim();
/*  68 */     int length = pattern.length();
/*  69 */     StringBuilder buffer = new StringBuilder(length + 8);
/*  70 */     int i = 0;
/*     */     while (true) {
/*     */       char c;
/*  70 */       if (i < length) {
/*  71 */         c = pattern.charAt(i);
/*  72 */         if (!Character.isLetterOrDigit(c)) {
/*  73 */           switch (c) {
/*     */             case '?':
/*  74 */               buffer.append('.');
/*     */               break;
/*     */             case '*':
/*  75 */               buffer.append(".*");
/*     */               break;
/*     */             default:
/*  76 */               buffer.append('\\');
/*  79 */               buffer.append(c);
/*     */               break;
/*     */           } 
/*     */           continue;
/*     */         } 
/*     */       } else {
/*     */         break;
/*     */       } 
/*  79 */       buffer.append(c);
/*     */       break;
/*     */       i++;
/*     */     } 
/*  81 */     this.pattern = Pattern.compile(buffer.toString());
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  88 */     return this.description;
/*     */   }
/*     */   
/*     */   public boolean accept(File file) {
/*  98 */     return (file != null && this.pattern.matcher(file.getName()).matches());
/*     */   }
/*     */   
/*     */   public boolean accept(File directory, String name) {
/* 109 */     return (name != null && this.pattern.matcher(name).matches());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\io\DefaultFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */