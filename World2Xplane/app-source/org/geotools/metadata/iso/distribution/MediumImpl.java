/*     */ package org.geotools.metadata.iso.distribution;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.distribution.Medium;
/*     */ import org.opengis.metadata.distribution.MediumFormat;
/*     */ import org.opengis.metadata.distribution.MediumName;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class MediumImpl extends MetadataEntity implements Medium {
/*     */   private static final long serialVersionUID = -2838122926367921673L;
/*     */   
/*     */   private MediumName name;
/*     */   
/*     */   private Collection<Double> densities;
/*     */   
/*     */   private Unit densityUnits;
/*     */   
/*     */   private Integer volumes;
/*     */   
/*     */   private Collection<MediumFormat> mediumFormats;
/*     */   
/*     */   private InternationalString mediumNote;
/*     */   
/*     */   public MediumImpl() {}
/*     */   
/*     */   public MediumImpl(Medium source) {
/*  94 */     super(source);
/*     */   }
/*     */   
/*     */   public MediumName getName() {
/* 101 */     return this.name;
/*     */   }
/*     */   
/*     */   public synchronized void setName(MediumName newValue) {
/* 108 */     checkWritePermission();
/* 109 */     this.name = newValue;
/*     */   }
/*     */   
/*     */   public Unit getDensityUnits() {
/* 116 */     return this.densityUnits;
/*     */   }
/*     */   
/*     */   public synchronized void setDensityUnits(Unit newValue) {
/* 123 */     checkWritePermission();
/* 124 */     this.densityUnits = newValue;
/*     */   }
/*     */   
/*     */   public Integer getVolumes() {
/* 132 */     return this.volumes;
/*     */   }
/*     */   
/*     */   public synchronized void setVolumes(Integer newValue) {
/* 140 */     checkWritePermission();
/* 141 */     this.volumes = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<MediumFormat> getMediumFormats() {
/* 148 */     return this.mediumFormats = nonNullCollection(this.mediumFormats, MediumFormat.class);
/*     */   }
/*     */   
/*     */   public synchronized void setMediumFormats(Collection<? extends MediumFormat> newValues) {
/* 155 */     this.mediumFormats = copyCollection(newValues, this.mediumFormats, MediumFormat.class);
/*     */   }
/*     */   
/*     */   public InternationalString getMediumNote() {
/* 162 */     return this.mediumNote;
/*     */   }
/*     */   
/*     */   public synchronized void setMediumNote(InternationalString newValue) {
/* 169 */     checkWritePermission();
/* 170 */     this.mediumNote = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<Double> getDensities() {
/* 178 */     return this.densities = nonNullCollection(this.densities, Double.class);
/*     */   }
/*     */   
/*     */   public synchronized void setDensities(Collection<? extends Double> newValues) {
/* 186 */     this.densities = copyCollection(newValues, this.densities, Double.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\distribution\MediumImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */