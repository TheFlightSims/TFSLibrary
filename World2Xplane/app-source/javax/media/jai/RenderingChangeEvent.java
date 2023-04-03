/*    */ package javax.media.jai;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ 
/*    */ public class RenderingChangeEvent extends PropertyChangeEventJAI {
/*    */   private Shape invalidRegion;
/*    */   
/*    */   public RenderingChangeEvent(RenderedOp source, PlanarImage oldRendering, PlanarImage newRendering, Shape invalidRegion) {
/* 45 */     super(source, "Rendering", oldRendering, newRendering);
/* 46 */     this.invalidRegion = invalidRegion;
/*    */   }
/*    */   
/*    */   public Shape getInvalidRegion() {
/* 59 */     return this.invalidRegion;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RenderingChangeEvent.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */