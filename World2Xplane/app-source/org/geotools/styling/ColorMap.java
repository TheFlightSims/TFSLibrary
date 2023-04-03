package org.geotools.styling;

import org.opengis.style.ColorMap;

public interface ColorMap extends ColorMap {
  public static final int TYPE_RAMP = 1;
  
  public static final int TYPE_INTERVALS = 2;
  
  public static final int TYPE_VALUES = 3;
  
  void addColorMapEntry(ColorMapEntry paramColorMapEntry);
  
  ColorMapEntry[] getColorMapEntries();
  
  ColorMapEntry getColorMapEntry(int paramInt);
  
  int getType();
  
  void setType(int paramInt);
  
  void accept(StyleVisitor paramStyleVisitor);
  
  void setExtendedColors(boolean paramBoolean);
  
  boolean getExtendedColors();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ColorMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */