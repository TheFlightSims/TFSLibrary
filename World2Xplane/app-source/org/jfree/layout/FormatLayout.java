/*      */ package org.jfree.layout;
/*      */ 
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Insets;
/*      */ import java.awt.LayoutManager;
/*      */ import java.io.Serializable;
/*      */ 
/*      */ public class FormatLayout implements LayoutManager, Serializable {
/*      */   private static final long serialVersionUID = 2866692886323930722L;
/*      */   
/*      */   public static final int C = 1;
/*      */   
/*      */   public static final int LC = 2;
/*      */   
/*      */   public static final int LCB = 3;
/*      */   
/*      */   public static final int LCLC = 4;
/*      */   
/*      */   public static final int LCLCB = 5;
/*      */   
/*      */   public static final int LCBLC = 6;
/*      */   
/*      */   public static final int LCBLCB = 7;
/*      */   
/*      */   private int[] rowFormats;
/*      */   
/*      */   private int rowGap;
/*      */   
/*      */   private int[] columnGaps;
/*      */   
/*      */   private int[] rowHeights;
/*      */   
/*      */   private int totalHeight;
/*      */   
/*      */   private int[] columnWidths;
/*      */   
/*      */   private int totalWidth;
/*      */   
/*      */   private int columns1and2Width;
/*      */   
/*      */   private int columns4and5Width;
/*      */   
/*      */   private int columns1to4Width;
/*      */   
/*      */   private int columns1to5Width;
/*      */   
/*      */   private int columns0to5Width;
/*      */   
/*      */   public FormatLayout(int rowCount, int[] rowFormats) {
/*  180 */     this.rowFormats = rowFormats;
/*  181 */     this.rowGap = 2;
/*  182 */     this.columnGaps = new int[5];
/*  183 */     this.columnGaps[0] = 10;
/*  184 */     this.columnGaps[1] = 5;
/*  185 */     this.columnGaps[2] = 5;
/*  186 */     this.columnGaps[3] = 10;
/*  187 */     this.columnGaps[4] = 5;
/*  190 */     this.rowHeights = new int[rowCount];
/*  191 */     this.columnWidths = new int[6];
/*      */   }
/*      */   
/*      */   public Dimension preferredLayoutSize(Container parent) {
/*  205 */     synchronized (parent.getTreeLock()) {
/*  206 */       Insets insets = parent.getInsets();
/*  207 */       int componentIndex = 0;
/*  208 */       int rowCount = this.rowHeights.length;
/*  209 */       for (int i = 0; i < this.columnWidths.length; i++)
/*  210 */         this.columnWidths[i] = 0; 
/*  212 */       this.columns1and2Width = 0;
/*  213 */       this.columns4and5Width = 0;
/*  214 */       this.columns1to4Width = 0;
/*  215 */       this.columns1to5Width = 0;
/*  216 */       this.columns0to5Width = 0;
/*  218 */       this.totalHeight = 0;
/*  219 */       for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
/*      */         Component c0, c1, c2, c3, c4, c5;
/*  220 */         int format = this.rowFormats[rowIndex % this.rowFormats.length];
/*  222 */         switch (format) {
/*      */           case 1:
/*  224 */             c0 = parent.getComponent(componentIndex);
/*  225 */             updateC(rowIndex, c0.getPreferredSize());
/*  226 */             componentIndex++;
/*      */             break;
/*      */           case 2:
/*  229 */             c0 = parent.getComponent(componentIndex);
/*  230 */             c1 = parent.getComponent(componentIndex + 1);
/*  231 */             updateLC(rowIndex, c0.getPreferredSize(), c1.getPreferredSize());
/*  233 */             componentIndex += 2;
/*      */             break;
/*      */           case 3:
/*  236 */             c0 = parent.getComponent(componentIndex);
/*  237 */             c1 = parent.getComponent(componentIndex + 1);
/*  238 */             c2 = parent.getComponent(componentIndex + 2);
/*  239 */             updateLCB(rowIndex, c0.getPreferredSize(), c1.getPreferredSize(), c2.getPreferredSize());
/*  243 */             componentIndex += 3;
/*      */             break;
/*      */           case 4:
/*  246 */             c0 = parent.getComponent(componentIndex);
/*  247 */             c1 = parent.getComponent(componentIndex + 1);
/*  248 */             c2 = parent.getComponent(componentIndex + 2);
/*  249 */             c3 = parent.getComponent(componentIndex + 3);
/*  250 */             updateLCLC(rowIndex, c0.getPreferredSize(), c1.getPreferredSize(), c2.getPreferredSize(), c3.getPreferredSize());
/*  255 */             componentIndex += 4;
/*      */             break;
/*      */           case 6:
/*  258 */             c0 = parent.getComponent(componentIndex);
/*  259 */             c1 = parent.getComponent(componentIndex + 1);
/*  260 */             c2 = parent.getComponent(componentIndex + 2);
/*  261 */             c3 = parent.getComponent(componentIndex + 3);
/*  262 */             c4 = parent.getComponent(componentIndex + 4);
/*  263 */             updateLCBLC(rowIndex, c0.getPreferredSize(), c1.getPreferredSize(), c2.getPreferredSize(), c3.getPreferredSize(), c4.getPreferredSize());
/*  269 */             componentIndex += 5;
/*      */             break;
/*      */           case 5:
/*  272 */             c0 = parent.getComponent(componentIndex);
/*  273 */             c1 = parent.getComponent(componentIndex + 1);
/*  274 */             c2 = parent.getComponent(componentIndex + 2);
/*  275 */             c3 = parent.getComponent(componentIndex + 3);
/*  276 */             c4 = parent.getComponent(componentIndex + 4);
/*  277 */             updateLCLCB(rowIndex, c0.getPreferredSize(), c1.getPreferredSize(), c2.getPreferredSize(), c3.getPreferredSize(), c4.getPreferredSize());
/*  283 */             componentIndex += 5;
/*      */             break;
/*      */           case 7:
/*  286 */             c0 = parent.getComponent(componentIndex);
/*  287 */             c1 = parent.getComponent(componentIndex + 1);
/*  288 */             c2 = parent.getComponent(componentIndex + 2);
/*  289 */             c3 = parent.getComponent(componentIndex + 3);
/*  290 */             c4 = parent.getComponent(componentIndex + 4);
/*  291 */             c5 = parent.getComponent(componentIndex + 5);
/*  292 */             updateLCBLCB(rowIndex, c0.getPreferredSize(), c1.getPreferredSize(), c2.getPreferredSize(), c3.getPreferredSize(), c4.getPreferredSize(), c5.getPreferredSize());
/*  299 */             componentIndex += 6;
/*      */             break;
/*      */         } 
/*      */       } 
/*  303 */       complete();
/*  304 */       return new Dimension(this.totalWidth + insets.left + insets.right, this.totalHeight + (rowCount - 1) * this.rowGap + insets.top + insets.bottom);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Dimension minimumLayoutSize(Container parent) {
/*  321 */     synchronized (parent.getTreeLock()) {
/*  322 */       Insets insets = parent.getInsets();
/*  323 */       int componentIndex = 0;
/*  324 */       int rowCount = this.rowHeights.length;
/*  325 */       for (int i = 0; i < this.columnWidths.length; i++)
/*  326 */         this.columnWidths[i] = 0; 
/*  328 */       this.columns1and2Width = 0;
/*  329 */       this.columns4and5Width = 0;
/*  330 */       this.columns1to4Width = 0;
/*  331 */       this.columns1to5Width = 0;
/*  332 */       this.columns0to5Width = 0;
/*  333 */       int totalHeight = 0;
/*  334 */       for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
/*      */         Component c0, c1, c2, c3, c4, c5;
/*  336 */         int format = this.rowFormats[rowIndex % this.rowFormats.length];
/*  339 */         switch (format) {
/*      */           case 1:
/*  341 */             c0 = parent.getComponent(componentIndex);
/*  342 */             this.columns0to5Width = Math.max(this.columns0to5Width, (c0.getMinimumSize()).width);
/*  345 */             componentIndex++;
/*      */             break;
/*      */           case 2:
/*  348 */             c0 = parent.getComponent(componentIndex);
/*  349 */             c1 = parent.getComponent(componentIndex + 1);
/*  350 */             updateLC(rowIndex, c0.getMinimumSize(), c1.getMinimumSize());
/*  353 */             componentIndex += 2;
/*      */             break;
/*      */           case 3:
/*  356 */             c0 = parent.getComponent(componentIndex);
/*  357 */             c1 = parent.getComponent(componentIndex + 1);
/*  358 */             c2 = parent.getComponent(componentIndex + 2);
/*  359 */             updateLCB(rowIndex, c0.getMinimumSize(), c1.getMinimumSize(), c2.getMinimumSize());
/*  363 */             componentIndex += 3;
/*      */             break;
/*      */           case 4:
/*  366 */             c0 = parent.getComponent(componentIndex);
/*  367 */             c1 = parent.getComponent(componentIndex + 1);
/*  368 */             c2 = parent.getComponent(componentIndex + 2);
/*  369 */             c3 = parent.getComponent(componentIndex + 3);
/*  370 */             updateLCLC(rowIndex, c0.getMinimumSize(), c1.getMinimumSize(), c2.getMinimumSize(), c3.getMinimumSize());
/*  375 */             componentIndex += 3;
/*      */             break;
/*      */           case 6:
/*  378 */             c0 = parent.getComponent(componentIndex);
/*  379 */             c1 = parent.getComponent(componentIndex + 1);
/*  380 */             c2 = parent.getComponent(componentIndex + 2);
/*  381 */             c3 = parent.getComponent(componentIndex + 3);
/*  382 */             c4 = parent.getComponent(componentIndex + 4);
/*  383 */             updateLCBLC(rowIndex, c0.getMinimumSize(), c1.getMinimumSize(), c2.getMinimumSize(), c3.getMinimumSize(), c4.getMinimumSize());
/*  389 */             componentIndex += 4;
/*      */             break;
/*      */           case 5:
/*  392 */             c0 = parent.getComponent(componentIndex);
/*  393 */             c1 = parent.getComponent(componentIndex + 1);
/*  394 */             c2 = parent.getComponent(componentIndex + 2);
/*  395 */             c3 = parent.getComponent(componentIndex + 3);
/*  396 */             c4 = parent.getComponent(componentIndex + 4);
/*  397 */             updateLCLCB(rowIndex, c0.getMinimumSize(), c1.getMinimumSize(), c2.getMinimumSize(), c3.getMinimumSize(), c4.getMinimumSize());
/*  403 */             componentIndex += 4;
/*      */             break;
/*      */           case 7:
/*  406 */             c0 = parent.getComponent(componentIndex);
/*  407 */             c1 = parent.getComponent(componentIndex + 1);
/*  408 */             c2 = parent.getComponent(componentIndex + 2);
/*  409 */             c3 = parent.getComponent(componentIndex + 3);
/*  410 */             c4 = parent.getComponent(componentIndex + 4);
/*  411 */             c5 = parent.getComponent(componentIndex + 5);
/*  412 */             updateLCBLCB(rowIndex, c0.getMinimumSize(), c1.getMinimumSize(), c2.getMinimumSize(), c3.getMinimumSize(), c4.getMinimumSize(), c5.getMinimumSize());
/*  419 */             componentIndex += 5;
/*      */             break;
/*      */         } 
/*      */       } 
/*  423 */       complete();
/*  424 */       return new Dimension(this.totalWidth + insets.left + insets.right, 0 + (rowCount - 1) * this.rowGap + insets.top + insets.bottom);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void layoutContainer(Container parent) {
/*  438 */     synchronized (parent.getTreeLock()) {
/*  439 */       Insets insets = parent.getInsets();
/*  440 */       int componentIndex = 0;
/*  441 */       int rowCount = this.rowHeights.length;
/*  442 */       for (int i = 0; i < this.columnWidths.length; i++)
/*  443 */         this.columnWidths[i] = 0; 
/*  445 */       this.columns1and2Width = 0;
/*  446 */       this.columns4and5Width = 0;
/*  447 */       this.columns1to4Width = 0;
/*  448 */       this.columns1to5Width = 0;
/*  449 */       this.columns0to5Width = (parent.getBounds()).width - insets.left - insets.right;
/*  452 */       this.totalHeight = 0;
/*  453 */       for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
/*      */         Component c0, c1, c2, c3, c4, c5;
/*  454 */         int format = this.rowFormats[rowIndex % this.rowFormats.length];
/*  456 */         switch (format) {
/*      */           case 1:
/*  458 */             c0 = parent.getComponent(componentIndex);
/*  459 */             updateC(rowIndex, c0.getPreferredSize());
/*  460 */             componentIndex++;
/*      */             break;
/*      */           case 2:
/*  463 */             c0 = parent.getComponent(componentIndex);
/*  464 */             c1 = parent.getComponent(componentIndex + 1);
/*  465 */             updateLC(rowIndex, c0.getPreferredSize(), c1.getPreferredSize());
/*  467 */             componentIndex += 2;
/*      */             break;
/*      */           case 3:
/*  470 */             c0 = parent.getComponent(componentIndex);
/*  471 */             c1 = parent.getComponent(componentIndex + 1);
/*  472 */             c2 = parent.getComponent(componentIndex + 2);
/*  473 */             updateLCB(rowIndex, c0.getPreferredSize(), c1.getPreferredSize(), c2.getPreferredSize());
/*  477 */             componentIndex += 3;
/*      */             break;
/*      */           case 4:
/*  480 */             c0 = parent.getComponent(componentIndex);
/*  481 */             c1 = parent.getComponent(componentIndex + 1);
/*  482 */             c2 = parent.getComponent(componentIndex + 2);
/*  483 */             c3 = parent.getComponent(componentIndex + 3);
/*  484 */             updateLCLC(rowIndex, c0.getPreferredSize(), c1.getPreferredSize(), c2.getPreferredSize(), c3.getPreferredSize());
/*  489 */             componentIndex += 4;
/*      */             break;
/*      */           case 6:
/*  492 */             c0 = parent.getComponent(componentIndex);
/*  493 */             c1 = parent.getComponent(componentIndex + 1);
/*  494 */             c2 = parent.getComponent(componentIndex + 2);
/*  495 */             c3 = parent.getComponent(componentIndex + 3);
/*  496 */             c4 = parent.getComponent(componentIndex + 4);
/*  497 */             updateLCBLC(rowIndex, c0.getPreferredSize(), c1.getPreferredSize(), c2.getPreferredSize(), c3.getPreferredSize(), c4.getPreferredSize());
/*  503 */             componentIndex += 5;
/*      */             break;
/*      */           case 5:
/*  506 */             c0 = parent.getComponent(componentIndex);
/*  507 */             c1 = parent.getComponent(componentIndex + 1);
/*  508 */             c2 = parent.getComponent(componentIndex + 2);
/*  509 */             c3 = parent.getComponent(componentIndex + 3);
/*  510 */             c4 = parent.getComponent(componentIndex + 4);
/*  511 */             updateLCLCB(rowIndex, c0.getPreferredSize(), c1.getPreferredSize(), c2.getPreferredSize(), c3.getPreferredSize(), c4.getPreferredSize());
/*  517 */             componentIndex += 5;
/*      */             break;
/*      */           case 7:
/*  520 */             c0 = parent.getComponent(componentIndex);
/*  521 */             c1 = parent.getComponent(componentIndex + 1);
/*  522 */             c2 = parent.getComponent(componentIndex + 2);
/*  523 */             c3 = parent.getComponent(componentIndex + 3);
/*  524 */             c4 = parent.getComponent(componentIndex + 4);
/*  525 */             c5 = parent.getComponent(componentIndex + 5);
/*  526 */             updateLCBLCB(rowIndex, c0.getPreferredSize(), c1.getPreferredSize(), c2.getPreferredSize(), c3.getPreferredSize(), c4.getPreferredSize(), c5.getPreferredSize());
/*  533 */             componentIndex += 6;
/*      */             break;
/*      */         } 
/*      */       } 
/*  537 */       complete();
/*  539 */       componentIndex = 0;
/*  540 */       int rowY = insets.top;
/*  541 */       int[] rowX = new int[6];
/*  542 */       rowX[0] = insets.left;
/*  543 */       rowX[1] = rowX[0] + this.columnWidths[0] + this.columnGaps[0];
/*  544 */       rowX[2] = rowX[1] + this.columnWidths[1] + this.columnGaps[1];
/*  545 */       rowX[3] = rowX[2] + this.columnWidths[2] + this.columnGaps[2];
/*  546 */       rowX[4] = rowX[3] + this.columnWidths[3] + this.columnGaps[3];
/*  547 */       rowX[5] = rowX[4] + this.columnWidths[4] + this.columnGaps[4];
/*  548 */       int w1to2 = this.columnWidths[1] + this.columnGaps[1] + this.columnWidths[2];
/*  550 */       int w4to5 = this.columnWidths[4] + this.columnGaps[4] + this.columnWidths[5];
/*  552 */       int w1to4 = w1to2 + this.columnGaps[2] + this.columnWidths[3] + this.columnGaps[3] + this.columnWidths[4];
/*  554 */       int w1to5 = w1to4 + this.columnGaps[4] + this.columnWidths[5];
/*  555 */       int w0to5 = w1to5 + this.columnWidths[0] + this.columnGaps[0];
/*  556 */       for (int j = 0; j < rowCount; j++) {
/*      */         Component c0, c1, c2, c3, c4, c5;
/*  557 */         int format = this.rowFormats[j % this.rowFormats.length];
/*  560 */         switch (format) {
/*      */           case 1:
/*  562 */             c0 = parent.getComponent(componentIndex);
/*  563 */             c0.setBounds(rowX[0], rowY, w0to5, (c0.getPreferredSize()).height);
/*  565 */             componentIndex++;
/*      */             break;
/*      */           case 2:
/*  568 */             c0 = parent.getComponent(componentIndex);
/*  569 */             c0.setBounds(rowX[0], rowY + (this.rowHeights[j] - (c0.getPreferredSize()).height) / 2, this.columnWidths[0], (c0.getPreferredSize()).height);
/*  575 */             c1 = parent.getComponent(componentIndex + 1);
/*  576 */             c1.setBounds(rowX[1], rowY + (this.rowHeights[j] - (c1.getPreferredSize()).height) / 2, w1to5, (c1.getPreferredSize()).height);
/*  582 */             componentIndex += 2;
/*      */             break;
/*      */           case 3:
/*  585 */             c0 = parent.getComponent(componentIndex);
/*  586 */             c0.setBounds(rowX[0], rowY + (this.rowHeights[j] - (c0.getPreferredSize()).height) / 2, this.columnWidths[0], (c0.getPreferredSize()).height);
/*  592 */             c1 = parent.getComponent(componentIndex + 1);
/*  593 */             c1.setBounds(rowX[1], rowY + (this.rowHeights[j] - (c1.getPreferredSize()).height) / 2, w1to4, (c1.getPreferredSize()).height);
/*  599 */             c2 = parent.getComponent(componentIndex + 2);
/*  600 */             c2.setBounds(rowX[5], rowY + (this.rowHeights[j] - (c2.getPreferredSize()).height) / 2, this.columnWidths[5], (c2.getPreferredSize()).height);
/*  606 */             componentIndex += 3;
/*      */             break;
/*      */           case 4:
/*  609 */             c0 = parent.getComponent(componentIndex);
/*  610 */             c0.setBounds(rowX[0], rowY + (this.rowHeights[j] - (c0.getPreferredSize()).height) / 2, this.columnWidths[0], (c0.getPreferredSize()).height);
/*  616 */             c1 = parent.getComponent(componentIndex + 1);
/*  617 */             c1.setBounds(rowX[1], rowY + (this.rowHeights[j] - (c1.getPreferredSize()).height) / 2, w1to2, (c1.getPreferredSize()).height);
/*  623 */             c2 = parent.getComponent(componentIndex + 2);
/*  624 */             c2.setBounds(rowX[3], rowY + (this.rowHeights[j] - (c2.getPreferredSize()).height) / 2, this.columnWidths[3], (c2.getPreferredSize()).height);
/*  630 */             c3 = parent.getComponent(componentIndex + 3);
/*  631 */             c3.setBounds(rowX[4], rowY + (this.rowHeights[j] - (c3.getPreferredSize()).height) / 2, w4to5, (c3.getPreferredSize()).height);
/*  637 */             componentIndex += 4;
/*      */             break;
/*      */           case 6:
/*  640 */             c0 = parent.getComponent(componentIndex);
/*  641 */             c0.setBounds(rowX[0], rowY + (this.rowHeights[j] - (c0.getPreferredSize()).height) / 2, this.columnWidths[0], (c0.getPreferredSize()).height);
/*  647 */             c1 = parent.getComponent(componentIndex + 1);
/*  648 */             c1.setBounds(rowX[1], rowY + (this.rowHeights[j] - (c1.getPreferredSize()).height) / 2, this.columnWidths[1], (c1.getPreferredSize()).height);
/*  654 */             c2 = parent.getComponent(componentIndex + 2);
/*  655 */             c2.setBounds(rowX[2], rowY + (this.rowHeights[j] - (c2.getPreferredSize()).height) / 2, this.columnWidths[2], (c2.getPreferredSize()).height);
/*  661 */             c3 = parent.getComponent(componentIndex + 3);
/*  662 */             c3.setBounds(rowX[3], rowY + (this.rowHeights[j] - (c3.getPreferredSize()).height) / 2, this.columnWidths[3], (c3.getPreferredSize()).height);
/*  668 */             c4 = parent.getComponent(componentIndex + 4);
/*  669 */             c4.setBounds(rowX[4], rowY + (this.rowHeights[j] - (c4.getPreferredSize()).height) / 2, w4to5, (c4.getPreferredSize()).height);
/*  675 */             componentIndex += 5;
/*      */             break;
/*      */           case 5:
/*  678 */             c0 = parent.getComponent(componentIndex);
/*  679 */             c0.setBounds(rowX[0], rowY + (this.rowHeights[j] - (c0.getPreferredSize()).height) / 2, this.columnWidths[0], (c0.getPreferredSize()).height);
/*  685 */             c1 = parent.getComponent(componentIndex + 1);
/*  686 */             c1.setBounds(rowX[1], rowY + (this.rowHeights[j] - (c1.getPreferredSize()).height) / 2, w1to2, (c1.getPreferredSize()).height);
/*  692 */             c2 = parent.getComponent(componentIndex + 2);
/*  693 */             c2.setBounds(rowX[3], rowY + (this.rowHeights[j] - (c2.getPreferredSize()).height) / 2, this.columnWidths[3], (c2.getPreferredSize()).height);
/*  699 */             c3 = parent.getComponent(componentIndex + 3);
/*  700 */             c3.setBounds(rowX[4], rowY + (this.rowHeights[j] - (c3.getPreferredSize()).height) / 2, this.columnWidths[4], (c3.getPreferredSize()).height);
/*  706 */             c4 = parent.getComponent(componentIndex + 4);
/*  707 */             c4.setBounds(rowX[5], rowY + (this.rowHeights[j] - (c4.getPreferredSize()).height) / 2, this.columnWidths[5], (c4.getPreferredSize()).height);
/*  713 */             componentIndex += 5;
/*      */             break;
/*      */           case 7:
/*  716 */             c0 = parent.getComponent(componentIndex);
/*  717 */             c0.setBounds(rowX[0], rowY + (this.rowHeights[j] - (c0.getPreferredSize()).height) / 2, this.columnWidths[0], (c0.getPreferredSize()).height);
/*  723 */             c1 = parent.getComponent(componentIndex + 1);
/*  724 */             c1.setBounds(rowX[1], rowY + (this.rowHeights[j] - (c1.getPreferredSize()).height) / 2, this.columnWidths[1], (c1.getPreferredSize()).height);
/*  730 */             c2 = parent.getComponent(componentIndex + 2);
/*  731 */             c2.setBounds(rowX[2], rowY + (this.rowHeights[j] - (c2.getPreferredSize()).height) / 2, this.columnWidths[2], (c2.getPreferredSize()).height);
/*  737 */             c3 = parent.getComponent(componentIndex + 3);
/*  738 */             c3.setBounds(rowX[3], rowY + (this.rowHeights[j] - (c3.getPreferredSize()).height) / 2, this.columnWidths[3], (c3.getPreferredSize()).height);
/*  744 */             c4 = parent.getComponent(componentIndex + 4);
/*  745 */             c4.setBounds(rowX[4], rowY + (this.rowHeights[j] - (c4.getPreferredSize()).height) / 2, this.columnWidths[4], (c4.getPreferredSize()).height);
/*  751 */             c5 = parent.getComponent(componentIndex + 5);
/*  752 */             c5.setBounds(rowX[5], rowY + (this.rowHeights[j] - (c5.getPreferredSize()).height) / 2, this.columnWidths[5], (c5.getPreferredSize()).height);
/*  758 */             componentIndex += 6;
/*      */             break;
/*      */         } 
/*  761 */         rowY = rowY + this.rowHeights[j] + this.rowGap;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateC(int rowIndex, Dimension d0) {
/*  773 */     this.rowHeights[rowIndex] = d0.height;
/*  774 */     this.totalHeight += this.rowHeights[rowIndex];
/*  775 */     this.columns0to5Width = Math.max(this.columns0to5Width, d0.width);
/*      */   }
/*      */   
/*      */   protected void updateLC(int rowIndex, Dimension d0, Dimension d1) {
/*  788 */     this.rowHeights[rowIndex] = Math.max(d0.height, d1.height);
/*  789 */     this.totalHeight += this.rowHeights[rowIndex];
/*  790 */     this.columnWidths[0] = Math.max(this.columnWidths[0], d0.width);
/*  791 */     this.columns1to5Width = Math.max(this.columns1to5Width, d1.width);
/*      */   }
/*      */   
/*      */   protected void updateLCB(int rowIndex, Dimension d0, Dimension d1, Dimension d2) {
/*  807 */     this.rowHeights[rowIndex] = Math.max(d0.height, Math.max(d1.height, d2.height));
/*  809 */     this.totalHeight += this.rowHeights[rowIndex];
/*  810 */     this.columnWidths[0] = Math.max(this.columnWidths[0], d0.width);
/*  811 */     this.columns1to4Width = Math.max(this.columns1to4Width, d1.width);
/*  812 */     this.columnWidths[5] = Math.max(this.columnWidths[5], d2.width);
/*      */   }
/*      */   
/*      */   protected void updateLCLC(int rowIndex, Dimension d0, Dimension d1, Dimension d2, Dimension d3) {
/*  829 */     this.rowHeights[rowIndex] = Math.max(Math.max(d0.height, d1.height), Math.max(d2.height, d3.height));
/*  831 */     this.totalHeight += this.rowHeights[rowIndex];
/*  832 */     this.columnWidths[0] = Math.max(this.columnWidths[0], d0.width);
/*  833 */     this.columns1and2Width = Math.max(this.columns1and2Width, d1.width);
/*  834 */     this.columnWidths[3] = Math.max(this.columnWidths[3], d2.width);
/*  835 */     this.columns4and5Width = Math.max(this.columns4and5Width, d3.width);
/*      */   }
/*      */   
/*      */   protected void updateLCBLC(int rowIndex, Dimension d0, Dimension d1, Dimension d2, Dimension d3, Dimension d4) {
/*  852 */     this.rowHeights[rowIndex] = Math.max(d0.height, Math.max(Math.max(d1.height, d2.height), Math.max(d3.height, d4.height)));
/*  857 */     this.totalHeight += this.rowHeights[rowIndex];
/*  858 */     this.columnWidths[0] = Math.max(this.columnWidths[0], d0.width);
/*  859 */     this.columnWidths[1] = Math.max(this.columnWidths[1], d1.width);
/*  860 */     this.columnWidths[2] = Math.max(this.columnWidths[2], d2.width);
/*  861 */     this.columnWidths[3] = Math.max(this.columnWidths[3], d3.width);
/*  862 */     this.columns4and5Width = Math.max(this.columns4and5Width, d4.width);
/*      */   }
/*      */   
/*      */   protected void updateLCLCB(int rowIndex, Dimension d0, Dimension d1, Dimension d2, Dimension d3, Dimension d4) {
/*  880 */     this.rowHeights[rowIndex] = Math.max(d0.height, Math.max(Math.max(d1.height, d2.height), Math.max(d3.height, d4.height)));
/*  883 */     this.totalHeight += this.rowHeights[rowIndex];
/*  884 */     this.columnWidths[0] = Math.max(this.columnWidths[0], d0.width);
/*  885 */     this.columns1and2Width = Math.max(this.columns1and2Width, d1.width);
/*  886 */     this.columnWidths[3] = Math.max(this.columnWidths[3], d2.width);
/*  887 */     this.columnWidths[4] = Math.max(this.columnWidths[4], d3.width);
/*  888 */     this.columnWidths[5] = Math.max(this.columnWidths[5], d4.width);
/*      */   }
/*      */   
/*      */   protected void updateLCBLCB(int rowIndex, Dimension d0, Dimension d1, Dimension d2, Dimension d3, Dimension d4, Dimension d5) {
/*  909 */     this.rowHeights[rowIndex] = Math.max(Math.max(d0.height, d1.height), Math.max(Math.max(d2.height, d3.height), Math.max(d4.height, d5.height)));
/*  914 */     this.totalHeight += this.rowHeights[rowIndex];
/*  915 */     this.columnWidths[0] = Math.max(this.columnWidths[0], d0.width);
/*  916 */     this.columnWidths[1] = Math.max(this.columnWidths[1], d1.width);
/*  917 */     this.columnWidths[2] = Math.max(this.columnWidths[2], d2.width);
/*  918 */     this.columnWidths[3] = Math.max(this.columnWidths[3], d3.width);
/*  919 */     this.columnWidths[4] = Math.max(this.columnWidths[4], d4.width);
/*  920 */     this.columnWidths[5] = Math.max(this.columnWidths[5], d5.width);
/*      */   }
/*      */   
/*      */   public void complete() {
/*  929 */     this.columnWidths[1] = Math.max(this.columnWidths[1], this.columns1and2Width - this.columnGaps[1] - this.columnWidths[2]);
/*  934 */     this.columnWidths[4] = Math.max(this.columnWidths[4], Math.max(this.columns4and5Width - this.columnGaps[4] - this.columnWidths[5], Math.max(this.columns1to4Width - this.columnGaps[1] - this.columnGaps[2] - this.columnGaps[3] - this.columnWidths[1] - this.columnWidths[2] - this.columnWidths[3], this.columns1to5Width - this.columnGaps[1] - this.columnGaps[2] - this.columnGaps[3] - this.columnWidths[1] - this.columnWidths[2] - this.columnWidths[3] - this.columnGaps[4])));
/*  952 */     int leftWidth = this.columnWidths[0] + this.columnGaps[0] + this.columnWidths[1] + this.columnGaps[1] + this.columnWidths[2];
/*  956 */     int rightWidth = this.columnWidths[3] + this.columnGaps[3] + this.columnWidths[4] + this.columnGaps[4] + this.columnWidths[5];
/*  960 */     if (splitLayout())
/*  961 */       if (leftWidth > rightWidth) {
/*  962 */         int mismatch = leftWidth - rightWidth;
/*  963 */         this.columnWidths[4] = this.columnWidths[4] + mismatch;
/*  964 */         rightWidth += mismatch;
/*      */       } else {
/*  967 */         int mismatch = rightWidth - leftWidth;
/*  968 */         this.columnWidths[1] = this.columnWidths[1] + mismatch;
/*  969 */         leftWidth += mismatch;
/*      */       }  
/*  973 */     this.totalWidth = leftWidth + this.columnGaps[2] + rightWidth;
/*  975 */     if (this.columns0to5Width > this.totalWidth) {
/*  976 */       int spaceToAdd = this.columns0to5Width - this.totalWidth;
/*  977 */       if (splitLayout()) {
/*  978 */         int halfSpaceToAdd = spaceToAdd / 2;
/*  979 */         this.columnWidths[1] = this.columnWidths[1] + halfSpaceToAdd;
/*  980 */         this.columnWidths[4] = this.columnWidths[4] + spaceToAdd - halfSpaceToAdd;
/*  982 */         this.totalWidth += spaceToAdd;
/*      */       } else {
/*  985 */         this.columnWidths[1] = this.columnWidths[1] + spaceToAdd;
/*  986 */         this.totalWidth += spaceToAdd;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean splitLayout() {
/*  999 */     for (int i = 0; i < this.rowFormats.length; i++) {
/* 1000 */       if (this.rowFormats[i] > 3)
/* 1001 */         return true; 
/*      */     } 
/* 1004 */     return false;
/*      */   }
/*      */   
/*      */   public void addLayoutComponent(Component comp) {}
/*      */   
/*      */   public void removeLayoutComponent(Component comp) {}
/*      */   
/*      */   public void addLayoutComponent(String name, Component comp) {}
/*      */   
/*      */   public void removeLayoutComponent(String name, Component comp) {}
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\layout\FormatLayout.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */