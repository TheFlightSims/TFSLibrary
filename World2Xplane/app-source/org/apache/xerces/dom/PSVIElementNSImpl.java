package org.apache.xerces.dom;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.xerces.xs.ElementPSVI;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.XSNotationDeclaration;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;

public class PSVIElementNSImpl extends ElementNSImpl implements ElementPSVI {
  static final long serialVersionUID = 6815489624636016068L;
  
  protected XSElementDeclaration fDeclaration = null;
  
  protected XSTypeDefinition fTypeDecl = null;
  
  protected boolean fNil = false;
  
  protected boolean fSpecified = true;
  
  protected String fNormalizedValue = null;
  
  protected Object fActualValue = null;
  
  protected short fActualValueType = 45;
  
  protected ShortList fItemValueTypes = null;
  
  protected XSNotationDeclaration fNotation = null;
  
  protected XSSimpleTypeDefinition fMemberType = null;
  
  protected short fValidationAttempted = 0;
  
  protected short fValidity = 0;
  
  protected StringList fErrorCodes = null;
  
  protected String fValidationContext = null;
  
  protected XSModel fSchemaInformation = null;
  
  public PSVIElementNSImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString1, String paramString2, String paramString3) {
    super(paramCoreDocumentImpl, paramString1, paramString2, paramString3);
  }
  
  public PSVIElementNSImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString1, String paramString2) {
    super(paramCoreDocumentImpl, paramString1, paramString2);
  }
  
  public String getSchemaDefault() {
    return (this.fDeclaration == null) ? null : this.fDeclaration.getConstraintValue();
  }
  
  public String getSchemaNormalizedValue() {
    return this.fNormalizedValue;
  }
  
  public boolean getIsSchemaSpecified() {
    return this.fSpecified;
  }
  
  public short getValidationAttempted() {
    return this.fValidationAttempted;
  }
  
  public short getValidity() {
    return this.fValidity;
  }
  
  public StringList getErrorCodes() {
    return this.fErrorCodes;
  }
  
  public String getValidationContext() {
    return this.fValidationContext;
  }
  
  public boolean getNil() {
    return this.fNil;
  }
  
  public XSNotationDeclaration getNotation() {
    return this.fNotation;
  }
  
  public XSTypeDefinition getTypeDefinition() {
    return this.fTypeDecl;
  }
  
  public XSSimpleTypeDefinition getMemberTypeDefinition() {
    return this.fMemberType;
  }
  
  public XSElementDeclaration getElementDeclaration() {
    return this.fDeclaration;
  }
  
  public XSModel getSchemaInformation() {
    return this.fSchemaInformation;
  }
  
  public void setPSVI(ElementPSVI paramElementPSVI) {
    this.fDeclaration = paramElementPSVI.getElementDeclaration();
    this.fNotation = paramElementPSVI.getNotation();
    this.fValidationContext = paramElementPSVI.getValidationContext();
    this.fTypeDecl = paramElementPSVI.getTypeDefinition();
    this.fSchemaInformation = paramElementPSVI.getSchemaInformation();
    this.fValidity = paramElementPSVI.getValidity();
    this.fValidationAttempted = paramElementPSVI.getValidationAttempted();
    this.fErrorCodes = paramElementPSVI.getErrorCodes();
    this.fNormalizedValue = paramElementPSVI.getSchemaNormalizedValue();
    this.fActualValue = paramElementPSVI.getActualNormalizedValue();
    this.fActualValueType = paramElementPSVI.getActualNormalizedValueType();
    this.fItemValueTypes = paramElementPSVI.getItemValueTypes();
    this.fMemberType = paramElementPSVI.getMemberTypeDefinition();
    this.fSpecified = paramElementPSVI.getIsSchemaSpecified();
    this.fNil = paramElementPSVI.getNil();
  }
  
  public Object getActualNormalizedValue() {
    return this.fActualValue;
  }
  
  public short getActualNormalizedValueType() {
    return this.fActualValueType;
  }
  
  public ShortList getItemValueTypes() {
    return this.fItemValueTypes;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream) throws IOException {
    throw new NotSerializableException(getClass().getName());
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream) throws IOException, ClassNotFoundException {
    throw new NotSerializableException(getClass().getName());
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\PSVIElementNSImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */