package javax.media.jai.tilecodec;

import java.awt.image.SampleModel;
import javax.media.jai.RegistryElementDescriptor;

public interface TileCodecDescriptor extends RegistryElementDescriptor {
  boolean includesSampleModelInfo();
  
  boolean includesLocationInfo();
  
  TileCodecParameterList getDefaultParameters(String paramString);
  
  TileCodecParameterList getDefaultParameters(String paramString, SampleModel paramSampleModel);
  
  TileCodecParameterList getCompatibleParameters(String paramString, TileCodecParameterList paramTileCodecParameterList);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\TileCodecDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */