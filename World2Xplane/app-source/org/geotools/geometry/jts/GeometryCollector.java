/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.impl.PackedCoordinateSequenceFactory;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class GeometryCollector {
/*  55 */   List<Geometry> geometries = new ArrayList<Geometry>();
/*     */   
/*  57 */   GeometryFactory factory = new GeometryFactory((CoordinateSequenceFactory)new PackedCoordinateSequenceFactory());
/*     */   
/*  59 */   long coordinates = 0L;
/*     */   
/*  61 */   long maxCoordinates = -1L;
/*     */   
/*  63 */   CoordinateReferenceSystem crs = null;
/*     */   
/*  65 */   int srid = -1;
/*     */   
/*     */   public long getMaxCoordinates() {
/*  74 */     return this.maxCoordinates;
/*     */   }
/*     */   
/*     */   public void setMaxCoordinates(long maxCoordinates) {
/*  83 */     this.maxCoordinates = maxCoordinates;
/*     */   }
/*     */   
/*     */   public GeometryFactory getFactory() {
/*  93 */     return this.factory;
/*     */   }
/*     */   
/*     */   public void setFactory(GeometryFactory factory) {
/* 104 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public GeometryCollection collect() {
/* 113 */     GeometryCollection gc = collectInternal();
/* 115 */     if (this.srid > 0)
/* 116 */       gc.setSRID(this.srid); 
/* 118 */     if (this.crs != null)
/* 119 */       gc.setUserData(this.crs); 
/* 121 */     return gc;
/*     */   }
/*     */   
/*     */   public GeometryCollection collectInternal() {
/* 127 */     if (this.geometries.isEmpty())
/* 128 */       return new GeometryCollection(null, (this.factory == null) ? new GeometryFactory() : this.factory); 
/* 132 */     GeometryFactory gf = this.factory;
/* 133 */     if (gf == null)
/* 134 */       gf = ((Geometry)this.geometries.get(0)).getFactory(); 
/* 136 */     if (gf == null)
/* 137 */       gf = new GeometryFactory(); 
/* 141 */     Class<MultiPoint> collectionClass = guessCollectionType();
/* 142 */     if (collectionClass == MultiPoint.class) {
/* 143 */       Point[] arrayOfPoint = this.geometries.<Point>toArray(new Point[this.geometries.size()]);
/* 144 */       return (GeometryCollection)gf.createMultiPoint(arrayOfPoint);
/*     */     } 
/* 145 */     if (collectionClass == MultiPolygon.class) {
/* 146 */       Polygon[] arrayOfPolygon = this.geometries.<Polygon>toArray(new Polygon[this.geometries.size()]);
/* 147 */       MultiPolygon mp = gf.createMultiPolygon(arrayOfPolygon);
/* 150 */       if (arrayOfPolygon.length > 1 && !mp.isValid()) {
/* 151 */         Geometry g = mp.buffer(0.0D);
/* 152 */         if (g instanceof Polygon)
/* 153 */           return (GeometryCollection)gf.createMultiPolygon(new Polygon[] { (Polygon)g }); 
/* 155 */         return (GeometryCollection)g;
/*     */       } 
/* 158 */       return (GeometryCollection)mp;
/*     */     } 
/* 160 */     if (collectionClass == MultiLineString.class) {
/* 161 */       LineString[] arrayOfLineString = this.geometries.<LineString>toArray(new LineString[this.geometries.size()]);
/* 163 */       return (GeometryCollection)gf.createMultiLineString(arrayOfLineString);
/*     */     } 
/* 165 */     Geometry[] array = this.geometries.<Geometry>toArray(new Geometry[this.geometries.size()]);
/* 166 */     return gf.createGeometryCollection(array);
/*     */   }
/*     */   
/*     */   private Class guessCollectionType() {
/* 172 */     if (this.geometries == null || this.geometries.size() == 0)
/* 173 */       return GeometryCollection.class; 
/* 177 */     Class<?> result = baseType(((Geometry)this.geometries.get(0)).getClass());
/* 178 */     for (int i = 1; i < this.geometries.size(); i++) {
/* 179 */       Class<?> curr = ((Geometry)this.geometries.get(i)).getClass();
/* 180 */       if (curr != result && !result.isAssignableFrom(curr))
/* 181 */         return GeometryCollection.class; 
/*     */     } 
/* 186 */     if (result == Point.class)
/* 187 */       return MultiPoint.class; 
/* 188 */     if (result == LineString.class)
/* 189 */       return MultiLineString.class; 
/* 190 */     if (result == Polygon.class)
/* 191 */       return MultiPolygon.class; 
/* 193 */     return GeometryCollection.class;
/*     */   }
/*     */   
/*     */   private Class baseType(Class<?> geometry) {
/* 199 */     if (Polygon.class.isAssignableFrom(geometry))
/* 200 */       return Polygon.class; 
/* 201 */     if (LineString.class.isAssignableFrom(geometry))
/* 202 */       return LineString.class; 
/* 203 */     if (Point.class.isAssignableFrom(geometry))
/* 204 */       return Point.class; 
/* 206 */     return geometry;
/*     */   }
/*     */   
/*     */   public void add(Geometry g) {
/* 217 */     if (g == null)
/*     */       return; 
/* 221 */     initCRS(g);
/* 222 */     if (g instanceof GeometryCollection) {
/* 223 */       GeometryCollection gc = (GeometryCollection)g;
/* 224 */       for (int i = 0; i < gc.getNumGeometries(); i++)
/* 225 */         add(gc.getGeometryN(i)); 
/*     */     } else {
/* 228 */       this.coordinates += g.getNumPoints();
/* 229 */       if (this.maxCoordinates > 0L && this.coordinates > this.maxCoordinates)
/* 230 */         throw new IllegalStateException("Max number of collected ordinates has been exceeded. Current count is " + this.coordinates + ", max count is " + this.maxCoordinates); 
/* 237 */       if (this.factory != null)
/* 238 */         g = this.factory.createGeometry(g); 
/* 240 */       this.geometries.add(g);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initCRS(Geometry g) {
/* 246 */     if (this.crs == null && g.getUserData() instanceof CoordinateReferenceSystem)
/* 247 */       this.crs = (CoordinateReferenceSystem)g.getUserData(); 
/* 249 */     if (this.srid == -1 && g.getSRID() > 0)
/* 250 */       this.srid = g.getSRID(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\GeometryCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */