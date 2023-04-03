/*     */ package org.geotools.filter.spatial;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope3D;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.util.Converters;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.MultiValuedFilter;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.BBOX3D;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.geometry.BoundingBox3D;
/*     */ 
/*     */ public class BBOX3DImpl implements BBOX3D {
/*     */   PropertyName property;
/*     */   
/*     */   ReferencedEnvelope3D envelope;
/*     */   
/*     */   FilterFactory factory;
/*     */   
/*     */   public BBOX3DImpl(PropertyName propertyName, ReferencedEnvelope3D env, FilterFactory factory) {
/*  55 */     this.property = propertyName;
/*  56 */     this.envelope = env;
/*  57 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public double getMaxX() {
/*  61 */     return this.envelope.getMaxX();
/*     */   }
/*     */   
/*     */   public double getMaxY() {
/*  65 */     return this.envelope.getMaxY();
/*     */   }
/*     */   
/*     */   public double getMinX() {
/*  69 */     return this.envelope.getMinX();
/*     */   }
/*     */   
/*     */   public double getMinY() {
/*  73 */     return this.envelope.getMinY();
/*     */   }
/*     */   
/*     */   public double getMinZ() {
/*  77 */     return this.envelope.getMinX();
/*     */   }
/*     */   
/*     */   public double getMaxZ() {
/*  81 */     return this.envelope.getMaxZ();
/*     */   }
/*     */   
/*     */   public PropertyName getProperty() {
/*  85 */     return this.property;
/*     */   }
/*     */   
/*     */   public String getPropertyName() {
/*  89 */     return this.property.getPropertyName();
/*     */   }
/*     */   
/*     */   public String getSRS() {
/*  93 */     return CRS.toSRS(this.envelope.getCoordinateReferenceSystem());
/*     */   }
/*     */   
/*     */   public BoundingBox3D getBounds() {
/*  97 */     return (BoundingBox3D)this.envelope;
/*     */   }
/*     */   
/*     */   public Expression getExpression1() {
/* 101 */     return (Expression)this.property;
/*     */   }
/*     */   
/*     */   public Expression getExpression2() {
/* 108 */     Coordinate[] coords = new Coordinate[5];
/* 109 */     coords[0] = new Coordinate(this.envelope.getMinX(), this.envelope.getMinY());
/* 110 */     coords[1] = new Coordinate(this.envelope.getMinX(), this.envelope.getMaxY());
/* 111 */     coords[2] = new Coordinate(this.envelope.getMaxX(), this.envelope.getMaxY());
/* 112 */     coords[3] = new Coordinate(this.envelope.getMaxX(), this.envelope.getMinY());
/* 113 */     coords[4] = new Coordinate(this.envelope.getMinX(), this.envelope.getMinY());
/* 115 */     LinearRing ring = null;
/* 117 */     GeometryFactory gfac = new GeometryFactory();
/*     */     try {
/* 119 */       ring = gfac.createLinearRing(coords);
/* 120 */     } catch (TopologyException tex) {
/* 121 */       throw new IllegalFilterException(tex.toString());
/*     */     } 
/* 124 */     Polygon polygon = gfac.createPolygon(ring, null);
/* 125 */     if (this.envelope instanceof ReferencedEnvelope3D) {
/* 126 */       ReferencedEnvelope3D refEnv = this.envelope;
/* 127 */       polygon.setUserData(refEnv.getCoordinateReferenceSystem());
/*     */     } 
/* 130 */     return (Expression)this.factory.literal(polygon);
/*     */   }
/*     */   
/*     */   public Object accept(FilterVisitor visitor, Object context) {
/* 134 */     return visitor.visit((BBOX)this, context);
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope3D get3DEnvelope(Geometry geom) {
/* 138 */     Coordinate[] coordinates = geom.getCoordinates();
/* 140 */     ReferencedEnvelope3D env = new ReferencedEnvelope3D();
/* 141 */     for (Coordinate coordinate : coordinates)
/* 142 */       env.expandToInclude(coordinate); 
/* 144 */     return env;
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object feature) {
/* 149 */     Geometry other = (Geometry)Converters.convert(this.property.evaluate(feature), Geometry.class);
/* 150 */     if (other == null)
/* 151 */       return false; 
/* 153 */     return get3DEnvelope(other).intersects(this.envelope);
/*     */   }
/*     */   
/*     */   public boolean isMatchingCase() {
/* 160 */     return false;
/*     */   }
/*     */   
/*     */   public boolean contains(SimpleFeature feature) {
/* 164 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   public boolean evaluate(SimpleFeature feature) {
/* 168 */     return evaluate(feature);
/*     */   }
/*     */   
/*     */   public MultiValuedFilter.MatchAction getMatchAction() {
/* 172 */     return MultiValuedFilter.MatchAction.ANY;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 177 */     return "BBOX3D [property=" + this.property + ", envelope=" + this.envelope + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\spatial\BBOX3DImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */