/*     */ package org.postgresql.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ public class ServerErrorMessage implements Serializable {
/*  18 */   private static final Character SEVERITY = new Character('S');
/*     */   
/*  19 */   private static final Character MESSAGE = new Character('M');
/*     */   
/*  20 */   private static final Character DETAIL = new Character('D');
/*     */   
/*  21 */   private static final Character HINT = new Character('H');
/*     */   
/*  22 */   private static final Character POSITION = new Character('P');
/*     */   
/*  23 */   private static final Character WHERE = new Character('W');
/*     */   
/*  24 */   private static final Character FILE = new Character('F');
/*     */   
/*  25 */   private static final Character LINE = new Character('L');
/*     */   
/*  26 */   private static final Character ROUTINE = new Character('R');
/*     */   
/*  27 */   private static final Character SQLSTATE = new Character('C');
/*     */   
/*  28 */   private static final Character INTERNAL_POSITION = new Character('p');
/*     */   
/*  29 */   private static final Character INTERNAL_QUERY = new Character('q');
/*     */   
/*  31 */   private final Hashtable m_mesgParts = new Hashtable<Object, Object>();
/*     */   
/*     */   private final int verbosity;
/*     */   
/*     */   public ServerErrorMessage(String p_serverError, int verbosity) {
/*  36 */     this.verbosity = verbosity;
/*  38 */     char[] l_chars = p_serverError.toCharArray();
/*  39 */     int l_pos = 0;
/*  40 */     int l_length = l_chars.length;
/*  41 */     while (l_pos < l_length) {
/*  43 */       char l_mesgType = l_chars[l_pos];
/*  44 */       if (l_mesgType != '\000') {
/*  47 */         int l_startString = ++l_pos;
/*  48 */         while (l_chars[l_pos] != '\000' && l_pos < l_length)
/*  50 */           l_pos++; 
/*  52 */         String l_mesgPart = new String(l_chars, l_startString, l_pos - l_startString);
/*  53 */         this.m_mesgParts.put(new Character(l_mesgType), l_mesgPart);
/*     */       } 
/*  55 */       l_pos++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getSQLState() {
/*  61 */     return (String)this.m_mesgParts.get(SQLSTATE);
/*     */   }
/*     */   
/*     */   public String getMessage() {
/*  66 */     return (String)this.m_mesgParts.get(MESSAGE);
/*     */   }
/*     */   
/*     */   public String getSeverity() {
/*  71 */     return (String)this.m_mesgParts.get(SEVERITY);
/*     */   }
/*     */   
/*     */   public String getDetail() {
/*  76 */     return (String)this.m_mesgParts.get(DETAIL);
/*     */   }
/*     */   
/*     */   public String getHint() {
/*  81 */     return (String)this.m_mesgParts.get(HINT);
/*     */   }
/*     */   
/*     */   public int getPosition() {
/*  86 */     return getIntegerPart(POSITION);
/*     */   }
/*     */   
/*     */   public String getWhere() {
/*  91 */     return (String)this.m_mesgParts.get(WHERE);
/*     */   }
/*     */   
/*     */   public String getFile() {
/*  96 */     return (String)this.m_mesgParts.get(FILE);
/*     */   }
/*     */   
/*     */   public int getLine() {
/* 101 */     return getIntegerPart(LINE);
/*     */   }
/*     */   
/*     */   public String getRoutine() {
/* 106 */     return (String)this.m_mesgParts.get(ROUTINE);
/*     */   }
/*     */   
/*     */   public String getInternalQuery() {
/* 111 */     return (String)this.m_mesgParts.get(INTERNAL_QUERY);
/*     */   }
/*     */   
/*     */   public int getInternalPosition() {
/* 116 */     return getIntegerPart(INTERNAL_POSITION);
/*     */   }
/*     */   
/*     */   private int getIntegerPart(Character c) {
/* 121 */     String s = (String)this.m_mesgParts.get(c);
/* 122 */     if (s == null)
/* 123 */       return 0; 
/* 124 */     return Integer.parseInt(s);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 146 */     StringBuffer l_totalMessage = new StringBuffer();
/* 147 */     String l_message = (String)this.m_mesgParts.get(SEVERITY);
/* 148 */     if (l_message != null)
/* 149 */       l_totalMessage.append(l_message).append(": "); 
/* 150 */     l_message = (String)this.m_mesgParts.get(MESSAGE);
/* 151 */     if (l_message != null)
/* 152 */       l_totalMessage.append(l_message); 
/* 153 */     l_message = (String)this.m_mesgParts.get(DETAIL);
/* 154 */     if (l_message != null)
/* 155 */       l_totalMessage.append("\n  ").append(GT.tr("Detail: {0}", l_message)); 
/* 157 */     l_message = (String)this.m_mesgParts.get(HINT);
/* 158 */     if (l_message != null)
/* 159 */       l_totalMessage.append("\n  ").append(GT.tr("Hint: {0}", l_message)); 
/* 160 */     l_message = (String)this.m_mesgParts.get(POSITION);
/* 161 */     if (l_message != null)
/* 162 */       l_totalMessage.append("\n  ").append(GT.tr("Position: {0}", l_message)); 
/* 163 */     l_message = (String)this.m_mesgParts.get(WHERE);
/* 164 */     if (l_message != null)
/* 165 */       l_totalMessage.append("\n  ").append(GT.tr("Where: {0}", l_message)); 
/* 167 */     if (this.verbosity > 2) {
/* 169 */       String l_internalQuery = (String)this.m_mesgParts.get(INTERNAL_QUERY);
/* 170 */       if (l_internalQuery != null)
/* 171 */         l_totalMessage.append("\n  ").append(GT.tr("Internal Query: {0}", l_internalQuery)); 
/* 172 */       String l_internalPosition = (String)this.m_mesgParts.get(INTERNAL_POSITION);
/* 173 */       if (l_internalPosition != null)
/* 174 */         l_totalMessage.append("\n  ").append(GT.tr("Internal Position: {0}", l_internalPosition)); 
/* 176 */       String l_file = (String)this.m_mesgParts.get(FILE);
/* 177 */       String l_line = (String)this.m_mesgParts.get(LINE);
/* 178 */       String l_routine = (String)this.m_mesgParts.get(ROUTINE);
/* 179 */       if (l_file != null || l_line != null || l_routine != null)
/* 180 */         l_totalMessage.append("\n  ").append(GT.tr("Location: File: {0}, Routine: {1}, Line: {2}", new Object[] { l_file, l_routine, l_line })); 
/* 181 */       l_message = (String)this.m_mesgParts.get(SQLSTATE);
/* 182 */       if (l_message != null)
/* 183 */         l_totalMessage.append("\n  ").append(GT.tr("Server SQLState: {0}", l_message)); 
/*     */     } 
/* 186 */     return l_totalMessage.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\ServerErrorMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */