/*     */ package com.vividsolutions.jts.precision;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.geom.util.GeometryEditor;
/*     */ 
/*     */ public class GeometryPrecisionReducer {
/*     */   private PrecisionModel targetPM;
/*     */   
/*     */   public static Geometry reduce(Geometry g, PrecisionModel precModel) {
/*  61 */     GeometryPrecisionReducer reducer = new GeometryPrecisionReducer(precModel);
/*  62 */     return reducer.reduce(g);
/*     */   }
/*     */   
/*     */   public static Geometry reducePointwise(Geometry g, PrecisionModel precModel) {
/*  78 */     GeometryPrecisionReducer reducer = new GeometryPrecisionReducer(precModel);
/*  79 */     reducer.setPointwise(true);
/*  80 */     return reducer.reduce(g);
/*     */   }
/*     */   
/*     */   private boolean removeCollapsed = true;
/*     */   
/*     */   private boolean changePrecisionModel = false;
/*     */   
/*     */   private boolean isPointwise = false;
/*     */   
/*     */   public GeometryPrecisionReducer(PrecisionModel pm) {
/*  90 */     this.targetPM = pm;
/*     */   }
/*     */   
/*     */   public void setRemoveCollapsedComponents(boolean removeCollapsed) {
/* 103 */     this.removeCollapsed = removeCollapsed;
/*     */   }
/*     */   
/*     */   public void setChangePrecisionModel(boolean changePrecisionModel) {
/* 118 */     this.changePrecisionModel = changePrecisionModel;
/*     */   }
/*     */   
/*     */   public void setPointwise(boolean isPointwise) {
/* 133 */     this.isPointwise = isPointwise;
/*     */   }
/*     */   
/*     */   public Geometry reduce(Geometry geom) {
/* 138 */     Geometry reducePW = reducePointwise(geom);
/* 139 */     if (this.isPointwise)
/* 140 */       return reducePW; 
/* 143 */     if (!(reducePW instanceof com.vividsolutions.jts.geom.Polygonal))
/* 144 */       return reducePW; 
/* 147 */     if (reducePW.isValid())
/* 147 */       return reducePW; 
/* 151 */     return fixPolygonalTopology(reducePW);
/*     */   }
/*     */   
/*     */   private Geometry reducePointwise(Geometry geom) {
/*     */     GeometryEditor geomEdit;
/* 157 */     if (this.changePrecisionModel) {
/* 158 */       GeometryFactory newFactory = createFactory(geom.getFactory(), this.targetPM);
/* 159 */       geomEdit = new GeometryEditor(newFactory);
/*     */     } else {
/* 163 */       geomEdit = new GeometryEditor();
/*     */     } 
/* 169 */     boolean finalRemoveCollapsed = this.removeCollapsed;
/* 170 */     if (geom.getDimension() >= 2)
/* 171 */       finalRemoveCollapsed = true; 
/* 173 */     Geometry reduceGeom = geomEdit.edit(geom, (GeometryEditor.GeometryEditorOperation)new PrecisionReducerCoordinateOperation(this.targetPM, finalRemoveCollapsed));
/* 176 */     return reduceGeom;
/*     */   }
/*     */   
/*     */   private Geometry fixPolygonalTopology(Geometry geom) {
/* 185 */     Geometry geomToBuffer = geom;
/* 186 */     if (!this.changePrecisionModel)
/* 187 */       geomToBuffer = changePM(geom, this.targetPM); 
/* 190 */     Geometry bufGeom = geomToBuffer.buffer(0.0D);
/* 192 */     Geometry finalGeom = bufGeom;
/* 193 */     if (!this.changePrecisionModel)
/* 195 */       finalGeom = geom.getFactory().createGeometry(bufGeom); 
/* 197 */     return finalGeom;
/*     */   }
/*     */   
/*     */   private Geometry changePM(Geometry geom, PrecisionModel newPM) {
/* 210 */     GeometryEditor geomEditor = createEditor(geom.getFactory(), newPM);
/* 212 */     return geomEditor.edit(geom, (GeometryEditor.GeometryEditorOperation)new GeometryEditor.NoOpGeometryOperation());
/*     */   }
/*     */   
/*     */   private GeometryEditor createEditor(GeometryFactory geomFactory, PrecisionModel newPM) {
/* 218 */     if (geomFactory.getPrecisionModel() == newPM)
/* 219 */       return new GeometryEditor(); 
/* 221 */     GeometryFactory newFactory = createFactory(geomFactory, newPM);
/* 222 */     GeometryEditor geomEdit = new GeometryEditor(newFactory);
/* 223 */     return geomEdit;
/*     */   }
/*     */   
/*     */   private GeometryFactory createFactory(GeometryFactory inputFactory, PrecisionModel pm) {
/* 228 */     GeometryFactory newFactory = new GeometryFactory(pm, inputFactory.getSRID(), inputFactory.getCoordinateSequenceFactory());
/* 232 */     return newFactory;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\precision\GeometryPrecisionReducer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */