/*    */ package com.sun.media.jai.rmi;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.image.renderable.RenderContext;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import javax.media.jai.remote.SerializableState;
/*    */ import javax.media.jai.remote.SerializerFactory;
/*    */ 
/*    */ public class RenderContextState extends SerializableStateImpl {
/*    */   public static Class[] getSupportedClasses() {
/* 36 */     return new Class[] { RenderContext.class };
/*    */   }
/*    */   
/*    */   public RenderContextState(Class c, Object o, RenderingHints h) {
/* 48 */     super(c, o, h);
/*    */   }
/*    */   
/*    */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 58 */     RenderContext renderContext = (RenderContext)this.theObject;
/* 61 */     AffineTransform usr2dev = renderContext.getTransform();
/* 64 */     RenderingHints hints = renderContext.getRenderingHints();
/* 67 */     Shape aoi = renderContext.getAreaOfInterest();
/* 70 */     out.writeObject(usr2dev);
/* 71 */     out.writeObject(SerializerFactory.getState(aoi));
/* 72 */     out.writeObject(SerializerFactory.getState(hints, null));
/*    */   }
/*    */   
/*    */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 83 */     RenderContext renderContext = null;
/* 86 */     AffineTransform usr2dev = (AffineTransform)in.readObject();
/* 88 */     SerializableState aoi = (SerializableState)in.readObject();
/* 89 */     Shape shape = (Shape)aoi.getObject();
/* 91 */     SerializableState rhs = (SerializableState)in.readObject();
/* 92 */     RenderingHints hints = (RenderingHints)rhs.getObject();
/* 95 */     renderContext = new RenderContext(usr2dev, shape, hints);
/* 96 */     this.theObject = renderContext;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RenderContextState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */