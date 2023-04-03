package org.apache.xml.serialize;

import java.io.OutputStream;
import java.io.Writer;

public class XHTMLSerializer extends HTMLSerializer {
  public XHTMLSerializer() {
    super(true, new OutputFormat("xhtml", null, false));
  }
  
  public XHTMLSerializer(OutputFormat paramOutputFormat) {
    super(true, (paramOutputFormat != null) ? paramOutputFormat : new OutputFormat("xhtml", null, false));
  }
  
  public XHTMLSerializer(Writer paramWriter, OutputFormat paramOutputFormat) {
    super(true, (paramOutputFormat != null) ? paramOutputFormat : new OutputFormat("xhtml", null, false));
    setOutputCharStream(paramWriter);
  }
  
  public XHTMLSerializer(OutputStream paramOutputStream, OutputFormat paramOutputFormat) {
    super(true, (paramOutputFormat != null) ? paramOutputFormat : new OutputFormat("xhtml", null, false));
    setOutputByteStream(paramOutputStream);
  }
  
  public void setOutputFormat(OutputFormat paramOutputFormat) {
    super.setOutputFormat((paramOutputFormat != null) ? paramOutputFormat : new OutputFormat("xhtml", null, false));
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xml\serialize\XHTMLSerializer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */