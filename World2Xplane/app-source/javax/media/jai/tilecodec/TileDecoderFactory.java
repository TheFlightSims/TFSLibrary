package javax.media.jai.tilecodec;

import java.io.InputStream;
import javax.media.jai.remote.NegotiableCapability;

public interface TileDecoderFactory {
  TileDecoder createDecoder(InputStream paramInputStream, TileCodecParameterList paramTileCodecParameterList);
  
  NegotiableCapability getDecodeCapability();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\TileDecoderFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */