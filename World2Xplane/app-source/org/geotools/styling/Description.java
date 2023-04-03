package org.geotools.styling;

import org.opengis.style.Description;
import org.opengis.util.InternationalString;

public interface Description extends Description {
  InternationalString getTitle();
  
  void setTitle(InternationalString paramInternationalString);
  
  void setTitle(String paramString);
  
  InternationalString getAbstract();
  
  void setAbstract(InternationalString paramInternationalString);
  
  void setAbstract(String paramString);
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Description.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */