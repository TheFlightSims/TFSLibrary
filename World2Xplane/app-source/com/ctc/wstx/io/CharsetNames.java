/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.util.StringUtil;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ 
/*     */ public final class CharsetNames {
/*     */   public static final String CS_US_ASCII = "US-ASCII";
/*     */   
/*     */   public static final String CS_UTF8 = "UTF-8";
/*     */   
/*     */   public static final String CS_UTF16 = "UTF-16";
/*     */   
/*     */   public static final String CS_UTF16BE = "UTF-16BE";
/*     */   
/*     */   public static final String CS_UTF16LE = "UTF-16LE";
/*     */   
/*     */   public static final String CS_UTF32 = "UTF-32";
/*     */   
/*     */   public static final String CS_UTF32BE = "UTF-32BE";
/*     */   
/*     */   public static final String CS_UTF32LE = "UTF-32LE";
/*     */   
/*     */   public static final String CS_ISO_LATIN1 = "ISO-8859-1";
/*     */   
/*     */   public static final String CS_SHIFT_JIS = "Shift_JIS";
/*     */   
/*     */   public static final String CS_EBCDIC_SUBSET = "IBM037";
/*     */   
/*     */   public static String normalize(String csName) {
/*  64 */     if (csName == null || csName.length() < 3)
/*  65 */       return csName; 
/*  80 */     boolean gotCsPrefix = false;
/*  81 */     char c = csName.charAt(0);
/*  82 */     if (c == 'c' || c == 'C') {
/*  83 */       char d = csName.charAt(1);
/*  84 */       if (d == 's' || d == 'S') {
/*  85 */         csName = csName.substring(2);
/*  86 */         c = csName.charAt(0);
/*  87 */         gotCsPrefix = true;
/*     */       } 
/*     */     } 
/*  91 */     switch (c) {
/*     */       case 'A':
/*     */       case 'a':
/*  94 */         if (StringUtil.equalEncodings(csName, "ASCII"))
/*  95 */           return "US-ASCII"; 
/*     */         break;
/*     */       case 'C':
/*     */       case 'c':
/* 104 */         if (StringUtil.encodingStartsWith(csName, "cp"))
/* 105 */           return "IBM" + StringUtil.trimEncoding(csName, true).substring(2); 
/* 108 */         if (StringUtil.encodingStartsWith(csName, "cs"))
/* 110 */           if (StringUtil.encodingStartsWith(csName, "csIBM"))
/* 112 */             return StringUtil.trimEncoding(csName, true).substring(2);  
/*     */         break;
/*     */       case 'E':
/*     */       case 'e':
/* 120 */         if (csName.startsWith("EBCDIC-CP-") || csName.startsWith("ebcdic-cp-")) {
/* 124 */           String type = StringUtil.trimEncoding(csName, true).substring(8);
/* 126 */           if (type.equals("US") || type.equals("CA") || type.equals("WT") || type.equals("NL"))
/* 128 */             return "IBM037"; 
/* 130 */           if (type.equals("DK") || type.equals("NO"))
/* 131 */             return "IBM277"; 
/* 133 */           if (type.equals("FI") || type.equals("SE"))
/* 134 */             return "IBM278"; 
/* 136 */           if (type.equals("ROECE") || type.equals("YU"))
/* 137 */             return "IBM870"; 
/* 139 */           if (type.equals("IT"))
/* 139 */             return "IBM280"; 
/* 140 */           if (type.equals("ES"))
/* 140 */             return "IBM284"; 
/* 141 */           if (type.equals("GB"))
/* 141 */             return "IBM285"; 
/* 142 */           if (type.equals("FR"))
/* 142 */             return "IBM297"; 
/* 143 */           if (type.equals("AR1"))
/* 143 */             return "IBM420"; 
/* 144 */           if (type.equals("AR2"))
/* 144 */             return "IBM918"; 
/* 145 */           if (type.equals("HE"))
/* 145 */             return "IBM424"; 
/* 146 */           if (type.equals("CH"))
/* 146 */             return "IBM500"; 
/* 147 */           if (type.equals("IS"))
/* 147 */             return "IBM871"; 
/* 150 */           return "IBM037";
/*     */         } 
/*     */         break;
/*     */       case 'I':
/*     */       case 'i':
/* 155 */         if (StringUtil.equalEncodings(csName, "ISO-8859-1") || StringUtil.equalEncodings(csName, "ISO-Latin1"))
/* 157 */           return "ISO-8859-1"; 
/* 159 */         if (StringUtil.encodingStartsWith(csName, "ISO-10646")) {
/* 164 */           int ix = csName.indexOf("10646");
/* 165 */           String suffix = csName.substring(ix + 5);
/* 166 */           if (StringUtil.equalEncodings(suffix, "UCS-Basic"))
/* 167 */             return "US-ASCII"; 
/* 169 */           if (StringUtil.equalEncodings(suffix, "Unicode-Latin1"))
/* 170 */             return "ISO-8859-1"; 
/* 172 */           if (StringUtil.equalEncodings(suffix, "UCS-2"))
/* 173 */             return "UTF-16"; 
/* 175 */           if (StringUtil.equalEncodings(suffix, "UCS-4"))
/* 176 */             return "UTF-32"; 
/* 178 */           if (StringUtil.equalEncodings(suffix, "UTF-1"))
/* 180 */             return "US-ASCII"; 
/* 182 */           if (StringUtil.equalEncodings(suffix, "J-1"))
/* 185 */             return "US-ASCII"; 
/* 187 */           if (StringUtil.equalEncodings(suffix, "US-ASCII"))
/* 188 */             return "US-ASCII"; 
/*     */           break;
/*     */         } 
/* 190 */         if (StringUtil.encodingStartsWith(csName, "IBM"))
/* 193 */           return csName; 
/*     */         break;
/*     */       case 'J':
/*     */       case 'j':
/* 198 */         if (StringUtil.equalEncodings(csName, "JIS_Encoding"))
/* 199 */           return "Shift_JIS"; 
/*     */         break;
/*     */       case 'S':
/*     */       case 's':
/* 204 */         if (StringUtil.equalEncodings(csName, "Shift_JIS"))
/* 205 */           return "Shift_JIS"; 
/*     */         break;
/*     */       case 'U':
/*     */       case 'u':
/* 210 */         if (csName.length() < 2)
/*     */           break; 
/* 213 */         switch (csName.charAt(1)) {
/*     */           case 'C':
/*     */           case 'c':
/* 216 */             if (StringUtil.equalEncodings(csName, "UCS-2"))
/* 217 */               return "UTF-16"; 
/* 219 */             if (StringUtil.equalEncodings(csName, "UCS-4"))
/* 220 */               return "UTF-32"; 
/*     */             break;
/*     */           case 'N':
/*     */           case 'n':
/* 225 */             if (gotCsPrefix) {
/* 226 */               if (StringUtil.equalEncodings(csName, "Unicode"))
/* 227 */                 return "UTF-16"; 
/* 229 */               if (StringUtil.equalEncodings(csName, "UnicodeAscii"))
/* 230 */                 return "ISO-8859-1"; 
/* 232 */               if (StringUtil.equalEncodings(csName, "UnicodeAscii"))
/* 233 */                 return "US-ASCII"; 
/*     */             } 
/*     */             break;
/*     */           case 'S':
/*     */           case 's':
/* 239 */             if (StringUtil.equalEncodings(csName, "US-ASCII"))
/* 240 */               return "US-ASCII"; 
/*     */             break;
/*     */           case 'T':
/*     */           case 't':
/* 245 */             if (StringUtil.equalEncodings(csName, "UTF-8"))
/* 246 */               return "UTF-8"; 
/* 248 */             if (StringUtil.equalEncodings(csName, "UTF-16BE"))
/* 249 */               return "UTF-16BE"; 
/* 251 */             if (StringUtil.equalEncodings(csName, "UTF-16LE"))
/* 252 */               return "UTF-16LE"; 
/* 254 */             if (StringUtil.equalEncodings(csName, "UTF-16"))
/* 255 */               return "UTF-16"; 
/* 257 */             if (StringUtil.equalEncodings(csName, "UTF-32BE"))
/* 258 */               return "UTF-32BE"; 
/* 260 */             if (StringUtil.equalEncodings(csName, "UTF-32LE"))
/* 261 */               return "UTF-32LE"; 
/* 263 */             if (StringUtil.equalEncodings(csName, "UTF-32"))
/* 264 */               return "UTF-32"; 
/* 266 */             if (StringUtil.equalEncodings(csName, "UTF"))
/* 268 */               return "UTF-16"; 
/*     */             break;
/*     */         } 
/*     */         break;
/*     */     } 
/* 274 */     return csName;
/*     */   }
/*     */   
/*     */   public static String findEncodingFor(Writer w) {
/* 284 */     if (w instanceof OutputStreamWriter) {
/* 285 */       String enc = ((OutputStreamWriter)w).getEncoding();
/* 290 */       return normalize(enc);
/*     */     } 
/* 292 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\CharsetNames.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */