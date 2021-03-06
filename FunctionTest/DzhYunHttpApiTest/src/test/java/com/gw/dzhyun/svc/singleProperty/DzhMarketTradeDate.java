// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: dzhMarketTradeDate.proto

package com.gw.dzhyun.svc.singleProperty;

public final class DzhMarketTradeDate {
  private DzhMarketTradeDate() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface MarketTradeDateOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.gw.dzhyun.svc.singleProperty.MarketTradeDate)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string Market = 1;</code>
     *
     * <pre>
     * 交易市场
     * </pre>
     */
    boolean hasMarket();
    /**
     * <code>required string Market = 1;</code>
     *
     * <pre>
     * 交易市场
     * </pre>
     */
    java.lang.String getMarket();
    /**
     * <code>required string Market = 1;</code>
     *
     * <pre>
     * 交易市场
     * </pre>
     */
    com.google.protobuf.ByteString
        getMarketBytes();

    /**
     * <code>repeated string TradeDate = 2;</code>
     *
     * <pre>
     *　最近两年可交易日期列表
     * </pre>
     */
    com.google.protobuf.ProtocolStringList
        getTradeDateList();
    /**
     * <code>repeated string TradeDate = 2;</code>
     *
     * <pre>
     *　最近两年可交易日期列表
     * </pre>
     */
    int getTradeDateCount();
    /**
     * <code>repeated string TradeDate = 2;</code>
     *
     * <pre>
     *　最近两年可交易日期列表
     * </pre>
     */
    java.lang.String getTradeDate(int index);
    /**
     * <code>repeated string TradeDate = 2;</code>
     *
     * <pre>
     *　最近两年可交易日期列表
     * </pre>
     */
    com.google.protobuf.ByteString
        getTradeDateBytes(int index);
  }
  /**
   * Protobuf type {@code com.gw.dzhyun.svc.singleProperty.MarketTradeDate}
   *
   * <pre>
   * 市场交易日期列表(最近两年) 
   * </pre>
   */
  public static final class MarketTradeDate extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:com.gw.dzhyun.svc.singleProperty.MarketTradeDate)
      MarketTradeDateOrBuilder {
    // Use MarketTradeDate.newBuilder() to construct.
    private MarketTradeDate(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private MarketTradeDate(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final MarketTradeDate defaultInstance;
    public static MarketTradeDate getDefaultInstance() {
      return defaultInstance;
    }

    public MarketTradeDate getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private MarketTradeDate(
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
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              market_ = bs;
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                tradeDate_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000002;
              }
              tradeDate_.add(bs);
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
        if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
          tradeDate_ = tradeDate_.getUnmodifiableView();
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.internal_static_com_gw_dzhyun_svc_singleProperty_MarketTradeDate_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.internal_static_com_gw_dzhyun_svc_singleProperty_MarketTradeDate_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate.class, com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate.Builder.class);
    }

    public static com.google.protobuf.Parser<MarketTradeDate> PARSER =
        new com.google.protobuf.AbstractParser<MarketTradeDate>() {
      public MarketTradeDate parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new MarketTradeDate(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<MarketTradeDate> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int MARKET_FIELD_NUMBER = 1;
    private java.lang.Object market_;
    /**
     * <code>required string Market = 1;</code>
     *
     * <pre>
     * 交易市场
     * </pre>
     */
    public boolean hasMarket() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string Market = 1;</code>
     *
     * <pre>
     * 交易市场
     * </pre>
     */
    public java.lang.String getMarket() {
      java.lang.Object ref = market_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          market_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string Market = 1;</code>
     *
     * <pre>
     * 交易市场
     * </pre>
     */
    public com.google.protobuf.ByteString
        getMarketBytes() {
      java.lang.Object ref = market_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        market_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TRADEDATE_FIELD_NUMBER = 2;
    private com.google.protobuf.LazyStringList tradeDate_;
    /**
     * <code>repeated string TradeDate = 2;</code>
     *
     * <pre>
     *　最近两年可交易日期列表
     * </pre>
     */
    public com.google.protobuf.ProtocolStringList
        getTradeDateList() {
      return tradeDate_;
    }
    /**
     * <code>repeated string TradeDate = 2;</code>
     *
     * <pre>
     *　最近两年可交易日期列表
     * </pre>
     */
    public int getTradeDateCount() {
      return tradeDate_.size();
    }
    /**
     * <code>repeated string TradeDate = 2;</code>
     *
     * <pre>
     *　最近两年可交易日期列表
     * </pre>
     */
    public java.lang.String getTradeDate(int index) {
      return tradeDate_.get(index);
    }
    /**
     * <code>repeated string TradeDate = 2;</code>
     *
     * <pre>
     *　最近两年可交易日期列表
     * </pre>
     */
    public com.google.protobuf.ByteString
        getTradeDateBytes(int index) {
      return tradeDate_.getByteString(index);
    }

    private void initFields() {
      market_ = "";
      tradeDate_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasMarket()) {
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
        output.writeBytes(1, getMarketBytes());
      }
      for (int i = 0; i < tradeDate_.size(); i++) {
        output.writeBytes(2, tradeDate_.getByteString(i));
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
          .computeBytesSize(1, getMarketBytes());
      }
      {
        int dataSize = 0;
        for (int i = 0; i < tradeDate_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeBytesSizeNoTag(tradeDate_.getByteString(i));
        }
        size += dataSize;
        size += 1 * getTradeDateList().size();
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

    public static com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate prototype) {
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
     * Protobuf type {@code com.gw.dzhyun.svc.singleProperty.MarketTradeDate}
     *
     * <pre>
     * 市场交易日期列表(最近两年) 
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.gw.dzhyun.svc.singleProperty.MarketTradeDate)
        com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDateOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.internal_static_com_gw_dzhyun_svc_singleProperty_MarketTradeDate_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.internal_static_com_gw_dzhyun_svc_singleProperty_MarketTradeDate_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate.class, com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate.Builder.class);
      }

      // Construct using com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate.newBuilder()
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
        market_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        tradeDate_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.internal_static_com_gw_dzhyun_svc_singleProperty_MarketTradeDate_descriptor;
      }

      public com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate getDefaultInstanceForType() {
        return com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate.getDefaultInstance();
      }

      public com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate build() {
        com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate buildPartial() {
        com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate result = new com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.market_ = market_;
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          tradeDate_ = tradeDate_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.tradeDate_ = tradeDate_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate) {
          return mergeFrom((com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate other) {
        if (other == com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate.getDefaultInstance()) return this;
        if (other.hasMarket()) {
          bitField0_ |= 0x00000001;
          market_ = other.market_;
          onChanged();
        }
        if (!other.tradeDate_.isEmpty()) {
          if (tradeDate_.isEmpty()) {
            tradeDate_ = other.tradeDate_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureTradeDateIsMutable();
            tradeDate_.addAll(other.tradeDate_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasMarket()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gw.dzhyun.svc.singleProperty.DzhMarketTradeDate.MarketTradeDate) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object market_ = "";
      /**
       * <code>required string Market = 1;</code>
       *
       * <pre>
       * 交易市场
       * </pre>
       */
      public boolean hasMarket() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string Market = 1;</code>
       *
       * <pre>
       * 交易市场
       * </pre>
       */
      public java.lang.String getMarket() {
        java.lang.Object ref = market_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            market_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string Market = 1;</code>
       *
       * <pre>
       * 交易市场
       * </pre>
       */
      public com.google.protobuf.ByteString
          getMarketBytes() {
        java.lang.Object ref = market_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          market_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string Market = 1;</code>
       *
       * <pre>
       * 交易市场
       * </pre>
       */
      public Builder setMarket(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        market_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string Market = 1;</code>
       *
       * <pre>
       * 交易市场
       * </pre>
       */
      public Builder clearMarket() {
        bitField0_ = (bitField0_ & ~0x00000001);
        market_ = getDefaultInstance().getMarket();
        onChanged();
        return this;
      }
      /**
       * <code>required string Market = 1;</code>
       *
       * <pre>
       * 交易市场
       * </pre>
       */
      public Builder setMarketBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        market_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringList tradeDate_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureTradeDateIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          tradeDate_ = new com.google.protobuf.LazyStringArrayList(tradeDate_);
          bitField0_ |= 0x00000002;
         }
      }
      /**
       * <code>repeated string TradeDate = 2;</code>
       *
       * <pre>
       *　最近两年可交易日期列表
       * </pre>
       */
      public com.google.protobuf.ProtocolStringList
          getTradeDateList() {
        return tradeDate_.getUnmodifiableView();
      }
      /**
       * <code>repeated string TradeDate = 2;</code>
       *
       * <pre>
       *　最近两年可交易日期列表
       * </pre>
       */
      public int getTradeDateCount() {
        return tradeDate_.size();
      }
      /**
       * <code>repeated string TradeDate = 2;</code>
       *
       * <pre>
       *　最近两年可交易日期列表
       * </pre>
       */
      public java.lang.String getTradeDate(int index) {
        return tradeDate_.get(index);
      }
      /**
       * <code>repeated string TradeDate = 2;</code>
       *
       * <pre>
       *　最近两年可交易日期列表
       * </pre>
       */
      public com.google.protobuf.ByteString
          getTradeDateBytes(int index) {
        return tradeDate_.getByteString(index);
      }
      /**
       * <code>repeated string TradeDate = 2;</code>
       *
       * <pre>
       *　最近两年可交易日期列表
       * </pre>
       */
      public Builder setTradeDate(
          int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureTradeDateIsMutable();
        tradeDate_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string TradeDate = 2;</code>
       *
       * <pre>
       *　最近两年可交易日期列表
       * </pre>
       */
      public Builder addTradeDate(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureTradeDateIsMutable();
        tradeDate_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string TradeDate = 2;</code>
       *
       * <pre>
       *　最近两年可交易日期列表
       * </pre>
       */
      public Builder addAllTradeDate(
          java.lang.Iterable<java.lang.String> values) {
        ensureTradeDateIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, tradeDate_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string TradeDate = 2;</code>
       *
       * <pre>
       *　最近两年可交易日期列表
       * </pre>
       */
      public Builder clearTradeDate() {
        tradeDate_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string TradeDate = 2;</code>
       *
       * <pre>
       *　最近两年可交易日期列表
       * </pre>
       */
      public Builder addTradeDateBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureTradeDateIsMutable();
        tradeDate_.add(value);
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.gw.dzhyun.svc.singleProperty.MarketTradeDate)
    }

    static {
      defaultInstance = new MarketTradeDate(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.gw.dzhyun.svc.singleProperty.MarketTradeDate)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_gw_dzhyun_svc_singleProperty_MarketTradeDate_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_gw_dzhyun_svc_singleProperty_MarketTradeDate_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030dzhMarketTradeDate.proto\022 com.gw.dzhyu" +
      "n.svc.singleProperty\"4\n\017MarketTradeDate\022" +
      "\016\n\006Market\030\001 \002(\t\022\021\n\tTradeDate\030\002 \003(\tB\"\n co" +
      "m.gw.dzhyun.svc.singleProperty"
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
    internal_static_com_gw_dzhyun_svc_singleProperty_MarketTradeDate_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_gw_dzhyun_svc_singleProperty_MarketTradeDate_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_gw_dzhyun_svc_singleProperty_MarketTradeDate_descriptor,
        new java.lang.String[] { "Market", "TradeDate", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
