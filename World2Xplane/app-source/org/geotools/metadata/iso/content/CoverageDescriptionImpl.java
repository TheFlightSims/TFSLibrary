/*     */ package org.geotools.metadata.iso.content;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.opengis.metadata.content.ContentInformation;
/*     */ import org.opengis.metadata.content.CoverageContentType;
/*     */ import org.opengis.metadata.content.CoverageDescription;
/*     */ import org.opengis.metadata.content.RangeDimension;
/*     */ import org.opengis.util.RecordType;
/*     */ 
/*     */ public class CoverageDescriptionImpl extends ContentInformationImpl implements CoverageDescription {
/*     */   private static final long serialVersionUID = -326050615789333559L;
/*     */   
/*     */   private RecordType attributeDescription;
/*     */   
/*     */   private CoverageContentType contentType;
/*     */   
/*     */   private Collection<RangeDimension> dimensions;
/*     */   
/*     */   public CoverageDescriptionImpl() {}
/*     */   
/*     */   public CoverageDescriptionImpl(CoverageDescription source) {
/*  73 */     super((ContentInformation)source);
/*     */   }
/*     */   
/*     */   public RecordType getAttributeDescription() {
/*  80 */     return this.attributeDescription;
/*     */   }
/*     */   
/*     */   public synchronized void setAttributeDescription(RecordType newValue) {
/*  87 */     checkWritePermission();
/*  88 */     this.attributeDescription = newValue;
/*     */   }
/*     */   
/*     */   public CoverageContentType getContentType() {
/*  95 */     return this.contentType;
/*     */   }
/*     */   
/*     */   public synchronized void setContentType(CoverageContentType newValue) {
/* 102 */     checkWritePermission();
/* 103 */     this.contentType = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<RangeDimension> getDimensions() {
/* 112 */     return this.dimensions = nonNullCollection(this.dimensions, RangeDimension.class);
/*     */   }
/*     */   
/*     */   public synchronized void setDimensions(Collection<? extends RangeDimension> newValues) {
/* 121 */     this.dimensions = copyCollection(newValues, this.dimensions, RangeDimension.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\content\CoverageDescriptionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */