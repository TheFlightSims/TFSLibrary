package javax.media.jai.tilecodec;

import java.awt.Point;
import java.awt.image.Raster;
import java.io.IOException;
import java.io.InputStream;

public interface TileDecoder {
  String getFormatName();
  
  TileCodecParameterList getDecodeParameterList();
  
  InputStream getInputStream();
  
  Raster decode() throws IOException;
  
  Raster decode(Point paramPoint) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\TileDecoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */