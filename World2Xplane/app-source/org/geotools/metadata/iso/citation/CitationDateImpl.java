/*     */ package org.geotools.metadata.iso.citation;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.CitationDate;
/*     */ import org.opengis.metadata.citation.DateType;
/*     */ 
/*     */ public class CitationDateImpl extends MetadataEntity implements CitationDate {
/*     */   private static final long serialVersionUID = -2884791484254008454L;
/*     */   
/*  49 */   private long date = Long.MIN_VALUE;
/*     */   
/*     */   private DateType dateType;
/*     */   
/*     */   public CitationDateImpl() {}
/*     */   
/*     */   public CitationDateImpl(CitationDate source) {
/*  68 */     super(source);
/*     */   }
/*     */   
/*     */   public CitationDateImpl(Date date, DateType dateType) {
/*  75 */     setDate(date);
/*  76 */     setDateType(dateType);
/*     */   }
/*     */   
/*     */   public synchronized Date getDate() {
/*  83 */     return (this.date != Long.MIN_VALUE) ? new Date(this.date) : null;
/*     */   }
/*     */   
/*     */   public synchronized void setDate(Date newValue) {
/*  90 */     checkWritePermission();
/*  91 */     this.date = (newValue != null) ? newValue.getTime() : Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public DateType getDateType() {
/*  98 */     return this.dateType;
/*     */   }
/*     */   
/*     */   public synchronized void setDateType(DateType newValue) {
/* 105 */     checkWritePermission();
/* 106 */     this.dateType = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\citation\CitationDateImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */