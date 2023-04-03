package org.apache.xerces.impl.xs;

import java.util.Hashtable;
import java.util.Vector;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;

public class SubstitutionGroupHandler {
  private static final XSElementDecl[] EMPTY_GROUP = new XSElementDecl[0];
  
  XSGrammarBucket fGrammarBucket;
  
  Hashtable fSubGroupsB = new Hashtable();
  
  private static final OneSubGroup[] EMPTY_VECTOR = new OneSubGroup[0];
  
  Hashtable fSubGroups = new Hashtable();
  
  public SubstitutionGroupHandler(XSGrammarBucket paramXSGrammarBucket) {
    this.fGrammarBucket = paramXSGrammarBucket;
  }
  
  public XSElementDecl getMatchingElemDecl(QName paramQName, XSElementDecl paramXSElementDecl) {
    if (paramQName.localpart == paramXSElementDecl.fName && paramQName.uri == paramXSElementDecl.fTargetNamespace)
      return paramXSElementDecl; 
    if (paramXSElementDecl.fScope != 1)
      return null; 
    if ((paramXSElementDecl.fBlock & 0x4) != 0)
      return null; 
    SchemaGrammar schemaGrammar = this.fGrammarBucket.getGrammar(paramQName.uri);
    if (schemaGrammar == null)
      return null; 
    XSElementDecl xSElementDecl = schemaGrammar.getGlobalElementDecl(paramQName.localpart);
    return (xSElementDecl == null) ? null : (substitutionGroupOK(xSElementDecl, paramXSElementDecl, paramXSElementDecl.fBlock) ? xSElementDecl : null);
  }
  
  protected boolean substitutionGroupOK(XSElementDecl paramXSElementDecl1, XSElementDecl paramXSElementDecl2, short paramShort) {
    if (paramXSElementDecl1 == paramXSElementDecl2)
      return true; 
    if ((paramShort & 0x4) != 0)
      return false; 
    XSElementDecl xSElementDecl;
    for (xSElementDecl = paramXSElementDecl1.fSubGroup; xSElementDecl != null && xSElementDecl != paramXSElementDecl2; xSElementDecl = xSElementDecl.fSubGroup);
    return (xSElementDecl == null) ? false : typeDerivationOK(paramXSElementDecl1.fType, paramXSElementDecl2.fType, paramShort);
  }
  
  private boolean typeDerivationOK(XSTypeDefinition paramXSTypeDefinition1, XSTypeDefinition paramXSTypeDefinition2, short paramShort) {
    XSComplexTypeDecl xSComplexTypeDecl;
    short s1 = 0;
    short s2 = paramShort;
    XSTypeDefinition xSTypeDefinition = paramXSTypeDefinition1;
    while (xSTypeDefinition != paramXSTypeDefinition2 && xSTypeDefinition != SchemaGrammar.fAnyType) {
      if (xSTypeDefinition.getTypeCategory() == 15) {
        s1 = (short)(s1 | ((XSComplexTypeDecl)xSTypeDefinition).fDerivedBy);
      } else {
        s1 = (short)(s1 | 0x2);
      } 
      xSTypeDefinition = xSTypeDefinition.getBaseType();
      if (xSTypeDefinition == null)
        xSComplexTypeDecl = SchemaGrammar.fAnyType; 
      if (xSComplexTypeDecl.getTypeCategory() == 15)
        s2 = (short)(s2 | xSComplexTypeDecl.fBlock); 
    } 
    if (xSComplexTypeDecl != paramXSTypeDefinition2) {
      if (paramXSTypeDefinition2.getTypeCategory() == 16) {
        XSSimpleTypeDefinition xSSimpleTypeDefinition = (XSSimpleTypeDefinition)paramXSTypeDefinition2;
        if (xSSimpleTypeDefinition.getVariety() == 3) {
          XSObjectList xSObjectList = xSSimpleTypeDefinition.getMemberTypes();
          int i = xSObjectList.getLength();
          for (byte b = 0; b < i; b++) {
            if (typeDerivationOK(paramXSTypeDefinition1, (XSTypeDefinition)xSObjectList.item(b), paramShort))
              return true; 
          } 
        } 
      } 
      return false;
    } 
    return !((s1 & s2) != 0);
  }
  
  public boolean inSubstitutionGroup(XSElementDecl paramXSElementDecl1, XSElementDecl paramXSElementDecl2) {
    return substitutionGroupOK(paramXSElementDecl1, paramXSElementDecl2, paramXSElementDecl2.fBlock);
  }
  
  public void reset() {
    this.fSubGroupsB.clear();
    this.fSubGroups.clear();
  }
  
  public void addSubstitutionGroup(XSElementDecl[] paramArrayOfXSElementDecl) {
    for (int i = paramArrayOfXSElementDecl.length - 1; i >= 0; i--) {
      XSElementDecl xSElementDecl2 = paramArrayOfXSElementDecl[i];
      XSElementDecl xSElementDecl1 = xSElementDecl2.fSubGroup;
      Vector vector = (Vector)this.fSubGroupsB.get(xSElementDecl1);
      if (vector == null) {
        vector = new Vector();
        this.fSubGroupsB.put(xSElementDecl1, vector);
      } 
      vector.addElement(xSElementDecl2);
    } 
  }
  
  public XSElementDecl[] getSubstitutionGroup(XSElementDecl paramXSElementDecl) {
    Object object = this.fSubGroups.get(paramXSElementDecl);
    if (object != null)
      return (XSElementDecl[])object; 
    if ((paramXSElementDecl.fBlock & 0x4) != 0) {
      this.fSubGroups.put(paramXSElementDecl, EMPTY_GROUP);
      return EMPTY_GROUP;
    } 
    OneSubGroup[] arrayOfOneSubGroup = getSubGroupB(paramXSElementDecl, new OneSubGroup());
    int i = arrayOfOneSubGroup.length;
    byte b1 = 0;
    XSElementDecl[] arrayOfXSElementDecl = new XSElementDecl[i];
    for (byte b2 = 0; b2 < i; b2++) {
      if ((paramXSElementDecl.fBlock & (arrayOfOneSubGroup[b2]).dMethod) == 0)
        arrayOfXSElementDecl[b1++] = (arrayOfOneSubGroup[b2]).sub; 
    } 
    if (b1 < i) {
      XSElementDecl[] arrayOfXSElementDecl1 = new XSElementDecl[b1];
      System.arraycopy(arrayOfXSElementDecl, 0, arrayOfXSElementDecl1, 0, b1);
      arrayOfXSElementDecl = arrayOfXSElementDecl1;
    } 
    this.fSubGroups.put(paramXSElementDecl, arrayOfXSElementDecl);
    return arrayOfXSElementDecl;
  }
  
  private OneSubGroup[] getSubGroupB(XSElementDecl paramXSElementDecl, OneSubGroup paramOneSubGroup) {
    Object object = this.fSubGroupsB.get(paramXSElementDecl);
    if (object == null) {
      this.fSubGroupsB.put(paramXSElementDecl, EMPTY_VECTOR);
      return EMPTY_VECTOR;
    } 
    if (object instanceof OneSubGroup[])
      return (OneSubGroup[])object; 
    Vector vector = (Vector)object;
    Vector vector1 = new Vector();
    for (int i = vector.size() - 1; i >= 0; i--) {
      XSElementDecl xSElementDecl = vector.elementAt(i);
      if (getDBMethods(xSElementDecl.fType, paramXSElementDecl.fType, paramOneSubGroup)) {
        short s1 = paramOneSubGroup.dMethod;
        short s2 = paramOneSubGroup.bMethod;
        vector1.addElement(new OneSubGroup(xSElementDecl, paramOneSubGroup.dMethod, paramOneSubGroup.bMethod));
        OneSubGroup[] arrayOfOneSubGroup1 = getSubGroupB(xSElementDecl, paramOneSubGroup);
        for (int k = arrayOfOneSubGroup1.length - 1; k >= 0; k--) {
          short s3 = (short)(s1 | (arrayOfOneSubGroup1[k]).dMethod);
          short s4 = (short)(s2 | (arrayOfOneSubGroup1[k]).bMethod);
          if ((s3 & s4) == 0)
            vector1.addElement(new OneSubGroup((arrayOfOneSubGroup1[k]).sub, s3, s4)); 
        } 
      } 
    } 
    OneSubGroup[] arrayOfOneSubGroup = new OneSubGroup[vector1.size()];
    for (int j = vector1.size() - 1; j >= 0; j--)
      arrayOfOneSubGroup[j] = vector1.elementAt(j); 
    this.fSubGroupsB.put(paramXSElementDecl, arrayOfOneSubGroup);
    return arrayOfOneSubGroup;
  }
  
  private boolean getDBMethods(XSTypeDefinition paramXSTypeDefinition1, XSTypeDefinition paramXSTypeDefinition2, OneSubGroup paramOneSubGroup) {
    XSComplexTypeDecl xSComplexTypeDecl;
    short s1 = 0;
    short s2 = 0;
    while (paramXSTypeDefinition1 != paramXSTypeDefinition2 && paramXSTypeDefinition1 != SchemaGrammar.fAnyType) {
      if (paramXSTypeDefinition1.getTypeCategory() == 15) {
        s1 = (short)(s1 | ((XSComplexTypeDecl)paramXSTypeDefinition1).fDerivedBy);
      } else {
        s1 = (short)(s1 | 0x2);
      } 
      paramXSTypeDefinition1 = paramXSTypeDefinition1.getBaseType();
      if (paramXSTypeDefinition1 == null)
        xSComplexTypeDecl = SchemaGrammar.fAnyType; 
      if (xSComplexTypeDecl.getTypeCategory() == 15)
        s2 = (short)(s2 | xSComplexTypeDecl.fBlock); 
    } 
    if (xSComplexTypeDecl != paramXSTypeDefinition2 || (s1 & s2) != 0)
      return false; 
    paramOneSubGroup.dMethod = s1;
    paramOneSubGroup.bMethod = s2;
    return true;
  }
  
  private static final class OneSubGroup {
    XSElementDecl sub;
    
    short dMethod;
    
    short bMethod;
    
    OneSubGroup() {}
    
    OneSubGroup(XSElementDecl param1XSElementDecl, short param1Short1, short param1Short2) {
      this.sub = param1XSElementDecl;
      this.dMethod = param1Short1;
      this.bMethod = param1Short2;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\SubstitutionGroupHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */