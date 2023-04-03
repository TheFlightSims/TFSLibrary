/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.net.URL;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import javax.swing.Icon;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ 
/*     */ public abstract class AbstractStyleFactory implements StyleFactory {
/*     */   public abstract TextSymbolizer createTextSymbolizer(Fill paramFill, Font[] paramArrayOfFont, Halo paramHalo, Expression paramExpression, LabelPlacement paramLabelPlacement, String paramString);
/*     */   
/*     */   public abstract ExternalGraphic createExternalGraphic(URL paramURL, String paramString);
/*     */   
/*     */   public abstract ExternalGraphic createExternalGraphic(String paramString1, String paramString2);
/*     */   
/*     */   public abstract ExternalGraphic createExternalGraphic(Icon paramIcon, String paramString);
/*     */   
/*     */   public abstract AnchorPoint createAnchorPoint(Expression paramExpression1, Expression paramExpression2);
/*     */   
/*     */   public abstract Displacement createDisplacement(Expression paramExpression1, Expression paramExpression2);
/*     */   
/*     */   public abstract PointSymbolizer createPointSymbolizer();
/*     */   
/*     */   public abstract Mark createMark(Expression paramExpression1, Stroke paramStroke, Fill paramFill, Expression paramExpression2, Expression paramExpression3);
/*     */   
/*     */   public abstract Mark getCircleMark();
/*     */   
/*     */   public abstract Mark getXMark();
/*     */   
/*     */   public abstract Mark getStarMark();
/*     */   
/*     */   public abstract Mark getSquareMark();
/*     */   
/*     */   public abstract Mark getCrossMark();
/*     */   
/*     */   public abstract Mark getTriangleMark();
/*     */   
/*     */   public abstract FeatureTypeStyle createFeatureTypeStyle(Rule[] paramArrayOfRule);
/*     */   
/*     */   public abstract LinePlacement createLinePlacement(Expression paramExpression);
/*     */   
/*     */   public abstract PolygonSymbolizer createPolygonSymbolizer();
/*     */   
/*     */   public abstract Halo createHalo(Fill paramFill, Expression paramExpression);
/*     */   
/*     */   public abstract Fill createFill(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Graphic paramGraphic);
/*     */   
/*     */   public abstract LineSymbolizer createLineSymbolizer();
/*     */   
/*     */   public abstract PointSymbolizer createPointSymbolizer(Graphic paramGraphic, String paramString);
/*     */   
/*     */   public abstract Style createStyle();
/*     */   
/*     */   public abstract NamedStyle createNamedStyle();
/*     */   
/*     */   public abstract Fill createFill(Expression paramExpression1, Expression paramExpression2);
/*     */   
/*     */   public abstract Fill createFill(Expression paramExpression);
/*     */   
/*     */   public abstract TextSymbolizer createTextSymbolizer();
/*     */   
/*     */   public abstract PointPlacement createPointPlacement(AnchorPoint paramAnchorPoint, Displacement paramDisplacement, Expression paramExpression);
/*     */   
/*     */   public abstract Stroke createStroke(Expression paramExpression1, Expression paramExpression2);
/*     */   
/*     */   public abstract Stroke createStroke(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3);
/*     */   
/*     */   public abstract Stroke createStroke(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Expression paramExpression4, Expression paramExpression5, float[] paramArrayOffloat, Expression paramExpression6, Graphic paramGraphic1, Graphic paramGraphic2);
/*     */   
/*     */   public abstract Rule createRule();
/*     */   
/*     */   public abstract LineSymbolizer createLineSymbolizer(Stroke paramStroke, String paramString);
/*     */   
/*     */   public abstract FeatureTypeStyle createFeatureTypeStyle();
/*     */   
/*     */   public abstract Graphic createGraphic(ExternalGraphic[] paramArrayOfExternalGraphic, Mark[] paramArrayOfMark, Symbol[] paramArrayOfSymbol, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3);
/*     */   
/*     */   public abstract Font createFont(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Expression paramExpression4);
/*     */   
/*     */   public abstract Mark createMark();
/*     */   
/*     */   public abstract PolygonSymbolizer createPolygonSymbolizer(Stroke paramStroke, Fill paramFill, String paramString);
/*     */   
/*     */   public abstract RasterSymbolizer createRasterSymbolizer(String paramString, Expression paramExpression1, ChannelSelection paramChannelSelection, Expression paramExpression2, ColorMap paramColorMap, ContrastEnhancement paramContrastEnhancement, ShadedRelief paramShadedRelief, Symbolizer paramSymbolizer);
/*     */   
/*     */   public abstract RasterSymbolizer getDefaultRasterSymbolizer();
/*     */   
/*     */   public abstract ChannelSelection createChannelSelection(SelectedChannelType[] paramArrayOfSelectedChannelType);
/*     */   
/*     */   public abstract SelectedChannelType createSelectedChannelType(String paramString, Expression paramExpression);
/*     */   
/*     */   public abstract ColorMap createColorMap();
/*     */   
/*     */   public abstract ColorMapEntry createColorMapEntry();
/*     */   
/*     */   public abstract Style getDefaultStyle();
/*     */   
/*     */   public abstract Stroke getDefaultStroke();
/*     */   
/*     */   public abstract Fill getDefaultFill();
/*     */   
/*     */   public abstract Mark getDefaultMark();
/*     */   
/*     */   public abstract PointSymbolizer getDefaultPointSymbolizer();
/*     */   
/*     */   public abstract PolygonSymbolizer getDefaultPolygonSymbolizer();
/*     */   
/*     */   public abstract LineSymbolizer getDefaultLineSymbolizer();
/*     */   
/*     */   public abstract TextSymbolizer getDefaultTextSymbolizer();
/*     */   
/*     */   public abstract Graphic getDefaultGraphic();
/*     */   
/*     */   public abstract Font getDefaultFont();
/*     */   
/*     */   public abstract PointPlacement getDefaultPointPlacement();
/*     */   
/*     */   public Map<RenderingHints.Key, ?> getImplementationHints() {
/* 248 */     return Collections.emptyMap();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\AbstractStyleFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */