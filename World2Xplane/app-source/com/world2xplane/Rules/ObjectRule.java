/*     */ package com.world2xplane.Rules;
/*     */ 
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import com.world2xplane.Rules.ObjectRules.ObjectList;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class ObjectRule extends Rule {
/*  41 */   private final List<Integer> objectFiles = new ArrayList<>();
/*     */   
/*  42 */   private final Map<Integer, Boolean> cache = new HashMap<>();
/*     */   
/*  43 */   private final List<ObjectTagRule> objectTagRules = new ArrayList<>();
/*     */   
/*  45 */   DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/*     */   
/*  48 */   NumberFormat numberFormat = new DecimalFormat("#####.###########", this.formatSymbols);
/*     */   
/*     */   private ObjectList bestFitList;
/*     */   
/*     */   private boolean adjustHeight = false;
/*     */   
/*  56 */   private int level = 6;
/*     */   
/*     */   private Float angle;
/*     */   
/*     */   private String angleFromWay;
/*     */   
/*     */   private Float angleFromWayBase;
/*     */   
/*     */   private boolean angleFromRoad = false;
/*     */   
/*     */   private boolean streetFacing = false;
/*     */   
/*     */   private boolean rejectMultiPolygon = false;
/*     */   
/*  69 */   private double minAreaX = -1.0D;
/*     */   
/*  70 */   private double maxAreaX = -1.0D;
/*     */   
/*  72 */   private double minAreaY = -1.0D;
/*     */   
/*  73 */   private double maxAreaY = -1.0D;
/*     */   
/*  75 */   private double minArea = -1.0D;
/*     */   
/*  76 */   private double maxArea = -1.0D;
/*     */   
/*  78 */   private double defaultHeight = 9.0D;
/*     */   
/*  80 */   private double tolerance = 1.0D;
/*     */   
/*     */   private boolean closedOverride = false;
/*     */   
/*     */   private boolean restrictShape = false;
/*     */   
/*     */   private int minRandomHeight;
/*     */   
/*     */   private int maxRandomHeight;
/*     */   
/*     */   public ObjectRule(GeneratorStore generatorStore) {
/*  88 */     super(generatorStore);
/*     */   }
/*     */   
/*     */   public List<Integer> getObjectFiles() {
/*  92 */     return this.objectFiles;
/*     */   }
/*     */   
/*     */   public void setAngle(Float angle) {
/*  96 */     this.angle = angle;
/*     */   }
/*     */   
/*     */   public Integer getDefinitionNumber(Object osmObject, Rule.Delegate delegate) {
/* 101 */     if (getBestFitList() != null && osmObject instanceof OSMPolygon) {
/* 102 */       OSMPolygon shape = (OSMPolygon)osmObject;
/* 103 */       return this.bestFitList.getBestFit(shape, new HashSet(), this.restrictShape, delegate, this);
/*     */     } 
/* 106 */     int objectNumber = getRandomNumber(0, getObjectFiles().size());
/* 107 */     return this.objectFiles.get(objectNumber);
/*     */   }
/*     */   
/*     */   public Integer getDefinitionNumber(Object osmObject) {
/* 114 */     return getDefinitionNumber(osmObject, (Rule.Delegate)null);
/*     */   }
/*     */   
/*     */   public boolean acceptsShape(Byte tag, OSMPolygon shape, Set<String> possibleRegions, Rule.Delegate delegate, boolean acceptOnly) {
/* 128 */     if (this.bestFitList != null && shape == null)
/* 129 */       return false; 
/* 134 */     if (shape == null && !isCircular() && this.minArea == -1.0D) {
/* 136 */       if (this.randomSeedIdentifier != null) {
/* 137 */         RandomSeed randomSeed = GeneratorStore.getGeneratorStore().getRandomSeed(this.randomSeedIdentifier);
/* 138 */         if (randomSeed != null) {
/* 139 */           int seed = randomSeed.getCurrentSeed();
/* 140 */           if (seed < this.minSeed || seed > this.maxSeed)
/* 141 */             return false; 
/*     */         } 
/*     */       } 
/* 146 */       return true;
/*     */     } 
/* 149 */     if (isRejectMultiPolygon() && shape != null && shape.isMultipolygon())
/* 150 */       return false; 
/* 153 */     if (this.closedOverride && shape == null)
/* 154 */       return true; 
/* 158 */     if (shape == null)
/* 159 */       return false; 
/* 164 */     boolean accept = validateShape(tag, shape, possibleRegions, delegate);
/* 165 */     if (!accept)
/* 166 */       return false; 
/* 169 */     if (this.generatorStore.isCreateBuildingParts() && shape.isBuildingPart())
/* 170 */       return false; 
/* 173 */     if (this.bestFitList != null) {
/* 174 */       if (shape != null && shape.vertexNumber() < 3)
/* 175 */         return false; 
/* 177 */       Integer def = this.bestFitList.getBestFit(shape, possibleRegions, this.restrictShape, delegate, this);
/* 178 */       return (def != null);
/*     */     } 
/* 183 */     if (this.minArea != -1.0D || this.maxArea != -1.0D) {
/* 185 */       if (shape != null && shape.vertexNumber() < 3)
/* 186 */         return false; 
/* 189 */       double area = shape.getArea().floatValue();
/* 191 */       if (this.minArea != -1.0D && area < this.minArea)
/* 192 */         accept = false; 
/* 194 */       if (this.maxArea != -1.0D && area > this.maxArea)
/* 195 */         accept = false; 
/* 198 */       if (accept) {
/*     */         try {
/* 200 */           if (analyseShape(shape) != null)
/* 201 */             return true; 
/* 202 */         } catch (Exception e) {
/* 203 */           return false;
/*     */         } 
/*     */       } else {
/* 206 */         return false;
/*     */       } 
/*     */     } 
/* 210 */     if (this.closedOverride)
/* 211 */       return true; 
/* 213 */     return accept;
/*     */   }
/*     */   
/*     */   public Double analyseShape(OSMPolygon shape) {
/* 219 */     if (this.minAreaX != -1.0D) {
/* 220 */       double smallest = shape.getShortestSide().floatValue();
/* 221 */       double largest = shape.getLongestSide().floatValue();
/* 224 */       if (smallest >= this.minAreaX && smallest <= this.maxAreaX && largest >= this.minAreaY && largest <= this.maxAreaY)
/* 227 */         return Double.valueOf(FastMath.abs(smallest - this.maxAreaX - (this.maxAreaX - this.minAreaX) / 2.0D + largest - this.maxAreaY - (this.maxAreaY - this.minAreaY) / 2.0D)); 
/* 230 */       if (largest >= this.minAreaX && largest <= this.maxAreaX && smallest >= this.minAreaY && smallest <= this.maxAreaY)
/* 233 */         return Double.valueOf(FastMath.abs(largest - this.maxAreaX - (this.maxAreaX - this.minAreaX) / 2.0D + smallest - this.maxAreaY - (this.maxAreaY - this.minAreaY) / 2.0D)); 
/*     */     } 
/* 236 */     return null;
/*     */   }
/*     */   
/*     */   public double getMinAreaX() {
/* 240 */     return this.minAreaX;
/*     */   }
/*     */   
/*     */   public void setMinAreaX(double minAreaX) {
/* 244 */     this.minAreaX = minAreaX;
/*     */   }
/*     */   
/*     */   public double getMaxAreaX() {
/* 248 */     return this.maxAreaX;
/*     */   }
/*     */   
/*     */   public void setMaxAreaX(double maxAreaX) {
/* 252 */     this.maxAreaX = maxAreaX;
/*     */   }
/*     */   
/*     */   public double getMinAreaY() {
/* 256 */     return this.minAreaY;
/*     */   }
/*     */   
/*     */   public void setMinAreaY(double minAreaY) {
/* 260 */     this.minAreaY = minAreaY;
/*     */   }
/*     */   
/*     */   public double getMaxAreaY() {
/* 264 */     return this.maxAreaY;
/*     */   }
/*     */   
/*     */   public void setMaxAreaY(double maxAreaY) {
/* 268 */     this.maxAreaY = maxAreaY;
/*     */   }
/*     */   
/*     */   public Float getAngle() {
/* 272 */     return this.angle;
/*     */   }
/*     */   
/*     */   public double getMinArea() {
/* 278 */     return this.minArea;
/*     */   }
/*     */   
/*     */   public void setMinArea(double minArea) {
/* 282 */     this.minArea = minArea;
/*     */   }
/*     */   
/*     */   public double getMaxArea() {
/* 286 */     return this.maxArea;
/*     */   }
/*     */   
/*     */   public void setMaxArea(double maxArea) {
/* 290 */     this.maxArea = maxArea;
/*     */   }
/*     */   
/*     */   public void setMaxArea(int maxArea) {
/* 294 */     this.maxArea = maxArea;
/*     */   }
/*     */   
/*     */   public void calculateAreas() {
/* 300 */     if (this.minAreaX == this.maxAreaX && this.minAreaX != -1.0D) {
/* 301 */       this.minAreaX -= 0.8D;
/* 302 */       this.maxAreaX += 0.8D;
/*     */     } 
/* 305 */     if (this.minAreaY == this.maxAreaY && this.maxAreaY != -1.0D) {
/* 306 */       this.minAreaY -= 0.8D;
/* 307 */       this.maxAreaY += 0.8D;
/*     */     } 
/* 310 */     if (this.maxArea == -1.0D && this.maxAreaX != -1.0D)
/* 311 */       this.maxArea = this.maxAreaX * this.maxAreaY; 
/* 313 */     if (this.minArea == -1.0D && this.minAreaX != -1.0D)
/* 314 */       this.minArea = this.minAreaX * this.minAreaY; 
/*     */   }
/*     */   
/*     */   public Float getRandomAngle() {
/* 319 */     this.angle = Float.valueOf(getRandomNumber(0, 360));
/* 320 */     return this.angle;
/*     */   }
/*     */   
/*     */   public FilterList getFilterList() {
/* 324 */     return this.filterList;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 328 */     return "Object: " + getFilterList().getFilter();
/*     */   }
/*     */   
/*     */   public boolean isClosedOverride() {
/* 332 */     return this.closedOverride;
/*     */   }
/*     */   
/*     */   public void setClosedOverride(boolean closedOverride) {
/* 336 */     this.closedOverride = closedOverride;
/*     */   }
/*     */   
/*     */   public ObjectList getBestFitList() {
/* 340 */     return this.bestFitList;
/*     */   }
/*     */   
/*     */   public void setBestFitList(ObjectList bestFitList) {
/* 344 */     this.bestFitList = bestFitList;
/*     */   }
/*     */   
/*     */   public double getTolerance() {
/* 348 */     return this.tolerance;
/*     */   }
/*     */   
/*     */   public void setTolerance(double tolerance) {
/* 352 */     if (tolerance == -1.0D)
/* 353 */       tolerance = 1.0D; 
/* 355 */     this.tolerance = tolerance;
/*     */   }
/*     */   
/*     */   public double getDefaultHeight() {
/* 359 */     return this.defaultHeight;
/*     */   }
/*     */   
/*     */   public void setDefaultHeight(double defaultHeight) {
/* 363 */     this.defaultHeight = defaultHeight;
/*     */   }
/*     */   
/*     */   public String getAngleFromWay() {
/* 367 */     return this.angleFromWay;
/*     */   }
/*     */   
/*     */   public void setAngleFromWay(String angleFromWay) {
/* 371 */     this.angleFromWay = angleFromWay;
/*     */   }
/*     */   
/*     */   public Float getAngleFromWayBase() {
/* 375 */     return this.angleFromWayBase;
/*     */   }
/*     */   
/*     */   public void setAngleFromWayBase(Float angleFromWayBase) {
/* 379 */     this.angleFromWayBase = angleFromWayBase;
/*     */   }
/*     */   
/*     */   public void setRestrictShape(boolean restrictShape) {
/* 384 */     this.restrictShape = restrictShape;
/*     */   }
/*     */   
/*     */   public boolean isRestrictShape() {
/* 388 */     return this.restrictShape;
/*     */   }
/*     */   
/*     */   public boolean isAdjustHeight() {
/* 392 */     return this.adjustHeight;
/*     */   }
/*     */   
/*     */   public void setAdjustHeight(boolean adjustHeight) {
/* 396 */     this.adjustHeight = adjustHeight;
/*     */   }
/*     */   
/*     */   public int getRandomHeight() {
/* 400 */     return getRandomNumber(this.minRandomHeight, this.maxRandomHeight + 1);
/*     */   }
/*     */   
/*     */   public int getMinRandomHeight() {
/* 404 */     return this.minRandomHeight;
/*     */   }
/*     */   
/*     */   public void setMinRandomHeight(int minRandomHeight) {
/* 408 */     this.minRandomHeight = minRandomHeight;
/*     */   }
/*     */   
/*     */   public int getMaxRandomHeight() {
/* 412 */     return this.maxRandomHeight;
/*     */   }
/*     */   
/*     */   public void setMaxRandomHeight(int maxRandomHeight) {
/* 416 */     this.maxRandomHeight = maxRandomHeight;
/*     */   }
/*     */   
/*     */   public boolean isStreetFacing() {
/* 420 */     return this.streetFacing;
/*     */   }
/*     */   
/*     */   public void setStreetFacing(boolean streetFacing) {
/* 424 */     this.streetFacing = streetFacing;
/*     */   }
/*     */   
/*     */   public boolean isRejectMultiPolygon() {
/* 428 */     return this.rejectMultiPolygon;
/*     */   }
/*     */   
/*     */   public void setRejectMultiPolygon(boolean rejectMultiPolygon) {
/* 432 */     this.rejectMultiPolygon = rejectMultiPolygon;
/*     */   }
/*     */   
/*     */   public List<ObjectTagRule> getObjectTagRules() {
/* 436 */     return this.objectTagRules;
/*     */   }
/*     */   
/*     */   public void readTagRules(String fileName) throws IOException, ParseException {
/* 440 */     File file = new File(fileName);
/* 441 */     if (!file.exists())
/* 442 */       throw new RuntimeException("File " + fileName + " does not exist"); 
/* 444 */     String contents = FileUtils.readFileToString(file);
/* 446 */     String[] lines = contents.split("\n");
/* 448 */     for (int x = 1; x < lines.length; x++) {
/* 449 */       String[] rows = lines[x].split(",");
/* 450 */       if (rows.length >= 5) {
/* 453 */         String region = rows[0];
/* 454 */         String area = rows[1];
/* 455 */         String tag = rows[2];
/* 456 */         float min = this.numberFormat.parse(rows[3]).floatValue();
/* 457 */         float max = this.numberFormat.parse(rows[4]).floatValue();
/* 458 */         int score = this.numberFormat.parse(rows[5]).intValue();
/* 460 */         ObjectTagRule objectTagRule = new ObjectTagRule(region, area, tag, min, max, score);
/* 461 */         this.objectTagRules.add(objectTagRule);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isAngleFromRoad() {
/* 470 */     return this.angleFromRoad;
/*     */   }
/*     */   
/*     */   public void setAngleFromRoad(boolean angleFromRoad) {
/* 474 */     this.angleFromRoad = angleFromRoad;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\ObjectRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */