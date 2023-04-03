/*     */ package org.geotools.gml.producer;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.impl.PackedCoordinateSequence;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.geotools.xml.transform.TransformerBase;
/*     */ import org.geotools.xml.transform.Translator;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ import org.xml.sax.helpers.NamespaceSupport;
/*     */ 
/*     */ public class GeometryTransformer extends TransformerBase {
/*  61 */   static final Logger LOGGER = Logging.getLogger(GeometryTransformer.class);
/*     */   
/*     */   protected boolean useDummyZ = false;
/*     */   
/*  65 */   protected int numDecimals = 4;
/*     */   
/*     */   public void setUseDummyZ(boolean flag) {
/*  68 */     this.useDummyZ = flag;
/*     */   }
/*     */   
/*     */   public void setNumDecimals(int num) {
/*  72 */     this.numDecimals = num;
/*     */   }
/*     */   
/*     */   public Translator createTranslator(ContentHandler handler) {
/*  80 */     return (Translator)new GeometryTranslator(handler, this.numDecimals, this.useDummyZ);
/*     */   }
/*     */   
/*     */   public static class GeometryTranslator extends TransformerBase.TranslatorSupport {
/*  84 */     protected CoordinateWriter coordWriter = new CoordinateWriter();
/*     */     
/*     */     public GeometryTranslator(ContentHandler handler) {
/*  87 */       this(handler, "gml", "http://www.opengis.net/gml");
/*     */     }
/*     */     
/*     */     public GeometryTranslator(ContentHandler handler, String prefix, String nsUri) {
/*  91 */       super(handler, prefix, nsUri);
/*  92 */       this.coordWriter.setPrefix(prefix);
/*  93 */       this.coordWriter.setNamespaceUri(nsUri);
/*     */     }
/*     */     
/*     */     public GeometryTranslator(ContentHandler handler, int numDecimals) {
/*  97 */       this(handler, "gml", "http://www.opengis.net/gml", numDecimals);
/*     */     }
/*     */     
/*     */     public GeometryTranslator(ContentHandler handler, String prefix, String nsUri, int numDecimals) {
/* 101 */       this(handler, prefix, nsUri);
/* 102 */       this.coordWriter = new CoordinateWriter(numDecimals, false);
/* 103 */       this.coordWriter.setPrefix(prefix);
/* 104 */       this.coordWriter.setNamespaceUri(nsUri);
/*     */     }
/*     */     
/*     */     public GeometryTranslator(ContentHandler handler, int numDecimals, boolean isDummyZEnabled) {
/* 108 */       this(handler, "gml", "http://www.opengis.net/gml", numDecimals, isDummyZEnabled);
/*     */     }
/*     */     
/*     */     public GeometryTranslator(ContentHandler handler, String prefix, String nsUri, int numDecimals, boolean isDummyZEnabled) {
/* 112 */       this(handler, prefix, nsUri);
/* 113 */       this.coordWriter = new CoordinateWriter(numDecimals, isDummyZEnabled);
/* 114 */       this.coordWriter.setPrefix(prefix);
/* 115 */       this.coordWriter.setNamespaceUri(nsUri);
/*     */     }
/*     */     
/*     */     public GeometryTranslator(ContentHandler handler, String prefix, String nsUri, int numDecimals, boolean isDummyZEnabled, int dimension) {
/* 129 */       this(handler, prefix, nsUri);
/* 130 */       this.coordWriter = new CoordinateWriter(numDecimals, isDummyZEnabled, dimension);
/* 131 */       this.coordWriter.setPrefix(prefix);
/* 132 */       this.coordWriter.setNamespaceUri(nsUri);
/*     */     }
/*     */     
/*     */     public boolean isDummyZEnabled() {
/* 135 */       return this.coordWriter.isDummyZEnabled();
/*     */     }
/*     */     
/*     */     public int getNumDecimals() {
/* 139 */       return this.coordWriter.getNumDecimals();
/*     */     }
/*     */     
/*     */     public void encode(Object o, String srsName) throws IllegalArgumentException {
/* 144 */       if (o instanceof Geometry) {
/* 145 */         encode((Geometry)o, srsName);
/*     */       } else {
/* 147 */         throw new IllegalArgumentException("Unable to encode " + o);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void encode(Object o) throws IllegalArgumentException {
/* 152 */       encode(o, (String)null);
/*     */     }
/*     */     
/*     */     public void encode(Envelope bounds) {
/* 156 */       encode(bounds, (String)null);
/*     */     }
/*     */     
/*     */     public void encode(Envelope bounds, String srsName) {
/* 168 */       if (bounds == null || bounds.isNull()) {
/* 169 */         encodeNullBounds();
/*     */         return;
/*     */       } 
/* 173 */       String boxName = boxName();
/* 175 */       if (srsName == null || srsName.equals("")) {
/* 176 */         start(boxName);
/*     */       } else {
/* 178 */         AttributesImpl atts = new AttributesImpl();
/* 179 */         atts.addAttribute("", "srsName", "srsName", "", srsName);
/* 180 */         start(boxName, atts);
/*     */       } 
/*     */       try {
/* 184 */         double[] coords = new double[4];
/* 185 */         coords[0] = bounds.getMinX();
/* 186 */         coords[1] = bounds.getMinY();
/* 187 */         coords[2] = bounds.getMaxX();
/* 188 */         coords[3] = bounds.getMaxY();
/* 189 */         PackedCoordinateSequence.Double double_ = new PackedCoordinateSequence.Double(coords, 2);
/* 190 */         this.coordWriter.writeCoordinates((CoordinateSequence)double_, this.contentHandler);
/* 191 */       } catch (SAXException se) {
/* 192 */         throw new RuntimeException(se);
/*     */       } 
/* 195 */       end(boxName);
/*     */     }
/*     */     
/*     */     protected void encodeNullBounds() {
/* 202 */       start("null");
/* 203 */       String text = "unknown";
/*     */       try {
/* 205 */         this.contentHandler.characters(text.toCharArray(), 0, text.length());
/* 206 */       } catch (Exception e) {
/* 208 */         System.out.println("got exception while writing null boundedby:" + e.getLocalizedMessage());
/* 209 */         e.printStackTrace();
/*     */       } 
/* 211 */       end("null");
/*     */     }
/*     */     
/*     */     protected String boxName() {
/* 219 */       return "Box";
/*     */     }
/*     */     
/*     */     public void encode(Geometry geometry) {
/* 226 */       String srsName = null;
/* 228 */       if (geometry.getUserData() instanceof CoordinateReferenceSystem)
/*     */         try {
/* 230 */           CoordinateReferenceSystem crs = (CoordinateReferenceSystem)geometry.getUserData();
/* 231 */           Integer code = CRS.lookupEpsgCode(crs, false);
/* 232 */           if (code != null)
/* 233 */             if (CRS.AxisOrder.NORTH_EAST.equals(CRS.getAxisOrder(crs))) {
/* 234 */               srsName = "urn:ogc:def:crs:EPSG::" + code;
/*     */             } else {
/* 236 */               srsName = "EPSG:" + code;
/*     */             }  
/* 239 */         } catch (Exception e) {
/* 240 */           GeometryTransformer.LOGGER.fine("Failed to encode the CoordinateReferenceSystem into a srsName");
/*     */         }  
/* 243 */       encode(geometry, srsName);
/*     */     }
/*     */     
/*     */     public void encode(Geometry geometry, String srsName) {
/* 251 */       encode(geometry, srsName, 2);
/*     */     }
/*     */     
/*     */     public void encode(Geometry geometry, String srsName, int dimensions) {
/*     */       CoordinateSequence coordSeq;
/* 262 */       String geomName = GMLUtils.getGeometryName(geometry);
/* 264 */       if (srsName == null || srsName.equals("")) {
/* 265 */         start(geomName);
/*     */       } else {
/* 267 */         AttributesImpl atts = new AttributesImpl();
/* 268 */         atts.addAttribute("", "srsName", "srsName", "", srsName);
/* 269 */         start(geomName, atts);
/*     */       } 
/* 272 */       int geometryType = GMLUtils.getGeometryType(geometry);
/* 275 */       switch (geometryType) {
/*     */         case 1:
/* 277 */           coordSeq = ((Point)geometry).getCoordinateSequence();
/*     */           try {
/* 279 */             this.coordWriter.writeCoordinates(coordSeq, this.contentHandler);
/* 280 */           } catch (SAXException e) {
/* 281 */             throw new RuntimeException(e);
/*     */           } 
/*     */           break;
/*     */         case 2:
/* 285 */           coordSeq = ((LineString)geometry).getCoordinateSequence();
/*     */           try {
/* 287 */             this.coordWriter.writeCoordinates(coordSeq, this.contentHandler);
/* 288 */           } catch (SAXException s) {
/* 289 */             throw new RuntimeException(s);
/*     */           } 
/*     */           break;
/*     */         case 3:
/* 295 */           writePolygon((Polygon)geometry);
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/*     */         case 6:
/*     */         case 7:
/* 303 */           writeMulti((GeometryCollection)geometry, GMLUtils.getMemberName(geometryType));
/*     */           break;
/*     */       } 
/* 309 */       end(geomName);
/*     */     }
/*     */     
/*     */     private void writePolygon(Polygon geometry) {
/* 312 */       String outBound = "outerBoundaryIs";
/* 313 */       String lineRing = "LinearRing";
/* 314 */       String inBound = "innerBoundaryIs";
/* 315 */       start(outBound);
/* 316 */       start(lineRing);
/*     */       try {
/* 320 */         CoordinateSequence coordSeq = geometry.getExteriorRing().getCoordinateSequence();
/* 321 */         this.coordWriter.writeCoordinates(coordSeq, this.contentHandler);
/* 322 */       } catch (SAXException s) {
/* 323 */         throw new RuntimeException(s);
/*     */       } 
/* 326 */       end(lineRing);
/* 327 */       end(outBound);
/* 329 */       for (int i = 0, ii = geometry.getNumInteriorRing(); i < ii; i++) {
/* 330 */         start(inBound);
/* 331 */         start(lineRing);
/*     */         try {
/* 334 */           CoordinateSequence coordinateSequence = geometry.getInteriorRingN(i).getCoordinateSequence();
/* 335 */           this.coordWriter.writeCoordinates(coordinateSequence, this.contentHandler);
/* 336 */         } catch (SAXException s) {
/* 337 */           throw new RuntimeException(s);
/*     */         } 
/* 340 */         end(lineRing);
/* 341 */         end(inBound);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void writeMulti(GeometryCollection geometry, String member) {
/* 346 */       for (int i = 0, n = geometry.getNumGeometries(); i < n; i++) {
/* 347 */         start(member);
/* 349 */         encode(geometry.getGeometryN(i));
/* 351 */         end(member);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\producer\GeometryTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */