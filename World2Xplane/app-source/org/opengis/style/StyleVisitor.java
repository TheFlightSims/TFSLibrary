package org.opengis.style;

public interface StyleVisitor {
  Object visit(Style paramStyle, Object paramObject);
  
  Object visit(FeatureTypeStyle paramFeatureTypeStyle, Object paramObject);
  
  Object visit(Rule paramRule, Object paramObject);
  
  Object visit(PointSymbolizer paramPointSymbolizer, Object paramObject);
  
  Object visit(LineSymbolizer paramLineSymbolizer, Object paramObject);
  
  Object visit(PolygonSymbolizer paramPolygonSymbolizer, Object paramObject);
  
  Object visit(TextSymbolizer paramTextSymbolizer, Object paramObject);
  
  Object visit(RasterSymbolizer paramRasterSymbolizer, Object paramObject);
  
  Object visit(ExtensionSymbolizer paramExtensionSymbolizer, Object paramObject);
  
  Object visit(Description paramDescription, Object paramObject);
  
  Object visit(Displacement paramDisplacement, Object paramObject);
  
  Object visit(Fill paramFill, Object paramObject);
  
  Object visit(Font paramFont, Object paramObject);
  
  Object visit(Stroke paramStroke, Object paramObject);
  
  Object visit(Graphic paramGraphic, Object paramObject);
  
  Object visit(GraphicFill paramGraphicFill, Object paramObject);
  
  Object visit(GraphicStroke paramGraphicStroke, Object paramObject);
  
  Object visit(Mark paramMark, Object paramObject);
  
  Object visit(ExternalMark paramExternalMark, Object paramObject);
  
  Object visit(ExternalGraphic paramExternalGraphic, Object paramObject);
  
  Object visit(PointPlacement paramPointPlacement, Object paramObject);
  
  Object visit(AnchorPoint paramAnchorPoint, Object paramObject);
  
  Object visit(LinePlacement paramLinePlacement, Object paramObject);
  
  Object visit(GraphicLegend paramGraphicLegend, Object paramObject);
  
  Object visit(Halo paramHalo, Object paramObject);
  
  Object visit(ColorMap paramColorMap, Object paramObject);
  
  Object visit(ColorReplacement paramColorReplacement, Object paramObject);
  
  Object visit(ContrastEnhancement paramContrastEnhancement, Object paramObject);
  
  Object visit(ChannelSelection paramChannelSelection, Object paramObject);
  
  Object visit(SelectedChannelType paramSelectedChannelType, Object paramObject);
  
  Object visit(ShadedRelief paramShadedRelief, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\StyleVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */