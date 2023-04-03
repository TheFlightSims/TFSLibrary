package javax.media.jai.tilecodec;

import java.awt.image.SampleModel;
import java.io.OutputStream;
import javax.media.jai.remote.NegotiableCapability;

public interface TileEncoderFactory {
  TileEncoder createEncoder(OutputStream paramOutputStream, TileCodecParameterList paramTileCodecParameterList, SampleModel paramSampleModel);
  
  NegotiableCapability getEncodeCapability();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\TileEncoderFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */