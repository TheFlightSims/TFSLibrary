/*     */ package com.world2xplane.Rules.Regions;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometry;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometryFactory;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.io.File;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import math.geom2d.Point2D;
/*     */ import org.geotools.data.DataStore;
/*     */ import org.geotools.data.DataStoreFinder;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class ShapeFileReader extends RegionProvider {
/*  47 */   private static Logger log = LoggerFactory.getLogger(ShapeFileReader.class);
/*     */   
/*  48 */   private STRtree quadtree = new STRtree();
/*     */   
/*  50 */   final GeometryFactory geometryFactory = new GeometryFactory();
/*     */   
/*     */   private String path;
/*     */   
/*     */   private String identifier;
/*     */   
/*     */   public void setPath(String path) {
/*  56 */     this.path = path;
/*     */   }
/*     */   
/*     */   public String getPath() {
/*  60 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setIdentifier(String identifier) {
/*  64 */     this.identifier = identifier;
/*     */   }
/*     */   
/*     */   public String getIdentifier() {
/*  68 */     return this.identifier;
/*     */   }
/*     */   
/*     */   public class Region {
/*     */     final PreparedGeometry shape;
/*     */     
/*     */     final String isoCode;
/*     */     
/*     */     public Region(PreparedGeometry shape, String isoCode) {
/*  78 */       this.shape = shape;
/*  79 */       this.isoCode = isoCode;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean validate() {
/*  84 */     File file = new File(this.path);
/*  85 */     if (!file.exists()) {
/*  86 */       log.error("Can not find shape file " + file.getAbsolutePath());
/*  87 */       return false;
/*     */     } 
/*  89 */     if (getIdentifier() == null || getIdentifier().length() == 0) {
/*  90 */       log.error("Identifier for shapefile region cannot be empty " + file.getAbsolutePath());
/*  91 */       return false;
/*     */     } 
/*  93 */     return true;
/*     */   }
/*     */   
/*     */   public boolean load() {
/*  98 */     Set<String> usedCodes = GeneratorStore.getGeneratorStore().getRegions();
/*     */     try {
/* 101 */       Map<Object, Object> connect = new HashMap<>();
/* 102 */       connect.put("url", (new File(this.path)).toURL());
/* 104 */       DataStore dataStore = DataStoreFinder.getDataStore(connect);
/* 105 */       if (dataStore == null) {
/* 106 */         log.error("Could not open shapefile " + this.path);
/* 107 */         return false;
/*     */       } 
/* 109 */       String[] typeNames = dataStore.getTypeNames();
/* 110 */       String typeName = typeNames[0];
/* 112 */       SimpleFeatureSource simpleFeatureSource = dataStore.getFeatureSource(typeName);
/* 113 */       FeatureCollection collection = simpleFeatureSource.getFeatures();
/* 114 */       FeatureIterator iterator = collection.features();
/*     */       try {
/* 118 */         while (iterator.hasNext()) {
/* 119 */           Feature feature = iterator.next();
/* 120 */           Geometry sourceGeometry = (Geometry)feature.getDefaultGeometryProperty().getValue();
/* 122 */           Region region = new Region(PreparedGeometryFactory.prepare(sourceGeometry), (String)feature.getProperty(this.identifier).getValue());
/* 124 */           if (usedCodes.contains(region.isoCode)) {
/* 125 */             Envelope envelope = sourceGeometry.getEnvelopeInternal();
/* 126 */             this.quadtree.insert(envelope, region);
/*     */           } 
/*     */         } 
/*     */       } finally {
/* 130 */         iterator.close();
/*     */       } 
/* 133 */     } catch (Throwable e) {
/* 134 */       log.error("Could not load shapefile ", e);
/* 135 */       return false;
/*     */     } 
/* 137 */     return true;
/*     */   }
/*     */   
/*     */   public Set<String> getRegionsAtPoint(double lon, double lat, Set<String> filter) {
/* 144 */     Point2D position = new Point2D(lon, lat);
/* 146 */     if (position == null)
/* 147 */       return new HashSet<>(); 
/* 150 */     Point point = this.geometryFactory.createPoint(new Coordinate(position.x(), position.y()));
/* 151 */     List<Region> regions = this.quadtree.query(new Envelope(new Coordinate(position.x(), position.y())));
/* 154 */     if (regions != null && regions.size() > 0) {
/* 157 */       HashSet<String> data = new HashSet<>();
/* 158 */       for (Region item : regions) {
/* 159 */         if (filter != null && filter.size() > 0 && filter.contains(item.isoCode)) {
/* 161 */           if (item.shape.intersects((Geometry)point))
/* 162 */             data.add(item.isoCode); 
/*     */           continue;
/*     */         } 
/* 165 */         if (item.shape.intersects((Geometry)point))
/* 166 */           data.add(item.isoCode); 
/*     */       } 
/* 170 */       return data;
/*     */     } 
/* 173 */     return new HashSet<>();
/*     */   }
/*     */   
/*     */   public Set<String> getRegionsInside(double north, double west, double south, double east) {
/* 177 */     PreparedGeometry area = PreparedGeometryFactory.prepare(this.geometryFactory.toGeometry(new Envelope(west, east, north, south)));
/* 178 */     List<Region> regions = this.quadtree.query(new Envelope(west, east, north, south));
/* 180 */     Set<String> regionCodes = new HashSet<>();
/* 182 */     if (regions != null)
/* 183 */       for (Region item : regions) {
/* 184 */         if (area.intersects(item.shape.getGeometry()))
/* 185 */           regionCodes.add(item.isoCode); 
/*     */       }  
/* 188 */     return regionCodes;
/*     */   }
/*     */   
/*     */   public Set<String> getRegionsContaining(double north, double west, double south, double east) {
/* 193 */     Geometry area = this.geometryFactory.toGeometry(new Envelope(west, east, north, south));
/* 194 */     List<Region> regions = this.quadtree.query(new Envelope(west, east, north, south));
/* 196 */     Set<String> regionCodes = new HashSet<>();
/* 198 */     if (regions != null)
/* 199 */       for (Region item : regions) {
/* 200 */         if (item.shape.contains(area))
/* 201 */           regionCodes.add(item.isoCode); 
/*     */       }  
/* 204 */     return regionCodes;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Regions\ShapeFileReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */