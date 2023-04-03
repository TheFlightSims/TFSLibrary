/*     */ package org.geotools.metadata.iso.identification;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.identification.KeywordType;
/*     */ import org.opengis.metadata.identification.Keywords;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class KeywordsImpl extends MetadataEntity implements Keywords {
/*     */   private static final long serialVersionUID = 48691634443678266L;
/*     */   
/*     */   private Collection<InternationalString> keywords;
/*     */   
/*     */   private KeywordType type;
/*     */   
/*     */   private Citation thesaurusName;
/*     */   
/*     */   public KeywordsImpl() {}
/*     */   
/*     */   public KeywordsImpl(Keywords source) {
/*  76 */     super(source);
/*     */   }
/*     */   
/*     */   public KeywordsImpl(Collection<? extends InternationalString> keywords) {
/*  83 */     setKeywords(keywords);
/*     */   }
/*     */   
/*     */   public synchronized Collection<InternationalString> getKeywords() {
/*  90 */     return this.keywords = nonNullCollection(this.keywords, InternationalString.class);
/*     */   }
/*     */   
/*     */   public synchronized void setKeywords(Collection<? extends InternationalString> newValues) {
/*  97 */     this.keywords = copyCollection(newValues, this.keywords, InternationalString.class);
/*     */   }
/*     */   
/*     */   public KeywordType getType() {
/* 104 */     return this.type;
/*     */   }
/*     */   
/*     */   public synchronized void setType(KeywordType newValue) {
/* 111 */     checkWritePermission();
/* 112 */     this.type = newValue;
/*     */   }
/*     */   
/*     */   public Citation getThesaurusName() {
/* 119 */     return this.thesaurusName;
/*     */   }
/*     */   
/*     */   public synchronized void setThesaurusName(Citation newValue) {
/* 127 */     checkWritePermission();
/* 128 */     this.thesaurusName = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\identification\KeywordsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */