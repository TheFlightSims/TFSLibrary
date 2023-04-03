/*     */ package com.world2xplane.OSM;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.operation.union.UnaryUnionOp;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ 
/*     */ public class PolygonFileReader {
/*  94 */   private static final Logger LOG = Logger.getLogger(PolygonFileReader.class.getName());
/*     */   
/*     */   private Reader fileReader;
/*     */   
/*     */   private String polygonFile;
/*     */   
/*     */   private String myPolygonName;
/*     */   
/*     */   public PolygonFileReader(InputStream polygonFile, String name) {
/* 119 */     this.polygonFile = name;
/* 120 */     this.fileReader = new InputStreamReader(polygonFile);
/*     */   }
/*     */   
/*     */   public PolygonFileReader(File polygonFile) {
/*     */     try {
/* 131 */       this.polygonFile = polygonFile.getName();
/* 132 */       this.fileReader = new FileReader(polygonFile);
/* 133 */     } catch (IOException e) {
/* 134 */       throw new OsmosisRuntimeException("Unable to read from polygon file " + polygonFile + ".", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void cleanup() {
/* 143 */     if (this.fileReader != null)
/*     */       try {
/* 145 */         this.fileReader.close();
/* 146 */       } catch (Exception e) {
/* 147 */         LOG.log(Level.SEVERE, "Unable to close polygon file reader.", e);
/*     */       } finally {
/* 149 */         this.fileReader = null;
/*     */       }  
/*     */   }
/*     */   
/*     */   public Geometry loadPolygon() {
/* 162 */     GeometryFactory geometryFactory = new GeometryFactory();
/*     */     try {
/* 165 */       List<Polygon> outer = new ArrayList<>();
/* 166 */       List<Polygon> inner = new ArrayList<>();
/* 169 */       BufferedReader bufferedReader = new BufferedReader(this.fileReader);
/* 172 */       this.myPolygonName = bufferedReader.readLine();
/* 173 */       if (this.myPolygonName == null || this.myPolygonName.trim().length() == 0)
/* 174 */         throw new OsmosisRuntimeException("The file must begin with a header naming the polygon file."); 
/*     */       while (true) {
/* 185 */         String sectionHeader = bufferedReader.readLine();
/* 188 */         if (sectionHeader == null)
/* 189 */           throw new OsmosisRuntimeException("File terminated prematurely without a section END record."); 
/* 193 */         sectionHeader = sectionHeader.trim();
/* 195 */         if (sectionHeader.length() != 0) {
/* 198 */           if ("END".equals(sectionHeader))
/*     */             break; 
/* 204 */           boolean positivePolygon = (sectionHeader.charAt(0) != '!');
/* 208 */           LinearRing sectionArea = loadSectionPolygon(bufferedReader);
/* 212 */           if (positivePolygon) {
/* 213 */             outer.add(geometryFactory.createPolygon(sectionArea, null));
/*     */             continue;
/*     */           } 
/* 215 */           inner.add(geometryFactory.createPolygon(sectionArea, null));
/*     */         } 
/*     */       } 
/* 219 */       return createPolygon(outer, inner);
/* 221 */     } catch (IOException e) {
/* 222 */       throw new OsmosisRuntimeException("Unable to read from polygon file " + this.polygonFile + ".", e);
/*     */     } finally {
/* 224 */       cleanup();
/*     */     } 
/*     */   }
/*     */   
/*     */   private Geometry createPolygon(List<Polygon> outer, List<Polygon> inner) {
/* 229 */     UnaryUnionOp unaryUnionOp = new UnaryUnionOp(outer);
/* 230 */     Geometry outerRing = unaryUnionOp.union();
/* 232 */     return outerRing;
/*     */   }
/*     */   
/*     */   private LinearRing loadSectionPolygon(BufferedReader bufferedReader) throws IOException {
/* 245 */     List<Coordinate> points = new ArrayList<>();
/* 246 */     double[] beginPoint = null;
/*     */     while (true) {
/* 255 */       String sectionLine = bufferedReader.readLine();
/* 258 */       if (sectionLine == null)
/* 259 */         throw new OsmosisRuntimeException("File terminated prematurely without a section END record."); 
/* 263 */       sectionLine = sectionLine.trim();
/* 265 */       if (sectionLine.length() != 0) {
/* 268 */         if ("END".equals(sectionLine))
/*     */           break; 
/* 273 */         double[] coordinates = parseCoordinates(sectionLine);
/* 275 */         points.add(new Coordinate(coordinates[0], coordinates[1]));
/*     */       } 
/*     */     } 
/* 279 */     if (!((Coordinate)points.get(0)).equals(points.get(points.size() - 1)))
/* 280 */       points.add(points.get(0)); 
/* 283 */     return (new GeometryFactory()).createLinearRing(points.<Coordinate>toArray(new Coordinate[points.size()]));
/*     */   }
/*     */   
/*     */   private double[] parseCoordinates(String coordinateLine) {
/* 302 */     String[] rawTokens = coordinateLine.split("\\s");
/* 305 */     int tokenCount = 0;
/* 306 */     double[] results = new double[2];
/* 307 */     for (int i = 0; i < rawTokens.length; i++) {
/* 308 */       if (rawTokens[i].length() > 0) {
/* 310 */         if (tokenCount >= 2)
/* 311 */           throw new OsmosisRuntimeException("A polygon coordinate line must contain 2 numbers, not (" + coordinateLine + ")."); 
/*     */         try {
/* 318 */           results[tokenCount++] = Double.parseDouble(rawTokens[i]);
/* 319 */         } catch (NumberFormatException e) {
/* 320 */           throw new OsmosisRuntimeException("Unable to parse " + rawTokens[i] + " into a double precision number.");
/*     */         } 
/*     */       } 
/*     */     } 
/* 327 */     if (tokenCount < 2)
/* 328 */       throw new OsmosisRuntimeException("Could not find two coordinates on line (" + coordinateLine + ")."); 
/* 331 */     return results;
/*     */   }
/*     */   
/*     */   public String getPolygonName() {
/* 339 */     return this.myPolygonName;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\PolygonFileReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */