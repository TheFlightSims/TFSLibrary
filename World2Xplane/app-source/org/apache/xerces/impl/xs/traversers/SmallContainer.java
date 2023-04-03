package org.apache.xerces.impl.xs.traversers;

class SmallContainer extends Container {
  String[] keys;
  
  SmallContainer(int paramInt) {
    this.keys = new String[paramInt];
    this.values = new OneAttr[paramInt];
  }
  
  void put(String paramString, OneAttr paramOneAttr) {
    this.keys[this.pos] = paramString;
    this.values[this.pos++] = paramOneAttr;
  }
  
  OneAttr get(String paramString) {
    for (byte b = 0; b < this.pos; b++) {
      if (this.keys[b].equals(paramString))
        return this.values[b]; 
    } 
    return null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\traversers\SmallContainer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */