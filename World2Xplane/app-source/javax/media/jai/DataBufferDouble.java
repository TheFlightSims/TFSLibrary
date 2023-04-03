/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.image.DataBuffer;
/*     */ 
/*     */ public class DataBufferDouble extends DataBuffer {
/*     */   protected double[][] bankdata;
/*     */   
/*     */   protected double[] data;
/*     */   
/*     */   public DataBufferDouble(int size) {
/*  36 */     super(5, size);
/*  37 */     this.data = new double[size];
/*  38 */     this.bankdata = new double[1][];
/*  39 */     this.bankdata[0] = this.data;
/*     */   }
/*     */   
/*     */   public DataBufferDouble(int size, int numBanks) {
/*  52 */     super(5, size, numBanks);
/*  53 */     this.bankdata = new double[numBanks][];
/*  54 */     for (int i = 0; i < numBanks; i++)
/*  55 */       this.bankdata[i] = new double[size]; 
/*  57 */     this.data = this.bankdata[0];
/*     */   }
/*     */   
/*     */   public DataBufferDouble(double[] dataArray, int size) {
/*  72 */     super(5, size);
/*  73 */     if (dataArray.length < size)
/*  74 */       throw new RuntimeException(JaiI18N.getString("DataBuffer0")); 
/*  75 */     this.data = dataArray;
/*  76 */     this.bankdata = new double[1][];
/*  77 */     this.bankdata[0] = this.data;
/*     */   }
/*     */   
/*     */   public DataBufferDouble(double[] dataArray, int size, int offset) {
/*  94 */     super(5, size, 1, offset);
/*  95 */     if (dataArray.length < size)
/*  96 */       throw new RuntimeException(JaiI18N.getString("DataBuffer1")); 
/*  97 */     this.data = dataArray;
/*  98 */     this.bankdata = new double[1][];
/*  99 */     this.bankdata[0] = this.data;
/*     */   }
/*     */   
/*     */   public DataBufferDouble(double[][] dataArray, int size) {
/* 114 */     super(5, size, dataArray.length);
/* 115 */     this.bankdata = dataArray;
/* 116 */     this.data = this.bankdata[0];
/*     */   }
/*     */   
/*     */   public DataBufferDouble(double[][] dataArray, int size, int[] offsets) {
/* 133 */     super(5, size, dataArray.length, offsets);
/* 134 */     this.bankdata = dataArray;
/* 135 */     this.data = this.bankdata[0];
/*     */   }
/*     */   
/*     */   public double[] getData() {
/* 140 */     return this.data;
/*     */   }
/*     */   
/*     */   public double[] getData(int bank) {
/* 145 */     return this.bankdata[bank];
/*     */   }
/*     */   
/*     */   public double[][] getBankData() {
/* 150 */     return this.bankdata;
/*     */   }
/*     */   
/*     */   public int getElem(int i) {
/* 162 */     return (int)this.data[i + this.offset];
/*     */   }
/*     */   
/*     */   public int getElem(int bank, int i) {
/* 175 */     return (int)this.bankdata[bank][i + this.offsets[bank]];
/*     */   }
/*     */   
/*     */   public void setElem(int i, int val) {
/* 186 */     this.data[i + this.offset] = val;
/*     */   }
/*     */   
/*     */   public void setElem(int bank, int i, int val) {
/* 198 */     this.bankdata[bank][i + this.offsets[bank]] = val;
/*     */   }
/*     */   
/*     */   public float getElemFloat(int i) {
/* 210 */     return (float)this.data[i + this.offset];
/*     */   }
/*     */   
/*     */   public float getElemFloat(int bank, int i) {
/* 223 */     return (float)this.bankdata[bank][i + this.offsets[bank]];
/*     */   }
/*     */   
/*     */   public void setElemFloat(int i, float val) {
/* 234 */     this.data[i + this.offset] = val;
/*     */   }
/*     */   
/*     */   public void setElemFloat(int bank, int i, float val) {
/* 246 */     this.bankdata[bank][i + this.offsets[bank]] = val;
/*     */   }
/*     */   
/*     */   public double getElemDouble(int i) {
/* 258 */     return this.data[i + this.offset];
/*     */   }
/*     */   
/*     */   public double getElemDouble(int bank, int i) {
/* 271 */     return this.bankdata[bank][i + this.offsets[bank]];
/*     */   }
/*     */   
/*     */   public void setElemDouble(int i, double val) {
/* 282 */     this.data[i + this.offset] = val;
/*     */   }
/*     */   
/*     */   public void setElemDouble(int bank, int i, double val) {
/* 294 */     this.bankdata[bank][i + this.offsets[bank]] = val;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\DataBufferDouble.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */