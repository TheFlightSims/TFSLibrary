/*     */ package com.world2xplane.Rules.Facade.osm2xp;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlTransient;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name = "Facade", propOrder = {"file", "roofColor", "wallColor", "industrial", "commercial", "residential", "sloped", "minVectorLength", "maxVectorLength", "simpleBuildingOnly", "minHeight", "maxHeight"})
/*     */ public class Facade {
/*     */   @XmlElement(required = true)
/*     */   protected String file;
/*     */   
/*     */   @XmlElement(required = true)
/*     */   protected String roofColor;
/*     */   
/*     */   @XmlElement(required = true)
/*     */   protected String wallColor;
/*     */   
/*     */   protected boolean industrial;
/*     */   
/*     */   protected boolean commercial;
/*     */   
/*     */   protected boolean residential;
/*     */   
/*     */   protected boolean sloped;
/*     */   
/*     */   protected double minVectorLength;
/*     */   
/*     */   protected double maxVectorLength;
/*     */   
/*     */   protected boolean simpleBuildingOnly;
/*     */   
/*     */   protected int minHeight;
/*     */   
/*     */   protected int maxHeight;
/*     */   
/*     */   @XmlTransient
/*     */   private String identifier;
/*     */   
/*     */   public String getFile() {
/*  67 */     return this.file;
/*     */   }
/*     */   
/*     */   public void setFile(String value) {
/*  78 */     this.file = value;
/*     */   }
/*     */   
/*     */   public String getRoofColor() {
/*  88 */     return this.roofColor;
/*     */   }
/*     */   
/*     */   public void setRoofColor(String value) {
/*  99 */     this.roofColor = value;
/*     */   }
/*     */   
/*     */   public String getWallColor() {
/* 109 */     return this.wallColor;
/*     */   }
/*     */   
/*     */   public void setWallColor(String value) {
/* 120 */     this.wallColor = value;
/*     */   }
/*     */   
/*     */   public boolean isIndustrial() {
/* 128 */     return this.industrial;
/*     */   }
/*     */   
/*     */   public void setIndustrial(boolean value) {
/* 136 */     this.industrial = value;
/*     */   }
/*     */   
/*     */   public boolean isCommercial() {
/* 144 */     return this.commercial;
/*     */   }
/*     */   
/*     */   public void setCommercial(boolean value) {
/* 152 */     this.commercial = value;
/*     */   }
/*     */   
/*     */   public boolean isResidential() {
/* 160 */     return this.residential;
/*     */   }
/*     */   
/*     */   public void setResidential(boolean value) {
/* 168 */     this.residential = value;
/*     */   }
/*     */   
/*     */   public boolean isSloped() {
/* 176 */     return this.sloped;
/*     */   }
/*     */   
/*     */   public void setSloped(boolean value) {
/* 184 */     this.sloped = value;
/*     */   }
/*     */   
/*     */   public double getMinVectorLength() {
/* 192 */     return this.minVectorLength;
/*     */   }
/*     */   
/*     */   public void setMinVectorLength(double value) {
/* 200 */     this.minVectorLength = value;
/*     */   }
/*     */   
/*     */   public double getMaxVectorLength() {
/* 208 */     return this.maxVectorLength;
/*     */   }
/*     */   
/*     */   public void setMaxVectorLength(double value) {
/* 216 */     this.maxVectorLength = value;
/*     */   }
/*     */   
/*     */   public boolean isSimpleBuildingOnly() {
/* 224 */     return this.simpleBuildingOnly;
/*     */   }
/*     */   
/*     */   public void setSimpleBuildingOnly(boolean value) {
/* 232 */     this.simpleBuildingOnly = value;
/*     */   }
/*     */   
/*     */   public int getMinHeight() {
/* 240 */     return this.minHeight;
/*     */   }
/*     */   
/*     */   public void setMinHeight(int value) {
/* 248 */     this.minHeight = value;
/*     */   }
/*     */   
/*     */   public int getMaxHeight() {
/* 256 */     return this.maxHeight;
/*     */   }
/*     */   
/*     */   public void setMaxHeight(int value) {
/* 264 */     this.maxHeight = value;
/*     */   }
/*     */   
/*     */   public String getIdentifier() {
/* 268 */     return this.identifier;
/*     */   }
/*     */   
/*     */   public void setIdentifier(String identifier) {
/* 272 */     this.identifier = identifier;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Facade\osm2xp\Facade.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */