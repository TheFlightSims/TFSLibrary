/*     */ package org.openstreetmap.osmosis.core.time;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ 
/*     */ public class FallbackDateParser {
/*  24 */   private static final Logger LOG = Logger.getLogger(FallbackDateParser.class.getName());
/*     */   
/*  26 */   private static final String[] FORMATS = new String[] { 
/*  26 */       "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss", "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'", "MM/dd/yyyy'T'HH:mm:ss.SSSZ", "MM/dd/yyyy'T'HH:mm:ss.SSS", 
/*  26 */       "MM/dd/yyyy'T'HH:mm:ssZ", "MM/dd/yyyy'T'HH:mm:ss", "yyyy:MM:dd HH:mm:ss" };
/*     */   
/*  52 */   private List<DateFormat> dateParsers = new ArrayList<DateFormat>(FORMATS.length);
/*     */   
/*     */   private int activeDateParser;
/*     */   
/*     */   public FallbackDateParser() {
/*  53 */     for (int i = 0; i < FORMATS.length; i++)
/*  54 */       this.dateParsers.add(new SimpleDateFormat(FORMATS[i])); 
/*  58 */     this.activeDateParser = -1;
/*     */   }
/*     */   
/*     */   public Date parse(String date) {
/*     */     String correctedDate;
/*  77 */     if (date.length() == 25 && date.charAt(22) == ':') {
/*  78 */       correctedDate = date.substring(0, 22) + date.substring(23, 25);
/*     */     } else {
/*  80 */       correctedDate = date;
/*     */     } 
/*  85 */     if (this.activeDateParser >= 0)
/*     */       try {
/*  87 */         return ((DateFormat)this.dateParsers.get(this.activeDateParser)).parse(correctedDate);
/*  88 */       } catch (ParseException e) {
/*  91 */         this.activeDateParser = -1;
/*     */       }  
/*  96 */     for (int i = 0; i < this.dateParsers.size(); i++) {
/*     */       try {
/* 102 */         Date result = ((DateFormat)this.dateParsers.get(i)).parse(correctedDate);
/* 103 */         this.activeDateParser = i;
/* 105 */         return result;
/* 107 */       } catch (ParseException e) {
/* 108 */         LOG.log(Level.FINER, "Pattern " + i + " could not parse the date.", e);
/*     */       } 
/*     */     } 
/* 113 */     throw new OsmosisRuntimeException("The date string (" + date + ") could not be parsed.");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\time\FallbackDateParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */