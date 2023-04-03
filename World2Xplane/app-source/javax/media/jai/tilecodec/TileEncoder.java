package javax.media.jai.tilecodec;

import java.awt.image.Raster;
import java.io.IOException;
import java.io.OutputStream;

public interface TileEncoder {
  String getFormatName();
  
  TileCodecParameterList getEncodeParameterList();
  
  OutputStream getOutputStream();
  
  void encode(Raster paramRaster) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\TileEncoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */