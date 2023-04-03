/*     */ package org.postgresql.util;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ public class PGbytea {
/*     */   private static final int MAX_3_BUFF_SIZE = 2097152;
/*     */   
/*     */   public static byte[] toBytes(byte[] s) throws SQLException {
/*  27 */     if (s == null)
/*  28 */       return null; 
/*  34 */     if (s.length < 2 || s[0] != 92 || s[1] != 120)
/*  35 */       return toBytesOctalEscaped(s); 
/*  37 */     return toBytesHexEscaped(s);
/*     */   }
/*     */   
/*     */   private static byte[] toBytesHexEscaped(byte[] s) {
/*  42 */     byte[] output = new byte[(s.length - 2) / 2];
/*  43 */     for (int i = 0; i < output.length; i++) {
/*  44 */       byte b1 = gethex(s[2 + i * 2]);
/*  45 */       byte b2 = gethex(s[2 + i * 2 + 1]);
/*  46 */       output[i] = (byte)(b1 << 4 | b2);
/*     */     } 
/*  48 */     return output;
/*     */   }
/*     */   
/*     */   private static byte gethex(byte b) {
/*  53 */     if (b <= 57)
/*  54 */       return (byte)(b - 48); 
/*  57 */     if (b >= 97)
/*  58 */       return (byte)(b - 97 + 10); 
/*  61 */     return (byte)(b - 65 + 10);
/*     */   }
/*     */   
/*     */   private static byte[] toBytesOctalEscaped(byte[] s) {
/*  66 */     int slength = s.length;
/*  67 */     byte[] buf = null;
/*  68 */     int correctSize = slength;
/*  69 */     if (slength > 2097152) {
/*  74 */       for (int j = 0; j < slength; j++) {
/*  76 */         byte current = s[j];
/*  77 */         if (current == 92) {
/*  79 */           byte next = s[++j];
/*  80 */           if (next == 92) {
/*  82 */             correctSize--;
/*     */           } else {
/*  86 */             correctSize -= 3;
/*     */           } 
/*     */         } 
/*     */       } 
/*  90 */       buf = new byte[correctSize];
/*     */     } else {
/*  94 */       buf = new byte[slength];
/*     */     } 
/*  96 */     int bufpos = 0;
/* 100 */     for (int i = 0; i < slength; i++) {
/* 102 */       byte nextbyte = s[i];
/* 103 */       if (nextbyte == 92) {
/* 105 */         byte secondbyte = s[++i];
/* 106 */         if (secondbyte == 92) {
/* 109 */           buf[bufpos++] = 92;
/*     */         } else {
/* 113 */           int thebyte = (secondbyte - 48) * 64 + (s[++i] - 48) * 8 + s[++i] - 48;
/* 114 */           if (thebyte > 127)
/* 115 */             thebyte -= 256; 
/* 116 */           buf[bufpos++] = (byte)thebyte;
/*     */         } 
/*     */       } else {
/* 121 */         buf[bufpos++] = nextbyte;
/*     */       } 
/*     */     } 
/* 124 */     if (bufpos == correctSize)
/* 126 */       return buf; 
/* 128 */     byte[] l_return = new byte[bufpos];
/* 129 */     System.arraycopy(buf, 0, l_return, 0, bufpos);
/* 130 */     return l_return;
/*     */   }
/*     */   
/*     */   public static String toPGString(byte[] p_buf) throws SQLException {
/* 139 */     if (p_buf == null)
/* 140 */       return null; 
/* 141 */     StringBuffer l_strbuf = new StringBuffer(2 * p_buf.length);
/* 142 */     for (int i = 0; i < p_buf.length; i++) {
/* 144 */       int l_int = p_buf[i];
/* 145 */       if (l_int < 0)
/* 147 */         l_int = 256 + l_int; 
/* 153 */       if (l_int < 32 || l_int > 126) {
/* 157 */         l_strbuf.append("\\");
/* 158 */         l_strbuf.append((char)((l_int >> 6 & 0x3) + 48));
/* 159 */         l_strbuf.append((char)((l_int >> 3 & 0x7) + 48));
/* 160 */         l_strbuf.append((char)((l_int & 0x7) + 48));
/* 162 */       } else if (p_buf[i] == 92) {
/* 166 */         l_strbuf.append("\\\\");
/*     */       } else {
/* 171 */         l_strbuf.append((char)p_buf[i]);
/*     */       } 
/*     */     } 
/* 174 */     return l_strbuf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\PGbytea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */