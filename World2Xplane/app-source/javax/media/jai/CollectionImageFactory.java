package javax.media.jai;

import java.awt.RenderingHints;
import java.awt.image.renderable.ParameterBlock;

public interface CollectionImageFactory {
  CollectionImage create(ParameterBlock paramParameterBlock, RenderingHints paramRenderingHints);
  
  CollectionImage update(ParameterBlock paramParameterBlock1, RenderingHints paramRenderingHints1, ParameterBlock paramParameterBlock2, RenderingHints paramRenderingHints2, CollectionImage paramCollectionImage, CollectionOp paramCollectionOp);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\CollectionImageFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */