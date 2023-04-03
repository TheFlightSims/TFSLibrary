package com.sun.media.jai.rmi;

import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;
import java.rmi.MarshalException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import java.rmi.UnmarshalException;
import java.rmi.server.Operation;
import java.rmi.server.RemoteCall;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteStub;
import java.util.List;
import javax.media.jai.RenderableOp;
import javax.media.jai.RenderedOp;
import javax.media.jai.remote.NegotiableCapabilitySet;
import javax.media.jai.remote.SerializableState;

public final class JAIRMIImageServer_Stub extends RemoteStub implements ImageServer, Remote {
  private static final Operation[] operations = new Operation[] { 
      new Operation("javax.media.jai.remote.SerializableState copyData(java.lang.Long, java.awt.Rectangle)"), new Operation("java.awt.image.RenderedImage createDefaultRendering(java.lang.Long)"), new Operation("void createRenderableOp(java.lang.Long, java.lang.String, java.awt.image.renderable.ParameterBlock)"), new Operation("void createRenderedOp(java.lang.Long, java.lang.String, java.awt.image.renderable.ParameterBlock, javax.media.jai.remote.SerializableState)"), new Operation("java.awt.image.RenderedImage createRendering(java.lang.Long, javax.media.jai.remote.SerializableState)"), new Operation("java.awt.image.RenderedImage createScaledRendering(java.lang.Long, int, int, javax.media.jai.remote.SerializableState)"), new Operation("void dispose(java.lang.Long)"), new Operation("javax.media.jai.remote.SerializableState getBounds2D(java.lang.Long, java.lang.String)"), new Operation("javax.media.jai.remote.SerializableState getColorModel(java.lang.Long)"), new Operation("byte getCompressedTile(java.lang.Long, int, int)[]"), 
      new Operation("javax.media.jai.remote.SerializableState getData(java.lang.Long)"), new Operation("javax.media.jai.remote.SerializableState getData(java.lang.Long, java.awt.Rectangle)"), new Operation("int getHeight(java.lang.Long)"), new Operation("javax.media.jai.remote.SerializableState getInvalidRegion(java.lang.Long, java.awt.image.renderable.ParameterBlock, javax.media.jai.remote.SerializableState, java.awt.image.renderable.ParameterBlock, javax.media.jai.remote.SerializableState)"), new Operation("int getMinTileX(java.lang.Long)"), new Operation("int getMinTileY(java.lang.Long)"), new Operation("int getMinX(java.lang.Long)"), new Operation("int getMinY(java.lang.Long)"), new Operation("javax.media.jai.RenderedOp getNode(java.lang.Long)"), new Operation("int getNumXTiles(java.lang.Long)"), 
      new Operation("int getNumYTiles(java.lang.Long)"), new Operation("java.util.List getOperationDescriptors()"), new Operation("java.lang.Object getProperty(java.lang.Long, java.lang.String)"), new Operation("java.lang.String getPropertyNames(java.lang.Long)[]"), new Operation("java.lang.String getPropertyNames(java.lang.String)[]"), new Operation("java.lang.Long getRemoteID()"), new Operation("float getRenderableHeight(java.lang.Long)"), new Operation("float getRenderableMinX(java.lang.Long)"), new Operation("float getRenderableMinY(java.lang.Long)"), new Operation("float getRenderableWidth(java.lang.Long)"), 
      new Operation("boolean getRendering(java.lang.Long)"), new Operation("java.lang.Long getRendering(java.lang.Long, javax.media.jai.remote.SerializableState)"), new Operation("javax.media.jai.remote.SerializableState getSampleModel(java.lang.Long)"), new Operation("javax.media.jai.remote.NegotiableCapabilitySet getServerCapabilities()"), new Operation("java.lang.String getServerSupportedOperationNames()[]"), new Operation("javax.media.jai.remote.SerializableState getTile(java.lang.Long, int, int)"), new Operation("int getTileGridXOffset(java.lang.Long)"), new Operation("int getTileGridYOffset(java.lang.Long)"), new Operation("int getTileHeight(java.lang.Long)"), new Operation("int getTileWidth(java.lang.Long)"), 
      new Operation("int getWidth(java.lang.Long)"), new Operation("java.lang.Long handleEvent(java.lang.Long, int, javax.media.jai.remote.SerializableState, java.lang.Object)"), new Operation("java.lang.Long handleEvent(java.lang.Long, java.lang.String, java.lang.Object, java.lang.Object)"), new Operation("void incrementRefCount(java.lang.Long)"), new Operation("boolean isDynamic(java.lang.Long)"), new Operation("boolean isDynamic(java.lang.String)"), new Operation("java.awt.Rectangle mapDestRect(java.lang.Long, java.awt.Rectangle, int)"), new Operation("javax.media.jai.remote.SerializableState mapRenderContext(int, java.lang.Long, java.lang.String, javax.media.jai.remote.SerializableState)"), new Operation("java.awt.Rectangle mapSourceRect(java.lang.Long, java.awt.Rectangle, int)"), new Operation("void setRenderableRMIServerProxyAsSource(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, int)"), 
      new Operation("void setRenderableSource(java.lang.Long, com.sun.media.jai.rmi.SerializableRenderableImage, int)"), new Operation("void setRenderableSource(java.lang.Long, java.awt.image.RenderedImage, int)"), new Operation("void setRenderableSource(java.lang.Long, java.lang.Long, int)"), new Operation("void setRenderableSource(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, int)"), new Operation("void setRenderableSource(java.lang.Long, javax.media.jai.RenderableOp, int)"), new Operation("void setRenderedSource(java.lang.Long, java.awt.image.RenderedImage, int)"), new Operation("void setRenderedSource(java.lang.Long, java.lang.Long, int)"), new Operation("void setRenderedSource(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, int)"), new Operation("void setRenderedSource(java.lang.Long, javax.media.jai.RenderedOp, int)"), new Operation("void setServerNegotiatedValues(java.lang.Long, javax.media.jai.remote.NegotiableCapabilitySet)") };
  
  private static final long interfaceHash = 6167769405001739342L;
  
  private static final long serialVersionUID = 2L;
  
  private static boolean useNewInvoke;
  
  private static Method $method_copyData_0;
  
  private static Method $method_createDefaultRendering_1;
  
  private static Method $method_createRenderableOp_2;
  
  private static Method $method_createRenderedOp_3;
  
  private static Method $method_createRendering_4;
  
  private static Method $method_createScaledRendering_5;
  
  private static Method $method_dispose_6;
  
  private static Method $method_getBounds2D_7;
  
  private static Method $method_getColorModel_8;
  
  private static Method $method_getCompressedTile_9;
  
  private static Method $method_getData_10;
  
  private static Method $method_getData_11;
  
  private static Method $method_getHeight_12;
  
  private static Method $method_getInvalidRegion_13;
  
  private static Method $method_getMinTileX_14;
  
  private static Method $method_getMinTileY_15;
  
  private static Method $method_getMinX_16;
  
  private static Method $method_getMinY_17;
  
  private static Method $method_getNode_18;
  
  private static Method $method_getNumXTiles_19;
  
  private static Method $method_getNumYTiles_20;
  
  private static Method $method_getOperationDescriptors_21;
  
  private static Method $method_getProperty_22;
  
  private static Method $method_getPropertyNames_23;
  
  private static Method $method_getPropertyNames_24;
  
  private static Method $method_getRemoteID_25;
  
  private static Method $method_getRenderableHeight_26;
  
  private static Method $method_getRenderableMinX_27;
  
  private static Method $method_getRenderableMinY_28;
  
  private static Method $method_getRenderableWidth_29;
  
  private static Method $method_getRendering_30;
  
  private static Method $method_getRendering_31;
  
  private static Method $method_getSampleModel_32;
  
  private static Method $method_getServerCapabilities_33;
  
  private static Method $method_getServerSupportedOperationNames_34;
  
  private static Method $method_getTile_35;
  
  private static Method $method_getTileGridXOffset_36;
  
  private static Method $method_getTileGridYOffset_37;
  
  private static Method $method_getTileHeight_38;
  
  private static Method $method_getTileWidth_39;
  
  private static Method $method_getWidth_40;
  
  private static Method $method_handleEvent_41;
  
  private static Method $method_handleEvent_42;
  
  private static Method $method_incrementRefCount_43;
  
  private static Method $method_isDynamic_44;
  
  private static Method $method_isDynamic_45;
  
  private static Method $method_mapDestRect_46;
  
  private static Method $method_mapRenderContext_47;
  
  private static Method $method_mapSourceRect_48;
  
  private static Method $method_setRenderableRMIServerProxyAsSource_49;
  
  private static Method $method_setRenderableSource_50;
  
  private static Method $method_setRenderableSource_51;
  
  private static Method $method_setRenderableSource_52;
  
  private static Method $method_setRenderableSource_53;
  
  private static Method $method_setRenderableSource_54;
  
  private static Method $method_setRenderedSource_55;
  
  private static Method $method_setRenderedSource_56;
  
  private static Method $method_setRenderedSource_57;
  
  private static Method $method_setRenderedSource_58;
  
  private static Method $method_setServerNegotiatedValues_59;
  
  static Class array$Ljava$lang$Object;
  
  static {
    try {
      RemoteRef.class.getMethod("invoke", new Class[] { Remote.class, Method.class, (array$Ljava$lang$Object != null) ? array$Ljava$lang$Object : (array$Ljava$lang$Object = class$("[Ljava.lang.Object;")), long.class });
      useNewInvoke = true;
      $method_copyData_0 = ImageServer.class.getMethod("copyData", new Class[] { Long.class, Rectangle.class });
      $method_createDefaultRendering_1 = ImageServer.class.getMethod("createDefaultRendering", new Class[] { Long.class });
      $method_createRenderableOp_2 = ImageServer.class.getMethod("createRenderableOp", new Class[] { Long.class, String.class, ParameterBlock.class });
      $method_createRenderedOp_3 = ImageServer.class.getMethod("createRenderedOp", new Class[] { Long.class, String.class, ParameterBlock.class, SerializableState.class });
      $method_createRendering_4 = ImageServer.class.getMethod("createRendering", new Class[] { Long.class, SerializableState.class });
      $method_createScaledRendering_5 = ImageServer.class.getMethod("createScaledRendering", new Class[] { Long.class, int.class, int.class, SerializableState.class });
      $method_dispose_6 = ImageServer.class.getMethod("dispose", new Class[] { Long.class });
      $method_getBounds2D_7 = ImageServer.class.getMethod("getBounds2D", new Class[] { Long.class, String.class });
      $method_getColorModel_8 = ImageServer.class.getMethod("getColorModel", new Class[] { Long.class });
      $method_getCompressedTile_9 = ImageServer.class.getMethod("getCompressedTile", new Class[] { Long.class, int.class, int.class });
      $method_getData_10 = ImageServer.class.getMethod("getData", new Class[] { Long.class });
      $method_getData_11 = ImageServer.class.getMethod("getData", new Class[] { Long.class, Rectangle.class });
      $method_getHeight_12 = ImageServer.class.getMethod("getHeight", new Class[] { Long.class });
      $method_getInvalidRegion_13 = ImageServer.class.getMethod("getInvalidRegion", new Class[] { Long.class, ParameterBlock.class, SerializableState.class, ParameterBlock.class, SerializableState.class });
      $method_getMinTileX_14 = ImageServer.class.getMethod("getMinTileX", new Class[] { Long.class });
      $method_getMinTileY_15 = ImageServer.class.getMethod("getMinTileY", new Class[] { Long.class });
      $method_getMinX_16 = ImageServer.class.getMethod("getMinX", new Class[] { Long.class });
      $method_getMinY_17 = ImageServer.class.getMethod("getMinY", new Class[] { Long.class });
      $method_getNode_18 = ImageServer.class.getMethod("getNode", new Class[] { Long.class });
      $method_getNumXTiles_19 = ImageServer.class.getMethod("getNumXTiles", new Class[] { Long.class });
      $method_getNumYTiles_20 = ImageServer.class.getMethod("getNumYTiles", new Class[] { Long.class });
      $method_getOperationDescriptors_21 = ImageServer.class.getMethod("getOperationDescriptors", new Class[0]);
      $method_getProperty_22 = ImageServer.class.getMethod("getProperty", new Class[] { Long.class, String.class });
      $method_getPropertyNames_23 = ImageServer.class.getMethod("getPropertyNames", new Class[] { Long.class });
      $method_getPropertyNames_24 = ImageServer.class.getMethod("getPropertyNames", new Class[] { String.class });
      $method_getRemoteID_25 = ImageServer.class.getMethod("getRemoteID", new Class[0]);
      $method_getRenderableHeight_26 = ImageServer.class.getMethod("getRenderableHeight", new Class[] { Long.class });
      $method_getRenderableMinX_27 = ImageServer.class.getMethod("getRenderableMinX", new Class[] { Long.class });
      $method_getRenderableMinY_28 = ImageServer.class.getMethod("getRenderableMinY", new Class[] { Long.class });
      $method_getRenderableWidth_29 = ImageServer.class.getMethod("getRenderableWidth", new Class[] { Long.class });
      $method_getRendering_30 = ImageServer.class.getMethod("getRendering", new Class[] { Long.class });
      $method_getRendering_31 = ImageServer.class.getMethod("getRendering", new Class[] { Long.class, SerializableState.class });
      $method_getSampleModel_32 = ImageServer.class.getMethod("getSampleModel", new Class[] { Long.class });
      $method_getServerCapabilities_33 = ImageServer.class.getMethod("getServerCapabilities", new Class[0]);
      $method_getServerSupportedOperationNames_34 = ImageServer.class.getMethod("getServerSupportedOperationNames", new Class[0]);
      $method_getTile_35 = ImageServer.class.getMethod("getTile", new Class[] { Long.class, int.class, int.class });
      $method_getTileGridXOffset_36 = ImageServer.class.getMethod("getTileGridXOffset", new Class[] { Long.class });
      $method_getTileGridYOffset_37 = ImageServer.class.getMethod("getTileGridYOffset", new Class[] { Long.class });
      $method_getTileHeight_38 = ImageServer.class.getMethod("getTileHeight", new Class[] { Long.class });
      $method_getTileWidth_39 = ImageServer.class.getMethod("getTileWidth", new Class[] { Long.class });
      $method_getWidth_40 = ImageServer.class.getMethod("getWidth", new Class[] { Long.class });
      $method_handleEvent_41 = ImageServer.class.getMethod("handleEvent", new Class[] { Long.class, int.class, SerializableState.class, Object.class });
      $method_handleEvent_42 = ImageServer.class.getMethod("handleEvent", new Class[] { Long.class, String.class, Object.class, Object.class });
      $method_incrementRefCount_43 = ImageServer.class.getMethod("incrementRefCount", new Class[] { Long.class });
      $method_isDynamic_44 = ImageServer.class.getMethod("isDynamic", new Class[] { Long.class });
      $method_isDynamic_45 = ImageServer.class.getMethod("isDynamic", new Class[] { String.class });
      $method_mapDestRect_46 = ImageServer.class.getMethod("mapDestRect", new Class[] { Long.class, Rectangle.class, int.class });
      $method_mapRenderContext_47 = ImageServer.class.getMethod("mapRenderContext", new Class[] { int.class, Long.class, String.class, SerializableState.class });
      $method_mapSourceRect_48 = ImageServer.class.getMethod("mapSourceRect", new Class[] { Long.class, Rectangle.class, int.class });
      $method_setRenderableRMIServerProxyAsSource_49 = ImageServer.class.getMethod("setRenderableRMIServerProxyAsSource", new Class[] { Long.class, Long.class, String.class, String.class, int.class });
      $method_setRenderableSource_50 = ImageServer.class.getMethod("setRenderableSource", new Class[] { Long.class, SerializableRenderableImage.class, int.class });
      $method_setRenderableSource_51 = ImageServer.class.getMethod("setRenderableSource", new Class[] { Long.class, RenderedImage.class, int.class });
      $method_setRenderableSource_52 = ImageServer.class.getMethod("setRenderableSource", new Class[] { Long.class, Long.class, int.class });
      $method_setRenderableSource_53 = ImageServer.class.getMethod("setRenderableSource", new Class[] { Long.class, Long.class, String.class, String.class, int.class });
      $method_setRenderableSource_54 = ImageServer.class.getMethod("setRenderableSource", new Class[] { Long.class, RenderableOp.class, int.class });
      $method_setRenderedSource_55 = ImageServer.class.getMethod("setRenderedSource", new Class[] { Long.class, RenderedImage.class, int.class });
      $method_setRenderedSource_56 = ImageServer.class.getMethod("setRenderedSource", new Class[] { Long.class, Long.class, int.class });
      $method_setRenderedSource_57 = ImageServer.class.getMethod("setRenderedSource", new Class[] { Long.class, Long.class, String.class, String.class, int.class });
      $method_setRenderedSource_58 = ImageServer.class.getMethod("setRenderedSource", new Class[] { Long.class, RenderedOp.class, int.class });
      $method_setServerNegotiatedValues_59 = ImageServer.class.getMethod("setServerNegotiatedValues", new Class[] { Long.class, NegotiableCapabilitySet.class });
    } catch (NoSuchMethodException noSuchMethodException) {
      useNewInvoke = false;
    } 
  }
  
  public JAIRMIImageServer_Stub() {}
  
  public JAIRMIImageServer_Stub(RemoteRef paramRemoteRef) {
    super(paramRemoteRef);
  }
  
  public SerializableState copyData(Long paramLong, Rectangle paramRectangle) throws RemoteException {
    try {
      SerializableState serializableState;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_copyData_0, new Object[] { paramLong, paramRectangle }, -967509352521768614L);
        return (SerializableState)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 0, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeObject(paramRectangle);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        serializableState = (SerializableState)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return serializableState;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public RenderedImage createDefaultRendering(Long paramLong) throws RemoteException {
    try {
      RenderedImage renderedImage;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_createDefaultRendering_1, new Object[] { paramLong }, -8497891458627429487L);
        return (RenderedImage)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 1, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        renderedImage = (RenderedImage)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return renderedImage;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void createRenderableOp(Long paramLong, String paramString, ParameterBlock paramParameterBlock) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_createRenderableOp_2, new Object[] { paramLong, paramString, paramParameterBlock }, 7086259789809689998L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 2, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong);
          objectOutput.writeObject(paramString);
          objectOutput.writeObject(paramParameterBlock);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void createRenderedOp(Long paramLong, String paramString, ParameterBlock paramParameterBlock, SerializableState paramSerializableState) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_createRenderedOp_3, new Object[] { paramLong, paramString, paramParameterBlock, paramSerializableState }, 5101379426256032149L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 3, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong);
          objectOutput.writeObject(paramString);
          objectOutput.writeObject(paramParameterBlock);
          objectOutput.writeObject(paramSerializableState);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public RenderedImage createRendering(Long paramLong, SerializableState paramSerializableState) throws RemoteException {
    try {
      RenderedImage renderedImage;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_createRendering_4, new Object[] { paramLong, paramSerializableState }, -5245001515136243438L);
        return (RenderedImage)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 4, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeObject(paramSerializableState);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        renderedImage = (RenderedImage)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return renderedImage;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public RenderedImage createScaledRendering(Long paramLong, int paramInt1, int paramInt2, SerializableState paramSerializableState) throws RemoteException {
    try {
      RenderedImage renderedImage;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_createScaledRendering_5, new Object[] { paramLong, new Integer(paramInt1), new Integer(paramInt2), paramSerializableState }, 2752392759141353347L);
        return (RenderedImage)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 5, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeInt(paramInt1);
        objectOutput.writeInt(paramInt2);
        objectOutput.writeObject(paramSerializableState);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        renderedImage = (RenderedImage)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return renderedImage;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void dispose(Long paramLong) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_dispose_6, new Object[] { paramLong }, 6460799139781649959L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 6, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public SerializableState getBounds2D(Long paramLong, String paramString) throws RemoteException {
    try {
      SerializableState serializableState;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getBounds2D_7, new Object[] { paramLong, paramString }, -7344372886056435090L);
        return (SerializableState)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 7, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeObject(paramString);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        serializableState = (SerializableState)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return serializableState;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public SerializableState getColorModel(Long paramLong) throws RemoteException {
    try {
      SerializableState serializableState;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getColorModel_8, new Object[] { paramLong }, -1100163628488185119L);
        return (SerializableState)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 8, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        serializableState = (SerializableState)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return serializableState;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public byte[] getCompressedTile(Long paramLong, int paramInt1, int paramInt2) throws RemoteException {
    try {
      byte[] arrayOfByte;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getCompressedTile_9, new Object[] { paramLong, new Integer(paramInt1), new Integer(paramInt2) }, -1379943561537216322L);
        return (byte[])object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 9, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeInt(paramInt1);
        objectOutput.writeInt(paramInt2);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        arrayOfByte = (byte[])objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return arrayOfByte;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public SerializableState getData(Long paramLong) throws RemoteException {
    try {
      SerializableState serializableState;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getData_10, new Object[] { paramLong }, 6361054168006114985L);
        return (SerializableState)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 10, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        serializableState = (SerializableState)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return serializableState;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public SerializableState getData(Long paramLong, Rectangle paramRectangle) throws RemoteException {
    try {
      SerializableState serializableState;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getData_11, new Object[] { paramLong, paramRectangle }, -3749893868609537021L);
        return (SerializableState)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 11, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeObject(paramRectangle);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        serializableState = (SerializableState)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return serializableState;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getHeight(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getHeight_12, new Object[] { paramLong }, -7560603472052038977L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 12, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public SerializableState getInvalidRegion(Long paramLong, ParameterBlock paramParameterBlock1, SerializableState paramSerializableState1, ParameterBlock paramParameterBlock2, SerializableState paramSerializableState2) throws RemoteException {
    try {
      SerializableState serializableState;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getInvalidRegion_13, new Object[] { paramLong, paramParameterBlock1, paramSerializableState1, paramParameterBlock2, paramSerializableState2 }, 2196538291040842281L);
        return (SerializableState)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 13, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeObject(paramParameterBlock1);
        objectOutput.writeObject(paramSerializableState1);
        objectOutput.writeObject(paramParameterBlock2);
        objectOutput.writeObject(paramSerializableState2);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        serializableState = (SerializableState)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return serializableState;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getMinTileX(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getMinTileX_14, new Object[] { paramLong }, 5809966745410438246L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 14, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getMinTileY(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getMinTileY_15, new Object[] { paramLong }, -9076617268613815876L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 15, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getMinX(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getMinX_16, new Object[] { paramLong }, -5297535099750447733L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 16, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getMinY(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getMinY_17, new Object[] { paramLong }, 7733459005376369327L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 17, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public RenderedOp getNode(Long paramLong) throws RemoteException {
    try {
      RenderedOp renderedOp;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getNode_18, new Object[] { paramLong }, 9161432851012319050L);
        return (RenderedOp)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 18, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        renderedOp = (RenderedOp)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return renderedOp;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getNumXTiles(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getNumXTiles_19, new Object[] { paramLong }, 3645100420184954761L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 19, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getNumYTiles(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getNumYTiles_20, new Object[] { paramLong }, -1731091968647972742L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 20, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public List getOperationDescriptors() throws RemoteException {
    try {
      List list;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getOperationDescriptors_21, null, 3535648159716437706L);
        return (List)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 21, 6167769405001739342L);
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        list = (List)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return list;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public Object getProperty(Long paramLong, String paramString) throws RemoteException {
    try {
      Object object;
      if (useNewInvoke)
        return this.ref.invoke(this, $method_getProperty_22, new Object[] { paramLong, paramString }, 216968610676295195L); 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 22, 6167769405001739342L);
      try {
        object = remoteCall.getOutputStream();
        object.writeObject(paramLong);
        object.writeObject(paramString);
      } catch (IOException null) {
        throw new MarshalException("error marshalling arguments", object);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        object = objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return object;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public String[] getPropertyNames(Long paramLong) throws RemoteException {
    try {
      String[] arrayOfString;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getPropertyNames_23, new Object[] { paramLong }, 3931591828613160321L);
        return (String[])object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 23, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        arrayOfString = (String[])objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return arrayOfString;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public String[] getPropertyNames(String paramString) throws RemoteException {
    try {
      String[] arrayOfString;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getPropertyNames_24, new Object[] { paramString }, 316409741847260476L);
        return (String[])object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 24, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramString);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        arrayOfString = (String[])objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return arrayOfString;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public Long getRemoteID() throws RemoteException {
    try {
      Long long_;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getRemoteID_25, null, -232353888923603427L);
        return (Long)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 25, 6167769405001739342L);
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        long_ = (Long)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return long_;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public float getRenderableHeight(Long paramLong) throws RemoteException {
    try {
      float f;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getRenderableHeight_26, new Object[] { paramLong }, 5608422195731594411L);
        return ((Float)object).floatValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 26, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        f = objectInput.readFloat();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return f;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public float getRenderableMinX(Long paramLong) throws RemoteException {
    try {
      float f;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getRenderableMinX_27, new Object[] { paramLong }, 2691228702599857582L);
        return ((Float)object).floatValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 27, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        f = objectInput.readFloat();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return f;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public float getRenderableMinY(Long paramLong) throws RemoteException {
    try {
      float f;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getRenderableMinY_28, new Object[] { paramLong }, 4212368935241858980L);
        return ((Float)object).floatValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 28, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        f = objectInput.readFloat();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return f;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public float getRenderableWidth(Long paramLong) throws RemoteException {
    try {
      float f;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getRenderableWidth_29, new Object[] { paramLong }, 5338396004630022671L);
        return ((Float)object).floatValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 29, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        f = objectInput.readFloat();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return f;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public boolean getRendering(Long paramLong) throws RemoteException {
    try {
      boolean bool;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getRendering_30, new Object[] { paramLong }, -2265440493870323208L);
        return ((Boolean)object).booleanValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 30, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        bool = objectInput.readBoolean();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return bool;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public Long getRendering(Long paramLong, SerializableState paramSerializableState) throws RemoteException {
    try {
      Long long_;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getRendering_31, new Object[] { paramLong, paramSerializableState }, -6125241444070859614L);
        return (Long)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 31, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeObject(paramSerializableState);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        long_ = (Long)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return long_;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public SerializableState getSampleModel(Long paramLong) throws RemoteException {
    try {
      SerializableState serializableState;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getSampleModel_32, new Object[] { paramLong }, -1813341280855901292L);
        return (SerializableState)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 32, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        serializableState = (SerializableState)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return serializableState;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public NegotiableCapabilitySet getServerCapabilities() throws RemoteException {
    try {
      NegotiableCapabilitySet negotiableCapabilitySet;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getServerCapabilities_33, null, -5684371542470892640L);
        return (NegotiableCapabilitySet)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 33, 6167769405001739342L);
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        negotiableCapabilitySet = (NegotiableCapabilitySet)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return negotiableCapabilitySet;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public String[] getServerSupportedOperationNames() throws RemoteException {
    try {
      String[] arrayOfString;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getServerSupportedOperationNames_34, null, -4886984326445878690L);
        return (String[])object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 34, 6167769405001739342L);
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        arrayOfString = (String[])objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return arrayOfString;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public SerializableState getTile(Long paramLong, int paramInt1, int paramInt2) throws RemoteException {
    try {
      SerializableState serializableState;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getTile_35, new Object[] { paramLong, new Integer(paramInt1), new Integer(paramInt2) }, 3187214795636220126L);
        return (SerializableState)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 35, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeInt(paramInt1);
        objectOutput.writeInt(paramInt2);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        serializableState = (SerializableState)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return serializableState;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getTileGridXOffset(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getTileGridXOffset_36, new Object[] { paramLong }, -8218495432205133449L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 36, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getTileGridYOffset(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getTileGridYOffset_37, new Object[] { paramLong }, -7482127068346373541L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 37, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getTileHeight(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getTileHeight_38, new Object[] { paramLong }, 7785669351714030715L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 38, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getTileWidth(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getTileWidth_39, new Object[] { paramLong }, 282122131312695349L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 39, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public int getWidth(Long paramLong) throws RemoteException {
    try {
      int i;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_getWidth_40, new Object[] { paramLong }, -8357318297729299690L);
        return ((Integer)object).intValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 40, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        i = objectInput.readInt();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return i;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public Long handleEvent(Long paramLong, int paramInt, SerializableState paramSerializableState, Object paramObject) throws RemoteException {
    try {
      Long long_;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_handleEvent_41, new Object[] { paramLong, new Integer(paramInt), paramSerializableState, paramObject }, -2091789747834377998L);
        return (Long)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 41, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeInt(paramInt);
        objectOutput.writeObject(paramSerializableState);
        objectOutput.writeObject(paramObject);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        long_ = (Long)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return long_;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public Long handleEvent(Long paramLong, String paramString, Object paramObject1, Object paramObject2) throws RemoteException {
    try {
      Long long_;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_handleEvent_42, new Object[] { paramLong, paramString, paramObject1, paramObject2 }, 6735595879989328767L);
        return (Long)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 42, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeObject(paramString);
        objectOutput.writeObject(paramObject1);
        objectOutput.writeObject(paramObject2);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        long_ = (Long)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return long_;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void incrementRefCount(Long paramLong) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_incrementRefCount_43, new Object[] { paramLong }, -3309069034569190342L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 43, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public boolean isDynamic(Long paramLong) throws RemoteException {
    try {
      boolean bool;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_isDynamic_44, new Object[] { paramLong }, 9106025340051027274L);
        return ((Boolean)object).booleanValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 44, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        bool = objectInput.readBoolean();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return bool;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public boolean isDynamic(String paramString) throws RemoteException {
    try {
      boolean bool;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_isDynamic_45, new Object[] { paramString }, -6284830256520969130L);
        return ((Boolean)object).booleanValue();
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 45, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramString);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        bool = objectInput.readBoolean();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return bool;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public Rectangle mapDestRect(Long paramLong, Rectangle paramRectangle, int paramInt) throws RemoteException {
    try {
      Rectangle rectangle;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_mapDestRect_46, new Object[] { paramLong, paramRectangle, new Integer(paramInt) }, 2783117304536308041L);
        return (Rectangle)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 46, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeObject(paramRectangle);
        objectOutput.writeInt(paramInt);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        rectangle = (Rectangle)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return rectangle;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public SerializableState mapRenderContext(int paramInt, Long paramLong, String paramString, SerializableState paramSerializableState) throws RemoteException {
    try {
      SerializableState serializableState;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_mapRenderContext_47, new Object[] { new Integer(paramInt), paramLong, paramString, paramSerializableState }, 3382362498715729166L);
        return (SerializableState)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 47, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeInt(paramInt);
        objectOutput.writeObject(paramLong);
        objectOutput.writeObject(paramString);
        objectOutput.writeObject(paramSerializableState);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        serializableState = (SerializableState)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return serializableState;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public Rectangle mapSourceRect(Long paramLong, Rectangle paramRectangle, int paramInt) throws RemoteException {
    try {
      Rectangle rectangle;
      if (useNewInvoke) {
        Object object = this.ref.invoke(this, $method_mapSourceRect_48, new Object[] { paramLong, paramRectangle, new Integer(paramInt) }, -5162241366759407841L);
        return (Rectangle)object;
      } 
      RemoteCall remoteCall = this.ref.newCall(this, operations, 48, 6167769405001739342L);
      try {
        ObjectOutput objectOutput = remoteCall.getOutputStream();
        objectOutput.writeObject(paramLong);
        objectOutput.writeObject(paramRectangle);
        objectOutput.writeInt(paramInt);
      } catch (IOException iOException) {
        throw new MarshalException("error marshalling arguments", iOException);
      } 
      this.ref.invoke(remoteCall);
      try {
        ObjectInput objectInput = remoteCall.getInputStream();
        rectangle = (Rectangle)objectInput.readObject();
      } catch (IOException iOException) {
        throw new UnmarshalException("error unmarshalling return", iOException);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new UnmarshalException("error unmarshalling return", classNotFoundException);
      } finally {
        this.ref.done(remoteCall);
      } 
      return rectangle;
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void setRenderableRMIServerProxyAsSource(Long paramLong1, Long paramLong2, String paramString1, String paramString2, int paramInt) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_setRenderableRMIServerProxyAsSource_49, new Object[] { paramLong1, paramLong2, paramString1, paramString2, new Integer(paramInt) }, -1865549286439023174L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 49, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong1);
          objectOutput.writeObject(paramLong2);
          objectOutput.writeObject(paramString1);
          objectOutput.writeObject(paramString2);
          objectOutput.writeInt(paramInt);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void setRenderableSource(Long paramLong, SerializableRenderableImage paramSerializableRenderableImage, int paramInt) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_setRenderableSource_50, new Object[] { paramLong, paramSerializableRenderableImage, new Integer(paramInt) }, -2003236639401449658L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 50, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong);
          objectOutput.writeObject(paramSerializableRenderableImage);
          objectOutput.writeInt(paramInt);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void setRenderableSource(Long paramLong, RenderedImage paramRenderedImage, int paramInt) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_setRenderableSource_51, new Object[] { paramLong, paramRenderedImage, new Integer(paramInt) }, -8080617916453915737L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 51, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong);
          objectOutput.writeObject(paramRenderedImage);
          objectOutput.writeInt(paramInt);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void setRenderableSource(Long paramLong1, Long paramLong2, int paramInt) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_setRenderableSource_52, new Object[] { paramLong1, paramLong2, new Integer(paramInt) }, -7879955699630425072L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 52, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong1);
          objectOutput.writeObject(paramLong2);
          objectOutput.writeInt(paramInt);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void setRenderableSource(Long paramLong1, Long paramLong2, String paramString1, String paramString2, int paramInt) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_setRenderableSource_53, new Object[] { paramLong1, paramLong2, paramString1, paramString2, new Integer(paramInt) }, -5890575207352710342L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 53, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong1);
          objectOutput.writeObject(paramLong2);
          objectOutput.writeObject(paramString1);
          objectOutput.writeObject(paramString2);
          objectOutput.writeInt(paramInt);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void setRenderableSource(Long paramLong, RenderableOp paramRenderableOp, int paramInt) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_setRenderableSource_54, new Object[] { paramLong, paramRenderableOp, new Integer(paramInt) }, -8761942329287512340L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 54, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong);
          objectOutput.writeObject(paramRenderableOp);
          objectOutput.writeInt(paramInt);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void setRenderedSource(Long paramLong, RenderedImage paramRenderedImage, int paramInt) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_setRenderedSource_55, new Object[] { paramLong, paramRenderedImage, new Integer(paramInt) }, 2834995389306513647L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 55, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong);
          objectOutput.writeObject(paramRenderedImage);
          objectOutput.writeInt(paramInt);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void setRenderedSource(Long paramLong1, Long paramLong2, int paramInt) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_setRenderedSource_56, new Object[] { paramLong1, paramLong2, new Integer(paramInt) }, -6335170796820847995L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 56, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong1);
          objectOutput.writeObject(paramLong2);
          objectOutput.writeInt(paramInt);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void setRenderedSource(Long paramLong1, Long paramLong2, String paramString1, String paramString2, int paramInt) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_setRenderedSource_57, new Object[] { paramLong1, paramLong2, paramString1, paramString2, new Integer(paramInt) }, -1071494500456449009L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 57, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong1);
          objectOutput.writeObject(paramLong2);
          objectOutput.writeObject(paramString1);
          objectOutput.writeObject(paramString2);
          objectOutput.writeInt(paramInt);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void setRenderedSource(Long paramLong, RenderedOp paramRenderedOp, int paramInt) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_setRenderedSource_58, new Object[] { paramLong, paramRenderedOp, new Integer(paramInt) }, -7819102304157660296L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 58, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong);
          objectOutput.writeObject(paramRenderedOp);
          objectOutput.writeInt(paramInt);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
  
  public void setServerNegotiatedValues(Long paramLong, NegotiableCapabilitySet paramNegotiableCapabilitySet) throws RemoteException {
    try {
      if (useNewInvoke) {
        this.ref.invoke(this, $method_setServerNegotiatedValues_59, new Object[] { paramLong, paramNegotiableCapabilitySet }, -27037179580597379L);
      } else {
        RemoteCall remoteCall = this.ref.newCall(this, operations, 59, 6167769405001739342L);
        try {
          ObjectOutput objectOutput = remoteCall.getOutputStream();
          objectOutput.writeObject(paramLong);
          objectOutput.writeObject(paramNegotiableCapabilitySet);
        } catch (IOException iOException) {
          throw new MarshalException("error marshalling arguments", iOException);
        } 
        this.ref.invoke(remoteCall);
        this.ref.done(remoteCall);
      } 
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException;
    } catch (Exception exception) {
      throw new UnexpectedException("undeclared checked exception", exception);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\JAIRMIImageServer_Stub.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */