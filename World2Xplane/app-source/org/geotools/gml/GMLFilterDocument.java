/*     */ package org.geotools.gml;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.XMLFilterImpl;
/*     */ 
/*     */ public class GMLFilterDocument extends XMLFilterImpl {
/*  53 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.gml");
/*     */   
/*     */   private static final String GML_NAMESPACE = "http://www.opengis.net/gml";
/*     */   
/*     */   private static final String COORD_NAME = "coord";
/*     */   
/*     */   private static final String COORDINATES_NAME = "coordinates";
/*     */   
/*     */   private static final String X_NAME = "X";
/*     */   
/*     */   private static final String Y_NAME = "Y";
/*     */   
/*     */   private static final String Z_NAME = "Z";
/*     */   
/*  76 */   private static final Collection SUB_GEOMETRY_TYPES = new Vector(Arrays.asList((Object[])new String[] { "outerBoundaryIs", "innerBoundaryIs" }));
/*     */   
/*  80 */   private static final Collection BASE_GEOMETRY_TYPES = new Vector(Arrays.asList((Object[])new String[] { "Point", "LineString", "Polygon", "LinearRing", "Box", "MultiPoint", "MultiLineString", "MultiPolygon", "GeometryCollection" }));
/*     */   
/*  88 */   private StringBuffer buffer = new StringBuffer();
/*     */   
/*     */   private GMLHandlerGeometry parent;
/*     */   
/*  94 */   private CoordinateReader coordinateReader = new CoordinateReader();
/*     */   
/*     */   private boolean namespaceAware = true;
/*     */   
/*     */   public GMLFilterDocument(GMLHandlerGeometry parent) {
/* 106 */     LOGGER.entering("GMLFilterDocument", "new", parent);
/* 107 */     this.parent = parent;
/* 108 */     LOGGER.exiting("GMLFilterDocument", "new");
/*     */   }
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 132 */     LOGGER.entering("GMLFilterDocument", "startElement", new Object[] { namespaceURI, localName, qName, atts });
/* 138 */     if (namespaceURI != null && namespaceURI.equals("http://www.opengis.net/gml")) {
/* 140 */       if (BASE_GEOMETRY_TYPES.contains(localName)) {
/* 141 */         this.parent.geometryStart(localName, atts);
/* 142 */       } else if (SUB_GEOMETRY_TYPES.contains(localName)) {
/* 143 */         this.parent.geometrySub(localName);
/* 144 */       } else if ("coordinates".equals(localName)) {
/* 146 */         this.coordinateReader.insideCoordinates(true, atts);
/* 147 */         this.buffer = new StringBuffer();
/* 148 */       } else if ("coord".equals(localName)) {
/* 149 */         this.coordinateReader.insideCoord(true);
/* 150 */         this.buffer = new StringBuffer();
/* 151 */       } else if ("X".equals(localName)) {
/* 152 */         this.buffer = new StringBuffer();
/* 153 */         this.coordinateReader.insideX(true);
/* 154 */       } else if ("Y".equals(localName)) {
/* 155 */         this.buffer = new StringBuffer();
/* 156 */         this.coordinateReader.insideY(true);
/* 157 */       } else if ("Z".equals(localName)) {
/* 158 */         this.buffer = new StringBuffer();
/* 159 */         this.coordinateReader.insideZ(true);
/*     */       } else {
/* 161 */         this.parent.startElement(namespaceURI, localName, qName, atts);
/*     */       } 
/*     */     } else {
/* 167 */       this.parent.startElement(namespaceURI, localName, qName, atts);
/*     */     } 
/* 170 */     LOGGER.exiting("GMLFilterDocument", "startElement");
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 195 */     LOGGER.entering("GMLFilterDocument", "characters", new Object[] { ch, new Integer(start), new Integer(length) });
/* 202 */     String rawCoordinates = new String(ch, start, length);
/* 207 */     if (this.coordinateReader.insideCoordinates()) {
/* 208 */       this.buffer.append(rawCoordinates);
/* 211 */     } else if (this.coordinateReader.insideCoord()) {
/* 212 */       this.buffer.append(rawCoordinates);
/*     */     } else {
/* 219 */       this.parent.characters(ch, start, length);
/*     */     } 
/* 222 */     LOGGER.exiting("GMLFilterDocument", "characters");
/*     */   }
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/* 246 */     LOGGER.entering("GMLFilterDocument", "endElement", new Object[] { namespaceURI, localName, qName });
/* 252 */     if (namespaceURI.equals("http://www.opengis.net/gml") || !this.namespaceAware) {
/* 254 */       if (BASE_GEOMETRY_TYPES.contains(localName)) {
/* 255 */         this.parent.geometryEnd(localName);
/* 256 */       } else if (SUB_GEOMETRY_TYPES.contains(localName)) {
/* 257 */         this.parent.geometrySub(localName);
/* 258 */       } else if ("coordinates".equals(localName)) {
/* 261 */         this.coordinateReader.readCoordinates(this.buffer.toString());
/* 262 */         this.coordinateReader.insideCoordinates(false);
/* 263 */       } else if ("coord".equals(localName)) {
/* 264 */         this.coordinateReader.readCoord(this.buffer.toString());
/* 265 */         this.coordinateReader.insideCoord(false);
/* 266 */       } else if ("X".equals(localName)) {
/* 267 */         this.coordinateReader.readCoord(this.buffer.toString());
/* 268 */         this.coordinateReader.insideX(false);
/* 269 */       } else if ("Y".equals(localName)) {
/* 270 */         this.coordinateReader.readCoord(this.buffer.toString());
/* 271 */         this.coordinateReader.insideY(false);
/* 272 */       } else if ("Z".equals(localName)) {
/* 273 */         this.coordinateReader.readCoord(this.buffer.toString());
/* 274 */         this.coordinateReader.insideZ(false);
/* 275 */       } else if (!this.namespaceAware) {
/* 279 */         this.parent.endElement(namespaceURI, localName, qName);
/*     */       } else {
/* 281 */         this.parent.endElement(namespaceURI, localName, qName);
/*     */       } 
/*     */     } else {
/* 289 */       this.parent.endElement(namespaceURI, localName, qName);
/*     */     } 
/* 292 */     LOGGER.exiting("GMLFilterDocument", "endElement");
/*     */   }
/*     */   
/*     */   private class CoordinateReader {
/*     */     private static final int NOT_INSIDE = 0;
/*     */     
/*     */     private static final int INSIDE_COORD = 1;
/*     */     
/*     */     private static final int INSIDE_COORDINATES = 2;
/*     */     
/*     */     private static final int INSIDE_X = 1;
/*     */     
/*     */     private static final int INSIDE_Y = 2;
/*     */     
/*     */     private static final int INSIDE_Z = 3;
/*     */     
/* 327 */     private int insideOuterFlag = 0;
/*     */     
/* 330 */     private int insideInnerFlag = 0;
/*     */     
/* 333 */     private Double x = new Double(Double.NaN);
/*     */     
/* 336 */     private Double y = new Double(Double.NaN);
/*     */     
/* 339 */     private Double z = new Double(Double.NaN);
/*     */     
/* 344 */     private String coordinateDelimeter = ",";
/*     */     
/* 347 */     private String tupleDelimeter = " ";
/*     */     
/* 350 */     private StringBuffer decimalDelimeter = new StringBuffer(".");
/*     */     
/*     */     private boolean standardDecimalFlag = true;
/*     */     
/*     */     public void readCoordinates(String coordinateString) throws SAXException {
/* 378 */       if (!this.standardDecimalFlag)
/* 379 */         coordinateString = coordinateString.replace(this.decimalDelimeter.charAt(0), '.'); 
/* 384 */       StringTokenizer coordinateSets = new StringTokenizer(coordinateString.trim(), this.tupleDelimeter);
/* 391 */       while (coordinateSets.hasMoreElements()) {
/* 392 */         StringTokenizer coordinates = new StringTokenizer(coordinateSets.nextToken(), this.coordinateDelimeter);
/* 394 */         this.x = new Double(coordinates.nextToken().trim());
/* 395 */         this.y = new Double(coordinates.nextToken().trim());
/* 397 */         if (coordinates.hasMoreElements()) {
/* 398 */           this.z = new Double(coordinates.nextToken().trim());
/* 399 */           GMLFilterDocument.this.parent.gmlCoordinates(this.x.doubleValue(), this.y.doubleValue(), this.z.doubleValue());
/*     */           continue;
/*     */         } 
/* 402 */         GMLFilterDocument.this.parent.gmlCoordinates(this.x.doubleValue(), this.y.doubleValue());
/*     */       } 
/*     */     }
/*     */     
/*     */     public void readCoord(String coordString) {
/* 417 */       if (!this.standardDecimalFlag)
/* 418 */         coordString = coordString.replace(this.decimalDelimeter.charAt(0), '.'); 
/* 424 */       switch (this.insideInnerFlag) {
/*     */         case 1:
/* 426 */           this.x = new Double(coordString.trim());
/*     */           break;
/*     */         case 2:
/* 431 */           this.y = new Double(coordString.trim());
/*     */           break;
/*     */         case 3:
/* 436 */           this.z = new Double(coordString.trim());
/*     */           break;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void insideCoordinates(boolean isInside, Attributes atts) {
/* 454 */       insideCoordinates(isInside);
/*     */     }
/*     */     
/*     */     public void insideCoordinates(boolean isInside) {
/* 463 */       if (isInside) {
/* 464 */         this.insideOuterFlag = 2;
/*     */       } else {
/* 466 */         this.insideOuterFlag = 0;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void insideCoord(boolean isInside) throws SAXException {
/* 479 */       if (isInside) {
/* 480 */         this.insideOuterFlag = 1;
/*     */       } else {
/* 486 */         if (!this.x.isNaN() && !this.y.isNaN() && this.z.isNaN()) {
/* 487 */           GMLFilterDocument.this.parent.gmlCoordinates(this.x.doubleValue(), this.y.doubleValue());
/* 488 */         } else if (!this.x.isNaN() && !this.y.isNaN() && !this.z.isNaN()) {
/* 489 */           GMLFilterDocument.this.parent.gmlCoordinates(this.x.doubleValue(), this.y.doubleValue(), this.z.doubleValue());
/*     */         } 
/* 494 */         this.x = new Double(Double.NaN);
/* 495 */         this.y = new Double(Double.NaN);
/* 496 */         this.z = new Double(Double.NaN);
/* 497 */         this.insideOuterFlag = 0;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void insideX(boolean isInside) {
/* 507 */       if (isInside) {
/* 508 */         this.insideInnerFlag = 1;
/*     */       } else {
/* 510 */         this.insideInnerFlag = 0;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void insideY(boolean isInside) {
/* 520 */       if (isInside) {
/* 521 */         this.insideInnerFlag = 2;
/*     */       } else {
/* 523 */         this.insideInnerFlag = 0;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void insideZ(boolean isInside) {
/* 533 */       if (isInside) {
/* 534 */         this.insideInnerFlag = 3;
/*     */       } else {
/* 536 */         this.insideInnerFlag = 0;
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean insideCoordinates() {
/* 546 */       return (this.insideOuterFlag == 2);
/*     */     }
/*     */     
/*     */     public boolean insideCoord() {
/* 555 */       return (this.insideOuterFlag == 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\GMLFilterDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */