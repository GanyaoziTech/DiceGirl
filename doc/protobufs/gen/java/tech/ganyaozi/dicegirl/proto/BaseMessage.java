// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: IM.main.proto

package tech.ganyaozi.dicegirl.proto;

public final class BaseMessage {
  private BaseMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code tech.ganyaozi.dicegirl.proto.Commands}
   */
  public enum Commands
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>IM_PING_PONG = 0;</code>
     */
    IM_PING_PONG(0),
    /**
     * <code>IM_SECURE_KEY = 1;</code>
     */
    IM_SECURE_KEY(1),
    /**
     * <code>IM_CREATE_ROOM_REQ = 2;</code>
     */
    IM_CREATE_ROOM_REQ(2),
    /**
     * <code>IM_CREATE_ROOM_RES = 3;</code>
     */
    IM_CREATE_ROOM_RES(3),
    /**
     * <code>IM_ENTER_ROOM_REQ = 4;</code>
     */
    IM_ENTER_ROOM_REQ(4),
    /**
     * <code>IM_ENTER_ROOM_RES = 5;</code>
     */
    IM_ENTER_ROOM_RES(5),
    /**
     * <code>IM_EXIT_ROOM_REQ = 6;</code>
     */
    IM_EXIT_ROOM_REQ(6),
    /**
     * <code>IM_EXIT_ROOM_RES = 7;</code>
     */
    IM_EXIT_ROOM_RES(7),
    /**
     * <code>IM_UPDATE_ROOM_INFO_REQ = 8;</code>
     */
    IM_UPDATE_ROOM_INFO_REQ(8),
    /**
     * <code>IM_UPDATE_ROOM_INFO_RES = 9;</code>
     */
    IM_UPDATE_ROOM_INFO_RES(9),
    /**
     * <code>IM_MODIFY_ROOM_INFO_REQ = 10;</code>
     */
    IM_MODIFY_ROOM_INFO_REQ(10),
    /**
     * <code>IM_MODIFY_ROOM_INFO_RES = 11;</code>
     */
    IM_MODIFY_ROOM_INFO_RES(11),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>IM_PING_PONG = 0;</code>
     */
    public static final int IM_PING_PONG_VALUE = 0;
    /**
     * <code>IM_SECURE_KEY = 1;</code>
     */
    public static final int IM_SECURE_KEY_VALUE = 1;
    /**
     * <code>IM_CREATE_ROOM_REQ = 2;</code>
     */
    public static final int IM_CREATE_ROOM_REQ_VALUE = 2;
    /**
     * <code>IM_CREATE_ROOM_RES = 3;</code>
     */
    public static final int IM_CREATE_ROOM_RES_VALUE = 3;
    /**
     * <code>IM_ENTER_ROOM_REQ = 4;</code>
     */
    public static final int IM_ENTER_ROOM_REQ_VALUE = 4;
    /**
     * <code>IM_ENTER_ROOM_RES = 5;</code>
     */
    public static final int IM_ENTER_ROOM_RES_VALUE = 5;
    /**
     * <code>IM_EXIT_ROOM_REQ = 6;</code>
     */
    public static final int IM_EXIT_ROOM_REQ_VALUE = 6;
    /**
     * <code>IM_EXIT_ROOM_RES = 7;</code>
     */
    public static final int IM_EXIT_ROOM_RES_VALUE = 7;
    /**
     * <code>IM_UPDATE_ROOM_INFO_REQ = 8;</code>
     */
    public static final int IM_UPDATE_ROOM_INFO_REQ_VALUE = 8;
    /**
     * <code>IM_UPDATE_ROOM_INFO_RES = 9;</code>
     */
    public static final int IM_UPDATE_ROOM_INFO_RES_VALUE = 9;
    /**
     * <code>IM_MODIFY_ROOM_INFO_REQ = 10;</code>
     */
    public static final int IM_MODIFY_ROOM_INFO_REQ_VALUE = 10;
    /**
     * <code>IM_MODIFY_ROOM_INFO_RES = 11;</code>
     */
    public static final int IM_MODIFY_ROOM_INFO_RES_VALUE = 11;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Commands valueOf(int value) {
      return forNumber(value);
    }

    public static Commands forNumber(int value) {
      switch (value) {
        case 0: return IM_PING_PONG;
        case 1: return IM_SECURE_KEY;
        case 2: return IM_CREATE_ROOM_REQ;
        case 3: return IM_CREATE_ROOM_RES;
        case 4: return IM_ENTER_ROOM_REQ;
        case 5: return IM_ENTER_ROOM_RES;
        case 6: return IM_EXIT_ROOM_REQ;
        case 7: return IM_EXIT_ROOM_RES;
        case 8: return IM_UPDATE_ROOM_INFO_REQ;
        case 9: return IM_UPDATE_ROOM_INFO_RES;
        case 10: return IM_MODIFY_ROOM_INFO_REQ;
        case 11: return IM_MODIFY_ROOM_INFO_RES;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Commands>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Commands> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Commands>() {
            public Commands findValueByNumber(int number) {
              return Commands.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return tech.ganyaozi.dicegirl.proto.BaseMessage.getDescriptor().getEnumTypes().get(0);
    }

    private static final Commands[] VALUES = values();

    public static Commands valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private Commands(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:tech.ganyaozi.dicegirl.proto.Commands)
  }

  public interface baseMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:tech.ganyaozi.dicegirl.proto.baseMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.tech.ganyaozi.dicegirl.proto.Commands cmd = 1;</code>
     */
    int getCmdValue();
    /**
     * <code>.tech.ganyaozi.dicegirl.proto.Commands cmd = 1;</code>
     */
    tech.ganyaozi.dicegirl.proto.BaseMessage.Commands getCmd();

    /**
     * <code>fixed64 seq = 2;</code>
     */
    long getSeq();

    /**
     * <code>string srcID = 3;</code>
     */
    java.lang.String getSrcID();
    /**
     * <code>string srcID = 3;</code>
     */
    com.google.protobuf.ByteString
        getSrcIDBytes();

    /**
     * <code>string dstID = 4;</code>
     */
    java.lang.String getDstID();
    /**
     * <code>string dstID = 4;</code>
     */
    com.google.protobuf.ByteString
        getDstIDBytes();

    /**
     * <code>bool ack = 5;</code>
     */
    boolean getAck();

    /**
     * <code>bytes content = 6;</code>
     */
    com.google.protobuf.ByteString getContent();
  }
  /**
   * Protobuf type {@code tech.ganyaozi.dicegirl.proto.baseMessage}
   */
  public  static final class baseMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:tech.ganyaozi.dicegirl.proto.baseMessage)
      baseMessageOrBuilder {
    // Use baseMessage.newBuilder() to construct.
    private baseMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private baseMessage() {
      cmd_ = 0;
      seq_ = 0L;
      srcID_ = "";
      dstID_ = "";
      ack_ = false;
      content_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private baseMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              int rawValue = input.readEnum();

              cmd_ = rawValue;
              break;
            }
            case 17: {

              seq_ = input.readFixed64();
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              srcID_ = s;
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              dstID_ = s;
              break;
            }
            case 40: {

              ack_ = input.readBool();
              break;
            }
            case 50: {

              content_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return tech.ganyaozi.dicegirl.proto.BaseMessage.internal_static_tech_ganyaozi_dicegirl_proto_baseMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return tech.ganyaozi.dicegirl.proto.BaseMessage.internal_static_tech_ganyaozi_dicegirl_proto_baseMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage.class, tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage.Builder.class);
    }

    public static final int CMD_FIELD_NUMBER = 1;
    private int cmd_;
    /**
     * <code>.tech.ganyaozi.dicegirl.proto.Commands cmd = 1;</code>
     */
    public int getCmdValue() {
      return cmd_;
    }
    /**
     * <code>.tech.ganyaozi.dicegirl.proto.Commands cmd = 1;</code>
     */
    public tech.ganyaozi.dicegirl.proto.BaseMessage.Commands getCmd() {
      tech.ganyaozi.dicegirl.proto.BaseMessage.Commands result = tech.ganyaozi.dicegirl.proto.BaseMessage.Commands.valueOf(cmd_);
      return result == null ? tech.ganyaozi.dicegirl.proto.BaseMessage.Commands.UNRECOGNIZED : result;
    }

    public static final int SEQ_FIELD_NUMBER = 2;
    private long seq_;
    /**
     * <code>fixed64 seq = 2;</code>
     */
    public long getSeq() {
      return seq_;
    }

    public static final int SRCID_FIELD_NUMBER = 3;
    private volatile java.lang.Object srcID_;
    /**
     * <code>string srcID = 3;</code>
     */
    public java.lang.String getSrcID() {
      java.lang.Object ref = srcID_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        srcID_ = s;
        return s;
      }
    }
    /**
     * <code>string srcID = 3;</code>
     */
    public com.google.protobuf.ByteString
        getSrcIDBytes() {
      java.lang.Object ref = srcID_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        srcID_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int DSTID_FIELD_NUMBER = 4;
    private volatile java.lang.Object dstID_;
    /**
     * <code>string dstID = 4;</code>
     */
    public java.lang.String getDstID() {
      java.lang.Object ref = dstID_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        dstID_ = s;
        return s;
      }
    }
    /**
     * <code>string dstID = 4;</code>
     */
    public com.google.protobuf.ByteString
        getDstIDBytes() {
      java.lang.Object ref = dstID_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        dstID_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int ACK_FIELD_NUMBER = 5;
    private boolean ack_;
    /**
     * <code>bool ack = 5;</code>
     */
    public boolean getAck() {
      return ack_;
    }

    public static final int CONTENT_FIELD_NUMBER = 6;
    private com.google.protobuf.ByteString content_;
    /**
     * <code>bytes content = 6;</code>
     */
    public com.google.protobuf.ByteString getContent() {
      return content_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (cmd_ != tech.ganyaozi.dicegirl.proto.BaseMessage.Commands.IM_PING_PONG.getNumber()) {
        output.writeEnum(1, cmd_);
      }
      if (seq_ != 0L) {
        output.writeFixed64(2, seq_);
      }
      if (!getSrcIDBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, srcID_);
      }
      if (!getDstIDBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, dstID_);
      }
      if (ack_ != false) {
        output.writeBool(5, ack_);
      }
      if (!content_.isEmpty()) {
        output.writeBytes(6, content_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (cmd_ != tech.ganyaozi.dicegirl.proto.BaseMessage.Commands.IM_PING_PONG.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, cmd_);
      }
      if (seq_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeFixed64Size(2, seq_);
      }
      if (!getSrcIDBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, srcID_);
      }
      if (!getDstIDBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, dstID_);
      }
      if (ack_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(5, ack_);
      }
      if (!content_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(6, content_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage)) {
        return super.equals(obj);
      }
      tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage other = (tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage) obj;

      boolean result = true;
      result = result && cmd_ == other.cmd_;
      result = result && (getSeq()
          == other.getSeq());
      result = result && getSrcID()
          .equals(other.getSrcID());
      result = result && getDstID()
          .equals(other.getDstID());
      result = result && (getAck()
          == other.getAck());
      result = result && getContent()
          .equals(other.getContent());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + CMD_FIELD_NUMBER;
      hash = (53 * hash) + cmd_;
      hash = (37 * hash) + SEQ_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getSeq());
      hash = (37 * hash) + SRCID_FIELD_NUMBER;
      hash = (53 * hash) + getSrcID().hashCode();
      hash = (37 * hash) + DSTID_FIELD_NUMBER;
      hash = (53 * hash) + getDstID().hashCode();
      hash = (37 * hash) + ACK_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getAck());
      hash = (37 * hash) + CONTENT_FIELD_NUMBER;
      hash = (53 * hash) + getContent().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code tech.ganyaozi.dicegirl.proto.baseMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:tech.ganyaozi.dicegirl.proto.baseMessage)
        tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return tech.ganyaozi.dicegirl.proto.BaseMessage.internal_static_tech_ganyaozi_dicegirl_proto_baseMessage_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return tech.ganyaozi.dicegirl.proto.BaseMessage.internal_static_tech_ganyaozi_dicegirl_proto_baseMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage.class, tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage.Builder.class);
      }

      // Construct using tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        cmd_ = 0;

        seq_ = 0L;

        srcID_ = "";

        dstID_ = "";

        ack_ = false;

        content_ = com.google.protobuf.ByteString.EMPTY;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return tech.ganyaozi.dicegirl.proto.BaseMessage.internal_static_tech_ganyaozi_dicegirl_proto_baseMessage_descriptor;
      }

      public tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage getDefaultInstanceForType() {
        return tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage.getDefaultInstance();
      }

      public tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage build() {
        tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage buildPartial() {
        tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage result = new tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage(this);
        result.cmd_ = cmd_;
        result.seq_ = seq_;
        result.srcID_ = srcID_;
        result.dstID_ = dstID_;
        result.ack_ = ack_;
        result.content_ = content_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage) {
          return mergeFrom((tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage other) {
        if (other == tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage.getDefaultInstance()) return this;
        if (other.cmd_ != 0) {
          setCmdValue(other.getCmdValue());
        }
        if (other.getSeq() != 0L) {
          setSeq(other.getSeq());
        }
        if (!other.getSrcID().isEmpty()) {
          srcID_ = other.srcID_;
          onChanged();
        }
        if (!other.getDstID().isEmpty()) {
          dstID_ = other.dstID_;
          onChanged();
        }
        if (other.getAck() != false) {
          setAck(other.getAck());
        }
        if (other.getContent() != com.google.protobuf.ByteString.EMPTY) {
          setContent(other.getContent());
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int cmd_ = 0;
      /**
       * <code>.tech.ganyaozi.dicegirl.proto.Commands cmd = 1;</code>
       */
      public int getCmdValue() {
        return cmd_;
      }
      /**
       * <code>.tech.ganyaozi.dicegirl.proto.Commands cmd = 1;</code>
       */
      public Builder setCmdValue(int value) {
        cmd_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>.tech.ganyaozi.dicegirl.proto.Commands cmd = 1;</code>
       */
      public tech.ganyaozi.dicegirl.proto.BaseMessage.Commands getCmd() {
        tech.ganyaozi.dicegirl.proto.BaseMessage.Commands result = tech.ganyaozi.dicegirl.proto.BaseMessage.Commands.valueOf(cmd_);
        return result == null ? tech.ganyaozi.dicegirl.proto.BaseMessage.Commands.UNRECOGNIZED : result;
      }
      /**
       * <code>.tech.ganyaozi.dicegirl.proto.Commands cmd = 1;</code>
       */
      public Builder setCmd(tech.ganyaozi.dicegirl.proto.BaseMessage.Commands value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        cmd_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.tech.ganyaozi.dicegirl.proto.Commands cmd = 1;</code>
       */
      public Builder clearCmd() {
        
        cmd_ = 0;
        onChanged();
        return this;
      }

      private long seq_ ;
      /**
       * <code>fixed64 seq = 2;</code>
       */
      public long getSeq() {
        return seq_;
      }
      /**
       * <code>fixed64 seq = 2;</code>
       */
      public Builder setSeq(long value) {
        
        seq_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>fixed64 seq = 2;</code>
       */
      public Builder clearSeq() {
        
        seq_ = 0L;
        onChanged();
        return this;
      }

      private java.lang.Object srcID_ = "";
      /**
       * <code>string srcID = 3;</code>
       */
      public java.lang.String getSrcID() {
        java.lang.Object ref = srcID_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          srcID_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string srcID = 3;</code>
       */
      public com.google.protobuf.ByteString
          getSrcIDBytes() {
        java.lang.Object ref = srcID_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          srcID_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string srcID = 3;</code>
       */
      public Builder setSrcID(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        srcID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string srcID = 3;</code>
       */
      public Builder clearSrcID() {
        
        srcID_ = getDefaultInstance().getSrcID();
        onChanged();
        return this;
      }
      /**
       * <code>string srcID = 3;</code>
       */
      public Builder setSrcIDBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        srcID_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object dstID_ = "";
      /**
       * <code>string dstID = 4;</code>
       */
      public java.lang.String getDstID() {
        java.lang.Object ref = dstID_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          dstID_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string dstID = 4;</code>
       */
      public com.google.protobuf.ByteString
          getDstIDBytes() {
        java.lang.Object ref = dstID_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          dstID_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string dstID = 4;</code>
       */
      public Builder setDstID(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        dstID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string dstID = 4;</code>
       */
      public Builder clearDstID() {
        
        dstID_ = getDefaultInstance().getDstID();
        onChanged();
        return this;
      }
      /**
       * <code>string dstID = 4;</code>
       */
      public Builder setDstIDBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        dstID_ = value;
        onChanged();
        return this;
      }

      private boolean ack_ ;
      /**
       * <code>bool ack = 5;</code>
       */
      public boolean getAck() {
        return ack_;
      }
      /**
       * <code>bool ack = 5;</code>
       */
      public Builder setAck(boolean value) {
        
        ack_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>bool ack = 5;</code>
       */
      public Builder clearAck() {
        
        ack_ = false;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString content_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>bytes content = 6;</code>
       */
      public com.google.protobuf.ByteString getContent() {
        return content_;
      }
      /**
       * <code>bytes content = 6;</code>
       */
      public Builder setContent(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        content_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>bytes content = 6;</code>
       */
      public Builder clearContent() {
        
        content_ = getDefaultInstance().getContent();
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:tech.ganyaozi.dicegirl.proto.baseMessage)
    }

    // @@protoc_insertion_point(class_scope:tech.ganyaozi.dicegirl.proto.baseMessage)
    private static final tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage();
    }

    public static tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<baseMessage>
        PARSER = new com.google.protobuf.AbstractParser<baseMessage>() {
      public baseMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new baseMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<baseMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<baseMessage> getParserForType() {
      return PARSER;
    }

    public tech.ganyaozi.dicegirl.proto.BaseMessage.baseMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tech_ganyaozi_dicegirl_proto_baseMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tech_ganyaozi_dicegirl_proto_baseMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rIM.main.proto\022\034tech.ganyaozi.dicegirl." +
      "proto\"\213\001\n\013baseMessage\0223\n\003cmd\030\001 \001(\0162&.tec" +
      "h.ganyaozi.dicegirl.proto.Commands\022\013\n\003se" +
      "q\030\002 \001(\006\022\r\n\005srcID\030\003 \001(\t\022\r\n\005dstID\030\004 \001(\t\022\013\n" +
      "\003ack\030\005 \001(\010\022\017\n\007content\030\006 \001(\014*\255\002\n\010Commands" +
      "\022\020\n\014IM_PING_PONG\020\000\022\021\n\rIM_SECURE_KEY\020\001\022\026\n" +
      "\022IM_CREATE_ROOM_REQ\020\002\022\026\n\022IM_CREATE_ROOM_" +
      "RES\020\003\022\025\n\021IM_ENTER_ROOM_REQ\020\004\022\025\n\021IM_ENTER" +
      "_ROOM_RES\020\005\022\024\n\020IM_EXIT_ROOM_REQ\020\006\022\024\n\020IM_" +
      "EXIT_ROOM_RES\020\007\022\033\n\027IM_UPDATE_ROOM_INFO_R",
      "EQ\020\010\022\033\n\027IM_UPDATE_ROOM_INFO_RES\020\t\022\033\n\027IM_" +
      "MODIFY_ROOM_INFO_REQ\020\n\022\033\n\027IM_MODIFY_ROOM" +
      "_INFO_RES\020\013B+\n\034tech.ganyaozi.dicegirl.pr" +
      "otoB\013BaseMessageb\006proto3"
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
    internal_static_tech_ganyaozi_dicegirl_proto_baseMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tech_ganyaozi_dicegirl_proto_baseMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tech_ganyaozi_dicegirl_proto_baseMessage_descriptor,
        new java.lang.String[] { "Cmd", "Seq", "SrcID", "DstID", "Ack", "Content", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}