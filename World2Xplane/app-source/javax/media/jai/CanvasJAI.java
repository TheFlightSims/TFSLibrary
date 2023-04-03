/*    */ package javax.media.jai;
/*    */ 
/*    */ import java.awt.Canvas;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.GraphicsConfiguration;
/*    */ 
/*    */ public class CanvasJAI extends Canvas {
/*    */   public CanvasJAI(GraphicsConfiguration config) {
/* 41 */     super(config);
/*    */   }
/*    */   
/*    */   public Graphics getGraphics() {
/* 49 */     Graphics2D g = (Graphics2D)super.getGraphics();
/* 50 */     return GraphicsJAI.createGraphicsJAI(g, this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\CanvasJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */