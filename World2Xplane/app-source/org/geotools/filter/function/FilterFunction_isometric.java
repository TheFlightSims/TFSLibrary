/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.geotools.filter.FunctionExpressionImpl;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class FilterFunction_isometric extends FunctionExpressionImpl implements GeometryTransformation {
/*  50 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("isometric", Geometry.class, new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class), FunctionNameImpl.parameter("extrusion", Double.class) });
/*     */   
/*     */   public FilterFunction_isometric() {
/*  55 */     super(NAME);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/*  59 */     Geometry geom = (Geometry)getExpression(0).evaluate(feature, Geometry.class);
/*  60 */     Double extrusion = (Double)getExpression(1).evaluate(feature, Double.class);
/*  62 */     if (geom != null && extrusion != null) {
/*  64 */       SegmentExtractorFilter extractor = new SegmentExtractorFilter();
/*  65 */       geom.apply(extractor);
/*  66 */       List<Polygon> faces = extractor.getFaces(geom.getFactory(), extrusion.doubleValue());
/*  69 */       if (geom instanceof Polygon) {
/*  70 */         Polygon offseted = (Polygon)geom.clone();
/*  71 */         offseted.apply(new FilterFunction_offset.OffsetOrdinateFilter(0.0D, extrusion.doubleValue()));
/*  72 */         faces.add(0, (Polygon)geom);
/*  73 */         faces.add(offseted);
/*  74 */       } else if (geom instanceof GeometryCollection) {
/*  75 */         GeometryCollection gc = (GeometryCollection)geom;
/*  76 */         for (int i = 0; i < gc.getNumGeometries(); i++) {
/*  77 */           Geometry g = gc.getGeometryN(i);
/*  78 */           if (g instanceof Polygon) {
/*  79 */             Polygon offseted = (Polygon)g.clone();
/*  80 */             offseted.apply(new FilterFunction_offset.OffsetOrdinateFilter(0.0D, extrusion.doubleValue()));
/*  81 */             faces.add(0, (Polygon)g);
/*  82 */             faces.add(offseted);
/*     */           } 
/*     */         } 
/*     */       } 
/*  87 */       Polygon[] polyArray = faces.<Polygon>toArray(new Polygon[faces.size()]);
/*  88 */       return geom.getFactory().createMultiPolygon(polyArray);
/*     */     } 
/*  91 */     return null;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope invert(ReferencedEnvelope renderingEnvelope) {
/* 101 */     Double offsetY = (Double)getExpression(1).evaluate(null, Double.class);
/* 103 */     if (offsetY != null) {
/* 104 */       ReferencedEnvelope offseted = new ReferencedEnvelope(renderingEnvelope);
/* 105 */       offseted.translate(0.0D, offsetY.doubleValue());
/* 106 */       return offseted;
/*     */     } 
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   static class SegmentExtractorFilter implements GeometryComponentFilter {
/* 116 */     List<FilterFunction_isometric.Segment> segments = new ArrayList<FilterFunction_isometric.Segment>();
/*     */     
/*     */     public void filter(Geometry geom) {
/* 119 */       if (geom instanceof LineString)
/* 120 */         extractSegments(((LineString)geom).getCoordinateSequence()); 
/*     */     }
/*     */     
/*     */     private void extractSegments(CoordinateSequence cs) {
/* 125 */       for (int i = 0; i < cs.size() - 1; i++)
/* 126 */         this.segments.add(new FilterFunction_isometric.Segment(cs.getX(i), cs.getY(i), cs.getX(i + 1), cs.getY(i + 1))); 
/*     */     }
/*     */     
/*     */     List<Polygon> getFaces(GeometryFactory gf, double extrude) {
/* 132 */       Collections.sort(this.segments);
/* 135 */       List<Polygon> result = new ArrayList<Polygon>();
/* 136 */       for (FilterFunction_isometric.Segment segment : this.segments) {
/* 137 */         CoordinateSequence cs = gf.getCoordinateSequenceFactory().create(5, 2);
/* 138 */         cs.setOrdinate(0, 0, segment.x0);
/* 139 */         cs.setOrdinate(0, 1, segment.y0);
/* 140 */         cs.setOrdinate(3, 0, segment.x0);
/* 141 */         cs.setOrdinate(3, 1, segment.y0 + extrude);
/* 142 */         cs.setOrdinate(2, 0, segment.x1);
/* 143 */         cs.setOrdinate(2, 1, segment.y1 + extrude);
/* 144 */         cs.setOrdinate(1, 0, segment.x1);
/* 145 */         cs.setOrdinate(1, 1, segment.y1);
/* 146 */         cs.setOrdinate(4, 0, segment.x0);
/* 147 */         cs.setOrdinate(4, 1, segment.y0);
/* 148 */         result.add(gf.createPolygon(gf.createLinearRing(cs), null));
/*     */       } 
/* 151 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   static class Segment implements Comparable<Segment> {
/*     */     double x0;
/*     */     
/*     */     double y0;
/*     */     
/*     */     double x1;
/*     */     
/*     */     double y1;
/*     */     
/*     */     public Segment(double x0, double y0, double x1, double y1) {
/* 161 */       this.x0 = x0;
/* 162 */       this.y0 = y0;
/* 163 */       this.x1 = x1;
/* 164 */       this.y1 = y1;
/*     */     }
/*     */     
/*     */     public int compareTo(Segment other) {
/* 168 */       double maxY = Math.max(this.y0, this.y1);
/* 169 */       double otherMaxY = Math.max(other.y0, other.y1);
/* 170 */       if (maxY > otherMaxY)
/* 171 */         return -1; 
/* 172 */       if (maxY < otherMaxY)
/* 173 */         return 1; 
/* 175 */       double maxX = Math.max(this.x0, this.x1);
/* 176 */       double otherMaxX = Math.max(other.x0, other.x1);
/* 177 */       if (maxX > otherMaxX)
/* 178 */         return 1; 
/* 179 */       if (maxX < otherMaxX)
/* 180 */         return -1; 
/* 181 */       return 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_isometric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */