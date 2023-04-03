/*     */ package com.world2xplane.Rules.Facade;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class FacadeItem {
/*     */   private Float minHeight;
/*     */   
/*     */   private Float maxHeight;
/*     */   
/*     */   private Double minArea;
/*     */   
/*     */   private Double maxArea;
/*     */   
/*  39 */   private Set<String> regions = new HashSet<>();
/*     */   
/*     */   private String facade;
/*     */   
/*     */   private Float roofHeight;
/*     */   
/*     */   private Integer objectDefinition;
/*     */   
/*     */   public Float getMinHeight() {
/*  45 */     return this.minHeight;
/*     */   }
/*     */   
/*     */   public void setMinHeight(Float minHeight) {
/*  49 */     this.minHeight = minHeight;
/*     */   }
/*     */   
/*     */   public Float getMaxHeight() {
/*  53 */     return this.maxHeight;
/*     */   }
/*     */   
/*     */   public void setMaxHeight(Float maxHeight) {
/*  57 */     this.maxHeight = maxHeight;
/*     */   }
/*     */   
/*     */   public Double getMinArea() {
/*  61 */     return this.minArea;
/*     */   }
/*     */   
/*     */   public void setMinArea(Double minArea) {
/*  65 */     this.minArea = minArea;
/*     */   }
/*     */   
/*     */   public Double getMaxArea() {
/*  69 */     return this.maxArea;
/*     */   }
/*     */   
/*     */   public void setMaxArea(Double maxArea) {
/*  73 */     this.maxArea = maxArea;
/*     */   }
/*     */   
/*     */   public Set<String> getRegions() {
/*  77 */     return this.regions;
/*     */   }
/*     */   
/*     */   public void setRegions(Set<String> regions) {
/*  81 */     this.regions = regions;
/*     */   }
/*     */   
/*     */   public String getFacade() {
/*  85 */     return this.facade;
/*     */   }
/*     */   
/*     */   public void setFacade(String facade) {
/*  89 */     this.facade = facade;
/*     */   }
/*     */   
/*     */   public Float getRoofHeight() {
/*  93 */     return this.roofHeight;
/*     */   }
/*     */   
/*     */   public void setRoofHeight(Float roofHeight) {
/*  97 */     this.roofHeight = roofHeight;
/*     */   }
/*     */   
/*     */   public Integer getObjectDefinition() {
/* 101 */     return this.objectDefinition;
/*     */   }
/*     */   
/*     */   public void setObjectDefinition(Integer objectDefinition) {
/* 105 */     this.objectDefinition = objectDefinition;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Facade\FacadeItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */