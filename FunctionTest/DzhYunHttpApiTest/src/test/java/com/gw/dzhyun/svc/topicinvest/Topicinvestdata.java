package com.gw.dzhyun.svc.topicinvest;

public final class Topicinvestdata {
  private Topicinvestdata() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface TopicInvestDataOrBuilder extends
      // @@protoc_insertion_point(interface_extends:dzhyun.TopicInvestData)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 TopicInvestId = 1;</code>
     */
    boolean hasTopicInvestId();
    /**
     * <code>required int32 TopicInvestId = 1;</code>
     */
    int getTopicInvestId();

    /**
     * <code>required string TopicInvestName = 2;</code>
     */
    boolean hasTopicInvestName();
    /**
     * <code>required string TopicInvestName = 2;</code>
     */
    java.lang.String getTopicInvestName();
    /**
     * <code>required string TopicInvestName = 2;</code>
     */
    com.google.protobuf.ByteString
        getTopicInvestNameBytes();

    /**
     * <code>repeated string ComponentObj = 3;</code>
     */
    com.google.protobuf.ProtocolStringList
        getComponentObjList();
    /**
     * <code>repeated string ComponentObj = 3;</code>
     */
    int getComponentObjCount();
    /**
     * <code>repeated string ComponentObj = 3;</code>
     */
    java.lang.String getComponentObj(int index);
    /**
     * <code>repeated string ComponentObj = 3;</code>
     */
    com.google.protobuf.ByteString
        getComponentObjBytes(int index);
  }
  /**
   * Protobuf type {@code dzhyun.TopicInvestData}
   */
  public static final class TopicInvestData extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:dzhyun.TopicInvestData)
      TopicInvestDataOrBuilder {
    // Use TopicInvestData.newBuilder() to construct.
    private TopicInvestData(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private TopicInvestData(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final TopicInvestData defaultInstance;
    public static TopicInvestData getDefaultInstance() {
      return defaultInstance;
    }

    public TopicInvestData getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private TopicInvestData(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              topicInvestId_ = input.readInt32();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              topicInvestName_ = bs;
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
                componentObj_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000004;
              }
              componentObj_.add(bs);
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
          componentObj_ = componentObj_.getUnmodifiableView();
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gw.dzhyun.svc.topicinvest.Topicinvestdata.internal_static_dzhyun_TopicInvestData_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gw.dzhyun.svc.topicinvest.Topicinvestdata.internal_static_dzhyun_TopicInvestData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData.class, com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData.Builder.class);
    }

    public static com.google.protobuf.Parser<TopicInvestData> PARSER =
        new com.google.protobuf.AbstractParser<TopicInvestData>() {
      public TopicInvestData parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new TopicInvestData(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<TopicInvestData> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int TOPICINVESTID_FIELD_NUMBER = 1;
    private int topicInvestId_;
    /**
     * <code>required int32 TopicInvestId = 1;</code>
     */
    public boolean hasTopicInvestId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 TopicInvestId = 1;</code>
     */
    public int getTopicInvestId() {
      return topicInvestId_;
    }

    public static final int TOPICINVESTNAME_FIELD_NUMBER = 2;
    private java.lang.Object topicInvestName_;
    /**
     * <code>required string TopicInvestName = 2;</code>
     */
    public boolean hasTopicInvestName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string TopicInvestName = 2;</code>
     */
    public java.lang.String getTopicInvestName() {
      java.lang.Object ref = topicInvestName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          topicInvestName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string TopicInvestName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getTopicInvestNameBytes() {
      java.lang.Object ref = topicInvestName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        topicInvestName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int COMPONENTOBJ_FIELD_NUMBER = 3;
    private com.google.protobuf.LazyStringList componentObj_;
    /**
     * <code>repeated string ComponentObj = 3;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getComponentObjList() {
      return componentObj_;
    }
    /**
     * <code>repeated string ComponentObj = 3;</code>
     */
    public int getComponentObjCount() {
      return componentObj_.size();
    }
    /**
     * <code>repeated string ComponentObj = 3;</code>
     */
    public java.lang.String getComponentObj(int index) {
      return componentObj_.get(index);
    }
    /**
     * <code>repeated string ComponentObj = 3;</code>
     */
    public com.google.protobuf.ByteString
        getComponentObjBytes(int index) {
      return componentObj_.getByteString(index);
    }

    private void initFields() {
      topicInvestId_ = 0;
      topicInvestName_ = "";
      componentObj_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasTopicInvestId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasTopicInvestName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, topicInvestId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getTopicInvestNameBytes());
      }
      for (int i = 0; i < componentObj_.size(); i++) {
        output.writeBytes(3, componentObj_.getByteString(i));
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, topicInvestId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getTopicInvestNameBytes());
      }
      {
        int dataSize = 0;
        for (int i = 0; i < componentObj_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeBytesSizeNoTag(componentObj_.getByteString(i));
        }
        size += dataSize;
        size += 1 * getComponentObjList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code dzhyun.TopicInvestData}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:dzhyun.TopicInvestData)
        com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestDataOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gw.dzhyun.svc.topicinvest.Topicinvestdata.internal_static_dzhyun_TopicInvestData_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gw.dzhyun.svc.topicinvest.Topicinvestdata.internal_static_dzhyun_TopicInvestData_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData.class, com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData.Builder.class);
      }

      // Construct using com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        topicInvestId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        topicInvestName_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        componentObj_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gw.dzhyun.svc.topicinvest.Topicinvestdata.internal_static_dzhyun_TopicInvestData_descriptor;
      }

      public com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData getDefaultInstanceForType() {
        return com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData.getDefaultInstance();
      }

      public com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData build() {
        com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData buildPartial() {
        com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData result = new com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.topicInvestId_ = topicInvestId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.topicInvestName_ = topicInvestName_;
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          componentObj_ = componentObj_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.componentObj_ = componentObj_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData) {
          return mergeFrom((com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData other) {
        if (other == com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData.getDefaultInstance()) return this;
        if (other.hasTopicInvestId()) {
          setTopicInvestId(other.getTopicInvestId());
        }
        if (other.hasTopicInvestName()) {
          bitField0_ |= 0x00000002;
          topicInvestName_ = other.topicInvestName_;
          onChanged();
        }
        if (!other.componentObj_.isEmpty()) {
          if (componentObj_.isEmpty()) {
            componentObj_ = other.componentObj_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureComponentObjIsMutable();
            componentObj_.addAll(other.componentObj_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasTopicInvestId()) {
          
          return false;
        }
        if (!hasTopicInvestName()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gw.dzhyun.svc.topicinvest.Topicinvestdata.TopicInvestData) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int topicInvestId_ ;
      /**
       * <code>required int32 TopicInvestId = 1;</code>
       */
      public boolean hasTopicInvestId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 TopicInvestId = 1;</code>
       */
      public int getTopicInvestId() {
        return topicInvestId_;
      }
      /**
       * <code>required int32 TopicInvestId = 1;</code>
       */
      public Builder setTopicInvestId(int value) {
        bitField0_ |= 0x00000001;
        topicInvestId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 TopicInvestId = 1;</code>
       */
      public Builder clearTopicInvestId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        topicInvestId_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object topicInvestName_ = "";
      /**
       * <code>required string TopicInvestName = 2;</code>
       */
      public boolean hasTopicInvestName() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string TopicInvestName = 2;</code>
       */
      public java.lang.String getTopicInvestName() {
        java.lang.Object ref = topicInvestName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            topicInvestName_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string TopicInvestName = 2;</code>
       */
      public com.google.protobuf.ByteString
          getTopicInvestNameBytes() {
        java.lang.Object ref = topicInvestName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          topicInvestName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string TopicInvestName = 2;</code>
       */
      public Builder setTopicInvestName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        topicInvestName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string TopicInvestName = 2;</code>
       */
      public Builder clearTopicInvestName() {
        bitField0_ = (bitField0_ & ~0x00000002);
        topicInvestName_ = getDefaultInstance().getTopicInvestName();
        onChanged();
        return this;
      }
      /**
       * <code>required string TopicInvestName = 2;</code>
       */
      public Builder setTopicInvestNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        topicInvestName_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringList componentObj_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureComponentObjIsMutable() {
        if (!((bitField0_ & 0x00000004) == 0x00000004)) {
          componentObj_ = new com.google.protobuf.LazyStringArrayList(componentObj_);
          bitField0_ |= 0x00000004;
         }
      }
      /**
       * <code>repeated string ComponentObj = 3;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getComponentObjList() {
        return componentObj_.getUnmodifiableView();
      }
      /**
       * <code>repeated string ComponentObj = 3;</code>
       */
      public int getComponentObjCount() {
        return componentObj_.size();
      }
      /**
       * <code>repeated string ComponentObj = 3;</code>
       */
      public java.lang.String getComponentObj(int index) {
        return componentObj_.get(index);
      }
      /**
       * <code>repeated string ComponentObj = 3;</code>
       */
      public com.google.protobuf.ByteString
          getComponentObjBytes(int index) {
        return componentObj_.getByteString(index);
      }
      /**
       * <code>repeated string ComponentObj = 3;</code>
       */
      public Builder setComponentObj(
          int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureComponentObjIsMutable();
        componentObj_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string ComponentObj = 3;</code>
       */
      public Builder addComponentObj(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureComponentObjIsMutable();
        componentObj_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string ComponentObj = 3;</code>
       */
      public Builder addAllComponentObj(
          java.lang.Iterable<java.lang.String> values) {
        ensureComponentObjIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, componentObj_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string ComponentObj = 3;</code>
       */
      public Builder clearComponentObj() {
        componentObj_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string ComponentObj = 3;</code>
       */
      public Builder addComponentObjBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureComponentObjIsMutable();
        componentObj_.add(value);
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:dzhyun.TopicInvestData)
    }

    static {
      defaultInstance = new TopicInvestData(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:dzhyun.TopicInvestData)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_dzhyun_TopicInvestData_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_dzhyun_TopicInvestData_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025topicinvestdata.proto\022\006dzhyun\"W\n\017Topic" +
      "InvestData\022\025\n\rTopicInvestId\030\001 \002(\005\022\027\n\017Top" +
      "icInvestName\030\002 \002(\t\022\024\n\014ComponentObj\030\003 \003(\t" +
      "B\037\n\035com.gw.dzhyun.svc.topicinvest"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_dzhyun_TopicInvestData_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_dzhyun_TopicInvestData_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_dzhyun_TopicInvestData_descriptor,
        new java.lang.String[] { "TopicInvestId", "TopicInvestName", "ComponentObj", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
