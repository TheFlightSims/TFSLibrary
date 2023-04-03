/*     */ package com.world2xplane.Rules;
/*     */ 
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class LineRule extends Rule {
/*  34 */   private int count = -1;
/*     */   
/*     */   public static class LineRuleObject {
/*     */     public Integer objectId;
/*     */     
/*     */     public Double width;
/*     */     
/*     */     public Double spacing;
/*     */     
/*     */     public Integer groupId;
/*     */   }
/*     */   
/*  43 */   private final HashMap<Integer, ArrayList<LineRuleObject>> lineRuleObjects = new HashMap<>();
/*     */   
/*  44 */   private final List<Integer> groupIds = new ArrayList<>();
/*     */   
/*  46 */   private final List<Integer> objectFiles = new ArrayList<>();
/*     */   
/*  49 */   private double minDensity = 1.0D;
/*     */   
/*  50 */   private double maxDensity = 1.0D;
/*     */   
/*  52 */   private List<Float> offsetAngles = new ArrayList<>();
/*     */   
/*     */   private float offset;
/*     */   
/*     */   private float angle;
/*     */   
/*     */   private boolean collisionTest = false;
/*     */   
/*     */   private boolean track = false;
/*     */   
/*     */   private boolean spacingDivision = true;
/*     */   
/*     */   private boolean clipToArea = false;
/*     */   
/*     */   public LineRule(GeneratorStore generatorStore) {
/*  65 */     super(generatorStore);
/*     */   }
/*     */   
/*     */   public List<Integer> getObjectFiles() {
/*  70 */     return this.objectFiles;
/*     */   }
/*     */   
/*     */   public int getRandomAngle() {
/*  76 */     return getRandomNumber(1, 359);
/*     */   }
/*     */   
/*     */   public FilterList getFilterList() {
/*  80 */     return this.filterList;
/*     */   }
/*     */   
/*     */   public Integer getDefinitionNumber(Object object) {
/*  85 */     return this.objectFiles.get(getRandomNumber(0, this.objectFiles.size()));
/*     */   }
/*     */   
/*     */   public LineRuleObject getRandomLineRuleObject(Integer groupId) {
/*  89 */     List<LineRuleObject> rules = this.lineRuleObjects.get(groupId);
/*  90 */     if (rules == null)
/*  91 */       return null; 
/*  93 */     return rules.get(getRandomNumber(0, rules.size()));
/*     */   }
/*     */   
/*     */   public Integer getRandomGroup() {
/*  97 */     return this.groupIds.get(getRandomNumber(0, this.groupIds.size()));
/*     */   }
/*     */   
/*     */   public boolean acceptsShape(Byte tagList, OSMPolygon shape, Set<String> possibleRegions, Rule.Delegate delegate, boolean acceptOnly) {
/* 105 */     if (shape == null)
/* 106 */       return false; 
/* 108 */     if (!validateShape(tagList, shape, possibleRegions, delegate))
/* 109 */       return false; 
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   public double getMinDensity() {
/* 115 */     return this.minDensity;
/*     */   }
/*     */   
/*     */   public void setMinDensity(double minDensity) {
/* 119 */     this.minDensity = minDensity;
/*     */   }
/*     */   
/*     */   public double getMaxDensity() {
/* 123 */     return this.maxDensity;
/*     */   }
/*     */   
/*     */   public void setMaxDensity(double maxDensity) {
/* 127 */     this.maxDensity = maxDensity;
/*     */   }
/*     */   
/*     */   public List<Float> getOffsetAngles() {
/* 131 */     return this.offsetAngles;
/*     */   }
/*     */   
/*     */   public void setOffsetAngles(List<Float> offsetAngles) {
/* 135 */     this.offsetAngles = offsetAngles;
/*     */   }
/*     */   
/*     */   public float getOffset() {
/* 139 */     return this.offset;
/*     */   }
/*     */   
/*     */   public void setOffset(float offset) {
/* 143 */     this.offset = offset;
/*     */   }
/*     */   
/*     */   public float getAngle() {
/* 147 */     return this.angle;
/*     */   }
/*     */   
/*     */   public void setAngle(float angle) {
/* 151 */     this.angle = angle;
/*     */   }
/*     */   
/*     */   public boolean isCollisionTest() {
/* 155 */     return this.collisionTest;
/*     */   }
/*     */   
/*     */   public void setCollisionTest(boolean collisionTest) {
/* 159 */     this.collisionTest = collisionTest;
/*     */   }
/*     */   
/*     */   public boolean isTrack() {
/* 163 */     return this.track;
/*     */   }
/*     */   
/*     */   public void setTrack(boolean track) {
/* 167 */     this.track = track;
/*     */   }
/*     */   
/*     */   public void addLineRuleObject(LineRuleObject lineRuleObject) {
/* 171 */     Integer groupId = Integer.valueOf(-1);
/* 172 */     if (lineRuleObject.groupId != null)
/* 173 */       groupId = lineRuleObject.groupId; 
/* 175 */     if (this.lineRuleObjects.get(groupId) == null) {
/* 176 */       this.groupIds.add(groupId);
/* 177 */       this.lineRuleObjects.put(groupId, new ArrayList<>());
/*     */     } 
/* 179 */     ((ArrayList<LineRuleObject>)this.lineRuleObjects.get(groupId)).add(lineRuleObject);
/*     */   }
/*     */   
/*     */   public boolean isSpacingDivision() {
/* 183 */     return this.spacingDivision;
/*     */   }
/*     */   
/*     */   public void setSpacingDivision(boolean spacingDivision) {
/* 187 */     this.spacingDivision = spacingDivision;
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 191 */     return this.count;
/*     */   }
/*     */   
/*     */   public void setCount(int count) {
/* 195 */     this.count = count;
/*     */   }
/*     */   
/*     */   public boolean isClipToArea() {
/* 199 */     return this.clipToArea;
/*     */   }
/*     */   
/*     */   public void setClipToArea(boolean clipToArea) {
/* 203 */     this.clipToArea = clipToArea;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\LineRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */