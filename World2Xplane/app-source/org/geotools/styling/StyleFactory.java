package org.geotools.styling;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.measure.unit.Unit;
import javax.swing.Icon;
import org.geotools.factory.Factory;
import org.opengis.feature.type.Name;
import org.opengis.filter.Filter;
import org.opengis.filter.Id;
import org.opengis.filter.expression.Expression;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.style.AnchorPoint;
import org.opengis.style.ChannelSelection;
import org.opengis.style.ColorMap;
import org.opengis.style.ColorReplacement;
import org.opengis.style.ContrastEnhancement;
import org.opengis.style.ContrastMethod;
import org.opengis.style.Description;
import org.opengis.style.Displacement;
import org.opengis.style.ExternalMark;
import org.opengis.style.FeatureTypeStyle;
import org.opengis.style.Fill;
import org.opengis.style.Font;
import org.opengis.style.Graphic;
import org.opengis.style.GraphicFill;
import org.opengis.style.GraphicLegend;
import org.opengis.style.GraphicStroke;
import org.opengis.style.GraphicalSymbol;
import org.opengis.style.Halo;
import org.opengis.style.LabelPlacement;
import org.opengis.style.OverlapBehavior;
import org.opengis.style.Rule;
import org.opengis.style.SelectedChannelType;
import org.opengis.style.SemanticType;
import org.opengis.style.ShadedRelief;
import org.opengis.style.Stroke;
import org.opengis.style.StyleFactory;
import org.opengis.style.Symbolizer;
import org.opengis.util.InternationalString;

public interface StyleFactory extends Factory, StyleFactory {
  TextSymbolizer createTextSymbolizer(Fill paramFill, Font[] paramArrayOfFont, Halo paramHalo, Expression paramExpression, LabelPlacement paramLabelPlacement, String paramString);
  
  ExternalGraphic createExternalGraphic(URL paramURL, String paramString);
  
  ExternalGraphic createExternalGraphic(String paramString1, String paramString2);
  
  ExternalGraphic createExternalGraphic(Icon paramIcon, String paramString);
  
  AnchorPoint createAnchorPoint(Expression paramExpression1, Expression paramExpression2);
  
  Displacement createDisplacement(Expression paramExpression1, Expression paramExpression2);
  
  PointSymbolizer createPointSymbolizer();
  
  Mark createMark(Expression paramExpression1, Stroke paramStroke, Fill paramFill, Expression paramExpression2, Expression paramExpression3);
  
  Mark getCircleMark();
  
  Mark getXMark();
  
  Mark getStarMark();
  
  Mark getSquareMark();
  
  Mark getCrossMark();
  
  Mark getTriangleMark();
  
  Extent createExtent(String paramString1, String paramString2);
  
  FeatureTypeConstraint createFeatureTypeConstraint(String paramString, Filter paramFilter, Extent[] paramArrayOfExtent);
  
  LayerFeatureConstraints createLayerFeatureConstraints(FeatureTypeConstraint[] paramArrayOfFeatureTypeConstraint);
  
  FeatureTypeStyle createFeatureTypeStyle(Rule[] paramArrayOfRule);
  
  ImageOutline createImageOutline(Symbolizer paramSymbolizer);
  
  LinePlacement createLinePlacement(Expression paramExpression);
  
  PolygonSymbolizer createPolygonSymbolizer();
  
  Halo createHalo(Fill paramFill, Expression paramExpression);
  
  Fill createFill(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Graphic paramGraphic);
  
  LineSymbolizer createLineSymbolizer();
  
  PointSymbolizer createPointSymbolizer(Graphic paramGraphic, String paramString);
  
  Style createStyle();
  
  NamedStyle createNamedStyle();
  
  Fill createFill(Expression paramExpression1, Expression paramExpression2);
  
  Fill createFill(Expression paramExpression);
  
  TextSymbolizer createTextSymbolizer();
  
  PointPlacement createPointPlacement(AnchorPoint paramAnchorPoint, Displacement paramDisplacement, Expression paramExpression);
  
  Stroke createStroke(Expression paramExpression1, Expression paramExpression2);
  
  Stroke createStroke(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3);
  
  Stroke createStroke(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Expression paramExpression4, Expression paramExpression5, float[] paramArrayOffloat, Expression paramExpression6, Graphic paramGraphic1, Graphic paramGraphic2);
  
  Rule createRule();
  
  LineSymbolizer createLineSymbolizer(Stroke paramStroke, String paramString);
  
  FeatureTypeStyle createFeatureTypeStyle();
  
  Graphic createGraphic(ExternalGraphic[] paramArrayOfExternalGraphic, Mark[] paramArrayOfMark, Symbol[] paramArrayOfSymbol, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3);
  
  Font createFont(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Expression paramExpression4);
  
  Mark createMark();
  
  PolygonSymbolizer createPolygonSymbolizer(Stroke paramStroke, Fill paramFill, String paramString);
  
  RasterSymbolizer createRasterSymbolizer();
  
  RasterSymbolizer createRasterSymbolizer(String paramString, Expression paramExpression1, ChannelSelection paramChannelSelection, Expression paramExpression2, ColorMap paramColorMap, ContrastEnhancement paramContrastEnhancement, ShadedRelief paramShadedRelief, Symbolizer paramSymbolizer);
  
  RasterSymbolizer getDefaultRasterSymbolizer();
  
  ChannelSelection createChannelSelection(SelectedChannelType[] paramArrayOfSelectedChannelType);
  
  ContrastEnhancement createContrastEnhancement();
  
  ContrastEnhancement createContrastEnhancement(Expression paramExpression);
  
  SelectedChannelType createSelectedChannelType(String paramString, ContrastEnhancement paramContrastEnhancement);
  
  SelectedChannelType createSelectedChannelType(String paramString, Expression paramExpression);
  
  ColorMap createColorMap();
  
  ColorMapEntry createColorMapEntry();
  
  Style getDefaultStyle();
  
  Stroke getDefaultStroke();
  
  Fill getDefaultFill();
  
  Mark getDefaultMark();
  
  PointSymbolizer getDefaultPointSymbolizer();
  
  PolygonSymbolizer getDefaultPolygonSymbolizer();
  
  LineSymbolizer getDefaultLineSymbolizer();
  
  TextSymbolizer getDefaultTextSymbolizer();
  
  Graphic createDefaultGraphic();
  
  Graphic getDefaultGraphic();
  
  Font getDefaultFont();
  
  PointPlacement getDefaultPointPlacement();
  
  StyledLayerDescriptor createStyledLayerDescriptor();
  
  UserLayer createUserLayer();
  
  NamedLayer createNamedLayer();
  
  RemoteOWS createRemoteOWS(String paramString1, String paramString2);
  
  ShadedRelief createShadedRelief(Expression paramExpression);
  
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
  
  Graphic graphicFill(List<GraphicalSymbol> paramList, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, AnchorPoint paramAnchorPoint, Displacement paramDisplacement);
  
  GraphicLegend graphicLegend(List<GraphicalSymbol> paramList, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, AnchorPoint paramAnchorPoint, Displacement paramDisplacement);
  
  Graphic graphicStroke(List<GraphicalSymbol> paramList, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, AnchorPoint paramAnchorPoint, Displacement paramDisplacement, Expression paramExpression4, Expression paramExpression5);
  
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyleFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */