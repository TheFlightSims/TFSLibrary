/*     */ package org.geotools.metadata.iso.quality;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.lineage.Lineage;
/*     */ import org.opengis.metadata.quality.DataQuality;
/*     */ import org.opengis.metadata.quality.Element;
/*     */ import org.opengis.metadata.quality.Scope;
/*     */ 
/*     */ public class DataQualityImpl extends MetadataEntity implements DataQuality {
/*     */   private static final long serialVersionUID = 7964896551368382214L;
/*     */   
/*     */   private Scope scope;
/*     */   
/*     */   private Collection<Element> reports;
/*     */   
/*     */   private Lineage lineage;
/*     */   
/*     */   public DataQualityImpl() {}
/*     */   
/*     */   public DataQualityImpl(DataQuality source) {
/*  78 */     super(source);
/*     */   }
/*     */   
/*     */   public DataQualityImpl(Scope scope) {
/*  85 */     setScope(scope);
/*     */   }
/*     */   
/*     */   public Scope getScope() {
/*  92 */     return this.scope;
/*     */   }
/*     */   
/*     */   public synchronized void setScope(Scope newValue) {
/*  99 */     checkWritePermission();
/* 100 */     this.scope = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<Element> getReports() {
/* 109 */     return this.reports = nonNullCollection(this.reports, Element.class);
/*     */   }
/*     */   
/*     */   public synchronized void setReports(Collection<? extends Element> newValues) {
/* 118 */     this.reports = copyCollection(newValues, this.reports, Element.class);
/*     */   }
/*     */   
/*     */   public Lineage getLineage() {
/* 127 */     return this.lineage;
/*     */   }
/*     */   
/*     */   public synchronized void setLineage(Lineage newValue) {
/* 136 */     checkWritePermission();
/* 137 */     this.lineage = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\DataQualityImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */