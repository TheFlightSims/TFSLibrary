/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public abstract class AbstractMessageLite implements MessageLite {
/*     */   public ByteString toByteString() {
/*     */     try {
/*  49 */       ByteString.CodedBuilder out = ByteString.newCodedBuilder(getSerializedSize());
/*  51 */       writeTo(out.getCodedOutput());
/*  52 */       return out.build();
/*  53 */     } catch (IOException e) {
/*  54 */       throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] toByteArray() {
/*     */     try {
/*  62 */       byte[] result = new byte[getSerializedSize()];
/*  63 */       CodedOutputStream output = CodedOutputStream.newInstance(result);
/*  64 */       writeTo(output);
/*  65 */       output.checkNoSpaceLeft();
/*  66 */       return result;
/*  67 */     } catch (IOException e) {
/*  68 */       throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeTo(OutputStream output) throws IOException {
/*  75 */     int bufferSize = CodedOutputStream.computePreferredBufferSize(getSerializedSize());
/*  77 */     CodedOutputStream codedOutput = CodedOutputStream.newInstance(output, bufferSize);
/*  79 */     writeTo(codedOutput);
/*  80 */     codedOutput.flush();
/*     */   }
/*     */   
/*     */   public void writeDelimitedTo(OutputStream output) throws IOException {
/*  84 */     int serialized = getSerializedSize();
/*  85 */     int bufferSize = CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeRawVarint32Size(serialized) + serialized);
/*  87 */     CodedOutputStream codedOutput = CodedOutputStream.newInstance(output, bufferSize);
/*  89 */     codedOutput.writeRawVarint32(serialized);
/*  90 */     writeTo(codedOutput);
/*  91 */     codedOutput.flush();
/*     */   }
/*     */   
/*     */   public static abstract class Builder<BuilderType extends Builder> implements MessageLite.Builder {
/*     */     public BuilderType mergeFrom(CodedInputStream input) throws IOException {
/* 108 */       return mergeFrom(input, ExtensionRegistryLite.getEmptyRegistry());
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(ByteString data) throws InvalidProtocolBufferException {
/*     */       try {
/* 120 */         CodedInputStream input = data.newCodedInput();
/* 121 */         mergeFrom(input);
/* 122 */         input.checkLastTagWas(0);
/* 123 */         return (BuilderType)this;
/* 124 */       } catch (InvalidProtocolBufferException e) {
/* 125 */         throw e;
/* 126 */       } catch (IOException e) {
/* 127 */         throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*     */       try {
/* 138 */         CodedInputStream input = data.newCodedInput();
/* 139 */         mergeFrom(input, extensionRegistry);
/* 140 */         input.checkLastTagWas(0);
/* 141 */         return (BuilderType)this;
/* 142 */       } catch (InvalidProtocolBufferException e) {
/* 143 */         throw e;
/* 144 */       } catch (IOException e) {
/* 145 */         throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(byte[] data) throws InvalidProtocolBufferException {
/* 153 */       return mergeFrom(data, 0, data.length);
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
/*     */       try {
/* 160 */         CodedInputStream input = CodedInputStream.newInstance(data, off, len);
/* 162 */         mergeFrom(input);
/* 163 */         input.checkLastTagWas(0);
/* 164 */         return (BuilderType)this;
/* 165 */       } catch (InvalidProtocolBufferException e) {
/* 166 */         throw e;
/* 167 */       } catch (IOException e) {
/* 168 */         throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 178 */       return mergeFrom(data, 0, data.length, extensionRegistry);
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*     */       try {
/* 186 */         CodedInputStream input = CodedInputStream.newInstance(data, off, len);
/* 188 */         mergeFrom(input, extensionRegistry);
/* 189 */         input.checkLastTagWas(0);
/* 190 */         return (BuilderType)this;
/* 191 */       } catch (InvalidProtocolBufferException e) {
/* 192 */         throw e;
/* 193 */       } catch (IOException e) {
/* 194 */         throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(InputStream input) throws IOException {
/* 201 */       CodedInputStream codedInput = CodedInputStream.newInstance(input);
/* 202 */       mergeFrom(codedInput);
/* 203 */       codedInput.checkLastTagWas(0);
/* 204 */       return (BuilderType)this;
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 211 */       CodedInputStream codedInput = CodedInputStream.newInstance(input);
/* 212 */       mergeFrom(codedInput, extensionRegistry);
/* 213 */       codedInput.checkLastTagWas(0);
/* 214 */       return (BuilderType)this;
/*     */     }
/*     */     
/*     */     static final class LimitedInputStream extends FilterInputStream {
/*     */       private int limit;
/*     */       
/*     */       LimitedInputStream(InputStream in, int limit) {
/* 227 */         super(in);
/* 228 */         this.limit = limit;
/*     */       }
/*     */       
/*     */       public int available() throws IOException {
/* 233 */         return Math.min(super.available(), this.limit);
/*     */       }
/*     */       
/*     */       public int read() throws IOException {
/* 238 */         if (this.limit <= 0)
/* 239 */           return -1; 
/* 241 */         int result = super.read();
/* 242 */         if (result >= 0)
/* 243 */           this.limit--; 
/* 245 */         return result;
/*     */       }
/*     */       
/*     */       public int read(byte[] b, int off, int len) throws IOException {
/* 251 */         if (this.limit <= 0)
/* 252 */           return -1; 
/* 254 */         len = Math.min(len, this.limit);
/* 255 */         int result = super.read(b, off, len);
/* 256 */         if (result >= 0)
/* 257 */           this.limit -= result; 
/* 259 */         return result;
/*     */       }
/*     */       
/*     */       public long skip(long n) throws IOException {
/* 264 */         long result = super.skip(Math.min(n, this.limit));
/* 265 */         if (result >= 0L)
/* 266 */           this.limit = (int)(this.limit - result); 
/* 268 */         return result;
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean mergeDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 276 */       int firstByte = input.read();
/* 277 */       if (firstByte == -1)
/* 278 */         return false; 
/* 280 */       int size = CodedInputStream.readRawVarint32(firstByte, input);
/* 281 */       InputStream limitedInput = new LimitedInputStream(input, size);
/* 282 */       mergeFrom(limitedInput, extensionRegistry);
/* 283 */       return true;
/*     */     }
/*     */     
/*     */     public boolean mergeDelimitedFrom(InputStream input) throws IOException {
/* 288 */       return mergeDelimitedFrom(input, ExtensionRegistryLite.getEmptyRegistry());
/*     */     }
/*     */     
/*     */     protected static UninitializedMessageException newUninitializedMessageException(MessageLite message) {
/* 298 */       return new UninitializedMessageException(message);
/*     */     }
/*     */     
/*     */     protected static <T> void addAll(Iterable<T> values, Collection<? super T> list) {
/* 310 */       for (T value : values) {
/* 311 */         if (value == null)
/* 312 */           throw new NullPointerException(); 
/*     */       } 
/* 315 */       if (values instanceof Collection) {
/* 316 */         Collection<T> collection = (Collection<T>)values;
/* 317 */         list.addAll(collection);
/*     */       } else {
/* 319 */         for (T value : values)
/* 320 */           list.add(value); 
/*     */       } 
/*     */     }
/*     */     
/*     */     public abstract BuilderType clone();
/*     */     
/*     */     public abstract BuilderType mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\AbstractMessageLite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */