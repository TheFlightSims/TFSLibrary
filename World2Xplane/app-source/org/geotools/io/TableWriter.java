/*      */ package org.geotools.io;
/*      */ 
/*      */ import java.io.FilterWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ import java.util.StringTokenizer;
/*      */ import org.geotools.resources.XArray;
/*      */ import org.geotools.util.Utilities;
/*      */ 
/*      */ public class TableWriter extends FilterWriter {
/*      */   public static final int ALIGN_LEFT = 0;
/*      */   
/*      */   public static final int ALIGN_RIGHT = 2;
/*      */   
/*      */   public static final int ALIGN_CENTER = 1;
/*      */   
/*      */   public static final String SINGLE_VERTICAL_LINE = " │ ";
/*      */   
/*      */   public static final String DOUBLE_VERTICAL_LINE = " ║ ";
/*      */   
/*      */   public static final char SINGLE_HORIZONTAL_LINE = '─';
/*      */   
/*      */   public static final char DOUBLE_HORIZONTAL_LINE = '═';
/*      */   
/*  121 */   private static final char[][] BOX = new char[][] { { 
/*  121 */         '┌', '┬', '┐', '├', '┼', '┤', '└', '┴', '┘', '─', 
/*  121 */         '│' }, { 
/*  121 */         '╓', '╥', '╖', '╟', '╫', '╢', '╙', '╨', '╜', '─', 
/*  121 */         '║' }, { 
/*  121 */         '╒', '╤', '╕', '╞', '╪', '╡', '╘', '╧', '╛', '═', 
/*  121 */         '│' }, { 
/*  121 */         '╔', '╦', '╗', '╠', '╬', '╣', '╚', '╩', '╝', '═', 
/*  121 */         '║' }, { 
/*  121 */         '+', '+', '+', '+', '+', '+', '+', '+', '+', '-', 
/*  121 */         '|' } };
/*      */   
/*      */   private static final char SPACE = ' ';
/*      */   
/*  162 */   private final StringBuilder buffer = new StringBuilder();
/*      */   
/*  169 */   private final List<Cell> cells = new ArrayList<Cell>();
/*      */   
/*  174 */   private int alignment = 0;
/*      */   
/*      */   private int column;
/*      */   
/*      */   private int row;
/*      */   
/*  192 */   private int[] width = new int[0];
/*      */   
/*      */   private final String separator;
/*      */   
/*      */   private final String leftBorder;
/*      */   
/*      */   private final String rightBorder;
/*      */   
/*      */   private boolean multiLinesCells;
/*      */   
/*      */   private final boolean stringOnly;
/*      */   
/*      */   private boolean skipCR;
/*      */   
/*      */   public TableWriter(Writer out) {
/*  240 */     super((out != null) ? out : new StringWriter());
/*  241 */     this.stringOnly = (out == null);
/*  242 */     this.leftBorder = "║ ";
/*  243 */     this.rightBorder = " ║";
/*  244 */     this.separator = " │ ";
/*      */   }
/*      */   
/*      */   public TableWriter(Writer out, int spaces) {
/*  256 */     this(out, Utilities.spaces(spaces));
/*      */   }
/*      */   
/*      */   public TableWriter(Writer out, String separator) {
/*  272 */     super((out != null) ? out : new StringWriter());
/*  273 */     this.stringOnly = (out == null);
/*  274 */     int length = separator.length();
/*  275 */     int lower = 0;
/*  276 */     int upper = length;
/*  277 */     for (; lower < length && Character.isSpaceChar(separator.charAt(lower)); lower++);
/*  278 */     for (; upper > 0 && Character.isSpaceChar(separator.charAt(upper - 1)); upper--);
/*  279 */     this.leftBorder = separator.substring(lower);
/*  280 */     this.rightBorder = separator.substring(0, upper);
/*  281 */     this.separator = separator;
/*      */   }
/*      */   
/*      */   private void writeBorder(Writer out, int horizontalBorder, int verticalBorder, char horizontalChar) throws IOException {
/*      */     String border;
/*  302 */     int boxCount = 0;
/*  303 */     char[][] box = new char[BOX.length][];
/*  304 */     for (int i = 0; i < BOX.length; i++) {
/*  305 */       if (BOX[i][9] == horizontalChar)
/*  306 */         box[boxCount++] = BOX[i]; 
/*      */     } 
/*  314 */     switch (horizontalBorder) {
/*      */       case -1:
/*  315 */         border = this.leftBorder;
/*      */         break;
/*      */       case 1:
/*  316 */         border = this.rightBorder;
/*      */         break;
/*      */       case 0:
/*  317 */         border = this.separator;
/*      */         break;
/*      */       default:
/*  318 */         throw new IllegalArgumentException(String.valueOf(horizontalBorder));
/*      */     } 
/*  320 */     if (verticalBorder < -1 || verticalBorder > 1)
/*  321 */       throw new IllegalArgumentException(String.valueOf(verticalBorder)); 
/*  327 */     int index = horizontalBorder + 1 + (verticalBorder + 1) * 3;
/*  328 */     int borderLength = border.length();
/*  329 */     for (int j = 0; j < borderLength; j++) {
/*  330 */       char c = border.charAt(j);
/*  331 */       if (Character.isSpaceChar(c)) {
/*  332 */         c = horizontalChar;
/*      */       } else {
/*  334 */         for (int k = 0; k < boxCount; k++) {
/*  335 */           if (box[k][10] == c) {
/*  336 */             c = box[k][index];
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*  341 */       out.write(c);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setMultiLinesCells(boolean multiLines) {
/*  362 */     synchronized (this.lock) {
/*  363 */       this.multiLinesCells = multiLines;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isMultiLinesCells() {
/*  373 */     synchronized (this.lock) {
/*  374 */       return this.multiLinesCells;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setColumnAlignment(int column, int alignment) {
/*  387 */     if (alignment != 0 && alignment != 2 && alignment != 1)
/*  391 */       throw new IllegalArgumentException(String.valueOf(alignment)); 
/*  393 */     synchronized (this.lock) {
/*  394 */       int current = 0;
/*  395 */       for (Cell cell : this.cells) {
/*  396 */         if (cell == null || cell.text == null) {
/*  397 */           current = 0;
/*      */           continue;
/*      */         } 
/*  400 */         if (current == column)
/*  401 */           cell.alignment = alignment; 
/*  403 */         current++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setAlignment(int alignment) {
/*  417 */     if (alignment != 0 && alignment != 2 && alignment != 1)
/*  421 */       throw new IllegalArgumentException(String.valueOf(alignment)); 
/*  423 */     synchronized (this.lock) {
/*  424 */       this.alignment = alignment;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getAlignment() {
/*  435 */     synchronized (this.lock) {
/*  436 */       return this.alignment;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getRowCount() {
/*  448 */     int count = this.row;
/*  449 */     if (this.column != 0)
/*  450 */       count++; 
/*  452 */     return count;
/*      */   }
/*      */   
/*      */   public int getColumnCount() {
/*  463 */     return this.width.length;
/*      */   }
/*      */   
/*      */   public void write(int c) {
/*  479 */     synchronized (this.lock) {
/*  480 */       if (!this.multiLinesCells)
/*  481 */         switch (c) {
/*      */           case 9:
/*  483 */             nextColumn();
/*  484 */             this.skipCR = false;
/*      */             return;
/*      */           case 13:
/*  488 */             nextLine();
/*  489 */             this.skipCR = true;
/*      */             return;
/*      */           case 10:
/*  493 */             if (!this.skipCR)
/*  494 */               nextLine(); 
/*  496 */             this.skipCR = false;
/*      */             return;
/*      */         }  
/*  501 */       if (c < 0 || c > 65535)
/*  502 */         throw new IllegalArgumentException(String.valueOf(c)); 
/*  504 */       this.buffer.append((char)c);
/*  505 */       this.skipCR = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void write(String string) {
/*  516 */     write(string, 0, string.length());
/*      */   }
/*      */   
/*      */   public void write(String string, int offset, int length) {
/*  529 */     if (offset < 0 || length < 0 || offset + length > string.length())
/*  530 */       throw new IndexOutOfBoundsException(); 
/*  532 */     if (length == 0)
/*      */       return; 
/*  535 */     synchronized (this.lock) {
/*  536 */       if (this.skipCR && string.charAt(offset) == '\n') {
/*  537 */         offset++;
/*  538 */         length--;
/*      */       } 
/*  540 */       if (!this.multiLinesCells) {
/*  541 */         int upper = offset;
/*  542 */         for (; length != 0; length--) {
/*  543 */           switch (string.charAt(upper++)) {
/*      */             case '\t':
/*  545 */               this.buffer.append(string.substring(offset, upper - 1));
/*  546 */               nextColumn();
/*  547 */               offset = upper;
/*      */               break;
/*      */             case '\r':
/*  551 */               this.buffer.append(string.substring(offset, upper - 1));
/*  552 */               nextLine();
/*  553 */               if (length != 0 && string.charAt(upper) == '\n') {
/*  554 */                 upper++;
/*  555 */                 length--;
/*      */               } 
/*  557 */               offset = upper;
/*      */               break;
/*      */             case '\n':
/*  561 */               this.buffer.append(string.substring(offset, upper - 1));
/*  562 */               nextLine();
/*  563 */               offset = upper;
/*      */               break;
/*      */           } 
/*      */         } 
/*  568 */         length = upper - offset;
/*      */       } 
/*  570 */       this.skipCR = (string.charAt(offset + length - 1) == '\r');
/*  571 */       this.buffer.append(string.substring(offset, offset + length));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void write(char[] cbuf) {
/*  583 */     write(cbuf, 0, cbuf.length);
/*      */   }
/*      */   
/*      */   public void write(char[] cbuf, int offset, int length) {
/*  596 */     if (offset < 0 || length < 0 || offset + length > cbuf.length)
/*  597 */       throw new IndexOutOfBoundsException(); 
/*  599 */     if (length == 0)
/*      */       return; 
/*  602 */     synchronized (this.lock) {
/*  603 */       if (this.skipCR && cbuf[offset] == '\n') {
/*  604 */         offset++;
/*  605 */         length--;
/*      */       } 
/*  607 */       if (!this.multiLinesCells) {
/*  608 */         int upper = offset;
/*  609 */         for (; length != 0; length--) {
/*  610 */           switch (cbuf[upper++]) {
/*      */             case '\t':
/*  612 */               this.buffer.append(cbuf, offset, upper - offset - 1);
/*  613 */               nextColumn();
/*  614 */               offset = upper;
/*      */               break;
/*      */             case '\r':
/*  618 */               this.buffer.append(cbuf, offset, upper - offset - 1);
/*  619 */               nextLine();
/*  620 */               if (length != 0 && cbuf[upper] == '\n') {
/*  621 */                 upper++;
/*  622 */                 length--;
/*      */               } 
/*  624 */               offset = upper;
/*      */               break;
/*      */             case '\n':
/*  628 */               this.buffer.append(cbuf, offset, upper - offset - 1);
/*  629 */               nextLine();
/*  630 */               offset = upper;
/*      */               break;
/*      */           } 
/*      */         } 
/*  635 */         length = upper - offset;
/*      */       } 
/*  637 */       this.skipCR = (cbuf[offset + length - 1] == '\r');
/*  638 */       this.buffer.append(cbuf, offset, length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeHorizontalSeparator() {
/*  646 */     synchronized (this.lock) {
/*  647 */       if (this.column != 0 || this.buffer.length() != 0)
/*  648 */         nextLine(); 
/*  650 */       nextLine('─');
/*      */     } 
/*      */   }
/*      */   
/*      */   public void nextColumn() {
/*  659 */     nextColumn(' ');
/*      */   }
/*      */   
/*      */   public void nextColumn(char fill) {
/*  671 */     synchronized (this.lock) {
/*  672 */       String cellText = this.buffer.toString();
/*  673 */       this.cells.add(new Cell(cellText, this.alignment, fill));
/*  674 */       if (this.column >= this.width.length)
/*  675 */         this.width = XArray.resize(this.width, this.column + 1); 
/*  677 */       int length = 0;
/*  678 */       StringTokenizer tk = new StringTokenizer(cellText, "\r\n");
/*  679 */       while (tk.hasMoreTokens()) {
/*  680 */         int lg = tk.nextToken().length();
/*  681 */         if (lg > length)
/*  682 */           length = lg; 
/*      */       } 
/*  685 */       if (length > this.width[this.column])
/*  686 */         this.width[this.column] = length; 
/*  688 */       this.column++;
/*  689 */       this.buffer.setLength(0);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void nextLine() {
/*  698 */     nextLine(' ');
/*      */   }
/*      */   
/*      */   public void nextLine(char fill) {
/*  714 */     synchronized (this.lock) {
/*  715 */       if (this.buffer.length() != 0)
/*  716 */         nextColumn(fill); 
/*  718 */       assert this.buffer.length() == 0;
/*  719 */       this.cells.add(!Character.isSpaceChar(fill) ? new Cell(null, this.alignment, fill) : null);
/*  720 */       this.column = 0;
/*  721 */       this.row++;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void flush() throws IOException {
/*  733 */     synchronized (this.lock) {
/*  734 */       if (this.buffer.length() != 0) {
/*  735 */         nextLine();
/*  736 */         assert this.buffer.length() == 0;
/*      */       } 
/*  738 */       flushTo(this.out);
/*  739 */       this.row = this.column = 0;
/*  740 */       this.cells.clear();
/*  741 */       if (!(this.out instanceof TableWriter))
/*  746 */         this.out.flush(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void close() throws IOException {
/*  758 */     synchronized (this.lock) {
/*  759 */       flush();
/*  760 */       this.out.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void flushTo(Writer out) throws IOException {
/*  774 */     String columnSeparator = this.separator;
/*  775 */     String lineSeparator = System.getProperty("line.separator", "\n");
/*  776 */     Cell[] currentLine = new Cell[this.width.length];
/*  777 */     int cellCount = this.cells.size();
/*  778 */     for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
/*  786 */       Cell lineFill = null;
/*  787 */       int currentCount = 0;
/*      */       do {
/*  789 */         Cell cell = this.cells.get(cellIndex);
/*  790 */         if (cell == null)
/*      */           break; 
/*  793 */         if (cell.text == null) {
/*  794 */           lineFill = new Cell("", cell.alignment, cell.fill);
/*      */           break;
/*      */         } 
/*  797 */         currentLine[currentCount++] = cell;
/*  799 */       } while (++cellIndex < cellCount);
/*  800 */       Arrays.fill((Object[])currentLine, currentCount, currentLine.length, lineFill);
/*  808 */       while (!isEmpty((Object[])currentLine)) {
/*  809 */         for (int j = 0; j < currentLine.length; j++) {
/*  810 */           boolean isFirstColumn = (j == 0);
/*  811 */           boolean isLastColumn = (j + 1 == currentLine.length);
/*  812 */           Cell cell = currentLine[j];
/*  813 */           int cellWidth = this.width[j];
/*  814 */           if (cell == null) {
/*  815 */             if (isFirstColumn)
/*  816 */               out.write(this.leftBorder); 
/*  818 */             repeat(out, ' ', cellWidth);
/*  819 */             out.write(isLastColumn ? this.rightBorder : columnSeparator);
/*      */           } else {
/*  822 */             String cellText = cell.toString();
/*  823 */             int endCR = cellText.indexOf('\r');
/*  824 */             int endLF = cellText.indexOf('\n');
/*  825 */             int end = (endCR < 0) ? endLF : ((endLF < 0) ? endCR : Math.min(endCR, endLF));
/*  826 */             if (end >= 0) {
/*  833 */               int top = end + 1;
/*  834 */               if (endCR >= 0 && endCR + 1 == endLF)
/*  834 */                 top++; 
/*  835 */               int scan = top;
/*  836 */               int i = cellText.length();
/*  837 */               while (scan < i && Character.isWhitespace(cellText.charAt(scan)))
/*  838 */                 scan++; 
/*  840 */               currentLine[j] = (scan < i) ? cell.substring(top) : null;
/*  841 */               cellText = cellText.substring(0, end);
/*      */             } else {
/*  843 */               currentLine[j] = null;
/*      */             } 
/*  844 */             int textLength = cellText.length();
/*  850 */             if (currentCount == 0) {
/*      */               int verticalBorder;
/*  851 */               assert textLength == 0;
/*  853 */               if (cellIndex == 0) {
/*  853 */                 verticalBorder = -1;
/*  854 */               } else if (cellIndex >= cellCount - 1) {
/*  854 */                 verticalBorder = 1;
/*      */               } else {
/*  855 */                 verticalBorder = 0;
/*      */               } 
/*  856 */               if (isFirstColumn)
/*  857 */                 writeBorder(out, -1, verticalBorder, cell.fill); 
/*  859 */               repeat(out, cell.fill, cellWidth);
/*  860 */               writeBorder(out, isLastColumn ? 1 : 0, verticalBorder, cell.fill);
/*      */             } else {
/*      */               int rightMargin;
/*  868 */               if (isFirstColumn)
/*  869 */                 out.write(this.leftBorder); 
/*  871 */               Writer tabExpander = (cellText.indexOf('\t') >= 0) ? new ExpandedTabWriter(out) : out;
/*  873 */               switch (cell.alignment) {
/*      */                 default:
/*  876 */                   throw new AssertionError(cell.alignment);
/*      */                 case 0:
/*  879 */                   tabExpander.write(cellText);
/*  880 */                   repeat(tabExpander, cell.fill, cellWidth - textLength);
/*      */                   break;
/*      */                 case 2:
/*  884 */                   repeat(tabExpander, cell.fill, cellWidth - textLength);
/*  885 */                   tabExpander.write(cellText);
/*      */                   break;
/*      */                 case 1:
/*  889 */                   rightMargin = (cellWidth - textLength) / 2;
/*  890 */                   repeat(tabExpander, cell.fill, rightMargin);
/*  891 */                   tabExpander.write(cellText);
/*  892 */                   repeat(tabExpander, cell.fill, cellWidth - rightMargin - textLength);
/*      */                   break;
/*      */               } 
/*  896 */               out.write(isLastColumn ? this.rightBorder : columnSeparator);
/*      */             } 
/*      */           } 
/*      */         } 
/*  898 */         out.write(lineSeparator);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static boolean isEmpty(Object[] array) {
/*  907 */     for (int i = array.length; --i >= 0;) {
/*  908 */       if (array[i] != null)
/*  909 */         return false; 
/*      */     } 
/*  912 */     return true;
/*      */   }
/*      */   
/*      */   private static void repeat(Writer out, char car, int count) throws IOException {
/*  923 */     while (--count >= 0)
/*  924 */       out.write(car); 
/*      */   }
/*      */   
/*      */   public String toString() {
/*  933 */     synchronized (this.lock) {
/*      */       StringWriter writer;
/*  935 */       if (this.buffer.length() > 0)
/*  936 */         nextColumn(); 
/*  938 */       int capacity = 2;
/*  939 */       for (int i = 0; i < this.width.length; i++)
/*  940 */         capacity += this.width[i]; 
/*  942 */       capacity *= getRowCount();
/*  944 */       if (this.stringOnly) {
/*  945 */         writer = (StringWriter)this.out;
/*  946 */         StringBuffer buffer = writer.getBuffer();
/*  947 */         buffer.setLength(0);
/*  948 */         buffer.ensureCapacity(capacity);
/*      */       } else {
/*  950 */         writer = new StringWriter(capacity);
/*      */       } 
/*      */       try {
/*  953 */         flushTo(writer);
/*  954 */       } catch (IOException exception) {
/*  956 */         throw new AssertionError(exception);
/*      */       } 
/*  958 */       return writer.toString();
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final class Cell {
/*      */     public final String text;
/*      */     
/*      */     public int alignment;
/*      */     
/*      */     public final char fill;
/*      */     
/*      */     public Cell(String text, int alignment, char fill) {
/*  990 */       this.text = text;
/*  991 */       this.alignment = alignment;
/*  992 */       this.fill = fill;
/*      */     }
/*      */     
/*      */     public Cell substring(int lower) {
/*  999 */       return new Cell(this.text.substring(lower), this.alignment, this.fill);
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1007 */       return this.text;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\io\TableWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */