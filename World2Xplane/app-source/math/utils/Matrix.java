/*     */ package math.utils;
/*     */ 
/*     */ public class Matrix {
/*  17 */   public static double tolerance = 1.0E-14D;
/*     */   
/*     */   private int nRows;
/*     */   
/*     */   private int nCols;
/*     */   
/*     */   private double[][] el;
/*     */   
/*     */   public Matrix() {
/*  39 */     this(1, 1);
/*     */   }
/*     */   
/*     */   public Matrix(int nbRows, int nbCols) {
/*  44 */     this.nRows = nbRows;
/*  45 */     this.nCols = nbCols;
/*  46 */     this.el = new double[this.nRows][this.nCols];
/*  47 */     setToIdentity();
/*     */   }
/*     */   
/*     */   public Matrix(double[][] coef) {
/*  54 */     if (coef == null) {
/*  55 */       this.nRows = 1;
/*  56 */       this.nCols = 1;
/*  57 */       this.el = new double[this.nRows][this.nCols];
/*  58 */       setToIdentity();
/*     */       return;
/*     */     } 
/*  62 */     this.nRows = coef.length;
/*  63 */     this.nCols = (coef[0]).length;
/*  64 */     this.el = new double[this.nRows][this.nCols];
/*  65 */     for (int r = 0; r < this.nRows; r++) {
/*  66 */       for (int c = 0; c < this.nCols; c++)
/*  67 */         this.el[r][c] = coef[r][c]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getCoef(int row, int col) {
/*  78 */     return this.el[row - 1][col - 1];
/*     */   }
/*     */   
/*     */   public int getRows() {
/*  83 */     return this.nRows;
/*     */   }
/*     */   
/*     */   public int getColumns() {
/*  88 */     return this.nCols;
/*     */   }
/*     */   
/*     */   public boolean isSquare() {
/*  96 */     return (this.nCols == this.nRows);
/*     */   }
/*     */   
/*     */   public void setCoef(int row, int col, double coef) {
/* 107 */     this.el[row - 1][col - 1] = coef;
/*     */   }
/*     */   
/*     */   public Matrix multiplyWith(Matrix matrix) {
/* 121 */     if (this.nCols != matrix.nRows)
/* 122 */       throw new IllegalArgumentException("Matrix sizes do not match"); 
/* 125 */     Matrix m = new Matrix(this.nRows, matrix.nCols);
/* 127 */     for (int r = 0; r < m.nRows; r++) {
/* 128 */       for (int c = 0; c < m.nCols; c++) {
/* 129 */         double sum = 0.0D;
/* 130 */         for (int i = 0; i < this.nCols; i++)
/* 131 */           sum += this.el[r][i] * matrix.el[i][c]; 
/* 132 */         m.el[r][c] = sum;
/*     */       } 
/*     */     } 
/* 135 */     return m;
/*     */   }
/*     */   
/*     */   public double[] multiplyWith(double[] coefs) {
/* 146 */     if (coefs == null)
/* 147 */       throw new NullPointerException(); 
/* 151 */     if (coefs.length != this.nCols)
/* 152 */       throw new IllegalArgumentException("Matrix sizes do not match"); 
/* 155 */     double[] res = new double[this.nRows];
/* 157 */     for (int r = 0; r < this.nRows; r++) {
/* 158 */       double sum = 0.0D;
/* 159 */       for (int c = 0; c < this.nCols; c++)
/* 160 */         sum += this.el[r][c] * coefs[c]; 
/* 161 */       res[r] = sum;
/*     */     } 
/* 163 */     return res;
/*     */   }
/*     */   
/*     */   public double[] multiplyWith(double[] src, double[] res) {
/* 174 */     if (src == null)
/* 175 */       throw new NullPointerException(); 
/* 178 */     if (src.length != this.nCols)
/* 179 */       throw new IllegalArgumentException("Matrix sizes do not match"); 
/* 181 */     if (src.length != res.length)
/* 182 */       res = new double[this.nRows]; 
/* 186 */     for (int r = 0; r < this.nRows; r++) {
/* 187 */       double sum = 0.0D;
/* 188 */       for (int c = 0; c < this.nCols; c++)
/* 189 */         sum += this.el[r][c] * src[c]; 
/* 190 */       res[r] = sum;
/*     */     } 
/* 192 */     return res;
/*     */   }
/*     */   
/*     */   public void transpose() {
/* 197 */     int tmp = this.nCols;
/* 198 */     this.nCols = this.nRows;
/* 199 */     this.nRows = tmp;
/* 200 */     double[][] oldData = this.el;
/* 201 */     this.el = new double[this.nRows][this.nCols];
/* 203 */     for (int r = 0; r < this.nRows; r++) {
/* 204 */       for (int c = 0; c < this.nCols; c++)
/* 205 */         this.el[r][c] = oldData[c][r]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Matrix getTranspose() {
/* 213 */     Matrix mat = new Matrix(this.nCols, this.nRows);
/* 215 */     for (int r = 0; r < this.nRows; r++) {
/* 216 */       for (int c = 0; c < this.nCols; c++)
/* 217 */         mat.el[c][r] = this.el[r][c]; 
/*     */     } 
/* 218 */     return mat;
/*     */   }
/*     */   
/*     */   public double[] solve(double[] vector) {
/* 231 */     if (vector == null)
/* 232 */       throw new NullPointerException(); 
/* 234 */     if (vector.length != this.nRows)
/* 235 */       throw new IllegalArgumentException("Matrix and vector dimensions do not match"); 
/* 237 */     if (this.nCols != this.nRows)
/* 238 */       throw new UnsupportedOperationException("Try to invert non square Matrix"); 
/* 240 */     double[] res = new double[vector.length];
/* 241 */     for (int i = 0; i < this.nRows; i++)
/* 242 */       res[i] = vector[i]; 
/* 245 */     Matrix mat = new Matrix(this.el);
/* 252 */     for (int r = 0; r < this.nRows; r++) {
/* 254 */       int p = r;
/* 256 */       while (Math.abs(mat.el[p][r]) < tolerance && p < this.nRows)
/* 257 */         p++; 
/* 259 */       if (p == this.nRows)
/* 260 */         throw new ArithmeticException("Degenerated linear system"); 
/*     */       int c;
/* 263 */       for (c = 0; c < this.nRows; c++) {
/* 264 */         double d = mat.el[r][c];
/* 265 */         mat.el[r][c] = mat.el[p][c];
/* 266 */         mat.el[p][c] = d;
/*     */       } 
/* 270 */       double tmp = res[r];
/* 271 */       res[r] = res[p];
/* 272 */       res[p] = tmp;
/* 274 */       double pivot = mat.el[r][r];
/* 277 */       for (c = r + 1; c < this.nRows; c++)
/* 278 */         mat.el[r][c] = mat.el[r][c] / pivot; 
/* 279 */       res[r] = res[r] / pivot;
/* 280 */       mat.el[r][r] = 1.0D;
/*     */       int r2;
/* 283 */       for (r2 = 0; r2 < r; r2++) {
/* 284 */         pivot = mat.el[r2][r];
/* 285 */         for (c = r + 1; c < this.nRows; c++)
/* 286 */           mat.el[r2][c] = mat.el[r2][c] - pivot * mat.el[r][c]; 
/* 287 */         res[r2] = res[r2] - pivot * res[r];
/* 288 */         mat.el[r2][r] = 0.0D;
/*     */       } 
/* 292 */       for (r2 = r + 1; r2 < this.nRows; r2++) {
/* 293 */         pivot = mat.el[r2][r];
/* 294 */         for (c = r + 1; c < this.nRows; c++)
/* 295 */           mat.el[r2][c] = mat.el[r2][c] - pivot * mat.el[r][c]; 
/* 296 */         res[r2] = res[r2] - pivot * res[r];
/* 297 */         mat.el[r2][r] = 0.0D;
/*     */       } 
/*     */     } 
/* 301 */     return res;
/*     */   }
/*     */   
/*     */   public void setToIdentity() {
/* 312 */     for (int r = 0; r < this.nRows; r++) {
/* 313 */       for (int c = 0; c < this.nCols; c++)
/* 314 */         this.el[r][c] = 0.0D; 
/*     */     } 
/* 315 */     for (int i = Math.min(this.nRows, this.nCols) - 1; i >= 0; i--)
/* 316 */       this.el[i][i] = 1.0D; 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 324 */     String res = new String("");
/* 325 */     res = res.concat("Matrix size : " + Integer.toString(this.nRows) + 
/* 326 */         " rows and " + Integer.toString(this.nCols) + " columns.\n");
/* 327 */     for (int r = 0; r < this.nRows; r++) {
/* 328 */       for (int c = 0; c < this.nCols; c++)
/* 329 */         res = res.concat(Double.toString(this.el[r][c])).concat(" "); 
/* 330 */       res = res.concat(new String("\n"));
/*     */     } 
/* 332 */     return res;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\mat\\utils\Matrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */