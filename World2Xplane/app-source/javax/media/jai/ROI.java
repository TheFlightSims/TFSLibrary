/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.awt.image.renderable.RenderedImageFactory;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Arrays;
/*      */ import java.util.LinkedList;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.iterator.RandomIter;
/*      */ import javax.media.jai.iterator.RandomIterFactory;
/*      */ import javax.media.jai.remote.SerializableState;
/*      */ import javax.media.jai.remote.SerializerFactory;
/*      */ 
/*      */ public class ROI implements Serializable {
/*   70 */   private transient RandomIter iter = null;
/*      */   
/*   73 */   transient PlanarImage theImage = null;
/*      */   
/*   76 */   int threshold = 127;
/*      */   
/*      */   protected static LinkedList mergeRunLengthList(LinkedList rectList) {
/*   90 */     if (rectList == null)
/*   91 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*   95 */     if (rectList.size() > 1) {
/*   99 */       int mergeIndex = 0;
/*  100 */       for (; mergeIndex < rectList.size() - 1; 
/*  101 */         mergeIndex++) {
/*  103 */         ListIterator rectIter = rectList.listIterator(mergeIndex);
/*  104 */         Rectangle mergeRect = rectIter.next();
/*  106 */         while (rectIter.hasNext()) {
/*  107 */           Rectangle runRect = rectIter.next();
/*  110 */           int abuttingY = mergeRect.y + mergeRect.height;
/*  112 */           if (runRect.y == abuttingY && runRect.x == mergeRect.x && runRect.width == mergeRect.width) {
/*  115 */             mergeRect = new Rectangle(mergeRect.x, mergeRect.y, mergeRect.width, mergeRect.height + runRect.height);
/*  121 */             rectIter.remove();
/*  124 */             rectList.set(mergeIndex, mergeRect);
/*      */             continue;
/*      */           } 
/*  125 */           if (runRect.y > abuttingY)
/*      */             break; 
/*      */         } 
/*      */       } 
/*      */     } 
/*  137 */     return rectList;
/*      */   }
/*      */   
/*      */   protected ROI() {}
/*      */   
/*      */   public ROI(RenderedImage im) {
/*  159 */     this(im, 127);
/*      */   }
/*      */   
/*      */   public ROI(RenderedImage im, int threshold) {
/*  174 */     if (im == null)
/*  175 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  178 */     SampleModel sm = im.getSampleModel();
/*  180 */     if (sm.getNumBands() != 1)
/*  181 */       throw new IllegalArgumentException(JaiI18N.getString("ROI0")); 
/*  184 */     this.threshold = threshold;
/*  188 */     if (threshold >= 1 && ImageUtil.isBinary(sm)) {
/*  189 */       this.theImage = PlanarImage.wrapRenderedImage(im);
/*      */     } else {
/*  194 */       ParameterBlockJAI pbj = new ParameterBlockJAI("binarize");
/*  196 */       pbj.setSource("source0", im);
/*  197 */       pbj.setParameter("threshold", threshold);
/*  199 */       this.theImage = JAI.create("binarize", pbj, (RenderingHints)null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private RandomIter getIter() {
/*  205 */     if (this.iter == null)
/*  206 */       this.iter = RandomIterFactory.create(this.theImage, null); 
/*  208 */     return this.iter;
/*      */   }
/*      */   
/*      */   public int getThreshold() {
/*  213 */     return this.threshold;
/*      */   }
/*      */   
/*      */   public void setThreshold(int threshold) {
/*  218 */     this.threshold = threshold;
/*  219 */     ((RenderedOp)this.theImage).setParameter(threshold, 0);
/*  220 */     this.iter = null;
/*  221 */     getIter();
/*      */   }
/*      */   
/*      */   public Rectangle getBounds() {
/*  226 */     return new Rectangle(this.theImage.getMinX(), this.theImage.getMinY(), this.theImage.getWidth(), this.theImage.getHeight());
/*      */   }
/*      */   
/*      */   public Rectangle2D getBounds2D() {
/*  234 */     return new Rectangle2D.Float(this.theImage.getMinX(), this.theImage.getMinY(), this.theImage.getWidth(), this.theImage.getHeight());
/*      */   }
/*      */   
/*      */   public boolean contains(Point p) {
/*  248 */     if (p == null)
/*  249 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  252 */     return contains(p.x, p.y);
/*      */   }
/*      */   
/*      */   public boolean contains(Point2D p) {
/*  263 */     if (p == null)
/*  264 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  267 */     return contains((int)p.getX(), (int)p.getY());
/*      */   }
/*      */   
/*      */   public boolean contains(int x, int y) {
/*  278 */     int minX = this.theImage.getMinX();
/*  279 */     int minY = this.theImage.getMinY();
/*  281 */     return (x >= minX && x < minX + this.theImage.getWidth() && y >= minY && y < minY + this.theImage.getHeight() && getIter().getSample(x, y, 0) >= 1);
/*      */   }
/*      */   
/*      */   public boolean contains(double x, double y) {
/*  296 */     return contains((int)x, (int)y);
/*      */   }
/*      */   
/*      */   public boolean contains(Rectangle rect) {
/*  310 */     if (rect == null)
/*  311 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  314 */     if (!rect.equals(rect.intersection(getBounds())))
/*  315 */       return false; 
/*  318 */     byte[] packedData = ImageUtil.getPackedBinaryData(this.theImage.getData(), rect);
/*  324 */     int leftover = rect.width % 8;
/*  326 */     if (leftover == 0) {
/*  328 */       for (int i = 0; i < packedData.length; i++) {
/*  329 */         if ((packedData[i] & 0xFF) != 255)
/*  330 */           return false; 
/*      */       } 
/*      */     } else {
/*  334 */       int mask = (1 << leftover) - 1 << 8 - leftover;
/*  336 */       for (int y = 0, k = 0; y < rect.height; y++) {
/*  337 */         for (int x = 0; x < rect.width - leftover; x += 8, k++) {
/*  338 */           if ((packedData[k] & 0xFF) != 255)
/*  339 */             return false; 
/*      */         } 
/*  342 */         if ((packedData[k] & mask) != mask)
/*  343 */           return false; 
/*  345 */         k++;
/*      */       } 
/*      */     } 
/*  349 */     return true;
/*      */   }
/*      */   
/*      */   public boolean contains(Rectangle2D rect) {
/*  363 */     if (rect == null)
/*  364 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  366 */     Rectangle r = new Rectangle((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
/*  370 */     return contains(r);
/*      */   }
/*      */   
/*      */   public boolean contains(int x, int y, int w, int h) {
/*  385 */     Rectangle r = new Rectangle(x, y, w, h);
/*  386 */     return contains(r);
/*      */   }
/*      */   
/*      */   public boolean contains(double x, double y, double w, double h) {
/*  402 */     Rectangle rect = new Rectangle((int)x, (int)y, (int)w, (int)h);
/*  404 */     return contains(rect);
/*      */   }
/*      */   
/*      */   public boolean intersects(Rectangle rect) {
/*  417 */     if (rect == null)
/*  418 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  421 */     Rectangle r = rect.intersection(getBounds());
/*  423 */     if (r.isEmpty())
/*  424 */       return false; 
/*  427 */     byte[] packedData = ImageUtil.getPackedBinaryData(this.theImage.getData(), r);
/*  433 */     int leftover = r.width % 8;
/*  435 */     if (leftover == 0) {
/*  437 */       for (int i = 0; i < packedData.length; i++) {
/*  438 */         if ((packedData[i] & 0xFF) != 0)
/*  439 */           return true; 
/*      */       } 
/*      */     } else {
/*  443 */       int mask = (1 << leftover) - 1 << 8 - leftover;
/*  445 */       for (int y = 0, k = 0; y < r.height; y++) {
/*  446 */         for (int x = 0; x < r.width - leftover; x += 8, k++) {
/*  447 */           if ((packedData[k] & 0xFF) != 0)
/*  448 */             return true; 
/*      */         } 
/*  450 */         if ((packedData[k] & mask) != 0)
/*  451 */           return true; 
/*  452 */         k++;
/*      */       } 
/*      */     } 
/*  456 */     return false;
/*      */   }
/*      */   
/*      */   public boolean intersects(Rectangle2D r) {
/*  469 */     if (r == null)
/*  470 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  473 */     Rectangle rect = new Rectangle((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
/*  477 */     return intersects(rect);
/*      */   }
/*      */   
/*      */   public boolean intersects(int x, int y, int w, int h) {
/*  491 */     Rectangle rect = new Rectangle(x, y, w, h);
/*  492 */     return intersects(rect);
/*      */   }
/*      */   
/*      */   public boolean intersects(double x, double y, double w, double h) {
/*  506 */     Rectangle rect = new Rectangle((int)x, (int)y, (int)w, (int)h);
/*  508 */     return intersects(rect);
/*      */   }
/*      */   
/*      */   private static PlanarImage createBinaryImage(Rectangle r) {
/*  517 */     if (r.x == 0 && r.y == 0) {
/*  519 */       BufferedImage bi = new BufferedImage(r.width, r.height, 12);
/*  523 */       return PlanarImage.wrapRenderedImage(bi);
/*      */     } 
/*  527 */     SampleModel sm = new MultiPixelPackedSampleModel(0, r.width, r.height, 1);
/*  532 */     return new TiledImage(r.x, r.y, r.width, r.height, r.x, r.y, sm, PlanarImage.createColorModel(sm));
/*      */   }
/*      */   
/*      */   private ROI createOpROI(ROI roi, String op) {
/*      */     PlanarImage imDest;
/*  548 */     if (roi == null)
/*  549 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  552 */     PlanarImage imThis = getAsImage();
/*  553 */     PlanarImage imROI = roi.getAsImage();
/*  556 */     Rectangle boundsThis = imThis.getBounds();
/*  557 */     Rectangle boundsROI = imROI.getBounds();
/*  563 */     if (op.equals("and") || boundsThis.equals(boundsROI)) {
/*  564 */       imDest = JAI.create(op, imThis, imROI);
/*  566 */     } else if (op.equals("subtract") || boundsThis.contains(boundsROI)) {
/*  568 */       PlanarImage imBounds = createBinaryImage(boundsThis);
/*  570 */       imBounds = JAI.create("overlay", imBounds, imROI);
/*  571 */       imDest = JAI.create(op, imThis, imBounds);
/*  573 */     } else if (boundsROI.contains(boundsThis)) {
/*  575 */       PlanarImage imBounds = createBinaryImage(boundsROI);
/*  577 */       imBounds = JAI.create("overlay", imBounds, imThis);
/*  578 */       imDest = JAI.create(op, imBounds, imROI);
/*      */     } else {
/*  582 */       Rectangle merged = boundsThis.union(boundsROI);
/*  584 */       PlanarImage imBoundsThis = createBinaryImage(merged);
/*  585 */       PlanarImage imBoundsROI = createBinaryImage(merged);
/*  587 */       imBoundsThis = JAI.create("overlay", imBoundsThis, imThis);
/*  588 */       imBoundsROI = JAI.create("overlay", imBoundsROI, imROI);
/*  589 */       imDest = JAI.create(op, imBoundsThis, imBoundsROI);
/*      */     } 
/*  592 */     return new ROI(imDest, this.threshold);
/*      */   }
/*      */   
/*      */   public ROI add(ROI roi) {
/*  607 */     return createOpROI(roi, "add");
/*      */   }
/*      */   
/*      */   public ROI subtract(ROI roi) {
/*  622 */     return createOpROI(roi, "subtract");
/*      */   }
/*      */   
/*      */   public ROI intersect(ROI roi) {
/*  636 */     return createOpROI(roi, "and");
/*      */   }
/*      */   
/*      */   public ROI exclusiveOr(ROI roi) {
/*  652 */     return createOpROI(roi, "xor");
/*      */   }
/*      */   
/*      */   public ROI transform(AffineTransform at, Interpolation interp) {
/*  668 */     if (at == null)
/*  669 */       throw new IllegalArgumentException(JaiI18N.getString("ROI5")); 
/*  672 */     if (interp == null)
/*  673 */       throw new IllegalArgumentException(JaiI18N.getString("ROI6")); 
/*  676 */     ParameterBlock paramBlock = new ParameterBlock();
/*  677 */     paramBlock.add(at);
/*  678 */     paramBlock.add(interp);
/*  679 */     return performImageOp("Affine", paramBlock, 0, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public ROI transform(AffineTransform at) {
/*  692 */     if (at == null)
/*  693 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  696 */     return transform(at, Interpolation.getInstance(0));
/*      */   }
/*      */   
/*      */   public ROI performImageOp(RenderedImageFactory RIF, ParameterBlock paramBlock, int sourceIndex, RenderingHints renderHints) {
/*  724 */     if (RIF == null || paramBlock == null)
/*  725 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  729 */     ParameterBlock pb = (ParameterBlock)paramBlock.clone();
/*  730 */     Vector sources = pb.getSources();
/*  731 */     sources.insertElementAt(getAsImage(), sourceIndex);
/*  735 */     RenderedImage im = RIF.create(pb, renderHints);
/*  736 */     return new ROI(im, this.threshold);
/*      */   }
/*      */   
/*      */   public ROI performImageOp(String name, ParameterBlock paramBlock, int sourceIndex, RenderingHints renderHints) {
/*  763 */     if (name == null || paramBlock == null)
/*  764 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  768 */     ParameterBlock pb = (ParameterBlock)paramBlock.clone();
/*  769 */     Vector sources = pb.getSources();
/*  770 */     sources.insertElementAt(getAsImage(), sourceIndex);
/*  774 */     RenderedImage im = JAI.create(name, pb, renderHints);
/*  775 */     return new ROI(im, this.threshold);
/*      */   }
/*      */   
/*      */   public Shape getAsShape() {
/*  788 */     return null;
/*      */   }
/*      */   
/*      */   public PlanarImage getAsImage() {
/*  800 */     return this.theImage;
/*      */   }
/*      */   
/*      */   public int[][] getAsBitmask(int x, int y, int width, int height, int[][] mask) {
/*  835 */     Rectangle rect = getBounds().intersection(new Rectangle(x, y, width, height));
/*  839 */     if (rect.isEmpty())
/*  840 */       return (int[][])null; 
/*  844 */     int bitmaskIntWidth = (width + 31) / 32;
/*  847 */     if (mask == null) {
/*  848 */       mask = new int[height][bitmaskIntWidth];
/*  849 */     } else if (mask.length < height || (mask[0]).length < bitmaskIntWidth) {
/*  850 */       throw new RuntimeException(JaiI18N.getString("ROI3"));
/*      */     } 
/*  853 */     byte[] data = ImageUtil.getPackedBinaryData(this.theImage.getData(), rect);
/*  859 */     int leftover = rect.width % 8;
/*  861 */     if (leftover != 0) {
/*  862 */       int datamask = (1 << leftover) - 1 << 8 - leftover;
/*  863 */       int linestride = (width + 7) / 8;
/*      */       int i;
/*  865 */       for (i = linestride - 1; i < data.length; i += linestride)
/*  866 */         data[i] = (byte)(data[i] & datamask); 
/*      */     } 
/*  870 */     int lineStride = (rect.width + 7) / 8;
/*  871 */     int leftOver = lineStride % 4;
/*  874 */     int ncols = (lineStride - leftOver) / 4;
/*      */     int row, k;
/*  876 */     for (row = 0, k = 0; row < rect.height; row++) {
/*  877 */       int[] maskRow = mask[row];
/*      */       int col;
/*  879 */       for (col = 0; col < ncols; col++) {
/*  880 */         maskRow[col] = (data[k] & 0xFF) << 24 | (data[k + 1] & 0xFF) << 16 | (data[k + 2] & 0xFF) << 8 | (data[k + 3] & 0xFF) << 0;
/*  884 */         k += 4;
/*      */       } 
/*  887 */       switch (leftOver) {
/*      */         case 1:
/*  889 */           maskRow[col++] = (data[k] & 0xFF) << 24;
/*      */           break;
/*      */         case 2:
/*  891 */           maskRow[col++] = (data[k] & 0xFF) << 24 | (data[k + 1] & 0xFF) << 16;
/*      */           break;
/*      */         case 3:
/*  894 */           maskRow[col++] = (data[k] & 0xFF) << 24 | (data[k + 1] & 0xFF) << 16 | (data[k + 2] & 0xFF) << 8;
/*      */           break;
/*      */       } 
/*  900 */       k += leftOver;
/*  902 */       Arrays.fill(maskRow, col, bitmaskIntWidth, 0);
/*      */     } 
/*  906 */     for (row = rect.height; row < height; row++)
/*  907 */       Arrays.fill(mask[row], 0); 
/*  910 */     return mask;
/*      */   }
/*      */   
/*      */   public LinkedList getAsRectangleList(int x, int y, int width, int height) {
/*  929 */     return getAsRectangleList(x, y, width, height, true);
/*      */   }
/*      */   
/*      */   protected LinkedList getAsRectangleList(int x, int y, int width, int height, boolean mergeRectangles) {
/*  951 */     Rectangle bounds = getBounds();
/*  952 */     Rectangle rect = new Rectangle(x, y, width, height);
/*  953 */     if (!bounds.intersects(rect))
/*  954 */       return null; 
/*  958 */     if (!bounds.contains(rect)) {
/*  959 */       rect = bounds.intersection(rect);
/*  960 */       x = rect.x;
/*  961 */       y = rect.y;
/*  962 */       width = rect.width;
/*  963 */       height = rect.height;
/*      */     } 
/*  966 */     byte[] data = ImageUtil.getPackedBinaryData(this.theImage.getData(), rect);
/*  972 */     int lineStride = (width + 7) / 8;
/*  973 */     int leftover = width % 8;
/*  974 */     int mask = (leftover == 0) ? 255 : ((1 << leftover) - 1 << 8 - leftover);
/*  977 */     LinkedList rectList = new LinkedList();
/*  984 */     for (int row = 0, k = 0; row < height; row++) {
/*  986 */       int start = -1;
/*      */       int col, cnt;
/*  988 */       for (col = 0, cnt = 0; col < lineStride; col++, k++) {
/*  990 */         int val = data[k] & ((col == lineStride - 1) ? mask : 255);
/*  992 */         if (val == 0) {
/*  993 */           if (start >= 0) {
/*  994 */             rectList.addLast(new Rectangle(x + start, y + row, col * 8 - start, 1));
/*  996 */             start = -1;
/*      */           } 
/*  999 */         } else if (val == 255) {
/* 1000 */           if (start < 0)
/* 1001 */             start = col * 8; 
/*      */         } else {
/* 1005 */           for (int bit = 7; bit >= 0; bit--) {
/* 1006 */             if ((val & 1 << bit) == 0) {
/* 1007 */               if (start >= 0) {
/* 1008 */                 rectList.addLast(new Rectangle(x + start, y + row, col * 8 + 7 - bit - start, 1));
/* 1010 */                 start = -1;
/*      */               } 
/* 1013 */             } else if (start < 0) {
/* 1014 */               start = col * 8 + 7 - bit;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1021 */       if (start >= 0)
/* 1022 */         rectList.addLast(new Rectangle(x + start, y + row, col * 8 - start, 1)); 
/*      */     } 
/* 1028 */     return mergeRectangles ? mergeRunLengthList(rectList) : rectList;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 1037 */     out.defaultWriteObject();
/* 1038 */     if (this.theImage != null) {
/* 1039 */       out.writeBoolean(true);
/* 1040 */       RenderingHints hints = new RenderingHints(null);
/* 1041 */       hints.put(JAI.KEY_SERIALIZE_DEEP_COPY, new Boolean(true));
/* 1042 */       out.writeObject(SerializerFactory.getState(this.theImage, hints));
/*      */     } else {
/* 1044 */       out.writeBoolean(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1055 */     in.defaultReadObject();
/* 1056 */     if (in.readBoolean()) {
/* 1057 */       SerializableState ss = (SerializableState)in.readObject();
/* 1058 */       RenderedImage ri = (RenderedImage)ss.getObject();
/* 1059 */       this.theImage = PlanarImage.wrapRenderedImage(ri);
/*      */     } else {
/* 1061 */       this.theImage = null;
/*      */     } 
/* 1063 */     this.iter = null;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ROI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */