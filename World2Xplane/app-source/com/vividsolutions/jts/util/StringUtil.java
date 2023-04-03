/*    */ package com.vividsolutions.jts.util;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.LineNumberReader;
/*    */ import java.io.PrintStream;
/*    */ import java.io.StringReader;
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class StringUtil {
/*    */   public static String[] split(String s, String separator) {
/* 29 */     int separatorlen = separator.length();
/* 30 */     ArrayList<String> tokenList = new ArrayList();
/* 31 */     String tmpString = "" + s;
/* 32 */     int pos = tmpString.indexOf(separator);
/* 33 */     while (pos >= 0) {
/* 34 */       String token = tmpString.substring(0, pos);
/* 35 */       tokenList.add(token);
/* 36 */       tmpString = tmpString.substring(pos + separatorlen);
/* 37 */       pos = tmpString.indexOf(separator);
/*    */     } 
/* 39 */     if (tmpString.length() > 0)
/* 40 */       tokenList.add(tmpString); 
/* 41 */     String[] res = new String[tokenList.size()];
/* 42 */     for (int i = 0; i < res.length; i++)
/* 43 */       res[i] = tokenList.get(i); 
/* 45 */     return res;
/*    */   }
/*    */   
/* 48 */   public static final String NEWLINE = System.getProperty("line.separator");
/*    */   
/*    */   public static String getStackTrace(Throwable t) {
/* 54 */     ByteArrayOutputStream os = new ByteArrayOutputStream();
/* 55 */     PrintStream ps = new PrintStream(os);
/* 56 */     t.printStackTrace(ps);
/* 57 */     return os.toString();
/*    */   }
/*    */   
/*    */   public static String getStackTrace(Throwable t, int depth) {
/* 61 */     String stackTrace = "";
/* 62 */     StringReader stringReader = new StringReader(getStackTrace(t));
/* 63 */     LineNumberReader lineNumberReader = new LineNumberReader(stringReader);
/* 64 */     for (int i = 0; i < depth; i++) {
/*    */       try {
/* 66 */         stackTrace = stackTrace + lineNumberReader.readLine() + NEWLINE;
/* 67 */       } catch (IOException e) {
/* 68 */         Assert.shouldNeverReachHere();
/*    */       } 
/*    */     } 
/* 71 */     return stackTrace;
/*    */   }
/*    */   
/* 74 */   private static NumberFormat SIMPLE_ORDINATE_FORMAT = new DecimalFormat("0.#");
/*    */   
/*    */   public static String toString(double d) {
/* 78 */     return SIMPLE_ORDINATE_FORMAT.format(d);
/*    */   }
/*    */   
/*    */   public static String spaces(int n) {
/* 83 */     return chars(' ', n);
/*    */   }
/*    */   
/*    */   public static String chars(char c, int n) {
/* 88 */     char[] ch = new char[n];
/* 89 */     for (int i = 0; i < n; i++)
/* 90 */       ch[i] = c; 
/* 92 */     return new String(ch);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\StringUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */