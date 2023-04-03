/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Vector;
/*    */ import javax.media.jai.CollectionImage;
/*    */ import javax.media.jai.JAI;
/*    */ 
/*    */ final class AddConstToCollectionOpImage extends CollectionImage {
/*    */   public AddConstToCollectionOpImage(Collection sourceCollection, RenderingHints hints, double[] constants) {
/*    */     try {
/* 49 */       this.imageCollection = (Collection)sourceCollection.getClass().newInstance();
/* 51 */     } catch (Exception e) {
/* 52 */       this.imageCollection = new Vector();
/*    */     } 
/* 55 */     Iterator iter = sourceCollection.iterator();
/* 56 */     while (iter.hasNext()) {
/* 57 */       ParameterBlock pb = new ParameterBlock();
/* 58 */       pb.addSource(iter.next());
/* 59 */       pb.add(constants);
/* 61 */       this.imageCollection.add(JAI.create("AddConst", pb, hints));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AddConstToCollectionOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */