package org.apache.xerces.util;

import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;

public class XMLAttributesImpl implements XMLAttributes {
  protected static final int TABLE_SIZE = 101;
  
  protected static final int SIZE_LIMIT = 20;
  
  protected boolean fNamespaces = true;
  
  protected int fLargeCount = 1;
  
  protected int fLength;
  
  protected Attribute[] fAttributes = new Attribute[4];
  
  protected Attribute[] fAttributeTableView;
  
  protected int[] fAttributeTableViewChainState;
  
  protected int fTableViewBuckets;
  
  protected boolean fIsTableViewConsistent;
  
  public XMLAttributesImpl() {
    this(101);
  }
  
  public XMLAttributesImpl(int paramInt) {
    this.fTableViewBuckets = paramInt;
    for (byte b = 0; b < this.fAttributes.length; b++)
      this.fAttributes[b] = new Attribute(); 
  }
  
  public void setNamespaces(boolean paramBoolean) {
    this.fNamespaces = paramBoolean;
  }
  
  public int addAttribute(QName paramQName, String paramString1, String paramString2) {
    int i;
    if (this.fLength < 20) {
      i = (paramQName.uri != null && !paramQName.uri.equals("")) ? getIndexFast(paramQName.uri, paramQName.localpart) : getIndexFast(paramQName.rawname);
      i = this.fLength;
      if (i == -1 && this.fLength++ == this.fAttributes.length) {
        Attribute[] arrayOfAttribute = new Attribute[this.fAttributes.length + 4];
        System.arraycopy(this.fAttributes, 0, arrayOfAttribute, 0, this.fAttributes.length);
        for (int j = this.fAttributes.length; j < arrayOfAttribute.length; j++)
          arrayOfAttribute[j] = new Attribute(); 
        this.fAttributes = arrayOfAttribute;
      } 
    } else if (paramQName.uri == null || paramQName.uri.length() == 0 || (i = getIndexFast(paramQName.uri, paramQName.localpart)) == -1) {
      if (!this.fIsTableViewConsistent || this.fLength == 20) {
        prepareAndPopulateTableView();
        this.fIsTableViewConsistent = true;
      } 
      int j = getTableViewBucket(paramQName.rawname);
      if (this.fAttributeTableViewChainState[j] != this.fLargeCount) {
        i = this.fLength;
        if (this.fLength++ == this.fAttributes.length) {
          Attribute[] arrayOfAttribute = new Attribute[this.fAttributes.length << 1];
          System.arraycopy(this.fAttributes, 0, arrayOfAttribute, 0, this.fAttributes.length);
          for (int k = this.fAttributes.length; k < arrayOfAttribute.length; k++)
            arrayOfAttribute[k] = new Attribute(); 
          this.fAttributes = arrayOfAttribute;
        } 
        this.fAttributeTableViewChainState[j] = this.fLargeCount;
        (this.fAttributes[i]).next = null;
        this.fAttributeTableView[j] = this.fAttributes[i];
      } else {
        Attribute attribute1;
        for (attribute1 = this.fAttributeTableView[j]; attribute1 != null && attribute1.name.rawname != paramQName.rawname; attribute1 = attribute1.next);
        if (attribute1 == null) {
          i = this.fLength;
          if (this.fLength++ == this.fAttributes.length) {
            Attribute[] arrayOfAttribute = new Attribute[this.fAttributes.length << 1];
            System.arraycopy(this.fAttributes, 0, arrayOfAttribute, 0, this.fAttributes.length);
            for (int k = this.fAttributes.length; k < arrayOfAttribute.length; k++)
              arrayOfAttribute[k] = new Attribute(); 
            this.fAttributes = arrayOfAttribute;
          } 
          (this.fAttributes[i]).next = this.fAttributeTableView[j];
          this.fAttributeTableView[j] = this.fAttributes[i];
        } else {
          i = getIndexFast(paramQName.rawname);
        } 
      } 
    } 
    Attribute attribute = this.fAttributes[i];
    attribute.name.setValues(paramQName);
    attribute.type = paramString1;
    attribute.value = paramString2;
    attribute.nonNormalizedValue = paramString2;
    attribute.specified = false;
    attribute.augs.removeAllItems();
    return i;
  }
  
  public void removeAllAttributes() {
    this.fLength = 0;
  }
  
  public void removeAttributeAt(int paramInt) {
    this.fIsTableViewConsistent = false;
    if (paramInt < this.fLength - 1) {
      Attribute attribute = this.fAttributes[paramInt];
      System.arraycopy(this.fAttributes, paramInt + 1, this.fAttributes, paramInt, this.fLength - paramInt - 1);
      this.fAttributes[this.fLength - 1] = attribute;
    } 
    this.fLength--;
  }
  
  public void setName(int paramInt, QName paramQName) {
    (this.fAttributes[paramInt]).name.setValues(paramQName);
  }
  
  public void getName(int paramInt, QName paramQName) {
    paramQName.setValues((this.fAttributes[paramInt]).name);
  }
  
  public void setType(int paramInt, String paramString) {
    (this.fAttributes[paramInt]).type = paramString;
  }
  
  public void setValue(int paramInt, String paramString) {
    Attribute attribute = this.fAttributes[paramInt];
    attribute.value = paramString;
    attribute.nonNormalizedValue = paramString;
  }
  
  public void setNonNormalizedValue(int paramInt, String paramString) {
    if (paramString == null)
      paramString = (this.fAttributes[paramInt]).value; 
    (this.fAttributes[paramInt]).nonNormalizedValue = paramString;
  }
  
  public String getNonNormalizedValue(int paramInt) {
    return (this.fAttributes[paramInt]).nonNormalizedValue;
  }
  
  public void setSpecified(int paramInt, boolean paramBoolean) {
    (this.fAttributes[paramInt]).specified = paramBoolean;
  }
  
  public boolean isSpecified(int paramInt) {
    return (this.fAttributes[paramInt]).specified;
  }
  
  public int getLength() {
    return this.fLength;
  }
  
  public String getType(int paramInt) {
    return (paramInt < 0 || paramInt >= this.fLength) ? null : getReportableType((this.fAttributes[paramInt]).type);
  }
  
  public String getType(String paramString) {
    int i = getIndex(paramString);
    return (i != -1) ? getReportableType((this.fAttributes[i]).type) : null;
  }
  
  public String getValue(int paramInt) {
    return (paramInt < 0 || paramInt >= this.fLength) ? null : (this.fAttributes[paramInt]).value;
  }
  
  public String getValue(String paramString) {
    int i = getIndex(paramString);
    return (i != -1) ? (this.fAttributes[i]).value : null;
  }
  
  public String getName(int paramInt) {
    return (paramInt < 0 || paramInt >= this.fLength) ? null : (this.fAttributes[paramInt]).name.rawname;
  }
  
  public int getIndex(String paramString) {
    for (byte b = 0; b < this.fLength; b++) {
      Attribute attribute = this.fAttributes[b];
      if (attribute.name.rawname != null && attribute.name.rawname.equals(paramString))
        return b; 
    } 
    return -1;
  }
  
  public int getIndex(String paramString1, String paramString2) {
    for (byte b = 0; b < this.fLength; b++) {
      Attribute attribute = this.fAttributes[b];
      if (attribute.name.localpart != null && attribute.name.localpart.equals(paramString2) && (paramString1 == attribute.name.uri || (paramString1 != null && attribute.name.uri != null && attribute.name.uri.equals(paramString1))))
        return b; 
    } 
    return -1;
  }
  
  public String getLocalName(int paramInt) {
    return !this.fNamespaces ? "" : ((paramInt < 0 || paramInt >= this.fLength) ? null : (this.fAttributes[paramInt]).name.localpart);
  }
  
  public String getQName(int paramInt) {
    if (paramInt < 0 || paramInt >= this.fLength)
      return null; 
    String str = (this.fAttributes[paramInt]).name.rawname;
    return (str != null) ? str : "";
  }
  
  public String getType(String paramString1, String paramString2) {
    if (!this.fNamespaces)
      return null; 
    int i = getIndex(paramString1, paramString2);
    return (i != -1) ? getReportableType((this.fAttributes[i]).type) : null;
  }
  
  public String getPrefix(int paramInt) {
    if (paramInt < 0 || paramInt >= this.fLength)
      return null; 
    String str = (this.fAttributes[paramInt]).name.prefix;
    return (str != null) ? str : "";
  }
  
  public String getURI(int paramInt) {
    return (paramInt < 0 || paramInt >= this.fLength) ? null : (this.fAttributes[paramInt]).name.uri;
  }
  
  public String getValue(String paramString1, String paramString2) {
    int i = getIndex(paramString1, paramString2);
    return (i != -1) ? getValue(i) : null;
  }
  
  public Augmentations getAugmentations(String paramString1, String paramString2) {
    int i = getIndex(paramString1, paramString2);
    return (i != -1) ? (this.fAttributes[i]).augs : null;
  }
  
  public Augmentations getAugmentations(String paramString) {
    int i = getIndex(paramString);
    return (i != -1) ? (this.fAttributes[i]).augs : null;
  }
  
  public Augmentations getAugmentations(int paramInt) {
    return (paramInt < 0 || paramInt >= this.fLength) ? null : (this.fAttributes[paramInt]).augs;
  }
  
  public void setAugmentations(int paramInt, Augmentations paramAugmentations) {
    (this.fAttributes[paramInt]).augs = paramAugmentations;
  }
  
  public void setURI(int paramInt, String paramString) {
    (this.fAttributes[paramInt]).name.uri = paramString;
  }
  
  public void setSchemaId(int paramInt, boolean paramBoolean) {
    (this.fAttributes[paramInt]).schemaId = paramBoolean;
  }
  
  public boolean getSchemaId(int paramInt) {
    return (paramInt < 0 || paramInt >= this.fLength) ? false : (this.fAttributes[paramInt]).schemaId;
  }
  
  public boolean getSchemaId(String paramString) {
    int i = getIndex(paramString);
    return (i != -1) ? (this.fAttributes[i]).schemaId : false;
  }
  
  public boolean getSchemaId(String paramString1, String paramString2) {
    if (!this.fNamespaces)
      return false; 
    int i = getIndex(paramString1, paramString2);
    return (i != -1) ? (this.fAttributes[i]).schemaId : false;
  }
  
  public int getIndexFast(String paramString) {
    for (byte b = 0; b < this.fLength; b++) {
      Attribute attribute = this.fAttributes[b];
      if (attribute.name.rawname == paramString)
        return b; 
    } 
    return -1;
  }
  
  public void addAttributeNS(QName paramQName, String paramString1, String paramString2) {
    int i = this.fLength;
    if (this.fLength++ == this.fAttributes.length) {
      Attribute[] arrayOfAttribute;
      if (this.fLength < 20) {
        arrayOfAttribute = new Attribute[this.fAttributes.length + 4];
      } else {
        arrayOfAttribute = new Attribute[this.fAttributes.length << 1];
      } 
      System.arraycopy(this.fAttributes, 0, arrayOfAttribute, 0, this.fAttributes.length);
      for (int j = this.fAttributes.length; j < arrayOfAttribute.length; j++)
        arrayOfAttribute[j] = new Attribute(); 
      this.fAttributes = arrayOfAttribute;
    } 
    Attribute attribute = this.fAttributes[i];
    attribute.name.setValues(paramQName);
    attribute.type = paramString1;
    attribute.value = paramString2;
    attribute.nonNormalizedValue = paramString2;
    attribute.specified = false;
    attribute.augs.removeAllItems();
  }
  
  public QName checkDuplicatesNS() {
    if (this.fLength <= 20) {
      for (byte b = 0; b < this.fLength - 1; b++) {
        Attribute attribute = this.fAttributes[b];
        for (int i = b + 1; i < this.fLength; i++) {
          Attribute attribute1 = this.fAttributes[i];
          if (attribute.name.localpart == attribute1.name.localpart && attribute.name.uri == attribute1.name.uri)
            return attribute1.name; 
        } 
      } 
    } else {
      this.fIsTableViewConsistent = false;
      prepareTableView();
      for (int i = this.fLength - 1; i >= 0; i--) {
        Attribute attribute = this.fAttributes[i];
        int j = getTableViewBucket(attribute.name.localpart, attribute.name.uri);
        if (this.fAttributeTableViewChainState[j] != this.fLargeCount) {
          this.fAttributeTableViewChainState[j] = this.fLargeCount;
          attribute.next = null;
          this.fAttributeTableView[j] = attribute;
        } else {
          for (Attribute attribute1 = this.fAttributeTableView[j]; attribute1 != null; attribute1 = attribute1.next) {
            if (attribute1.name.localpart == attribute.name.localpart && attribute1.name.uri == attribute.name.uri)
              return attribute.name; 
          } 
          attribute.next = this.fAttributeTableView[j];
          this.fAttributeTableView[j] = attribute;
        } 
      } 
    } 
    return null;
  }
  
  public int getIndexFast(String paramString1, String paramString2) {
    for (byte b = 0; b < this.fLength; b++) {
      Attribute attribute = this.fAttributes[b];
      if (attribute.name.localpart == paramString2 && attribute.name.uri == paramString1)
        return b; 
    } 
    return -1;
  }
  
  private String getReportableType(String paramString) {
    return (paramString.charAt(0) == '(') ? "NMTOKEN" : paramString;
  }
  
  protected int getTableViewBucket(String paramString) {
    return (paramString.hashCode() & Integer.MAX_VALUE) % this.fTableViewBuckets;
  }
  
  protected int getTableViewBucket(String paramString1, String paramString2) {
    return (paramString2 == null) ? ((paramString1.hashCode() & Integer.MAX_VALUE) % this.fTableViewBuckets) : ((paramString1.hashCode() + paramString2.hashCode() & Integer.MAX_VALUE) % this.fTableViewBuckets);
  }
  
  protected void cleanTableView() {
    if (++this.fLargeCount < 0) {
      if (this.fAttributeTableViewChainState != null)
        for (int i = this.fTableViewBuckets - 1; i >= 0; i--)
          this.fAttributeTableViewChainState[i] = 0;  
      this.fLargeCount = 1;
    } 
  }
  
  protected void prepareTableView() {
    if (this.fAttributeTableView == null) {
      this.fAttributeTableView = new Attribute[this.fTableViewBuckets];
      this.fAttributeTableViewChainState = new int[this.fTableViewBuckets];
    } else {
      cleanTableView();
    } 
  }
  
  protected void prepareAndPopulateTableView() {
    prepareTableView();
    for (byte b = 0; b < this.fLength; b++) {
      Attribute attribute = this.fAttributes[b];
      int i = getTableViewBucket(attribute.name.rawname);
      if (this.fAttributeTableViewChainState[i] != this.fLargeCount) {
        this.fAttributeTableViewChainState[i] = this.fLargeCount;
        attribute.next = null;
        this.fAttributeTableView[i] = attribute;
      } else {
        attribute.next = this.fAttributeTableView[i];
        this.fAttributeTableView[i] = attribute;
      } 
    } 
  }
  
  static class Attribute {
    public QName name = new QName();
    
    public String type;
    
    public String value;
    
    public String nonNormalizedValue;
    
    public boolean specified;
    
    public boolean schemaId;
    
    public Augmentations augs = new AugmentationsImpl();
    
    public Attribute next;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\XMLAttributesImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */