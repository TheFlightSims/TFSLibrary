/*     */ package javax.measure.unit.format;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ class SimpleCharStream {
/*     */   public static final boolean staticFlag = false;
/*     */   
/*     */   int bufsize;
/*     */   
/*     */   int available;
/*     */   
/*     */   int tokenBegin;
/*     */   
/*  15 */   public int bufpos = -1;
/*     */   
/*     */   protected int[] bufline;
/*     */   
/*     */   protected int[] bufcolumn;
/*     */   
/*  19 */   protected int column = 0;
/*     */   
/*  20 */   protected int line = 1;
/*     */   
/*     */   protected boolean prevCharIsCR = false;
/*     */   
/*     */   protected boolean prevCharIsLF = false;
/*     */   
/*     */   protected Reader inputStream;
/*     */   
/*     */   protected char[] buffer;
/*     */   
/*  28 */   protected int maxNextCharInd = 0;
/*     */   
/*  29 */   protected int inBuf = 0;
/*     */   
/*  30 */   protected int tabSize = 8;
/*     */   
/*     */   protected void setTabSize(int i) {
/*  32 */     this.tabSize = i;
/*     */   }
/*     */   
/*     */   protected int getTabSize(int i) {
/*  33 */     return this.tabSize;
/*     */   }
/*     */   
/*     */   protected void ExpandBuff(boolean wrapAround) {
/*  38 */     char[] newbuffer = new char[this.bufsize + 2048];
/*  39 */     int[] newbufline = new int[this.bufsize + 2048];
/*  40 */     int[] newbufcolumn = new int[this.bufsize + 2048];
/*     */     try {
/*  44 */       if (wrapAround) {
/*  46 */         System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
/*  47 */         System.arraycopy(this.buffer, 0, newbuffer, this.bufsize - this.tokenBegin, this.bufpos);
/*  49 */         this.buffer = newbuffer;
/*  51 */         System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
/*  52 */         System.arraycopy(this.bufline, 0, newbufline, this.bufsize - this.tokenBegin, this.bufpos);
/*  53 */         this.bufline = newbufline;
/*  55 */         System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
/*  56 */         System.arraycopy(this.bufcolumn, 0, newbufcolumn, this.bufsize - this.tokenBegin, this.bufpos);
/*  57 */         this.bufcolumn = newbufcolumn;
/*  59 */         this.maxNextCharInd = this.bufpos += this.bufsize - this.tokenBegin;
/*     */       } else {
/*  63 */         System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
/*  64 */         this.buffer = newbuffer;
/*  66 */         System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
/*  67 */         this.bufline = newbufline;
/*  69 */         System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
/*  70 */         this.bufcolumn = newbufcolumn;
/*  72 */         this.maxNextCharInd = this.bufpos -= this.tokenBegin;
/*     */       } 
/*  75 */     } catch (Throwable t) {
/*  77 */       throw new Error(t.getMessage());
/*     */     } 
/*  81 */     this.bufsize += 2048;
/*  82 */     this.available = this.bufsize;
/*  83 */     this.tokenBegin = 0;
/*     */   }
/*     */   
/*     */   protected void FillBuff() throws IOException {
/*  88 */     if (this.maxNextCharInd == this.available)
/*  90 */       if (this.available == this.bufsize) {
/*  92 */         if (this.tokenBegin > 2048) {
/*  94 */           this.bufpos = this.maxNextCharInd = 0;
/*  95 */           this.available = this.tokenBegin;
/*  97 */         } else if (this.tokenBegin < 0) {
/*  98 */           this.bufpos = this.maxNextCharInd = 0;
/*     */         } else {
/* 100 */           ExpandBuff(false);
/*     */         } 
/* 102 */       } else if (this.available > this.tokenBegin) {
/* 103 */         this.available = this.bufsize;
/* 104 */       } else if (this.tokenBegin - this.available < 2048) {
/* 105 */         ExpandBuff(true);
/*     */       } else {
/* 107 */         this.available = this.tokenBegin;
/*     */       }  
/*     */     try {
/*     */       int i;
/* 112 */       if ((i = this.inputStream.read(this.buffer, this.maxNextCharInd, this.available - this.maxNextCharInd)) == -1) {
/* 115 */         this.inputStream.close();
/* 116 */         throw new IOException();
/*     */       } 
/* 119 */       this.maxNextCharInd += i;
/*     */       return;
/* 122 */     } catch (IOException e) {
/* 123 */       this.bufpos--;
/* 124 */       backup(0);
/* 125 */       if (this.tokenBegin == -1)
/* 126 */         this.tokenBegin = this.bufpos; 
/* 127 */       throw e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public char BeginToken() throws IOException {
/* 133 */     this.tokenBegin = -1;
/* 134 */     char c = readChar();
/* 135 */     this.tokenBegin = this.bufpos;
/* 137 */     return c;
/*     */   }
/*     */   
/*     */   protected void UpdateLineColumn(char c) {
/* 142 */     this.column++;
/* 144 */     if (this.prevCharIsLF) {
/* 146 */       this.prevCharIsLF = false;
/* 147 */       this.line += this.column = 1;
/* 149 */     } else if (this.prevCharIsCR) {
/* 151 */       this.prevCharIsCR = false;
/* 152 */       if (c == '\n') {
/* 154 */         this.prevCharIsLF = true;
/*     */       } else {
/* 157 */         this.line += this.column = 1;
/*     */       } 
/*     */     } 
/* 160 */     switch (c) {
/*     */       case '\r':
/* 163 */         this.prevCharIsCR = true;
/*     */         break;
/*     */       case '\n':
/* 166 */         this.prevCharIsLF = true;
/*     */         break;
/*     */       case '\t':
/* 169 */         this.column--;
/* 170 */         this.column += this.tabSize - this.column % this.tabSize;
/*     */         break;
/*     */     } 
/* 176 */     this.bufline[this.bufpos] = this.line;
/* 177 */     this.bufcolumn[this.bufpos] = this.column;
/*     */   }
/*     */   
/*     */   public char readChar() throws IOException {
/* 182 */     if (this.inBuf > 0) {
/* 184 */       this.inBuf--;
/* 186 */       if (++this.bufpos == this.bufsize)
/* 187 */         this.bufpos = 0; 
/* 189 */       return this.buffer[this.bufpos];
/*     */     } 
/* 192 */     if (++this.bufpos >= this.maxNextCharInd)
/* 193 */       FillBuff(); 
/* 195 */     char c = this.buffer[this.bufpos];
/* 197 */     UpdateLineColumn(c);
/* 198 */     return c;
/*     */   }
/*     */   
/*     */   public int getColumn() {
/* 207 */     return this.bufcolumn[this.bufpos];
/*     */   }
/*     */   
/*     */   public int getLine() {
/* 216 */     return this.bufline[this.bufpos];
/*     */   }
/*     */   
/*     */   public int getEndColumn() {
/* 220 */     return this.bufcolumn[this.bufpos];
/*     */   }
/*     */   
/*     */   public int getEndLine() {
/* 224 */     return this.bufline[this.bufpos];
/*     */   }
/*     */   
/*     */   public int getBeginColumn() {
/* 228 */     return this.bufcolumn[this.tokenBegin];
/*     */   }
/*     */   
/*     */   public int getBeginLine() {
/* 232 */     return this.bufline[this.tokenBegin];
/*     */   }
/*     */   
/*     */   public void backup(int amount) {
/* 237 */     this.inBuf += amount;
/* 238 */     if ((this.bufpos -= amount) < 0)
/* 239 */       this.bufpos += this.bufsize; 
/*     */   }
/*     */   
/*     */   public SimpleCharStream(Reader dstream, int startline, int startcolumn, int buffersize) {
/* 245 */     this.inputStream = dstream;
/* 246 */     this.line = startline;
/* 247 */     this.column = startcolumn - 1;
/* 249 */     this.available = this.bufsize = buffersize;
/* 250 */     this.buffer = new char[buffersize];
/* 251 */     this.bufline = new int[buffersize];
/* 252 */     this.bufcolumn = new int[buffersize];
/*     */   }
/*     */   
/*     */   public SimpleCharStream(Reader dstream, int startline, int startcolumn) {
/* 258 */     this(dstream, startline, startcolumn, 4096);
/*     */   }
/*     */   
/*     */   public SimpleCharStream(Reader dstream) {
/* 263 */     this(dstream, 1, 1, 4096);
/*     */   }
/*     */   
/*     */   public void ReInit(Reader dstream, int startline, int startcolumn, int buffersize) {
/* 268 */     this.inputStream = dstream;
/* 269 */     this.line = startline;
/* 270 */     this.column = startcolumn - 1;
/* 272 */     if (this.buffer == null || buffersize != this.buffer.length) {
/* 274 */       this.available = this.bufsize = buffersize;
/* 275 */       this.buffer = new char[buffersize];
/* 276 */       this.bufline = new int[buffersize];
/* 277 */       this.bufcolumn = new int[buffersize];
/*     */     } 
/* 279 */     this.prevCharIsLF = this.prevCharIsCR = false;
/* 280 */     this.tokenBegin = this.inBuf = this.maxNextCharInd = 0;
/* 281 */     this.bufpos = -1;
/*     */   }
/*     */   
/*     */   public void ReInit(Reader dstream, int startline, int startcolumn) {
/* 287 */     ReInit(dstream, startline, startcolumn, 4096);
/*     */   }
/*     */   
/*     */   public void ReInit(Reader dstream) {
/* 292 */     ReInit(dstream, 1, 1, 4096);
/*     */   }
/*     */   
/*     */   public SimpleCharStream(InputStream dstream, String encoding, int startline, int startcolumn, int buffersize) throws UnsupportedEncodingException {
/* 297 */     this((encoding == null) ? new InputStreamReader(dstream) : new InputStreamReader(dstream, encoding), startline, startcolumn, buffersize);
/*     */   }
/*     */   
/*     */   public SimpleCharStream(InputStream dstream, int startline, int startcolumn, int buffersize) {
/* 303 */     this(new InputStreamReader(dstream), startline, startcolumn, buffersize);
/*     */   }
/*     */   
/*     */   public SimpleCharStream(InputStream dstream, String encoding, int startline, int startcolumn) throws UnsupportedEncodingException {
/* 309 */     this(dstream, encoding, startline, startcolumn, 4096);
/*     */   }
/*     */   
/*     */   public SimpleCharStream(InputStream dstream, int startline, int startcolumn) {
/* 315 */     this(dstream, startline, startcolumn, 4096);
/*     */   }
/*     */   
/*     */   public SimpleCharStream(InputStream dstream, String encoding) throws UnsupportedEncodingException {
/* 320 */     this(dstream, encoding, 1, 1, 4096);
/*     */   }
/*     */   
/*     */   public SimpleCharStream(InputStream dstream) {
/* 325 */     this(dstream, 1, 1, 4096);
/*     */   }
/*     */   
/*     */   public void ReInit(InputStream dstream, String encoding, int startline, int startcolumn, int buffersize) throws UnsupportedEncodingException {
/* 331 */     ReInit((encoding == null) ? new InputStreamReader(dstream) : new InputStreamReader(dstream, encoding), startline, startcolumn, buffersize);
/*     */   }
/*     */   
/*     */   public void ReInit(InputStream dstream, int startline, int startcolumn, int buffersize) {
/* 337 */     ReInit(new InputStreamReader(dstream), startline, startcolumn, buffersize);
/*     */   }
/*     */   
/*     */   public void ReInit(InputStream dstream, String encoding) throws UnsupportedEncodingException {
/* 342 */     ReInit(dstream, encoding, 1, 1, 4096);
/*     */   }
/*     */   
/*     */   public void ReInit(InputStream dstream) {
/* 347 */     ReInit(dstream, 1, 1, 4096);
/*     */   }
/*     */   
/*     */   public void ReInit(InputStream dstream, String encoding, int startline, int startcolumn) throws UnsupportedEncodingException {
/* 352 */     ReInit(dstream, encoding, startline, startcolumn, 4096);
/*     */   }
/*     */   
/*     */   public void ReInit(InputStream dstream, int startline, int startcolumn) {
/* 357 */     ReInit(dstream, startline, startcolumn, 4096);
/*     */   }
/*     */   
/*     */   public String GetImage() {
/* 361 */     if (this.bufpos >= this.tokenBegin)
/* 362 */       return new String(this.buffer, this.tokenBegin, this.bufpos - this.tokenBegin + 1); 
/* 364 */     return new String(this.buffer, this.tokenBegin, this.bufsize - this.tokenBegin) + new String(this.buffer, 0, this.bufpos + 1);
/*     */   }
/*     */   
/*     */   public char[] GetSuffix(int len) {
/* 370 */     char[] ret = new char[len];
/* 372 */     if (this.bufpos + 1 >= len) {
/* 373 */       System.arraycopy(this.buffer, this.bufpos - len + 1, ret, 0, len);
/*     */     } else {
/* 376 */       System.arraycopy(this.buffer, this.bufsize - len - this.bufpos - 1, ret, 0, len - this.bufpos - 1);
/* 378 */       System.arraycopy(this.buffer, 0, ret, len - this.bufpos - 1, this.bufpos + 1);
/*     */     } 
/* 381 */     return ret;
/*     */   }
/*     */   
/*     */   public void Done() {
/* 386 */     this.buffer = null;
/* 387 */     this.bufline = null;
/* 388 */     this.bufcolumn = null;
/*     */   }
/*     */   
/*     */   public void adjustBeginLineColumn(int newLine, int newCol) {
/* 396 */     int len, start = this.tokenBegin;
/* 399 */     if (this.bufpos >= this.tokenBegin) {
/* 401 */       len = this.bufpos - this.tokenBegin + this.inBuf + 1;
/*     */     } else {
/* 405 */       len = this.bufsize - this.tokenBegin + this.bufpos + 1 + this.inBuf;
/*     */     } 
/* 408 */     int i = 0, j = 0, k = 0;
/* 409 */     int nextColDiff = 0, columnDiff = 0;
/* 412 */     while (i < len && this.bufline[j = start % this.bufsize] == this.bufline[k = ++start % this.bufsize]) {
/* 414 */       this.bufline[j] = newLine;
/* 415 */       nextColDiff = columnDiff + this.bufcolumn[k] - this.bufcolumn[j];
/* 416 */       this.bufcolumn[j] = newCol + columnDiff;
/* 417 */       columnDiff = nextColDiff;
/* 418 */       i++;
/*     */     } 
/* 421 */     if (i < len) {
/* 423 */       this.bufline[j] = newLine++;
/* 424 */       this.bufcolumn[j] = newCol + columnDiff;
/* 426 */       while (i++ < len) {
/* 428 */         if (this.bufline[j = start % this.bufsize] != this.bufline[++start % this.bufsize]) {
/* 429 */           this.bufline[j] = newLine++;
/*     */           continue;
/*     */         } 
/* 431 */         this.bufline[j] = newLine;
/*     */       } 
/*     */     } 
/* 435 */     this.line = this.bufline[j];
/* 436 */     this.column = this.bufcolumn[j];
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\SimpleCharStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */