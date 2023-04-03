/*     */ package org.geotools.metadata.iso.spatial;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.opengis.metadata.spatial.CellGeometry;
/*     */ import org.opengis.metadata.spatial.Dimension;
/*     */ import org.opengis.metadata.spatial.GridSpatialRepresentation;
/*     */ import org.opengis.metadata.spatial.SpatialRepresentation;
/*     */ 
/*     */ public class GridSpatialRepresentationImpl extends SpatialRepresentationImpl implements GridSpatialRepresentation {
/*     */   private static final long serialVersionUID = -8400572307442433979L;
/*     */   
/*     */   private Integer numberOfDimensions;
/*     */   
/*     */   private List<Dimension> axisDimensionsProperties;
/*     */   
/*     */   private CellGeometry cellGeometry;
/*     */   
/*     */   private boolean transformationParameterAvailable;
/*     */   
/*     */   public GridSpatialRepresentationImpl() {}
/*     */   
/*     */   public GridSpatialRepresentationImpl(GridSpatialRepresentation source) {
/*  80 */     super((SpatialRepresentation)source);
/*     */   }
/*     */   
/*     */   public GridSpatialRepresentationImpl(int numberOfDimensions, List<? extends Dimension> axisDimensionsProperties, CellGeometry cellGeometry, boolean transformationParameterAvailable) {
/*  94 */     setNumberOfDimensions(Integer.valueOf(numberOfDimensions));
/*  95 */     setAxisDimensionsProperties(axisDimensionsProperties);
/*  96 */     setCellGeometry(cellGeometry);
/*  97 */     setTransformationParameterAvailable(transformationParameterAvailable);
/*     */   }
/*     */   
/*     */   public Integer getNumberOfDimensions() {
/* 104 */     return this.numberOfDimensions;
/*     */   }
/*     */   
/*     */   public synchronized void setNumberOfDimensions(Integer newValue) {
/* 111 */     checkWritePermission();
/* 112 */     this.numberOfDimensions = newValue;
/*     */   }
/*     */   
/*     */   public synchronized List<Dimension> getAxisDimensionsProperties() {
/* 119 */     return this.axisDimensionsProperties = nonNullList(this.axisDimensionsProperties, Dimension.class);
/*     */   }
/*     */   
/*     */   public synchronized void setAxisDimensionsProperties(List<? extends Dimension> newValues) {
/* 126 */     checkWritePermission();
/* 127 */     this.axisDimensionsProperties = (List<Dimension>)copyCollection(newValues, this.axisDimensionsProperties, Dimension.class);
/*     */   }
/*     */   
/*     */   public CellGeometry getCellGeometry() {
/* 135 */     return this.cellGeometry;
/*     */   }
/*     */   
/*     */   public synchronized void setCellGeometry(CellGeometry newValue) {
/* 142 */     checkWritePermission();
/* 143 */     this.cellGeometry = newValue;
/*     */   }
/*     */   
/*     */   public boolean isTransformationParameterAvailable() {
/* 150 */     return this.transformationParameterAvailable;
/*     */   }
/*     */   
/*     */   public synchronized void setTransformationParameterAvailable(boolean newValue) {
/* 157 */     checkWritePermission();
/* 158 */     this.transformationParameterAvailable = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\spatial\GridSpatialRepresentationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */