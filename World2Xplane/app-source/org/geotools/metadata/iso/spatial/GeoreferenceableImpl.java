/*     */ package org.geotools.metadata.iso.spatial;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.spatial.CellGeometry;
/*     */ import org.opengis.metadata.spatial.Dimension;
/*     */ import org.opengis.metadata.spatial.Georeferenceable;
/*     */ import org.opengis.metadata.spatial.GridSpatialRepresentation;
/*     */ import org.opengis.util.InternationalString;
/*     */ import org.opengis.util.Record;
/*     */ 
/*     */ public class GeoreferenceableImpl extends GridSpatialRepresentationImpl implements Georeferenceable {
/*     */   private static final long serialVersionUID = 5203270142818028946L;
/*     */   
/*     */   private boolean controlPointAvailable;
/*     */   
/*     */   private boolean orientationParameterAvailable;
/*     */   
/*     */   private InternationalString orientationParameterDescription;
/*     */   
/*     */   private Record georeferencedParameters;
/*     */   
/*     */   private Collection<Citation> parameterCitation;
/*     */   
/*     */   public GeoreferenceableImpl() {}
/*     */   
/*     */   public GeoreferenceableImpl(Georeferenceable source) {
/*  89 */     super((GridSpatialRepresentation)source);
/*     */   }
/*     */   
/*     */   public GeoreferenceableImpl(int numberOfDimensions, List<? extends Dimension> axisDimensionsProperties, CellGeometry cellGeometry, boolean transformationParameterAvailable, boolean controlPointAvailable, boolean orientationParameterAvailable) {
/* 102 */     super(numberOfDimensions, axisDimensionsProperties, cellGeometry, transformationParameterAvailable);
/* 103 */     setControlPointAvailable(controlPointAvailable);
/* 104 */     setOrientationParameterAvailable(orientationParameterAvailable);
/*     */   }
/*     */   
/*     */   public boolean isControlPointAvailable() {
/* 111 */     return this.controlPointAvailable;
/*     */   }
/*     */   
/*     */   public synchronized void setControlPointAvailable(boolean newValue) {
/* 118 */     checkWritePermission();
/* 119 */     this.controlPointAvailable = newValue;
/*     */   }
/*     */   
/*     */   public boolean isOrientationParameterAvailable() {
/* 126 */     return this.orientationParameterAvailable;
/*     */   }
/*     */   
/*     */   public synchronized void setOrientationParameterAvailable(boolean newValue) {
/* 133 */     checkWritePermission();
/* 134 */     this.orientationParameterAvailable = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getOrientationParameterDescription() {
/* 141 */     return this.orientationParameterDescription;
/*     */   }
/*     */   
/*     */   public synchronized void setOrientationParameterDescription(InternationalString newValue) {
/* 148 */     checkWritePermission();
/* 149 */     this.orientationParameterDescription = newValue;
/*     */   }
/*     */   
/*     */   public Record getGeoreferencedParameters() {
/* 158 */     return this.georeferencedParameters;
/*     */   }
/*     */   
/*     */   public synchronized void setGeoreferencedParameters(Record newValue) {
/* 167 */     checkWritePermission();
/* 168 */     this.georeferencedParameters = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<Citation> getParameterCitation() {
/* 175 */     return this.parameterCitation = nonNullCollection(this.parameterCitation, Citation.class);
/*     */   }
/*     */   
/*     */   public synchronized void setParameterCitation(Collection<? extends Citation> newValues) {
/* 182 */     this.parameterCitation = copyCollection(newValues, this.parameterCitation, Citation.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\spatial\GeoreferenceableImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */