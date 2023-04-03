/*     */ package org.apache.commons.compress.compressors.pack200;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.jar.JarOutputStream;
/*     */ import java.util.jar.Pack200;
/*     */ 
/*     */ public class Pack200Utils {
/*     */   public static void normalize(File jar) throws IOException {
/*  59 */     normalize(jar, jar, null);
/*     */   }
/*     */   
/*     */   public static void normalize(File jar, Map<String, String> props) throws IOException {
/*  79 */     normalize(jar, jar, props);
/*     */   }
/*     */   
/*     */   public static void normalize(File from, File to) throws IOException {
/* 103 */     normalize(from, to, null);
/*     */   }
/*     */   
/*     */   public static void normalize(File from, File to, Map<String, String> props) throws IOException {
/* 126 */     if (props == null)
/* 127 */       props = new HashMap<String, String>(); 
/* 129 */     props.put("pack.segment.limit", "-1");
/* 130 */     File f = File.createTempFile("commons-compress", "pack200normalize");
/* 131 */     f.deleteOnExit();
/*     */     try {
/* 133 */       OutputStream os = new FileOutputStream(f);
/* 134 */       JarFile j = null;
/*     */       try {
/* 136 */         Pack200.Packer p = Pack200.newPacker();
/* 137 */         p.properties().putAll(props);
/* 138 */         p.pack(j = new JarFile(from), os);
/* 139 */         j = null;
/* 140 */         os.close();
/* 141 */         os = null;
/* 143 */         Pack200.Unpacker u = Pack200.newUnpacker();
/* 144 */         os = new JarOutputStream(new FileOutputStream(to));
/* 145 */         u.unpack(f, (JarOutputStream)os);
/*     */       } finally {
/* 147 */         if (j != null)
/* 148 */           j.close(); 
/* 150 */         if (os != null)
/* 151 */           os.close(); 
/*     */       } 
/*     */     } finally {
/* 155 */       f.delete();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\pack200\Pack200Utils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */