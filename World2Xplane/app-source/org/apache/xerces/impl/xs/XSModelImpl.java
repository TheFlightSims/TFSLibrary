package org.apache.xerces.impl.xs;

import java.util.Vector;
import org.apache.xerces.impl.xs.util.NSItemListImpl;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.impl.xs.util.XSNamedMap4Types;
import org.apache.xerces.impl.xs.util.XSNamedMapImpl;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.xs.XSAttributeGroupDefinition;
import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.XSModelGroupDefinition;
import org.apache.xerces.xs.XSNamedMap;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSNamespaceItemList;
import org.apache.xerces.xs.XSNotationDeclaration;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSTypeDefinition;

public class XSModelImpl implements XSModel {
  private static final short MAX_COMP_IDX = 16;
  
  private static final boolean[] GLOBAL_COMP = new boolean[] { 
      false, true, true, true, false, true, true, false, false, false, 
      false, true, false, false, false, true, true };
  
  private int fGrammarCount;
  
  private String[] fNamespaces;
  
  private SchemaGrammar[] fGrammarList;
  
  private SymbolHash fGrammarMap;
  
  private SymbolHash fSubGroupMap;
  
  private XSNamedMap[] fGlobalComponents;
  
  private XSNamedMap[][] fNSComponents;
  
  private XSObjectListImpl fAnnotations = null;
  
  private boolean fHasIDC = false;
  
  public XSModelImpl(SchemaGrammar[] paramArrayOfSchemaGrammar) {
    int i = paramArrayOfSchemaGrammar.length;
    this.fNamespaces = new String[Math.max(i + 1, 5)];
    this.fGrammarList = new SchemaGrammar[Math.max(i + 1, 5)];
    boolean bool = false;
    for (byte b1 = 0; b1 < i; b1++) {
      this.fNamespaces[b1] = paramArrayOfSchemaGrammar[b1].getTargetNamespace();
      this.fGrammarList[b1] = paramArrayOfSchemaGrammar[b1];
      if (this.fNamespaces[b1] == SchemaSymbols.URI_SCHEMAFORSCHEMA)
        bool = true; 
    } 
    if (!bool) {
      this.fNamespaces[i] = SchemaSymbols.URI_SCHEMAFORSCHEMA;
      this.fGrammarList[i++] = SchemaGrammar.SG_SchemaNS;
    } 
    byte b2;
    for (b2 = 0; b2 < i; b2++) {
      SchemaGrammar schemaGrammar = this.fGrammarList[b2];
      Vector vector = schemaGrammar.getImportedGrammars();
      for (byte b = (vector == null) ? -1 : (vector.size() - 1); !b; b--) {
        SchemaGrammar schemaGrammar1 = vector.elementAt(b);
        byte b3;
        for (b3 = 0; b3 < i && schemaGrammar1 != this.fGrammarList[b3]; b3++);
        if (b3 == i) {
          if (i == this.fGrammarList.length) {
            String[] arrayOfString = new String[i * 2];
            System.arraycopy(this.fNamespaces, 0, arrayOfString, 0, i);
            this.fNamespaces = arrayOfString;
            SchemaGrammar[] arrayOfSchemaGrammar = new SchemaGrammar[i * 2];
            System.arraycopy(this.fGrammarList, 0, arrayOfSchemaGrammar, 0, i);
            this.fGrammarList = arrayOfSchemaGrammar;
          } 
          this.fNamespaces[i] = schemaGrammar1.getTargetNamespace();
          this.fGrammarList[i] = schemaGrammar1;
          i++;
        } 
      } 
    } 
    this.fGrammarMap = new SymbolHash(i * 2);
    for (b2 = 0; b2 < i; b2++) {
      this.fGrammarMap.put(null2EmptyString(this.fNamespaces[b2]), this.fGrammarList[b2]);
      if (this.fGrammarList[b2].hasIDConstraints())
        this.fHasIDC = true; 
    } 
    this.fGrammarCount = i;
    this.fGlobalComponents = new XSNamedMap[17];
    this.fNSComponents = new XSNamedMap[i][17];
    buildSubGroups();
  }
  
  private void buildSubGroups() {
    SubstitutionGroupHandler substitutionGroupHandler = new SubstitutionGroupHandler(null);
    for (byte b1 = 0; b1 < this.fGrammarCount; b1++)
      substitutionGroupHandler.addSubstitutionGroup(this.fGrammarList[b1].getSubstitutionGroups()); 
    XSNamedMap xSNamedMap = getComponents((short)2);
    int i = xSNamedMap.getLength();
    this.fSubGroupMap = new SymbolHash(i * 2);
    for (byte b2 = 0; b2 < i; b2++) {
      XSElementDecl xSElementDecl = (XSElementDecl)xSNamedMap.item(b2);
      XSElementDecl[] arrayOfXSElementDecl = substitutionGroupHandler.getSubstitutionGroup(xSElementDecl);
      this.fSubGroupMap.put(xSElementDecl, (arrayOfXSElementDecl.length > 0) ? new XSObjectListImpl((XSObject[])arrayOfXSElementDecl, arrayOfXSElementDecl.length) : XSObjectListImpl.EMPTY_LIST);
    } 
  }
  
  public StringList getNamespaces() {
    return (StringList)new StringListImpl(this.fNamespaces, this.fGrammarCount);
  }
  
  public XSNamespaceItemList getNamespaceItems() {
    return (XSNamespaceItemList)new NSItemListImpl((XSNamespaceItem[])this.fGrammarList, this.fGrammarCount);
  }
  
  public synchronized XSNamedMap getComponents(short paramShort) {
    if (paramShort <= 0 || paramShort > 16 || !GLOBAL_COMP[paramShort])
      return XSNamedMapImpl.EMPTY_MAP; 
    SymbolHash[] arrayOfSymbolHash = new SymbolHash[this.fGrammarCount];
    if (this.fGlobalComponents[paramShort] == null) {
      for (byte b = 0; b < this.fGrammarCount; b++) {
        switch (paramShort) {
          case 3:
          case 15:
          case 16:
            arrayOfSymbolHash[b] = (this.fGrammarList[b]).fGlobalTypeDecls;
            break;
          case 1:
            arrayOfSymbolHash[b] = (this.fGrammarList[b]).fGlobalAttrDecls;
            break;
          case 2:
            arrayOfSymbolHash[b] = (this.fGrammarList[b]).fGlobalElemDecls;
            break;
          case 5:
            arrayOfSymbolHash[b] = (this.fGrammarList[b]).fGlobalAttrGrpDecls;
            break;
          case 6:
            arrayOfSymbolHash[b] = (this.fGrammarList[b]).fGlobalGroupDecls;
            break;
          case 11:
            arrayOfSymbolHash[b] = (this.fGrammarList[b]).fGlobalNotationDecls;
            break;
        } 
      } 
      if (paramShort == 15 || paramShort == 16) {
        this.fGlobalComponents[paramShort] = (XSNamedMap)new XSNamedMap4Types(this.fNamespaces, arrayOfSymbolHash, this.fGrammarCount, paramShort);
      } else {
        this.fGlobalComponents[paramShort] = (XSNamedMap)new XSNamedMapImpl(this.fNamespaces, arrayOfSymbolHash, this.fGrammarCount);
      } 
    } 
    return this.fGlobalComponents[paramShort];
  }
  
  public synchronized XSNamedMap getComponentsByNamespace(short paramShort, String paramString) {
    if (paramShort <= 0 || paramShort > 16 || !GLOBAL_COMP[paramShort])
      return XSNamedMapImpl.EMPTY_MAP; 
    byte b = 0;
    if (paramString != null) {
      while (b < this.fGrammarCount && !paramString.equals(this.fNamespaces[b]))
        b++; 
    } else {
      while (b < this.fGrammarCount && this.fNamespaces[b] != null)
        b++; 
    } 
    if (b == this.fGrammarCount)
      return XSNamedMapImpl.EMPTY_MAP; 
    if (this.fNSComponents[b][paramShort] == null) {
      SymbolHash symbolHash = null;
      switch (paramShort) {
        case 3:
        case 15:
        case 16:
          symbolHash = (this.fGrammarList[b]).fGlobalTypeDecls;
          break;
        case 1:
          symbolHash = (this.fGrammarList[b]).fGlobalAttrDecls;
          break;
        case 2:
          symbolHash = (this.fGrammarList[b]).fGlobalElemDecls;
          break;
        case 5:
          symbolHash = (this.fGrammarList[b]).fGlobalAttrGrpDecls;
          break;
        case 6:
          symbolHash = (this.fGrammarList[b]).fGlobalGroupDecls;
          break;
        case 11:
          symbolHash = (this.fGrammarList[b]).fGlobalNotationDecls;
          break;
      } 
      if (paramShort == 15 || paramShort == 16) {
        this.fNSComponents[b][paramShort] = (XSNamedMap)new XSNamedMap4Types(paramString, symbolHash, paramShort);
      } else {
        this.fNSComponents[b][paramShort] = (XSNamedMap)new XSNamedMapImpl(paramString, symbolHash);
      } 
    } 
    return this.fNSComponents[b][paramShort];
  }
  
  public XSTypeDefinition getTypeDefinition(String paramString1, String paramString2) {
    SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(paramString2));
    return (schemaGrammar == null) ? null : (XSTypeDefinition)schemaGrammar.fGlobalTypeDecls.get(paramString1);
  }
  
  public XSAttributeDeclaration getAttributeDeclaration(String paramString1, String paramString2) {
    SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(paramString2));
    return (schemaGrammar == null) ? null : (XSAttributeDeclaration)schemaGrammar.fGlobalAttrDecls.get(paramString1);
  }
  
  public XSElementDeclaration getElementDeclaration(String paramString1, String paramString2) {
    SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(paramString2));
    return (schemaGrammar == null) ? null : (XSElementDeclaration)schemaGrammar.fGlobalElemDecls.get(paramString1);
  }
  
  public XSAttributeGroupDefinition getAttributeGroup(String paramString1, String paramString2) {
    SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(paramString2));
    return (schemaGrammar == null) ? null : (XSAttributeGroupDefinition)schemaGrammar.fGlobalAttrGrpDecls.get(paramString1);
  }
  
  public XSModelGroupDefinition getModelGroupDefinition(String paramString1, String paramString2) {
    SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(paramString2));
    return (schemaGrammar == null) ? null : (XSModelGroupDefinition)schemaGrammar.fGlobalGroupDecls.get(paramString1);
  }
  
  public XSNotationDeclaration getNotationDeclaration(String paramString1, String paramString2) {
    SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(paramString2));
    return (schemaGrammar == null) ? null : (XSNotationDeclaration)schemaGrammar.fGlobalNotationDecls.get(paramString1);
  }
  
  public synchronized XSObjectList getAnnotations() {
    if (this.fAnnotations != null)
      return (XSObjectList)this.fAnnotations; 
    int i = 0;
    for (byte b1 = 0; b1 < this.fGrammarCount; b1++)
      i += (this.fGrammarList[b1]).fNumAnnotations; 
    XSAnnotationImpl[] arrayOfXSAnnotationImpl = new XSAnnotationImpl[i];
    int j = 0;
    for (byte b2 = 0; b2 < this.fGrammarCount; b2++) {
      SchemaGrammar schemaGrammar = this.fGrammarList[b2];
      if (schemaGrammar.fNumAnnotations > 0) {
        System.arraycopy(schemaGrammar.fAnnotations, 0, arrayOfXSAnnotationImpl, j, schemaGrammar.fNumAnnotations);
        j += schemaGrammar.fNumAnnotations;
      } 
    } 
    this.fAnnotations = new XSObjectListImpl((XSObject[])arrayOfXSAnnotationImpl, arrayOfXSAnnotationImpl.length);
    return (XSObjectList)this.fAnnotations;
  }
  
  private static final String null2EmptyString(String paramString) {
    return (paramString == null) ? XMLSymbols.EMPTY_STRING : paramString;
  }
  
  public boolean hasIDConstraints() {
    return this.fHasIDC;
  }
  
  public XSObjectList getSubstitutionGroup(XSElementDeclaration paramXSElementDeclaration) {
    return (XSObjectList)this.fSubGroupMap.get(paramXSElementDeclaration);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\XSModelImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */