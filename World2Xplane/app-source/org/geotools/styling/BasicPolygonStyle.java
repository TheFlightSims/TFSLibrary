/*    */ package org.geotools.styling;
/*    */ 
/*    */ public class BasicPolygonStyle extends StyleImpl implements Style {
/*    */   public BasicPolygonStyle() {
/* 35 */     this(new FillImpl(), new StrokeImpl());
/*    */   }
/*    */   
/*    */   public BasicPolygonStyle(Fill fill, Stroke stroke) {
/* 39 */     PolygonSymbolizerImpl polysym = new PolygonSymbolizerImpl();
/* 40 */     polysym.setFill(fill);
/* 41 */     polysym.setStroke(stroke);
/* 43 */     RuleImpl rule = new RuleImpl();
/* 44 */     rule.setSymbolizers(new Symbolizer[] { polysym });
/* 46 */     FeatureTypeStyleImpl fts = new FeatureTypeStyleImpl();
/* 47 */     fts.setRules(new Rule[] { rule });
/* 48 */     setFeatureTypeStyles(new FeatureTypeStyle[] { fts });
/*    */   }
/*    */   
/*    */   public String getAbstract() {
/* 52 */     return "A simple polygon style";
/*    */   }
/*    */   
/*    */   public String getName() {
/* 56 */     return "default polygon style";
/*    */   }
/*    */   
/*    */   public String getTitle() {
/* 60 */     return "default polygon style";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\BasicPolygonStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */