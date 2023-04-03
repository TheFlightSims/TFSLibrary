package org.codehaus.stax2.typed;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public interface TypedXMLStreamReader extends XMLStreamReader {
  boolean getElementAsBoolean() throws XMLStreamException;
  
  int getElementAsInt() throws XMLStreamException;
  
  long getElementAsLong() throws XMLStreamException;
  
  float getElementAsFloat() throws XMLStreamException;
  
  double getElementAsDouble() throws XMLStreamException;
  
  BigInteger getElementAsInteger() throws XMLStreamException;
  
  BigDecimal getElementAsDecimal() throws XMLStreamException;
  
  QName getElementAsQName() throws XMLStreamException;
  
  byte[] getElementAsBinary() throws XMLStreamException;
  
  byte[] getElementAsBinary(Base64Variant paramBase64Variant) throws XMLStreamException;
  
  void getElementAs(TypedValueDecoder paramTypedValueDecoder) throws XMLStreamException;
  
  int readElementAsBinary(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, Base64Variant paramBase64Variant) throws XMLStreamException;
  
  int readElementAsBinary(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws XMLStreamException;
  
  int readElementAsIntArray(int[] paramArrayOfint, int paramInt1, int paramInt2) throws XMLStreamException;
  
  int readElementAsLongArray(long[] paramArrayOflong, int paramInt1, int paramInt2) throws XMLStreamException;
  
  int readElementAsFloatArray(float[] paramArrayOffloat, int paramInt1, int paramInt2) throws XMLStreamException;
  
  int readElementAsDoubleArray(double[] paramArrayOfdouble, int paramInt1, int paramInt2) throws XMLStreamException;
  
  int readElementAsArray(TypedArrayDecoder paramTypedArrayDecoder) throws XMLStreamException;
  
  int getAttributeIndex(String paramString1, String paramString2);
  
  boolean getAttributeAsBoolean(int paramInt) throws XMLStreamException;
  
  int getAttributeAsInt(int paramInt) throws XMLStreamException;
  
  long getAttributeAsLong(int paramInt) throws XMLStreamException;
  
  float getAttributeAsFloat(int paramInt) throws XMLStreamException;
  
  double getAttributeAsDouble(int paramInt) throws XMLStreamException;
  
  BigInteger getAttributeAsInteger(int paramInt) throws XMLStreamException;
  
  BigDecimal getAttributeAsDecimal(int paramInt) throws XMLStreamException;
  
  QName getAttributeAsQName(int paramInt) throws XMLStreamException;
  
  void getAttributeAs(int paramInt, TypedValueDecoder paramTypedValueDecoder) throws XMLStreamException;
  
  byte[] getAttributeAsBinary(int paramInt) throws XMLStreamException;
  
  byte[] getAttributeAsBinary(int paramInt, Base64Variant paramBase64Variant) throws XMLStreamException;
  
  int[] getAttributeAsIntArray(int paramInt) throws XMLStreamException;
  
  long[] getAttributeAsLongArray(int paramInt) throws XMLStreamException;
  
  float[] getAttributeAsFloatArray(int paramInt) throws XMLStreamException;
  
  double[] getAttributeAsDoubleArray(int paramInt) throws XMLStreamException;
  
  int getAttributeAsArray(int paramInt, TypedArrayDecoder paramTypedArrayDecoder) throws XMLStreamException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\typed\TypedXMLStreamReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */