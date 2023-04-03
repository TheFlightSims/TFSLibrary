/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.media.jai.util.Rational;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.util.Map;
/*      */ 
/*      */ public abstract class ScaleOpImage extends GeometricOpImage {
/*      */   protected float scaleX;
/*      */   
/*      */   protected float scaleY;
/*      */   
/*      */   protected float transX;
/*      */   
/*      */   protected float transY;
/*      */   
/*      */   protected Rational scaleXRational;
/*      */   
/*      */   protected Rational scaleYRational;
/*      */   
/*      */   protected long scaleXRationalNum;
/*      */   
/*      */   protected long scaleXRationalDenom;
/*      */   
/*      */   protected long scaleYRationalNum;
/*      */   
/*      */   protected long scaleYRationalDenom;
/*      */   
/*      */   protected Rational invScaleXRational;
/*      */   
/*      */   protected Rational invScaleYRational;
/*      */   
/*      */   protected long invScaleXRationalNum;
/*      */   
/*      */   protected long invScaleXRationalDenom;
/*      */   
/*      */   protected long invScaleYRationalNum;
/*      */   
/*      */   protected long invScaleYRationalDenom;
/*      */   
/*      */   protected Rational transXRational;
/*      */   
/*      */   protected Rational transYRational;
/*      */   
/*      */   protected long transXRationalNum;
/*      */   
/*      */   protected long transXRationalDenom;
/*      */   
/*      */   protected long transYRationalNum;
/*      */   
/*      */   protected long transYRationalDenom;
/*      */   
/*  140 */   protected static float rationalTolerance = 1.0E-6F;
/*      */   
/*      */   private int lpad;
/*      */   
/*      */   private int rpad;
/*      */   
/*      */   private int tpad;
/*      */   
/*      */   private int bpad;
/*      */   
/*      */   private static ImageLayout layoutHelper(RenderedImage source, float scaleX, float scaleY, float transX, float transY, Interpolation interp, ImageLayout il) {
/*  218 */     Rational scaleXRational = Rational.approximate(scaleX, rationalTolerance);
/*  221 */     Rational scaleYRational = Rational.approximate(scaleY, rationalTolerance);
/*  224 */     long scaleXRationalNum = scaleXRational.num;
/*  225 */     long scaleXRationalDenom = scaleXRational.denom;
/*  226 */     long scaleYRationalNum = scaleYRational.num;
/*  227 */     long scaleYRationalDenom = scaleYRational.denom;
/*  229 */     Rational transXRational = Rational.approximate(transX, rationalTolerance);
/*  232 */     Rational transYRational = Rational.approximate(transY, rationalTolerance);
/*  235 */     long transXRationalNum = transXRational.num;
/*  236 */     long transXRationalDenom = transXRational.denom;
/*  237 */     long transYRationalNum = transYRational.num;
/*  238 */     long transYRationalDenom = transYRational.denom;
/*  240 */     ImageLayout layout = (il == null) ? new ImageLayout() : (ImageLayout)il.clone();
/*  243 */     int x0 = source.getMinX();
/*  244 */     int y0 = source.getMinY();
/*  245 */     int w = source.getWidth();
/*  246 */     int h = source.getHeight();
/*  257 */     long dx0Num = x0;
/*  258 */     long dx0Denom = 1L;
/*  260 */     long dy0Num = y0;
/*  261 */     long dy0Denom = 1L;
/*  264 */     long dx1Num = (x0 + w);
/*  265 */     long dx1Denom = 1L;
/*  268 */     long dy1Num = (y0 + h);
/*  269 */     long dy1Denom = 1L;
/*  271 */     dx0Num *= scaleXRationalNum;
/*  272 */     dx0Denom *= scaleXRationalDenom;
/*  274 */     dy0Num *= scaleYRationalNum;
/*  275 */     dy0Denom *= scaleYRationalDenom;
/*  277 */     dx1Num *= scaleXRationalNum;
/*  278 */     dx1Denom *= scaleXRationalDenom;
/*  280 */     dy1Num *= scaleYRationalNum;
/*  281 */     dy1Denom *= scaleYRationalDenom;
/*  284 */     dx0Num = 2L * dx0Num - dx0Denom;
/*  285 */     dx0Denom *= 2L;
/*  287 */     dy0Num = 2L * dy0Num - dy0Denom;
/*  288 */     dy0Denom *= 2L;
/*  291 */     dx1Num = 2L * dx1Num - 3L * dx1Denom;
/*  292 */     dx1Denom *= 2L;
/*  294 */     dy1Num = 2L * dy1Num - 3L * dy1Denom;
/*  295 */     dy1Denom *= 2L;
/*  300 */     dx0Num = dx0Num * transXRationalDenom + transXRationalNum * dx0Denom;
/*  301 */     dx0Denom *= transXRationalDenom;
/*  304 */     dy0Num = dy0Num * transYRationalDenom + transYRationalNum * dy0Denom;
/*  305 */     dy0Denom *= transYRationalDenom;
/*  308 */     dx1Num = dx1Num * transXRationalDenom + transXRationalNum * dx1Denom;
/*  309 */     dx1Denom *= transXRationalDenom;
/*  312 */     dy1Num = dy1Num * transYRationalDenom + transYRationalNum * dy1Denom;
/*  313 */     dy1Denom *= transYRationalDenom;
/*  318 */     int l_x0 = Rational.ceil(dx0Num, dx0Denom);
/*  319 */     int l_y0 = Rational.ceil(dy0Num, dy0Denom);
/*  321 */     int l_x1 = Rational.ceil(dx1Num, dx1Denom);
/*  322 */     int l_y1 = Rational.ceil(dy1Num, dy1Denom);
/*  325 */     layout.setMinX(l_x0);
/*  326 */     layout.setMinY(l_y0);
/*  329 */     layout.setWidth(l_x1 - l_x0 + 1);
/*  330 */     layout.setHeight(l_y1 - l_y0 + 1);
/*  332 */     return layout;
/*      */   }
/*      */   
/*      */   private static Map configHelper(RenderedImage source, Map configuration, Interpolation interp) {
/*  339 */     Map config = configuration;
/*  343 */     if (ImageUtil.isBinary(source.getSampleModel()) && (interp == null || interp instanceof InterpolationNearest || interp instanceof InterpolationBilinear))
/*  349 */       if (configuration == null) {
/*  350 */         config = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*  356 */       } else if (!config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/*  357 */         RenderingHints hints = new RenderingHints(null);
/*  359 */         hints.putAll(configuration);
/*  360 */         config = hints;
/*  361 */         config.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.TRUE);
/*      */       }  
/*  367 */     return config;
/*      */   }
/*      */   
/*      */   public ScaleOpImage(RenderedImage source, ImageLayout layout, Map configuration, boolean cobbleSources, BorderExtender extender, Interpolation interp, float scaleX, float scaleY, float transX, float transY) {
/*  427 */     super(vectorize(source), layoutHelper(source, scaleX, scaleY, transX, transY, interp, layout), configHelper(source, configuration, interp), cobbleSources, extender, interp, (double[])null);
/*  436 */     this.scaleX = scaleX;
/*  437 */     this.scaleY = scaleY;
/*  438 */     this.transX = transX;
/*  439 */     this.transY = transY;
/*  445 */     this.scaleXRational = Rational.approximate(scaleX, rationalTolerance);
/*  446 */     this.scaleYRational = Rational.approximate(scaleY, rationalTolerance);
/*  448 */     this.scaleXRationalNum = this.scaleXRational.num;
/*  449 */     this.scaleXRationalDenom = this.scaleXRational.denom;
/*  450 */     this.scaleYRationalNum = this.scaleYRational.num;
/*  451 */     this.scaleYRationalDenom = this.scaleYRational.denom;
/*  453 */     this.transXRational = Rational.approximate(transX, rationalTolerance);
/*  454 */     this.transYRational = Rational.approximate(transY, rationalTolerance);
/*  456 */     this.transXRationalNum = this.transXRational.num;
/*  457 */     this.transXRationalDenom = this.transXRational.denom;
/*  458 */     this.transYRationalNum = this.transYRational.num;
/*  459 */     this.transYRationalDenom = this.transYRational.denom;
/*  462 */     this.invScaleXRational = new Rational(this.scaleXRational);
/*  463 */     this.invScaleXRational.invert();
/*  464 */     this.invScaleYRational = new Rational(this.scaleYRational);
/*  465 */     this.invScaleYRational.invert();
/*  466 */     this.invScaleXRationalNum = this.invScaleXRational.num;
/*  467 */     this.invScaleXRationalDenom = this.invScaleXRational.denom;
/*  468 */     this.invScaleYRationalNum = this.invScaleYRational.num;
/*  469 */     this.invScaleYRationalDenom = this.invScaleYRational.denom;
/*  471 */     this.lpad = interp.getLeftPadding();
/*  472 */     this.rpad = interp.getRightPadding();
/*  473 */     this.tpad = interp.getTopPadding();
/*  474 */     this.bpad = interp.getBottomPadding();
/*  476 */     if (extender == null) {
/*      */       long dx0Denom, dy0Denom, dx1Denom, dy1Denom;
/*  479 */       int x0 = source.getMinX();
/*  480 */       int y0 = source.getMinY();
/*  481 */       int w = source.getWidth();
/*  482 */       int h = source.getHeight();
/*  490 */       if (interp instanceof InterpolationNearest) {
/*  493 */         dx0Num = x0;
/*  494 */         dx0Denom = 1L;
/*  496 */         dy0Num = y0;
/*  497 */         dy0Denom = 1L;
/*  504 */         dx1Num = (x0 + w);
/*  505 */         dx1Denom = 1L;
/*  508 */         dy1Num = (y0 + h);
/*  509 */         dy1Denom = 1L;
/*      */       } else {
/*  514 */         dx0Num = (2 * x0 + 1);
/*  515 */         dx0Denom = 2L;
/*  517 */         dy0Num = (2 * y0 + 1);
/*  518 */         dy0Denom = 2L;
/*  522 */         dx1Num = (2 * x0 + 2 * w + 1);
/*  523 */         dx1Denom = 2L;
/*  525 */         dy1Num = (2 * y0 + 2 * h + 1);
/*  526 */         dy1Denom = 2L;
/*  529 */         dx0Num += dx0Denom * this.lpad;
/*  531 */         dy0Num += dy0Denom * this.tpad;
/*  534 */         dx1Num -= dx1Denom * this.rpad;
/*  536 */         dy1Num -= dy1Denom * this.bpad;
/*      */       } 
/*  542 */       dx0Num *= this.scaleXRationalNum;
/*  543 */       dx0Denom *= this.scaleXRationalDenom;
/*  546 */       long dx0Num = dx0Num * this.transXRationalDenom + this.transXRationalNum * dx0Denom;
/*  547 */       dx0Denom *= this.transXRationalDenom;
/*  550 */       dy0Num *= this.scaleYRationalNum;
/*  551 */       dy0Denom *= this.scaleYRationalDenom;
/*  554 */       long dy0Num = dy0Num * this.transYRationalDenom + this.transYRationalNum * dy0Denom;
/*  555 */       dy0Denom *= this.transYRationalDenom;
/*  558 */       dx1Num *= this.scaleXRationalNum;
/*  559 */       dx1Denom *= this.scaleXRationalDenom;
/*  562 */       long dx1Num = dx1Num * this.transXRationalDenom + this.transXRationalNum * dx1Denom;
/*  563 */       dx1Denom *= this.transXRationalDenom;
/*  566 */       dy1Num *= this.scaleYRationalNum;
/*  567 */       dy1Denom *= this.scaleYRationalDenom;
/*  570 */       long dy1Num = dy1Num * this.transYRationalDenom + this.transYRationalNum * dy1Denom;
/*  571 */       dy1Denom *= this.transYRationalDenom;
/*  578 */       dx0Num = 2L * dx0Num - dx0Denom;
/*  579 */       dx0Denom *= 2L;
/*  581 */       dy0Num = 2L * dy0Num - dy0Denom;
/*  582 */       dy0Denom *= 2L;
/*  584 */       int l_x0 = Rational.ceil(dx0Num, dx0Denom);
/*  585 */       int l_y0 = Rational.ceil(dy0Num, dy0Denom);
/*  588 */       dx1Num = 2L * dx1Num - dx1Denom;
/*  589 */       dx1Denom *= 2L;
/*  591 */       dy1Num = 2L * dy1Num - dy1Denom;
/*  592 */       dy1Denom *= 2L;
/*  594 */       int l_x1 = Rational.floor(dx1Num, dx1Denom);
/*  596 */       if (l_x1 * dx1Denom == dx1Num)
/*  597 */         l_x1--; 
/*  600 */       int l_y1 = Rational.floor(dy1Num, dy1Denom);
/*  601 */       if (l_y1 * dy1Denom == dy1Num)
/*  602 */         l_y1--; 
/*  605 */       this.computableBounds = new Rectangle(l_x0, l_y0, l_x1 - l_x0 + 1, l_y1 - l_y0 + 1);
/*      */     } else {
/*  610 */       this.computableBounds = getBounds();
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getLeftPadding() {
/*  622 */     return (this.interp == null) ? 0 : this.interp.getLeftPadding();
/*      */   }
/*      */   
/*      */   public int getRightPadding() {
/*  633 */     return (this.interp == null) ? 0 : this.interp.getRightPadding();
/*      */   }
/*      */   
/*      */   public int getTopPadding() {
/*  644 */     return (this.interp == null) ? 0 : this.interp.getTopPadding();
/*      */   }
/*      */   
/*      */   public int getBottomPadding() {
/*  655 */     return (this.interp == null) ? 0 : this.interp.getBottomPadding();
/*      */   }
/*      */   
/*      */   public Point2D mapDestPoint(Point2D destPt, int sourceIndex) {
/*  689 */     if (destPt == null)
/*  690 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  691 */     if (sourceIndex != 0)
/*  692 */       throw new IndexOutOfBoundsException(JaiI18N.getString("Generic1")); 
/*  695 */     Point2D pt = (Point2D)destPt.clone();
/*  697 */     pt.setLocation((destPt.getX() - this.transX + 0.5D) / this.scaleX - 0.5D, (destPt.getY() - this.transY + 0.5D) / this.scaleY - 0.5D);
/*  700 */     return pt;
/*      */   }
/*      */   
/*      */   public Point2D mapSourcePoint(Point2D sourcePt, int sourceIndex) {
/*  734 */     if (sourcePt == null)
/*  735 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  736 */     if (sourceIndex != 0)
/*  737 */       throw new IndexOutOfBoundsException(JaiI18N.getString("Generic1")); 
/*  740 */     Point2D pt = (Point2D)sourcePt.clone();
/*  742 */     pt.setLocation(this.scaleX * (sourcePt.getX() + 0.5D) + this.transX - 0.5D, this.scaleY * (sourcePt.getY() + 0.5D) + this.transY - 0.5D);
/*  745 */     return pt;
/*      */   }
/*      */   
/*      */   protected Rectangle forwardMapRect(Rectangle sourceRect, int sourceIndex) {
/*      */     long dx0Denom, dy0Denom, dx1Denom, dy1Denom;
/*  770 */     if (sourceRect == null)
/*  771 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  774 */     if (sourceIndex != 0)
/*  775 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/*  779 */     int x0 = sourceRect.x;
/*  780 */     int y0 = sourceRect.y;
/*  781 */     int w = sourceRect.width;
/*  782 */     int h = sourceRect.height;
/*  790 */     if (this.interp instanceof InterpolationNearest) {
/*  793 */       dx0Num = x0;
/*  794 */       dx0Denom = 1L;
/*  796 */       dy0Num = y0;
/*  797 */       dy0Denom = 1L;
/*  805 */       dx1Num = (x0 + w);
/*  806 */       dx1Denom = 1L;
/*  809 */       dy1Num = (y0 + h);
/*  810 */       dy1Denom = 1L;
/*      */     } else {
/*  815 */       dx0Num = (2 * x0 + 1);
/*  816 */       dx0Denom = 2L;
/*  818 */       dy0Num = (2 * y0 + 1);
/*  819 */       dy0Denom = 2L;
/*  823 */       dx1Num = (2 * x0 + 2 * w + 1);
/*  824 */       dx1Denom = 2L;
/*  826 */       dy1Num = (2 * y0 + 2 * h + 1);
/*  827 */       dy1Denom = 2L;
/*      */     } 
/*  833 */     dx0Num *= this.scaleXRationalNum;
/*  834 */     dx0Denom *= this.scaleXRationalDenom;
/*  837 */     dy0Num *= this.scaleYRationalNum;
/*  838 */     dy0Denom *= this.scaleYRationalDenom;
/*  841 */     dx1Num *= this.scaleXRationalNum;
/*  842 */     dx1Denom *= this.scaleXRationalDenom;
/*  845 */     dy1Num *= this.scaleYRationalNum;
/*  846 */     dy1Denom *= this.scaleYRationalDenom;
/*  851 */     long dx0Num = dx0Num * this.transXRationalDenom + this.transXRationalNum * dx0Denom;
/*  852 */     dx0Denom *= this.transXRationalDenom;
/*  855 */     long dy0Num = dy0Num * this.transYRationalDenom + this.transYRationalNum * dy0Denom;
/*  856 */     dy0Denom *= this.transYRationalDenom;
/*  859 */     long dx1Num = dx1Num * this.transXRationalDenom + this.transXRationalNum * dx1Denom;
/*  860 */     dx1Denom *= this.transXRationalDenom;
/*  863 */     long dy1Num = dy1Num * this.transYRationalDenom + this.transYRationalNum * dy1Denom;
/*  864 */     dy1Denom *= this.transYRationalDenom;
/*  870 */     dx0Num = 2L * dx0Num - dx0Denom;
/*  871 */     dx0Denom *= 2L;
/*  873 */     dy0Num = 2L * dy0Num - dy0Denom;
/*  874 */     dy0Denom *= 2L;
/*  876 */     int l_x0 = Rational.ceil(dx0Num, dx0Denom);
/*  877 */     int l_y0 = Rational.ceil(dy0Num, dy0Denom);
/*  880 */     dx1Num = 2L * dx1Num - dx1Denom;
/*  881 */     dx1Denom *= 2L;
/*  883 */     dy1Num = 2L * dy1Num - dy1Denom;
/*  884 */     dy1Denom *= 2L;
/*  886 */     int l_x1 = Rational.floor(dx1Num, dx1Denom);
/*  887 */     if (l_x1 * dx1Denom == dx1Num)
/*  888 */       l_x1--; 
/*  891 */     int l_y1 = Rational.floor(dy1Num, dy1Denom);
/*  892 */     if (l_y1 * dy1Denom == dy1Num)
/*  893 */       l_y1--; 
/*  896 */     return new Rectangle(l_x0, l_y0, l_x1 - l_x0 + 1, l_y1 - l_y0 + 1);
/*      */   }
/*      */   
/*      */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/*  920 */     if (destRect == null)
/*  921 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  924 */     if (sourceIndex != 0)
/*  925 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/*  929 */     int x0 = destRect.x;
/*  930 */     int y0 = destRect.y;
/*  931 */     int w = destRect.width;
/*  932 */     int h = destRect.height;
/*  940 */     long sx0Num = (x0 * 2 + 1);
/*  941 */     long sx0Denom = 2L;
/*  943 */     long sy0Num = (y0 * 2 + 1);
/*  944 */     long sy0Denom = 2L;
/*  952 */     long sx1Num = (2 * x0 + 2 * w - 1);
/*  953 */     long sx1Denom = 2L;
/*  956 */     long sy1Num = (2 * y0 + 2 * h - 1);
/*  957 */     long sy1Denom = 2L;
/*  960 */     sx0Num = sx0Num * this.transXRationalDenom - this.transXRationalNum * sx0Denom;
/*  961 */     sx0Denom *= this.transXRationalDenom;
/*  963 */     sy0Num = sy0Num * this.transYRationalDenom - this.transYRationalNum * sy0Denom;
/*  964 */     sy0Denom *= this.transYRationalDenom;
/*  966 */     sx1Num = sx1Num * this.transXRationalDenom - this.transXRationalNum * sx1Denom;
/*  967 */     sx1Denom *= this.transXRationalDenom;
/*  969 */     sy1Num = sy1Num * this.transYRationalDenom - this.transYRationalNum * sy1Denom;
/*  970 */     sy1Denom *= this.transYRationalDenom;
/*  975 */     sx0Num *= this.invScaleXRationalNum;
/*  976 */     sx0Denom *= this.invScaleXRationalDenom;
/*  978 */     sy0Num *= this.invScaleYRationalNum;
/*  979 */     sy0Denom *= this.invScaleYRationalDenom;
/*  981 */     sx1Num *= this.invScaleXRationalNum;
/*  982 */     sx1Denom *= this.invScaleXRationalDenom;
/*  984 */     sy1Num *= this.invScaleYRationalNum;
/*  985 */     sy1Denom *= this.invScaleYRationalDenom;
/*  987 */     int s_x0 = 0, s_y0 = 0, s_x1 = 0, s_y1 = 0;
/*  988 */     if (this.interp instanceof InterpolationNearest) {
/*  991 */       s_x0 = Rational.floor(sx0Num, sx0Denom);
/*  992 */       s_y0 = Rational.floor(sy0Num, sy0Denom);
/*  995 */       s_x1 = Rational.floor(sx1Num, sx1Denom);
/*  998 */       s_y1 = Rational.floor(sy1Num, sy1Denom);
/*      */     } else {
/* 1004 */       s_x0 = Rational.floor(2L * sx0Num - sx0Denom, 2L * sx0Denom);
/* 1007 */       s_y0 = Rational.floor(2L * sy0Num - sy0Denom, 2L * sy0Denom);
/* 1010 */       s_x1 = Rational.floor(2L * sx1Num - sx1Denom, 2L * sx1Denom);
/* 1013 */       s_y1 = Rational.floor(2L * sy1Num - sy1Denom, 2L * sy1Denom);
/*      */     } 
/* 1016 */     return new Rectangle(s_x0, s_y0, s_x1 - s_x0 + 1, s_y1 - s_y0 + 1);
/*      */   }
/*      */   
/*      */   public Raster computeTile(int tileX, int tileY) {
/* 1056 */     if (!this.cobbleSources)
/* 1057 */       return super.computeTile(tileX, tileY); 
/* 1061 */     int orgX = tileXToX(tileX);
/* 1062 */     int orgY = tileYToY(tileY);
/* 1065 */     WritableRaster dest = createWritableRaster(this.sampleModel, new Point(orgX, orgY));
/* 1068 */     Rectangle rect = new Rectangle(orgX, orgY, this.tileWidth, this.tileHeight);
/* 1072 */     Rectangle destRect = rect.intersection(this.computableBounds);
/* 1073 */     if (destRect.width <= 0 || destRect.height <= 0)
/* 1075 */       return dest; 
/* 1079 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 1080 */     Raster[] sources = new Raster[1];
/* 1087 */     PlanarImage source0 = getSource(0);
/* 1089 */     IntegerSequence srcXSplits = new IntegerSequence();
/* 1090 */     IntegerSequence srcYSplits = new IntegerSequence();
/* 1091 */     source0.getSplits(srcXSplits, srcYSplits, srcRect);
/* 1093 */     if (srcXSplits.getNumElements() == 1 && srcYSplits.getNumElements() == 1) {
/* 1098 */       if (this.extender == null) {
/* 1099 */         sources[0] = source0.getData(srcRect);
/*      */       } else {
/* 1101 */         sources[0] = source0.getExtendedData(srcRect, this.extender);
/*      */       } 
/* 1105 */       computeRect(sources, dest, destRect);
/*      */     } else {
/* 1110 */       int srcTileWidth = source0.getTileWidth();
/* 1111 */       int srcTileHeight = source0.getTileHeight();
/* 1113 */       srcYSplits.startEnumeration();
/* 1114 */       while (srcYSplits.hasMoreElements()) {
/* 1116 */         int ysplit = srcYSplits.nextElement();
/* 1118 */         srcXSplits.startEnumeration();
/* 1119 */         while (srcXSplits.hasMoreElements()) {
/* 1121 */           int xsplit = srcXSplits.nextElement();
/* 1124 */           Rectangle srcTile = new Rectangle(xsplit, ysplit, srcTileWidth, srcTileHeight);
/* 1130 */           Rectangle newSrcRect = srcRect.intersection(srcTile);
/* 1141 */           if (!(this.interp instanceof InterpolationNearest)) {
/* 1143 */             if (newSrcRect.width <= this.interp.getWidth()) {
/* 1152 */               Rectangle wSrcRect = new Rectangle();
/* 1155 */               wSrcRect.x = newSrcRect.x;
/* 1156 */               wSrcRect.y = newSrcRect.y - this.tpad - 1;
/* 1157 */               wSrcRect.width = 2 * (this.lpad + this.rpad + 1);
/* 1158 */               wSrcRect.height = newSrcRect.height + this.bpad + this.tpad + 2;
/* 1161 */               wSrcRect = wSrcRect.intersection(source0.getBounds());
/* 1164 */               Rectangle wDestRect = mapSourceRect(wSrcRect, 0);
/* 1171 */               wDestRect = wDestRect.intersection(destRect);
/* 1173 */               if (wDestRect.width > 0 && wDestRect.height > 0) {
/* 1176 */                 if (this.extender == null) {
/* 1177 */                   sources[0] = source0.getData(wSrcRect);
/*      */                 } else {
/* 1179 */                   sources[0] = source0.getExtendedData(wSrcRect, this.extender);
/*      */                 } 
/* 1185 */                 computeRect(sources, dest, wDestRect);
/*      */               } 
/*      */             } 
/* 1189 */             if (newSrcRect.height <= this.interp.getHeight()) {
/* 1198 */               Rectangle hSrcRect = new Rectangle();
/* 1201 */               hSrcRect.x = newSrcRect.x - this.lpad - 1;
/* 1202 */               hSrcRect.y = newSrcRect.y;
/* 1203 */               hSrcRect.width = newSrcRect.width + this.lpad + this.rpad + 2;
/* 1205 */               hSrcRect.height = 2 * (this.tpad + this.bpad + 1);
/* 1207 */               hSrcRect = hSrcRect.intersection(source0.getBounds());
/* 1210 */               Rectangle hDestRect = mapSourceRect(hSrcRect, 0);
/* 1217 */               hDestRect = hDestRect.intersection(destRect);
/* 1219 */               if (hDestRect.width > 0 && hDestRect.height > 0) {
/* 1222 */                 if (this.extender == null) {
/* 1223 */                   sources[0] = source0.getData(hSrcRect);
/*      */                 } else {
/* 1225 */                   sources[0] = source0.getExtendedData(hSrcRect, this.extender);
/*      */                 } 
/* 1231 */                 computeRect(sources, dest, hDestRect);
/*      */               } 
/*      */             } 
/*      */           } 
/* 1237 */           if (newSrcRect.width > 0 && newSrcRect.height > 0) {
/* 1241 */             Rectangle newDestRect = mapSourceRect(newSrcRect, 0);
/* 1246 */             newDestRect = newDestRect.intersection(destRect);
/* 1248 */             if (newDestRect.width > 0 && newDestRect.height > 0) {
/* 1252 */               if (this.extender == null) {
/* 1253 */                 sources[0] = source0.getData(newSrcRect);
/*      */               } else {
/* 1255 */                 sources[0] = source0.getExtendedData(newSrcRect, this.extender);
/*      */               } 
/* 1261 */               computeRect(sources, dest, newDestRect);
/*      */             } 
/* 1279 */             if (!(this.interp instanceof InterpolationNearest)) {
/* 1280 */               Rectangle RTSrcRect = new Rectangle();
/* 1284 */               RTSrcRect.x = newSrcRect.x + newSrcRect.width - 1 - this.rpad - this.lpad;
/* 1286 */               RTSrcRect.y = newSrcRect.y;
/* 1301 */               RTSrcRect.width = 2 * (this.lpad + this.rpad + 1);
/* 1302 */               RTSrcRect.height = newSrcRect.height;
/* 1304 */               Rectangle RTDestRect = mapSourceRect(RTSrcRect, 0);
/* 1307 */               RTDestRect = RTDestRect.intersection(destRect);
/* 1311 */               RTSrcRect = mapDestRect(RTDestRect, 0);
/* 1313 */               if (RTDestRect.width > 0 && RTDestRect.height > 0) {
/* 1316 */                 if (this.extender == null) {
/* 1317 */                   sources[0] = source0.getData(RTSrcRect);
/*      */                 } else {
/* 1319 */                   sources[0] = source0.getExtendedData(RTSrcRect, this.extender);
/*      */                 } 
/* 1324 */                 computeRect(sources, dest, RTDestRect);
/*      */               } 
/* 1328 */               Rectangle BTSrcRect = new Rectangle();
/* 1331 */               BTSrcRect.x = newSrcRect.x;
/* 1332 */               BTSrcRect.y = newSrcRect.y + newSrcRect.height - 1 - this.bpad - this.tpad;
/* 1348 */               BTSrcRect.width = newSrcRect.width;
/* 1349 */               BTSrcRect.height = 2 * (this.tpad + this.bpad + 1);
/* 1351 */               Rectangle BTDestRect = mapSourceRect(BTSrcRect, 0);
/* 1354 */               BTDestRect = BTDestRect.intersection(destRect);
/* 1358 */               BTSrcRect = mapDestRect(BTDestRect, 0);
/* 1361 */               if (BTDestRect.width > 0 && BTDestRect.height > 0) {
/* 1365 */                 if (this.extender == null) {
/* 1366 */                   sources[0] = source0.getData(BTSrcRect);
/*      */                 } else {
/* 1368 */                   sources[0] = source0.getExtendedData(BTSrcRect, this.extender);
/*      */                 } 
/* 1373 */                 computeRect(sources, dest, BTDestRect);
/*      */               } 
/* 1377 */               Rectangle LRTSrcRect = new Rectangle();
/* 1380 */               LRTSrcRect.x = newSrcRect.x + newSrcRect.width - 1 - this.rpad - this.lpad;
/* 1382 */               LRTSrcRect.y = newSrcRect.y + newSrcRect.height - 1 - this.bpad - this.tpad;
/* 1386 */               LRTSrcRect.width = 2 * (this.rpad + this.lpad + 1);
/* 1387 */               LRTSrcRect.height = 2 * (this.tpad + this.bpad + 1);
/* 1389 */               Rectangle LRTDestRect = mapSourceRect(LRTSrcRect, 0);
/* 1392 */               LRTDestRect = LRTDestRect.intersection(destRect);
/* 1395 */               LRTSrcRect = mapDestRect(LRTDestRect, 0);
/* 1397 */               if (LRTDestRect.width > 0 && LRTDestRect.height > 0) {
/* 1400 */                 if (this.extender == null) {
/* 1401 */                   sources[0] = source0.getData(LRTSrcRect);
/*      */                 } else {
/* 1403 */                   sources[0] = source0.getExtendedData(LRTSrcRect, this.extender);
/*      */                 } 
/* 1408 */                 computeRect(sources, dest, LRTDestRect);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1417 */     return dest;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ScaleOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */