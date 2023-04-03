/*     */ package org.postgresql.core;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class Utils {
/*     */   public static String toHexString(byte[] data) {
/*  36 */     StringBuffer sb = new StringBuffer(data.length * 2);
/*  37 */     for (int i = 0; i < data.length; i++) {
/*  39 */       sb.append(Integer.toHexString(data[i] >> 4 & 0xF));
/*  40 */       sb.append(Integer.toHexString(data[i] & 0xF));
/*     */     } 
/*  42 */     return sb.toString();
/*     */   }
/*     */   
/*  50 */   private static final Charset utf8Charset = Charset.forName("UTF-8");
/*     */   
/*     */   public static byte[] encodeUTF8(String str) {
/*  65 */     ByteBuffer buf = utf8Charset.encode(CharBuffer.wrap(str));
/*  66 */     byte[] b = new byte[buf.limit()];
/*  67 */     buf.get(b, 0, buf.limit());
/*  68 */     return b;
/*     */   }
/*     */   
/*     */   public static StringBuffer appendEscapedLiteral(StringBuffer sbuf, String value, boolean standardConformingStrings) throws SQLException {
/*  87 */     if (sbuf == null)
/*  88 */       sbuf = new StringBuffer(value.length() * 11 / 10); 
/*  90 */     if (standardConformingStrings) {
/*  93 */       for (int i = 0; i < value.length(); i++) {
/*  95 */         char ch = value.charAt(i);
/*  96 */         if (ch == '\000')
/*  97 */           throw new PSQLException(GT.tr("Zero bytes may not occur in string parameters."), PSQLState.INVALID_PARAMETER_VALUE); 
/*  98 */         if (ch == '\'')
/*  99 */           sbuf.append('\''); 
/* 100 */         sbuf.append(ch);
/*     */       } 
/*     */     } else {
/* 110 */       for (int i = 0; i < value.length(); i++) {
/* 112 */         char ch = value.charAt(i);
/* 113 */         if (ch == '\000')
/* 114 */           throw new PSQLException(GT.tr("Zero bytes may not occur in string parameters."), PSQLState.INVALID_PARAMETER_VALUE); 
/* 115 */         if (ch == '\\' || ch == '\'')
/* 116 */           sbuf.append(ch); 
/* 117 */         sbuf.append(ch);
/*     */       } 
/*     */     } 
/* 121 */     return sbuf;
/*     */   }
/*     */   
/*     */   public static StringBuffer appendEscapedIdentifier(StringBuffer sbuf, String value) throws SQLException {
/* 138 */     if (sbuf == null)
/* 139 */       sbuf = new StringBuffer(2 + value.length() * 11 / 10); 
/* 141 */     sbuf.append('"');
/* 143 */     for (int i = 0; i < value.length(); i++) {
/* 145 */       char ch = value.charAt(i);
/* 146 */       if (ch == '\000')
/* 147 */         throw new PSQLException(GT.tr("Zero bytes may not occur in identifiers."), PSQLState.INVALID_PARAMETER_VALUE); 
/* 148 */       if (ch == '"')
/* 149 */         sbuf.append(ch); 
/* 150 */       sbuf.append(ch);
/*     */     } 
/* 153 */     sbuf.append('"');
/* 155 */     return sbuf;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */