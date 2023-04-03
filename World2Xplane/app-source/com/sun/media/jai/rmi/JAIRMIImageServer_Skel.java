package com.sun.media.jai.rmi;

import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
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
import java.util.List;
import javax.media.jai.RenderableOp;
import javax.media.jai.RenderedOp;
import javax.media.jai.remote.NegotiableCapabilitySet;
import javax.media.jai.remote.SerializableState;

public final class JAIRMIImageServer_Skel implements Skeleton {
  private static final Operation[] operations = new Operation[] { 
      new Operation("javax.media.jai.remote.SerializableState copyData(java.lang.Long, java.awt.Rectangle)"), new Operation("java.awt.image.RenderedImage createDefaultRendering(java.lang.Long)"), new Operation("void createRenderableOp(java.lang.Long, java.lang.String, java.awt.image.renderable.ParameterBlock)"), new Operation("void createRenderedOp(java.lang.Long, java.lang.String, java.awt.image.renderable.ParameterBlock, javax.media.jai.remote.SerializableState)"), new Operation("java.awt.image.RenderedImage createRendering(java.lang.Long, javax.media.jai.remote.SerializableState)"), new Operation("java.awt.image.RenderedImage createScaledRendering(java.lang.Long, int, int, javax.media.jai.remote.SerializableState)"), new Operation("void dispose(java.lang.Long)"), new Operation("javax.media.jai.remote.SerializableState getBounds2D(java.lang.Long, java.lang.String)"), new Operation("javax.media.jai.remote.SerializableState getColorModel(java.lang.Long)"), new Operation("byte getCompressedTile(java.lang.Long, int, int)[]"), 
      new Operation("javax.media.jai.remote.SerializableState getData(java.lang.Long)"), new Operation("javax.media.jai.remote.SerializableState getData(java.lang.Long, java.awt.Rectangle)"), new Operation("int getHeight(java.lang.Long)"), new Operation("javax.media.jai.remote.SerializableState getInvalidRegion(java.lang.Long, java.awt.image.renderable.ParameterBlock, javax.media.jai.remote.SerializableState, java.awt.image.renderable.ParameterBlock, javax.media.jai.remote.SerializableState)"), new Operation("int getMinTileX(java.lang.Long)"), new Operation("int getMinTileY(java.lang.Long)"), new Operation("int getMinX(java.lang.Long)"), new Operation("int getMinY(java.lang.Long)"), new Operation("javax.media.jai.RenderedOp getNode(java.lang.Long)"), new Operation("int getNumXTiles(java.lang.Long)"), 
      new Operation("int getNumYTiles(java.lang.Long)"), new Operation("java.util.List getOperationDescriptors()"), new Operation("java.lang.Object getProperty(java.lang.Long, java.lang.String)"), new Operation("java.lang.String getPropertyNames(java.lang.Long)[]"), new Operation("java.lang.String getPropertyNames(java.lang.String)[]"), new Operation("java.lang.Long getRemoteID()"), new Operation("float getRenderableHeight(java.lang.Long)"), new Operation("float getRenderableMinX(java.lang.Long)"), new Operation("float getRenderableMinY(java.lang.Long)"), new Operation("float getRenderableWidth(java.lang.Long)"), 
      new Operation("boolean getRendering(java.lang.Long)"), new Operation("java.lang.Long getRendering(java.lang.Long, javax.media.jai.remote.SerializableState)"), new Operation("javax.media.jai.remote.SerializableState getSampleModel(java.lang.Long)"), new Operation("javax.media.jai.remote.NegotiableCapabilitySet getServerCapabilities()"), new Operation("java.lang.String getServerSupportedOperationNames()[]"), new Operation("javax.media.jai.remote.SerializableState getTile(java.lang.Long, int, int)"), new Operation("int getTileGridXOffset(java.lang.Long)"), new Operation("int getTileGridYOffset(java.lang.Long)"), new Operation("int getTileHeight(java.lang.Long)"), new Operation("int getTileWidth(java.lang.Long)"), 
      new Operation("int getWidth(java.lang.Long)"), new Operation("java.lang.Long handleEvent(java.lang.Long, int, javax.media.jai.remote.SerializableState, java.lang.Object)"), new Operation("java.lang.Long handleEvent(java.lang.Long, java.lang.String, java.lang.Object, java.lang.Object)"), new Operation("void incrementRefCount(java.lang.Long)"), new Operation("boolean isDynamic(java.lang.Long)"), new Operation("boolean isDynamic(java.lang.String)"), new Operation("java.awt.Rectangle mapDestRect(java.lang.Long, java.awt.Rectangle, int)"), new Operation("javax.media.jai.remote.SerializableState mapRenderContext(int, java.lang.Long, java.lang.String, javax.media.jai.remote.SerializableState)"), new Operation("java.awt.Rectangle mapSourceRect(java.lang.Long, java.awt.Rectangle, int)"), new Operation("void setRenderableRMIServerProxyAsSource(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, int)"), 
      new Operation("void setRenderableSource(java.lang.Long, com.sun.media.jai.rmi.SerializableRenderableImage, int)"), new Operation("void setRenderableSource(java.lang.Long, java.awt.image.RenderedImage, int)"), new Operation("void setRenderableSource(java.lang.Long, java.lang.Long, int)"), new Operation("void setRenderableSource(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, int)"), new Operation("void setRenderableSource(java.lang.Long, javax.media.jai.RenderableOp, int)"), new Operation("void setRenderedSource(java.lang.Long, java.awt.image.RenderedImage, int)"), new Operation("void setRenderedSource(java.lang.Long, java.lang.Long, int)"), new Operation("void setRenderedSource(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, int)"), new Operation("void setRenderedSource(java.lang.Long, javax.media.jai.RenderedOp, int)"), new Operation("void setServerNegotiatedValues(java.lang.Long, javax.media.jai.remote.NegotiableCapabilitySet)") };
  
  private static final long interfaceHash = 6167769405001739342L;
  
  public void dispatch(Remote paramRemote, RemoteCall paramRemoteCall, int paramInt, long paramLong) throws Exception {
    Long long_6;
    List list;
    Long long_5;
    String str2;
    Long long_4;
    NegotiableCapabilitySet negotiableCapabilitySet1;
    String[] arrayOfString;
    Long long_3;
    String str1;
    Long long_2;
    int i;
    Long long_1;
    RenderedImage renderedImage3;
    String str3;
    SerializableState serializableState4;
    int n;
    SerializableState serializableState3;
    int m;
    SerializableState serializableState2;
    int k;
    float f;
    boolean bool2;
    SerializableState serializableState1;
    int j;
    boolean bool1;
    Rectangle rectangle2;
    Long long_10;
    Rectangle rectangle1;
    Long long_9;
    SerializableRenderableImage serializableRenderableImage;
    RenderedImage renderedImage2;
    Long long_8;
    RenderableOp renderableOp;
    RenderedImage renderedImage1;
    Long long_7;
    RenderedOp renderedOp;
    NegotiableCapabilitySet negotiableCapabilitySet2;
    SerializableState serializableState6;
    RenderedImage renderedImage4;
    int i8;
    SerializableState serializableState5;
    int i7;
    Long long_11;
    int i6;
    Object object;
    int i5;
    String str7;
    int i4;
    String str6;
    int i3;
    String str5;
    int i2;
    String str4;
    int i1;
    RenderedImage renderedImage5;
    SerializableState serializableState7;
    int i9;
    if (paramInt < 0) {
      if (paramLong == -967509352521768614L) {
        paramInt = 0;
      } else if (paramLong == -8497891458627429487L) {
        paramInt = 1;
      } else if (paramLong == 7086259789809689998L) {
        paramInt = 2;
      } else if (paramLong == 5101379426256032149L) {
        paramInt = 3;
      } else if (paramLong == -5245001515136243438L) {
        paramInt = 4;
      } else if (paramLong == 2752392759141353347L) {
        paramInt = 5;
      } else if (paramLong == 6460799139781649959L) {
        paramInt = 6;
      } else if (paramLong == -7344372886056435090L) {
        paramInt = 7;
      } else if (paramLong == -1100163628488185119L) {
        paramInt = 8;
      } else if (paramLong == -1379943561537216322L) {
        paramInt = 9;
      } else if (paramLong == 6361054168006114985L) {
        paramInt = 10;
      } else if (paramLong == -3749893868609537021L) {
        paramInt = 11;
      } else if (paramLong == -7560603472052038977L) {
        paramInt = 12;
      } else if (paramLong == 2196538291040842281L) {
        paramInt = 13;
      } else if (paramLong == 5809966745410438246L) {
        paramInt = 14;
      } else if (paramLong == -9076617268613815876L) {
        paramInt = 15;
      } else if (paramLong == -5297535099750447733L) {
        paramInt = 16;
      } else if (paramLong == 7733459005376369327L) {
        paramInt = 17;
      } else if (paramLong == 9161432851012319050L) {
        paramInt = 18;
      } else if (paramLong == 3645100420184954761L) {
        paramInt = 19;
      } else if (paramLong == -1731091968647972742L) {
        paramInt = 20;
      } else if (paramLong == 3535648159716437706L) {
        paramInt = 21;
      } else if (paramLong == 216968610676295195L) {
        paramInt = 22;
      } else if (paramLong == 3931591828613160321L) {
        paramInt = 23;
      } else if (paramLong == 316409741847260476L) {
        paramInt = 24;
      } else if (paramLong == -232353888923603427L) {
        paramInt = 25;
      } else if (paramLong == 5608422195731594411L) {
        paramInt = 26;
      } else if (paramLong == 2691228702599857582L) {
        paramInt = 27;
      } else if (paramLong == 4212368935241858980L) {
        paramInt = 28;
      } else if (paramLong == 5338396004630022671L) {
        paramInt = 29;
      } else if (paramLong == -2265440493870323208L) {
        paramInt = 30;
      } else if (paramLong == -6125241444070859614L) {
        paramInt = 31;
      } else if (paramLong == -1813341280855901292L) {
        paramInt = 32;
      } else if (paramLong == -5684371542470892640L) {
        paramInt = 33;
      } else if (paramLong == -4886984326445878690L) {
        paramInt = 34;
      } else if (paramLong == 3187214795636220126L) {
        paramInt = 35;
      } else if (paramLong == -8218495432205133449L) {
        paramInt = 36;
      } else if (paramLong == -7482127068346373541L) {
        paramInt = 37;
      } else if (paramLong == 7785669351714030715L) {
        paramInt = 38;
      } else if (paramLong == 282122131312695349L) {
        paramInt = 39;
      } else if (paramLong == -8357318297729299690L) {
        paramInt = 40;
      } else if (paramLong == -2091789747834377998L) {
        paramInt = 41;
      } else if (paramLong == 6735595879989328767L) {
        paramInt = 42;
      } else if (paramLong == -3309069034569190342L) {
        paramInt = 43;
      } else if (paramLong == 9106025340051027274L) {
        paramInt = 44;
      } else if (paramLong == -6284830256520969130L) {
        paramInt = 45;
      } else if (paramLong == 2783117304536308041L) {
        paramInt = 46;
      } else if (paramLong == 3382362498715729166L) {
        paramInt = 47;
      } else if (paramLong == -5162241366759407841L) {
        paramInt = 48;
      } else if (paramLong == -1865549286439023174L) {
        paramInt = 49;
      } else if (paramLong == -2003236639401449658L) {
        paramInt = 50;
      } else if (paramLong == -8080617916453915737L) {
        paramInt = 51;
      } else if (paramLong == -7879955699630425072L) {
        paramInt = 52;
      } else if (paramLong == -5890575207352710342L) {
        paramInt = 53;
      } else if (paramLong == -8761942329287512340L) {
        paramInt = 54;
      } else if (paramLong == 2834995389306513647L) {
        paramInt = 55;
      } else if (paramLong == -6335170796820847995L) {
        paramInt = 56;
      } else if (paramLong == -1071494500456449009L) {
        paramInt = 57;
      } else if (paramLong == -7819102304157660296L) {
        paramInt = 58;
      } else if (paramLong == -27037179580597379L) {
        paramInt = 59;
      } else {
        throw new UnmarshalException("invalid method hash");
      } 
    } else if (paramLong != 6167769405001739342L) {
      throw new SkeletonMismatchException("interface hash mismatch");
    } 
    JAIRMIImageServer jAIRMIImageServer = (JAIRMIImageServer)paramRemote;
    switch (paramInt) {
      case 0:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
          null = (Rectangle)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        serializableState6 = jAIRMIImageServer.copyData(long_6, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(serializableState6);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 1:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        renderedImage3 = jAIRMIImageServer.createDefaultRendering(long_6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(renderedImage3);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 2:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
          str3 = (String)objectInput.readObject();
          null = (ParameterBlock)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.createRenderableOp(long_6, str3, null);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 3:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
          str3 = (String)objectInput.readObject();
          null = (ParameterBlock)objectInput.readObject();
          null = (SerializableState)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.createRenderedOp(long_6, str3, null, null);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 4:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
          serializableState4 = (SerializableState)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        renderedImage4 = jAIRMIImageServer.createRendering(long_6, serializableState4);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(renderedImage4);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 5:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
          n = objectInput.readInt();
          i8 = objectInput.readInt();
          null = (SerializableState)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        renderedImage5 = jAIRMIImageServer.createScaledRendering(long_6, n, i8, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(renderedImage5);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 6:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.dispose(long_6);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 7:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
          null = (String)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        serializableState5 = jAIRMIImageServer.getBounds2D(long_6, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(serializableState5);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 8:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        serializableState3 = jAIRMIImageServer.getColorModel(long_6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(serializableState3);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 9:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
          m = objectInput.readInt();
          i7 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.getCompressedTile(long_6, m, i7);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 10:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        serializableState2 = jAIRMIImageServer.getData(long_6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(serializableState2);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 11:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
          null = (Rectangle)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.getData(long_6, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 12:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        k = jAIRMIImageServer.getHeight(long_6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(k);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 13:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
          null = (ParameterBlock)objectInput.readObject();
          null = (SerializableState)objectInput.readObject();
          null = (ParameterBlock)objectInput.readObject();
          null = (SerializableState)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.getInvalidRegion(long_6, null, null, null, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 14:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        k = jAIRMIImageServer.getMinTileX(long_6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(k);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 15:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        k = jAIRMIImageServer.getMinTileY(long_6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(k);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 16:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        k = jAIRMIImageServer.getMinX(long_6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(k);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 17:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        k = jAIRMIImageServer.getMinY(long_6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(k);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 18:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.getNode(long_6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 19:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        k = jAIRMIImageServer.getNumXTiles(long_6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(k);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 20:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_6 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        k = jAIRMIImageServer.getNumYTiles(long_6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(k);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 21:
        paramRemoteCall.releaseInputStream();
        list = jAIRMIImageServer.getOperationDescriptors();
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(list);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 22:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_5 = (Long)objectInput.readObject();
          null = (String)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.getProperty(long_5, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 23:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_5 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.getPropertyNames(long_5);
        try {
          null = paramRemoteCall.getResultStream(true);
          null.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 24:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          str2 = (String)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.getPropertyNames(str2);
        try {
          null = paramRemoteCall.getResultStream(true);
          null.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 25:
        paramRemoteCall.releaseInputStream();
        long_4 = jAIRMIImageServer.getRemoteID();
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(long_4);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 26:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_4 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        f = jAIRMIImageServer.getRenderableHeight(long_4);
        try {
          null = paramRemoteCall.getResultStream(true);
          null.writeFloat(f);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 27:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_4 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        f = jAIRMIImageServer.getRenderableMinX(long_4);
        try {
          null = paramRemoteCall.getResultStream(true);
          null.writeFloat(f);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 28:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_4 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        f = jAIRMIImageServer.getRenderableMinY(long_4);
        try {
          null = paramRemoteCall.getResultStream(true);
          null.writeFloat(f);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 29:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_4 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        f = jAIRMIImageServer.getRenderableWidth(long_4);
        try {
          null = paramRemoteCall.getResultStream(true);
          null.writeFloat(f);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 30:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_4 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        bool2 = jAIRMIImageServer.getRendering(long_4);
        try {
          null = paramRemoteCall.getResultStream(true);
          null.writeBoolean(bool2);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 31:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_4 = (Long)objectInput.readObject();
          null = (SerializableState)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        long_11 = jAIRMIImageServer.getRendering(long_4, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(long_11);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 32:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_4 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        serializableState1 = jAIRMIImageServer.getSampleModel(long_4);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(serializableState1);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 33:
        paramRemoteCall.releaseInputStream();
        negotiableCapabilitySet1 = jAIRMIImageServer.getServerCapabilities();
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(negotiableCapabilitySet1);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 34:
        paramRemoteCall.releaseInputStream();
        arrayOfString = jAIRMIImageServer.getServerSupportedOperationNames();
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(arrayOfString);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 35:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_3 = (Long)objectInput.readObject();
          j = objectInput.readInt();
          i6 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.getTile(long_3, j, i6);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 36:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_3 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = jAIRMIImageServer.getTileGridXOffset(long_3);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 37:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_3 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = jAIRMIImageServer.getTileGridYOffset(long_3);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 38:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_3 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = jAIRMIImageServer.getTileHeight(long_3);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 39:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_3 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = jAIRMIImageServer.getTileWidth(long_3);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 40:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_3 = (Long)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        j = jAIRMIImageServer.getWidth(long_3);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeInt(j);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 41:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_3 = (Long)objectInput.readObject();
          j = objectInput.readInt();
          object = objectInput.readObject();
          null = objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.handleEvent(long_3, j, (SerializableState)object, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 42:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_3 = (Long)objectInput.readObject();
          null = (String)objectInput.readObject();
          object = objectInput.readObject();
          null = objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.handleEvent(long_3, null, object, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 43:
        try {
          null = paramRemoteCall.getInputStream();
          long_3 = (Long)null.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.incrementRefCount(long_3);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 44:
        try {
          null = paramRemoteCall.getInputStream();
          long_3 = (Long)null.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        bool1 = jAIRMIImageServer.isDynamic(long_3);
        try {
          object = paramRemoteCall.getResultStream(true);
          object.writeBoolean(bool1);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 45:
        try {
          null = paramRemoteCall.getInputStream();
          str1 = (String)null.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        bool1 = jAIRMIImageServer.isDynamic(str1);
        try {
          object = paramRemoteCall.getResultStream(true);
          object.writeBoolean(bool1);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 46:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_2 = (Long)objectInput.readObject();
          rectangle2 = (Rectangle)objectInput.readObject();
          i5 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.mapDestRect(long_2, rectangle2, i5);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 47:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          i = objectInput.readInt();
          long_10 = (Long)objectInput.readObject();
          str7 = (String)objectInput.readObject();
          null = (SerializableState)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        serializableState7 = jAIRMIImageServer.mapRenderContext(i, long_10, str7, null);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(serializableState7);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 48:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          rectangle1 = (Rectangle)objectInput.readObject();
          i4 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        null = jAIRMIImageServer.mapSourceRect(long_1, rectangle1, i4);
        try {
          ObjectOutput objectOutput = paramRemoteCall.getResultStream(true);
          objectOutput.writeObject(null);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 49:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          long_9 = (Long)objectInput.readObject();
          str6 = (String)objectInput.readObject();
          null = (String)objectInput.readObject();
          i9 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.setRenderableRMIServerProxyAsSource(long_1, long_9, str6, null, i9);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 50:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          serializableRenderableImage = (SerializableRenderableImage)objectInput.readObject();
          i3 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.setRenderableSource(long_1, serializableRenderableImage, i3);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 51:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          renderedImage2 = (RenderedImage)objectInput.readObject();
          i3 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.setRenderableSource(long_1, renderedImage2, i3);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 52:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          long_8 = (Long)objectInput.readObject();
          i3 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.setRenderableSource(long_1, long_8, i3);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 53:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          long_8 = (Long)objectInput.readObject();
          str5 = (String)objectInput.readObject();
          null = (String)objectInput.readObject();
          i9 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.setRenderableSource(long_1, long_8, str5, null, i9);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 54:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          renderableOp = (RenderableOp)objectInput.readObject();
          i2 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.setRenderableSource(long_1, renderableOp, i2);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 55:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          renderedImage1 = (RenderedImage)objectInput.readObject();
          i2 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.setRenderedSource(long_1, renderedImage1, i2);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 56:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          long_7 = (Long)objectInput.readObject();
          i2 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.setRenderedSource(long_1, long_7, i2);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 57:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          long_7 = (Long)objectInput.readObject();
          str4 = (String)objectInput.readObject();
          null = (String)objectInput.readObject();
          i9 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.setRenderedSource(long_1, long_7, str4, null, i9);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 58:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          renderedOp = (RenderedOp)objectInput.readObject();
          i1 = objectInput.readInt();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.setRenderedSource(long_1, renderedOp, i1);
        try {
          paramRemoteCall.getResultStream(true);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling return", iOException);
        } 
        return;
      case 59:
        try {
          ObjectInput objectInput = paramRemoteCall.getInputStream();
          long_1 = (Long)objectInput.readObject();
          negotiableCapabilitySet2 = (NegotiableCapabilitySet)objectInput.readObject();
        } catch (IOException iOException) {
          throw new UnmarshalException("error unmarshalling arguments", iOException);
        } catch (ClassNotFoundException classNotFoundException) {
          throw new UnmarshalException("error unmarshalling arguments", classNotFoundException);
        } finally {
          paramRemoteCall.releaseInputStream();
        } 
        jAIRMIImageServer.setServerNegotiatedValues(long_1, negotiableCapabilitySet2);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\JAIRMIImageServer_Skel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */