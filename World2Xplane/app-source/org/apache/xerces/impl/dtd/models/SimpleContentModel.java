package org.apache.xerces.impl.dtd.models;

import org.apache.xerces.xni.QName;

public class SimpleContentModel implements ContentModelValidator {
  public static final short CHOICE = -1;
  
  public static final short SEQUENCE = -1;
  
  private final QName fFirstChild = new QName();
  
  private final QName fSecondChild = new QName();
  
  private final int fOperator;
  
  public SimpleContentModel(short paramShort, QName paramQName1, QName paramQName2) {
    this.fFirstChild.setValues(paramQName1);
    if (paramQName2 != null) {
      this.fSecondChild.setValues(paramQName2);
    } else {
      this.fSecondChild.clear();
    } 
    this.fOperator = paramShort;
  }
  
  public int validate(QName[] paramArrayOfQName, int paramInt1, int paramInt2) {
    byte b;
    switch (this.fOperator) {
      case 0:
        return (paramInt2 == 0) ? 0 : (((paramArrayOfQName[paramInt1]).rawname != this.fFirstChild.rawname) ? 0 : ((paramInt2 > 1) ? 1 : -1));
      case 1:
        return (paramInt2 == 1 && (paramArrayOfQName[paramInt1]).rawname != this.fFirstChild.rawname) ? 0 : ((paramInt2 > 1) ? 1 : -1);
      case 2:
        if (paramInt2 > 0)
          for (byte b1 = 0; b1 < paramInt2; b1++) {
            if ((paramArrayOfQName[paramInt1 + b1]).rawname != this.fFirstChild.rawname)
              return b1; 
          }  
        return -1;
      case 3:
        if (paramInt2 == 0)
          return 0; 
        for (b = 0; b < paramInt2; b++) {
          if ((paramArrayOfQName[paramInt1 + b]).rawname != this.fFirstChild.rawname)
            return b; 
        } 
        return -1;
      case 4:
        return (paramInt2 == 0) ? 0 : (((paramArrayOfQName[paramInt1]).rawname != this.fFirstChild.rawname && (paramArrayOfQName[paramInt1]).rawname != this.fSecondChild.rawname) ? 0 : ((paramInt2 > 1) ? 1 : -1));
      case 5:
        if (paramInt2 == 2) {
          if ((paramArrayOfQName[paramInt1]).rawname != this.fFirstChild.rawname)
            return 0; 
          if ((paramArrayOfQName[paramInt1 + 1]).rawname != this.fSecondChild.rawname)
            return 1; 
        } else {
          return (paramInt2 > 2) ? 2 : paramInt2;
        } 
        return -1;
    } 
    throw new RuntimeException("ImplementationMessages.VAL_CST");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\models\SimpleContentModel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */