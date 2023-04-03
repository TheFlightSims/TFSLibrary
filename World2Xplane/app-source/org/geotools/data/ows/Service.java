/*     */ package org.geotools.data.ows;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.net.URL;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ 
/*     */ public class Service {
/*     */   private String name;
/*     */   
/*     */   private String title;
/*     */   
/*     */   private URL onlineResource;
/*     */   
/*     */   private String[] keywordList;
/*     */   
/*     */   private String _abstract;
/*     */   
/*     */   private ResponsibleParty contactInformation;
/*     */   
/*     */   private int layerLimit;
/*     */   
/*     */   private int maxWidth;
/*     */   
/*     */   private int maxHeight;
/*     */   
/*     */   public String get_abstract() {
/*  66 */     return this._abstract;
/*     */   }
/*     */   
/*     */   public void set_abstract(String _abstract) {
/*  70 */     this._abstract = _abstract;
/*     */   }
/*     */   
/*     */   public String[] getKeywordList() {
/*  74 */     return this.keywordList;
/*     */   }
/*     */   
/*     */   public void setKeywordList(String[] keywordList) {
/*  78 */     this.keywordList = keywordList;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  82 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  86 */     this.name = name;
/*     */   }
/*     */   
/*     */   public URL getOnlineResource() {
/*  90 */     return this.onlineResource;
/*     */   }
/*     */   
/*     */   public void setOnlineResource(URL onlineResource) {
/*  94 */     this.onlineResource = onlineResource;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  98 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/* 102 */     this.title = title;
/*     */   }
/*     */   
/*     */   public int getLayerLimit() {
/* 106 */     return this.layerLimit;
/*     */   }
/*     */   
/*     */   public void setLayerLimit(int layerLimit) {
/* 110 */     this.layerLimit = layerLimit;
/*     */   }
/*     */   
/*     */   public Dimension getMaxDimension() {
/* 114 */     return new Dimension(this.maxWidth, this.maxHeight);
/*     */   }
/*     */   
/*     */   public int getMaxHeight() {
/* 118 */     return this.maxHeight;
/*     */   }
/*     */   
/*     */   public void setMaxHeight(int maxHeight) {
/* 122 */     this.maxHeight = maxHeight;
/*     */   }
/*     */   
/*     */   public int getMaxWidth() {
/* 126 */     return this.maxWidth;
/*     */   }
/*     */   
/*     */   public void setMaxWidth(int maxWidth) {
/* 130 */     this.maxWidth = maxWidth;
/*     */   }
/*     */   
/*     */   public ResponsibleParty getContactInformation() {
/* 142 */     return this.contactInformation;
/*     */   }
/*     */   
/*     */   public void setContactInformation(ResponsibleParty contactInformation) {
/* 146 */     this.contactInformation = contactInformation;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\Service.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */