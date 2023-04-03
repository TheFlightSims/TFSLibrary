package org.apache.xerces.dom3.as;

import java.io.OutputStream;
import org.w3c.dom.ls.LSSerializer;

public interface DOMASWriter extends LSSerializer {
  void writeASModel(OutputStream paramOutputStream, ASModel paramASModel) throws Exception;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom3\as\DOMASWriter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */