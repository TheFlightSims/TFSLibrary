/*     */ package com.vividsolutions.jts.precision;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.geom.util.GeometryEditor;
/*     */ 
/*     */ public class SimpleGeometryPrecisionReducer {
/*     */   private PrecisionModel newPrecisionModel;
/*     */   
/*     */   public static Geometry reduce(Geometry g, PrecisionModel precModel) {
/*  68 */     SimpleGeometryPrecisionReducer reducer = new SimpleGeometryPrecisionReducer(precModel);
/*  69 */     return reducer.reduce(g);
/*     */   }
/*     */   
/*     */   private boolean removeCollapsed = true;
/*     */   
/*     */   private boolean changePrecisionModel = false;
/*     */   
/*     */   public SimpleGeometryPrecisionReducer(PrecisionModel pm) {
/*  78 */     this.newPrecisionModel = pm;
/*     */   }
/*     */   
/*     */   public void setRemoveCollapsedComponents(boolean removeCollapsed) {
/*  91 */     this.removeCollapsed = removeCollapsed;
/*     */   }
/*     */   
/*     */   public void setChangePrecisionModel(boolean changePrecisionModel) {
/* 106 */     this.changePrecisionModel = changePrecisionModel;
/*     */   }
/*     */   
/*     */   public Geometry reduce(Geometry geom) {
/*     */     GeometryEditor geomEdit;
/* 112 */     if (this.changePrecisionModel) {
/* 113 */       GeometryFactory newFactory = new GeometryFactory(this.newPrecisionModel, geom.getFactory().getSRID());
/* 114 */       geomEdit = new GeometryEditor(newFactory);
/*     */     } else {
/* 118 */       geomEdit = new GeometryEditor();
/*     */     } 
/* 120 */     return geomEdit.edit(geom, (GeometryEditor.GeometryEditorOperation)new PrecisionReducerCoordinateOperation());
/*     */   }
/*     */   
/*     */   private class PrecisionReducerCoordinateOperation extends GeometryEditor.CoordinateOperation {
/*     */     private PrecisionReducerCoordinateOperation() {}
/*     */     
/*     */     public Coordinate[] edit(Coordinate[] coordinates, Geometry geom) {
/* 128 */       if (coordinates.length == 0)
/* 128 */         return null; 
/* 130 */       Coordinate[] reducedCoords = new Coordinate[coordinates.length];
/* 132 */       for (int i = 0; i < coordinates.length; i++) {
/* 133 */         Coordinate coord = new Coordinate(coordinates[i]);
/* 134 */         SimpleGeometryPrecisionReducer.this.newPrecisionModel.makePrecise(coord);
/* 135 */         reducedCoords[i] = coord;
/*     */       } 
/* 138 */       CoordinateList noRepeatedCoordList = new CoordinateList(reducedCoords, false);
/* 139 */       Coordinate[] noRepeatedCoords = noRepeatedCoordList.toCoordinateArray();
/* 151 */       int minLength = 0;
/* 152 */       if (geom instanceof com.vividsolutions.jts.geom.LineString)
/* 152 */         minLength = 2; 
/* 153 */       if (geom instanceof com.vividsolutions.jts.geom.LinearRing)
/* 153 */         minLength = 4; 
/* 155 */       Coordinate[] collapsedCoords = reducedCoords;
/* 156 */       if (SimpleGeometryPrecisionReducer.this.removeCollapsed)
/* 156 */         collapsedCoords = null; 
/* 159 */       if (noRepeatedCoords.length < minLength)
/* 160 */         return collapsedCoords; 
/* 164 */       return noRepeatedCoords;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\precision\SimpleGeometryPrecisionReducer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */