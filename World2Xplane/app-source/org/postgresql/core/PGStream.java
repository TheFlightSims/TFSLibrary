/*     */ package org.postgresql.core;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.net.Socket;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ public class PGStream {
/*     */   private final String host;
/*     */   
/*     */   private final int port;
/*     */   
/*     */   private final byte[] _int4buf;
/*     */   
/*     */   private final byte[] _int2buf;
/*     */   
/*     */   private Socket connection;
/*     */   
/*     */   private VisibleBufferedInputStream pg_input;
/*     */   
/*     */   private OutputStream pg_output;
/*     */   
/*     */   private byte[] streamBuffer;
/*     */   
/*     */   private Encoding encoding;
/*     */   
/*     */   private Writer encodingWriter;
/*     */   
/*     */   public PGStream(String host, int port) throws IOException {
/*  59 */     this.host = host;
/*  60 */     this.port = port;
/*  62 */     changeSocket(new Socket(host, port));
/*  63 */     setEncoding(Encoding.getJVMEncoding("US-ASCII"));
/*  65 */     this._int2buf = new byte[2];
/*  66 */     this._int4buf = new byte[4];
/*     */   }
/*     */   
/*     */   public String getHost() {
/*  70 */     return this.host;
/*     */   }
/*     */   
/*     */   public int getPort() {
/*  74 */     return this.port;
/*     */   }
/*     */   
/*     */   public Socket getSocket() {
/*  78 */     return this.connection;
/*     */   }
/*     */   
/*     */   public boolean hasMessagePending() throws IOException {
/*  91 */     return (this.pg_input.available() > 0 || this.connection.getInputStream().available() > 0);
/*     */   }
/*     */   
/*     */   public void changeSocket(Socket socket) throws IOException {
/* 103 */     this.connection = socket;
/* 108 */     this.connection.setTcpNoDelay(true);
/* 111 */     this.pg_input = new VisibleBufferedInputStream(this.connection.getInputStream(), 8192);
/* 112 */     this.pg_output = new BufferedOutputStream(this.connection.getOutputStream(), 8192);
/* 114 */     if (this.encoding != null)
/* 115 */       setEncoding(this.encoding); 
/*     */   }
/*     */   
/*     */   public Encoding getEncoding() {
/* 119 */     return this.encoding;
/*     */   }
/*     */   
/*     */   public void setEncoding(Encoding encoding) throws IOException {
/* 130 */     if (this.encodingWriter != null)
/* 131 */       this.encodingWriter.close(); 
/* 133 */     this.encoding = encoding;
/* 137 */     OutputStream interceptor = new FilterOutputStream(this.pg_output) {
/*     */         public void flush() throws IOException {}
/*     */         
/*     */         public void close() throws IOException {
/* 141 */           super.flush();
/*     */         }
/*     */       };
/* 145 */     this.encodingWriter = encoding.getEncodingWriter(interceptor);
/*     */   }
/*     */   
/*     */   public Writer getEncodingWriter() throws IOException {
/* 161 */     if (this.encodingWriter == null)
/* 162 */       throw new IOException("No encoding has been set on this connection"); 
/* 163 */     return this.encodingWriter;
/*     */   }
/*     */   
/*     */   public void SendChar(int val) throws IOException {
/* 174 */     this.pg_output.write(val);
/*     */   }
/*     */   
/*     */   public void SendInteger4(int val) throws IOException {
/* 185 */     this._int4buf[0] = (byte)(val >>> 24);
/* 186 */     this._int4buf[1] = (byte)(val >>> 16);
/* 187 */     this._int4buf[2] = (byte)(val >>> 8);
/* 188 */     this._int4buf[3] = (byte)val;
/* 189 */     this.pg_output.write(this._int4buf);
/*     */   }
/*     */   
/*     */   public void SendInteger2(int val) throws IOException {
/* 200 */     if (val < -32768 || val > 32767)
/* 201 */       throw new IOException("Tried to send an out-of-range integer as a 2-byte value: " + val); 
/* 203 */     this._int2buf[0] = (byte)(val >>> 8);
/* 204 */     this._int2buf[1] = (byte)val;
/* 205 */     this.pg_output.write(this._int2buf);
/*     */   }
/*     */   
/*     */   public void Send(byte[] buf) throws IOException {
/* 216 */     this.pg_output.write(buf);
/*     */   }
/*     */   
/*     */   public void Send(byte[] buf, int siz) throws IOException {
/* 229 */     Send(buf, 0, siz);
/*     */   }
/*     */   
/*     */   public void Send(byte[] buf, int off, int siz) throws IOException {
/* 243 */     int bufamt = buf.length - off;
/* 244 */     this.pg_output.write(buf, off, (bufamt < siz) ? bufamt : siz);
/* 245 */     for (int i = bufamt; i < siz; i++)
/* 247 */       this.pg_output.write(0); 
/*     */   }
/*     */   
/*     */   public int PeekChar() throws IOException {
/* 260 */     int c = this.pg_input.peek();
/* 261 */     if (c < 0)
/* 262 */       throw new EOFException(); 
/* 263 */     return c;
/*     */   }
/*     */   
/*     */   public int ReceiveChar() throws IOException {
/* 274 */     int c = this.pg_input.read();
/* 275 */     if (c < 0)
/* 276 */       throw new EOFException(); 
/* 277 */     return c;
/*     */   }
/*     */   
/*     */   public int ReceiveInteger4() throws IOException {
/* 288 */     if (this.pg_input.read(this._int4buf) != 4)
/* 289 */       throw new EOFException(); 
/* 291 */     return (this._int4buf[0] & 0xFF) << 24 | (this._int4buf[1] & 0xFF) << 16 | (this._int4buf[2] & 0xFF) << 8 | this._int4buf[3] & 0xFF;
/*     */   }
/*     */   
/*     */   public int ReceiveInteger2() throws IOException {
/* 302 */     if (this.pg_input.read(this._int2buf) != 2)
/* 303 */       throw new EOFException(); 
/* 305 */     return (this._int2buf[0] & 0xFF) << 8 | this._int2buf[1] & 0xFF;
/*     */   }
/*     */   
/*     */   public String ReceiveString(int len) throws IOException {
/* 315 */     if (!this.pg_input.ensureBytes(len))
/* 316 */       throw new EOFException(); 
/* 319 */     String res = this.encoding.decode(this.pg_input.getBuffer(), this.pg_input.getIndex(), len);
/* 321 */     this.pg_input.skip(len);
/* 322 */     return res;
/*     */   }
/*     */   
/*     */   public String ReceiveString() throws IOException {
/* 334 */     int len = this.pg_input.scanCStringLength();
/* 335 */     String res = this.encoding.decode(this.pg_input.getBuffer(), this.pg_input.getIndex(), len - 1);
/* 337 */     this.pg_input.skip(len);
/* 338 */     return res;
/*     */   }
/*     */   
/*     */   public byte[][] ReceiveTupleV3() throws IOException, OutOfMemoryError {
/* 353 */     int l_msgSize = ReceiveInteger4();
/* 355 */     int l_nf = ReceiveInteger2();
/* 356 */     byte[][] answer = new byte[l_nf][];
/* 358 */     OutOfMemoryError oom = null;
/* 359 */     for (int i = 0; i < l_nf; i++) {
/* 361 */       int l_size = ReceiveInteger4();
/* 362 */       if (l_size != -1)
/*     */         try {
/* 364 */           answer[i] = new byte[l_size];
/* 365 */           Receive(answer[i], 0, l_size);
/* 366 */         } catch (OutOfMemoryError oome) {
/* 367 */           oom = oome;
/* 368 */           Skip(l_size);
/*     */         }  
/*     */     } 
/* 373 */     if (oom != null)
/* 374 */       throw oom; 
/* 376 */     return answer;
/*     */   }
/*     */   
/*     */   public byte[][] ReceiveTupleV2(int nf, boolean bin) throws IOException, OutOfMemoryError {
/* 392 */     int bim = (nf + 7) / 8;
/* 393 */     byte[] bitmask = Receive(bim);
/* 394 */     byte[][] answer = new byte[nf][];
/* 396 */     int whichbit = 128;
/* 397 */     int whichbyte = 0;
/* 399 */     OutOfMemoryError oom = null;
/* 400 */     for (int i = 0; i < nf; i++) {
/* 402 */       boolean isNull = ((bitmask[whichbyte] & whichbit) == 0);
/* 403 */       whichbit >>= 1;
/* 404 */       if (whichbit == 0) {
/* 406 */         whichbyte++;
/* 407 */         whichbit = 128;
/*     */       } 
/* 409 */       if (!isNull) {
/* 411 */         int len = ReceiveInteger4();
/* 412 */         if (!bin)
/* 413 */           len -= 4; 
/* 414 */         if (len < 0)
/* 415 */           len = 0; 
/*     */         try {
/* 417 */           answer[i] = new byte[len];
/* 418 */           Receive(answer[i], 0, len);
/* 419 */         } catch (OutOfMemoryError oome) {
/* 420 */           oom = oome;
/* 421 */           Skip(len);
/*     */         } 
/*     */       } 
/*     */     } 
/* 426 */     if (oom != null)
/* 427 */       throw oom; 
/* 429 */     return answer;
/*     */   }
/*     */   
/*     */   public byte[] Receive(int siz) throws IOException {
/* 441 */     byte[] answer = new byte[siz];
/* 442 */     Receive(answer, 0, siz);
/* 443 */     return answer;
/*     */   }
/*     */   
/*     */   public void Receive(byte[] buf, int off, int siz) throws IOException {
/* 456 */     int s = 0;
/* 458 */     while (s < siz) {
/* 460 */       int w = this.pg_input.read(buf, off + s, siz - s);
/* 461 */       if (w < 0)
/* 462 */         throw new EOFException(); 
/* 463 */       s += w;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void Skip(int size) throws IOException {
/* 468 */     long s = 0L;
/* 469 */     while (s < size)
/* 470 */       s += this.pg_input.skip(size - s); 
/*     */   }
/*     */   
/*     */   public void SendStream(InputStream inStream, int remaining) throws IOException {
/* 482 */     int expectedLength = remaining;
/* 483 */     if (this.streamBuffer == null)
/* 484 */       this.streamBuffer = new byte[8192]; 
/* 486 */     while (remaining > 0) {
/* 488 */       int readCount, count = (remaining > this.streamBuffer.length) ? this.streamBuffer.length : remaining;
/*     */       try {
/* 493 */         readCount = inStream.read(this.streamBuffer, 0, count);
/* 494 */         if (readCount < 0)
/* 495 */           throw new EOFException(GT.tr("Premature end of input stream, expected {0} bytes, but only read {1}.", new Object[] { new Integer(expectedLength), new Integer(expectedLength - remaining) })); 
/* 497 */       } catch (IOException ioe) {
/* 499 */         while (remaining > 0) {
/* 501 */           Send(this.streamBuffer, count);
/* 502 */           remaining -= count;
/* 503 */           count = (remaining > this.streamBuffer.length) ? this.streamBuffer.length : remaining;
/*     */         } 
/* 505 */         throw new PGBindException(ioe);
/*     */       } 
/* 508 */       Send(this.streamBuffer, readCount);
/* 509 */       remaining -= readCount;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/* 521 */     if (this.encodingWriter != null)
/* 522 */       this.encodingWriter.flush(); 
/* 523 */     this.pg_output.flush();
/*     */   }
/*     */   
/*     */   public void ReceiveEOF() throws SQLException, IOException {
/* 532 */     int c = this.pg_input.read();
/* 533 */     if (c < 0)
/*     */       return; 
/* 535 */     throw new PSQLException(GT.tr("Expected an EOF from server, got: {0}", new Integer(c)), PSQLState.COMMUNICATION_ERROR);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 545 */     if (this.encodingWriter != null)
/* 546 */       this.encodingWriter.close(); 
/* 548 */     this.pg_output.close();
/* 549 */     this.pg_input.close();
/* 550 */     this.connection.close();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\PGStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */