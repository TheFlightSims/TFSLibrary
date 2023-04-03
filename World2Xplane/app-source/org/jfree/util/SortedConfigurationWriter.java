/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class SortedConfigurationWriter {
/*     */   private static final int ESCAPE_KEY = 0;
/*     */   
/*     */   private static final int ESCAPE_VALUE = 1;
/*     */   
/*     */   private static final int ESCAPE_COMMENT = 2;
/*     */   
/*  80 */   private static final String END_OF_LINE = StringUtils.getLineSeparator();
/*     */   
/*     */   protected String getDescription(String key) {
/*  98 */     return null;
/*     */   }
/*     */   
/*     */   public void save(String filename, Configuration config) throws IOException {
/* 111 */     save(new File(filename), config);
/*     */   }
/*     */   
/*     */   public void save(File file, Configuration config) throws IOException {
/* 124 */     BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
/* 126 */     save(out, config);
/* 127 */     out.close();
/*     */   }
/*     */   
/*     */   public void save(OutputStream outStream, Configuration config) throws IOException {
/* 140 */     ArrayList names = new ArrayList();
/* 143 */     Iterator defaults = config.findPropertyKeys("");
/* 144 */     while (defaults.hasNext()) {
/* 145 */       String key = defaults.next();
/* 146 */       names.add(key);
/*     */     } 
/* 149 */     Collections.sort(names);
/* 151 */     OutputStreamWriter out = new OutputStreamWriter(outStream, "iso-8859-1");
/* 154 */     for (int i = 0; i < names.size(); i++) {
/* 155 */       String key = names.get(i);
/* 156 */       String value = config.getConfigProperty(key);
/* 158 */       String description = getDescription(key);
/* 159 */       if (description != null)
/* 160 */         writeDescription(description, out); 
/* 162 */       saveConvert(key, 0, out);
/* 163 */       out.write("=");
/* 164 */       saveConvert(value, 1, out);
/* 165 */       out.write(END_OF_LINE);
/*     */     } 
/* 167 */     out.flush();
/*     */   }
/*     */   
/*     */   private void writeDescription(String text, Writer writer) throws IOException {
/* 182 */     if (text.length() == 0)
/*     */       return; 
/* 186 */     writer.write("# ");
/* 187 */     writer.write(END_OF_LINE);
/* 188 */     LineBreakIterator iterator = new LineBreakIterator(text);
/* 189 */     while (iterator.hasNext()) {
/* 190 */       writer.write("# ");
/* 191 */       saveConvert((String)iterator.next(), 2, writer);
/* 192 */       writer.write(END_OF_LINE);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void saveConvert(String text, int escapeMode, Writer writer) throws IOException {
/* 208 */     char[] string = text.toCharArray();
/* 210 */     for (int x = 0; x < string.length; x++) {
/* 211 */       char aChar = string[x];
/* 212 */       switch (aChar) {
/*     */         case ' ':
/* 215 */           if (escapeMode != 2 && (x == 0 || escapeMode == 0))
/* 217 */             writer.write(92); 
/* 219 */           writer.write(32);
/*     */           break;
/*     */         case '\\':
/* 224 */           writer.write(92);
/* 225 */           writer.write(92);
/*     */           break;
/*     */         case '\t':
/* 230 */           if (escapeMode == 2) {
/* 231 */             writer.write(aChar);
/*     */             break;
/*     */           } 
/* 234 */           writer.write(92);
/* 235 */           writer.write(116);
/*     */           break;
/*     */         case '\n':
/* 241 */           writer.write(92);
/* 242 */           writer.write(110);
/*     */           break;
/*     */         case '\r':
/* 247 */           writer.write(92);
/* 248 */           writer.write(114);
/*     */           break;
/*     */         case '\f':
/* 253 */           if (escapeMode == 2) {
/* 254 */             writer.write(aChar);
/*     */             break;
/*     */           } 
/* 257 */           writer.write(92);
/* 258 */           writer.write(102);
/*     */           break;
/*     */         case '!':
/*     */         case '"':
/*     */         case '#':
/*     */         case ':':
/*     */         case '=':
/* 268 */           if (escapeMode == 2) {
/* 269 */             writer.write(aChar);
/*     */             break;
/*     */           } 
/* 272 */           writer.write(92);
/* 273 */           writer.write(aChar);
/*     */           break;
/*     */         default:
/* 278 */           if (aChar < ' ' || aChar > '~') {
/* 279 */             writer.write(92);
/* 280 */             writer.write(117);
/* 281 */             writer.write(HEX_CHARS[aChar >> 12 & 0xF]);
/* 282 */             writer.write(HEX_CHARS[aChar >> 8 & 0xF]);
/* 283 */             writer.write(HEX_CHARS[aChar >> 4 & 0xF]);
/* 284 */             writer.write(HEX_CHARS[aChar & 0xF]);
/*     */             break;
/*     */           } 
/* 287 */           writer.write(aChar);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/* 294 */   private static final char[] HEX_CHARS = new char[] { 
/* 294 */       '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
/* 294 */       'a', 'b', 'c', 'd', 'e', 'f' };
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\SortedConfigurationWriter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */