package org.apache.xerces.impl.dtd.models;

import java.util.HashMap;
import org.apache.xerces.xni.QName;

public class DFAContentModel implements ContentModelValidator {
  private static String fEpsilonString = "<<CMNODE_EPSILON>>";
  
  private static String fEOCString = "<<CMNODE_EOC>>";
  
  private static final boolean DEBUG_VALIDATE_CONTENT = false;
  
  private QName[] fElemMap = null;
  
  private int[] fElemMapType = null;
  
  private int fElemMapSize = 0;
  
  private boolean fMixed;
  
  private int fEOCPos = 0;
  
  private boolean[] fFinalStateFlags = null;
  
  private CMStateSet[] fFollowList = null;
  
  private CMNode fHeadNode = null;
  
  private int fLeafCount = 0;
  
  private CMLeaf[] fLeafList = null;
  
  private int[] fLeafListType = null;
  
  private int[][] fTransTable = null;
  
  private int fTransTableSize = 0;
  
  private boolean fEmptyContentIsValid = false;
  
  private final QName fQName = new QName();
  
  public DFAContentModel(CMNode paramCMNode, int paramInt, boolean paramBoolean) {
    this.fLeafCount = paramInt;
    this.fMixed = paramBoolean;
    buildDFA(paramCMNode);
  }
  
  public int validate(QName[] paramArrayOfQName, int paramInt1, int paramInt2) {
    if (paramInt2 == 0)
      return this.fEmptyContentIsValid ? -1 : 0; 
    int i = 0;
    for (byte b = 0; b < paramInt2; b++) {
      QName qName = paramArrayOfQName[paramInt1 + b];
      if (!this.fMixed || qName.localpart != null) {
        byte b1;
        for (b1 = 0; b1 < this.fElemMapSize; b1++) {
          int j = this.fElemMapType[b1] & 0xF;
          if (j == 0) {
            if ((this.fElemMap[b1]).rawname == qName.rawname)
              break; 
          } else if (j == 6) {
            String str = (this.fElemMap[b1]).uri;
            if (str == null || str == qName.uri)
              break; 
          } else if ((j == 8) ? (qName.uri == null) : (j == 7 && (this.fElemMap[b1]).uri != qName.uri)) {
            break;
          } 
        } 
        if (b1 == this.fElemMapSize)
          return b; 
        i = this.fTransTable[i][b1];
        if (i == -1)
          return b; 
      } 
    } 
    return !this.fFinalStateFlags[i] ? paramInt2 : -1;
  }
  
  private void buildDFA(CMNode paramCMNode) {
    this.fQName.setValues(null, fEOCString, fEOCString, null);
    CMLeaf cMLeaf = new CMLeaf(this.fQName);
    this.fHeadNode = new CMBinOp(5, paramCMNode, cMLeaf);
    this.fEOCPos = this.fLeafCount;
    cMLeaf.setPosition(this.fLeafCount++);
    this.fLeafList = new CMLeaf[this.fLeafCount];
    this.fLeafListType = new int[this.fLeafCount];
    postTreeBuildInit(this.fHeadNode, 0);
    this.fFollowList = new CMStateSet[this.fLeafCount];
    for (byte b1 = 0; b1 < this.fLeafCount; b1++)
      this.fFollowList[b1] = new CMStateSet(this.fLeafCount); 
    calcFollowList(this.fHeadNode);
    this.fElemMap = new QName[this.fLeafCount];
    this.fElemMapType = new int[this.fLeafCount];
    this.fElemMapSize = 0;
    for (byte b2 = 0; b2 < this.fLeafCount; b2++) {
      this.fElemMap[b2] = new QName();
      QName qName = this.fLeafList[b2].getElement();
      byte b;
      for (b = 0; b < this.fElemMapSize && (this.fElemMap[b]).rawname != qName.rawname; b++);
      if (b == this.fElemMapSize) {
        this.fElemMap[this.fElemMapSize].setValues(qName);
        this.fElemMapType[this.fElemMapSize] = this.fLeafListType[b2];
        this.fElemMapSize++;
      } 
    } 
    int[] arrayOfInt = new int[this.fLeafCount + this.fElemMapSize];
    byte b3 = 0;
    for (byte b4 = 0; b4 < this.fElemMapSize; b4++) {
      for (byte b = 0; b < this.fLeafCount; b++) {
        QName qName1 = this.fLeafList[b].getElement();
        QName qName2 = this.fElemMap[b4];
        if (qName1.rawname == qName2.rawname)
          arrayOfInt[b3++] = b; 
      } 
      arrayOfInt[b3++] = -1;
    } 
    int i = this.fLeafCount * 4;
    CMStateSet[] arrayOfCMStateSet = new CMStateSet[i];
    this.fFinalStateFlags = new boolean[i];
    this.fTransTable = new int[i][];
    CMStateSet cMStateSet = this.fHeadNode.firstPos();
    byte b5 = 0;
    byte b6 = 0;
    this.fTransTable[b6] = makeDefStateList();
    arrayOfCMStateSet[b6] = cMStateSet;
    b6++;
    HashMap hashMap = new HashMap();
    while (b5 < b6) {
      cMStateSet = arrayOfCMStateSet[b5];
      int[] arrayOfInt1 = this.fTransTable[b5];
      this.fFinalStateFlags[b5] = cMStateSet.getBit(this.fEOCPos);
      b5++;
      CMStateSet cMStateSet1 = null;
      byte b7 = 0;
      for (byte b8 = 0; b8 < this.fElemMapSize; b8++) {
        if (cMStateSet1 == null) {
          cMStateSet1 = new CMStateSet(this.fLeafCount);
        } else {
          cMStateSet1.zeroBits();
        } 
        for (int j = arrayOfInt[b7++]; j != -1; j = arrayOfInt[b7++]) {
          if (cMStateSet.getBit(j))
            cMStateSet1.union(this.fFollowList[j]); 
        } 
        if (!cMStateSet1.isEmpty()) {
          Integer integer = (Integer)hashMap.get(cMStateSet1);
          byte b = (integer == null) ? b6 : integer.intValue();
          if (b == b6) {
            arrayOfCMStateSet[b6] = cMStateSet1;
            this.fTransTable[b6] = makeDefStateList();
            hashMap.put(cMStateSet1, new Integer(b6));
            b6++;
            cMStateSet1 = null;
          } 
          arrayOfInt1[b8] = b;
          if (b6 == i) {
            int k = (int)(i * 1.5D);
            CMStateSet[] arrayOfCMStateSet1 = new CMStateSet[k];
            boolean[] arrayOfBoolean = new boolean[k];
            int[][] arrayOfInt2 = new int[k][];
            System.arraycopy(arrayOfCMStateSet, 0, arrayOfCMStateSet1, 0, i);
            System.arraycopy(this.fFinalStateFlags, 0, arrayOfBoolean, 0, i);
            System.arraycopy(this.fTransTable, 0, arrayOfInt2, 0, i);
            i = k;
            arrayOfCMStateSet = arrayOfCMStateSet1;
            this.fFinalStateFlags = arrayOfBoolean;
            this.fTransTable = arrayOfInt2;
          } 
        } 
      } 
    } 
    this.fEmptyContentIsValid = ((CMBinOp)this.fHeadNode).getLeft().isNullable();
    this.fHeadNode = null;
    this.fLeafList = null;
    this.fFollowList = null;
  }
  
  private void calcFollowList(CMNode paramCMNode) {
    if (paramCMNode.type() == 4) {
      calcFollowList(((CMBinOp)paramCMNode).getLeft());
      calcFollowList(((CMBinOp)paramCMNode).getRight());
    } else if (paramCMNode.type() == 5) {
      calcFollowList(((CMBinOp)paramCMNode).getLeft());
      calcFollowList(((CMBinOp)paramCMNode).getRight());
      CMStateSet cMStateSet1 = ((CMBinOp)paramCMNode).getLeft().lastPos();
      CMStateSet cMStateSet2 = ((CMBinOp)paramCMNode).getRight().firstPos();
      for (byte b = 0; b < this.fLeafCount; b++) {
        if (cMStateSet1.getBit(b))
          this.fFollowList[b].union(cMStateSet2); 
      } 
    } else if (paramCMNode.type() == 2 || paramCMNode.type() == 3) {
      calcFollowList(((CMUniOp)paramCMNode).getChild());
      CMStateSet cMStateSet1 = paramCMNode.firstPos();
      CMStateSet cMStateSet2 = paramCMNode.lastPos();
      for (byte b = 0; b < this.fLeafCount; b++) {
        if (cMStateSet2.getBit(b))
          this.fFollowList[b].union(cMStateSet1); 
      } 
    } else if (paramCMNode.type() == 1) {
      calcFollowList(((CMUniOp)paramCMNode).getChild());
    } 
  }
  
  private void dumpTree(CMNode paramCMNode, int paramInt) {
    for (byte b = 0; b < paramInt; b++)
      System.out.print("   "); 
    int i = paramCMNode.type();
    if (i == 4 || i == 5) {
      if (i == 4) {
        System.out.print("Choice Node ");
      } else {
        System.out.print("Seq Node ");
      } 
      if (paramCMNode.isNullable())
        System.out.print("Nullable "); 
      System.out.print("firstPos=");
      System.out.print(paramCMNode.firstPos().toString());
      System.out.print(" lastPos=");
      System.out.println(paramCMNode.lastPos().toString());
      dumpTree(((CMBinOp)paramCMNode).getLeft(), paramInt + 1);
      dumpTree(((CMBinOp)paramCMNode).getRight(), paramInt + 1);
    } else if (paramCMNode.type() == 2) {
      System.out.print("Rep Node ");
      if (paramCMNode.isNullable())
        System.out.print("Nullable "); 
      System.out.print("firstPos=");
      System.out.print(paramCMNode.firstPos().toString());
      System.out.print(" lastPos=");
      System.out.println(paramCMNode.lastPos().toString());
      dumpTree(((CMUniOp)paramCMNode).getChild(), paramInt + 1);
    } else if (paramCMNode.type() == 0) {
      System.out.print("Leaf: (pos=" + ((CMLeaf)paramCMNode).getPosition() + "), " + ((CMLeaf)paramCMNode).getElement() + "(elemIndex=" + ((CMLeaf)paramCMNode).getElement() + ") ");
      if (paramCMNode.isNullable())
        System.out.print(" Nullable "); 
      System.out.print("firstPos=");
      System.out.print(paramCMNode.firstPos().toString());
      System.out.print(" lastPos=");
      System.out.println(paramCMNode.lastPos().toString());
    } else {
      throw new RuntimeException("ImplementationMessages.VAL_NIICM");
    } 
  }
  
  private int[] makeDefStateList() {
    int[] arrayOfInt = new int[this.fElemMapSize];
    for (byte b = 0; b < this.fElemMapSize; b++)
      arrayOfInt[b] = -1; 
    return arrayOfInt;
  }
  
  private int postTreeBuildInit(CMNode paramCMNode, int paramInt) {
    paramCMNode.setMaxStates(this.fLeafCount);
    if ((paramCMNode.type() & 0xF) == 6 || (paramCMNode.type() & 0xF) == 8 || (paramCMNode.type() & 0xF) == 7) {
      QName qName = new QName(null, null, null, ((CMAny)paramCMNode).getURI());
      this.fLeafList[paramInt] = new CMLeaf(qName, ((CMAny)paramCMNode).getPosition());
      this.fLeafListType[paramInt] = paramCMNode.type();
      paramInt++;
    } else if (paramCMNode.type() == 4 || paramCMNode.type() == 5) {
      paramInt = postTreeBuildInit(((CMBinOp)paramCMNode).getLeft(), paramInt);
      paramInt = postTreeBuildInit(((CMBinOp)paramCMNode).getRight(), paramInt);
    } else if (paramCMNode.type() == 2 || paramCMNode.type() == 3 || paramCMNode.type() == 1) {
      paramInt = postTreeBuildInit(((CMUniOp)paramCMNode).getChild(), paramInt);
    } else if (paramCMNode.type() == 0) {
      QName qName = ((CMLeaf)paramCMNode).getElement();
      if (qName.localpart != fEpsilonString) {
        this.fLeafList[paramInt] = (CMLeaf)paramCMNode;
        this.fLeafListType[paramInt] = 0;
        paramInt++;
      } 
    } else {
      throw new RuntimeException("ImplementationMessages.VAL_NIICM: type=" + paramCMNode.type());
    } 
    return paramInt;
  }
  
  static {
    fEpsilonString = fEpsilonString.intern();
    fEOCString = fEOCString.intern();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\models\DFAContentModel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */