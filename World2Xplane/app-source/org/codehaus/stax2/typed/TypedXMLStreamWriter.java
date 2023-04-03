package org.codehaus.stax2.typed;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public interface TypedXMLStreamWriter extends XMLStreamWriter {
  void writeBoolean(boolean paramBoolean) throws XMLStreamException;
  
  void writeInt(int paramInt) throws XMLStreamException;
  
  void writeLong(long paramLong) throws XMLStreamException;
  
  void writeFloat(float paramFloat) throws XMLStreamException;
  
  void writeDouble(double paramDouble) throws XMLStreamException;
  
  void writeInteger(BigInteger paramBigInteger) throws XMLStreamException;
  
  void writeDecimal(BigDecimal paramBigDecimal) throws XMLStreamException;
  
  void writeQName(QName paramQName) throws XMLStreamException;
  
  void writeBinary(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws XMLStreamException;
  
  void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws XMLStreamException;
  
  void writeIntArray(int[] paramArrayOfint, int paramInt1, int paramInt2) throws XMLStreamException;
  
  void writeLongArray(long[] paramArrayOflong, int paramInt1, int paramInt2) throws XMLStreamException;
  
  void writeFloatArray(float[] paramArrayOffloat, int paramInt1, int paramInt2) throws XMLStreamException;
  
  void writeDoubleArray(double[] paramArrayOfdouble, int paramInt1, int paramInt2) throws XMLStreamException;
  
  void writeBooleanAttribute(String paramString1, String paramString2, String paramString3, boolean paramBoolean) throws XMLStreamException;
  
  void writeIntAttribute(String paramString1, String paramString2, String paramString3, int paramInt) throws XMLStreamException;
  
  void writeLongAttribute(String paramString1, String paramString2, String paramString3, long paramLong) throws XMLStreamException;
  
  void writeFloatAttribute(String paramString1, String paramString2, String paramString3, float paramFloat) throws XMLStreamException;
  
  void writeDoubleAttribute(String paramString1, String paramString2, String paramString3, double paramDouble) throws XMLStreamException;
  
  void writeIntegerAttribute(String paramString1, String paramString2, String paramString3, BigInteger paramBigInteger) throws XMLStreamException;
  
  void writeDecimalAttribute(String paramString1, String paramString2, String paramString3, BigDecimal paramBigDecimal) throws XMLStreamException;
  
  void writeQNameAttribute(String paramString1, String paramString2, String paramString3, QName paramQName) throws XMLStreamException;
  
  void writeBinaryAttribute(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfbyte) throws XMLStreamException;
  
  void writeBinaryAttribute(Base64Variant paramBase64Variant, String paramString1, String paramString2, String paramString3, byte[] paramArrayOfbyte) throws XMLStreamException;
  
  void writeIntArrayAttribute(String paramString1, String paramString2, String paramString3, int[] paramArrayOfint) throws XMLStreamException;
  
  void writeLongArrayAttribute(String paramString1, String paramString2, String paramString3, long[] paramArrayOflong) throws XMLStreamException;
  
  void writeFloatArrayAttribute(String paramString1, String paramString2, String paramString3, float[] paramArrayOffloat) throws XMLStreamException;
  
  void writeDoubleArrayAttribute(String paramString1, String paramString2, String paramString3, double[] paramArrayOfdouble) throws XMLStreamException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\typed\TypedXMLStreamWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */