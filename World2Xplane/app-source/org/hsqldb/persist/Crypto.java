package org.hsqldb.persist;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;

public class Crypto {
  SecretKeySpec key;
  
  Cipher outCipher;
  
  Cipher inCipher;
  
  Cipher inStreamCipher;
  
  Cipher outStreamCipher;
  
  public Crypto(String paramString1, String paramString2, String paramString3) {
    try {
      byte[] arrayOfByte = StringConverter.hexStringToByteArray(paramString1);
      this.key = new SecretKeySpec(arrayOfByte, paramString2);
      this.outCipher = (paramString3 == null) ? Cipher.getInstance(paramString2) : Cipher.getInstance(paramString2, paramString3);
      this.outCipher.init(1, this.key);
      this.outStreamCipher = (paramString3 == null) ? Cipher.getInstance(paramString2) : Cipher.getInstance(paramString2, paramString3);
      this.outStreamCipher.init(1, this.key);
      this.inCipher = (paramString3 == null) ? Cipher.getInstance(paramString2) : Cipher.getInstance(paramString2, paramString3);
      this.inCipher.init(2, this.key);
      this.inStreamCipher = (paramString3 == null) ? Cipher.getInstance(paramString2) : Cipher.getInstance(paramString2, paramString3);
      this.inStreamCipher.init(2, this.key);
      return;
    } catch (NoSuchPaddingException noSuchPaddingException) {
      throw Error.error(331, noSuchPaddingException);
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      throw Error.error(331, noSuchAlgorithmException);
    } catch (InvalidKeyException invalidKeyException) {
      throw Error.error(331, invalidKeyException);
    } catch (NoSuchProviderException noSuchProviderException) {
      throw Error.error(331, noSuchProviderException);
    } catch (IOException iOException) {
      throw Error.error(331, iOException);
    } 
  }
  
  public synchronized InputStream getInputStream(InputStream paramInputStream) {
    if (this.inCipher == null)
      return paramInputStream; 
    try {
      this.inStreamCipher.init(2, this.key);
      return new CipherInputStream(paramInputStream, this.inStreamCipher);
    } catch (InvalidKeyException invalidKeyException) {
      throw Error.error(331, invalidKeyException);
    } 
  }
  
  public synchronized OutputStream getOutputStream(OutputStream paramOutputStream) {
    if (this.outCipher == null)
      return paramOutputStream; 
    try {
      this.outStreamCipher.init(1, this.key);
      return new CipherOutputStream(paramOutputStream, this.outStreamCipher);
    } catch (InvalidKeyException invalidKeyException) {
      throw Error.error(331, invalidKeyException);
    } 
  }
  
  public synchronized int decode(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3) {
    if (this.inCipher == null)
      return paramInt2; 
    try {
      this.inCipher.init(2, this.key);
      return this.inCipher.doFinal(paramArrayOfbyte1, paramInt1, paramInt2, paramArrayOfbyte2, paramInt3);
    } catch (InvalidKeyException invalidKeyException) {
      throw Error.error(331, invalidKeyException);
    } catch (BadPaddingException badPaddingException) {
      throw Error.error(331, badPaddingException);
    } catch (IllegalBlockSizeException illegalBlockSizeException) {
      throw Error.error(331, illegalBlockSizeException);
    } catch (ShortBufferException shortBufferException) {
      throw Error.error(331, shortBufferException);
    } 
  }
  
  public synchronized int encode(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3) {
    if (this.outCipher == null)
      return paramInt2; 
    try {
      this.outCipher.init(1, this.key);
      return this.outCipher.doFinal(paramArrayOfbyte1, paramInt1, paramInt2, paramArrayOfbyte2, paramInt3);
    } catch (InvalidKeyException invalidKeyException) {
      throw Error.error(331, invalidKeyException);
    } catch (BadPaddingException badPaddingException) {
      throw Error.error(331, badPaddingException);
    } catch (IllegalBlockSizeException illegalBlockSizeException) {
      throw Error.error(331, illegalBlockSizeException);
    } catch (ShortBufferException shortBufferException) {
      throw Error.error(331, shortBufferException);
    } 
  }
  
  public static byte[] getNewKey(String paramString1, String paramString2) {
    try {
      KeyGenerator keyGenerator = (paramString2 == null) ? KeyGenerator.getInstance(paramString1) : KeyGenerator.getInstance(paramString1, paramString2);
      SecretKey secretKey = keyGenerator.generateKey();
      return secretKey.getEncoded();
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      throw Error.error(331, noSuchAlgorithmException);
    } catch (NoSuchProviderException noSuchProviderException) {
      throw Error.error(331, noSuchProviderException);
    } 
  }
  
  public synchronized int getEncodedSize(int paramInt) {
    try {
      return this.outCipher.getOutputSize(paramInt);
    } catch (IllegalStateException illegalStateException) {
      try {
        this.outCipher.init(1, this.key);
        return this.outCipher.getOutputSize(paramInt);
      } catch (InvalidKeyException invalidKeyException) {
        throw Error.error(331, invalidKeyException);
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\Crypto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */