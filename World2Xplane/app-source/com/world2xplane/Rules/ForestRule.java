/*     */ package com.world2xplane.Rules;
/*     */ 
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ForestRule extends Rule {
/*  33 */   private double density = 255.0D;
/*     */   
/*  34 */   private int randomDensityMin = 0;
/*     */   
/*  35 */   private int randomDensityMax = 0;
/*     */   
/*  37 */   private final List<Integer> forestFiles = new ArrayList<>();
/*     */   
/*     */   private boolean perimeterOnly = false;
/*     */   
/*     */   private boolean removeSharedEdges = false;
/*     */   
/*     */   public ForestRule(GeneratorStore generatorStore) {
/*  43 */     super(generatorStore);
/*     */   }
/*     */   
/*     */   public double getDensity() {
/*  47 */     return this.density;
/*     */   }
/*     */   
/*     */   public void setDensity(int density) {
/*  51 */     this.density = density;
/*     */   }
/*     */   
/*     */   public List<Integer> getForestFiles() {
/*  55 */     return this.forestFiles;
/*     */   }
/*     */   
/*     */   public Integer getDefinitionNumber(Object object) {
/*  60 */     int forestNumber = getRandomNumber(0, this.forestFiles.size());
/*  61 */     return this.forestFiles.get(forestNumber);
/*     */   }
/*     */   
/*     */   public boolean acceptsShape(Byte tagList, OSMPolygon shape, Set<String> regionsFromDsf, Rule.Delegate delegate, boolean acceptOnly) {
/*  69 */     if (shape == null)
/*  70 */       return false; 
/*  73 */     if (!validateShape(tagList, shape, regionsFromDsf, delegate))
/*  74 */       return false; 
/*  77 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isPerimeterOnly() {
/*  81 */     return this.perimeterOnly;
/*     */   }
/*     */   
/*     */   public void setPerimeterOnly(boolean perimeterOnly) {
/*  85 */     this.perimeterOnly = perimeterOnly;
/*     */   }
/*     */   
/*     */   public boolean canBeSplit() {
/*  90 */     return !isPerimeterOnly();
/*     */   }
/*     */   
/*     */   public int getRandomDensityMin() {
/*  94 */     return this.randomDensityMin;
/*     */   }
/*     */   
/*     */   public void setRandomDensityMin(int randomDensityMin) {
/*  98 */     this.randomDensityMin = randomDensityMin;
/*     */   }
/*     */   
/*     */   public int getRandomDensityMax() {
/* 102 */     return this.randomDensityMax;
/*     */   }
/*     */   
/*     */   public void setRandomDensityMax(int randomDensityMax) {
/* 106 */     this.randomDensityMax = randomDensityMax;
/*     */   }
/*     */   
/*     */   public int getRandomDensity() {
/* 110 */     return getRandomNumber(this.randomDensityMin, this.randomDensityMax);
/*     */   }
/*     */   
/*     */   public boolean isRemoveSharedEdges() {
/* 114 */     return this.removeSharedEdges;
/*     */   }
/*     */   
/*     */   public void setRemoveSharedEdges(boolean removeSharedEdges) {
/* 118 */     this.removeSharedEdges = removeSharedEdges;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\ForestRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */