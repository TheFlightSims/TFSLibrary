/*     */ package com.world2xplane.Rules.Regions;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.io.File;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import math.geom2d.Point2D;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class CSVRegion extends RegionProvider {
/*  42 */   final GeometryFactory geometryFactory = new GeometryFactory();
/*     */   
/*  44 */   private static Logger log = LoggerFactory.getLogger(CSVRegion.class);
/*     */   
/*  45 */   private STRtree quadtree = new STRtree();
/*     */   
/*  47 */   private List<Region> regionList = new ArrayList<>();
/*     */   
/*     */   private String path;
/*     */   
/*     */   public void setPath(String path) {
/*  51 */     this.path = path;
/*     */   }
/*     */   
/*     */   public String getPath() {
/*  55 */     return this.path;
/*     */   }
/*     */   
/*     */   public class Region {
/*     */     final Envelope shape;
/*     */     
/*     */     final String code;
/*     */     
/*     */     public Region(Envelope shape, String code) {
/*  64 */       this.shape = shape;
/*  65 */       this.code = code;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean validate() {
/*  70 */     File file = new File(this.path);
/*  71 */     if (!file.exists()) {
/*  72 */       log.error("Can not find CSV file " + file.getAbsolutePath());
/*  73 */       return false;
/*     */     } 
/*  76 */     return true;
/*     */   }
/*     */   
/*     */   public boolean load() {
/*  81 */     DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/*  83 */     NumberFormat numberFormat = new DecimalFormat("#####.###########", formatSymbols);
/*  89 */     Set<String> usedCodes = GeneratorStore.getGeneratorStore().getRegions();
/*  91 */     File file = new File(this.path);
/*     */     try {
/*  94 */       String contents = FileUtils.readFileToString(file);
/*  95 */       contents = contents.replaceAll("\r", "");
/*  96 */       String[] lines = contents.split("\n");
/*  97 */       for (int x = 1; x < lines.length; x++) {
/*  98 */         String[] columns = lines[x].split(",");
/*  99 */         if (columns.length < 3)
/* 100 */           throw new Exception("Too few columns at line " + x); 
/* 102 */         double north = numberFormat.parse(columns[0].trim()).doubleValue();
/* 103 */         double west = numberFormat.parse(columns[1].trim()).doubleValue();
/* 104 */         double south = numberFormat.parse(columns[2].trim()).doubleValue();
/* 105 */         double east = numberFormat.parse(columns[3].trim()).doubleValue();
/* 106 */         String identifier = columns[4].trim();
/* 107 */         Envelope envelope = new Envelope(west, east, north, south);
/* 108 */         Region region = new Region(envelope, identifier);
/* 109 */         if (usedCodes.contains(identifier))
/* 110 */           this.quadtree.insert(envelope, region); 
/* 111 */         this.regionList.add(region);
/*     */       } 
/* 114 */     } catch (Throwable e) {
/* 115 */       log.error("Could not load CSV file ", e);
/* 116 */       return false;
/*     */     } 
/* 118 */     return true;
/*     */   }
/*     */   
/*     */   public Set<String> getRegionsAtPoint(double lon, double lat, Set<String> filter) {
/* 125 */     Point2D position = new Point2D(lon, lat);
/* 128 */     List<Region> regions = this.quadtree.query(new Envelope(new Coordinate(position.x(), position.y())));
/* 130 */     if (regions != null && regions.size() > 0) {
/* 131 */       HashSet<String> data = new HashSet<>();
/* 132 */       for (Region item : regions)
/* 133 */         data.add(item.code); 
/* 135 */       return data;
/*     */     } 
/* 138 */     return new HashSet<>();
/*     */   }
/*     */   
/*     */   public Set<String> getRegionsInside(double north, double west, double south, double east) {
/* 142 */     Geometry area = this.geometryFactory.toGeometry(new Envelope(west, east, north, south));
/* 143 */     List<Region> regions = this.quadtree.query(new Envelope(west, east, north, south));
/* 145 */     Set<String> regionCodes = new HashSet<>();
/* 147 */     if (regions != null)
/* 148 */       for (Region item : regions) {
/* 149 */         if (area.getEnvelopeInternal().contains(item.shape))
/* 150 */           regionCodes.add(item.code); 
/*     */       }  
/* 153 */     return regionCodes;
/*     */   }
/*     */   
/*     */   public Set<String> getRegionsContaining(double north, double west, double south, double east) {
/* 158 */     Envelope area = new Envelope(west, east, north, south);
/* 159 */     Set<String> regionCodes = new HashSet<>();
/* 161 */     for (Region item : this.regionList) {
/* 162 */       if (item.shape.contains(area))
/* 163 */         regionCodes.add(item.code); 
/*     */     } 
/* 165 */     return regionCodes;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Regions\CSVRegion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */