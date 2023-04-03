/*    */ package javax.media.jai.operator;
/*    */ 
/*    */ import com.sun.media.jai.util.PropertyGeneratorImpl;
/*    */ import java.awt.Image;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.ImageFunction;
/*    */ import javax.media.jai.RenderableOp;
/*    */ import javax.media.jai.RenderedOp;
/*    */ 
/*    */ class ImageFunctionPropertyGenerator extends PropertyGeneratorImpl {
/*    */   public ImageFunctionPropertyGenerator() {
/* 35 */     super(new String[] { "COMPLEX" }, new Class[] { Boolean.class }, new Class[] { RenderedOp.class, RenderableOp.class });
/*    */   }
/*    */   
/*    */   public Object getProperty(String name, Object opNode) {
/* 48 */     validate(name, opNode);
/* 50 */     if (name.equalsIgnoreCase("complex")) {
/* 51 */       if (opNode instanceof RenderedOp) {
/* 52 */         RenderedOp op = (RenderedOp)opNode;
/* 53 */         ParameterBlock pb = op.getParameterBlock();
/* 54 */         ImageFunction imFunc = (ImageFunction)pb.getObjectParameter(0);
/* 55 */         return imFunc.isComplex() ? Boolean.TRUE : Boolean.FALSE;
/*    */       } 
/* 56 */       if (opNode instanceof RenderableOp) {
/* 57 */         RenderableOp op = (RenderableOp)opNode;
/* 58 */         ParameterBlock pb = op.getParameterBlock();
/* 59 */         ImageFunction imFunc = (ImageFunction)pb.getObjectParameter(0);
/* 60 */         return imFunc.isComplex() ? Boolean.TRUE : Boolean.FALSE;
/*    */       } 
/*    */     } 
/* 64 */     return Image.UndefinedProperty;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ImageFunctionPropertyGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */