package org.hsqldb.jdbc.pool;

import java.net.Inet4Address;
import java.util.Arrays;
import java.util.Random;
import javax.transaction.xa.Xid;

public class JDBCXID implements Xid {
  int formatID;
  
  byte[] txID;
  
  byte[] txBranch;
  
  int hash;
  
  boolean hashComputed;
  
  private static byte[] s_localIp = null;
  
  private static int s_txnSequenceNumber = 0;
  
  private static final int UXID_FORMAT_ID = 65261;
  
  public int getFormatId() {
    return this.formatID;
  }
  
  public byte[] getGlobalTransactionId() {
    return this.txID;
  }
  
  public byte[] getBranchQualifier() {
    return this.txBranch;
  }
  
  public JDBCXID(int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    this.formatID = paramInt;
    this.txID = paramArrayOfbyte1;
    this.txBranch = paramArrayOfbyte2;
  }
  
  public int hashCode() {
    if (!this.hashComputed) {
      this.hash = 7;
      this.hash = 83 * this.hash + this.formatID;
      this.hash = 83 * this.hash + Arrays.hashCode(this.txID);
      this.hash = 83 * this.hash + Arrays.hashCode(this.txBranch);
      this.hashComputed = true;
    } 
    return this.hash;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof Xid) {
      Xid xid = (Xid)paramObject;
      return (this.formatID == xid.getFormatId() && Arrays.equals(this.txID, xid.getGlobalTransactionId()) && Arrays.equals(this.txBranch, xid.getBranchQualifier()));
    } 
    return false;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(512);
    stringBuffer.append("formatId=").append(getFormatId());
    stringBuffer.append(" globalTransactionId(").append(this.txID.length).append(")={0x");
    byte b;
    for (b = 0; b < this.txID.length; b++) {
      int i = this.txID[b] & 0xFF;
      if (i < 16)
        stringBuffer.append("0").append(Integer.toHexString(this.txID[b] & 0xFF)); 
      stringBuffer.append(Integer.toHexString(this.txID[b] & 0xFF));
    } 
    stringBuffer.append("} branchQualifier(").append(this.txBranch.length).append("))={0x");
    for (b = 0; b < this.txBranch.length; b++) {
      int i = this.txBranch[b] & 0xFF;
      if (i < 16)
        stringBuffer.append("0"); 
      stringBuffer.append(Integer.toHexString(this.txBranch[b] & 0xFF));
    } 
    stringBuffer.append("}");
    return stringBuffer.toString();
  }
  
  private static int nextTxnSequenceNumber() {
    s_txnSequenceNumber++;
    return s_txnSequenceNumber;
  }
  
  private static byte[] getLocalIp() {
    if (null == s_localIp)
      try {
        s_localIp = Inet4Address.getLocalHost().getAddress();
      } catch (Exception exception) {
        s_localIp = new byte[] { Byte.MAX_VALUE, 0, 0, 1 };
      }  
    return s_localIp;
  }
  
  public static Xid getUniqueXid(int paramInt) {
    Random random = new Random(System.currentTimeMillis());
    int i = nextTxnSequenceNumber();
    int j = paramInt;
    int k = random.nextInt();
    byte[] arrayOfByte1 = new byte[64];
    byte[] arrayOfByte2 = new byte[64];
    byte[] arrayOfByte3 = getLocalIp();
    System.arraycopy(arrayOfByte3, 0, arrayOfByte1, 0, 4);
    System.arraycopy(arrayOfByte3, 0, arrayOfByte2, 0, 4);
    for (byte b = 0; b <= 3; b++) {
      arrayOfByte1[b + 4] = (byte)(i % 256);
      arrayOfByte2[b + 4] = (byte)(i % 256);
      i >>= 8;
      arrayOfByte1[b + 8] = (byte)(j % 256);
      arrayOfByte2[b + 8] = (byte)(j % 256);
      j >>= 8;
      arrayOfByte1[b + 12] = (byte)(k % 256);
      arrayOfByte2[b + 12] = (byte)(k % 256);
      k >>= 8;
    } 
    return new JDBCXID(65261, arrayOfByte1, arrayOfByte2);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\pool\JDBCXID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */