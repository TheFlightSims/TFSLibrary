/*    */ package javax.media.jai.operator;
/*    */ 
/*    */ import com.sun.media.jai.util.PropertyGeneratorImpl;
/*    */ import java.awt.Image;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.RenderableOp;
/*    */ import javax.media.jai.RenderedOp;
/*    */ 
/*    */ class IDFTPropertyGenerator extends PropertyGeneratorImpl {
/*    */   public IDFTPropertyGenerator() {
/* 38 */     super(new String[] { "COMPLEX" }, new Class[] { Boolean.class }, new Class[] { RenderedOp.class, RenderableOp.class });
/*    */   }
/*    */   
/*    */   public Object getProperty(String name, Object opNode) {
/* 51 */     validate(name, opNode);
/* 53 */     if (name.equalsIgnoreCase("complex")) {
/* 54 */       if (opNode instanceof RenderedOp) {
/* 55 */         RenderedOp op = (RenderedOp)opNode;
/* 56 */         ParameterBlock pb = op.getParameterBlock();
/* 57 */         DFTDataNature dataNature = (DFTDataNature)pb.getObjectParameter(1);
/* 59 */         return dataNature.equals(IDFTDescriptor.COMPLEX_TO_REAL) ? Boolean.FALSE : Boolean.TRUE;
/*    */       } 
/* 61 */       if (opNode instanceof RenderableOp) {
/* 62 */         RenderableOp op = (RenderableOp)opNode;
/* 63 */         ParameterBlock pb = op.getParameterBlock();
/* 64 */         DFTDataNature dataNature = (DFTDataNature)pb.getObjectParameter(1);
/* 66 */         return dataNature.equals(IDFTDescriptor.COMPLEX_TO_REAL) ? Boolean.FALSE : Boolean.TRUE;
/*    */       } 
/*    */     } 
/* 71 */     return Image.UndefinedProperty;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\IDFTPropertyGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */