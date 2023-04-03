package org.opengis.style;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.measure.unit.Unit;
import javax.swing.Icon;
import org.opengis.feature.type.Name;
import org.opengis.filter.Filter;
import org.opengis.filter.Id;
import org.opengis.filter.expression.Expression;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.util.InternationalString;

public interface StyleFactory {
  AnchorPoint anchorPoint(Expression paramExpression1, Expression paramExpression2);
  
  ChannelSelection channelSelection(SelectedChannelType paramSelectedChannelType);
  
  ChannelSelection channelSelection(SelectedChannelType paramSelectedChannelType1, SelectedChannelType paramSelectedChannelType2, SelectedChannelType paramSelectedChannelType3);
  
  ColorMap colorMap(Expression paramExpression, Expression... paramVarArgs);
  
  ColorReplacement colorReplacement(Expression paramExpression, Expression... paramVarArgs);
  
  ContrastEnhancement contrastEnhancement(Expression paramExpression, ContrastMethod paramContrastMethod);
  
  Description description(InternationalString paramInternationalString1, InternationalString paramInternationalString2);
  
  Displacement displacement(Expression paramExpression1, Expression paramExpression2);
  
  ExternalGraphic externalGraphic(OnLineResource paramOnLineResource, String paramString, Collection<ColorReplacement> paramCollection);
  
  ExternalGraphic externalGraphic(Icon paramIcon, Collection<ColorReplacement> paramCollection);
  
  ExternalMark externalMark(OnLineResource paramOnLineResource, String paramString, int paramInt);
  
  ExternalMark externalMark(Icon paramIcon);
  
  FeatureTypeStyle featureTypeStyle(String paramString, Description paramDescription, Id paramId, Set<Name> paramSet, Set<SemanticType> paramSet1, List<Rule> paramList);
  
  Fill fill(GraphicFill paramGraphicFill, Expression paramExpression1, Expression paramExpression2);
  
  Font font(List<Expression> paramList, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3);
  
  Graphic graphic(List<GraphicalSymbol> paramList, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, AnchorPoint paramAnchorPoint, Displacement paramDisplacement);
  
  GraphicFill graphicFill(List<GraphicalSymbol> paramList, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, AnchorPoint paramAnchorPoint, Displacement paramDisplacement);
  
  GraphicLegend graphicLegend(List<GraphicalSymbol> paramList, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, AnchorPoint paramAnchorPoint, Displacement paramDisplacement);
  
  GraphicStroke graphicStroke(List<GraphicalSymbol> paramList, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, AnchorPoint paramAnchorPoint, Displacement paramDisplacement, Expression paramExpression4, Expression paramExpression5);
  
  Halo halo(Fill paramFill, Expression paramExpression);
  
  LinePlacement linePlacement(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
  
  LineSymbolizer lineSymbolizer(String paramString, Expression paramExpression1, Description paramDescription, Unit<?> paramUnit, Stroke paramStroke, Expression paramExpression2);
  
  Mark mark(Expression paramExpression, Fill paramFill, Stroke paramStroke);
  
  Mark mark(ExternalMark paramExternalMark, Fill paramFill, Stroke paramStroke);
  
  PointPlacement pointPlacement(AnchorPoint paramAnchorPoint, Displacement paramDisplacement, Expression paramExpression);
  
  PointSymbolizer pointSymbolizer(String paramString, Expression paramExpression, Description paramDescription, Unit<?> paramUnit, Graphic paramGraphic);
  
  PolygonSymbolizer polygonSymbolizer(String paramString, Expression paramExpression1, Description paramDescription, Unit<?> paramUnit, Stroke paramStroke, Fill paramFill, Displacement paramDisplacement, Expression paramExpression2);
  
  RasterSymbolizer rasterSymbolizer(String paramString, Expression paramExpression1, Description paramDescription, Unit<?> paramUnit, Expression paramExpression2, ChannelSelection paramChannelSelection, OverlapBehavior paramOverlapBehavior, ColorMap paramColorMap, ContrastEnhancement paramContrastEnhancement, ShadedRelief paramShadedRelief, Symbolizer paramSymbolizer);
  
  ExtensionSymbolizer extensionSymbolizer(String paramString1, String paramString2, Description paramDescription, Unit<?> paramUnit, String paramString3, Map<String, Expression> paramMap);
  
  Rule rule(String paramString, Description paramDescription, GraphicLegend paramGraphicLegend, double paramDouble1, double paramDouble2, List<Symbolizer> paramList, Filter paramFilter);
  
  SelectedChannelType selectedChannelType(String paramString, ContrastEnhancement paramContrastEnhancement);
  
  ShadedRelief shadedRelief(Expression paramExpression, boolean paramBoolean);
  
  Stroke stroke(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Expression paramExpression4, Expression paramExpression5, float[] paramArrayOffloat, Expression paramExpression6);
  
  Stroke stroke(GraphicFill paramGraphicFill, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Expression paramExpression4, Expression paramExpression5, float[] paramArrayOffloat, Expression paramExpression6);
  
  Stroke stroke(GraphicStroke paramGraphicStroke, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Expression paramExpression4, Expression paramExpression5, float[] paramArrayOffloat, Expression paramExpression6);
  
  Style style(String paramString, Description paramDescription, boolean paramBoolean, List<FeatureTypeStyle> paramList, Symbolizer paramSymbolizer);
  
  TextSymbolizer textSymbolizer(String paramString, Expression paramExpression1, Description paramDescription, Unit<?> paramUnit, Expression paramExpression2, Font paramFont, LabelPlacement paramLabelPlacement, Halo paramHalo, Fill paramFill);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\StyleFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */