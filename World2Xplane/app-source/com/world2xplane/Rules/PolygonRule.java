/*     */ package com.world2xplane.Rules;
/*     */ 
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class PolygonRule extends Rule {
/*  36 */   private int minArea = -1;
/*     */   
/*  37 */   private int maxArea = -1;
/*     */   
/*  38 */   private Boolean skipLastPoint = Boolean.valueOf(true);
/*     */   
/*     */   private Boolean building;
/*     */   
/*     */   private boolean simple;
/*     */   
/*     */   private String areaType;
/*     */   
/*     */   private boolean allowUnclosed = false;
/*     */   
/*  45 */   private float roofHeight = 0.0F;
/*     */   
/*  48 */   private final List<Integer> polygonFiles = new ArrayList<>();
/*     */   
/*     */   public PolygonRule(GeneratorStore generatorStore) {
/*  51 */     super(generatorStore);
/*     */   }
/*     */   
/*     */   public List<Integer> getPolygonFiles() {
/*  56 */     return this.polygonFiles;
/*     */   }
/*     */   
/*     */   public Integer getDefinitionNumber(Object object) {
/*  61 */     int polygonNumber = getRandomNumber(0, this.polygonFiles.size());
/*  62 */     return this.polygonFiles.get(polygonNumber);
/*     */   }
/*     */   
/*     */   public boolean isSimple() {
/*  67 */     return this.simple;
/*     */   }
/*     */   
/*     */   public void setSimple(boolean simple) {
/*  71 */     this.simple = simple;
/*     */   }
/*     */   
/*     */   public boolean acceptsShape(Byte tagList, OSMPolygon shape, Set<String> regionsFromDsf, Rule.Delegate delegate, boolean acceptOnly) {
/*  82 */     if (shape == null)
/*  83 */       return false; 
/*  87 */     if (!validateShape(tagList, shape, regionsFromDsf, delegate))
/*  88 */       return false; 
/*  91 */     boolean accept = true;
/*  94 */     if (this.minArea != -1 || this.maxArea != -1) {
/*  95 */       double area = shape.getArea().floatValue();
/*  96 */       if (area != 0.0D) {
/*  97 */         if (this.minArea != -1 && area < (this.minArea * this.minArea))
/*  98 */           accept = false; 
/* 100 */         if (this.maxArea != -1 && area > (this.maxArea * this.maxArea))
/* 101 */           accept = false; 
/*     */       } 
/*     */     } 
/* 107 */     return accept;
/*     */   }
/*     */   
/*     */   public int getMinArea() {
/* 111 */     return this.minArea;
/*     */   }
/*     */   
/*     */   public void setMinArea(int minArea) {
/* 115 */     this.minArea = minArea;
/*     */   }
/*     */   
/*     */   public int getMaxArea() {
/* 119 */     return this.maxArea;
/*     */   }
/*     */   
/*     */   public void setMaxArea(int maxArea) {
/* 123 */     this.maxArea = maxArea;
/*     */   }
/*     */   
/*     */   public String getAreaType() {
/* 127 */     return this.areaType;
/*     */   }
/*     */   
/*     */   public void setAreaType(String areaType) {
/* 131 */     this.areaType = areaType;
/*     */   }
/*     */   
/*     */   public boolean isAllowUnclosed() {
/* 136 */     return this.allowUnclosed;
/*     */   }
/*     */   
/*     */   public void setAllowUnclosed(boolean allowUnclosed) {
/* 140 */     this.allowUnclosed = allowUnclosed;
/*     */   }
/*     */   
/*     */   public float getRoofHeight() {
/* 144 */     return this.roofHeight;
/*     */   }
/*     */   
/*     */   public void setRoofHeight(float roofHeight) {
/* 148 */     this.roofHeight = roofHeight;
/*     */   }
/*     */   
/*     */   public Boolean isSkipLastPoint() {
/* 152 */     return this.skipLastPoint;
/*     */   }
/*     */   
/*     */   public void setSkipLastPoint(Boolean skipLastPoint) {
/* 156 */     this.skipLastPoint = skipLastPoint;
/*     */   }
/*     */   
/*     */   public Boolean getBuilding() {
/* 160 */     return this.building;
/*     */   }
/*     */   
/*     */   public void setBuilding(Boolean building) {
/* 164 */     this.building = building;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\PolygonRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */