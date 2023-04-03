package org.geotools.styling;

import org.opengis.style.ChannelSelection;

public interface ChannelSelection extends ChannelSelection {
  void setRGBChannels(SelectedChannelType paramSelectedChannelType1, SelectedChannelType paramSelectedChannelType2, SelectedChannelType paramSelectedChannelType3);
  
  void setRGBChannels(SelectedChannelType[] paramArrayOfSelectedChannelType);
  
  SelectedChannelType[] getRGBChannels();
  
  void setGrayChannel(SelectedChannelType paramSelectedChannelType);
  
  SelectedChannelType getGrayChannel();
  
  void setSelectedChannels(SelectedChannelType[] paramArrayOfSelectedChannelType);
  
  SelectedChannelType[] getSelectedChannels();
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ChannelSelection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */