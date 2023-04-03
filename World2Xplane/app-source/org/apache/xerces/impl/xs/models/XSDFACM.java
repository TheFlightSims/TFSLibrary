package org.apache.xerces.impl.xs.models;

import java.util.HashMap;
import java.util.Vector;
import org.apache.xerces.impl.dtd.models.CMNode;
import org.apache.xerces.impl.dtd.models.CMStateSet;
import org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.impl.xs.XSConstraints;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.XSWildcardDecl;
import org.apache.xerces.xni.QName;

public class XSDFACM implements XSCMValidator {
  private static final boolean DEBUG = false;
  
  private static final boolean DEBUG_VALIDATE_CONTENT = false;
  
  private Object[] fElemMap = null;
  
  private int[] fElemMapType = null;
  
  private int[] fElemMapId = null;
  
  private int fElemMapSize = 0;
  
  private boolean[] fFinalStateFlags = null;
  
  private CMStateSet[] fFollowList = null;
  
  private CMNode fHeadNode = null;
  
  private int fLeafCount = 0;
  
  private XSCMLeaf[] fLeafList = null;
  
  private int[] fLeafListType = null;
  
  private int[][] fTransTable = null;
  
  private Occurence[] fCountingStates = null;
  
  private int fTransTableSize = 0;
  
  private boolean fIsCompactedForUPA;
  
  private static long time = 0L;
  
  public XSDFACM(CMNode paramCMNode, int paramInt) {
    this.fLeafCount = paramInt;
    this.fIsCompactedForUPA = paramCMNode.isCompactedForUPA();
    buildDFA(paramCMNode);
  }
  
  public boolean isFinalState(int paramInt) {
    return (paramInt < 0) ? false : this.fFinalStateFlags[paramInt];
  }
  
  public Object oneTransition(QName paramQName, int[] paramArrayOfint, SubstitutionGroupHandler paramSubstitutionGroupHandler) {
    int i = paramArrayOfint[0];
    if (i == -1 || i == -2) {
      if (i == -1)
        paramArrayOfint[0] = -2; 
      return findMatchingDecl(paramQName, paramSubstitutionGroupHandler);
    } 
    int j = 0;
    byte b = 0;
    Object object = null;
    while (b < this.fElemMapSize) {
      j = this.fTransTable[i][b];
      if (j != -1) {
        int k = this.fElemMapType[b];
        if (k == 1) {
          object = paramSubstitutionGroupHandler.getMatchingElemDecl(paramQName, (XSElementDecl)this.fElemMap[b]);
          if (object != null)
            break; 
        } else if (k == 2 && ((XSWildcardDecl)this.fElemMap[b]).allowNamespace(paramQName.uri)) {
          object = this.fElemMap[b];
          break;
        } 
      } 
      b++;
    } 
    if (b == this.fElemMapSize) {
      paramArrayOfint[1] = paramArrayOfint[0];
      paramArrayOfint[0] = -1;
      return findMatchingDecl(paramQName, paramSubstitutionGroupHandler);
    } 
    if (this.fCountingStates != null) {
      Occurence occurence = this.fCountingStates[i];
      if (occurence != null) {
        if (i == j) {
          paramArrayOfint[2] = paramArrayOfint[2] + 1;
          if (paramArrayOfint[2] + 1 > occurence.maxOccurs && occurence.maxOccurs != -1)
            return findMatchingDecl(paramQName, paramArrayOfint, paramSubstitutionGroupHandler, b); 
        } else {
          if (paramArrayOfint[2] < occurence.minOccurs) {
            paramArrayOfint[1] = paramArrayOfint[0];
            paramArrayOfint[0] = -1;
            return findMatchingDecl(paramQName, paramSubstitutionGroupHandler);
          } 
          occurence = this.fCountingStates[j];
          if (occurence != null)
            paramArrayOfint[2] = (b == occurence.elemIndex) ? 1 : 0; 
        } 
      } else {
        occurence = this.fCountingStates[j];
        if (occurence != null)
          paramArrayOfint[2] = (b == occurence.elemIndex) ? 1 : 0; 
      } 
    } 
    paramArrayOfint[0] = j;
    return object;
  }
  
  Object findMatchingDecl(QName paramQName, SubstitutionGroupHandler paramSubstitutionGroupHandler) {
    XSElementDecl xSElementDecl = null;
    for (byte b = 0; b < this.fElemMapSize; b++) {
      int i = this.fElemMapType[b];
      if (i == 1) {
        xSElementDecl = paramSubstitutionGroupHandler.getMatchingElemDecl(paramQName, (XSElementDecl)this.fElemMap[b]);
        if (xSElementDecl != null)
          return xSElementDecl; 
      } else if (i == 2 && ((XSWildcardDecl)this.fElemMap[b]).allowNamespace(paramQName.uri)) {
        return this.fElemMap[b];
      } 
    } 
    return null;
  }
  
  Object findMatchingDecl(QName paramQName, int[] paramArrayOfint, SubstitutionGroupHandler paramSubstitutionGroupHandler, int paramInt) {
    int i = paramArrayOfint[0];
    int j = 0;
    Object object = null;
    while (++paramInt < this.fElemMapSize) {
      j = this.fTransTable[i][paramInt];
      if (j == -1)
        continue; 
      int k = this.fElemMapType[paramInt];
      if (k == 1) {
        object = paramSubstitutionGroupHandler.getMatchingElemDecl(paramQName, (XSElementDecl)this.fElemMap[paramInt]);
        if (object != null)
          break; 
        continue;
      } 
      if (k == 2 && ((XSWildcardDecl)this.fElemMap[paramInt]).allowNamespace(paramQName.uri)) {
        object = this.fElemMap[paramInt];
        break;
      } 
    } 
    if (paramInt == this.fElemMapSize) {
      paramArrayOfint[1] = paramArrayOfint[0];
      paramArrayOfint[0] = -1;
      return findMatchingDecl(paramQName, paramSubstitutionGroupHandler);
    } 
    paramArrayOfint[0] = j;
    Occurence occurence = this.fCountingStates[j];
    if (occurence != null)
      paramArrayOfint[2] = (paramInt == occurence.elemIndex) ? 1 : 0; 
    return object;
  }
  
  public int[] startContentModel() {
    return new int[3];
  }
  
  public boolean endContentModel(int[] paramArrayOfint) {
    int i = paramArrayOfint[0];
    if (this.fFinalStateFlags[i]) {
      if (this.fCountingStates != null) {
        Occurence occurence = this.fCountingStates[i];
        if (occurence != null && paramArrayOfint[2] < occurence.minOccurs)
          return false; 
      } 
      return true;
    } 
    return false;
  }
  
  private void buildDFA(CMNode paramCMNode) {
    int i = this.fLeafCount;
    XSCMLeaf xSCMLeaf = new XSCMLeaf(1, null, -1, this.fLeafCount++);
    this.fHeadNode = new XSCMBinOp(102, paramCMNode, xSCMLeaf);
    this.fLeafList = new XSCMLeaf[this.fLeafCount];
    this.fLeafListType = new int[this.fLeafCount];
    postTreeBuildInit(this.fHeadNode);
    this.fFollowList = new CMStateSet[this.fLeafCount];
    for (byte b1 = 0; b1 < this.fLeafCount; b1++)
      this.fFollowList[b1] = new CMStateSet(this.fLeafCount); 
    calcFollowList(this.fHeadNode);
    this.fElemMap = new Object[this.fLeafCount];
    this.fElemMapType = new int[this.fLeafCount];
    this.fElemMapId = new int[this.fLeafCount];
    this.fElemMapSize = 0;
    Occurence[] arrayOfOccurence = null;
    for (byte b2 = 0; b2 < this.fLeafCount; b2++) {
      this.fElemMap[b2] = null;
      byte b = 0;
      int k = this.fLeafList[b2].getParticleId();
      while (b < this.fElemMapSize && k != this.fElemMapId[b])
        b++; 
      if (b == this.fElemMapSize) {
        XSCMLeaf xSCMLeaf1 = this.fLeafList[b2];
        this.fElemMap[this.fElemMapSize] = xSCMLeaf1.getLeaf();
        if (xSCMLeaf1 instanceof XSCMRepeatingLeaf) {
          if (arrayOfOccurence == null)
            arrayOfOccurence = new Occurence[this.fLeafCount]; 
          arrayOfOccurence[this.fElemMapSize] = new Occurence((XSCMRepeatingLeaf)xSCMLeaf1, this.fElemMapSize);
        } 
        this.fElemMapType[this.fElemMapSize] = this.fLeafListType[b2];
        this.fElemMapId[this.fElemMapSize] = k;
        this.fElemMapSize++;
      } 
    } 
    this.fElemMapSize--;
    int[] arrayOfInt = new int[this.fLeafCount + this.fElemMapSize];
    byte b3 = 0;
    for (byte b4 = 0; b4 < this.fElemMapSize; b4++) {
      int k = this.fElemMapId[b4];
      for (byte b = 0; b < this.fLeafCount; b++) {
        if (k == this.fLeafList[b].getParticleId())
          arrayOfInt[b3++] = b; 
      } 
      arrayOfInt[b3++] = -1;
    } 
    int j = this.fLeafCount * 4;
    CMStateSet[] arrayOfCMStateSet = new CMStateSet[j];
    this.fFinalStateFlags = new boolean[j];
    this.fTransTable = new int[j][];
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
      this.fFinalStateFlags[b5] = cMStateSet.getBit(i);
      b5++;
      CMStateSet cMStateSet1 = null;
      byte b7 = 0;
      for (byte b8 = 0; b8 < this.fElemMapSize; b8++) {
        if (cMStateSet1 == null) {
          cMStateSet1 = new CMStateSet(this.fLeafCount);
        } else {
          cMStateSet1.zeroBits();
        } 
        for (int k = arrayOfInt[b7++]; k != -1; k = arrayOfInt[b7++]) {
          if (cMStateSet.getBit(k))
            cMStateSet1.union(this.fFollowList[k]); 
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
          if (b6 == j) {
            int m = (int)(j * 1.5D);
            CMStateSet[] arrayOfCMStateSet1 = new CMStateSet[m];
            boolean[] arrayOfBoolean = new boolean[m];
            int[][] arrayOfInt2 = new int[m][];
            System.arraycopy(arrayOfCMStateSet, 0, arrayOfCMStateSet1, 0, j);
            System.arraycopy(this.fFinalStateFlags, 0, arrayOfBoolean, 0, j);
            System.arraycopy(this.fTransTable, 0, arrayOfInt2, 0, j);
            j = m;
            arrayOfCMStateSet = arrayOfCMStateSet1;
            this.fFinalStateFlags = arrayOfBoolean;
            this.fTransTable = arrayOfInt2;
          } 
        } 
      } 
    } 
    if (arrayOfOccurence != null) {
      this.fCountingStates = new Occurence[b6];
      for (byte b = 0; b < b6; b++) {
        int[] arrayOfInt1 = this.fTransTable[b];
        for (byte b7 = 0; b7 < arrayOfInt1.length; b7++) {
          if (b == arrayOfInt1[b7]) {
            this.fCountingStates[b] = arrayOfOccurence[b7];
            break;
          } 
        } 
      } 
    } 
    this.fHeadNode = null;
    this.fLeafList = null;
    this.fFollowList = null;
    this.fLeafListType = null;
    this.fElemMapId = null;
  }
  
  private void calcFollowList(CMNode paramCMNode) {
    if (paramCMNode.type() == 101) {
      calcFollowList(((XSCMBinOp)paramCMNode).getLeft());
      calcFollowList(((XSCMBinOp)paramCMNode).getRight());
    } else if (paramCMNode.type() == 102) {
      calcFollowList(((XSCMBinOp)paramCMNode).getLeft());
      calcFollowList(((XSCMBinOp)paramCMNode).getRight());
      CMStateSet cMStateSet1 = ((XSCMBinOp)paramCMNode).getLeft().lastPos();
      CMStateSet cMStateSet2 = ((XSCMBinOp)paramCMNode).getRight().firstPos();
      for (byte b = 0; b < this.fLeafCount; b++) {
        if (cMStateSet1.getBit(b))
          this.fFollowList[b].union(cMStateSet2); 
      } 
    } else if (paramCMNode.type() == 4 || paramCMNode.type() == 6) {
      calcFollowList(((XSCMUniOp)paramCMNode).getChild());
      CMStateSet cMStateSet1 = paramCMNode.firstPos();
      CMStateSet cMStateSet2 = paramCMNode.lastPos();
      for (byte b = 0; b < this.fLeafCount; b++) {
        if (cMStateSet2.getBit(b))
          this.fFollowList[b].union(cMStateSet1); 
      } 
    } else if (paramCMNode.type() == 5) {
      calcFollowList(((XSCMUniOp)paramCMNode).getChild());
    } 
  }
  
  private void dumpTree(CMNode paramCMNode, int paramInt) {
    for (byte b = 0; b < paramInt; b++)
      System.out.print("   "); 
    int i = paramCMNode.type();
    switch (i) {
      case 101:
      case 102:
        if (i == 101) {
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
        dumpTree(((XSCMBinOp)paramCMNode).getLeft(), paramInt + 1);
        dumpTree(((XSCMBinOp)paramCMNode).getRight(), paramInt + 1);
        return;
      case 4:
      case 5:
      case 6:
        System.out.print("Rep Node ");
        if (paramCMNode.isNullable())
          System.out.print("Nullable "); 
        System.out.print("firstPos=");
        System.out.print(paramCMNode.firstPos().toString());
        System.out.print(" lastPos=");
        System.out.println(paramCMNode.lastPos().toString());
        dumpTree(((XSCMUniOp)paramCMNode).getChild(), paramInt + 1);
        return;
      case 1:
        System.out.print("Leaf: (pos=" + ((XSCMLeaf)paramCMNode).getPosition() + "), " + "(elemIndex=" + ((XSCMLeaf)paramCMNode).getLeaf() + ") ");
        if (paramCMNode.isNullable())
          System.out.print(" Nullable "); 
        System.out.print("firstPos=");
        System.out.print(paramCMNode.firstPos().toString());
        System.out.print(" lastPos=");
        System.out.println(paramCMNode.lastPos().toString());
        return;
      case 2:
        System.out.print("Any Node: ");
        System.out.print("firstPos=");
        System.out.print(paramCMNode.firstPos().toString());
        System.out.print(" lastPos=");
        System.out.println(paramCMNode.lastPos().toString());
        return;
    } 
    throw new RuntimeException("ImplementationMessages.VAL_NIICM");
  }
  
  private int[] makeDefStateList() {
    int[] arrayOfInt = new int[this.fElemMapSize];
    for (byte b = 0; b < this.fElemMapSize; b++)
      arrayOfInt[b] = -1; 
    return arrayOfInt;
  }
  
  private void postTreeBuildInit(CMNode paramCMNode) throws RuntimeException {
    paramCMNode.setMaxStates(this.fLeafCount);
    XSCMLeaf xSCMLeaf = null;
    int i = 0;
    if (paramCMNode.type() == 2) {
      xSCMLeaf = (XSCMLeaf)paramCMNode;
      i = xSCMLeaf.getPosition();
      this.fLeafList[i] = xSCMLeaf;
      this.fLeafListType[i] = 2;
    } else if (paramCMNode.type() == 101 || paramCMNode.type() == 102) {
      postTreeBuildInit(((XSCMBinOp)paramCMNode).getLeft());
      postTreeBuildInit(((XSCMBinOp)paramCMNode).getRight());
    } else if (paramCMNode.type() == 4 || paramCMNode.type() == 6 || paramCMNode.type() == 5) {
      postTreeBuildInit(((XSCMUniOp)paramCMNode).getChild());
    } else if (paramCMNode.type() == 1) {
      xSCMLeaf = (XSCMLeaf)paramCMNode;
      i = xSCMLeaf.getPosition();
      this.fLeafList[i] = xSCMLeaf;
      this.fLeafListType[i] = 1;
    } else {
      throw new RuntimeException("ImplementationMessages.VAL_NIICM");
    } 
  }
  
  public boolean checkUniqueParticleAttribution(SubstitutionGroupHandler paramSubstitutionGroupHandler) throws XMLSchemaException {
    byte[][] arrayOfByte = new byte[this.fElemMapSize][this.fElemMapSize];
    for (byte b1 = 0; b1 < this.fTransTable.length && this.fTransTable[b1] != null; b1++) {
      for (byte b = 0; b < this.fElemMapSize; b++) {
        for (int i = b + 1; i < this.fElemMapSize; i++) {
          if (this.fTransTable[b1][b] != -1 && this.fTransTable[b1][i] != -1 && arrayOfByte[b][i] == 0) {
            if (XSConstraints.overlapUPA(this.fElemMap[b], this.fElemMap[i], paramSubstitutionGroupHandler)) {
              if (this.fCountingStates != null) {
                Occurence occurence = this.fCountingStates[b1];
                if (occurence != null)
                  if ((((this.fTransTable[b1][b] == b1) ? 1 : 0) ^ ((this.fTransTable[b1][i] == b1) ? 1 : 0)) != 0 && occurence.minOccurs == occurence.maxOccurs) {
                    arrayOfByte[b][i] = -1;
                    continue;
                  }  
              } 
              arrayOfByte[b][i] = 1;
              continue;
            } 
            arrayOfByte[b][i] = -1;
          } 
          continue;
        } 
      } 
    } 
    for (byte b2 = 0; b2 < this.fElemMapSize; b2++) {
      for (byte b = 0; b < this.fElemMapSize; b++) {
        if (arrayOfByte[b2][b] == 1)
          throw new XMLSchemaException("cos-nonambig", new Object[] { this.fElemMap[b2].toString(), this.fElemMap[b].toString() }); 
      } 
    } 
    for (byte b3 = 0; b3 < this.fElemMapSize; b3++) {
      if (this.fElemMapType[b3] == 2) {
        XSWildcardDecl xSWildcardDecl = (XSWildcardDecl)this.fElemMap[b3];
        if (xSWildcardDecl.fType == 3 || xSWildcardDecl.fType == 2)
          return true; 
      } 
    } 
    return false;
  }
  
  public Vector whatCanGoHere(int[] paramArrayOfint) {
    int i = paramArrayOfint[0];
    if (i < 0)
      i = paramArrayOfint[1]; 
    Occurence occurence = (this.fCountingStates != null) ? this.fCountingStates[i] : null;
    int j = paramArrayOfint[2];
    Vector vector = new Vector();
    for (byte b = 0; b < this.fElemMapSize; b++) {
      int k = this.fTransTable[i][b];
      if (k != -1 && (occurence == null || ((i == k) ? (j >= occurence.maxOccurs && occurence.maxOccurs != -1) : (j < occurence.minOccurs))))
        vector.addElement(this.fElemMap[b]); 
    } 
    return vector;
  }
  
  public boolean isCompactedForUPA() {
    return this.fIsCompactedForUPA;
  }
  
  static final class Occurence {
    final int minOccurs;
    
    final int maxOccurs;
    
    final int elemIndex;
    
    public Occurence(XSCMRepeatingLeaf param1XSCMRepeatingLeaf, int param1Int) {
      this.minOccurs = param1XSCMRepeatingLeaf.getMinOccurs();
      this.maxOccurs = param1XSCMRepeatingLeaf.getMaxOccurs();
      this.elemIndex = param1Int;
    }
    
    public String toString() {
      return "minOccurs=" + this.minOccurs + ";maxOccurs=" + ((this.maxOccurs != -1) ? Integer.toString(this.maxOccurs) : "unbounded");
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\models\XSDFACM.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */