/*      */ package org.apache.commons.compress.compressors.bzip2;
/*      */ 
/*      */ import java.util.BitSet;
/*      */ 
/*      */ class BlockSort {
/*      */   private static final int QSORT_STACK_SIZE = 1000;
/*      */   
/*      */   private static final int FALLBACK_QSORT_STACK_SIZE = 100;
/*      */   
/*      */   private static final int STACK_SIZE = 1000;
/*      */   
/*      */   private int workDone;
/*      */   
/*      */   private int workLimit;
/*      */   
/*      */   private boolean firstAttempt;
/*      */   
/*  132 */   private final int[] stack_ll = new int[1000];
/*      */   
/*  133 */   private final int[] stack_hh = new int[1000];
/*      */   
/*  134 */   private final int[] stack_dd = new int[1000];
/*      */   
/*  136 */   private final int[] mainSort_runningOrder = new int[256];
/*      */   
/*  137 */   private final int[] mainSort_copy = new int[256];
/*      */   
/*  138 */   private final boolean[] mainSort_bigDone = new boolean[256];
/*      */   
/*  140 */   private final int[] ftab = new int[65537];
/*      */   
/*      */   private final char[] quadrant;
/*      */   
/*      */   private static final int FALLBACK_QSORT_SMALL_THRESH = 10;
/*      */   
/*      */   private int[] eclass;
/*      */   
/*      */   BlockSort(BZip2CompressorOutputStream.Data data) {
/*  150 */     this.quadrant = data.sfmap;
/*      */   }
/*      */   
/*      */   void blockSort(BZip2CompressorOutputStream.Data data, int last) {
/*  154 */     this.workLimit = 30 * last;
/*  155 */     this.workDone = 0;
/*  156 */     this.firstAttempt = true;
/*  158 */     if (last + 1 < 10000) {
/*  159 */       fallbackSort(data, last);
/*      */     } else {
/*  161 */       mainSort(data, last);
/*  163 */       if (this.firstAttempt && this.workDone > this.workLimit)
/*  164 */         fallbackSort(data, last); 
/*      */     } 
/*  168 */     int[] fmap = data.fmap;
/*  169 */     data.origPtr = -1;
/*  170 */     for (int i = 0; i <= last; i++) {
/*  171 */       if (fmap[i] == 0) {
/*  172 */         data.origPtr = i;
/*      */         break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   final void fallbackSort(BZip2CompressorOutputStream.Data data, int last) {
/*  187 */     data.block[0] = data.block[last + 1];
/*  188 */     fallbackSort(data.fmap, data.block, last + 1);
/*      */     int i;
/*  189 */     for (i = 0; i < last + 1; i++)
/*  190 */       data.fmap[i] = data.fmap[i] - 1; 
/*  192 */     for (i = 0; i < last + 1; i++) {
/*  193 */       if (data.fmap[i] == -1) {
/*  194 */         data.fmap[i] = last;
/*      */         break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void fallbackSimpleSort(int[] fmap, int[] eclass, int lo, int hi) {
/*  271 */     if (lo == hi)
/*      */       return; 
/*  276 */     if (hi - lo > 3)
/*  277 */       for (int j = hi - 4; j >= lo; j--) {
/*  278 */         int tmp = fmap[j];
/*  279 */         int ec_tmp = eclass[tmp];
/*      */         int k;
/*  280 */         for (k = j + 4; k <= hi && ec_tmp > eclass[fmap[k]]; 
/*  281 */           k += 4)
/*  282 */           fmap[k - 4] = fmap[k]; 
/*  284 */         fmap[k - 4] = tmp;
/*      */       }  
/*  288 */     for (int i = hi - 1; i >= lo; i--) {
/*  289 */       int tmp = fmap[i];
/*  290 */       int ec_tmp = eclass[tmp];
/*      */       int j;
/*  291 */       for (j = i + 1; j <= hi && ec_tmp > eclass[fmap[j]]; j++)
/*  292 */         fmap[j - 1] = fmap[j]; 
/*  294 */       fmap[j - 1] = tmp;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void fswap(int[] fmap, int zz1, int zz2) {
/*  304 */     int zztmp = fmap[zz1];
/*  305 */     fmap[zz1] = fmap[zz2];
/*  306 */     fmap[zz2] = zztmp;
/*      */   }
/*      */   
/*      */   private void fvswap(int[] fmap, int yyp1, int yyp2, int yyn) {
/*  313 */     while (yyn > 0) {
/*  314 */       fswap(fmap, yyp1, yyp2);
/*  315 */       yyp1++;
/*  315 */       yyp2++;
/*  315 */       yyn--;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int fmin(int a, int b) {
/*  320 */     return (a < b) ? a : b;
/*      */   }
/*      */   
/*      */   private void fpush(int sp, int lz, int hz) {
/*  324 */     this.stack_ll[sp] = lz;
/*  325 */     this.stack_hh[sp] = hz;
/*      */   }
/*      */   
/*      */   private int[] fpop(int sp) {
/*  329 */     return new int[] { this.stack_ll[sp], this.stack_hh[sp] };
/*      */   }
/*      */   
/*      */   private void fallbackQSort3(int[] fmap, int[] eclass, int loSt, int hiSt) {
/*  348 */     long r = 0L;
/*  349 */     int sp = 0;
/*  350 */     fpush(sp++, loSt, hiSt);
/*  352 */     while (sp > 0) {
/*      */       long med;
/*  353 */       int[] s = fpop(--sp);
/*  354 */       int lo = s[0], hi = s[1];
/*  356 */       if (hi - lo < 10) {
/*  357 */         fallbackSimpleSort(fmap, eclass, lo, hi);
/*      */         continue;
/*      */       } 
/*  368 */       r = (r * 7621L + 1L) % 32768L;
/*  369 */       long r3 = r % 3L;
/*  370 */       if (r3 == 0L) {
/*  371 */         med = eclass[fmap[lo]];
/*  372 */       } else if (r3 == 1L) {
/*  373 */         med = eclass[fmap[lo + hi >>> 1]];
/*      */       } else {
/*  375 */         med = eclass[fmap[hi]];
/*      */       } 
/*  378 */       int ltLo = lo, unLo = ltLo;
/*  379 */       int gtHi = hi, unHi = gtHi;
/*      */       while (true) {
/*  385 */         if (unLo <= unHi) {
/*  388 */           int i = eclass[fmap[unLo]] - (int)med;
/*  389 */           if (i == 0) {
/*  390 */             fswap(fmap, unLo, ltLo);
/*  391 */             ltLo++;
/*  391 */             unLo++;
/*      */             continue;
/*      */           } 
/*  394 */           if (i <= 0) {
/*  397 */             unLo++;
/*      */             continue;
/*      */           } 
/*      */         } 
/*  400 */         while (unLo <= unHi) {
/*  403 */           int i = eclass[fmap[unHi]] - (int)med;
/*  404 */           if (i == 0) {
/*  405 */             fswap(fmap, unHi, gtHi);
/*  406 */             gtHi--;
/*  406 */             unHi--;
/*      */             continue;
/*      */           } 
/*  409 */           if (i < 0)
/*      */             break; 
/*  412 */           unHi--;
/*      */         } 
/*  414 */         if (unLo > unHi)
/*      */           break; 
/*  417 */         fswap(fmap, unLo, unHi);
/*  417 */         unLo++;
/*  417 */         unHi--;
/*      */       } 
/*  420 */       if (gtHi < ltLo)
/*      */         continue; 
/*  424 */       int n = fmin(ltLo - lo, unLo - ltLo);
/*  425 */       fvswap(fmap, lo, unLo - n, n);
/*  426 */       int m = fmin(hi - gtHi, gtHi - unHi);
/*  427 */       fvswap(fmap, unHi + 1, hi - m + 1, m);
/*  429 */       n = lo + unLo - ltLo - 1;
/*  430 */       m = hi - gtHi - unHi + 1;
/*  432 */       if (n - lo > hi - m) {
/*  433 */         fpush(sp++, lo, n);
/*  434 */         fpush(sp++, m, hi);
/*      */         continue;
/*      */       } 
/*  436 */       fpush(sp++, m, hi);
/*  437 */       fpush(sp++, lo, n);
/*      */     } 
/*      */   }
/*      */   
/*      */   private int[] getEclass() {
/*  448 */     return (this.eclass == null) ? (this.eclass = new int[this.quadrant.length / 2]) : this.eclass;
/*      */   }
/*      */   
/*      */   final void fallbackSort(int[] fmap, byte[] block, int nblock) {
/*  471 */     int nNotDone, ftab[] = new int[257];
/*  475 */     int[] eclass = getEclass();
/*      */     int i;
/*  477 */     for (i = 0; i < nblock; i++)
/*  478 */       eclass[i] = 0; 
/*  484 */     for (i = 0; i < nblock; i++)
/*  485 */       ftab[block[i] & 0xFF] = ftab[block[i] & 0xFF] + 1; 
/*  487 */     for (i = 1; i < 257; i++)
/*  488 */       ftab[i] = ftab[i] + ftab[i - 1]; 
/*  491 */     for (i = 0; i < nblock; i++) {
/*  492 */       int j = block[i] & 0xFF;
/*  493 */       int k = ftab[j] - 1;
/*  494 */       ftab[j] = k;
/*  495 */       fmap[k] = i;
/*      */     } 
/*  498 */     int nBhtab = 64 + nblock;
/*  499 */     BitSet bhtab = new BitSet(nBhtab);
/*  500 */     for (i = 0; i < 256; i++)
/*  501 */       bhtab.set(ftab[i]); 
/*  511 */     for (i = 0; i < 32; i++) {
/*  512 */       bhtab.set(nblock + 2 * i);
/*  513 */       bhtab.clear(nblock + 2 * i + 1);
/*      */     } 
/*  517 */     int H = 1;
/*      */     do {
/*  520 */       int j = 0;
/*  521 */       for (i = 0; i < nblock; i++) {
/*  522 */         if (bhtab.get(i))
/*  523 */           j = i; 
/*  525 */         int k = fmap[i] - H;
/*  526 */         if (k < 0)
/*  527 */           k += nblock; 
/*  529 */         eclass[k] = j;
/*      */       } 
/*  532 */       nNotDone = 0;
/*  533 */       int r = -1;
/*      */       while (true) {
/*  537 */         int k = r + 1;
/*  538 */         k = bhtab.nextClearBit(k);
/*  539 */         int l = k - 1;
/*  540 */         if (l >= nblock)
/*      */           break; 
/*  543 */         k = bhtab.nextSetBit(k + 1);
/*  544 */         r = k - 1;
/*  545 */         if (r >= nblock)
/*      */           break; 
/*  550 */         if (r > l) {
/*  551 */           nNotDone += r - l + 1;
/*  552 */           fallbackQSort3(fmap, eclass, l, r);
/*  555 */           int cc = -1;
/*  556 */           for (i = l; i <= r; i++) {
/*  557 */             int cc1 = eclass[fmap[i]];
/*  558 */             if (cc != cc1) {
/*  559 */               bhtab.set(i);
/*  560 */               cc = cc1;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  566 */       H *= 2;
/*  567 */     } while (H <= nblock && nNotDone != 0);
/*      */   }
/*      */   
/*  580 */   private static final int[] INCS = new int[] { 
/*  580 */       1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 
/*  580 */       88573, 265720, 797161, 2391484 };
/*      */   
/*      */   private static final int SMALL_THRESH = 20;
/*      */   
/*      */   private static final int DEPTH_THRESH = 10;
/*      */   
/*      */   private static final int WORK_FACTOR = 30;
/*      */   
/*      */   private static final int SETMASK = 2097152;
/*      */   
/*      */   private static final int CLEARMASK = -2097153;
/*      */   
/*      */   private boolean mainSimpleSort(BZip2CompressorOutputStream.Data dataShadow, int lo, int hi, int d, int lastShadow) {
/*  597 */     int bigN = hi - lo + 1;
/*  598 */     if (bigN < 2)
/*  599 */       return (this.firstAttempt && this.workDone > this.workLimit); 
/*  602 */     int hp = 0;
/*  603 */     while (INCS[hp] < bigN)
/*  604 */       hp++; 
/*  607 */     int[] fmap = dataShadow.fmap;
/*  608 */     char[] quadrant = this.quadrant;
/*  609 */     byte[] block = dataShadow.block;
/*  610 */     int lastPlus1 = lastShadow + 1;
/*  611 */     boolean firstAttemptShadow = this.firstAttempt;
/*  612 */     int workLimitShadow = this.workLimit;
/*  613 */     int workDoneShadow = this.workDone;
/*  618 */     label97: while (--hp >= 0) {
/*  619 */       int h = INCS[hp];
/*  620 */       int mj = lo + h - 1;
/*  622 */       for (int i = lo + h; i <= hi; ) {
/*  624 */         for (int k = 3; i <= hi && --k >= 0; i++) {
/*  625 */           int v = fmap[i];
/*  626 */           int vd = v + d;
/*  627 */           int j = i;
/*  639 */           boolean onceRunned = false;
/*  640 */           int a = 0;
/*      */           while (true) {
/*  643 */             if (onceRunned) {
/*  644 */               fmap[j] = a;
/*  645 */               if ((j -= h) <= mj)
/*      */                 break; 
/*      */             } else {
/*  649 */               onceRunned = true;
/*      */             } 
/*  652 */             a = fmap[j - h];
/*  653 */             int i1 = a + d;
/*  654 */             int i2 = vd;
/*  658 */             if (block[i1 + 1] == block[i2 + 1]) {
/*  659 */               if (block[i1 + 2] == block[i2 + 2]) {
/*  660 */                 if (block[i1 + 3] == block[i2 + 3]) {
/*  661 */                   if (block[i1 + 4] == block[i2 + 4]) {
/*  662 */                     if (block[i1 + 5] == block[i2 + 5]) {
/*  663 */                       i1 += 6;
/*  663 */                       i2 += 6;
/*  663 */                       if (block[i1] == block[i2]) {
/*  664 */                         int x = lastShadow;
/*  665 */                         while (x > 0) {
/*  666 */                           x -= 4;
/*  668 */                           if (block[i1 + 1] == block[i2 + 1]) {
/*  669 */                             if (quadrant[i1] == quadrant[i2]) {
/*  670 */                               if (block[i1 + 2] == block[i2 + 2]) {
/*  671 */                                 if (quadrant[i1 + 1] == quadrant[i2 + 1]) {
/*  672 */                                   if (block[i1 + 3] == block[i2 + 3]) {
/*  673 */                                     if (quadrant[i1 + 2] == quadrant[i2 + 2]) {
/*  674 */                                       if (block[i1 + 4] == block[i2 + 4]) {
/*  675 */                                         if (quadrant[i1 + 3] == quadrant[i2 + 3]) {
/*  676 */                                           i1 += 4;
/*  676 */                                           if (i1 >= lastPlus1)
/*  677 */                                             i1 -= lastPlus1; 
/*  679 */                                           i2 += 4;
/*  679 */                                           if (i2 >= lastPlus1)
/*  680 */                                             i2 -= lastPlus1; 
/*  682 */                                           workDoneShadow++;
/*      */                                           continue;
/*      */                                         } 
/*  684 */                                         if (quadrant[i1 + 3] > quadrant[i2 + 3])
/*      */                                           continue; 
/*      */                                         break;
/*      */                                       } 
/*  689 */                                       if ((block[i1 + 4] & 0xFF) > (block[i2 + 4] & 0xFF))
/*      */                                         continue; 
/*      */                                       break;
/*      */                                     } 
/*  694 */                                     if (quadrant[i1 + 2] > quadrant[i2 + 2])
/*      */                                       continue; 
/*      */                                     break;
/*      */                                   } 
/*  699 */                                   if ((block[i1 + 3] & 0xFF) > (block[i2 + 3] & 0xFF))
/*      */                                     continue; 
/*      */                                   break;
/*      */                                 } 
/*  704 */                                 if (quadrant[i1 + 1] > quadrant[i2 + 1])
/*      */                                   continue; 
/*      */                                 break;
/*      */                               } 
/*  709 */                               if ((block[i1 + 2] & 0xFF) > (block[i2 + 2] & 0xFF))
/*      */                                 continue; 
/*      */                               break;
/*      */                             } 
/*  714 */                             if (quadrant[i1] > quadrant[i2])
/*      */                               continue; 
/*      */                             break;
/*      */                           } 
/*  719 */                           if ((block[i1 + 1] & 0xFF) > (block[i2 + 1] & 0xFF));
/*      */                         } 
/*      */                         break;
/*      */                       } 
/*  729 */                       if ((block[i1] & 0xFF) > (block[i2] & 0xFF))
/*      */                         continue; 
/*      */                       break;
/*      */                     } 
/*  735 */                     if ((block[i1 + 5] & 0xFF) > (block[i2 + 5] & 0xFF))
/*      */                       continue; 
/*      */                     break;
/*      */                   } 
/*  740 */                   if ((block[i1 + 4] & 0xFF) > (block[i2 + 4] & 0xFF))
/*      */                     continue; 
/*      */                   break;
/*      */                 } 
/*  745 */                 if ((block[i1 + 3] & 0xFF) > (block[i2 + 3] & 0xFF))
/*      */                   continue; 
/*      */                 break;
/*      */               } 
/*  750 */               if ((block[i1 + 2] & 0xFF) > (block[i2 + 2] & 0xFF))
/*      */                 continue; 
/*      */               break;
/*      */             } 
/*  755 */             if ((block[i1 + 1] & 0xFF) > (block[i2 + 1] & 0xFF))
/*      */               continue; 
/*      */             break;
/*      */           } 
/*  764 */           fmap[j] = v;
/*      */         } 
/*  767 */         if (firstAttemptShadow && i <= hi && workDoneShadow > workLimitShadow)
/*      */           break label97; 
/*      */       } 
/*      */     } 
/*  774 */     this.workDone = workDoneShadow;
/*  775 */     return (firstAttemptShadow && workDoneShadow > workLimitShadow);
/*      */   }
/*      */   
/*      */   private static void vswap(int[] fmap, int p1, int p2, int n) {
/*  787 */     n += p1;
/*  788 */     while (p1 < n) {
/*  789 */       int t = fmap[p1];
/*  790 */       fmap[p1++] = fmap[p2];
/*  791 */       fmap[p2++] = t;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static byte med3(byte a, byte b, byte c) {
/*  796 */     return (a < b) ? ((b < c) ? b : ((a < c) ? c : a)) : ((b > c) ? b : ((a > c) ? c : a));
/*      */   }
/*      */   
/*      */   private void mainQSort3(BZip2CompressorOutputStream.Data dataShadow, int loSt, int hiSt, int dSt, int last) {
/*  810 */     int[] stack_ll = this.stack_ll;
/*  811 */     int[] stack_hh = this.stack_hh;
/*  812 */     int[] stack_dd = this.stack_dd;
/*  813 */     int[] fmap = dataShadow.fmap;
/*  814 */     byte[] block = dataShadow.block;
/*  816 */     stack_ll[0] = loSt;
/*  817 */     stack_hh[0] = hiSt;
/*  818 */     stack_dd[0] = dSt;
/*  820 */     for (int sp = 1; --sp >= 0; ) {
/*  821 */       int lo = stack_ll[sp];
/*  822 */       int hi = stack_hh[sp];
/*  823 */       int d = stack_dd[sp];
/*  825 */       if (hi - lo < 20 || d > 10) {
/*  826 */         if (mainSimpleSort(dataShadow, lo, hi, d, last))
/*      */           return; 
/*      */         continue;
/*      */       } 
/*  830 */       int d1 = d + 1;
/*  831 */       int med = med3(block[fmap[lo] + d1], block[fmap[hi] + d1], block[fmap[lo + hi >>> 1] + d1]) & 0xFF;
/*  834 */       int unLo = lo;
/*  835 */       int unHi = hi;
/*  836 */       int ltLo = lo;
/*  837 */       int gtHi = hi;
/*      */       while (true) {
/*  840 */         if (unLo <= unHi) {
/*  841 */           int i = (block[fmap[unLo] + d1] & 0xFF) - med;
/*  843 */           if (i == 0) {
/*  844 */             int temp = fmap[unLo];
/*  845 */             fmap[unLo++] = fmap[ltLo];
/*  846 */             fmap[ltLo++] = temp;
/*      */             continue;
/*      */           } 
/*  847 */           if (i < 0) {
/*  848 */             unLo++;
/*      */             continue;
/*      */           } 
/*      */         } 
/*  854 */         while (unLo <= unHi) {
/*  855 */           int i = (block[fmap[unHi] + d1] & 0xFF) - med;
/*  857 */           if (i == 0) {
/*  858 */             int temp = fmap[unHi];
/*  859 */             fmap[unHi--] = fmap[gtHi];
/*  860 */             fmap[gtHi--] = temp;
/*      */             continue;
/*      */           } 
/*  861 */           if (i > 0)
/*  862 */             unHi--; 
/*      */         } 
/*  868 */         if (unLo <= unHi) {
/*  869 */           int temp = fmap[unLo];
/*  870 */           fmap[unLo++] = fmap[unHi];
/*  871 */           fmap[unHi--] = temp;
/*      */           continue;
/*      */         } 
/*      */         break;
/*      */       } 
/*  877 */       if (gtHi < ltLo) {
/*  878 */         stack_ll[sp] = lo;
/*  879 */         stack_hh[sp] = hi;
/*  880 */         stack_dd[sp] = d1;
/*  881 */         sp++;
/*      */         continue;
/*      */       } 
/*  883 */       int n = (ltLo - lo < unLo - ltLo) ? (ltLo - lo) : (unLo - ltLo);
/*  885 */       vswap(fmap, lo, unLo - n, n);
/*  886 */       int m = (hi - gtHi < gtHi - unHi) ? (hi - gtHi) : (gtHi - unHi);
/*  888 */       vswap(fmap, unLo, hi - m + 1, m);
/*  890 */       n = lo + unLo - ltLo - 1;
/*  891 */       m = hi - gtHi - unHi + 1;
/*  893 */       stack_ll[sp] = lo;
/*  894 */       stack_hh[sp] = n;
/*  895 */       stack_dd[sp] = d;
/*  896 */       sp++;
/*  898 */       stack_ll[sp] = n + 1;
/*  899 */       stack_hh[sp] = m - 1;
/*  900 */       stack_dd[sp] = d1;
/*  901 */       sp++;
/*  903 */       stack_ll[sp] = m;
/*  904 */       stack_hh[sp] = hi;
/*  905 */       stack_dd[sp] = d;
/*  906 */       sp++;
/*      */     } 
/*      */   }
/*      */   
/*      */   final void mainSort(BZip2CompressorOutputStream.Data dataShadow, int lastShadow) {
/*  917 */     int[] runningOrder = this.mainSort_runningOrder;
/*  918 */     int[] copy = this.mainSort_copy;
/*  919 */     boolean[] bigDone = this.mainSort_bigDone;
/*  920 */     int[] ftab = this.ftab;
/*  921 */     byte[] block = dataShadow.block;
/*  922 */     int[] fmap = dataShadow.fmap;
/*  923 */     char[] quadrant = this.quadrant;
/*  924 */     int workLimitShadow = this.workLimit;
/*  925 */     boolean firstAttemptShadow = this.firstAttempt;
/*      */     int i;
/*  928 */     for (i = 65537; --i >= 0;)
/*  929 */       ftab[i] = 0; 
/*  937 */     for (i = 0; i < 20; i++)
/*  938 */       block[lastShadow + i + 2] = block[i % (lastShadow + 1) + 1]; 
/*  940 */     for (i = lastShadow + 20 + 1; --i >= 0;)
/*  941 */       quadrant[i] = Character.MIN_VALUE; 
/*  943 */     block[0] = block[lastShadow + 1];
/*  947 */     int c1 = block[0] & 0xFF;
/*      */     int k;
/*  948 */     for (k = 0; k <= lastShadow; k++) {
/*  949 */       int c2 = block[k + 1] & 0xFF;
/*  950 */       ftab[(c1 << 8) + c2] = ftab[(c1 << 8) + c2] + 1;
/*  951 */       c1 = c2;
/*      */     } 
/*  954 */     for (k = 1; k <= 65536; k++)
/*  955 */       ftab[k] = ftab[k] + ftab[k - 1]; 
/*  958 */     c1 = block[1] & 0xFF;
/*  959 */     for (k = 0; k < lastShadow; k++) {
/*  960 */       int c2 = block[k + 2] & 0xFF;
/*  961 */       ftab[(c1 << 8) + c2] = ftab[(c1 << 8) + c2] - 1;
/*  961 */       fmap[ftab[(c1 << 8) + c2] - 1] = k;
/*  962 */       c1 = c2;
/*      */     } 
/*  965 */     ftab[((block[lastShadow + 1] & 0xFF) << 8) + (block[1] & 0xFF)] = ftab[((block[lastShadow + 1] & 0xFF) << 8) + (block[1] & 0xFF)] - 1;
/*  965 */     fmap[ftab[((block[lastShadow + 1] & 0xFF) << 8) + (block[1] & 0xFF)] - 1] = lastShadow;
/*  971 */     for (k = 256; --k >= 0; ) {
/*  972 */       bigDone[k] = false;
/*  973 */       runningOrder[k] = k;
/*      */     } 
/*  976 */     for (int h = 364; h != 1; ) {
/*  977 */       h /= 3;
/*  978 */       for (int m = h; m <= 255; m++) {
/*  979 */         int vv = runningOrder[m];
/*  980 */         int a = ftab[vv + 1 << 8] - ftab[vv << 8];
/*  981 */         int b = h - 1;
/*  982 */         int n = m;
/*      */         int ro;
/*  983 */         for (ro = runningOrder[n - h]; ftab[ro + 1 << 8] - ftab[ro << 8] > a; ro = runningOrder[n - h]) {
/*  985 */           runningOrder[n] = ro;
/*  986 */           n -= h;
/*  987 */           if (n <= b)
/*      */             break; 
/*      */         } 
/*  991 */         runningOrder[n] = vv;
/*      */       } 
/*      */     } 
/*  998 */     for (int j = 0; j <= 255; j++) {
/* 1002 */       int ss = runningOrder[j];
/*      */       int m;
/* 1011 */       for (m = 0; m <= 255; m++) {
/* 1012 */         int sb = (ss << 8) + m;
/* 1013 */         int ftab_sb = ftab[sb];
/* 1014 */         if ((ftab_sb & 0x200000) != 2097152) {
/* 1015 */           int lo = ftab_sb & 0xFFDFFFFF;
/* 1016 */           int hi = (ftab[sb + 1] & 0xFFDFFFFF) - 1;
/* 1017 */           if (hi > lo) {
/* 1018 */             mainQSort3(dataShadow, lo, hi, 2, lastShadow);
/* 1019 */             if (firstAttemptShadow && this.workDone > workLimitShadow)
/*      */               return; 
/*      */           } 
/* 1024 */           ftab[sb] = ftab_sb | 0x200000;
/*      */         } 
/*      */       } 
/* 1032 */       for (m = 0; m <= 255; m++)
/* 1033 */         copy[m] = ftab[(m << 8) + ss] & 0xFFDFFFFF; 
/*      */       int hj;
/* 1036 */       for (m = ftab[ss << 8] & 0xFFDFFFFF, hj = ftab[ss + 1 << 8] & 0xFFDFFFFF; m < hj; m++) {
/* 1037 */         int fmap_j = fmap[m];
/* 1038 */         c1 = block[fmap_j] & 0xFF;
/* 1039 */         if (!bigDone[c1]) {
/* 1040 */           fmap[copy[c1]] = (fmap_j == 0) ? lastShadow : (fmap_j - 1);
/* 1041 */           copy[c1] = copy[c1] + 1;
/*      */         } 
/*      */       } 
/* 1045 */       for (m = 256; --m >= 0;)
/* 1046 */         ftab[(m << 8) + ss] = ftab[(m << 8) + ss] | 0x200000; 
/* 1057 */       bigDone[ss] = true;
/* 1059 */       if (j < 255) {
/* 1060 */         int bbStart = ftab[ss << 8] & 0xFFDFFFFF;
/* 1061 */         int bbSize = (ftab[ss + 1 << 8] & 0xFFDFFFFF) - bbStart;
/* 1062 */         int shifts = 0;
/* 1064 */         while (bbSize >> shifts > 65534)
/* 1065 */           shifts++; 
/* 1068 */         for (int n = 0; n < bbSize; n++) {
/* 1069 */           int a2update = fmap[bbStart + n];
/* 1070 */           char qVal = (char)(n >> shifts);
/* 1071 */           quadrant[a2update] = qVal;
/* 1072 */           if (a2update < 20)
/* 1073 */             quadrant[a2update + lastShadow + 1] = qVal; 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\bzip2\BlockSort.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */