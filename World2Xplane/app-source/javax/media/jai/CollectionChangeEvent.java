/*    */ package javax.media.jai;
/*    */ 
/*    */ import java.util.Collection;
/*    */ 
/*    */ public class CollectionChangeEvent extends PropertyChangeEventJAI {
/*    */   public CollectionChangeEvent(CollectionOp source, Collection oldValue, Collection newValue) {
/* 26 */     super(source, "Collection", oldValue, newValue);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\CollectionChangeEvent.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */