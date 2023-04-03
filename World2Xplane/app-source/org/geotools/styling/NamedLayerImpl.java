/*    */ package org.geotools.styling;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.geotools.util.Utilities;
/*    */ 
/*    */ public class NamedLayerImpl extends StyledLayerImpl implements NamedLayer {
/* 36 */   List<Style> styles = new ArrayList<Style>();
/*    */   
/* 39 */   List<FeatureTypeConstraint> featureTypeConstraints = new ArrayList<FeatureTypeConstraint>();
/*    */   
/*    */   public List<FeatureTypeConstraint> layerFeatureConstraints() {
/* 42 */     return this.featureTypeConstraints;
/*    */   }
/*    */   
/*    */   public FeatureTypeConstraint[] getLayerFeatureConstraints() {
/* 45 */     return this.featureTypeConstraints.<FeatureTypeConstraint>toArray(new FeatureTypeConstraint[0]);
/*    */   }
/*    */   
/*    */   public void setLayerFeatureConstraints(FeatureTypeConstraint[] featureTypeConstraints) {
/* 49 */     this.featureTypeConstraints.clear();
/* 50 */     this.featureTypeConstraints.addAll(Arrays.asList(featureTypeConstraints));
/*    */   }
/*    */   
/*    */   public Style[] getStyles() {
/* 54 */     return this.styles.<Style>toArray(new Style[0]);
/*    */   }
/*    */   
/*    */   public List<Style> styles() {
/* 58 */     return this.styles;
/*    */   }
/*    */   
/*    */   public void addStyle(Style sl) {
/* 62 */     this.styles.add(sl);
/*    */   }
/*    */   
/*    */   public void accept(StyleVisitor visitor) {
/* 66 */     visitor.visit(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object oth) {
/* 70 */     if (this == oth)
/* 71 */       return true; 
/* 74 */     if (oth instanceof NamedLayerImpl) {
/* 75 */       NamedLayerImpl other = (NamedLayerImpl)oth;
/* 77 */       if (!Utilities.equals(this.styles, other.styles))
/* 78 */         return false; 
/* 80 */       if (!Utilities.equals(this.featureTypeConstraints, other.featureTypeConstraints))
/* 81 */         return false; 
/* 83 */       return true;
/*    */     } 
/* 86 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\NamedLayerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */