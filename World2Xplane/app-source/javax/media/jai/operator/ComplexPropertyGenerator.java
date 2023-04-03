/*    */ package javax.media.jai.operator;
/*    */ 
/*    */ import com.sun.media.jai.util.PropertyGeneratorImpl;
/*    */ import java.awt.Image;
/*    */ import javax.media.jai.RenderableOp;
/*    */ import javax.media.jai.RenderedOp;
/*    */ 
/*    */ class ComplexPropertyGenerator extends PropertyGeneratorImpl {
/*    */   public ComplexPropertyGenerator() {
/* 26 */     super(new String[] { "COMPLEX" }, new Class[] { Boolean.class }, new Class[] { RenderedOp.class, RenderableOp.class });
/*    */   }
/*    */   
/*    */   public Object getProperty(String name, Object op) {
/* 39 */     validate(name, op);
/* 41 */     return name.equalsIgnoreCase("complex") ? Boolean.TRUE : Image.UndefinedProperty;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ComplexPropertyGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */