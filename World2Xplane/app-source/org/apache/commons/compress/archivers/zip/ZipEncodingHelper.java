/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.UnsupportedCharsetException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class ZipEncodingHelper {
/*     */   private static class SimpleEncodingHolder {
/*     */     private final char[] highChars;
/*     */     
/*     */     private Simple8BitZipEncoding encoding;
/*     */     
/*     */     SimpleEncodingHolder(char[] highChars) {
/*  52 */       this.highChars = highChars;
/*     */     }
/*     */     
/*     */     public synchronized Simple8BitZipEncoding getEncoding() {
/*  60 */       if (this.encoding == null)
/*  61 */         this.encoding = new Simple8BitZipEncoding(this.highChars); 
/*  63 */       return this.encoding;
/*     */     }
/*     */   }
/*     */   
/*  70 */   private static final Map<String, SimpleEncodingHolder> simpleEncodings = new HashMap<String, SimpleEncodingHolder>();
/*     */   
/*     */   static {
/*  72 */     char[] cp437_high_chars = { 
/*  72 */         'Ç', 'ü', 'é', 'â', 'ä', 'à', 'å', 'ç', 'ê', 'ë', 
/*  72 */         'è', 'ï', 'î', 'ì', 'Ä', 'Å', 'É', 'æ', 'Æ', 'ô', 
/*  72 */         'ö', 'ò', 'û', 'ù', 'ÿ', 'Ö', 'Ü', '¢', '£', '¥', 
/*  72 */         '₧', 'ƒ', 'á', 'í', 'ó', 'ú', 'ñ', 'Ñ', 'ª', 'º', 
/*  72 */         '¿', '⌐', '¬', '½', '¼', '¡', '«', '»', '░', '▒', 
/*  72 */         '▓', '│', '┤', '╡', '╢', '╖', '╕', '╣', '║', '╗', 
/*  72 */         '╝', '╜', '╛', '┐', '└', '┴', '┬', '├', '─', '┼', 
/*  72 */         '╞', '╟', '╚', '╔', '╩', '╦', '╠', '═', '╬', '╧', 
/*  72 */         '╨', '╤', '╥', '╙', '╘', '╒', '╓', '╫', '╪', '┘', 
/*  72 */         '┌', '█', '▄', '▌', '▐', '▀', 'α', 'ß', 'Γ', 'π', 
/*  72 */         'Σ', 'σ', 'µ', 'τ', 'Φ', 'Θ', 'Ω', 'δ', '∞', 'φ', 
/*  72 */         'ε', '∩', '≡', '±', '≥', '≤', '⌠', '⌡', '÷', '≈', 
/*  72 */         '°', '∙', '·', '√', 'ⁿ', '²', '■', ' ' };
/*  96 */     SimpleEncodingHolder cp437 = new SimpleEncodingHolder(cp437_high_chars);
/*  98 */     simpleEncodings.put("CP437", cp437);
/*  99 */     simpleEncodings.put("Cp437", cp437);
/* 100 */     simpleEncodings.put("cp437", cp437);
/* 101 */     simpleEncodings.put("IBM437", cp437);
/* 102 */     simpleEncodings.put("ibm437", cp437);
/* 104 */     char[] cp850_high_chars = { 
/* 104 */         'Ç', 'ü', 'é', 'â', 'ä', 'à', 'å', 'ç', 'ê', 'ë', 
/* 104 */         'è', 'ï', 'î', 'ì', 'Ä', 'Å', 'É', 'æ', 'Æ', 'ô', 
/* 104 */         'ö', 'ò', 'û', 'ù', 'ÿ', 'Ö', 'Ü', 'ø', '£', 'Ø', 
/* 104 */         '×', 'ƒ', 'á', 'í', 'ó', 'ú', 'ñ', 'Ñ', 'ª', 'º', 
/* 104 */         '¿', '®', '¬', '½', '¼', '¡', '«', '»', '░', '▒', 
/* 104 */         '▓', '│', '┤', 'Á', 'Â', 'À', '©', '╣', '║', '╗', 
/* 104 */         '╝', '¢', '¥', '┐', '└', '┴', '┬', '├', '─', '┼', 
/* 104 */         'ã', 'Ã', '╚', '╔', '╩', '╦', '╠', '═', '╬', '¤', 
/* 104 */         'ð', 'Ð', 'Ê', 'Ë', 'È', 'ı', 'Í', 'Î', 'Ï', '┘', 
/* 104 */         '┌', '█', '▄', '¦', 'Ì', '▀', 'Ó', 'ß', 'Ô', 'Ò', 
/* 104 */         'õ', 'Õ', 'µ', 'þ', 'Þ', 'Ú', 'Û', 'Ù', 'ý', 'Ý', 
/* 104 */         '¯', '´', '­', '±', '‗', '¾', '¶', '§', '÷', '¸', 
/* 104 */         '°', '¨', '·', '¹', '³', '²', '■', ' ' };
/* 128 */     SimpleEncodingHolder cp850 = new SimpleEncodingHolder(cp850_high_chars);
/* 130 */     simpleEncodings.put("CP850", cp850);
/* 131 */     simpleEncodings.put("Cp850", cp850);
/* 132 */     simpleEncodings.put("cp850", cp850);
/* 133 */     simpleEncodings.put("IBM850", cp850);
/* 134 */     simpleEncodings.put("ibm850", cp850);
/*     */   }
/*     */   
/*     */   static ByteBuffer growBuffer(ByteBuffer b, int newCapacity) {
/* 150 */     b.limit(b.position());
/* 151 */     b.rewind();
/* 153 */     int c2 = b.capacity() * 2;
/* 154 */     ByteBuffer on = ByteBuffer.allocate((c2 < newCapacity) ? newCapacity : c2);
/* 156 */     on.put(b);
/* 157 */     return on;
/*     */   }
/*     */   
/* 165 */   private static final byte[] HEX_DIGITS = new byte[] { 
/* 165 */       48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
/* 165 */       65, 66, 67, 68, 69, 70 };
/*     */   
/*     */   static final String UTF8 = "UTF8";
/*     */   
/*     */   private static final String UTF_DASH_8 = "UTF-8";
/*     */   
/*     */   static void appendSurrogate(ByteBuffer bb, char c) {
/* 180 */     bb.put((byte)37);
/* 181 */     bb.put((byte)85);
/* 183 */     bb.put(HEX_DIGITS[c >> 12 & 0xF]);
/* 184 */     bb.put(HEX_DIGITS[c >> 8 & 0xF]);
/* 185 */     bb.put(HEX_DIGITS[c >> 4 & 0xF]);
/* 186 */     bb.put(HEX_DIGITS[c & 0xF]);
/*     */   }
/*     */   
/* 203 */   static final ZipEncoding UTF8_ZIP_ENCODING = new FallbackZipEncoding("UTF8");
/*     */   
/*     */   public static ZipEncoding getZipEncoding(String name) {
/* 215 */     if (isUTF8(name))
/* 216 */       return UTF8_ZIP_ENCODING; 
/* 219 */     if (name == null)
/* 220 */       return new FallbackZipEncoding(); 
/* 223 */     SimpleEncodingHolder h = simpleEncodings.get(name);
/* 225 */     if (h != null)
/* 226 */       return h.getEncoding(); 
/*     */     try {
/* 231 */       Charset cs = Charset.forName(name);
/* 232 */       return new NioZipEncoding(cs);
/* 234 */     } catch (UnsupportedCharsetException e) {
/* 235 */       return new FallbackZipEncoding(name);
/*     */     } 
/*     */   }
/*     */   
/*     */   static boolean isUTF8(String encoding) {
/* 244 */     if (encoding == null)
/* 246 */       encoding = System.getProperty("file.encoding"); 
/* 248 */     return ("UTF8".equalsIgnoreCase(encoding) || "UTF-8".equalsIgnoreCase(encoding));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\zip\ZipEncodingHelper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */