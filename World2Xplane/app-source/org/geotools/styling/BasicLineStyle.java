/*    */ package org.geotools.styling;
/*    */ 
/*    */ public class BasicLineStyle extends StyleImpl implements Style {
/*    */   public BasicLineStyle() {
/* 35 */     this(new StrokeImpl());
/*    */   }
/*    */   
/*    */   public BasicLineStyle(Stroke stroke) {
/* 39 */     LineSymbolizerImpl linesym = new LineSymbolizerImpl();
/* 40 */     linesym.setStroke(stroke);
/* 42 */     RuleImpl rule = new RuleImpl();
/* 43 */     rule.setSymbolizers(new Symbolizer[] { linesym });
/* 45 */     FeatureTypeStyleImpl fts = new FeatureTypeStyleImpl();
/* 46 */     fts.setRules(new Rule[] { rule });
/* 47 */     setFeatureTypeStyles(new FeatureTypeStyle[] { fts });
/*    */   }
/*    */   
/*    */   public String getAbstract() {
/* 51 */     return "A simple line style";
/*    */   }
/*    */   
/*    */   public String getName() {
/* 55 */     return "default line style";
/*    */   }
/*    */   
/*    */   public String getTitle() {
/* 59 */     return "default line style";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\BasicLineStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */