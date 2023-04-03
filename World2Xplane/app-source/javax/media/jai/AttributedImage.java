/*    */ package javax.media.jai;
/*    */ 
/*    */ public class AttributedImage extends RenderedImageAdapter {
/*    */   protected Object attribute;
/*    */   
/*    */   public AttributedImage(PlanarImage image, Object attribute) {
/* 33 */     super(image);
/* 34 */     this.attribute = attribute;
/*    */   }
/*    */   
/*    */   public PlanarImage getImage() {
/* 39 */     return (PlanarImage)this.theImage;
/*    */   }
/*    */   
/*    */   public void setAttribute(Object attribute) {
/* 44 */     this.attribute = attribute;
/*    */   }
/*    */   
/*    */   public Object getAttribute() {
/* 49 */     return this.attribute;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 60 */     if (o != null && o instanceof AttributedImage) {
/* 61 */       AttributedImage ai = (AttributedImage)o;
/* 62 */       Object a = ai.getAttribute();
/* 63 */       return (getImage().equals(ai.getImage()) && ((this.attribute == null) ? (a == null) : (a != null && this.attribute.equals(a))));
/*    */     } 
/* 68 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 73 */     return "Attribute=(" + getAttribute() + ")  Image=" + getImage();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\AttributedImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */