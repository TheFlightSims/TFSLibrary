package org.geotools.styling;

import java.util.List;
import org.opengis.filter.Filter;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.style.Description;
import org.opengis.style.GraphicLegend;
import org.opengis.style.Rule;

public interface Rule extends Rule {
  void setName(String paramString);
  
  Description getDescription();
  
  void setDescription(Description paramDescription);
  
  String getTitle();
  
  void setTitle(String paramString);
  
  String getAbstract();
  
  void setAbstract(String paramString);
  
  void setMinScaleDenominator(double paramDouble);
  
  void setMaxScaleDenominator(double paramDouble);
  
  Filter getFilter();
  
  void setFilter(Filter paramFilter);
  
  boolean hasElseFilter();
  
  void setElseFilter(boolean paramBoolean);
  
  void setIsElseFilter(boolean paramBoolean);
  
  GraphicLegend getLegend();
  
  void setLegend(GraphicLegend paramGraphicLegend);
  
  Graphic[] getLegendGraphic();
  
  void setLegendGraphic(Graphic[] paramArrayOfGraphic);
  
  Symbolizer[] getSymbolizers();
  
  List<Symbolizer> symbolizers();
  
  void setSymbolizers(Symbolizer[] paramArrayOfSymbolizer);
  
  OnLineResource getOnlineResource();
  
  void setOnlineResource(OnLineResource paramOnLineResource);
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Rule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */