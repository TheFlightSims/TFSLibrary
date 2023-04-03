/*     */ package org.geotools.metadata.iso.citation;
/*     */ 
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.metadata.citation.Series;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class SeriesImpl extends MetadataEntity implements Series {
/*     */   private static final long serialVersionUID = 2784101441023323052L;
/*     */   
/*     */   private InternationalString name;
/*     */   
/*     */   private String issueIdentification;
/*     */   
/*     */   private String page;
/*     */   
/*     */   public SeriesImpl() {}
/*     */   
/*     */   public SeriesImpl(Series source) {
/*  72 */     super(source);
/*     */   }
/*     */   
/*     */   public SeriesImpl(CharSequence name) {
/*     */     SimpleInternationalString simpleInternationalString;
/*  80 */     if (name instanceof InternationalString) {
/*  81 */       InternationalString n = (InternationalString)name;
/*     */     } else {
/*  83 */       simpleInternationalString = new SimpleInternationalString(name.toString());
/*     */     } 
/*  85 */     setName((InternationalString)simpleInternationalString);
/*     */   }
/*     */   
/*     */   public InternationalString getName() {
/*  92 */     return this.name;
/*     */   }
/*     */   
/*     */   public synchronized void setName(InternationalString newValue) {
/*  99 */     checkWritePermission();
/* 100 */     this.name = newValue;
/*     */   }
/*     */   
/*     */   public String getIssueIdentification() {
/* 107 */     return this.issueIdentification;
/*     */   }
/*     */   
/*     */   public synchronized void setIssueIdentification(String newValue) {
/* 114 */     checkWritePermission();
/* 115 */     this.issueIdentification = newValue;
/*     */   }
/*     */   
/*     */   public String getPage() {
/* 122 */     return this.page;
/*     */   }
/*     */   
/*     */   public synchronized void setPage(String newValue) {
/* 129 */     checkWritePermission();
/* 130 */     this.page = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\citation\SeriesImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */