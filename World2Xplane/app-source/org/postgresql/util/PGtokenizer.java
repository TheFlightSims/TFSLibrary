/*     */ package org.postgresql.util;
/*     */ 
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class PGtokenizer {
/*     */   protected Vector tokens;
/*     */   
/*     */   public PGtokenizer(String string, char delim) {
/*  43 */     tokenize(string, delim);
/*     */   }
/*     */   
/*     */   public int tokenize(String string, char delim) {
/*  54 */     this.tokens = new Vector();
/*  63 */     int nest = 0;
/*     */     int s;
/*  65 */     for (int p = 0; p < string.length(); p++) {
/*  67 */       char c = string.charAt(p);
/*  70 */       if (c == '(' || c == '[' || c == '<')
/*  71 */         nest++; 
/*  74 */       if (c == ')' || c == ']' || c == '>')
/*  75 */         nest--; 
/*  77 */       if (nest == 0 && c == delim) {
/*  79 */         this.tokens.addElement(string.substring(s, p));
/*  80 */         s = p + 1;
/*     */       } 
/*     */     } 
/*  89 */     if (s < string.length())
/*  90 */       this.tokens.addElement(string.substring(s)); 
/*  92 */     return this.tokens.size();
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 100 */     return this.tokens.size();
/*     */   }
/*     */   
/*     */   public String getToken(int n) {
/* 109 */     return this.tokens.elementAt(n);
/*     */   }
/*     */   
/*     */   public PGtokenizer tokenizeToken(int n, char delim) {
/* 124 */     return new PGtokenizer(getToken(n), delim);
/*     */   }
/*     */   
/*     */   public static String remove(String s, String l, String t) {
/* 137 */     if (s.startsWith(l))
/* 138 */       s = s.substring(l.length()); 
/* 139 */     if (s.endsWith(t))
/* 140 */       s = s.substring(0, s.length() - t.length()); 
/* 141 */     return s;
/*     */   }
/*     */   
/*     */   public void remove(String l, String t) {
/* 152 */     for (int i = 0; i < this.tokens.size(); i++)
/* 154 */       this.tokens.setElementAt(remove(this.tokens.elementAt(i), l, t), i); 
/*     */   }
/*     */   
/*     */   public static String removePara(String s) {
/* 166 */     return remove(s, "(", ")");
/*     */   }
/*     */   
/*     */   public void removePara() {
/* 176 */     remove("(", ")");
/*     */   }
/*     */   
/*     */   public static String removeBox(String s) {
/* 187 */     return remove(s, "[", "]");
/*     */   }
/*     */   
/*     */   public void removeBox() {
/* 197 */     remove("[", "]");
/*     */   }
/*     */   
/*     */   public static String removeAngle(String s) {
/* 208 */     return remove(s, "<", ">");
/*     */   }
/*     */   
/*     */   public void removeAngle() {
/* 218 */     remove("<", ">");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\PGtokenizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */