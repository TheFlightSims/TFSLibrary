/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import javax.media.jai.remote.SerializableState;
/*     */ import javax.media.jai.remote.SerializerFactory;
/*     */ 
/*     */ public class ImageLayout implements Cloneable, Serializable {
/*     */   public static final int MIN_X_MASK = 1;
/*     */   
/*     */   public static final int MIN_Y_MASK = 2;
/*     */   
/*     */   public static final int WIDTH_MASK = 4;
/*     */   
/*     */   public static final int HEIGHT_MASK = 8;
/*     */   
/*     */   public static final int TILE_GRID_X_OFFSET_MASK = 16;
/*     */   
/*     */   public static final int TILE_GRID_Y_OFFSET_MASK = 32;
/*     */   
/*     */   public static final int TILE_WIDTH_MASK = 64;
/*     */   
/*     */   public static final int TILE_HEIGHT_MASK = 128;
/*     */   
/*     */   public static final int SAMPLE_MODEL_MASK = 256;
/*     */   
/*     */   public static final int COLOR_MODEL_MASK = 512;
/*     */   
/*  92 */   int minX = 0;
/*     */   
/*  95 */   int minY = 0;
/*     */   
/*  98 */   int width = 0;
/*     */   
/* 101 */   int height = 0;
/*     */   
/* 104 */   int tileGridXOffset = 0;
/*     */   
/* 107 */   int tileGridYOffset = 0;
/*     */   
/* 110 */   int tileWidth = 0;
/*     */   
/* 113 */   int tileHeight = 0;
/*     */   
/* 116 */   transient SampleModel sampleModel = null;
/*     */   
/* 119 */   transient ColorModel colorModel = null;
/*     */   
/* 122 */   protected int validMask = 0;
/*     */   
/*     */   public ImageLayout(int minX, int minY, int width, int height, int tileGridXOffset, int tileGridYOffset, int tileWidth, int tileHeight, SampleModel sampleModel, ColorModel colorModel) {
/* 152 */     setMinX(minX);
/* 153 */     setMinY(minY);
/* 154 */     setWidth(width);
/* 155 */     setHeight(height);
/* 156 */     setTileGridXOffset(tileGridXOffset);
/* 157 */     setTileGridYOffset(tileGridYOffset);
/* 158 */     setTileWidth(tileWidth);
/* 159 */     setTileHeight(tileHeight);
/* 160 */     if (sampleModel != null)
/* 161 */       setSampleModel(sampleModel); 
/* 163 */     if (colorModel != null)
/* 164 */       setColorModel(colorModel); 
/*     */   }
/*     */   
/*     */   public ImageLayout(int minX, int minY, int width, int height) {
/* 182 */     setMinX(minX);
/* 183 */     setMinY(minY);
/* 184 */     setWidth(width);
/* 185 */     setHeight(height);
/*     */   }
/*     */   
/*     */   public ImageLayout(int tileGridXOffset, int tileGridYOffset, int tileWidth, int tileHeight, SampleModel sampleModel, ColorModel colorModel) {
/* 206 */     setTileGridXOffset(tileGridXOffset);
/* 207 */     setTileGridYOffset(tileGridYOffset);
/* 208 */     setTileWidth(tileWidth);
/* 209 */     setTileHeight(tileHeight);
/* 210 */     if (sampleModel != null)
/* 211 */       setSampleModel(sampleModel); 
/* 213 */     if (colorModel != null)
/* 214 */       setColorModel(colorModel); 
/*     */   }
/*     */   
/*     */   public ImageLayout(RenderedImage im) {
/* 225 */     this(im.getMinX(), im.getMinY(), im.getWidth(), im.getHeight(), im.getTileGridXOffset(), im.getTileGridYOffset(), im.getTileWidth(), im.getTileHeight(), im.getSampleModel(), im.getColorModel());
/*     */   }
/*     */   
/*     */   public int getValidMask() {
/* 250 */     return this.validMask;
/*     */   }
/*     */   
/*     */   public final boolean isValid(int mask) {
/* 260 */     return ((this.validMask & mask) == mask);
/*     */   }
/*     */   
/*     */   public ImageLayout setValid(int mask) {
/* 271 */     this.validMask |= mask;
/* 272 */     return this;
/*     */   }
/*     */   
/*     */   public ImageLayout unsetValid(int mask) {
/* 286 */     this.validMask &= mask ^ 0xFFFFFFFF;
/* 287 */     return this;
/*     */   }
/*     */   
/*     */   public ImageLayout unsetImageBounds() {
/* 297 */     unsetValid(15);
/* 301 */     return this;
/*     */   }
/*     */   
/*     */   public ImageLayout unsetTileLayout() {
/* 311 */     unsetValid(240);
/* 315 */     return this;
/*     */   }
/*     */   
/*     */   public int getMinX(RenderedImage fallback) {
/* 327 */     if (isValid(1))
/* 328 */       return this.minX; 
/* 330 */     if (fallback == null)
/* 331 */       return 0; 
/* 333 */     return fallback.getMinX();
/*     */   }
/*     */   
/*     */   public ImageLayout setMinX(int minX) {
/* 345 */     this.minX = minX;
/* 346 */     setValid(1);
/* 347 */     return this;
/*     */   }
/*     */   
/*     */   public int getMinY(RenderedImage fallback) {
/* 359 */     if (isValid(2))
/* 360 */       return this.minY; 
/* 362 */     if (fallback == null)
/* 363 */       return 0; 
/* 365 */     return fallback.getMinY();
/*     */   }
/*     */   
/*     */   public ImageLayout setMinY(int minY) {
/* 377 */     this.minY = minY;
/* 378 */     setValid(2);
/* 379 */     return this;
/*     */   }
/*     */   
/*     */   public int getWidth(RenderedImage fallback) {
/* 391 */     if (isValid(4))
/* 392 */       return this.width; 
/* 394 */     if (fallback == null)
/* 395 */       return 0; 
/* 397 */     return fallback.getWidth();
/*     */   }
/*     */   
/*     */   public ImageLayout setWidth(int width) {
/* 410 */     if (width <= 0)
/* 411 */       throw new IllegalArgumentException(JaiI18N.getString("ImageLayout0")); 
/* 413 */     this.width = width;
/* 414 */     setValid(4);
/* 415 */     return this;
/*     */   }
/*     */   
/*     */   public int getHeight(RenderedImage fallback) {
/* 427 */     if (isValid(8))
/* 428 */       return this.height; 
/* 430 */     if (fallback == null)
/* 431 */       return 0; 
/* 433 */     return fallback.getHeight();
/*     */   }
/*     */   
/*     */   public ImageLayout setHeight(int height) {
/* 446 */     if (height <= 0)
/* 447 */       throw new IllegalArgumentException(JaiI18N.getString("ImageLayout0")); 
/* 449 */     this.height = height;
/* 450 */     setValid(8);
/* 451 */     return this;
/*     */   }
/*     */   
/*     */   public int getTileGridXOffset(RenderedImage fallback) {
/* 463 */     if (isValid(16))
/* 464 */       return this.tileGridXOffset; 
/* 466 */     if (fallback == null)
/* 467 */       return 0; 
/* 469 */     return fallback.getTileGridXOffset();
/*     */   }
/*     */   
/*     */   public ImageLayout setTileGridXOffset(int tileGridXOffset) {
/* 481 */     this.tileGridXOffset = tileGridXOffset;
/* 482 */     setValid(16);
/* 483 */     return this;
/*     */   }
/*     */   
/*     */   public int getTileGridYOffset(RenderedImage fallback) {
/* 495 */     if (isValid(32))
/* 496 */       return this.tileGridYOffset; 
/* 498 */     if (fallback == null)
/* 499 */       return 0; 
/* 501 */     return fallback.getTileGridYOffset();
/*     */   }
/*     */   
/*     */   public ImageLayout setTileGridYOffset(int tileGridYOffset) {
/* 513 */     this.tileGridYOffset = tileGridYOffset;
/* 514 */     setValid(32);
/* 515 */     return this;
/*     */   }
/*     */   
/*     */   public int getTileWidth(RenderedImage fallback) {
/* 527 */     if (isValid(64))
/* 528 */       return this.tileWidth; 
/* 530 */     if (fallback == null)
/* 531 */       return 0; 
/* 533 */     return fallback.getTileWidth();
/*     */   }
/*     */   
/*     */   public ImageLayout setTileWidth(int tileWidth) {
/* 547 */     if (tileWidth <= 0)
/* 548 */       throw new IllegalArgumentException(JaiI18N.getString("ImageLayout0")); 
/* 550 */     this.tileWidth = tileWidth;
/* 551 */     setValid(64);
/* 552 */     return this;
/*     */   }
/*     */   
/*     */   public int getTileHeight(RenderedImage fallback) {
/* 564 */     if (isValid(128))
/* 565 */       return this.tileHeight; 
/* 567 */     if (fallback == null)
/* 568 */       return 0; 
/* 570 */     return fallback.getTileHeight();
/*     */   }
/*     */   
/*     */   public ImageLayout setTileHeight(int tileHeight) {
/* 584 */     if (tileHeight <= 0)
/* 585 */       throw new IllegalArgumentException(JaiI18N.getString("ImageLayout0")); 
/* 587 */     this.tileHeight = tileHeight;
/* 588 */     setValid(128);
/* 589 */     return this;
/*     */   }
/*     */   
/*     */   public SampleModel getSampleModel(RenderedImage fallback) {
/* 601 */     if (isValid(256))
/* 602 */       return this.sampleModel; 
/* 604 */     if (fallback == null)
/* 605 */       return null; 
/* 607 */     return fallback.getSampleModel();
/*     */   }
/*     */   
/*     */   public ImageLayout setSampleModel(SampleModel sampleModel) {
/* 619 */     this.sampleModel = sampleModel;
/* 620 */     setValid(256);
/* 621 */     return this;
/*     */   }
/*     */   
/*     */   public ColorModel getColorModel(RenderedImage fallback) {
/* 633 */     if (isValid(512))
/* 634 */       return this.colorModel; 
/* 636 */     if (fallback == null)
/* 637 */       return null; 
/* 639 */     return fallback.getColorModel();
/*     */   }
/*     */   
/*     */   public ImageLayout setColorModel(ColorModel colorModel) {
/* 651 */     this.colorModel = colorModel;
/* 652 */     setValid(512);
/* 653 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 658 */     String s = "ImageLayout[";
/* 659 */     boolean first = true;
/* 661 */     if (isValid(1)) {
/* 662 */       s = s + "MIN_X=" + this.minX;
/* 663 */       first = false;
/*     */     } 
/* 666 */     if (isValid(2)) {
/* 667 */       if (!first)
/* 668 */         s = s + ", "; 
/* 670 */       s = s + "MIN_Y=" + this.minY;
/* 671 */       first = false;
/*     */     } 
/* 674 */     if (isValid(4)) {
/* 675 */       if (!first)
/* 676 */         s = s + ", "; 
/* 678 */       s = s + "WIDTH=" + this.width;
/* 679 */       first = false;
/*     */     } 
/* 682 */     if (isValid(8)) {
/* 683 */       if (!first)
/* 684 */         s = s + ", "; 
/* 686 */       s = s + "HEIGHT=" + this.height;
/* 687 */       first = false;
/*     */     } 
/* 690 */     if (isValid(16)) {
/* 691 */       if (!first)
/* 692 */         s = s + ", "; 
/* 694 */       s = s + "TILE_GRID_X_OFFSET=" + this.tileGridXOffset;
/* 695 */       first = false;
/*     */     } 
/* 698 */     if (isValid(32)) {
/* 699 */       if (!first)
/* 700 */         s = s + ", "; 
/* 702 */       s = s + "TILE_GRID_Y_OFFSET=" + this.tileGridYOffset;
/* 703 */       first = false;
/*     */     } 
/* 706 */     if (isValid(64)) {
/* 707 */       if (!first)
/* 708 */         s = s + ", "; 
/* 710 */       s = s + "TILE_WIDTH=" + this.tileWidth;
/* 711 */       first = false;
/*     */     } 
/* 714 */     if (isValid(128)) {
/* 715 */       if (!first)
/* 716 */         s = s + ", "; 
/* 718 */       s = s + "TILE_HEIGHT=" + this.tileHeight;
/* 719 */       first = false;
/*     */     } 
/* 722 */     if (isValid(256)) {
/* 723 */       if (!first)
/* 724 */         s = s + ", "; 
/* 726 */       s = s + "SAMPLE_MODEL=" + this.sampleModel;
/* 727 */       first = false;
/*     */     } 
/* 730 */     if (isValid(512)) {
/* 731 */       if (!first)
/* 732 */         s = s + ", "; 
/* 734 */       s = s + "COLOR_MODEL=" + this.colorModel;
/*     */     } 
/* 737 */     s = s + "]";
/* 738 */     return s;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 746 */       return super.clone();
/* 747 */     } catch (CloneNotSupportedException e) {
/* 748 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 758 */     out.defaultWriteObject();
/* 761 */     if (isValid(256))
/* 762 */       out.writeObject(SerializerFactory.getState(this.sampleModel, null)); 
/* 766 */     if (isValid(512))
/* 767 */       out.writeObject(SerializerFactory.getState(this.colorModel, null)); 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 778 */     in.defaultReadObject();
/* 781 */     if (isValid(256)) {
/* 782 */       Object object = in.readObject();
/* 783 */       if (!(object instanceof SerializableState))
/* 784 */         this.sampleModel = null; 
/* 786 */       SerializableState ss = (SerializableState)object;
/* 787 */       Class c = ss.getObjectClass();
/* 788 */       if (SampleModel.class.isAssignableFrom(c)) {
/* 789 */         this.sampleModel = (SampleModel)ss.getObject();
/*     */       } else {
/* 791 */         this.sampleModel = null;
/*     */       } 
/*     */     } 
/* 795 */     if (isValid(512)) {
/* 796 */       Object object = in.readObject();
/* 797 */       if (!(object instanceof SerializableState))
/* 798 */         this.colorModel = null; 
/* 800 */       SerializableState ss = (SerializableState)object;
/* 801 */       Class c = ss.getObjectClass();
/* 802 */       if (ColorModel.class.isAssignableFrom(c)) {
/* 803 */         this.colorModel = (ColorModel)ss.getObject();
/*     */       } else {
/* 805 */         this.colorModel = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 823 */     if (this == obj)
/* 824 */       return true; 
/* 826 */     if (!(obj instanceof ImageLayout))
/* 827 */       return false; 
/* 829 */     ImageLayout il = (ImageLayout)obj;
/* 831 */     return (this.validMask == il.validMask && this.width == il.width && this.height == il.height && this.minX == il.minX && this.minY == il.minY && this.tileHeight == il.tileHeight && this.tileWidth == il.tileWidth && this.tileGridXOffset == il.tileGridXOffset && this.tileGridYOffset == il.tileGridYOffset && this.sampleModel.equals(il.sampleModel) && this.colorModel.equals(il.colorModel));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 853 */     int code = 0, i = 1;
/* 857 */     code += this.width * i++;
/* 858 */     code += this.height * i++;
/* 859 */     code += this.minX * i++;
/* 860 */     code += this.minY * i++;
/* 861 */     code += this.tileHeight * i++;
/* 862 */     code += this.tileWidth * i++;
/* 863 */     code += this.tileGridXOffset * i++;
/* 864 */     code += this.tileGridYOffset * i++;
/* 866 */     code ^= this.sampleModel.hashCode();
/* 867 */     code ^= this.validMask;
/* 868 */     code ^= this.colorModel.hashCode();
/* 870 */     return code;
/*     */   }
/*     */   
/*     */   public ImageLayout() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ImageLayout.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */