/*     */ package com.world2xplane.Rules;
/*     */ 
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class RandomRule extends Rule {
/*     */   public static class Density {
/*     */     public int min;
/*     */     
/*     */     public int max;
/*     */     
/*     */     public double density;
/*     */   }
/*     */   
/*  41 */   private final List<Density> areaDensities = new ArrayList<>();
/*     */   
/*  43 */   private final List<Integer> objectFiles = new ArrayList<>();
/*     */   
/*  44 */   private final List<Integer> forestFiles = new ArrayList<>();
/*     */   
/*  46 */   private double density = 1.0D;
/*     */   
/*  47 */   private double angle = -1.0D;
/*     */   
/*     */   private boolean collisionTest = false;
/*     */   
/*     */   private boolean determineAngle;
/*     */   
/*  50 */   private double maxarea = -1.0D;
/*     */   
/*  51 */   private double minarea = -1.0D;
/*     */   
/*  52 */   private int count = -1;
/*     */   
/*     */   private boolean forestsOnEmpty = false;
/*     */   
/*     */   public RandomRule(GeneratorStore generatorStore) {
/*  56 */     super(generatorStore);
/*     */   }
/*     */   
/*     */   public List<Integer> getObjectFiles() {
/*  61 */     return this.objectFiles;
/*     */   }
/*     */   
/*     */   public FilterList getFilter() {
/*  65 */     return this.filterList;
/*     */   }
/*     */   
/*     */   public void setFilter(FilterList filterList) {
/*  69 */     this.filterList = filterList;
/*     */   }
/*     */   
/*     */   public double getRandomAngle() {
/*  75 */     if (this.angle != -1.0D)
/*  76 */       return this.angle; 
/*  78 */     return getRandomNumber(0, 360);
/*     */   }
/*     */   
/*     */   public FilterList getFilterList() {
/*  83 */     return this.filterList;
/*     */   }
/*     */   
/*     */   public Integer getDefinitionNumber(Object object) {
/*  88 */     return this.objectFiles.get(getRandomNumber(0, this.objectFiles.size()));
/*     */   }
/*     */   
/*     */   public boolean acceptsShape(Byte tagList, OSMPolygon shape, Set<String> regionsFromDsf, Rule.Delegate delegate, boolean acceptOnly) {
/*  96 */     if (getNotset() != null && !getNotset().isEmpty() && 
/*  97 */       this.generatorStore.variableIsSet(getNotset()))
/*  98 */       return false; 
/* 102 */     if (shape == null)
/* 103 */       return false; 
/* 106 */     if (!validateShape(tagList, shape, regionsFromDsf, delegate))
/* 107 */       return false; 
/* 110 */     double area = FastMath.sqrt(shape.getArea().floatValue());
/* 112 */     if (this.maxarea != -1.0D && 
/* 113 */       shape.getArea() != null && 
/* 114 */       area > this.maxarea)
/* 115 */       return false; 
/* 120 */     if (this.minarea != -1.0D && 
/* 121 */       shape.getArea() != null && 
/* 122 */       area < this.minarea)
/* 123 */       return false; 
/* 128 */     if (getAreaDensities() != null && getAreaDensities().size() > 0) {
/* 129 */       boolean pass = false;
/* 130 */       for (Density item : getAreaDensities()) {
/* 131 */         if (item.min != -1 && item.max != -1 && area >= item.min && area <= item.max) {
/* 132 */           pass = true;
/*     */           break;
/*     */         } 
/* 135 */         if (item.max != -1 && area <= item.max) {
/* 136 */           pass = true;
/*     */           break;
/*     */         } 
/* 139 */         if (item.min != -1 && area >= item.min) {
/* 140 */           pass = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 144 */       if (!pass)
/* 145 */         return false; 
/*     */     } 
/* 150 */     return true;
/*     */   }
/*     */   
/*     */   public double getDensity() {
/* 154 */     return this.density;
/*     */   }
/*     */   
/*     */   public double getDensity(OSMPolygon shape) {
/* 159 */     double area = FastMath.sqrt(shape.getArea().floatValue());
/* 160 */     if (getAreaDensities() != null && getAreaDensities().size() > 0) {
/* 161 */       boolean pass = false;
/* 162 */       for (Density item : getAreaDensities()) {
/* 163 */         if (item.min != -1 && item.max != -1 && area >= item.min && area <= item.max)
/* 165 */           return item.density; 
/*     */       } 
/*     */     } 
/* 172 */     return this.density;
/*     */   }
/*     */   
/*     */   public void setDensity(double density) {
/* 176 */     this.density = density;
/*     */   }
/*     */   
/*     */   public double getAngle() {
/* 180 */     return this.angle;
/*     */   }
/*     */   
/*     */   public void setAngle(int angle) {
/* 184 */     this.angle = angle;
/*     */   }
/*     */   
/*     */   public boolean isCollisionTest() {
/* 188 */     return this.collisionTest;
/*     */   }
/*     */   
/*     */   public void setCollisionTest(boolean collisionTest) {
/* 192 */     this.collisionTest = collisionTest;
/*     */   }
/*     */   
/*     */   public void setDetermineAngle(boolean determineAngle) {
/* 197 */     this.determineAngle = determineAngle;
/*     */   }
/*     */   
/*     */   public boolean isDetermineAngle() {
/* 201 */     return this.determineAngle;
/*     */   }
/*     */   
/*     */   public void setAngle(double angle) {
/* 205 */     this.angle = angle;
/*     */   }
/*     */   
/*     */   public double getMaxarea() {
/* 209 */     return this.maxarea;
/*     */   }
/*     */   
/*     */   public void setMaxarea(double maxarea) {
/* 213 */     this.maxarea = maxarea;
/*     */   }
/*     */   
/*     */   public double getMinarea() {
/* 217 */     return this.minarea;
/*     */   }
/*     */   
/*     */   public void setMinarea(double minarea) {
/* 221 */     this.minarea = minarea;
/*     */   }
/*     */   
/*     */   public List<Density> getAreaDensities() {
/* 225 */     return this.areaDensities;
/*     */   }
/*     */   
/*     */   public List<Integer> getForestFiles() {
/* 229 */     return this.forestFiles;
/*     */   }
/*     */   
/*     */   public boolean isForestsOnEmpty() {
/* 233 */     return this.forestsOnEmpty;
/*     */   }
/*     */   
/*     */   public void setForestsOnEmpty(boolean forestsOnEmpty) {
/* 237 */     this.forestsOnEmpty = forestsOnEmpty;
/*     */   }
/*     */   
/*     */   public int getRandomForest() {
/* 241 */     int forestNumber = getRandomNumber(0, this.forestFiles.size());
/* 242 */     return ((Integer)this.forestFiles.get(forestNumber)).intValue();
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 246 */     return this.count;
/*     */   }
/*     */   
/*     */   public void setCount(int count) {
/* 250 */     this.count = count;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\RandomRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */