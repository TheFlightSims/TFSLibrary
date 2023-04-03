/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.geotools.data.DataStore;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ 
/*     */ public class UserLayerImpl extends StyledLayerImpl implements UserLayer {
/*  82 */   private DataStore inlineFeatureDatastore = null;
/*     */   
/*  83 */   private SimpleFeatureType inlineFeatureType = null;
/*     */   
/*     */   RemoteOWS remoteOWS;
/*     */   
/*  85 */   List<Style> styles = new ArrayList<Style>();
/*     */   
/*  86 */   List<FeatureTypeConstraint> constraints = new ArrayList<FeatureTypeConstraint>();
/*     */   
/*     */   public RemoteOWS getRemoteOWS() {
/*  89 */     return this.remoteOWS;
/*     */   }
/*     */   
/*     */   public DataStore getInlineFeatureDatastore() {
/*  93 */     return this.inlineFeatureDatastore;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getInlineFeatureType() {
/*  97 */     return this.inlineFeatureType;
/*     */   }
/*     */   
/*     */   public void setInlineFeatureDatastore(DataStore store) {
/* 101 */     this.inlineFeatureDatastore = store;
/*     */   }
/*     */   
/*     */   public void setInlineFeatureType(SimpleFeatureType ft) {
/* 105 */     this.inlineFeatureType = ft;
/*     */   }
/*     */   
/*     */   public void setRemoteOWS(RemoteOWS service) {
/* 110 */     this.remoteOWS = service;
/*     */   }
/*     */   
/*     */   public List<FeatureTypeConstraint> layerFeatureConstraints() {
/* 113 */     return this.constraints;
/*     */   }
/*     */   
/*     */   public FeatureTypeConstraint[] getLayerFeatureConstraints() {
/* 116 */     return this.constraints.<FeatureTypeConstraint>toArray(new FeatureTypeConstraint[0]);
/*     */   }
/*     */   
/*     */   public void setLayerFeatureConstraints(FeatureTypeConstraint[] array) {
/* 120 */     this.constraints.clear();
/* 121 */     this.constraints.addAll(Arrays.asList(array));
/*     */   }
/*     */   
/*     */   public List<Style> userStyles() {
/* 125 */     return this.styles;
/*     */   }
/*     */   
/*     */   public Style[] getUserStyles() {
/* 128 */     return this.styles.<Style>toArray(new Style[0]);
/*     */   }
/*     */   
/*     */   public void setUserStyles(Style[] styles) {
/* 132 */     this.styles.clear();
/* 133 */     this.styles.addAll(Arrays.asList(styles));
/*     */   }
/*     */   
/*     */   public void addUserStyle(Style style) {
/* 137 */     this.styles.add(style);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 141 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object oth) {
/* 145 */     if (this == oth)
/* 146 */       return true; 
/* 149 */     if (oth instanceof UserLayerImpl) {
/* 150 */       UserLayerImpl other = (UserLayerImpl)oth;
/* 152 */       if (!Utilities.equals(this.inlineFeatureDatastore, other.inlineFeatureDatastore) || !Utilities.equals(this.inlineFeatureType, other.inlineFeatureType) || !Utilities.equals(this.remoteOWS, other.remoteOWS) || !Utilities.equals(this.styles, other.styles))
/* 153 */         return false; 
/* 155 */       if (!Utilities.equals(this.constraints, other.constraints))
/* 156 */         return false; 
/* 158 */       return true;
/*     */     } 
/* 161 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\UserLayerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */