package org.codehaus.stax2.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public abstract class Stax2BlockResult extends Stax2Result {
  public abstract Writer constructWriter() throws IOException;
  
  public abstract OutputStream constructOutputStream() throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\io\Stax2BlockResult.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */