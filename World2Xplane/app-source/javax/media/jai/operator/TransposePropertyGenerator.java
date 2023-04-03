/*    */ package javax.media.jai.operator;
/*    */ 
/*    */ import com.sun.media.jai.util.PropertyGeneratorImpl;
/*    */ import java.awt.Image;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.Interpolation;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.PlanarImage;
/*    */ import javax.media.jai.ROI;
/*    */ import javax.media.jai.RenderedOp;
/*    */ 
/*    */ class TransposePropertyGenerator extends PropertyGeneratorImpl {
/*    */   public TransposePropertyGenerator() {
/* 41 */     super(new String[] { "ROI" }, new Class[] { ROI.class }, new Class[] { RenderedOp.class });
/*    */   }
/*    */   
/*    */   public Object getProperty(String name, Object opNode) {
/* 54 */     validate(name, opNode);
/* 56 */     if (opNode instanceof RenderedOp && name.equalsIgnoreCase("roi")) {
/* 58 */       RenderedOp op = (RenderedOp)opNode;
/* 60 */       ParameterBlock pb = op.getParameterBlock();
/* 63 */       PlanarImage src = (PlanarImage)pb.getRenderedSource(0);
/* 64 */       Object property = src.getProperty("ROI");
/* 65 */       if (property == null || property.equals(Image.UndefinedProperty) || !(property instanceof ROI))
/* 68 */         return Image.UndefinedProperty; 
/* 72 */       ROI srcROI = (ROI)property;
/* 73 */       if (srcROI.getBounds().isEmpty())
/* 74 */         return Image.UndefinedProperty; 
/* 83 */       TransposeType transposeType = (TransposeType)pb.getObjectParameter(0);
/* 85 */       Interpolation interp = Interpolation.getInstance(0);
/* 89 */       return new ROI((RenderedImage)JAI.create("transpose", (RenderedImage)srcROI.getAsImage(), transposeType));
/*    */     } 
/* 93 */     return Image.UndefinedProperty;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\TransposePropertyGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */