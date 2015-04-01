// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CldJiaoYiPinZhongShuXing.proto

package com.dzhyun.proto;

public final class ProtoEntityBreedProperty {
  private ProtoEntityBreedProperty() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface JiaoYiPinZhongShuXingOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 id = 1;
    /**
     * <code>required int32 id = 1;</code>
     *
     * <pre>
     * ID
     * </pre>
     */
    boolean hasId();
    /**
     * <code>required int32 id = 1;</code>
     *
     * <pre>
     * ID
     * </pre>
     */
    int getId();

    // required string oCode = 2;
    /**
     * <code>required string oCode = 2;</code>
     *
     * <pre>
     * 交易品种代码
     * </pre>
     */
    boolean hasOCode();
    /**
     * <code>required string oCode = 2;</code>
     *
     * <pre>
     * 交易品种代码
     * </pre>
     */
    java.lang.String getOCode();
    /**
     * <code>required string oCode = 2;</code>
     *
     * <pre>
     * 交易品种代码
     * </pre>
     */
    com.google.protobuf.ByteString
        getOCodeBytes();

    // required string zhongWenJianCheng = 3;
    /**
     * <code>required string zhongWenJianCheng = 3;</code>
     *
     * <pre>
     * 中文简称
     * </pre>
     */
    boolean hasZhongWenJianCheng();
    /**
     * <code>required string zhongWenJianCheng = 3;</code>
     *
     * <pre>
     * 中文简称
     * </pre>
     */
    java.lang.String getZhongWenJianCheng();
    /**
     * <code>required string zhongWenJianCheng = 3;</code>
     *
     * <pre>
     * 中文简称
     * </pre>
     */
    com.google.protobuf.ByteString
        getZhongWenJianChengBytes();

    // required string tongYongMing = 4;
    /**
     * <code>required string tongYongMing = 4;</code>
     *
     * <pre>
     * 通用名
     * </pre>
     */
    boolean hasTongYongMing();
    /**
     * <code>required string tongYongMing = 4;</code>
     *
     * <pre>
     * 通用名
     * </pre>
     */
    java.lang.String getTongYongMing();
    /**
     * <code>required string tongYongMing = 4;</code>
     *
     * <pre>
     * 通用名
     * </pre>
     */
    com.google.protobuf.ByteString
        getTongYongMingBytes();

    // required string updateTime = 5;
    /**
     * <code>required string updateTime = 5;</code>
     *
     * <pre>
     * 更新时间
     * </pre>
     */
    boolean hasUpdateTime();
    /**
     * <code>required string updateTime = 5;</code>
     *
     * <pre>
     * 更新时间
     * </pre>
     */
    java.lang.String getUpdateTime();
    /**
     * <code>required string updateTime = 5;</code>
     *
     * <pre>
     * 更新时间
     * </pre>
     */
    com.google.protobuf.ByteString
        getUpdateTimeBytes();
  }
  /**
   * Protobuf type {@code dzhyun.JiaoYiPinZhongShuXing}
   *
   * <pre>
   * 交易品种-交易品种属性 
   * </pre>
   */
  public static final class JiaoYiPinZhongShuXing extends
      com.google.protobuf.GeneratedMessage
      implements JiaoYiPinZhongShuXingOrBuilder {
    // Use JiaoYiPinZhongShuXing.newBuilder() to construct.
    private JiaoYiPinZhongShuXing(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private JiaoYiPinZhongShuXing(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final JiaoYiPinZhongShuXing defaultInstance;
    public static JiaoYiPinZhongShuXing getDefaultInstance() {
      return defaultInstance;
    }

    public JiaoYiPinZhongShuXing getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private JiaoYiPinZhongShuXing(
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
              id_ = input.readInt32();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              oCode_ = input.readBytes();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              zhongWenJianCheng_ = input.readBytes();
              break;
            }
            case 34: {
              bitField0_ |= 0x00000008;
              tongYongMing_ = input.readBytes();
              break;
            }
            case 42: {
              bitField0_ |= 0x00000010;
              updateTime_ = input.readBytes();
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
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.dzhyun.proto.ProtoEntityBreedProperty.internal_static_dzhyun_JiaoYiPinZhongShuXing_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.dzhyun.proto.ProtoEntityBreedProperty.internal_static_dzhyun_JiaoYiPinZhongShuXing_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing.class, com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing.Builder.class);
    }

    public static com.google.protobuf.Parser<JiaoYiPinZhongShuXing> PARSER =
        new com.google.protobuf.AbstractParser<JiaoYiPinZhongShuXing>() {
      public JiaoYiPinZhongShuXing parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new JiaoYiPinZhongShuXing(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<JiaoYiPinZhongShuXing> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 id = 1;
    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <code>required int32 id = 1;</code>
     *
     * <pre>
     * ID
     * </pre>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 id = 1;</code>
     *
     * <pre>
     * ID
     * </pre>
     */
    public int getId() {
      return id_;
    }

    // required string oCode = 2;
    public static final int OCODE_FIELD_NUMBER = 2;
    private java.lang.Object oCode_;
    /**
     * <code>required string oCode = 2;</code>
     *
     * <pre>
     * 交易品种代码
     * </pre>
     */
    public boolean hasOCode() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string oCode = 2;</code>
     *
     * <pre>
     * 交易品种代码
     * </pre>
     */
    public java.lang.String getOCode() {
      java.lang.Object ref = oCode_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          oCode_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string oCode = 2;</code>
     *
     * <pre>
     * 交易品种代码
     * </pre>
     */
    public com.google.protobuf.ByteString
        getOCodeBytes() {
      java.lang.Object ref = oCode_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        oCode_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required string zhongWenJianCheng = 3;
    public static final int ZHONGWENJIANCHENG_FIELD_NUMBER = 3;
    private java.lang.Object zhongWenJianCheng_;
    /**
     * <code>required string zhongWenJianCheng = 3;</code>
     *
     * <pre>
     * 中文简称
     * </pre>
     */
    public boolean hasZhongWenJianCheng() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string zhongWenJianCheng = 3;</code>
     *
     * <pre>
     * 中文简称
     * </pre>
     */
    public java.lang.String getZhongWenJianCheng() {
      java.lang.Object ref = zhongWenJianCheng_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          zhongWenJianCheng_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string zhongWenJianCheng = 3;</code>
     *
     * <pre>
     * 中文简称
     * </pre>
     */
    public com.google.protobuf.ByteString
        getZhongWenJianChengBytes() {
      java.lang.Object ref = zhongWenJianCheng_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        zhongWenJianCheng_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required string tongYongMing = 4;
    public static final int TONGYONGMING_FIELD_NUMBER = 4;
    private java.lang.Object tongYongMing_;
    /**
     * <code>required string tongYongMing = 4;</code>
     *
     * <pre>
     * 通用名
     * </pre>
     */
    public boolean hasTongYongMing() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required string tongYongMing = 4;</code>
     *
     * <pre>
     * 通用名
     * </pre>
     */
    public java.lang.String getTongYongMing() {
      java.lang.Object ref = tongYongMing_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          tongYongMing_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string tongYongMing = 4;</code>
     *
     * <pre>
     * 通用名
     * </pre>
     */
    public com.google.protobuf.ByteString
        getTongYongMingBytes() {
      java.lang.Object ref = tongYongMing_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        tongYongMing_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required string updateTime = 5;
    public static final int UPDATETIME_FIELD_NUMBER = 5;
    private java.lang.Object updateTime_;
    /**
     * <code>required string updateTime = 5;</code>
     *
     * <pre>
     * 更新时间
     * </pre>
     */
    public boolean hasUpdateTime() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required string updateTime = 5;</code>
     *
     * <pre>
     * 更新时间
     * </pre>
     */
    public java.lang.String getUpdateTime() {
      java.lang.Object ref = updateTime_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          updateTime_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string updateTime = 5;</code>
     *
     * <pre>
     * 更新时间
     * </pre>
     */
    public com.google.protobuf.ByteString
        getUpdateTimeBytes() {
      java.lang.Object ref = updateTime_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        updateTime_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      id_ = 0;
      oCode_ = "";
      zhongWenJianCheng_ = "";
      tongYongMing_ = "";
      updateTime_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasOCode()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasZhongWenJianCheng()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasTongYongMing()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasUpdateTime()) {
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
        output.writeInt32(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getOCodeBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getZhongWenJianChengBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, getTongYongMingBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeBytes(5, getUpdateTimeBytes());
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
          .computeInt32Size(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getOCodeBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getZhongWenJianChengBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, getTongYongMingBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(5, getUpdateTimeBytes());
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

    public static com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing prototype) {
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
     * Protobuf type {@code dzhyun.JiaoYiPinZhongShuXing}
     *
     * <pre>
     * 交易品种-交易品种属性 
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXingOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.dzhyun.proto.ProtoEntityBreedProperty.internal_static_dzhyun_JiaoYiPinZhongShuXing_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.dzhyun.proto.ProtoEntityBreedProperty.internal_static_dzhyun_JiaoYiPinZhongShuXing_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing.class, com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing.Builder.class);
      }

      // Construct using com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing.newBuilder()
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
        id_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        oCode_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        zhongWenJianCheng_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        tongYongMing_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        updateTime_ = "";
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.dzhyun.proto.ProtoEntityBreedProperty.internal_static_dzhyun_JiaoYiPinZhongShuXing_descriptor;
      }

      public com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing getDefaultInstanceForType() {
        return com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing.getDefaultInstance();
      }

      public com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing build() {
        com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing buildPartial() {
        com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing result = new com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.id_ = id_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.oCode_ = oCode_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.zhongWenJianCheng_ = zhongWenJianCheng_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.tongYongMing_ = tongYongMing_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.updateTime_ = updateTime_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing) {
          return mergeFrom((com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing other) {
        if (other == com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing.getDefaultInstance()) return this;
        if (other.hasId()) {
          setId(other.getId());
        }
        if (other.hasOCode()) {
          bitField0_ |= 0x00000002;
          oCode_ = other.oCode_;
          onChanged();
        }
        if (other.hasZhongWenJianCheng()) {
          bitField0_ |= 0x00000004;
          zhongWenJianCheng_ = other.zhongWenJianCheng_;
          onChanged();
        }
        if (other.hasTongYongMing()) {
          bitField0_ |= 0x00000008;
          tongYongMing_ = other.tongYongMing_;
          onChanged();
        }
        if (other.hasUpdateTime()) {
          bitField0_ |= 0x00000010;
          updateTime_ = other.updateTime_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasId()) {
          
          return false;
        }
        if (!hasOCode()) {
          
          return false;
        }
        if (!hasZhongWenJianCheng()) {
          
          return false;
        }
        if (!hasTongYongMing()) {
          
          return false;
        }
        if (!hasUpdateTime()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.dzhyun.proto.ProtoEntityBreedProperty.JiaoYiPinZhongShuXing) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 id = 1;
      private int id_ ;
      /**
       * <code>required int32 id = 1;</code>
       *
       * <pre>
       * ID
       * </pre>
       */
      public boolean hasId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 id = 1;</code>
       *
       * <pre>
       * ID
       * </pre>
       */
      public int getId() {
        return id_;
      }
      /**
       * <code>required int32 id = 1;</code>
       *
       * <pre>
       * ID
       * </pre>
       */
      public Builder setId(int value) {
        bitField0_ |= 0x00000001;
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 id = 1;</code>
       *
       * <pre>
       * ID
       * </pre>
       */
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        id_ = 0;
        onChanged();
        return this;
      }

      // required string oCode = 2;
      private java.lang.Object oCode_ = "";
      /**
       * <code>required string oCode = 2;</code>
       *
       * <pre>
       * 交易品种代码
       * </pre>
       */
      public boolean hasOCode() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string oCode = 2;</code>
       *
       * <pre>
       * 交易品种代码
       * </pre>
       */
      public java.lang.String getOCode() {
        java.lang.Object ref = oCode_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          oCode_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string oCode = 2;</code>
       *
       * <pre>
       * 交易品种代码
       * </pre>
       */
      public com.google.protobuf.ByteString
          getOCodeBytes() {
        java.lang.Object ref = oCode_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          oCode_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string oCode = 2;</code>
       *
       * <pre>
       * 交易品种代码
       * </pre>
       */
      public Builder setOCode(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        oCode_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string oCode = 2;</code>
       *
       * <pre>
       * 交易品种代码
       * </pre>
       */
      public Builder clearOCode() {
        bitField0_ = (bitField0_ & ~0x00000002);
        oCode_ = getDefaultInstance().getOCode();
        onChanged();
        return this;
      }
      /**
       * <code>required string oCode = 2;</code>
       *
       * <pre>
       * 交易品种代码
       * </pre>
       */
      public Builder setOCodeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        oCode_ = value;
        onChanged();
        return this;
      }

      // required string zhongWenJianCheng = 3;
      private java.lang.Object zhongWenJianCheng_ = "";
      /**
       * <code>required string zhongWenJianCheng = 3;</code>
       *
       * <pre>
       * 中文简称
       * </pre>
       */
      public boolean hasZhongWenJianCheng() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string zhongWenJianCheng = 3;</code>
       *
       * <pre>
       * 中文简称
       * </pre>
       */
      public java.lang.String getZhongWenJianCheng() {
        java.lang.Object ref = zhongWenJianCheng_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          zhongWenJianCheng_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string zhongWenJianCheng = 3;</code>
       *
       * <pre>
       * 中文简称
       * </pre>
       */
      public com.google.protobuf.ByteString
          getZhongWenJianChengBytes() {
        java.lang.Object ref = zhongWenJianCheng_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          zhongWenJianCheng_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string zhongWenJianCheng = 3;</code>
       *
       * <pre>
       * 中文简称
       * </pre>
       */
      public Builder setZhongWenJianCheng(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        zhongWenJianCheng_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string zhongWenJianCheng = 3;</code>
       *
       * <pre>
       * 中文简称
       * </pre>
       */
      public Builder clearZhongWenJianCheng() {
        bitField0_ = (bitField0_ & ~0x00000004);
        zhongWenJianCheng_ = getDefaultInstance().getZhongWenJianCheng();
        onChanged();
        return this;
      }
      /**
       * <code>required string zhongWenJianCheng = 3;</code>
       *
       * <pre>
       * 中文简称
       * </pre>
       */
      public Builder setZhongWenJianChengBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        zhongWenJianCheng_ = value;
        onChanged();
        return this;
      }

      // required string tongYongMing = 4;
      private java.lang.Object tongYongMing_ = "";
      /**
       * <code>required string tongYongMing = 4;</code>
       *
       * <pre>
       * 通用名
       * </pre>
       */
      public boolean hasTongYongMing() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required string tongYongMing = 4;</code>
       *
       * <pre>
       * 通用名
       * </pre>
       */
      public java.lang.String getTongYongMing() {
        java.lang.Object ref = tongYongMing_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          tongYongMing_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string tongYongMing = 4;</code>
       *
       * <pre>
       * 通用名
       * </pre>
       */
      public com.google.protobuf.ByteString
          getTongYongMingBytes() {
        java.lang.Object ref = tongYongMing_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          tongYongMing_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string tongYongMing = 4;</code>
       *
       * <pre>
       * 通用名
       * </pre>
       */
      public Builder setTongYongMing(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        tongYongMing_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string tongYongMing = 4;</code>
       *
       * <pre>
       * 通用名
       * </pre>
       */
      public Builder clearTongYongMing() {
        bitField0_ = (bitField0_ & ~0x00000008);
        tongYongMing_ = getDefaultInstance().getTongYongMing();
        onChanged();
        return this;
      }
      /**
       * <code>required string tongYongMing = 4;</code>
       *
       * <pre>
       * 通用名
       * </pre>
       */
      public Builder setTongYongMingBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        tongYongMing_ = value;
        onChanged();
        return this;
      }

      // required string updateTime = 5;
      private java.lang.Object updateTime_ = "";
      /**
       * <code>required string updateTime = 5;</code>
       *
       * <pre>
       * 更新时间
       * </pre>
       */
      public boolean hasUpdateTime() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>required string updateTime = 5;</code>
       *
       * <pre>
       * 更新时间
       * </pre>
       */
      public java.lang.String getUpdateTime() {
        java.lang.Object ref = updateTime_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          updateTime_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string updateTime = 5;</code>
       *
       * <pre>
       * 更新时间
       * </pre>
       */
      public com.google.protobuf.ByteString
          getUpdateTimeBytes() {
        java.lang.Object ref = updateTime_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          updateTime_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string updateTime = 5;</code>
       *
       * <pre>
       * 更新时间
       * </pre>
       */
      public Builder setUpdateTime(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        updateTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string updateTime = 5;</code>
       *
       * <pre>
       * 更新时间
       * </pre>
       */
      public Builder clearUpdateTime() {
        bitField0_ = (bitField0_ & ~0x00000010);
        updateTime_ = getDefaultInstance().getUpdateTime();
        onChanged();
        return this;
      }
      /**
       * <code>required string updateTime = 5;</code>
       *
       * <pre>
       * 更新时间
       * </pre>
       */
      public Builder setUpdateTimeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        updateTime_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:dzhyun.JiaoYiPinZhongShuXing)
    }

    static {
      defaultInstance = new JiaoYiPinZhongShuXing(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:dzhyun.JiaoYiPinZhongShuXing)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_dzhyun_JiaoYiPinZhongShuXing_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_dzhyun_JiaoYiPinZhongShuXing_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036CldJiaoYiPinZhongShuXing.proto\022\006dzhyun" +
      "\"w\n\025JiaoYiPinZhongShuXing\022\n\n\002id\030\001 \002(\005\022\r\n" +
      "\005oCode\030\002 \002(\t\022\031\n\021zhongWenJianCheng\030\003 \002(\t\022" +
      "\024\n\014tongYongMing\030\004 \002(\t\022\022\n\nupdateTime\030\005 \002(" +
      "\tB,\n\020com.dzhyun.protoB\030ProtoEntityBreedP" +
      "roperty"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_dzhyun_JiaoYiPinZhongShuXing_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_dzhyun_JiaoYiPinZhongShuXing_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_dzhyun_JiaoYiPinZhongShuXing_descriptor,
              new java.lang.String[] { "Id", "OCode", "ZhongWenJianCheng", "TongYongMing", "UpdateTime", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
