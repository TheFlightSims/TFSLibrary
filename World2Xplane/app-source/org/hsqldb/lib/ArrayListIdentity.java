package org.hsqldb.lib;

public class ArrayListIdentity extends HsqlArrayList implements HsqlList {
  public int indexOf(Object paramObject) {
    for (byte b = 0; b < this.elementCount; b++) {
      if (this.elementData[b] == paramObject)
        return b; 
    } 
    return -1;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\ArrayListIdentity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */