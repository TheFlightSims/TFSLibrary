/*     */ package org.geotools.util;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.io.WKTReader;
/*     */ import org.geotools.factory.Hints;
/*     */ 
/*     */ public class GeometryConverterFactory implements ConverterFactory {
/*     */   public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
/*  50 */     if (Geometry.class.isAssignableFrom(target)) {
/*  53 */       if (String.class.equals(source))
/*  54 */         return new Converter() {
/*     */             public Object convert(Object source, Class target) throws Exception {
/*  56 */               return (new WKTReader()).read((String)source);
/*     */             }
/*     */           }; 
/*  62 */       if (Envelope.class.isAssignableFrom(source))
/*  63 */         return new Converter() {
/*     */             public Object convert(Object source, Class target) throws Exception {
/*  65 */               Envelope e = (Envelope)source;
/*  66 */               GeometryFactory factory = new GeometryFactory();
/*  67 */               return factory.createPolygon(factory.createLinearRing(new Coordinate[] { new Coordinate(e.getMinX(), e.getMinY()), new Coordinate(e.getMaxX(), e.getMinY()), new Coordinate(e.getMaxX(), e.getMaxY()), new Coordinate(e.getMinX(), e.getMaxY()), new Coordinate(e.getMinX(), e.getMinY()) }), null);
/*     */             }
/*     */           }; 
/*     */     } 
/*  83 */     if (Geometry.class.isAssignableFrom(source)) {
/*  85 */       if (Envelope.class.equals(target))
/*  86 */         return new Converter() {
/*     */             public Object convert(Object source, Class target) throws Exception {
/*  88 */               Geometry geometry = (Geometry)source;
/*  89 */               return geometry.getEnvelopeInternal();
/*     */             }
/*     */           }; 
/*  95 */       if (String.class.equals(target))
/*  96 */         return new Converter() {
/*     */             public Object convert(Object source, Class target) throws Exception {
/*  98 */               Geometry geometry = (Geometry)source;
/*  99 */               return geometry.toText();
/*     */             }
/*     */           }; 
/*     */     } 
/* 105 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\GeometryConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */