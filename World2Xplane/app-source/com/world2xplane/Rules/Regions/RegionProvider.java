/*     */ package com.world2xplane.Rules.Regions;
/*     */ 
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class RegionProvider {
/*     */   public static class Region {
/*     */     private String name;
/*     */     
/*     */     private int priority;
/*     */   }
/*     */   
/*  34 */   protected int priority = 0;
/*     */   
/*     */   public abstract Set<String> getRegionsAtPoint(double paramDouble1, double paramDouble2, Set<String> paramSet);
/*     */   
/*     */   public abstract Set<String> getRegionsInside(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);
/*     */   
/*     */   public abstract Set<String> getRegionsContaining(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);
/*     */   
/*     */   public abstract boolean validate();
/*     */   
/*     */   public abstract boolean load();
/*     */   
/*     */   public int getPriority() {
/*  97 */     return this.priority;
/*     */   }
/*     */   
/*     */   public void setPriority(int priority) {
/* 101 */     this.priority = priority;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Regions\RegionProvider.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */