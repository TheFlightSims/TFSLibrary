/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class StyledLayerDescriptorImpl implements StyledLayerDescriptor {
/*  73 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.styling");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private String title;
/*     */   
/*     */   private String abstractStr;
/*     */   
/*  85 */   private List<StyledLayer> layers = new ArrayList<StyledLayer>();
/*     */   
/*     */   public Style getDefaultStyle() {
/*  96 */     for (int i = 0; i < this.layers.size(); i++) {
/*  97 */       StyledLayer layer = this.layers.get(i);
/*  99 */       if (layer instanceof UserLayer) {
/* 100 */         UserLayer userLayer = (UserLayer)layer;
/* 103 */         Style[] styles = userLayer.getUserStyles();
/* 105 */         for (int j = 0; j < styles.length; j++) {
/* 107 */           if (styles[j].isDefault())
/* 108 */             return styles[j]; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 114 */     return null;
/*     */   }
/*     */   
/*     */   public StyledLayer[] getStyledLayers() {
/* 118 */     return (StyledLayer[])this.layers.<StyledLayerImpl>toArray(new StyledLayerImpl[this.layers.size()]);
/*     */   }
/*     */   
/*     */   public void setStyledLayers(StyledLayer[] layers) {
/* 123 */     this.layers.clear();
/* 125 */     for (int i = 0; i < layers.length; i++)
/* 126 */       addStyledLayer(layers[i]); 
/* 129 */     LOGGER.fine("StyleLayerDescriptorImpl added " + this.layers.size() + " styled layers");
/*     */   }
/*     */   
/*     */   public List<StyledLayer> layers() {
/* 134 */     return this.layers;
/*     */   }
/*     */   
/*     */   public void addStyledLayer(StyledLayer layer) {
/* 138 */     this.layers.add(layer);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 147 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 156 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/* 165 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/* 174 */     this.title = title;
/*     */   }
/*     */   
/*     */   public String getAbstract() {
/* 183 */     return this.abstractStr;
/*     */   }
/*     */   
/*     */   public void setAbstract(String abstractStr) {
/* 192 */     this.abstractStr = abstractStr;
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 196 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object oth) {
/* 200 */     if (this == oth)
/* 201 */       return true; 
/* 204 */     if (oth instanceof StyledLayerDescriptorImpl) {
/* 205 */       StyledLayerDescriptorImpl other = (StyledLayerDescriptorImpl)oth;
/* 207 */       return (Utilities.equals(this.abstractStr, other.abstractStr) && Utilities.equals(this.layers, other.layers) && Utilities.equals(this.name, other.name) && Utilities.equals(this.title, other.title));
/*     */     } 
/* 213 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyledLayerDescriptorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */