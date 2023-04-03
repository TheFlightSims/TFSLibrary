/*      */ package org.apache.commons.math3.linear;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import org.apache.commons.math3.Field;
/*      */ import org.apache.commons.math3.FieldElement;
/*      */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*      */ import org.apache.commons.math3.exception.NoDataException;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ import org.apache.commons.math3.util.MathUtils;
/*      */ 
/*      */ public class BlockFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> implements Serializable {
/*      */   public static final int BLOCK_SIZE = 36;
/*      */   
/*      */   private static final long serialVersionUID = -4602336630143123183L;
/*      */   
/*      */   private final T[][] blocks;
/*      */   
/*      */   private final int rows;
/*      */   
/*      */   private final int columns;
/*      */   
/*      */   private final int blockRows;
/*      */   
/*      */   private final int blockColumns;
/*      */   
/*      */   public BlockFieldMatrix(Field<T> field, int rows, int columns) {
/*   96 */     super(field, rows, columns);
/*   97 */     this.rows = rows;
/*   98 */     this.columns = columns;
/*  101 */     this.blockRows = (rows + 36 - 1) / 36;
/*  102 */     this.blockColumns = (columns + 36 - 1) / 36;
/*  105 */     this.blocks = createBlocksLayout(field, rows, columns);
/*      */   }
/*      */   
/*      */   public BlockFieldMatrix(T[][] rawData) {
/*  122 */     this(rawData.length, (rawData[0]).length, toBlocksLayout(rawData), false);
/*      */   }
/*      */   
/*      */   public BlockFieldMatrix(int rows, int columns, T[][] blockData, boolean copyArray) {
/*  142 */     super(extractField(blockData), rows, columns);
/*  143 */     this.rows = rows;
/*  144 */     this.columns = columns;
/*  147 */     this.blockRows = (rows + 36 - 1) / 36;
/*  148 */     this.blockColumns = (columns + 36 - 1) / 36;
/*  150 */     if (copyArray) {
/*  152 */       this.blocks = buildArray(getField(), this.blockRows * this.blockColumns, -1);
/*      */     } else {
/*  155 */       this.blocks = blockData;
/*      */     } 
/*  158 */     int index = 0;
/*  159 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/*  160 */       int iHeight = blockHeight(iBlock);
/*  161 */       for (int jBlock = 0; jBlock < this.blockColumns; jBlock++, index++) {
/*  162 */         if ((blockData[index]).length != iHeight * blockWidth(jBlock))
/*  163 */           throw new DimensionMismatchException((blockData[index]).length, iHeight * blockWidth(jBlock)); 
/*  166 */         if (copyArray)
/*  167 */           this.blocks[index] = (T[])blockData[index].clone(); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static <T extends FieldElement<T>> T[][] toBlocksLayout(T[][] rawData) {
/*  199 */     int rows = rawData.length;
/*  200 */     int columns = (rawData[0]).length;
/*  201 */     int blockRows = (rows + 36 - 1) / 36;
/*  202 */     int blockColumns = (columns + 36 - 1) / 36;
/*  205 */     for (int i = 0; i < rawData.length; i++) {
/*  206 */       int length = (rawData[i]).length;
/*  207 */       if (length != columns)
/*  208 */         throw new DimensionMismatchException(columns, length); 
/*      */     } 
/*  213 */     Field<T> field = extractField(rawData);
/*  214 */     T[][] blocks = buildArray(field, blockRows * blockColumns, -1);
/*  215 */     int blockIndex = 0;
/*  216 */     for (int iBlock = 0; iBlock < blockRows; iBlock++) {
/*  217 */       int pStart = iBlock * 36;
/*  218 */       int pEnd = FastMath.min(pStart + 36, rows);
/*  219 */       int iHeight = pEnd - pStart;
/*  220 */       for (int jBlock = 0; jBlock < blockColumns; jBlock++) {
/*  221 */         int qStart = jBlock * 36;
/*  222 */         int qEnd = FastMath.min(qStart + 36, columns);
/*  223 */         int jWidth = qEnd - qStart;
/*  226 */         T[] block = buildArray(field, iHeight * jWidth);
/*  227 */         blocks[blockIndex] = block;
/*  230 */         int index = 0;
/*  231 */         for (int p = pStart; p < pEnd; p++) {
/*  232 */           System.arraycopy(rawData[p], qStart, block, index, jWidth);
/*  233 */           index += jWidth;
/*      */         } 
/*  236 */         blockIndex++;
/*      */       } 
/*      */     } 
/*  240 */     return blocks;
/*      */   }
/*      */   
/*      */   public static <T extends FieldElement<T>> T[][] createBlocksLayout(Field<T> field, int rows, int columns) {
/*  260 */     int blockRows = (rows + 36 - 1) / 36;
/*  261 */     int blockColumns = (columns + 36 - 1) / 36;
/*  263 */     T[][] blocks = buildArray(field, blockRows * blockColumns, -1);
/*  264 */     int blockIndex = 0;
/*  265 */     for (int iBlock = 0; iBlock < blockRows; iBlock++) {
/*  266 */       int pStart = iBlock * 36;
/*  267 */       int pEnd = FastMath.min(pStart + 36, rows);
/*  268 */       int iHeight = pEnd - pStart;
/*  269 */       for (int jBlock = 0; jBlock < blockColumns; jBlock++) {
/*  270 */         int qStart = jBlock * 36;
/*  271 */         int qEnd = FastMath.min(qStart + 36, columns);
/*  272 */         int jWidth = qEnd - qStart;
/*  273 */         blocks[blockIndex] = buildArray(field, iHeight * jWidth);
/*  274 */         blockIndex++;
/*      */       } 
/*      */     } 
/*  278 */     return blocks;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> createMatrix(int rowDimension, int columnDimension) {
/*  284 */     return new BlockFieldMatrix(getField(), rowDimension, columnDimension);
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> copy() {
/*  292 */     BlockFieldMatrix<T> copied = new BlockFieldMatrix(getField(), this.rows, this.columns);
/*  295 */     for (int i = 0; i < this.blocks.length; i++)
/*  296 */       System.arraycopy(this.blocks[i], 0, copied.blocks[i], 0, (this.blocks[i]).length); 
/*  299 */     return copied;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> add(FieldMatrix<T> m) {
/*      */     try {
/*  306 */       return add((BlockFieldMatrix<T>)m);
/*  307 */     } catch (ClassCastException cce) {
/*  310 */       checkAdditionCompatible(m);
/*  312 */       BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), this.rows, this.columns);
/*  315 */       int blockIndex = 0;
/*  316 */       for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
/*  317 */         for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
/*  320 */           T[] outBlock = out.blocks[blockIndex];
/*  321 */           T[] tBlock = this.blocks[blockIndex];
/*  322 */           int pStart = iBlock * 36;
/*  323 */           int pEnd = FastMath.min(pStart + 36, this.rows);
/*  324 */           int qStart = jBlock * 36;
/*  325 */           int qEnd = FastMath.min(qStart + 36, this.columns);
/*  326 */           int k = 0;
/*  327 */           for (int p = pStart; p < pEnd; p++) {
/*  328 */             for (int q = qStart; q < qEnd; q++) {
/*  329 */               outBlock[k] = (T)tBlock[k].add(m.getEntry(p, q));
/*  330 */               k++;
/*      */             } 
/*      */           } 
/*  335 */           blockIndex++;
/*      */         } 
/*      */       } 
/*  340 */       return out;
/*      */     } 
/*      */   }
/*      */   
/*      */   public BlockFieldMatrix<T> add(BlockFieldMatrix<T> m) {
/*  354 */     checkAdditionCompatible(m);
/*  356 */     BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), this.rows, this.columns);
/*  359 */     for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
/*  360 */       T[] outBlock = out.blocks[blockIndex];
/*  361 */       T[] tBlock = this.blocks[blockIndex];
/*  362 */       T[] mBlock = m.blocks[blockIndex];
/*  363 */       for (int k = 0; k < outBlock.length; k++)
/*  364 */         outBlock[k] = (T)tBlock[k].add(mBlock[k]); 
/*      */     } 
/*  368 */     return out;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> subtract(FieldMatrix<T> m) {
/*      */     try {
/*  375 */       return subtract((BlockFieldMatrix<T>)m);
/*  376 */     } catch (ClassCastException cce) {
/*  379 */       checkSubtractionCompatible(m);
/*  381 */       BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), this.rows, this.columns);
/*  384 */       int blockIndex = 0;
/*  385 */       for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
/*  386 */         for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
/*  389 */           T[] outBlock = out.blocks[blockIndex];
/*  390 */           T[] tBlock = this.blocks[blockIndex];
/*  391 */           int pStart = iBlock * 36;
/*  392 */           int pEnd = FastMath.min(pStart + 36, this.rows);
/*  393 */           int qStart = jBlock * 36;
/*  394 */           int qEnd = FastMath.min(qStart + 36, this.columns);
/*  395 */           int k = 0;
/*  396 */           for (int p = pStart; p < pEnd; p++) {
/*  397 */             for (int q = qStart; q < qEnd; q++) {
/*  398 */               outBlock[k] = (T)tBlock[k].subtract(m.getEntry(p, q));
/*  399 */               k++;
/*      */             } 
/*      */           } 
/*  404 */           blockIndex++;
/*      */         } 
/*      */       } 
/*  409 */       return out;
/*      */     } 
/*      */   }
/*      */   
/*      */   public BlockFieldMatrix<T> subtract(BlockFieldMatrix<T> m) {
/*  422 */     checkSubtractionCompatible(m);
/*  424 */     BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), this.rows, this.columns);
/*  427 */     for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
/*  428 */       T[] outBlock = out.blocks[blockIndex];
/*  429 */       T[] tBlock = this.blocks[blockIndex];
/*  430 */       T[] mBlock = m.blocks[blockIndex];
/*  431 */       for (int k = 0; k < outBlock.length; k++)
/*  432 */         outBlock[k] = (T)tBlock[k].subtract(mBlock[k]); 
/*      */     } 
/*  436 */     return out;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> scalarAdd(T d) {
/*  442 */     BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), this.rows, this.columns);
/*  445 */     for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
/*  446 */       T[] outBlock = out.blocks[blockIndex];
/*  447 */       T[] tBlock = this.blocks[blockIndex];
/*  448 */       for (int k = 0; k < outBlock.length; k++)
/*  449 */         outBlock[k] = (T)tBlock[k].add(d); 
/*      */     } 
/*  453 */     return out;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> scalarMultiply(T d) {
/*  460 */     BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), this.rows, this.columns);
/*  463 */     for (int blockIndex = 0; blockIndex < out.blocks.length; blockIndex++) {
/*  464 */       T[] outBlock = out.blocks[blockIndex];
/*  465 */       T[] tBlock = this.blocks[blockIndex];
/*  466 */       for (int k = 0; k < outBlock.length; k++)
/*  467 */         outBlock[k] = (T)tBlock[k].multiply(d); 
/*      */     } 
/*  471 */     return out;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> multiply(FieldMatrix<T> m) {
/*      */     try {
/*  478 */       return multiply((BlockFieldMatrix<T>)m);
/*  479 */     } catch (ClassCastException cce) {
/*  482 */       checkMultiplicationCompatible(m);
/*  484 */       BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), this.rows, m.getColumnDimension());
/*  485 */       FieldElement fieldElement = (FieldElement)getField().getZero();
/*  488 */       int blockIndex = 0;
/*  489 */       for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
/*  491 */         int pStart = iBlock * 36;
/*  492 */         int pEnd = FastMath.min(pStart + 36, this.rows);
/*  494 */         for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
/*  496 */           int qStart = jBlock * 36;
/*  497 */           int qEnd = FastMath.min(qStart + 36, m.getColumnDimension());
/*  500 */           T[] outBlock = out.blocks[blockIndex];
/*  503 */           for (int kBlock = 0; kBlock < this.blockColumns; kBlock++) {
/*  504 */             int kWidth = blockWidth(kBlock);
/*  505 */             T[] tBlock = this.blocks[iBlock * this.blockColumns + kBlock];
/*  506 */             int rStart = kBlock * 36;
/*  507 */             int k = 0;
/*  508 */             for (int p = pStart; p < pEnd; p++) {
/*  509 */               int lStart = (p - pStart) * kWidth;
/*  510 */               int lEnd = lStart + kWidth;
/*  511 */               for (int q = qStart; q < qEnd; q++) {
/*  512 */                 FieldElement fieldElement1 = fieldElement;
/*  513 */                 int r = rStart;
/*  514 */                 for (int l = lStart; l < lEnd; l++) {
/*  515 */                   fieldElement1 = (FieldElement)fieldElement1.add(tBlock[l].multiply(m.getEntry(r, q)));
/*  516 */                   r++;
/*      */                 } 
/*  518 */                 outBlock[k] = (T)outBlock[k].add(fieldElement1);
/*  519 */                 k++;
/*      */               } 
/*      */             } 
/*      */           } 
/*  525 */           blockIndex++;
/*      */         } 
/*      */       } 
/*  530 */       return out;
/*      */     } 
/*      */   }
/*      */   
/*      */   public BlockFieldMatrix<T> multiply(BlockFieldMatrix<T> m) {
/*  545 */     checkMultiplicationCompatible(m);
/*  547 */     BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), this.rows, m.columns);
/*  548 */     FieldElement fieldElement = (FieldElement)getField().getZero();
/*  551 */     int blockIndex = 0;
/*  552 */     for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
/*  554 */       int pStart = iBlock * 36;
/*  555 */       int pEnd = FastMath.min(pStart + 36, this.rows);
/*  557 */       for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
/*  558 */         int jWidth = out.blockWidth(jBlock);
/*  559 */         int jWidth2 = jWidth + jWidth;
/*  560 */         int jWidth3 = jWidth2 + jWidth;
/*  561 */         int jWidth4 = jWidth3 + jWidth;
/*  564 */         T[] outBlock = out.blocks[blockIndex];
/*  567 */         for (int kBlock = 0; kBlock < this.blockColumns; kBlock++) {
/*  568 */           int kWidth = blockWidth(kBlock);
/*  569 */           T[] tBlock = this.blocks[iBlock * this.blockColumns + kBlock];
/*  570 */           T[] mBlock = m.blocks[kBlock * m.blockColumns + jBlock];
/*  571 */           int k = 0;
/*  572 */           for (int p = pStart; p < pEnd; p++) {
/*  573 */             int lStart = (p - pStart) * kWidth;
/*  574 */             int lEnd = lStart + kWidth;
/*  575 */             for (int nStart = 0; nStart < jWidth; nStart++) {
/*  576 */               FieldElement fieldElement1 = fieldElement;
/*  577 */               int l = lStart;
/*  578 */               int n = nStart;
/*  579 */               while (l < lEnd - 3) {
/*  580 */                 fieldElement1 = (FieldElement)((FieldElement)((FieldElement)((FieldElement)fieldElement1.add(tBlock[l].multiply(mBlock[n]))).add(tBlock[l + 1].multiply(mBlock[n + jWidth]))).add(tBlock[l + 2].multiply(mBlock[n + jWidth2]))).add(tBlock[l + 3].multiply(mBlock[n + jWidth3]));
/*  585 */                 l += 4;
/*  586 */                 n += jWidth4;
/*      */               } 
/*  588 */               while (l < lEnd) {
/*  589 */                 fieldElement1 = (FieldElement)fieldElement1.add(tBlock[l++].multiply(mBlock[n]));
/*  590 */                 n += jWidth;
/*      */               } 
/*  592 */               outBlock[k] = (T)outBlock[k].add(fieldElement1);
/*  593 */               k++;
/*      */             } 
/*      */           } 
/*      */         } 
/*  599 */         blockIndex++;
/*      */       } 
/*      */     } 
/*  603 */     return out;
/*      */   }
/*      */   
/*      */   public T[][] getData() {
/*  610 */     T[][] data = buildArray(getField(), getRowDimension(), getColumnDimension());
/*  611 */     int lastColumns = this.columns - (this.blockColumns - 1) * 36;
/*  613 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/*  614 */       int pStart = iBlock * 36;
/*  615 */       int pEnd = FastMath.min(pStart + 36, this.rows);
/*  616 */       int regularPos = 0;
/*  617 */       int lastPos = 0;
/*  618 */       for (int p = pStart; p < pEnd; p++) {
/*  619 */         T[] dataP = data[p];
/*  620 */         int blockIndex = iBlock * this.blockColumns;
/*  621 */         int dataPos = 0;
/*  622 */         for (int jBlock = 0; jBlock < this.blockColumns - 1; jBlock++) {
/*  623 */           System.arraycopy(this.blocks[blockIndex++], regularPos, dataP, dataPos, 36);
/*  624 */           dataPos += 36;
/*      */         } 
/*  626 */         System.arraycopy(this.blocks[blockIndex], lastPos, dataP, dataPos, lastColumns);
/*  627 */         regularPos += 36;
/*  628 */         lastPos += lastColumns;
/*      */       } 
/*      */     } 
/*  632 */     return data;
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> getSubMatrix(int startRow, int endRow, int startColumn, int endColumn) {
/*  640 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/*  643 */     BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), endRow - startRow + 1, endColumn - startColumn + 1);
/*  647 */     int blockStartRow = startRow / 36;
/*  648 */     int rowsShift = startRow % 36;
/*  649 */     int blockStartColumn = startColumn / 36;
/*  650 */     int columnsShift = startColumn % 36;
/*  653 */     int pBlock = blockStartRow;
/*  654 */     for (int iBlock = 0; iBlock < out.blockRows; iBlock++) {
/*  655 */       int iHeight = out.blockHeight(iBlock);
/*  656 */       int qBlock = blockStartColumn;
/*  657 */       for (int jBlock = 0; jBlock < out.blockColumns; jBlock++) {
/*  658 */         int jWidth = out.blockWidth(jBlock);
/*  661 */         int outIndex = iBlock * out.blockColumns + jBlock;
/*  662 */         T[] outBlock = out.blocks[outIndex];
/*  663 */         int index = pBlock * this.blockColumns + qBlock;
/*  664 */         int width = blockWidth(qBlock);
/*  666 */         int heightExcess = iHeight + rowsShift - 36;
/*  667 */         int widthExcess = jWidth + columnsShift - 36;
/*  668 */         if (heightExcess > 0) {
/*  670 */           if (widthExcess > 0) {
/*  672 */             int width2 = blockWidth(qBlock + 1);
/*  673 */             copyBlockPart(this.blocks[index], width, rowsShift, 36, columnsShift, 36, outBlock, jWidth, 0, 0);
/*  677 */             copyBlockPart(this.blocks[index + 1], width2, rowsShift, 36, 0, widthExcess, outBlock, jWidth, 0, jWidth - widthExcess);
/*  681 */             copyBlockPart(this.blocks[index + this.blockColumns], width, 0, heightExcess, columnsShift, 36, outBlock, jWidth, iHeight - heightExcess, 0);
/*  685 */             copyBlockPart(this.blocks[index + this.blockColumns + 1], width2, 0, heightExcess, 0, widthExcess, outBlock, jWidth, iHeight - heightExcess, jWidth - widthExcess);
/*      */           } else {
/*  691 */             copyBlockPart(this.blocks[index], width, rowsShift, 36, columnsShift, jWidth + columnsShift, outBlock, jWidth, 0, 0);
/*  695 */             copyBlockPart(this.blocks[index + this.blockColumns], width, 0, heightExcess, columnsShift, jWidth + columnsShift, outBlock, jWidth, iHeight - heightExcess, 0);
/*      */           } 
/*  702 */         } else if (widthExcess > 0) {
/*  704 */           int width2 = blockWidth(qBlock + 1);
/*  705 */           copyBlockPart(this.blocks[index], width, rowsShift, iHeight + rowsShift, columnsShift, 36, outBlock, jWidth, 0, 0);
/*  709 */           copyBlockPart(this.blocks[index + 1], width2, rowsShift, iHeight + rowsShift, 0, widthExcess, outBlock, jWidth, 0, jWidth - widthExcess);
/*      */         } else {
/*  715 */           copyBlockPart(this.blocks[index], width, rowsShift, iHeight + rowsShift, columnsShift, jWidth + columnsShift, outBlock, jWidth, 0, 0);
/*      */         } 
/*  721 */         qBlock++;
/*      */       } 
/*  723 */       pBlock++;
/*      */     } 
/*  726 */     return out;
/*      */   }
/*      */   
/*      */   private void copyBlockPart(T[] srcBlock, int srcWidth, int srcStartRow, int srcEndRow, int srcStartColumn, int srcEndColumn, T[] dstBlock, int dstWidth, int dstStartRow, int dstStartColumn) {
/*  749 */     int length = srcEndColumn - srcStartColumn;
/*  750 */     int srcPos = srcStartRow * srcWidth + srcStartColumn;
/*  751 */     int dstPos = dstStartRow * dstWidth + dstStartColumn;
/*  752 */     for (int srcRow = srcStartRow; srcRow < srcEndRow; srcRow++) {
/*  753 */       System.arraycopy(srcBlock, srcPos, dstBlock, dstPos, length);
/*  754 */       srcPos += srcWidth;
/*  755 */       dstPos += dstWidth;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setSubMatrix(T[][] subMatrix, int row, int column) {
/*  763 */     MathUtils.checkNotNull(subMatrix);
/*  764 */     int refLength = (subMatrix[0]).length;
/*  765 */     if (refLength == 0)
/*  766 */       throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN); 
/*  768 */     int endRow = row + subMatrix.length - 1;
/*  769 */     int endColumn = column + refLength - 1;
/*  770 */     checkSubMatrixIndex(row, endRow, column, endColumn);
/*  771 */     for (T[] subRow : subMatrix) {
/*  772 */       if (subRow.length != refLength)
/*  773 */         throw new DimensionMismatchException(refLength, subRow.length); 
/*      */     } 
/*  778 */     int blockStartRow = row / 36;
/*  779 */     int blockEndRow = (endRow + 36) / 36;
/*  780 */     int blockStartColumn = column / 36;
/*  781 */     int blockEndColumn = (endColumn + 36) / 36;
/*  784 */     for (int iBlock = blockStartRow; iBlock < blockEndRow; iBlock++) {
/*  785 */       int iHeight = blockHeight(iBlock);
/*  786 */       int firstRow = iBlock * 36;
/*  787 */       int iStart = FastMath.max(row, firstRow);
/*  788 */       int iEnd = FastMath.min(endRow + 1, firstRow + iHeight);
/*  790 */       for (int jBlock = blockStartColumn; jBlock < blockEndColumn; jBlock++) {
/*  791 */         int jWidth = blockWidth(jBlock);
/*  792 */         int firstColumn = jBlock * 36;
/*  793 */         int jStart = FastMath.max(column, firstColumn);
/*  794 */         int jEnd = FastMath.min(endColumn + 1, firstColumn + jWidth);
/*  795 */         int jLength = jEnd - jStart;
/*  798 */         T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  799 */         for (int i = iStart; i < iEnd; i++)
/*  800 */           System.arraycopy(subMatrix[i - row], jStart - column, block, (i - firstRow) * jWidth + jStart - firstColumn, jLength); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> getRowMatrix(int row) {
/*  812 */     checkRowIndex(row);
/*  813 */     BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), 1, this.columns);
/*  816 */     int iBlock = row / 36;
/*  817 */     int iRow = row - iBlock * 36;
/*  818 */     int outBlockIndex = 0;
/*  819 */     int outIndex = 0;
/*  820 */     T[] outBlock = out.blocks[outBlockIndex];
/*  821 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/*  822 */       int jWidth = blockWidth(jBlock);
/*  823 */       T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  824 */       int available = outBlock.length - outIndex;
/*  825 */       if (jWidth > available) {
/*  826 */         System.arraycopy(block, iRow * jWidth, outBlock, outIndex, available);
/*  827 */         outBlock = out.blocks[++outBlockIndex];
/*  828 */         System.arraycopy(block, iRow * jWidth, outBlock, 0, jWidth - available);
/*  829 */         outIndex = jWidth - available;
/*      */       } else {
/*  831 */         System.arraycopy(block, iRow * jWidth, outBlock, outIndex, jWidth);
/*  832 */         outIndex += jWidth;
/*      */       } 
/*      */     } 
/*  836 */     return out;
/*      */   }
/*      */   
/*      */   public void setRowMatrix(int row, FieldMatrix<T> matrix) {
/*      */     try {
/*  843 */       setRowMatrix(row, (BlockFieldMatrix<T>)matrix);
/*  844 */     } catch (ClassCastException cce) {
/*  845 */       super.setRowMatrix(row, matrix);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setRowMatrix(int row, BlockFieldMatrix<T> matrix) {
/*  862 */     checkRowIndex(row);
/*  863 */     int nCols = getColumnDimension();
/*  864 */     if (matrix.getRowDimension() != 1 || matrix.getColumnDimension() != nCols)
/*  866 */       throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols); 
/*  872 */     int iBlock = row / 36;
/*  873 */     int iRow = row - iBlock * 36;
/*  874 */     int mBlockIndex = 0;
/*  875 */     int mIndex = 0;
/*  876 */     T[] mBlock = matrix.blocks[mBlockIndex];
/*  877 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/*  878 */       int jWidth = blockWidth(jBlock);
/*  879 */       T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  880 */       int available = mBlock.length - mIndex;
/*  881 */       if (jWidth > available) {
/*  882 */         System.arraycopy(mBlock, mIndex, block, iRow * jWidth, available);
/*  883 */         mBlock = matrix.blocks[++mBlockIndex];
/*  884 */         System.arraycopy(mBlock, 0, block, iRow * jWidth, jWidth - available);
/*  885 */         mIndex = jWidth - available;
/*      */       } else {
/*  887 */         System.arraycopy(mBlock, mIndex, block, iRow * jWidth, jWidth);
/*  888 */         mIndex += jWidth;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> getColumnMatrix(int column) {
/*  896 */     checkColumnIndex(column);
/*  897 */     BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), this.rows, 1);
/*  900 */     int jBlock = column / 36;
/*  901 */     int jColumn = column - jBlock * 36;
/*  902 */     int jWidth = blockWidth(jBlock);
/*  903 */     int outBlockIndex = 0;
/*  904 */     int outIndex = 0;
/*  905 */     T[] outBlock = out.blocks[outBlockIndex];
/*  906 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/*  907 */       int iHeight = blockHeight(iBlock);
/*  908 */       T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  909 */       for (int i = 0; i < iHeight; i++) {
/*  910 */         if (outIndex >= outBlock.length) {
/*  911 */           outBlock = out.blocks[++outBlockIndex];
/*  912 */           outIndex = 0;
/*      */         } 
/*  914 */         outBlock[outIndex++] = block[i * jWidth + jColumn];
/*      */       } 
/*      */     } 
/*  918 */     return out;
/*      */   }
/*      */   
/*      */   public void setColumnMatrix(int column, FieldMatrix<T> matrix) {
/*      */     try {
/*  925 */       setColumnMatrix(column, (BlockFieldMatrix<T>)matrix);
/*  926 */     } catch (ClassCastException cce) {
/*  927 */       super.setColumnMatrix(column, matrix);
/*      */     } 
/*      */   }
/*      */   
/*      */   void setColumnMatrix(int column, BlockFieldMatrix<T> matrix) {
/*  944 */     checkColumnIndex(column);
/*  945 */     int nRows = getRowDimension();
/*  946 */     if (matrix.getRowDimension() != nRows || matrix.getColumnDimension() != 1)
/*  948 */       throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1); 
/*  954 */     int jBlock = column / 36;
/*  955 */     int jColumn = column - jBlock * 36;
/*  956 */     int jWidth = blockWidth(jBlock);
/*  957 */     int mBlockIndex = 0;
/*  958 */     int mIndex = 0;
/*  959 */     T[] mBlock = matrix.blocks[mBlockIndex];
/*  960 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/*  961 */       int iHeight = blockHeight(iBlock);
/*  962 */       T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  963 */       for (int i = 0; i < iHeight; i++) {
/*  964 */         if (mIndex >= mBlock.length) {
/*  965 */           mBlock = matrix.blocks[++mBlockIndex];
/*  966 */           mIndex = 0;
/*      */         } 
/*  968 */         block[i * jWidth + jColumn] = mBlock[mIndex++];
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public FieldVector<T> getRowVector(int row) {
/*  976 */     checkRowIndex(row);
/*  977 */     T[] outData = buildArray(getField(), this.columns);
/*  980 */     int iBlock = row / 36;
/*  981 */     int iRow = row - iBlock * 36;
/*  982 */     int outIndex = 0;
/*  983 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/*  984 */       int jWidth = blockWidth(jBlock);
/*  985 */       T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/*  986 */       System.arraycopy(block, iRow * jWidth, outData, outIndex, jWidth);
/*  987 */       outIndex += jWidth;
/*      */     } 
/*  990 */     return new ArrayFieldVector<T>(getField(), outData, false);
/*      */   }
/*      */   
/*      */   public void setRowVector(int row, FieldVector<T> vector) {
/*      */     try {
/*  997 */       setRow(row, ((ArrayFieldVector<T>)vector).getDataRef());
/*  998 */     } catch (ClassCastException cce) {
/*  999 */       super.setRowVector(row, vector);
/*      */     } 
/*      */   }
/*      */   
/*      */   public FieldVector<T> getColumnVector(int column) {
/* 1006 */     checkColumnIndex(column);
/* 1007 */     T[] outData = buildArray(getField(), this.rows);
/* 1010 */     int jBlock = column / 36;
/* 1011 */     int jColumn = column - jBlock * 36;
/* 1012 */     int jWidth = blockWidth(jBlock);
/* 1013 */     int outIndex = 0;
/* 1014 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1015 */       int iHeight = blockHeight(iBlock);
/* 1016 */       T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1017 */       for (int i = 0; i < iHeight; i++)
/* 1018 */         outData[outIndex++] = block[i * jWidth + jColumn]; 
/*      */     } 
/* 1022 */     return new ArrayFieldVector<T>(getField(), outData, false);
/*      */   }
/*      */   
/*      */   public void setColumnVector(int column, FieldVector<T> vector) {
/*      */     try {
/* 1029 */       setColumn(column, ((ArrayFieldVector<T>)vector).getDataRef());
/* 1030 */     } catch (ClassCastException cce) {
/* 1031 */       super.setColumnVector(column, vector);
/*      */     } 
/*      */   }
/*      */   
/*      */   public T[] getRow(int row) {
/* 1038 */     checkRowIndex(row);
/* 1039 */     T[] out = buildArray(getField(), this.columns);
/* 1042 */     int iBlock = row / 36;
/* 1043 */     int iRow = row - iBlock * 36;
/* 1044 */     int outIndex = 0;
/* 1045 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1046 */       int jWidth = blockWidth(jBlock);
/* 1047 */       T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1048 */       System.arraycopy(block, iRow * jWidth, out, outIndex, jWidth);
/* 1049 */       outIndex += jWidth;
/*      */     } 
/* 1052 */     return out;
/*      */   }
/*      */   
/*      */   public void setRow(int row, T[] array) {
/* 1058 */     checkRowIndex(row);
/* 1059 */     int nCols = getColumnDimension();
/* 1060 */     if (array.length != nCols)
/* 1061 */       throw new MatrixDimensionMismatchException(1, array.length, 1, nCols); 
/* 1065 */     int iBlock = row / 36;
/* 1066 */     int iRow = row - iBlock * 36;
/* 1067 */     int outIndex = 0;
/* 1068 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1069 */       int jWidth = blockWidth(jBlock);
/* 1070 */       T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1071 */       System.arraycopy(array, outIndex, block, iRow * jWidth, jWidth);
/* 1072 */       outIndex += jWidth;
/*      */     } 
/*      */   }
/*      */   
/*      */   public T[] getColumn(int column) {
/* 1079 */     checkColumnIndex(column);
/* 1080 */     T[] out = buildArray(getField(), this.rows);
/* 1083 */     int jBlock = column / 36;
/* 1084 */     int jColumn = column - jBlock * 36;
/* 1085 */     int jWidth = blockWidth(jBlock);
/* 1086 */     int outIndex = 0;
/* 1087 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1088 */       int iHeight = blockHeight(iBlock);
/* 1089 */       T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1090 */       for (int i = 0; i < iHeight; i++)
/* 1091 */         out[outIndex++] = block[i * jWidth + jColumn]; 
/*      */     } 
/* 1095 */     return out;
/*      */   }
/*      */   
/*      */   public void setColumn(int column, T[] array) {
/* 1101 */     checkColumnIndex(column);
/* 1102 */     int nRows = getRowDimension();
/* 1103 */     if (array.length != nRows)
/* 1104 */       throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1); 
/* 1108 */     int jBlock = column / 36;
/* 1109 */     int jColumn = column - jBlock * 36;
/* 1110 */     int jWidth = blockWidth(jBlock);
/* 1111 */     int outIndex = 0;
/* 1112 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1113 */       int iHeight = blockHeight(iBlock);
/* 1114 */       T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1115 */       for (int i = 0; i < iHeight; i++)
/* 1116 */         block[i * jWidth + jColumn] = array[outIndex++]; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public T getEntry(int row, int column) {
/* 1124 */     checkRowIndex(row);
/* 1125 */     checkColumnIndex(column);
/* 1127 */     int iBlock = row / 36;
/* 1128 */     int jBlock = column / 36;
/* 1129 */     int k = (row - iBlock * 36) * blockWidth(jBlock) + column - jBlock * 36;
/* 1132 */     return this.blocks[iBlock * this.blockColumns + jBlock][k];
/*      */   }
/*      */   
/*      */   public void setEntry(int row, int column, T value) {
/* 1138 */     checkRowIndex(row);
/* 1139 */     checkColumnIndex(column);
/* 1141 */     int iBlock = row / 36;
/* 1142 */     int jBlock = column / 36;
/* 1143 */     int k = (row - iBlock * 36) * blockWidth(jBlock) + column - jBlock * 36;
/* 1146 */     this.blocks[iBlock * this.blockColumns + jBlock][k] = value;
/*      */   }
/*      */   
/*      */   public void addToEntry(int row, int column, T increment) {
/* 1152 */     checkRowIndex(row);
/* 1153 */     checkColumnIndex(column);
/* 1155 */     int iBlock = row / 36;
/* 1156 */     int jBlock = column / 36;
/* 1157 */     int k = (row - iBlock * 36) * blockWidth(jBlock) + column - jBlock * 36;
/* 1159 */     T[] blockIJ = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1161 */     blockIJ[k] = (T)blockIJ[k].add(increment);
/*      */   }
/*      */   
/*      */   public void multiplyEntry(int row, int column, T factor) {
/* 1167 */     checkRowIndex(row);
/* 1168 */     checkColumnIndex(column);
/* 1170 */     int iBlock = row / 36;
/* 1171 */     int jBlock = column / 36;
/* 1172 */     int k = (row - iBlock * 36) * blockWidth(jBlock) + column - jBlock * 36;
/* 1174 */     T[] blockIJ = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1176 */     blockIJ[k] = (T)blockIJ[k].multiply(factor);
/*      */   }
/*      */   
/*      */   public FieldMatrix<T> transpose() {
/* 1182 */     int nRows = getRowDimension();
/* 1183 */     int nCols = getColumnDimension();
/* 1184 */     BlockFieldMatrix<T> out = new BlockFieldMatrix(getField(), nCols, nRows);
/* 1187 */     int blockIndex = 0;
/* 1188 */     for (int iBlock = 0; iBlock < this.blockColumns; iBlock++) {
/* 1189 */       for (int jBlock = 0; jBlock < this.blockRows; jBlock++) {
/* 1192 */         T[] outBlock = out.blocks[blockIndex];
/* 1193 */         T[] tBlock = this.blocks[jBlock * this.blockColumns + iBlock];
/* 1194 */         int pStart = iBlock * 36;
/* 1195 */         int pEnd = FastMath.min(pStart + 36, this.columns);
/* 1196 */         int qStart = jBlock * 36;
/* 1197 */         int qEnd = FastMath.min(qStart + 36, this.rows);
/* 1198 */         int k = 0;
/* 1199 */         for (int p = pStart; p < pEnd; p++) {
/* 1200 */           int lInc = pEnd - pStart;
/* 1201 */           int l = p - pStart;
/* 1202 */           for (int q = qStart; q < qEnd; q++) {
/* 1203 */             outBlock[k] = tBlock[l];
/* 1204 */             k++;
/* 1205 */             l += lInc;
/*      */           } 
/*      */         } 
/* 1210 */         blockIndex++;
/*      */       } 
/*      */     } 
/* 1215 */     return out;
/*      */   }
/*      */   
/*      */   public int getRowDimension() {
/* 1221 */     return this.rows;
/*      */   }
/*      */   
/*      */   public int getColumnDimension() {
/* 1227 */     return this.columns;
/*      */   }
/*      */   
/*      */   public T[] operate(T[] v) {
/* 1233 */     if (v.length != this.columns)
/* 1234 */       throw new DimensionMismatchException(v.length, this.columns); 
/* 1236 */     T[] out = buildArray(getField(), this.rows);
/* 1237 */     FieldElement fieldElement = (FieldElement)getField().getZero();
/* 1240 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1241 */       int pStart = iBlock * 36;
/* 1242 */       int pEnd = FastMath.min(pStart + 36, this.rows);
/* 1243 */       for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1244 */         T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1245 */         int qStart = jBlock * 36;
/* 1246 */         int qEnd = FastMath.min(qStart + 36, this.columns);
/* 1247 */         int k = 0;
/* 1248 */         for (int p = pStart; p < pEnd; p++) {
/* 1249 */           FieldElement fieldElement1 = fieldElement;
/* 1250 */           int q = qStart;
/* 1251 */           while (q < qEnd - 3) {
/* 1252 */             fieldElement1 = (FieldElement)((FieldElement)((FieldElement)((FieldElement)fieldElement1.add(block[k].multiply(v[q]))).add(block[k + 1].multiply(v[q + 1]))).add(block[k + 2].multiply(v[q + 2]))).add(block[k + 3].multiply(v[q + 3]));
/* 1257 */             k += 4;
/* 1258 */             q += 4;
/*      */           } 
/* 1260 */           while (q < qEnd)
/* 1261 */             fieldElement1 = (FieldElement)fieldElement1.add(block[k++].multiply(v[q++])); 
/* 1263 */           out[p] = (T)out[p].add(fieldElement1);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1268 */     return out;
/*      */   }
/*      */   
/*      */   public T[] preMultiply(T[] v) {
/* 1275 */     if (v.length != this.rows)
/* 1276 */       throw new DimensionMismatchException(v.length, this.rows); 
/* 1278 */     T[] out = buildArray(getField(), this.columns);
/* 1279 */     FieldElement fieldElement = (FieldElement)getField().getZero();
/* 1282 */     for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1283 */       int jWidth = blockWidth(jBlock);
/* 1284 */       int jWidth2 = jWidth + jWidth;
/* 1285 */       int jWidth3 = jWidth2 + jWidth;
/* 1286 */       int jWidth4 = jWidth3 + jWidth;
/* 1287 */       int qStart = jBlock * 36;
/* 1288 */       int qEnd = FastMath.min(qStart + 36, this.columns);
/* 1289 */       for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1290 */         T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1291 */         int pStart = iBlock * 36;
/* 1292 */         int pEnd = FastMath.min(pStart + 36, this.rows);
/* 1293 */         for (int q = qStart; q < qEnd; q++) {
/* 1294 */           int k = q - qStart;
/* 1295 */           FieldElement fieldElement1 = fieldElement;
/* 1296 */           int p = pStart;
/* 1297 */           while (p < pEnd - 3) {
/* 1298 */             fieldElement1 = (FieldElement)((FieldElement)((FieldElement)((FieldElement)fieldElement1.add(block[k].multiply(v[p]))).add(block[k + jWidth].multiply(v[p + 1]))).add(block[k + jWidth2].multiply(v[p + 2]))).add(block[k + jWidth3].multiply(v[p + 3]));
/* 1303 */             k += jWidth4;
/* 1304 */             p += 4;
/*      */           } 
/* 1306 */           while (p < pEnd) {
/* 1307 */             fieldElement1 = (FieldElement)fieldElement1.add(block[k].multiply(v[p++]));
/* 1308 */             k += jWidth;
/*      */           } 
/* 1310 */           out[q] = (T)out[q].add(fieldElement1);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1315 */     return out;
/*      */   }
/*      */   
/*      */   public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor) {
/* 1321 */     visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
/* 1322 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1323 */       int pStart = iBlock * 36;
/* 1324 */       int pEnd = FastMath.min(pStart + 36, this.rows);
/* 1325 */       for (int p = pStart; p < pEnd; p++) {
/* 1326 */         for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1327 */           int jWidth = blockWidth(jBlock);
/* 1328 */           int qStart = jBlock * 36;
/* 1329 */           int qEnd = FastMath.min(qStart + 36, this.columns);
/* 1330 */           T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1331 */           int k = (p - pStart) * jWidth;
/* 1332 */           for (int q = qStart; q < qEnd; q++) {
/* 1333 */             block[k] = visitor.visit(p, q, block[k]);
/* 1334 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1339 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor) {
/* 1345 */     visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
/* 1346 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1347 */       int pStart = iBlock * 36;
/* 1348 */       int pEnd = FastMath.min(pStart + 36, this.rows);
/* 1349 */       for (int p = pStart; p < pEnd; p++) {
/* 1350 */         for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1351 */           int jWidth = blockWidth(jBlock);
/* 1352 */           int qStart = jBlock * 36;
/* 1353 */           int qEnd = FastMath.min(qStart + 36, this.columns);
/* 1354 */           T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1355 */           int k = (p - pStart) * jWidth;
/* 1356 */           for (int q = qStart; q < qEnd; q++) {
/* 1357 */             visitor.visit(p, q, block[k]);
/* 1358 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1363 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInRowOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 1371 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/* 1372 */     visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
/* 1373 */     for (int iBlock = startRow / 36; iBlock < 1 + endRow / 36; iBlock++) {
/* 1374 */       int p0 = iBlock * 36;
/* 1375 */       int pStart = FastMath.max(startRow, p0);
/* 1376 */       int pEnd = FastMath.min((iBlock + 1) * 36, 1 + endRow);
/* 1377 */       for (int p = pStart; p < pEnd; p++) {
/* 1378 */         for (int jBlock = startColumn / 36; jBlock < 1 + endColumn / 36; jBlock++) {
/* 1379 */           int jWidth = blockWidth(jBlock);
/* 1380 */           int q0 = jBlock * 36;
/* 1381 */           int qStart = FastMath.max(startColumn, q0);
/* 1382 */           int qEnd = FastMath.min((jBlock + 1) * 36, 1 + endColumn);
/* 1383 */           T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1384 */           int k = (p - p0) * jWidth + qStart - q0;
/* 1385 */           for (int q = qStart; q < qEnd; q++) {
/* 1386 */             block[k] = visitor.visit(p, q, block[k]);
/* 1387 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1392 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInRowOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 1400 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/* 1401 */     visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
/* 1402 */     for (int iBlock = startRow / 36; iBlock < 1 + endRow / 36; iBlock++) {
/* 1403 */       int p0 = iBlock * 36;
/* 1404 */       int pStart = FastMath.max(startRow, p0);
/* 1405 */       int pEnd = FastMath.min((iBlock + 1) * 36, 1 + endRow);
/* 1406 */       for (int p = pStart; p < pEnd; p++) {
/* 1407 */         for (int jBlock = startColumn / 36; jBlock < 1 + endColumn / 36; jBlock++) {
/* 1408 */           int jWidth = blockWidth(jBlock);
/* 1409 */           int q0 = jBlock * 36;
/* 1410 */           int qStart = FastMath.max(startColumn, q0);
/* 1411 */           int qEnd = FastMath.min((jBlock + 1) * 36, 1 + endColumn);
/* 1412 */           T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1413 */           int k = (p - p0) * jWidth + qStart - q0;
/* 1414 */           for (int q = qStart; q < qEnd; q++) {
/* 1415 */             visitor.visit(p, q, block[k]);
/* 1416 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1421 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> visitor) {
/* 1427 */     visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
/* 1428 */     int blockIndex = 0;
/* 1429 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1430 */       int pStart = iBlock * 36;
/* 1431 */       int pEnd = FastMath.min(pStart + 36, this.rows);
/* 1432 */       for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1433 */         int qStart = jBlock * 36;
/* 1434 */         int qEnd = FastMath.min(qStart + 36, this.columns);
/* 1435 */         T[] block = this.blocks[blockIndex];
/* 1436 */         int k = 0;
/* 1437 */         for (int p = pStart; p < pEnd; p++) {
/* 1438 */           for (int q = qStart; q < qEnd; q++) {
/* 1439 */             block[k] = visitor.visit(p, q, block[k]);
/* 1440 */             k++;
/*      */           } 
/*      */         } 
/* 1443 */         blockIndex++;
/*      */       } 
/*      */     } 
/* 1446 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> visitor) {
/* 1452 */     visitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
/* 1453 */     int blockIndex = 0;
/* 1454 */     for (int iBlock = 0; iBlock < this.blockRows; iBlock++) {
/* 1455 */       int pStart = iBlock * 36;
/* 1456 */       int pEnd = FastMath.min(pStart + 36, this.rows);
/* 1457 */       for (int jBlock = 0; jBlock < this.blockColumns; jBlock++) {
/* 1458 */         int qStart = jBlock * 36;
/* 1459 */         int qEnd = FastMath.min(qStart + 36, this.columns);
/* 1460 */         T[] block = this.blocks[blockIndex];
/* 1461 */         int k = 0;
/* 1462 */         for (int p = pStart; p < pEnd; p++) {
/* 1463 */           for (int q = qStart; q < qEnd; q++) {
/* 1464 */             visitor.visit(p, q, block[k]);
/* 1465 */             k++;
/*      */           } 
/*      */         } 
/* 1468 */         blockIndex++;
/*      */       } 
/*      */     } 
/* 1471 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 1479 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/* 1480 */     visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
/* 1481 */     for (int iBlock = startRow / 36; iBlock < 1 + endRow / 36; iBlock++) {
/* 1482 */       int p0 = iBlock * 36;
/* 1483 */       int pStart = FastMath.max(startRow, p0);
/* 1484 */       int pEnd = FastMath.min((iBlock + 1) * 36, 1 + endRow);
/* 1485 */       for (int jBlock = startColumn / 36; jBlock < 1 + endColumn / 36; jBlock++) {
/* 1486 */         int jWidth = blockWidth(jBlock);
/* 1487 */         int q0 = jBlock * 36;
/* 1488 */         int qStart = FastMath.max(startColumn, q0);
/* 1489 */         int qEnd = FastMath.min((jBlock + 1) * 36, 1 + endColumn);
/* 1490 */         T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1491 */         for (int p = pStart; p < pEnd; p++) {
/* 1492 */           int k = (p - p0) * jWidth + qStart - q0;
/* 1493 */           for (int q = qStart; q < qEnd; q++) {
/* 1494 */             block[k] = visitor.visit(p, q, block[k]);
/* 1495 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1500 */     return visitor.end();
/*      */   }
/*      */   
/*      */   public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> visitor, int startRow, int endRow, int startColumn, int endColumn) {
/* 1508 */     checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
/* 1509 */     visitor.start(this.rows, this.columns, startRow, endRow, startColumn, endColumn);
/* 1510 */     for (int iBlock = startRow / 36; iBlock < 1 + endRow / 36; iBlock++) {
/* 1511 */       int p0 = iBlock * 36;
/* 1512 */       int pStart = FastMath.max(startRow, p0);
/* 1513 */       int pEnd = FastMath.min((iBlock + 1) * 36, 1 + endRow);
/* 1514 */       for (int jBlock = startColumn / 36; jBlock < 1 + endColumn / 36; jBlock++) {
/* 1515 */         int jWidth = blockWidth(jBlock);
/* 1516 */         int q0 = jBlock * 36;
/* 1517 */         int qStart = FastMath.max(startColumn, q0);
/* 1518 */         int qEnd = FastMath.min((jBlock + 1) * 36, 1 + endColumn);
/* 1519 */         T[] block = this.blocks[iBlock * this.blockColumns + jBlock];
/* 1520 */         for (int p = pStart; p < pEnd; p++) {
/* 1521 */           int k = (p - p0) * jWidth + qStart - q0;
/* 1522 */           for (int q = qStart; q < qEnd; q++) {
/* 1523 */             visitor.visit(p, q, block[k]);
/* 1524 */             k++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1529 */     return visitor.end();
/*      */   }
/*      */   
/*      */   private int blockHeight(int blockRow) {
/* 1538 */     return (blockRow == this.blockRows - 1) ? (this.rows - blockRow * 36) : 36;
/*      */   }
/*      */   
/*      */   private int blockWidth(int blockColumn) {
/* 1547 */     return (blockColumn == this.blockColumns - 1) ? (this.columns - blockColumn * 36) : 36;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\BlockFieldMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */