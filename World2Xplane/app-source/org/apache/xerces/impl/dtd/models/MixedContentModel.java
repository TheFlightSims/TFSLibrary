package org.apache.xerces.impl.dtd.models;

import org.apache.xerces.xni.QName;

public class MixedContentModel implements ContentModelValidator {
  private final int fCount;
  
  private final QName[] fChildren;
  
  private final int[] fChildrenType;
  
  private final boolean fOrdered;
  
  public MixedContentModel(QName[] paramArrayOfQName, int[] paramArrayOfint, int paramInt1, int paramInt2, boolean paramBoolean) {
    this.fCount = paramInt2;
    this.fChildren = new QName[this.fCount];
    this.fChildrenType = new int[this.fCount];
    for (byte b = 0; b < this.fCount; b++) {
      this.fChildren[b] = new QName(paramArrayOfQName[paramInt1 + b]);
      this.fChildrenType[b] = paramArrayOfint[paramInt1 + b];
    } 
    this.fOrdered = paramBoolean;
  }
  
  public int validate(QName[] paramArrayOfQName, int paramInt1, int paramInt2) {
    if (this.fOrdered) {
      byte b1 = 0;
      for (byte b2 = 0; b2 < paramInt2; b2++) {
        QName qName = paramArrayOfQName[paramInt1 + b2];
        if (qName.localpart != null) {
          int i = this.fChildrenType[b1];
          if (i == 0) {
            if ((this.fChildren[b1]).rawname != (paramArrayOfQName[paramInt1 + b2]).rawname)
              return b2; 
          } else if (i == 6) {
            String str = (this.fChildren[b1]).uri;
            if (str != null && str != (paramArrayOfQName[b2]).uri)
              return b2; 
          } else if (i == 8) {
            if ((paramArrayOfQName[b2]).uri != null)
              return b2; 
          } else if (i == 7 && (this.fChildren[b1]).uri == (paramArrayOfQName[b2]).uri) {
            return b2;
          } 
          b1++;
        } 
      } 
    } else {
      for (byte b = 0; b < paramInt2; b++) {
        QName qName = paramArrayOfQName[paramInt1 + b];
        if (qName.localpart != null) {
          byte b1;
          for (b1 = 0; b1 < this.fCount; b1++) {
            int i = this.fChildrenType[b1];
            if (i == 0) {
              if (qName.rawname == (this.fChildren[b1]).rawname)
                break; 
            } else if (i == 6) {
              String str = (this.fChildren[b1]).uri;
              if (str == null || str == (paramArrayOfQName[b]).uri)
                break; 
            } else if ((i == 8) ? ((paramArrayOfQName[b]).uri == null) : (i == 7 && (this.fChildren[b1]).uri != (paramArrayOfQName[b]).uri)) {
              break;
            } 
          } 
          if (b1 == this.fCount)
            return b; 
        } 
      } 
    } 
    return -1;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\models\MixedContentModel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */