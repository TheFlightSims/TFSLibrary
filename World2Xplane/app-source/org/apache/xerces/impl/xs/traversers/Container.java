package org.apache.xerces.impl.xs.traversers;

abstract class Container {
  static final int THRESHOLD = 5;
  
  OneAttr[] values;
  
  int pos = 0;
  
  static Container getContainer(int paramInt) {
    return (Container)((paramInt > 5) ? new LargeContainer(paramInt) : new SmallContainer(paramInt));
  }
  
  abstract void put(String paramString, OneAttr paramOneAttr);
  
  abstract OneAttr get(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\traversers\Container.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */