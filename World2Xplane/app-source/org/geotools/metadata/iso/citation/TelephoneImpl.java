/*     */ package org.geotools.metadata.iso.citation;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.Telephone;
/*     */ 
/*     */ public class TelephoneImpl extends MetadataEntity implements Telephone {
/*     */   private static final long serialVersionUID = 4920157673337669241L;
/*     */   
/*     */   private Collection<String> voices;
/*     */   
/*     */   private Collection<String> facsimiles;
/*     */   
/*     */   public TelephoneImpl() {}
/*     */   
/*     */   public TelephoneImpl(Telephone source) {
/*  67 */     super(source);
/*     */   }
/*     */   
/*     */   public synchronized Collection<String> getVoices() {
/*  77 */     return this.voices = nonNullCollection(this.voices, String.class);
/*     */   }
/*     */   
/*     */   public synchronized void setVoices(Collection<? extends String> newValues) {
/*  87 */     this.voices = copyCollection(newValues, this.voices, String.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<String> getFacsimiles() {
/*  97 */     return this.facsimiles = nonNullCollection(this.facsimiles, String.class);
/*     */   }
/*     */   
/*     */   public synchronized void setFacsimiles(Collection<? extends String> newValues) {
/* 107 */     this.facsimiles = copyCollection(newValues, this.facsimiles, String.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\citation\TelephoneImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */