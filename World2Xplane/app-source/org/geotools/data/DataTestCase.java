/*     */ package org.geotools.data;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.UUID;
/*     */ import junit.framework.TestCase;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ 
/*     */ public class DataTestCase extends TestCase {
/*     */   protected GeometryFactory gf;
/*     */   
/*     */   protected SimpleFeatureType roadType;
/*     */   
/*     */   protected SimpleFeatureType subRoadType;
/*     */   
/*     */   protected SimpleFeature[] roadFeatures;
/*     */   
/*     */   protected ReferencedEnvelope roadBounds;
/*     */   
/*     */   protected ReferencedEnvelope rd12Bounds;
/*     */   
/*     */   protected Filter rd1Filter;
/*     */   
/*     */   protected Filter rd2Filter;
/*     */   
/*     */   protected Filter rd12Filter;
/*     */   
/*     */   protected SimpleFeature newRoad;
/*     */   
/*     */   protected SimpleFeatureType riverType;
/*     */   
/*     */   protected SimpleFeatureType subRiverType;
/*     */   
/*     */   protected SimpleFeature[] riverFeatures;
/*     */   
/*     */   protected ReferencedEnvelope riverBounds;
/*     */   
/*     */   protected Filter rv1Filter;
/*     */   
/*     */   protected SimpleFeature newRiver;
/*     */   
/*     */   protected SimpleFeatureType lakeType;
/*     */   
/*     */   protected SimpleFeature[] lakeFeatures;
/*     */   
/*     */   protected ReferencedEnvelope lakeBounds;
/*     */   
/*     */   protected FilterFactory2 ff;
/*     */   
/*     */   public DataTestCase(String name) {
/*  93 */     super(name);
/*     */   }
/*     */   
/*     */   protected int expected(Filter filter) {
/*  97 */     if (filter instanceof Id) {
/*  98 */       Id id = (Id)filter;
/*  99 */       return id.getIDs().size();
/*     */     } 
/* 101 */     return -1;
/*     */   }
/*     */   
/*     */   protected void setUp() throws Exception {
/* 108 */     this.ff = CommonFactoryFinder.getFilterFactory2(null);
/* 109 */     dataSetUp();
/*     */   }
/*     */   
/*     */   protected void dataSetUp() throws Exception {
/* 118 */     String namespace = getName();
/* 119 */     this.roadType = DataUtilities.createType(namespace + ".road", "id:0,geom:LineString,name:String,uuid:UUID");
/* 121 */     this.subRoadType = DataUtilities.createType(namespace + "road", "id:0,geom:LineString");
/* 123 */     this.gf = new GeometryFactory();
/* 125 */     this.roadFeatures = new SimpleFeature[3];
/* 131 */     this.roadFeatures[0] = SimpleFeatureBuilder.build(this.roadType, new Object[] { new Integer(1), line(new int[] { 1, 1, 2, 2, 4, 2, 5, 1 }, ), "r1", UUID.randomUUID() }, "road.rd1");
/* 145 */     this.roadFeatures[1] = SimpleFeatureBuilder.build(this.roadType, new Object[] { new Integer(2), line(new int[] { 3, 0, 3, 2, 3, 3, 3, 4 }, ), "r2", UUID.randomUUID() }, "road.rd2");
/* 156 */     this.roadFeatures[2] = SimpleFeatureBuilder.build(this.roadType, new Object[] { new Integer(3), line(new int[] { 3, 2, 4, 2, 5, 3 }, ), "r3", UUID.randomUUID() }, "road.rd3");
/* 164 */     this.roadBounds = new ReferencedEnvelope();
/* 165 */     this.roadBounds.expandToInclude((Envelope)new ReferencedEnvelope(this.roadFeatures[0].getBounds()));
/* 166 */     this.roadBounds.expandToInclude((Envelope)new ReferencedEnvelope(this.roadFeatures[1].getBounds()));
/* 167 */     this.roadBounds.expandToInclude((Envelope)new ReferencedEnvelope(this.roadFeatures[2].getBounds()));
/* 169 */     this.rd1Filter = (Filter)this.ff.id(Collections.singleton(this.ff.featureId("road.rd1")));
/* 170 */     this.rd2Filter = (Filter)this.ff.id(Collections.singleton(this.ff.featureId("road.rd2")));
/* 172 */     Id create = this.ff.id(new HashSet(Arrays.asList((Object[])new FeatureId[] { this.ff.featureId("road.rd1"), this.ff.featureId("road.rd2") })));
/* 174 */     this.rd12Filter = (Filter)create;
/* 176 */     this.rd12Bounds = new ReferencedEnvelope();
/* 177 */     this.rd12Bounds.expandToInclude((Envelope)new ReferencedEnvelope(this.roadFeatures[0].getBounds()));
/* 178 */     this.rd12Bounds.expandToInclude((Envelope)new ReferencedEnvelope(this.roadFeatures[1].getBounds()));
/* 182 */     this.newRoad = SimpleFeatureBuilder.build(this.roadType, new Object[] { new Integer(4), line(new int[] { 1, 2, 2, 3 }, ), "r4", UUID.randomUUID() }, "road.rd4");
/* 186 */     this.riverType = DataUtilities.createType(namespace + ".river", "id:0,geom:MultiLineString,river:String,flow:0.0");
/* 188 */     this.subRiverType = DataUtilities.createType(namespace + ".river", "river:String,flow:0.0");
/* 190 */     this.gf = new GeometryFactory();
/* 191 */     this.riverFeatures = new SimpleFeature[2];
/* 199 */     this.riverFeatures[0] = SimpleFeatureBuilder.build(this.riverType, new Object[] { new Integer(1), lines(new int[][] { { 5, 5, 7, 4 }, , { 7, 5, 9, 7, 13, 7 }, , { 7, 5, 9, 3, 11, 3 },  }, ), "rv1", new Double(4.5D) }, "river.rv1");
/* 213 */     this.riverFeatures[1] = SimpleFeatureBuilder.build(this.riverType, new Object[] { new Integer(2), lines(new int[][] { { 4, 6, 4, 8, 6, 10 },  }, ), "rv2", new Double(3.0D) }, "river.rv2");
/* 219 */     this.riverBounds = new ReferencedEnvelope();
/* 220 */     this.riverBounds.expandToInclude((Envelope)ReferencedEnvelope.reference(this.riverFeatures[0].getBounds()));
/* 221 */     this.riverBounds.expandToInclude((Envelope)ReferencedEnvelope.reference(this.riverFeatures[1].getBounds()));
/* 223 */     this.rv1Filter = (Filter)this.ff.id(Collections.singleton(this.ff.featureId("river.rv1")));
/* 230 */     this.newRiver = SimpleFeatureBuilder.build(this.riverType, new Object[] { new Integer(3), lines(new int[][] { { 9, 5, 11, 5, 13, 3 },  }, ), "rv3", new Double(1.5D) }, "river.rv3");
/* 239 */     this.lakeType = DataUtilities.createType(namespace + ".lake", "id:0,geom:Polygon:nillable,name:String");
/* 241 */     this.lakeFeatures = new SimpleFeature[1];
/* 248 */     this.lakeFeatures[0] = SimpleFeatureBuilder.build(this.lakeType, new Object[] { new Integer(0), polygon(new int[] { 
/* 248 */               12, 6, 14, 8, 16, 6, 16, 4, 14, 4, 
/* 248 */               12, 6 }, ), "muddy" }, "lake.lk1");
/* 255 */     this.lakeBounds = new ReferencedEnvelope();
/* 256 */     this.lakeBounds.expandToInclude((Envelope)ReferencedEnvelope.reference(this.lakeFeatures[0].getBounds()));
/*     */   }
/*     */   
/*     */   protected void tearDown() throws Exception {
/* 264 */     this.gf = null;
/* 265 */     this.roadType = null;
/* 266 */     this.subRoadType = null;
/* 267 */     this.roadFeatures = null;
/* 268 */     this.roadBounds = null;
/* 269 */     this.rd1Filter = null;
/* 270 */     this.rd2Filter = null;
/* 271 */     this.newRoad = null;
/* 272 */     this.riverType = null;
/* 273 */     this.subRiverType = null;
/* 274 */     this.riverFeatures = null;
/* 275 */     this.riverBounds = null;
/* 276 */     this.rv1Filter = null;
/* 277 */     this.newRiver = null;
/*     */   }
/*     */   
/*     */   public LineString line(int[] xy) {
/* 285 */     Coordinate[] coords = new Coordinate[xy.length / 2];
/* 287 */     for (int i = 0; i < xy.length; i += 2)
/* 288 */       coords[i / 2] = new Coordinate(xy[i], xy[i + 1]); 
/* 291 */     return this.gf.createLineString(coords);
/*     */   }
/*     */   
/*     */   public MultiLineString lines(int[][] xy) {
/* 298 */     LineString[] lines = new LineString[xy.length];
/* 300 */     for (int i = 0; i < xy.length; i++)
/* 301 */       lines[i] = line(xy[i]); 
/* 304 */     return this.gf.createMultiLineString(lines);
/*     */   }
/*     */   
/*     */   public Polygon polygon(int[] xy) {
/* 312 */     LinearRing shell = ring(xy);
/* 313 */     return this.gf.createPolygon(shell, null);
/*     */   }
/*     */   
/*     */   public Polygon polygon(int[] xy, int[][] holes) {
/* 321 */     if (holes == null || holes.length == 0)
/* 322 */       return polygon(xy); 
/* 324 */     LinearRing shell = ring(xy);
/* 326 */     LinearRing[] rings = new LinearRing[holes.length];
/* 328 */     for (int i = 0; i < xy.length; i++)
/* 329 */       rings[i] = ring(holes[i]); 
/* 331 */     return this.gf.createPolygon(shell, rings);
/*     */   }
/*     */   
/*     */   public LinearRing ring(int[] xy) {
/* 339 */     Coordinate[] coords = new Coordinate[xy.length / 2];
/* 341 */     for (int i = 0; i < xy.length; i += 2)
/* 342 */       coords[i / 2] = new Coordinate(xy[i], xy[i + 1]); 
/* 345 */     return this.gf.createLinearRing(coords);
/*     */   }
/*     */   
/*     */   protected void assertEquals(Geometry expected, Geometry actual) {
/* 352 */     if (expected == actual)
/*     */       return; 
/* 355 */     assertNotNull(expected);
/* 356 */     assertNotNull(actual);
/* 357 */     assertTrue(expected.equalsExact(actual));
/*     */   }
/*     */   
/*     */   protected void assertEquals(String message, Geometry expected, Geometry actual) {
/* 364 */     if (expected == actual)
/*     */       return; 
/* 367 */     assertNotNull(message, expected);
/* 368 */     assertNotNull(message, actual);
/* 369 */     assertTrue(message, expected.equalsExact(actual));
/*     */   }
/*     */   
/*     */   protected int count(FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws IOException {
/* 379 */     if (reader == null)
/* 380 */       return -1; 
/* 382 */     int count = 0;
/*     */     try {
/* 384 */       while (reader.hasNext()) {
/* 385 */         reader.next();
/* 386 */         count++;
/*     */       } 
/* 388 */     } catch (NoSuchElementException e) {
/* 390 */       throw new DataSourceException("hasNext() lied to me at:" + count, e);
/* 391 */     } catch (Exception e) {
/* 392 */       throw new DataSourceException("next() could not understand feature at:" + count, e);
/*     */     } finally {
/* 395 */       reader.close();
/*     */     } 
/* 397 */     return count;
/*     */   }
/*     */   
/*     */   protected int count(FeatureWriter<SimpleFeatureType, SimpleFeature> writer) throws NoSuchElementException, IOException {
/* 406 */     int count = 0;
/*     */     try {
/* 409 */       while (writer.hasNext()) {
/* 410 */         writer.next();
/* 411 */         count++;
/*     */       } 
/*     */     } finally {
/* 414 */       writer.close();
/*     */     } 
/* 417 */     return count;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DataTestCase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */