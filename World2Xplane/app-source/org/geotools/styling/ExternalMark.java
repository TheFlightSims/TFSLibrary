package org.geotools.styling;

import javax.swing.Icon;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.style.ExternalMark;

public interface ExternalMark extends ExternalMark {
  OnLineResource getOnlineResource();
  
  void setOnlineResource(OnLineResource paramOnLineResource);
  
  Icon getInlineContent();
  
  void setInlineContent(Icon paramIcon);
  
  void getInlineContent(Icon paramIcon);
  
  String getFormat();
  
  void setFormat(String paramString);
  
  int getMarkIndex();
  
  void setMarkIndex(int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ExternalMark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */