/*     */ package org.postgresql.core.v2;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.postgresql.core.ParameterList;
/*     */ import org.postgresql.core.Parser;
/*     */ import org.postgresql.core.ProtocolConnection;
/*     */ import org.postgresql.core.Query;
/*     */ 
/*     */ class V2Query implements Query {
/*     */   V2Query(String query, boolean withParameters, ProtocolConnection pconn) {
/*  22 */     this.useEStringSyntax = (pconn.getServerVersion() != null && pconn.getServerVersion().compareTo("8.1") > 0);
/*  24 */     boolean stdStrings = pconn.getStandardConformingStrings();
/*  26 */     if (!withParameters) {
/*  28 */       this.fragments = new String[] { query };
/*     */       return;
/*     */     } 
/*  34 */     Vector<String> v = new Vector();
/*  35 */     int lastParmEnd = 0;
/*  37 */     char[] aChars = query.toCharArray();
/*     */     int i;
/*  39 */     for (i = 0; i < aChars.length; i++) {
/*  41 */       switch (aChars[i]) {
/*     */         case '\'':
/*  44 */           i = Parser.parseSingleQuotes(aChars, i, stdStrings);
/*     */           break;
/*     */         case '"':
/*  48 */           i = Parser.parseDoubleQuotes(aChars, i);
/*     */           break;
/*     */         case '-':
/*  52 */           i = Parser.parseLineComment(aChars, i);
/*     */           break;
/*     */         case '/':
/*  56 */           i = Parser.parseBlockComment(aChars, i);
/*     */           break;
/*     */         case '$':
/*  60 */           i = Parser.parseDollarQuotes(aChars, i);
/*     */           break;
/*     */         case '?':
/*  64 */           v.addElement(query.substring(lastParmEnd, i));
/*  65 */           lastParmEnd = i + 1;
/*     */           break;
/*     */       } 
/*     */     } 
/*  73 */     v.addElement(query.substring(lastParmEnd, query.length()));
/*  75 */     this.fragments = new String[v.size()];
/*  76 */     for (i = 0; i < this.fragments.length; i++)
/*  77 */       this.fragments[i] = v.elementAt(i); 
/*     */   }
/*     */   
/*     */   public ParameterList createParameterList() {
/*  81 */     if (this.fragments.length == 1)
/*  82 */       return NO_PARAMETERS; 
/*  84 */     return new SimpleParameterList(this.fragments.length - 1, this.useEStringSyntax);
/*     */   }
/*     */   
/*     */   public String toString(ParameterList parameters) {
/*  88 */     StringBuffer sbuf = new StringBuffer(this.fragments[0]);
/*  89 */     for (int i = 1; i < this.fragments.length; i++) {
/*  91 */       if (parameters == null) {
/*  92 */         sbuf.append("?");
/*     */       } else {
/*  94 */         sbuf.append(parameters.toString(i));
/*     */       } 
/*  95 */       sbuf.append(this.fragments[i]);
/*     */     } 
/*  97 */     return sbuf.toString();
/*     */   }
/*     */   
/*     */   public void close() {}
/*     */   
/*     */   String[] getFragments() {
/* 104 */     return this.fragments;
/*     */   }
/*     */   
/* 107 */   private static final ParameterList NO_PARAMETERS = new SimpleParameterList(0, false);
/*     */   
/*     */   private final String[] fragments;
/*     */   
/*     */   private final boolean useEStringSyntax;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v2\V2Query.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */