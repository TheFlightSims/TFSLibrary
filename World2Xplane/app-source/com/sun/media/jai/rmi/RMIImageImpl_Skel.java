package com.sun.media.jai.rmi;

import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.rmi.MarshalException;
import java.rmi.Remote;
import java.rmi.UnmarshalException;
import java.rmi.server.Operation;
import java.rmi.server.RemoteCall;
import java.rmi.server.Skeleton;
import java.rmi.server.SkeletonMismatchException;
import java.util.Vector;
import javax.media.jai.RenderableOp;
import javax.media.jai.RenderedOp;

public final class RMIImageImpl_Skel implements Skeleton {
  private static final Operation[] operations = new Operation[] { 
      new Operation("com.sun.media.jai.rmi.RasterProxy copyData(java.lang.Long, java.awt.Rectangle)"), new Operation("void dispose(java.lang.Long)"), new Operation("com.sun.media.jai.rmi.ColorModelProxy getColorModel(java.lang.Long)"), new Operation("com.sun.media.jai.rmi.RasterProxy getData(java.lang.Long)"), new Operation("com.sun.media.jai.rmi.RasterProxy getData(java.lang.Long, java.awt.Rectangle)"), new Operation("int getHeight(java.lang.Long)"), new Operation("int getMinTileX(java.lang.Long)"), new Operation("int getMinTileY(java.lang.Long)"), new Operation("int getMinX(java.lang.Long)"), new Operation("int getMinY(java.lang.Long)"), 
      new Operation("int getNumXTiles(java.lang.Long)"), new Operation("int getNumYTiles(java.lang.Long)"), new Operation("java.lang.Object getProperty(java.lang.Long, java.lang.String)"), new Operation("java.lang.String getPropertyNames(java.lang.Long)[]"), new Operation("java.lang.Long getRemoteID()"), new Operation("com.sun.media.jai.rmi.SampleModelProxy getSampleModel(java.lang.Long)"), new Operation("java.util.Vector getSources(java.lang.Long)"), new Operation("com.sun.media.jai.rmi.RasterProxy getTile(java.lang.Long, int, int)"), new Operation("int getTileGridXOffset(java.lang.Long)"), new Operation("int getTileGridYOffset(java.lang.Long)"), 
      new Operation("int getTileHeight(java.lang.Long)"), new Operation("int getTileWidth(java.lang.Long)"), new Operation("int getWidth(java.lang.Long)"), new Operation("void setSource(java.lang.Long, java.awt.image.RenderedImage)"), new Operation("void setSource(java.lang.Long, javax.media.jai.RenderableOp, com.sun.media.jai.rmi.RenderContextProxy)"), new Operation("void setSource(java.lang.Long, javax.media.jai.RenderedOp)") };
  
  private static final long interfaceHash = -9186133247174212020L;
  
  public void dispatch(Remote paramRemote, RemoteCall paramRemoteCall, int paramInt, long paramLong) throws Exception {
    Long long_;
    RasterProxy rasterProxy;
    int j;
    Vector vector;
    int i;
    RenderedImage renderedImage;
    RenderableOp renderableOp;
    RenderedOp renderedOp;
    Object object;
    int k;
    if (paramInt < 0) {
      if (paramLong == -4480130102587337594L) {
        paramInt = 0;
      } else if (paramLong == 6460799139781649959L) {
        paramInt = 1;
      } else if (paramLong == 5862232465831048388L) {
        paramInt = 2;
      } else if (paramLong == 5982474592659170320L) {
        paramInt = 3;
      } else if (paramLong == -7782001095732779284L) {
        paramInt = 4;
      } else if (paramLong == -7560603472052038977L) {
        paramInt = 5;
      } else if (paramLong == 5809966745410438246L) {
        paramInt = 6;
      } else if (paramLong == -9076617268613815876L) {
        paramInt = 7;
      } else if (paramLong == -5297535099750447733L) {
        paramInt = 8;
      } else if (paramLong == 7733459005376369327L) {
        paramInt = 9;
      } else if (paramLong == 3645100420184954761L) {
        paramInt = 10;
      } else if (paramLong == -1731091968647972742L) {
        paramInt = 11;
      } else if (paramLong == 216968610676295195L) {
        paramInt = 12;
      } else if (paramLong == 3931591828613160321L) {
        paramInt = 13;
      } else if (paramLong == -232353888923603427L) {
        paramInt = 14;
      } else if (paramLong == -8396533149827190655L) {
        paramInt = 15;
      } else if (paramLong == -3713513808775692904L) {
        paramInt = 16;
      } else if (paramLong == -1008030285235108860L) {
        paramInt = 17;
      } else if (paramLong == -8218495432205133449L) {
        paramInt = 18;
      } else if (paramLong == -7482127068346373541L) {
        paramInt = 19;
      } else if (paramLong == 7785669351714030715L) {
        paramInt = 20;
      } else if (paramLong == 282122131312695349L) {
        paramInt = 21;
      } else if (paramLong == -8357318297729299690L) {
        paramInt = 22;
      } else if (paramLong == 4248763766578677765L) {
        paramInt = 23;
      } else if (paramLong == 7010328997687947687L) {
        paramInt = 24;
      } else if (paramLong == -4039999355356694323L) {
        paramInt = 25;
      } else {
        throw new UnmarshalException("invalid method hash");
      } 
    } else if (paramLong != -9186133247174212020L) {
      throw new SkeletonMismatchException("interface hash mismatch");
    } 
    RMIImageImpl rMIImageImpl = (RMIImageImpl)paramRemote;
    switch (paramInt) {
      case 0:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
          null = (Rectangle)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = rMIImageImpl.copyData(long_, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 1:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        rMIImageImpl.dispose(long_);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 2:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = rMIImageImpl.getColorModel(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 3:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        rasterProxy = rMIImageImpl.getData(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(rasterProxy);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 4:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
          null = (Rectangle)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = rMIImageImpl.getData(long_, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 5:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = rMIImageImpl.getHeight(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 6:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = rMIImageImpl.getMinTileX(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 7:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = rMIImageImpl.getMinTileY(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 8:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = rMIImageImpl.getMinX(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 9:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = rMIImageImpl.getMinY(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 10:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = rMIImageImpl.getNumXTiles(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 11:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = rMIImageImpl.getNumYTiles(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 12:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
          null = (String)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        object = rMIImageImpl.getProperty(long_, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(object);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 13:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = rMIImageImpl.getPropertyNames(long_);
        try {
          object = paramRemoteCall.getResultStream(true);
          object.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 14:
        paramRemoteCall.releaseInputStream();
        long_ = rMIImageImpl.getRemoteID();
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(long_);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 15:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = rMIImageImpl.getSampleModel(long_);
        try {
          object = paramRemoteCall.getResultStream(true);
          object.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 16:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        vector = rMIImageImpl.getSources(long_);
        try {
          object = paramRemoteCall.getResultStream(true);
          object.writeObject(vector);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 17:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
          i = objectInput.readInt();
          k = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = rMIImageImpl.getTile(long_, i, k);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 18:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        i = rMIImageImpl.getTileGridXOffset(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(i);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 19:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        i = rMIImageImpl.getTileGridYOffset(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(i);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 20:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        i = rMIImageImpl.getTileHeight(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(i);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 21:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        i = rMIImageImpl.getTileWidth(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(i);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 22:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        i = rMIImageImpl.getWidth(long_);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(i);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 23:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
          renderedImage = (RenderedImage)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        rMIImageImpl.setSource(long_, renderedImage);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 24:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
          renderableOp = (RenderableOp)objectInput.readObject();
          null = (RenderContextProxy)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        rMIImageImpl.setSource(long_, renderableOp, null);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 25:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_ = (Long)objectInput.readObject();
          renderedOp = (RenderedOp)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        rMIImageImpl.setSource(long_, renderedOp);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
    } 
    throw new UnmarshalException("invalid method number");
  }
  
  public Operation[] getOperations() {
    return (Operation[])operations.clone();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RMIImageImpl_Skel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */