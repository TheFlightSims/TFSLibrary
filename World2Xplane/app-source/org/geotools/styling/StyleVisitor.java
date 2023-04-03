package org.geotools.styling;

public interface StyleVisitor {
  void visit(StyledLayerDescriptor paramStyledLayerDescriptor);
  
  void visit(NamedLayer paramNamedLayer);
  
  void visit(UserLayer paramUserLayer);
  
  void visit(FeatureTypeConstraint paramFeatureTypeConstraint);
  
  void visit(Style paramStyle);
  
  void visit(Rule paramRule);
  
  void visit(FeatureTypeStyle paramFeatureTypeStyle);
  
  void visit(Fill paramFill);
  
  void visit(Stroke paramStroke);
  
  void visit(Symbolizer paramSymbolizer);
  
  void visit(PointSymbolizer paramPointSymbolizer);
  
  void visit(LineSymbolizer paramLineSymbolizer);
  
  void visit(PolygonSymbolizer paramPolygonSymbolizer);
  
  void visit(TextSymbolizer paramTextSymbolizer);
  
  void visit(RasterSymbolizer paramRasterSymbolizer);
  
  void visit(Graphic paramGraphic);
  
  void visit(Mark paramMark);
  
  void visit(ExternalGraphic paramExternalGraphic);
  
  void visit(PointPlacement paramPointPlacement);
  
  void visit(AnchorPoint paramAnchorPoint);
  
  void visit(Displacement paramDisplacement);
  
  void visit(LinePlacement paramLinePlacement);
  
  void visit(Halo paramHalo);
  
  void visit(ColorMap paramColorMap);
  
  void visit(ColorMapEntry paramColorMapEntry);
  
  void visit(ContrastEnhancement paramContrastEnhancement);
  
  void visit(ImageOutline paramImageOutline);
  
  void visit(ChannelSelection paramChannelSelection);
  
  void visit(OverlapBehavior paramOverlapBehavior);
  
  void visit(SelectedChannelType paramSelectedChannelType);
  
  void visit(ShadedRelief paramShadedRelief);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyleVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */