/*     */ package org.geotools.metadata.iso.spatial;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.opengis.geometry.primitive.Point;
/*     */ import org.opengis.metadata.spatial.CellGeometry;
/*     */ import org.opengis.metadata.spatial.Dimension;
/*     */ import org.opengis.metadata.spatial.Georectified;
/*     */ import org.opengis.metadata.spatial.GridSpatialRepresentation;
/*     */ import org.opengis.metadata.spatial.PixelOrientation;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class GeorectifiedImpl extends GridSpatialRepresentationImpl implements Georectified {
/*     */   private static final long serialVersionUID = 5875851898471237138L;
/*     */   
/*     */   private boolean checkPointAvailable;
/*     */   
/*     */   private InternationalString checkPointDescription;
/*     */   
/*     */   private List<Point> cornerPoints;
/*     */   
/*     */   private Point centerPoint;
/*     */   
/*     */   private PixelOrientation pointInPixel;
/*     */   
/*     */   private InternationalString transformationDimensionDescription;
/*     */   
/*     */   private Collection<InternationalString> transformationDimensionMapping;
/*     */   
/*     */   public GeorectifiedImpl() {}
/*     */   
/*     */   public GeorectifiedImpl(Georectified source) {
/* 107 */     super((GridSpatialRepresentation)source);
/*     */   }
/*     */   
/*     */   public GeorectifiedImpl(int numberOfDimensions, List<? extends Dimension> axisDimensionsProperties, CellGeometry cellGeometry, boolean transformationParameterAvailable, boolean checkPointAvailable, List<? extends Point> cornerPoints, PixelOrientation pointInPixel) {
/* 121 */     super(numberOfDimensions, axisDimensionsProperties, cellGeometry, transformationParameterAvailable);
/* 125 */     setCheckPointAvailable(checkPointAvailable);
/* 126 */     setCornerPoints(cornerPoints);
/* 127 */     setPointInPixel(pointInPixel);
/*     */   }
/*     */   
/*     */   public boolean isCheckPointAvailable() {
/* 135 */     return this.checkPointAvailable;
/*     */   }
/*     */   
/*     */   public synchronized void setCheckPointAvailable(boolean newValue) {
/* 143 */     checkWritePermission();
/* 144 */     this.checkPointAvailable = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getCheckPointDescription() {
/* 152 */     return this.checkPointDescription;
/*     */   }
/*     */   
/*     */   public synchronized void setCheckPointDescription(InternationalString newValue) {
/* 160 */     checkWritePermission();
/* 161 */     this.checkPointDescription = newValue;
/*     */   }
/*     */   
/*     */   public synchronized List<Point> getCornerPoints() {
/* 173 */     return this.cornerPoints = nonNullList(this.cornerPoints, Point.class);
/*     */   }
/*     */   
/*     */   public synchronized void setCornerPoints(List<? extends Point> newValues) {
/* 180 */     this.cornerPoints = copyList(newValues, this.cornerPoints, Point.class);
/*     */   }
/*     */   
/*     */   public Point getCenterPoint() {
/* 191 */     return this.centerPoint;
/*     */   }
/*     */   
/*     */   public synchronized void setCenterPoint(Point newValue) {
/* 198 */     checkWritePermission();
/* 199 */     this.centerPoint = newValue;
/*     */   }
/*     */   
/*     */   public PixelOrientation getPointInPixel() {
/* 206 */     return this.pointInPixel;
/*     */   }
/*     */   
/*     */   public synchronized void setPointInPixel(PixelOrientation newValue) {
/* 213 */     checkWritePermission();
/* 214 */     this.pointInPixel = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getTransformationDimensionDescription() {
/* 221 */     return this.transformationDimensionDescription;
/*     */   }
/*     */   
/*     */   public synchronized void setTransformationDimensionDescription(InternationalString newValue) {
/* 228 */     checkWritePermission();
/* 229 */     this.transformationDimensionDescription = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<InternationalString> getTransformationDimensionMapping() {
/* 236 */     return this.transformationDimensionMapping = nonNullCollection(this.transformationDimensionMapping, InternationalString.class);
/*     */   }
/*     */   
/*     */   public synchronized void setTransformationDimensionMapping(Collection<? extends InternationalString> newValues) {
/* 246 */     this.transformationDimensionMapping = copyCollection(newValues, this.transformationDimensionMapping, InternationalString.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\spatial\GeorectifiedImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */