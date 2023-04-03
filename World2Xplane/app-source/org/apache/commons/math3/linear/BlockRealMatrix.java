/*      */ package org.apache.commons.math3.linear;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.util.Arrays;
/*      */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*      */ import org.apache.commons.math3.exception.NoDataException;
/*      */ import org.apache.commons.math3.exception.NullArgumentException;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ import org.apache.commons.math3.util.MathUtils;
/*      */ 
/*      */ public class BlockRealMatrix extends AbstractRealMatrix implements Serializable {
/*      */   public static final int BLOCK_SIZE = 52;
/*      */   
/*      */   private static final long serialVersionUID = 4991895511313664478L;
/*      */   
/*      */   private final double[][] blocks;
/*      */   
/*      */   private final int rows;
/*      */   
/*      */   private final int columns;
/*      */   
/*      */   private final int blockRows;
/*      */   
/*      */   private final int blockColumns;
/*      */   
/*      */   public BlockRealMatrix(int rows, int columns) {
/*   94 */     super(rows, columns);
/*   95 */     this.rows = rows;
/*   96 */     this.columns = columns;
/*   99 */     this.blockRows = (rows + 52 - 1) / 52;
/*  100 */     this.blockColumns = (columns + 52 - 1) / 52;
/*  103 */     this.blocks = createBlocksLayout(rows, columns);
/*      */   }
/*      */   
/*      */   public BlockRealMatrix(double[][] rawData) {
/*  120 */     this(rawData.length, (rawData[0]).length, toBlocksLayout(rawData), false);
/*      */   }
/*      */   
/*      */   public BlockRealMatrix(int rows, int columns, double[][] blockData, boolean copyArray) {
/*  139 */     super(rows, columns);
/*  140 */     this.rows = rows;
/*  141 */     this.columns = columns;
/*  144 */     this.blockRows = (rows + 52 - 1) / 52;
/*  145 */     this.blockColumns = (columns + 52 - 1) / 52;
/*  147 */     if (copyArray) {
/*  149 */       this.blocks = new double[this.blockRows * this.blockColumns][];
/*      */     } else {
/*  152 */       this.blocks = blockData;
/*      */     } 
/*  155 */     int index = 0;
/*  156 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/*  157 */       int iHeight = blockHeight(iBlock);
/*  158 */       for (int jBlock = 0; jBlock < this.blockColumns; jBlock++, index++) {
/*  159 */         if ((blockData[index]).length != iHeight * blockWidth(jBlock))
/*  160 */           throw new DimensionMismatchException((blockData[index]).length, iHeight * blockWidth(jBlock)); 
/*  163 */         if (copyArray)
/*  164 */           this.blocks[index] = (double[])blockData[index].clone(); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static double[][] toBlocksLayout(double[][] rawData) {
/*  192 */     int rows = rawData.length;
/*  193 */     int columns = (rawData[0]).length;
/*  194 */     int blockRows = (rows + 52 - 1) / 52;
/*  195 */     int blockColumns = (columns + 52 - 1) / 52;
/*  198 */     for (int i = 0; i < rawData.length; i++) {
/*  199 */       int length = (rawData[i]).length;
/*  200 */       if (length != columns)
/*  201 */         throw new DimensionMismatchException(columns, length); 
/*      */     } 
/*  206 */     double[][] blocks = new double[blockRows * blockColumns][];
/*  207 */     int blockIndex = 0;
/*  208 */     for (int iBlock = 0; iBlock < blockRows; iBlock++) {
/*  209 */       int pStart = iBlock * 52;
/*  210 */       int pEnd = FastMath.min(pStart + 52, rows);
/*  211 */       int iHeight = pEnd - pStart;
/*  212 */       for (int jBlock = 0; jBlock < blockColumns; jBlock++) {
/*  213 */         int qStart = jBlock * 52;
/*  214 */         int qEnd = FastMath.min(qStart + 52, columns);
/*  215 */         int jWidth = qEnd - qStart;
/*  218 */         double[] block = new double[iHeight * jWidth];
/*  219 */         blocks[blockIndex] = block;
/*  222 */         int index = 0;
/*  223 */         for (int p = pStart; p < pEnd; p++) {
/*  224 */           System.arraycopy(rawData[p], qStart, block, index, jWidth);
/*  225 */           index += jWidth;
/*      */         } 
/*  227 */         blockIndex++;
/*      */       } 
/*      */     } 
/*  231 */     return blocks;
/*      */   }
/*      */   
/*      */   public static double[][] createBlocksLayout(int rows, int columns) {
/*  247 */     int blockRows = (rows + 52 - 1) / 52;
/*  248 */     int blockColumns = (columns + 52 - 1) / 52;
/*  250 */     double[][] blocks = new double[blockRows * blockColumns][];
/*  251 */     int blockIndex = 0;
/*  252 */     for (int iBlock = 0; iBlock < blockRows; iBlock++) {
/*  253 */       int pStart = iBlock * 52;
/*  254 */       int pEnd = FastMath.min(pStart + 52, rows);
/*  255 */       int iHeight = pEnd - pStart;
/*  256 */       for (int jBlock = 0; jBlock < blockColumns; jBlock++) {
/*  257 */         int qStart = jBlock * 52;
/*  258 */         int qEnd = FastMath.min(qStart + 52, columns);
/*  259 */         int jWidth = qEnd - qStart;
/*  260 */         blocks[blockIndex] = new double[iHeight * jWidth];
/*  261 */         blockIndex++;
/*      */       } 
/*      */     } 
/*  265 */     return blocks;
/*      */   }
/*      */   
/*      */   public BlockRealMatrix createMatrix(int rowDimension, int columnDimension) {
/*  271 */     return new BlockRealMatrix(rowDimension, columnDimension);
/*      */   }
/*      */   
/*      */   public BlockRealMatrix copy() {
/*  278 */     BlockRealMatrix copied = new BlockRealMatrix(this.rows, this.columns);
/*  281 */     for (int i = 0; i < this.blocks.length; i++)
/*  282 */       System.arraycopy(this.blocks[i], 0, copied.blocks[i], 0, (this.blocks[i]).length); 
/*  285 */     return copied;
/*      */   }
/*      */   
/*      */   public BlockRealMatrix add(RealMatrix m) {
/*      */     try {
/*  292 */       return add((BlockRealMatrix)m);
/*  293 */     } catch (ClassCastException cce) {
/*  295 */       MatrixUtils.checkAdditionCompatible(this, m);
/*  297 */       BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
/*  300 */       int blockIndex = 0;
/*  301 */       for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
/*  302 */         for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
/*  305 */           double[] outBlock = out.blocks[blockIndex];
/*  306 */           double[] tBlock = this.blocks[blockIndex];
/*  307 */           int pStart = iBlock * 52;
/*  308 */           int pEnd = FastMath.min(pStart + 52, this.rows);
/*  309 */           int qStart = jBlock * 52;
/*  310 */           int qEnd = FastMath.min(qStart + 52, this.columns);
/*  311 */           int k = 0;
/*  312 */           for (int p = pStart; p < pEnd; p++) {
/*  313 */             for (int q = qStart; q < qEnd; q++) {
/*  314 */               outBlock[k] = tBlock[k] + m.getEntry(p, q);
/*  315 */               k++;
/*      */             } 
/*      */           } 
/*  319 */           blockIndex++;
/*      */         } 
/*      */       } 
/*  323 */       return out;
/*      */     } 
/*      */   }
/*      */   
/*      */   public BlockRealMatrix add(BlockRealMatrix m) {
/*  337 */     MatrixUtils.checkAdditionCompatible(this, m);
/*  339 */     BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
/*  342 */     for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
/*  343 */       double[] outBlock = out.blocks[blockIndex];
/*  344 */       double[] tBlock = this.blocks[blockIndex];
/*  345 */       double[] mBlock = m.blocks[blockIndex];
/*  346 */       for (int k = 0; k < outBlock.length; k++)
/*  347 */         outBlock[k] = tBlock[k] + mBlock[k]; 
/*      */     } 
/*  351 */     return out;
/*      */   }
/*      */   
/*      */   public BlockRealMatrix subtract(RealMatrix m) {
/*      */     try {
/*  358 */       return subtract((BlockRealMatrix)m);
/*  359 */     } catch (ClassCastException cce) {
/*  361 */       MatrixUtils.checkSubtractionCompatible(this, m);
/*  363 */       BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
/*  366 */       int blockIndex = 0;
/*  367 */       for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
/*  368 */         for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
/*  371 */           double[] outBlock = out.blocks[blockIndex];
/*  372 */           double[] tBlock = this.blocks[blockIndex];
/*  373 */           int pStart = iBlock * 52;
/*  374 */           int pEnd = FastMath.min(pStart + 52, this.rows);
/*  375 */           int qStart = jBlock * 52;
/*  376 */           int qEnd = FastMath.min(qStart + 52, this.columns);
/*  377 */           int k = 0;
/*  378 */           for (int p = pStart; p < pEnd; p++) {
/*  379 */             for (int q = qStart; q < qEnd; q++) {
/*  380 */               outBlock[k] = tBlock[k] - m.getEntry(p, q);
/*  381 */               k++;
/*      */             } 
/*      */           } 
/*  385 */           blockIndex++;
/*      */         } 
/*      */       } 
/*  389 */       return out;
/*      */     } 
/*      */   }
/*      */   
/*      */   public BlockRealMatrix subtract(BlockRealMatrix m) {
/*  403 */     MatrixUtils.checkSubtractionCompatible(this, m);
/*  405 */     BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
/*  408 */     for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
/*  409 */       double[] outBlock = out.blocks[blockIndex];
/*  410 */       double[] tBlock = this.blocks[blockIndex];
/*  411 */       double[] mBlock = m.blocks[blockIndex];
/*  412 */       for (int k = 0; k < outBlock.length; k++)
/*  413 */         outBlock[k] = tBlock[k] - mBlock[k]; 
/*      */     } 
/*  417 */     return out;
/*      */   }
/*      */   
/*      */   public BlockRealMatrix scalarAdd(double d) {
/*  424 */     BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
/*  427 */     for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
/*  428 */       double[] outBlock = out.blocks[blockIndex];
/*  429 */       double[] tBlock = this.blocks[blockIndex];
/*  430 */       for (int k = 0; k < outBlock.length; k++)
/*  431 */         outBlock[k] = tBlock[k] + d; 
/*      */     } 
/*  435 */     return out;
/*      */   }
/*      */   
/*      */   public RealMatrix scalarMultiply(double d) {
/*  441 */     BlockRealMatrix out = new BlockRealMatrix(this.rows, this.columns);
/*  444 */     for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
/*  445 */       double[] outBlock = out.blocks[blockIndex];
/*  446 */       double[] tBlock = this.blocks[blockIndex];
/*  447 */       for (int k = 0; k < outBlock.length; k++)
/*  448 */         outBlock[k] = tBlock[k] * d; 
/*      */     } 
/*  452 */     return out;
/*      */   }
/*      */   
/*      */   public BlockRealMatrix multiply(RealMatrix m) {
/*      */     try {
/*  459 */       return multiply((BlockRealMatrix)m);
/*  460 */     } catch (ClassCastException cce) {
/*  462 */       MatrixUtils.checkMultiplicationCompatible(this, m);
/*  464 */       BlockRealMatrix out = new BlockRealMatrix(this.rows, m.getColumnDimension());
/*  467 */       int blockIndex = 0;
/*  468 */       for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
/*  469 */         int pStart = iBlock * 52;
/*  470 */         int pEnd = FastMath.min(pStart + 52, this.rows);
/*  472 */         for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
/*  473 */           int qStart = jBlock * 52;
/*  474 */           int qEnd = FastMath.min(qStart + 52, m.getColumnDimension());
/*  477 */           double[] outBlock = out.blocks[blockIndex];
/*  480 */           for (int kBlock = 0; kBlock < this.blockColumns; kBlock++) {
/*  481 */             int kWidth = blockWidth(kBlock);
/*  482 */             double[] tBlock = this.blocks[iBlock * this.blockColumns + kBlock];
/*  483 */             int rStart = kBlock * 52;
/*  484 */             int k = 0;
/*  485 */             for (int p = pStart; p < pEnd; p++) {
/*  486 */               int lStart = (p - pStart) * kWidth;
/*  487 */               int lEnd = lStart + kWidth;
/*  488 */               for (int q = qStart; q < qEnd; q++) {
/*  489 */                 double sum = 0.0D;
/*  490 */                 int r = rStart;
/*  491 */                 for (int l = lStart; l < lEnd; l++) {
/*  492 */                   sum += tBlock[l] * m.getEntry(r, q);
/*  493 */                   r++;
/*      */                 } 
/*  495 */                 outBlock[k] = outBlock[k] + sum;
/*  496 */                 k++;
/*      */               } 
/*      */             } 
/*      */           } 
/*  501 */           blockIndex++;
/*      */         } 
/*      */       } 
/*  505 */       return out;
/*      */     } 
/*      */   }
/*      */   
/*      */   public BlockRealMatrix multiply(BlockRealMatrix m) {
/*  519 */     MatrixUtils.checkMultiplicationCompatible(this, m);
/*  521 */     BlockRealMatrix out = new BlockRealMatrix(this.rows, m.columns);
/*  524 */     int blockIndex = 0;
/*  525 */     for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
/*  527 */       int pStart = iBlock * 52;
/*  528 */       int pEnd = FastMath.min(pStart + 52, this.rows);
/*  530 */       for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
/*  531 */         int jWidth = out.blockWidth(jBlock);
/*  532 */         int jWidth2 = jWidth + jWidth;
/*  533 */         int jWidth3 = jWidth2 + jWidth;
/*  534 */         int jWidth4 = jWidth3 + jWidth;
/*  537 */         double[] outBlock = out.blocks[blockIndex];
/*  540 */         for (int kBlock = 0; kBlock < this.blockColumns; kBlock++) {
/*  541 */           int kWidth = blockWidth(kBlock);
/*  542 */           double[] tBlock = this.blocks[iBlock * this.blockColumns + kBlock];
/*  543 */           double[] mBlock = m.blocks[kBlock * m.blockColumns + jBlock];
/*  544 */           int k = 0;
/*  545 */           for (int p = pStart; p < pEnd; p++) {
/*  546 */             int lStart = (p - pStart) * kWidth;
/*  547 */             int lEnd = lStart + kWidth;
/*  548 */             for (int nStart = 0; nStart < jWidth; nStart++) {
/*  549 */               double sum = 0.0D;
/*  550 */               int l = lStart;
/*  551 */               int n = nStart;
/*  552 */               while (l < lEnd - 3) {
/*  553 */                 sum += tBlock[l] * mBlock[n] + tBlock[l + 1] * mBlock[n + jWidth] + tBlock[l + 2] * mBlock[n + jWidth2] + tBlock[l + 3] * mBlock[n + jWidth3];
/*  557 */                 l += 4;
/*  558 */                 n += jWidth4;
/*      */               } 
/*  560 */               while (l < lEnd) {
/*  561 */                 sum += tBlock[l++] * mBlock[n];
/*  562 */                 n += jWidth;
/*      */               } 
/*  564 */               outBlock[k] = outBlock[k] + sum;
/*  565 */               k++;
/*      */             } 
/*      */           } 
/*      */         } 
/*  570 */         blockIndex++;
/*      */       } 
/*      */     } 
/*  574 */     return out;
/*      */   }
/*      */   
/*      */   public double[][] getData() {
/*  580 */     double[][] data = new double[getRowDimension()][getColumnDimension()];
/*  581 */     int lastColumns = this.columns - (this.blockColumns - 1) * 52;
/*  583 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/*  584 */       int pStart = iBlock * 52;
/*  585 */       int pEnd = FastMath.min(pStart + 52, this.rows);
/*  586 */       int regularPos = 0;
/*  587 */       int lastPos = 0;
/*  588 */       for (int p = pStart; p < pEnd; p++) {
/*  589 */         double[] dataP = data[p];
/*  590 */         int blockIndex = iBlock * this.blockColumns;
/*  591 */         int dataPos = 0;
/*  592 */         for (int jBlock = 0; jBlock < this.blockColumns - 1; jBlock++) {
/*  593 */           System.arraycopy(this.blocks[blockIndex++], regularPos, dataP, dataPos, 52);
/*  594 */           dataPos += 52;
/*      */         } 
/*  596 */         System.arraycopy(this.blocks[blockIndex], lastPos, dataP, dataPos, lastColumns);
/*  597 */         regularPos += 52;
/*  598 */         lastPos += lastColumns;
/*      */       } 
/*      */     } 
/*  602 */     return data;
/*      */   }
/*      */   
/*      */   public double getNorm() {
/*  608 */     double[] colSums = new double[52];
/*  609 */     double maxColSum = 0.0D;
/*  610 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/*  611 */       int jWidth = blockWidth(jBlock);
/*  612 */       Arrays.fill(colSums, 0, jWidth, 0.0D);
/*  613 */       for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/*  614 */         int iHeight = blockHeight(iBlock);
/*  615 */         double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  616 */         for (int i = 0; i < jWidth; i++) {
/*  617 */           double sum = 0.0D;
/*  618 */           for (int k = 0; k < iHeight; k++)
/*  619 */             sum += FastMath.abs(block[k * jWidth + i]); 
/*  621 */           colSums[i] = colSums[i] + sum;
/*      */         } 
/*      */       } 
/*  624 */       for (int j = 0; j < jWidth; j++)
/*  625 */         maxColSum = FastMath.max(maxColSum, colSums[j]); 
/*      */     } 
/*  628 */     return maxColSum;
/*      */   }
/*      */   
/*      */   public double getFrobeniusNorm() {
/*  634 */     double sum2 = 0.0D;
/*  635 */     for (int blockIndex = 0; blockIndex < this.blocks.length; blockIndex++) {
/*  636 */       for (double entry : this.blocks[blockIndex])
/*  637 */         sum2 += entry * entry; 
/*      */     } 
/*  640 */     return FastMath.sqrt(sum2);
/*      */   }
/*      */   
/*      */   public BlockRealMatrix getSubMatrix(int startRow, int endRow, int startColumn, int endColumn) {
/*  648 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/*  651 */     BlockRealMatrix out = new BlockRealMatrix(endRow - startRow + 1, endColumn - startColumn + 1);
/*  655 */     int blockStartRow = startRow / 52;
/*  656 */     int rowsShift = startRow % 52;
/*  657 */     int blockStartColumn = startColumn / 52;
/*  658 */     int columnsShift = startColumn % 52;
/*  661 */     int pBlock = blockStartRow;
/*  662 */     for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
/*  663 */       int iHeight = out.blockHeight(iBlock);
/*  664 */       int qBlock = blockStartColumn;
/*  665 */       for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
/*  666 */         int jWidth = out.blockWidth(jBlock);
/*  669 */         int outIndex = iBlock * out.blockColumns + jBlock;
/*  670 */         double[] outBlock = out.blocks[outIndex];
/*  671 */         int index = pBlock * this.blockColumns + qBlock;
/*  672 */         int width = blockWidth(qBlock);
/*  674 */         int heightExcess = iHeight + rowsShift - 52;
/*  675 */         int widthExcess = jWidth + columnsShift - 52;
/*  676 */         if (heightExcess > 0) {
/*  678 */           if (widthExcess > 0) {
/*  680 */             int width2 = blockWidth(qBlock + 1);
/*  681 */             copyBlockPart(this.blocks[index], width, rowsShift, 52, columnsShift, 52, outBlock, jWidth, 0, 0);
/*  685 */             copyBlockPart(this.blocks[index + 1], width2, rowsShift, 52, 0, widthExcess, outBlock, jWidth, 0, jWidth - widthExcess);
/*  689 */             copyBlockPart(this.blocks[index + this.blockColumns], width, 0, heightExcess, columnsShift, 52, outBlock, jWidth, iHeight - heightExcess, 0);
/*  693 */             copyBlockPart(this.blocks[index + this.blockColumns + 1], width2, 0, heightExcess, 0, widthExcess, outBlock, jWidth, iHeight - heightExcess, jWidth - widthExcess);
/*      */           } else {
/*  699 */             copyBlockPart(this.blocks[index], width, rowsShift, 52, columnsShift, jWidth + columnsShift, outBlock, jWidth, 0, 0);
/*  703 */             copyBlockPart(this.blocks[index + this.blockColumns], width, 0, heightExcess, columnsShift, jWidth + columnsShift, outBlock, jWidth, iHeight - heightExcess, 0);
/*      */           } 
/*  710 */         } else if (widthExcess > 0) {
/*  712 */           int width2 = blockWidth(qBlock + 1);
/*  713 */           copyBlockPart(this.blocks[index], width, rowsShift, iHeight + rowsShift, columnsShift, 52, outBlock, jWidth, 0, 0);
/*  717 */           copyBlockPart(this.blocks[index + 1], width2, rowsShift, iHeight + rowsShift, 0, widthExcess, outBlock, jWidth, 0, jWidth - widthExcess);
/*      */         } else {
/*  723 */           copyBlockPart(this.blocks[index], width, rowsShift, iHeight + rowsShift, columnsShift, jWidth + columnsShift, outBlock, jWidth, 0, 0);
/*      */         } 
/*  729 */         qBlock++;
/*      */       } 
/*  731 */       pBlock++;
/*      */     } 
/*  734 */     return out;
/*      */   }
/*      */   
/*      */   private void copyBlockPart(double[] srcBlock, int srcWidth, int srcStartRow, int srcEndRow, int srcStartColumn, int srcEndColumn, double[] dstBlock, int dstWidth, int dstStartRow, int dstStartColumn) {
/*  757 */     int length = srcEndColumn - srcStartColumn;
/*  758 */     int srcPos = srcStartRow * srcWidth + srcStartColumn;
/*  759 */     int dstPos = dstStartRow * dstWidth + dstStartColumn;
/*  760 */     for (int srcRow = srcStartRow; srcRow < srcEndRow; srcRow++) {
/*  761 */       System.arraycopy(srcBlock, srcPos, dstBlock, dstPos, length);
/*  762 */       srcPos += srcWidth;
/*  763 */       dstPos += dstWidth;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setSubMatrix(double[][] subMatrix, int row, int column) throws NoDataException, NullArgumentException {
/*  772 */     MathUtils.checkNotNull(subMatrix);
/*  773 */     int refLength = (subMatrix[0]).length;
/*  774 */     if (refLength == 0)
/*  775 */       throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN); 
/*  777 */     int endRow = row + subMatrix.length - 1;
/*  778 */     int endColumn = column + refLength - 1;
/*  779 */     MatrixUtils.checkSubMatrixIndex(this, row, endRow, column, endColumn);
/*  780 */     for (double[] subRow : subMatrix) {
/*  781 */       if (subRow.length != refLength)
/*  782 */         throw new DimensionMismatchException(refLength, subRow.length); 
/*      */     } 
/*  787 */     int blockStartRow = row / 52;
/*  788 */     int blockEndRow = (endRow + 52) / 52;
/*  789 */     int blockStartColumn = column / 52;
/*  790 */     int blockEndColumn = (endColumn + 52) / 52;
/*  793 */     for (int iBlock = blockStartRow; iBlock < blockEndRow; iBlock++) {
/*  794 */       int iHeight = blockHeight(iBlock);
/*  795 */       int firstRow = iBlock * 52;
/*  796 */       int iStart = FastMath.max(row, firstRow);
/*  797 */       int iEnd = FastMath.min(endRow + 1, firstRow + iHeight);
/*  799 */       for (int jBlock = blockStartColumn; jBlock < blockEndColumn; jBlock++) {
/*  800 */         int jWidth = blockWidth(jBlock);
/*  801 */         int firstColumn = jBlock * 52;
/*  802 */         int jStart = FastMath.max(column, firstColumn);
/*  803 */         int jEnd = FastMath.min(endColumn + 1, firstColumn + jWidth);
/*  804 */         int jLength = jEnd - jStart;
/*  807 */         double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  808 */         for (int i = iStart; i < iEnd; i++)
/*  809 */           System.arraycopy(subMatrix[i - row], jStart - column, block, (i - firstRow) * jWidth + jStart - firstColumn, jLength); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public BlockRealMatrix getRowMatrix(int row) {
/*  821 */     MatrixUtils.checkRowIndex(this, row);
/*  822 */     BlockRealMatrix out = new BlockRealMatrix(1, this.columns);
/*  825 */     int iBlock = row / 52;
/*  826 */     int iRow = row - iBlock * 52;
/*  827 */     int outBlockIndex = 0;
/*  828 */     int outIndex = 0;
/*  829 */     double[] outBlock = out.blocks[outBlockIndex];
/*  830 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/*  831 */       int jWidth = blockWidth(jBlock);
/*  832 */       double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  833 */       int available = outBlock.length - outIndex;
/*  834 */       if (jWidth > available) {
/*  835 */         System.arraycopy(block, iRow * jWidth, outBlock, outIndex, available);
/*  836 */         outBlock = out.blocks[++outBlockIndex];
/*  837 */         System.arraycopy(block, iRow * jWidth, outBlock, 0, jWidth - available);
/*  838 */         outIndex = jWidth - available;
/*      */       } else {
/*  840 */         System.arraycopy(block, iRow * jWidth, outBlock, outIndex, jWidth);
/*  841 */         outIndex += jWidth;
/*      */       } 
/*      */     } 
/*  845 */     return out;
/*      */   }
/*      */   
/*      */   public void setRowMatrix(int row, RealMatrix matrix) {
/*      */     try {
/*  852 */       setRowMatrix(row, (BlockRealMatrix)matrix);
/*  853 */     } catch (ClassCastException cce) {
/*  854 */       super.setRowMatrix(row, matrix);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setRowMatrix(int row, BlockRealMatrix matrix) {
/*  871 */     MatrixUtils.checkRowIndex(this, row);
/*  872 */     int nCols = getColumnDimension();
/*  873 */     if (matrix.getRowDimension() != 1 || matrix.getColumnDimension() != nCols)
/*  875 */       throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols); 
/*  881 */     int iBlock = row / 52;
/*  882 */     int iRow = row - iBlock * 52;
/*  883 */     int mBlockIndex = 0;
/*  884 */     int mIndex = 0;
/*  885 */     double[] mBlock = matrix.blocks[mBlockIndex];
/*  886 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/*  887 */       int jWidth = blockWidth(jBlock);
/*  888 */       double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  889 */       int available = mBlock.length - mIndex;
/*  890 */       if (jWidth > available) {
/*  891 */         System.arraycopy(mBlock, mIndex, block, iRow * jWidth, available);
/*  892 */         mBlock = matrix.blocks[++mBlockIndex];
/*  893 */         System.arraycopy(mBlock, 0, block, iRow * jWidth, jWidth - available);
/*  894 */         mIndex = jWidth - available;
/*      */       } else {
/*  896 */         System.arraycopy(mBlock, mIndex, block, iRow * jWidth, jWidth);
/*  897 */         mIndex += jWidth;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public BlockRealMatrix getColumnMatrix(int column) {
/*  905 */     MatrixUtils.checkColumnIndex(this, column);
/*  906 */     BlockRealMatrix out = new BlockRealMatrix(this.rows, 1);
/*  909 */     int jBlock = column / 52;
/*  910 */     int jColumn = column - jBlock * 52;
/*  911 */     int jWidth = blockWidth(jBlock);
/*  912 */     int outBlockIndex = 0;
/*  913 */     int outIndex = 0;
/*  914 */     double[] outBlock = out.blocks[outBlockIndex];
/*  915 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/*  916 */       int iHeight = blockHeight(iBlock);
/*  917 */       double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  918 */       for (int i = 0; i < iHeight; i++) {
/*  919 */         if (outIndex >= outBlock.length) {
/*  920 */           outBlock = out.blocks[++outBlockIndex];
/*  921 */           outIndex = 0;
/*      */         } 
/*  923 */         outBlock[outIndex++] = block[i * jWidth + jColumn];
/*      */       } 
/*      */     } 
/*  927 */     return out;
/*      */   }
/*      */   
/*      */   public void setColumnMatrix(int column, RealMatrix matrix) {
/*      */     try {
/*  934 */       setColumnMatrix(column, (BlockRealMatrix)matrix);
/*  935 */     } catch (ClassCastException cce) {
/*  936 */       super.setColumnMatrix(column, matrix);
/*      */     } 
/*      */   }
/*      */   
/*      */   void setColumnMatrix(int column, BlockRealMatrix matrix) {
/*  953 */     MatrixUtils.checkColumnIndex(this, column);
/*  954 */     int nRows = getRowDimension();
/*  955 */     if (matrix.getRowDimension() != nRows || matrix.getColumnDimension() != 1)
/*  957 */       throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1); 
/*  963 */     int jBlock = column / 52;
/*  964 */     int jColumn = column - jBlock * 52;
/*  965 */     int jWidth = blockWidth(jBlock);
/*  966 */     int mBlockIndex = 0;
/*  967 */     int mIndex = 0;
/*  968 */     double[] mBlock = matrix.blocks[mBlockIndex];
/*  969 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/*  970 */       int iHeight = blockHeight(iBlock);
/*  971 */       double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  972 */       for (int i = 0; i < iHeight; i++) {
/*  973 */         if (mIndex >= mBlock.length) {
/*  974 */           mBlock = matrix.blocks[++mBlockIndex];
/*  975 */           mIndex = 0;
/*      */         } 
/*  977 */         block[i * jWidth + jColumn] = mBlock[mIndex++];
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public RealVector getRowVector(int row) {
/*  985 */     MatrixUtils.checkRowIndex(this, row);
/*  986 */     double[] outData = new double[this.columns];
/*  989 */     int iBlock = row / 52;
/*  990 */     int iRow = row - iBlock * 52;
/*  991 */     int outIndex = 0;
/*  992 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/*  993 */       int jWidth = blockWidth(jBlock);
/*  994 */       double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  995 */       System.arraycopy(block, iRow * jWidth, outData, outIndex, jWidth);
/*  996 */       outIndex += jWidth;
/*      */     } 
/*  999 */     return new ArrayRealVector(outData, false);
/*      */   }
/*      */   
/*      */   public void setRowVector(int row, RealVector vector) {
/*      */     try {
/* 1006 */       setRow(row, ((ArrayRealVector)vector).getDataRef());
/* 1007 */     } catch (ClassCastException cce) {
/* 1008 */       super.setRowVector(row, vector);
/*      */     } 
/*      */   }
/*      */   
/*      */   public RealVector getColumnVector(int column) {
/* 1015 */     MatrixUtils.checkColumnIndex(this, column);
/* 1016 */     double[] outData = new double[this.rows];
/* 1019 */     int jBlock = column / 52;
/* 1020 */     int jColumn = column - jBlock * 52;
/* 1021 */     int jWidth = blockWidth(jBlock);
/* 1022 */     int outIndex = 0;
/* 1023 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1024 */       int iHeight = blockHeight(iBlock);
/* 1025 */       double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1026 */       for (int i = 0; i < iHeight; i++)
/* 1027 */         outData[outIndex++] = block[i * jWidth + jColumn]; 
/*      */     } 
/* 1031 */     return new ArrayRealVector(outData, false);
/*      */   }
/*      */   
/*      */   public void setColumnVector(int column, RealVector vector) {
/*      */     try {
/* 1038 */       setColumn(column, ((ArrayRealVector)vector).getDataRef());
/* 1039 */     } catch (ClassCastException cce) {
/* 1040 */       super.setColumnVector(column, vector);
/*      */     } 
/*      */   }
/*      */   
/*      */   public double[] getRow(int row) {
/* 1047 */     MatrixUtils.checkRowIndex(this, row);
/* 1048 */     double[] out = new double[this.columns];
/* 1051 */     int iBlock = row / 52;
/* 1052 */     int iRow = row - iBlock * 52;
/* 1053 */     int outIndex = 0;
/* 1054 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1055 */       int jWidth = blockWidth(jBlock);
/* 1056 */       double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1057 */       System.arraycopy(block, iRow * jWidth, out, outIndex, jWidth);
/* 1058 */       outIndex += jWidth;
/*      */     } 
/* 1061 */     return out;
/*      */   }
/*      */   
/*      */   public void setRow(int row, double[] array) {
/* 1067 */     MatrixUtils.checkRowIndex(this, row);
/* 1068 */     int nCols = getColumnDimension();
/* 1069 */     if (array.length != nCols)
/* 1070 */       throw new MatrixDimensionMismatchException(1, array.length, 1, nCols); 
/* 1074 */     int iBlock = row / 52;
/* 1075 */     int iRow = row - iBlock * 52;
/* 1076 */     int outIndex = 0;
/* 1077 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1078 */       int jWidth = blockWidth(jBlock);
/* 1079 */       double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1080 */       System.arraycopy(array, outIndex, block, iRow * jWidth, jWidth);
/* 1081 */       outIndex += jWidth;
/*      */     } 
/*      */   }
/*      */   
/*      */   public double[] getColumn(int column) {
/* 1088 */     MatrixUtils.checkColumnIndex(this, column);
/* 1089 */     double[] out = new double[this.rows];
/* 1092 */     int jBlock = column / 52;
/* 1093 */     int jColumn = column - jBlock * 52;
/* 1094 */     int jWidth = blockWidth(jBlock);
/* 1095 */     int outIndex = 0;
/* 1096 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1097 */       int iHeight = blockHeight(iBlock);
/* 1098 */       double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1099 */       for (int i = 0; i < iHeight; i++)
/* 1100 */         out[outIndex++] = block[i * jWidth + jColumn]; 
/*      */     } 
/* 1104 */     return out;
/*      */   }
/*      */   
/*      */   public void setColumn(int column, double[] array) {
/* 1110 */     MatrixUtils.checkColumnIndex(this, column);
/* 1111 */     int nRows = getRowDimension();
/* 1112 */     if (array.length != nRows)
/* 1113 */       throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1); 
/* 1117 */     int jBlock = column / 52;
/* 1118 */     int jColumn = column - jBlock * 52;
/* 1119 */     int jWidth = blockWidth(jBlock);
/* 1120 */     int outIndex = 0;
/* 1121 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1122 */       int iHeight = blockHeight(iBlock);
/* 1123 */       double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1124 */       for (int i = 0; i < iHeight; i++)
/* 1125 */         block[i * jWidth + jColumn] = array[outIndex++]; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getEntry(int row, int column) {
/* 1133 */     MatrixUtils.checkMatrixIndex(this, row, column);
/* 1134 */     int iBlock = row / 52;
/* 1135 */     int jBlock = column / 52;
/* 1136 */     int k = (row - iBlock * 52) * blockWidth(jBlock) + column - jBlock * 52;
/* 1138 */     return this.blocks[iBlock * this.blockColumns + jBlock][k];
/*      */   }
/*      */   
/*      */   public void setEntry(int row, int column, double value) {
/* 1144 */     MatrixUtils.checkMatrixIndex(this, row, column);
/* 1145 */     int iBlock = row / 52;
/* 1146 */     int jBlock = column / 52;
/* 1147 */     int k = (row - iBlock * 52) * blockWidth(jBlock) + column - jBlock * 52;
/* 1149 */     this.blocks[iBlock * this.blockColumns + jBlock][k] = value;
/*      */   }
/*      */   
/*      */   public void addToEntry(int row, int column, double increment) {
/* 1155 */     MatrixUtils.checkMatrixIndex(this, row, column);
/* 1156 */     int iBlock = row / 52;
/* 1157 */     int jBlock = column / 52;
/* 1158 */     int k = (row - iBlock * 52) * blockWidth(jBlock) + column - jBlock * 52;
/* 1160 */     this.blocks[iBlock * this.blockColumns + jBlock][k] = this.blocks[iBlock * this.blockColumns + jBlock][k] + increment;
/*      */   }
/*      */   
/*      */   public void multiplyEntry(int row, int column, double factor) {
/* 1166 */     MatrixUtils.checkMatrixIndex(this, row, column);
/* 1167 */     int iBlock = row / 52;
/* 1168 */     int jBlock = column / 52;
/* 1169 */     int k = (row - iBlock * 52) * blockWidth(jBlock) + column - jBlock * 52;
/* 1171 */     this.blocks[iBlock * this.blockColumns + jBlock][k] = this.blocks[iBlock * this.blockColumns + jBlock][k] * factor;
/*      */   }
/*      */   
/*      */   public BlockRealMatrix transpose() {
/* 1177 */     int nRows = getRowDimension();
/* 1178 */     int nCols = getColumnDimension();
/* 1179 */     BlockRealMatrix out = new BlockRealMatrix(nCols, nRows);
/* 1182 */     int blockIndex = 0;
/* 1183 */     for (int iBlock = 0; iBlock < this.blockColumns; iBlock++) {
/* 1184 */       for (int jBlock = 0; jBlock < this.blockRows; jBlock++) {
/* 1186 */         double[] outBlock = out.blocks[blockIndex];
/* 1187 */         double[] tBlock = this.blocks[jBlock * this.blockColumns + iBlock];
/* 1188 */         int pStart = iBlock * 52;
/* 1189 */         int pEnd = FastMath.min(pStart + 52, this.columns);
/* 1190 */         int qStart = jBlock * 52;
/* 1191 */         int qEnd = FastMath.min(qStart + 52, this.rows);
/* 1192 */         int k = 0;
/* 1193 */         for (int p = pStart; p < pEnd; p++) {
/* 1194 */           int lInc = pEnd - pStart;
/* 1195 */           int l = p - pStart;
/* 1196 */           for (int q = qStart; q < qEnd; q++) {
/* 1197 */             outBlock[k] = tBlock[l];
/* 1198 */             k++;
/* 1199 */             l += lInc;
/*      */           } 
/*      */         } 
/* 1203 */         blockIndex++;
/*      */       } 
/*      */     } 
/* 1207 */     return out;
/*      */   }
/*      */   
/*      */   public int getRowDimension() {
/* 1213 */     return this.rows;
/*      */   }
/*      */   
/*      */   public int getColumnDimension() {
/* 1219 */     return this.columns;
/*      */   }
/*      */   
/*      */   public double[] operate(double[] v) {
/* 1225 */     if (v.length != this.columns)
/* 1226 */       throw new DimensionMismatchException(v.length, this.columns); 
/* 1228 */     double[] out = new double[this.rows];
/* 1231 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1232 */       int pStart = iBlock * 52;
/* 1233 */       int pEnd = FastMath.min(pStart + 52, this.rows);
/* 1234 */       for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1235 */         double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1236 */         int qStart = jBlock * 52;
/* 1237 */         int qEnd = FastMath.min(qStart + 52, this.columns);
/* 1238 */         int k = 0;
/* 1239 */         for (int p = pStart; p < pEnd; p++) {
/* 1240 */           double sum = 0.0D;
/* 1241 */           int q = qStart;
/* 1242 */           while (q < qEnd - 3) {
/* 1243 */             sum += block[k] * v[q] + block[k + 1] * v[q + 1] + block[k + 2] * v[q + 2] + block[k + 3] * v[q + 3];
/* 1247 */             k += 4;
/* 1248 */             q += 4;
/*      */           } 
/* 1250 */           while (q < qEnd)
/* 1251 */             sum += block[k++] * v[q++]; 
/* 1253 */           out[p] = out[p] + sum;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1258 */     return out;
/*      */   }
/*      */   
/*      */   public double[] preMultiply(double[] v) {
/* 1264 */     if (v.length != this.rows)
/* 1265 */       throw new DimensionMismatchException(v.length, this.rows); 
/* 1267 */     double[] out = new double[this.columns];
/* 1270 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1271 */       int jWidth = blockWidth(jBlock);
/* 1272 */       int jWidth2 = jWidth + jWidth;
/* 1273 */       int jWidth3 = jWidth2 + jWidth;
/* 1274 */       int jWidth4 = jWidth3 + jWidth;
/* 1275 */       int qStart = jBlock * 52;
/* 1276 */       int qEnd = FastMath.min(qStart + 52, this.columns);
/* 1277 */       for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1278 */         double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1279 */         int pStart = iBlock * 52;
/* 1280 */         int pEnd = FastMath.min(pStart + 52, this.rows);
/* 1281 */         for (int q = qStart; q < qEnd; q++) {
/* 1282 */           int k = q - qStart;
/* 1283 */           double sum = 0.0D;
/* 1284 */           int p = pStart;
/* 1285 */           while (p < pEnd - 3) {
/* 1286 */             sum += block[k] * v[p] + block[k + jWidth] * v[p + 1] + block[k + jWidth2] * v[p + 2] + block[k + jWidth3] * v[p + 3];
/* 1290 */             k += jWidth4;
/* 1291 */             p += 4;
/*      */           } 
/* 1293 */           while (p < pEnd) {
/* 1294 */             sum += block[k] * v[p++];
/* 1295 */             k += jWidth;
/*      */           } 
/* 1297 */           out[q] = out[q] + sum;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1302 */     return out;
/*      */   }
/*      */   
/*      */   public double walkInRowOrder(RealMatrixChangingVisitor visitor) {
/* 1308 */     visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
/* 1309 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1310 */       int pStart = iBlock * 52;
/* 1311 */       int pEnd = FastMath.min(pStart + 52, this.rows);
/* 1312 */       for (int p = pStart; p < pEnd; p++) {
/* 1313 */         for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1314 */           int jWidth = blockWidth(jBlock);
/* 1315 */           int qStart = jBlock * 52;
/* 1316 */           int qEnd = FastMath.min(qStart + 52, this.columns);
/* 1317 */           double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1318 */           int k = (p - pStart) * jWidth;
/* 1319 */           for (int q = qStart; q < qEnd; q++) {
/* 1320 */             block[k] = visitor.visit(p, q, block[k]);
/* 1321 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1326 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public double walkInRowOrder(RealMatrixPreservingVisitor visitor) {
/* 1332 */     visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
/* 1333 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1334 */       int pStart = iBlock * 52;
/* 1335 */       int pEnd = FastMath.min(pStart + 52, this.rows);
/* 1336 */       for (int p = pStart; p < pEnd; p++) {
/* 1337 */         for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1338 */           int jWidth = blockWidth(jBlock);
/* 1339 */           int qStart = jBlock * 52;
/* 1340 */           int qEnd = FastMath.min(qStart + 52, this.columns);
/* 1341 */           double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1342 */           int k = (p - pStart) * jWidth;
/* 1343 */           for (int q = qStart; q < qEnd; q++) {
/* 1344 */             visitor.visit(p, q, block[k]);
/* 1345 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1350 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public double walkInRowOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 1358 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 1359 */     visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
/* 1360 */     for (int iBlock = startRow / 52; iBlock < 1 + endRow / 52; iBlock++) {
/* 1361 */       int p0 = iBlock * 52;
/* 1362 */       int pStart = FastMath.max(startRow, p0);
/* 1363 */       int pEnd = FastMath.min((iBlock + 1) * 52, 1 + endRow);
/* 1364 */       for (int p = pStart; p < pEnd; p++) {
/* 1365 */         for (int jBlock = startColumn / 52; jBlock < 1 + endColumn / 52; jBlock++) {
/* 1366 */           int jWidth = blockWidth(jBlock);
/* 1367 */           int q0 = jBlock * 52;
/* 1368 */           int qStart = FastMath.max(startColumn, q0);
/* 1369 */           int qEnd = FastMath.min((jBlock + 1) * 52, 1 + endColumn);
/* 1370 */           double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1371 */           int k = (p - p0) * jWidth + qStart - q0;
/* 1372 */           for (int q = qStart; q < qEnd; q++) {
/* 1373 */             block[k] = visitor.visit(p, q, block[k]);
/* 1374 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1379 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public double walkInRowOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 1387 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 1388 */     visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
/* 1389 */     for (int iBlock = startRow / 52; iBlock < 1 + endRow / 52; iBlock++) {
/* 1390 */       int p0 = iBlock * 52;
/* 1391 */       int pStart = FastMath.max(startRow, p0);
/* 1392 */       int pEnd = FastMath.min((iBlock + 1) * 52, 1 + endRow);
/* 1393 */       for (int p = pStart; p < pEnd; p++) {
/* 1394 */         for (int jBlock = startColumn / 52; jBlock < 1 + endColumn / 52; jBlock++) {
/* 1395 */           int jWidth = blockWidth(jBlock);
/* 1396 */           int q0 = jBlock * 52;
/* 1397 */           int qStart = FastMath.max(startColumn, q0);
/* 1398 */           int qEnd = FastMath.min((jBlock + 1) * 52, 1 + endColumn);
/* 1399 */           double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1400 */           int k = (p - p0) * jWidth + qStart - q0;
/* 1401 */           for (int q = qStart; q < qEnd; q++) {
/* 1402 */             visitor.visit(p, q, block[k]);
/* 1403 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1408 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor) {
/* 1414 */     visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
/* 1415 */     int blockIndex = 0;
/* 1416 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1417 */       int pStart = iBlock * 52;
/* 1418 */       int pEnd = FastMath.min(pStart + 52, this.rows);
/* 1419 */       for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1420 */         int qStart = jBlock * 52;
/* 1421 */         int qEnd = FastMath.min(qStart + 52, this.columns);
/* 1422 */         double[] block = this.blocks[blockIndex];
/* 1423 */         int k = 0;
/* 1424 */         for (int p = pStart; p < pEnd; p++) {
/* 1425 */           for (int q = qStart; q < qEnd; q++) {
/* 1426 */             block[k] = visitor.visit(p, q, block[k]);
/* 1427 */             k++;
/*      */           } 
/*      */         } 
/* 1430 */         blockIndex++;
/*      */       } 
/*      */     } 
/* 1433 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor) {
/* 1439 */     visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
/* 1440 */     int blockIndex = 0;
/* 1441 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1442 */       int pStart = iBlock * 52;
/* 1443 */       int pEnd = FastMath.min(pStart + 52, this.rows);
/* 1444 */       for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1445 */         int qStart = jBlock * 52;
/* 1446 */         int qEnd = FastMath.min(qStart + 52, this.columns);
/* 1447 */         double[] block = this.blocks[blockIndex];
/* 1448 */         int k = 0;
/* 1449 */         for (int p = pStart; p < pEnd; p++) {
/* 1450 */           for (int q = qStart; q < qEnd; q++) {
/* 1451 */             visitor.visit(p, q, block[k]);
/* 1452 */             k++;
/*      */           } 
/*      */         } 
/* 1455 */         blockIndex++;
/*      */       } 
/*      */     } 
/* 1458 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public double walkInOptimizedOrder(RealMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 1466 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 1467 */     visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
/* 1468 */     for (int iBlock = startRow / 52; iBlock < 1 + endRow / 52; iBlock++) {
/* 1469 */       int p0 = iBlock * 52;
/* 1470 */       int pStart = FastMath.max(startRow, p0);
/* 1471 */       int pEnd = FastMath.min((iBlock + 1) * 52, 1 + endRow);
/* 1472 */       for (int jBlock = startColumn / 52; jBlock < 1 + endColumn / 52; jBlock++) {
/* 1473 */         int jWidth = blockWidth(jBlock);
/* 1474 */         int q0 = jBlock * 52;
/* 1475 */         int qStart = FastMath.max(startColumn, q0);
/* 1476 */         int qEnd = FastMath.min((jBlock + 1) * 52, 1 + endColumn);
/* 1477 */         double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1478 */         for (int p = pStart; p < pEnd; p++) {
/* 1479 */           int k = (p - p0) * jWidth + qStart - q0;
/* 1480 */           for (int q = qStart; q < qEnd; q++) {
/* 1481 */             block[k] = visitor.visit(p, q, block[k]);
/* 1482 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1487 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public double walkInOptimizedOrder(RealMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 1495 */     MatrixUtils.checkSubMatrixIndex(this, startRow, endRow, startColumn, endColumn);
/* 1496 */     visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
/* 1497 */     for (int iBlock = startRow / 52; iBlock < 1 + endRow / 52; iBlock++) {
/* 1498 */       int p0 = iBlock * 52;
/* 1499 */       int pStart = FastMath.max(startRow, p0);
/* 1500 */       int pEnd = FastMath.min((iBlock + 1) * 52, 1 + endRow);
/* 1501 */       for (int jBlock = startColumn / 52; jBlock < 1 + endColumn / 52; jBlock++) {
/* 1502 */         int jWidth = blockWidth(jBlock);
/* 1503 */         int q0 = jBlock * 52;
/* 1504 */         int qStart = FastMath.max(startColumn, q0);
/* 1505 */         int qEnd = FastMath.min((jBlock + 1) * 52, 1 + endColumn);
/* 1506 */         double[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1507 */         for (int p = pStart; p < pEnd; p++) {
/* 1508 */           int k = (p - p0) * jWidth + qStart - q0;
/* 1509 */           for (int q = qStart; q < qEnd; q++) {
/* 1510 */             visitor.visit(p, q, block[k]);
/* 1511 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1516 */     return visitor.end();
/*      */   }
/*      */   
/*      */   private int blockHeight(int blockRow) {
/* 1525 */     return (blockRow == this.blockRows - 1) ? (this.rows - blockRow * 52) : 52;
/*      */   }
/*      */   
/*      */   private int blockWidth(int blockColumn) {
/* 1534 */     return (blockColumn == this.blockColumns - 1) ? (this.columns - blockColumn * 52) : 52;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\BlockRealMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */