/*     */ package com.vividsolutions.jts.io;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class WKTReader {
/*     */   private static final String EMPTY = "EMPTY";
/*     */   
/*     */   private static final String COMMA = ",";
/*     */   
/*     */   private static final String L_PAREN = "(";
/*     */   
/*     */   private static final String R_PAREN = ")";
/*     */   
/*     */   private static final String NAN_SYMBOL = "NaN";
/*     */   
/*     */   private GeometryFactory geometryFactory;
/*     */   
/*     */   private PrecisionModel precisionModel;
/*     */   
/*     */   private StreamTokenizer tokenizer;
/*     */   
/*     */   private static final boolean ALLOW_OLD_JTS_MULTIPOINT_SYNTAX = true;
/*     */   
/*     */   public WKTReader() {
/* 146 */     this(new GeometryFactory());
/*     */   }
/*     */   
/*     */   public WKTReader(GeometryFactory geometryFactory) {
/* 156 */     this.geometryFactory = geometryFactory;
/* 157 */     this.precisionModel = geometryFactory.getPrecisionModel();
/*     */   }
/*     */   
/*     */   public Geometry read(String wellKnownText) throws ParseException {
/* 172 */     StringReader reader = new StringReader(wellKnownText);
/*     */     try {
/* 174 */       return read(reader);
/*     */     } finally {
/* 177 */       reader.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Geometry read(Reader reader) throws ParseException {
/* 191 */     this.tokenizer = new StreamTokenizer(reader);
/* 193 */     this.tokenizer.resetSyntax();
/* 194 */     this.tokenizer.wordChars(97, 122);
/* 195 */     this.tokenizer.wordChars(65, 90);
/* 196 */     this.tokenizer.wordChars(160, 255);
/* 197 */     this.tokenizer.wordChars(48, 57);
/* 198 */     this.tokenizer.wordChars(45, 45);
/* 199 */     this.tokenizer.wordChars(43, 43);
/* 200 */     this.tokenizer.wordChars(46, 46);
/* 201 */     this.tokenizer.whitespaceChars(0, 32);
/* 202 */     this.tokenizer.commentChar(35);
/*     */     try {
/* 205 */       return readGeometryTaggedText();
/* 207 */     } catch (IOException e) {
/* 208 */       throw new ParseException(e.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private Coordinate[] getCoordinates() throws IOException, ParseException {
/* 225 */     String nextToken = getNextEmptyOrOpener();
/* 226 */     if (nextToken.equals("EMPTY"))
/* 227 */       return new Coordinate[0]; 
/* 229 */     ArrayList<Coordinate> coordinates = new ArrayList();
/* 230 */     coordinates.add(getPreciseCoordinate());
/* 231 */     nextToken = getNextCloserOrComma();
/* 232 */     while (nextToken.equals(",")) {
/* 233 */       coordinates.add(getPreciseCoordinate());
/* 234 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 236 */     Coordinate[] array = new Coordinate[coordinates.size()];
/* 237 */     return coordinates.<Coordinate>toArray(array);
/*     */   }
/*     */   
/*     */   private Coordinate[] getCoordinatesNoLeftParen() throws IOException, ParseException {
/* 241 */     String nextToken = null;
/* 242 */     ArrayList<Coordinate> coordinates = new ArrayList();
/* 243 */     coordinates.add(getPreciseCoordinate());
/* 244 */     nextToken = getNextCloserOrComma();
/* 245 */     while (nextToken.equals(",")) {
/* 246 */       coordinates.add(getPreciseCoordinate());
/* 247 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 249 */     Coordinate[] array = new Coordinate[coordinates.size()];
/* 250 */     return coordinates.<Coordinate>toArray(array);
/*     */   }
/*     */   
/*     */   private Coordinate getPreciseCoordinate() throws IOException, ParseException {
/* 256 */     Coordinate coord = new Coordinate();
/* 257 */     coord.x = getNextNumber();
/* 258 */     coord.y = getNextNumber();
/* 259 */     if (isNumberNext())
/* 260 */       coord.z = getNextNumber(); 
/* 262 */     this.precisionModel.makePrecise(coord);
/* 263 */     return coord;
/*     */   }
/*     */   
/*     */   private boolean isNumberNext() throws IOException {
/* 267 */     int type = this.tokenizer.nextToken();
/* 268 */     this.tokenizer.pushBack();
/* 269 */     return (type == -3);
/*     */   }
/*     */   
/*     */   private double getNextNumber() throws IOException, ParseException {
/* 286 */     int type = this.tokenizer.nextToken();
/* 287 */     switch (type) {
/*     */       case -3:
/* 290 */         if (this.tokenizer.sval.equalsIgnoreCase("NaN"))
/* 291 */           return Double.NaN; 
/*     */         try {
/* 295 */           return Double.parseDouble(this.tokenizer.sval);
/* 297 */         } catch (NumberFormatException ex) {
/* 298 */           parseErrorWithLine("Invalid number: " + this.tokenizer.sval);
/*     */           break;
/*     */         } 
/*     */     } 
/* 303 */     parseErrorExpected("number");
/* 304 */     return 0.0D;
/*     */   }
/*     */   
/*     */   private String getNextEmptyOrOpener() throws IOException, ParseException {
/* 317 */     String nextWord = getNextWord();
/* 318 */     if (nextWord.equals("EMPTY") || nextWord.equals("("))
/* 319 */       return nextWord; 
/* 321 */     parseErrorExpected("EMPTY or (");
/* 322 */     return null;
/*     */   }
/*     */   
/*     */   private String getNextCloserOrComma() throws IOException, ParseException {
/* 335 */     String nextWord = getNextWord();
/* 336 */     if (nextWord.equals(",") || nextWord.equals(")"))
/* 337 */       return nextWord; 
/* 339 */     parseErrorExpected(", or )");
/* 340 */     return null;
/*     */   }
/*     */   
/*     */   private String getNextCloser() throws IOException, ParseException {
/* 353 */     String nextWord = getNextWord();
/* 354 */     if (nextWord.equals(")"))
/* 355 */       return nextWord; 
/* 357 */     parseErrorExpected(")");
/* 358 */     return null;
/*     */   }
/*     */   
/*     */   private String getNextWord() throws IOException, ParseException {
/*     */     String word;
/* 371 */     int type = this.tokenizer.nextToken();
/* 372 */     switch (type) {
/*     */       case -3:
/* 375 */         word = this.tokenizer.sval;
/* 376 */         if (word.equalsIgnoreCase("EMPTY"))
/* 377 */           return "EMPTY"; 
/* 378 */         return word;
/*     */       case 40:
/* 380 */         return "(";
/*     */       case 41:
/* 381 */         return ")";
/*     */       case 44:
/* 382 */         return ",";
/*     */     } 
/* 384 */     parseErrorExpected("word");
/* 385 */     return null;
/*     */   }
/*     */   
/*     */   private String lookaheadWord() throws IOException, ParseException {
/* 398 */     String nextWord = getNextWord();
/* 399 */     this.tokenizer.pushBack();
/* 400 */     return nextWord;
/*     */   }
/*     */   
/*     */   private void parseErrorExpected(String expected) throws ParseException {
/* 415 */     if (this.tokenizer.ttype == -2)
/* 416 */       Assert.shouldNeverReachHere("Unexpected NUMBER token"); 
/* 417 */     if (this.tokenizer.ttype == 10)
/* 418 */       Assert.shouldNeverReachHere("Unexpected EOL token"); 
/* 420 */     String tokenStr = tokenString();
/* 421 */     parseErrorWithLine("Expected " + expected + " but found " + tokenStr);
/*     */   }
/*     */   
/*     */   private void parseErrorWithLine(String msg) throws ParseException {
/* 427 */     throw new ParseException(msg + " (line " + this.tokenizer.lineno() + ")");
/*     */   }
/*     */   
/*     */   private String tokenString() {
/* 437 */     switch (this.tokenizer.ttype) {
/*     */       case -2:
/* 439 */         return "<NUMBER>";
/*     */       case 10:
/* 441 */         return "End-of-Line";
/*     */       case -1:
/* 442 */         return "End-of-Stream";
/*     */       case -3:
/* 443 */         return "'" + this.tokenizer.sval + "'";
/*     */     } 
/* 445 */     return "'" + (char)this.tokenizer.ttype + "'";
/*     */   }
/*     */   
/*     */   private Geometry readGeometryTaggedText() throws IOException, ParseException {
/* 461 */     String type = null;
/*     */     try {
/* 464 */       type = getNextWord();
/* 465 */     } catch (IOException e) {
/* 466 */       return null;
/* 467 */     } catch (ParseException e) {
/* 468 */       return null;
/*     */     } 
/* 471 */     if (type.equalsIgnoreCase("POINT"))
/* 472 */       return (Geometry)readPointText(); 
/* 474 */     if (type.equalsIgnoreCase("LINESTRING"))
/* 475 */       return (Geometry)readLineStringText(); 
/* 477 */     if (type.equalsIgnoreCase("LINEARRING"))
/* 478 */       return (Geometry)readLinearRingText(); 
/* 480 */     if (type.equalsIgnoreCase("POLYGON"))
/* 481 */       return (Geometry)readPolygonText(); 
/* 483 */     if (type.equalsIgnoreCase("MULTIPOINT"))
/* 484 */       return (Geometry)readMultiPointText(); 
/* 486 */     if (type.equalsIgnoreCase("MULTILINESTRING"))
/* 487 */       return (Geometry)readMultiLineStringText(); 
/* 489 */     if (type.equalsIgnoreCase("MULTIPOLYGON"))
/* 490 */       return (Geometry)readMultiPolygonText(); 
/* 492 */     if (type.equalsIgnoreCase("GEOMETRYCOLLECTION"))
/* 493 */       return (Geometry)readGeometryCollectionText(); 
/* 495 */     parseErrorWithLine("Unknown geometry type: " + type);
/* 497 */     return null;
/*     */   }
/*     */   
/*     */   private Point readPointText() throws IOException, ParseException {
/* 511 */     String nextToken = getNextEmptyOrOpener();
/* 512 */     if (nextToken.equals("EMPTY"))
/* 513 */       return this.geometryFactory.createPoint((Coordinate)null); 
/* 515 */     Point point = this.geometryFactory.createPoint(getPreciseCoordinate());
/* 516 */     getNextCloser();
/* 517 */     return point;
/*     */   }
/*     */   
/*     */   private LineString readLineStringText() throws IOException, ParseException {
/* 531 */     return this.geometryFactory.createLineString(getCoordinates());
/*     */   }
/*     */   
/*     */   private LinearRing readLinearRingText() throws IOException, ParseException {
/* 549 */     return this.geometryFactory.createLinearRing(getCoordinates());
/*     */   }
/*     */   
/*     */   private MultiPoint readMultiPointText() throws IOException, ParseException {
/* 572 */     String nextToken = getNextEmptyOrOpener();
/* 573 */     if (nextToken.equals("EMPTY"))
/* 574 */       return this.geometryFactory.createMultiPoint(new Point[0]); 
/* 580 */     String nextWord = lookaheadWord();
/* 581 */     if (nextWord != "(")
/* 582 */       return this.geometryFactory.createMultiPoint(toPoints(getCoordinatesNoLeftParen())); 
/* 586 */     ArrayList<Point> points = new ArrayList();
/* 587 */     Point point = readPointText();
/* 588 */     points.add(point);
/* 589 */     nextToken = getNextCloserOrComma();
/* 590 */     while (nextToken.equals(",")) {
/* 591 */       point = readPointText();
/* 592 */       points.add(point);
/* 593 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 595 */     Point[] array = new Point[points.size()];
/* 596 */     return this.geometryFactory.createMultiPoint(points.<Point>toArray(array));
/*     */   }
/*     */   
/*     */   private Point[] toPoints(Coordinate[] coordinates) {
/* 609 */     ArrayList<Point> points = new ArrayList();
/* 610 */     for (int i = 0; i < coordinates.length; i++)
/* 611 */       points.add(this.geometryFactory.createPoint(coordinates[i])); 
/* 613 */     return points.<Point>toArray(new Point[0]);
/*     */   }
/*     */   
/*     */   private Polygon readPolygonText() throws IOException, ParseException {
/* 629 */     String nextToken = getNextEmptyOrOpener();
/* 630 */     if (nextToken.equals("EMPTY"))
/* 631 */       return this.geometryFactory.createPolygon(this.geometryFactory.createLinearRing(new Coordinate[0]), new LinearRing[0]); 
/* 634 */     ArrayList<LinearRing> holes = new ArrayList();
/* 635 */     LinearRing shell = readLinearRingText();
/* 636 */     nextToken = getNextCloserOrComma();
/* 637 */     while (nextToken.equals(",")) {
/* 638 */       LinearRing hole = readLinearRingText();
/* 639 */       holes.add(hole);
/* 640 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 642 */     LinearRing[] array = new LinearRing[holes.size()];
/* 643 */     return this.geometryFactory.createPolygon(shell, holes.<LinearRing>toArray(array));
/*     */   }
/*     */   
/*     */   private MultiLineString readMultiLineStringText() throws IOException, ParseException {
/* 657 */     String nextToken = getNextEmptyOrOpener();
/* 658 */     if (nextToken.equals("EMPTY"))
/* 659 */       return this.geometryFactory.createMultiLineString(new LineString[0]); 
/* 661 */     ArrayList<LineString> lineStrings = new ArrayList();
/* 662 */     LineString lineString = readLineStringText();
/* 663 */     lineStrings.add(lineString);
/* 664 */     nextToken = getNextCloserOrComma();
/* 665 */     while (nextToken.equals(",")) {
/* 666 */       lineString = readLineStringText();
/* 667 */       lineStrings.add(lineString);
/* 668 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 670 */     LineString[] array = new LineString[lineStrings.size()];
/* 671 */     return this.geometryFactory.createMultiLineString(lineStrings.<LineString>toArray(array));
/*     */   }
/*     */   
/*     */   private MultiPolygon readMultiPolygonText() throws IOException, ParseException {
/* 686 */     String nextToken = getNextEmptyOrOpener();
/* 687 */     if (nextToken.equals("EMPTY"))
/* 688 */       return this.geometryFactory.createMultiPolygon(new Polygon[0]); 
/* 690 */     ArrayList<Polygon> polygons = new ArrayList();
/* 691 */     Polygon polygon = readPolygonText();
/* 692 */     polygons.add(polygon);
/* 693 */     nextToken = getNextCloserOrComma();
/* 694 */     while (nextToken.equals(",")) {
/* 695 */       polygon = readPolygonText();
/* 696 */       polygons.add(polygon);
/* 697 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 699 */     Polygon[] array = new Polygon[polygons.size()];
/* 700 */     return this.geometryFactory.createMultiPolygon(polygons.<Polygon>toArray(array));
/*     */   }
/*     */   
/*     */   private GeometryCollection readGeometryCollectionText() throws IOException, ParseException {
/* 717 */     String nextToken = getNextEmptyOrOpener();
/* 718 */     if (nextToken.equals("EMPTY"))
/* 719 */       return this.geometryFactory.createGeometryCollection(new Geometry[0]); 
/* 721 */     ArrayList<Geometry> geometries = new ArrayList();
/* 722 */     Geometry geometry = readGeometryTaggedText();
/* 723 */     geometries.add(geometry);
/* 724 */     nextToken = getNextCloserOrComma();
/* 725 */     while (nextToken.equals(",")) {
/* 726 */       geometry = readGeometryTaggedText();
/* 727 */       geometries.add(geometry);
/* 728 */       nextToken = getNextCloserOrComma();
/*     */     } 
/* 730 */     Geometry[] array = new Geometry[geometries.size()];
/* 731 */     return this.geometryFactory.createGeometryCollection(geometries.<Geometry>toArray(array));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\WKTReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */