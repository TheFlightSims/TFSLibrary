package org.geotools.styling;

public interface ImageOutline {
  Symbolizer getSymbolizer();
  
  void setSymbolizer(Symbolizer paramSymbolizer);
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ImageOutline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */