/*     */ package org.geotools.geometry.text;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.io.StringReader;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.geotools.geometry.GeometryBuilder;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.Geometry;
/*     */ import org.opengis.geometry.PositionFactory;
/*     */ import org.opengis.geometry.aggregate.AggregateFactory;
/*     */ import org.opengis.geometry.aggregate.MultiPrimitive;
/*     */ import org.opengis.geometry.coordinate.GeometryFactory;
/*     */ import org.opengis.geometry.coordinate.LineString;
/*     */ import org.opengis.geometry.coordinate.Position;
/*     */ import org.opengis.geometry.primitive.Curve;
/*     */ import org.opengis.geometry.primitive.Point;
/*     */ import org.opengis.geometry.primitive.PrimitiveFactory;
/*     */ import org.opengis.geometry.primitive.Ring;
/*     */ import org.opengis.geometry.primitive.Surface;
/*     */ import org.opengis.geometry.primitive.SurfaceBoundary;
/*     */ 
/*     */ public class WKTParser {
/*     */   private static final String EMPTY = "EMPTY";
/*     */   
/*     */   private static final String COMMA = ",";
/*     */   
/*     */   private static final String L_PAREN = "(";
/*     */   
/*     */   private static final String R_PAREN = ")";
/*     */   
/*     */   private GeometryFactory geometryFactory;
/*     */   
/*     */   private PrimitiveFactory primitiveFactory;
/*     */   
/*     */   private PositionFactory positionFactory;
/*     */   
/*     */   private AggregateFactory aggregateFactory;
/*     */   
/*     */   public WKTParser(GeometryBuilder builder) {
/* 110 */     this(builder.getGeometryFactory(), builder.getPrimitiveFactory(), builder.getPositionFactory(), builder.getAggregateFactory());
/*     */   }
/*     */   
/*     */   public WKTParser(GeometryFactory geometryFactory, PrimitiveFactory primitiveFactory, PositionFactory positionFactory, AggregateFactory aggregateFactory) {
/* 123 */     this.geometryFactory = geometryFactory;
/* 124 */     this.primitiveFactory = primitiveFactory;
/* 125 */     this.positionFactory = positionFactory;
/* 126 */     this.aggregateFactory = aggregateFactory;
/*     */   }
/*     */   
/*     */   public void setFactory(GeometryFactory factory) {
/* 135 */     this.geometryFactory = factory;
/*     */   }
/*     */   
/*     */   public void setFactory(PrimitiveFactory factory) {
/* 145 */     this.primitiveFactory = factory;
/*     */   }
/*     */   
/*     */   public void setFactory(PositionFactory factory) {
/* 154 */     this.positionFactory = factory;
/*     */   }
/*     */   
/*     */   public Geometry parse(String text) throws ParseException {
/* 165 */     return read(new StringReader(text));
/*     */   }
/*     */   
/*     */   public Geometry read(Reader reader) throws ParseException {
/* 178 */     StreamTokenizer tokenizer = new StreamTokenizer(reader);
/* 179 */     setUpTokenizer(tokenizer);
/*     */     try {
/* 182 */       return readGeometryTaggedText(tokenizer);
/* 183 */     } catch (IOException e) {
/* 184 */       throw new ParseException(e.toString(), tokenizer.lineno());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setUpTokenizer(StreamTokenizer tokenizer) {
/* 194 */     int char128 = 128;
/* 195 */     int skip32 = 32;
/* 196 */     int char255 = 255;
/* 198 */     tokenizer.resetSyntax();
/* 199 */     tokenizer.wordChars(97, 122);
/* 200 */     tokenizer.wordChars(65, 90);
/* 201 */     tokenizer.wordChars(160, 255);
/* 202 */     tokenizer.wordChars(48, 57);
/* 203 */     tokenizer.wordChars(45, 45);
/* 204 */     tokenizer.wordChars(43, 43);
/* 205 */     tokenizer.wordChars(46, 46);
/* 206 */     tokenizer.whitespaceChars(0, 32);
/* 207 */     tokenizer.commentChar(35);
/*     */   }
/*     */   
/*     */   private Geometry readGeometryTaggedText(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 224 */     String type = getNextWord(tokenizer);
/* 225 */     if (type.equals("POINT"))
/* 226 */       return (Geometry)readPointText(tokenizer); 
/* 227 */     if (type.equalsIgnoreCase("LINESTRING"))
/* 228 */       return (Geometry)readLineStringText(tokenizer); 
/* 229 */     if (type.equalsIgnoreCase("LINEARRING"))
/* 230 */       return (Geometry)readLinearRingText(tokenizer); 
/* 231 */     if (type.equalsIgnoreCase("POLYGON"))
/* 232 */       return (Geometry)readPolygonText(tokenizer); 
/* 233 */     if (type.equalsIgnoreCase("MULTIPOINT"))
/* 234 */       return (Geometry)readMultiPointText(tokenizer); 
/* 235 */     if (type.equalsIgnoreCase("MULTIPOLYGON"))
/* 236 */       return (Geometry)readMultiPolygonText(tokenizer); 
/* 237 */     if (type.equalsIgnoreCase("GEOMETRYCOLLECTION"))
/* 238 */       return (Geometry)readGeometryCollectionText(tokenizer); 
/* 239 */     if (type.equalsIgnoreCase("MULTILINESTRING"))
/* 240 */       return (Geometry)readMultiLineStringText(tokenizer); 
/* 242 */     throw new ParseException("Unknown geometry type: " + type, tokenizer.lineno());
/*     */   }
/*     */   
/*     */   private List getCoordinates(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 257 */     String nextToken = getNextEmptyOrOpener(tokenizer);
/* 258 */     List<DirectPosition> coordinates = new ArrayList();
/* 259 */     if (!nextToken.equals("EMPTY")) {
/* 260 */       coordinates.add(getPreciseCoordinate(tokenizer));
/* 261 */       nextToken = getNextCloserOrComma(tokenizer);
/* 262 */       while (nextToken.equals(",")) {
/* 263 */         coordinates.add(getPreciseCoordinate(tokenizer));
/* 264 */         nextToken = getNextCloserOrComma(tokenizer);
/*     */       } 
/*     */     } 
/* 267 */     return coordinates;
/*     */   }
/*     */   
/*     */   private DirectPosition getPreciseCoordinate(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 280 */     DirectPosition pos = this.geometryFactory.createDirectPosition();
/* 281 */     pos.setOrdinate(0, getNextNumber(tokenizer));
/* 282 */     pos.setOrdinate(1, getNextNumber(tokenizer));
/* 283 */     if (isNumberNext(tokenizer))
/* 284 */       pos.setOrdinate(1, getNextNumber(tokenizer)); 
/* 286 */     return pos;
/*     */   }
/*     */   
/*     */   private boolean isNumberNext(StreamTokenizer tokenizer) throws IOException {
/* 291 */     int type = tokenizer.nextToken();
/* 292 */     tokenizer.pushBack();
/* 293 */     return (type == -3);
/*     */   }
/*     */   
/*     */   private double getNextNumber(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 308 */     int type = tokenizer.nextToken();
/* 309 */     switch (type) {
/*     */       case -3:
/*     */         try {
/* 312 */           return Double.parseDouble(tokenizer.sval);
/* 313 */         } catch (NumberFormatException ex) {
/* 314 */           throw new ParseException("Invalid number: " + tokenizer.sval, tokenizer.lineno());
/*     */         } 
/*     */     } 
/* 319 */     parseError("number", tokenizer);
/* 320 */     return 0.0D;
/*     */   }
/*     */   
/*     */   private String getNextEmptyOrOpener(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 334 */     String nextWord = getNextWord(tokenizer);
/* 335 */     if (nextWord.equals("EMPTY") || nextWord.equals("("))
/* 336 */       return nextWord; 
/* 338 */     parseError("EMPTY or (", tokenizer);
/* 339 */     return null;
/*     */   }
/*     */   
/*     */   private String getNextCloserOrComma(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 352 */     String nextWord = getNextWord(tokenizer);
/* 353 */     if (nextWord.equals(",") || nextWord.equals(")"))
/* 354 */       return nextWord; 
/* 356 */     parseError(", or )", tokenizer);
/* 357 */     return null;
/*     */   }
/*     */   
/*     */   private String getNextCloser(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 370 */     String nextWord = getNextWord(tokenizer);
/* 371 */     if (nextWord.equals(")"))
/* 372 */       return nextWord; 
/* 374 */     parseError(")", tokenizer);
/* 375 */     return null;
/*     */   }
/*     */   
/*     */   private String getNextWord(StreamTokenizer tokenizer) throws IOException, ParseException {
/*     */     String word;
/* 388 */     int type = tokenizer.nextToken();
/* 390 */     switch (type) {
/*     */       case -3:
/* 392 */         word = tokenizer.sval;
/* 393 */         if (word.equalsIgnoreCase("EMPTY"))
/* 394 */           String str = "EMPTY"; 
/* 396 */         value = word;
/* 412 */         return value;
/*     */       case 40:
/*     */         value = "(";
/* 412 */         return value;
/*     */       case 41:
/*     */         value = ")";
/* 412 */         return value;
/*     */       case 44:
/*     */         value = ",";
/* 412 */         return value;
/*     */     } 
/*     */     parseError("word", tokenizer);
/*     */     String value = null;
/* 412 */     return value;
/*     */   }
/*     */   
/*     */   private void parseError(String expected, StreamTokenizer tokenizer) throws ParseException {
/* 423 */     String tokenStr = tokenString(tokenizer);
/* 424 */     throw new ParseException("Expected " + expected + " but found " + tokenStr, 0);
/*     */   }
/*     */   
/*     */   private String tokenString(StreamTokenizer tokenizer) {
/* 433 */     switch (tokenizer.ttype) {
/*     */       case -2:
/* 435 */         return "<NUMBER>";
/*     */       case 10:
/* 437 */         return "End-of-Line";
/*     */       case -1:
/* 439 */         return "End-of-Stream";
/*     */       case -3:
/* 441 */         return "'" + tokenizer.sval + "'";
/*     */     } 
/* 444 */     return "'" + (char)tokenizer.ttype + "'";
/*     */   }
/*     */   
/*     */   private Point readPointText(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 458 */     String nextToken = getNextEmptyOrOpener(tokenizer);
/* 459 */     if (nextToken.equals("EMPTY"))
/* 460 */       return this.primitiveFactory.createPoint(new double[2]); 
/* 462 */     Point point = this.primitiveFactory.createPoint((Position)getPreciseCoordinate(tokenizer));
/* 463 */     getNextCloser(tokenizer);
/* 464 */     return point;
/*     */   }
/*     */   
/*     */   private Curve readLineStringText(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 478 */     List coordList = getCoordinates(tokenizer);
/* 479 */     LineString lineString = this.geometryFactory.createLineString(coordList);
/* 480 */     List<LineString> curveSegmentList = Collections.singletonList(lineString);
/* 481 */     Curve curve = this.primitiveFactory.createCurve(curveSegmentList);
/* 482 */     return curve;
/*     */   }
/*     */   
/*     */   private Curve readLinearRingText(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 499 */     List coordList = getCoordinates(tokenizer);
/* 500 */     LineString lineString = this.geometryFactory.createLineString(coordList);
/* 501 */     List<LineString> curveSegmentList = Collections.singletonList(lineString);
/* 502 */     Curve curve = this.primitiveFactory.createCurve(curveSegmentList);
/* 503 */     return curve;
/*     */   }
/*     */   
/*     */   private List toPoints(List<Point> coordinates) {
/* 517 */     List<Position> points = new ArrayList();
/* 518 */     for (int i = 0; i < coordinates.size(); i++)
/* 519 */       points.add(this.positionFactory.createPosition((Position)coordinates.get(i))); 
/* 521 */     return points;
/*     */   }
/*     */   
/*     */   private Surface readPolygonText(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 536 */     String nextToken = getNextEmptyOrOpener(tokenizer);
/* 537 */     if (nextToken.equals("EMPTY"))
/* 538 */       return null; 
/* 540 */     Curve curve = readLinearRingText(tokenizer);
/* 541 */     List<Curve> curveList = Collections.singletonList(curve);
/* 542 */     Ring shell = this.primitiveFactory.createRing(curveList);
/* 544 */     List<Ring> holes = new ArrayList();
/* 545 */     nextToken = getNextCloserOrComma(tokenizer);
/* 546 */     while (nextToken.equals(",")) {
/* 547 */       Curve holecurve = readLinearRingText(tokenizer);
/* 548 */       List<Curve> holeList = Collections.singletonList(holecurve);
/* 549 */       Ring hole = this.primitiveFactory.createRing(holeList);
/* 551 */       holes.add(hole);
/* 552 */       nextToken = getNextCloserOrComma(tokenizer);
/*     */     } 
/* 554 */     SurfaceBoundary sb = this.primitiveFactory.createSurfaceBoundary(shell, holes);
/* 555 */     return this.primitiveFactory.createSurface(sb);
/*     */   }
/*     */   
/*     */   private MultiPrimitive readMultiPolygonText(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 571 */     String nextToken = getNextEmptyOrOpener(tokenizer);
/* 572 */     if (nextToken.equals("EMPTY"))
/* 573 */       return null; 
/* 575 */     MultiPrimitive multi = this.geometryFactory.createMultiPrimitive();
/* 576 */     Surface surface = readPolygonText(tokenizer);
/* 578 */     Set<Surface> elements = multi.getElements();
/* 579 */     elements.add(surface);
/* 580 */     nextToken = getNextCloserOrComma(tokenizer);
/* 581 */     while (nextToken.equals(",")) {
/* 582 */       surface = readPolygonText(tokenizer);
/* 584 */       elements.add(surface);
/* 585 */       nextToken = getNextCloserOrComma(tokenizer);
/*     */     } 
/* 587 */     return multi;
/*     */   }
/*     */   
/*     */   private MultiPrimitive readMultiPointText(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 603 */     String nextToken = getNextEmptyOrOpener(tokenizer);
/* 604 */     if (nextToken.equals("EMPTY"))
/* 605 */       return null; 
/* 607 */     MultiPrimitive multi = this.geometryFactory.createMultiPrimitive();
/* 608 */     Point point = this.primitiveFactory.createPoint((Position)getPreciseCoordinate(tokenizer));
/* 610 */     Set<Point> elements = multi.getElements();
/* 611 */     elements.add(point);
/* 612 */     nextToken = getNextCloserOrComma(tokenizer);
/* 613 */     while (nextToken.equals(",")) {
/* 614 */       point = this.primitiveFactory.createPoint((Position)getPreciseCoordinate(tokenizer));
/* 616 */       elements.add(point);
/* 617 */       nextToken = getNextCloserOrComma(tokenizer);
/*     */     } 
/* 619 */     return multi;
/*     */   }
/*     */   
/*     */   private MultiPrimitive readGeometryCollectionText(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 633 */     String nextToken = getNextEmptyOrOpener(tokenizer);
/* 634 */     if (nextToken.equals("EMPTY"))
/* 635 */       return null; 
/* 637 */     MultiPrimitive multi = this.geometryFactory.createMultiPrimitive();
/* 638 */     Geometry geom = readGeometryTaggedText(tokenizer);
/* 640 */     Set<Geometry> elements = multi.getElements();
/* 641 */     elements.add(geom);
/* 642 */     nextToken = getNextCloserOrComma(tokenizer);
/* 643 */     while (nextToken.equals(",")) {
/* 644 */       geom = readGeometryTaggedText(tokenizer);
/* 646 */       elements.add(geom);
/* 647 */       nextToken = getNextCloserOrComma(tokenizer);
/*     */     } 
/* 649 */     return multi;
/*     */   }
/*     */   
/*     */   private MultiPrimitive readMultiLineStringText(StreamTokenizer tokenizer) throws IOException, ParseException {
/* 663 */     String nextToken = getNextEmptyOrOpener(tokenizer);
/* 664 */     if (nextToken.equals("EMPTY"))
/* 665 */       return null; 
/* 667 */     MultiPrimitive multi = this.geometryFactory.createMultiPrimitive();
/* 668 */     Curve curve = readLineStringText(tokenizer);
/* 670 */     Set<Curve> elements = multi.getElements();
/* 671 */     elements.add(curve);
/* 672 */     nextToken = getNextCloserOrComma(tokenizer);
/* 673 */     while (nextToken.equals(",")) {
/* 674 */       curve = readLineStringText(tokenizer);
/* 676 */       elements.add(curve);
/* 677 */       nextToken = getNextCloserOrComma(tokenizer);
/*     */     } 
/* 679 */     return multi;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\text\WKTParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */