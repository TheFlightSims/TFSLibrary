/*     */ package org.openstreetmap.osmosis.core.time;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ import javax.xml.datatype.DatatypeConfigurationException;
/*     */ import javax.xml.datatype.DatatypeFactory;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ 
/*     */ public class DateParser {
/*     */   private DatatypeFactory datatypeFactory;
/*     */   
/*     */   private FallbackDateParser fallbackDateParser;
/*     */   
/*     */   private Calendar calendar;
/*     */   
/*     */   public DateParser() {
/*     */     try {
/*  36 */       this.datatypeFactory = DatatypeFactory.newInstance();
/*  38 */     } catch (DatatypeConfigurationException e) {
/*  39 */       throw new OsmosisRuntimeException("Unable to instantiate xml datatype factory.", e);
/*     */     } 
/*  42 */     this.fallbackDateParser = new FallbackDateParser();
/*  44 */     this.calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
/*     */   }
/*     */   
/*     */   private boolean isDateInShortStandardFormat(String date) {
/*  53 */     if (date.length() != 20)
/*  54 */       return false; 
/*  57 */     char[] dateChars = date.toCharArray();
/*  60 */     if (dateChars[4] != '-')
/*  61 */       return false; 
/*  63 */     if (dateChars[7] != '-')
/*  64 */       return false; 
/*  66 */     if (dateChars[10] != 'T')
/*  67 */       return false; 
/*  69 */     if (dateChars[13] != ':')
/*  70 */       return false; 
/*  72 */     if (dateChars[16] != ':')
/*  73 */       return false; 
/*  75 */     if (dateChars[19] != 'Z')
/*  76 */       return false; 
/*     */     int i;
/*  80 */     for (i = 0; i < 4; i++) {
/*  81 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/*  82 */         return false; 
/*     */     } 
/*  85 */     for (i = 5; i < 7; i++) {
/*  86 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/*  87 */         return false; 
/*     */     } 
/*  90 */     for (i = 8; i < 10; i++) {
/*  91 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/*  92 */         return false; 
/*     */     } 
/*  95 */     for (i = 11; i < 13; i++) {
/*  96 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/*  97 */         return false; 
/*     */     } 
/* 100 */     for (i = 14; i < 16; i++) {
/* 101 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/* 102 */         return false; 
/*     */     } 
/* 105 */     for (i = 17; i < 19; i++) {
/* 106 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/* 107 */         return false; 
/*     */     } 
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isDateInLongStandardFormat(String date) {
/* 121 */     if (date.length() != 24)
/* 122 */       return false; 
/* 125 */     char[] dateChars = date.toCharArray();
/* 128 */     if (dateChars[4] != '-')
/* 129 */       return false; 
/* 131 */     if (dateChars[7] != '-')
/* 132 */       return false; 
/* 134 */     if (dateChars[10] != 'T')
/* 135 */       return false; 
/* 137 */     if (dateChars[13] != ':')
/* 138 */       return false; 
/* 140 */     if (dateChars[16] != ':')
/* 141 */       return false; 
/* 143 */     if (dateChars[19] != '.')
/* 144 */       return false; 
/* 146 */     if (dateChars[23] != 'Z')
/* 147 */       return false; 
/*     */     int i;
/* 151 */     for (i = 0; i < 4; i++) {
/* 152 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/* 153 */         return false; 
/*     */     } 
/* 156 */     for (i = 5; i < 7; i++) {
/* 157 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/* 158 */         return false; 
/*     */     } 
/* 161 */     for (i = 8; i < 10; i++) {
/* 162 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/* 163 */         return false; 
/*     */     } 
/* 166 */     for (i = 11; i < 13; i++) {
/* 167 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/* 168 */         return false; 
/*     */     } 
/* 171 */     for (i = 14; i < 16; i++) {
/* 172 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/* 173 */         return false; 
/*     */     } 
/* 176 */     for (i = 17; i < 19; i++) {
/* 177 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/* 178 */         return false; 
/*     */     } 
/* 181 */     for (i = 20; i < 23; i++) {
/* 182 */       if (dateChars[i] < '0' || dateChars[i] > '9')
/* 183 */         return false; 
/*     */     } 
/* 188 */     return true;
/*     */   }
/*     */   
/*     */   private Date parseShortStandardDate(String date) {
/* 200 */     int year = Integer.parseInt(date.substring(0, 4));
/* 201 */     int month = Integer.parseInt(date.substring(5, 7));
/* 202 */     int day = Integer.parseInt(date.substring(8, 10));
/* 203 */     int hour = Integer.parseInt(date.substring(11, 13));
/* 204 */     int minute = Integer.parseInt(date.substring(14, 16));
/* 205 */     int second = Integer.parseInt(date.substring(17, 19));
/* 207 */     this.calendar.clear();
/* 208 */     this.calendar.set(1, year);
/* 209 */     this.calendar.set(2, month - 1);
/* 210 */     this.calendar.set(5, day);
/* 211 */     this.calendar.set(11, hour);
/* 212 */     this.calendar.set(12, minute);
/* 213 */     this.calendar.set(13, second);
/* 215 */     return this.calendar.getTime();
/*     */   }
/*     */   
/*     */   private Date parseLongStandardDate(String date) {
/* 228 */     int year = Integer.parseInt(date.substring(0, 4));
/* 229 */     int month = Integer.parseInt(date.substring(5, 7));
/* 230 */     int day = Integer.parseInt(date.substring(8, 10));
/* 231 */     int hour = Integer.parseInt(date.substring(11, 13));
/* 232 */     int minute = Integer.parseInt(date.substring(14, 16));
/* 233 */     int second = Integer.parseInt(date.substring(17, 19));
/* 234 */     int millisecond = Integer.parseInt(date.substring(20, 23));
/* 236 */     this.calendar.clear();
/* 237 */     this.calendar.set(1, year);
/* 238 */     this.calendar.set(2, month - 1);
/* 239 */     this.calendar.set(5, day);
/* 240 */     this.calendar.set(11, hour);
/* 241 */     this.calendar.set(12, minute);
/* 242 */     this.calendar.set(13, second);
/* 243 */     this.calendar.set(14, millisecond);
/* 245 */     return this.calendar.getTime();
/*     */   }
/*     */   
/*     */   public Date parse(String date) {
/*     */     try {
/* 258 */       if (isDateInShortStandardFormat(date))
/* 259 */         return parseShortStandardDate(date); 
/* 260 */       if (isDateInLongStandardFormat(date))
/* 261 */         return parseLongStandardDate(date); 
/* 263 */       return this.datatypeFactory.newXMLGregorianCalendar(date).toGregorianCalendar().getTime();
/* 266 */     } catch (IllegalArgumentException e) {
/* 267 */       return this.fallbackDateParser.parse(date);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\time\DateParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */