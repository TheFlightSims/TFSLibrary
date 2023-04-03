/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import javax.media.jai.CollectionImage;
/*     */ import javax.media.jai.CollectionImageFactory;
/*     */ import javax.media.jai.CollectionOp;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class AddConstToCollectionCIF implements CollectionImageFactory {
/*     */   public CollectionImage create(ParameterBlock args, RenderingHints hints) {
/*  48 */     return new AddConstToCollectionOpImage((Collection)args.getSource(0), hints, (double[])args.getObjectParameter(0));
/*     */   }
/*     */   
/*     */   public CollectionImage update(ParameterBlock oldParamBlock, RenderingHints oldHints, ParameterBlock newParamBlock, RenderingHints newHints, CollectionImage oldRendering, CollectionOp op) {
/*  63 */     CollectionImage updatedCollection = null;
/*  65 */     if (oldParamBlock.getObjectParameter(0).equals(newParamBlock.getObjectParameter(0)) && ((oldHints == null) ? (newHints == null) : oldHints.equals(newHints))) {
/*  69 */       Collection oldSource = (Collection)oldParamBlock.getSource(0);
/*  70 */       Collection newSource = (Collection)newParamBlock.getSource(0);
/*  71 */       double[] constants = (double[])oldParamBlock.getObjectParameter(0);
/*  74 */       Collection commonSources = new ArrayList();
/*  75 */       Iterator it = oldSource.iterator();
/*  76 */       while (it.hasNext()) {
/*  77 */         Object oldElement = it.next();
/*  78 */         if (newSource.contains(oldElement))
/*  79 */           commonSources.add(oldElement); 
/*     */       } 
/*  83 */       if (commonSources.size() != 0) {
/*  86 */         ArrayList commonNodes = new ArrayList(commonSources.size());
/*  87 */         it = oldRendering.iterator();
/*  88 */         while (it.hasNext()) {
/*  89 */           RenderedOp node = it.next();
/*  90 */           PlanarImage source = node.getSourceImage(0);
/*  91 */           if (commonSources.contains(source))
/*  92 */             commonNodes.add(node); 
/*     */         } 
/*  97 */         updatedCollection = new AddConstToCollectionOpImage(newSource, newHints, constants);
/* 103 */         ArrayList newNodes = new ArrayList(oldRendering.size() - commonSources.size());
/* 105 */         it = updatedCollection.iterator();
/* 106 */         while (it.hasNext()) {
/* 107 */           RenderedOp node = it.next();
/* 108 */           PlanarImage source = node.getSourceImage(0);
/* 109 */           if (commonSources.contains(source))
/* 110 */             it.remove(); 
/*     */         } 
/* 115 */         it = commonNodes.iterator();
/* 116 */         while (it.hasNext())
/* 117 */           updatedCollection.add(it.next()); 
/*     */       } 
/*     */     } 
/* 122 */     return updatedCollection;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AddConstToCollectionCIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */