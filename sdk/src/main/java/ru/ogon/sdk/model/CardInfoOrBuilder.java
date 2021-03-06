// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pb/apis/mobile.model.proto

package ru.ogon.sdk.model;

public interface CardInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb.CardInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string card_network = 1;</code>
   * @return The cardNetwork.
   */
  java.lang.String getCardNetwork();
  /**
   * <code>string card_network = 1;</code>
   * @return The bytes for cardNetwork.
   */
  com.google.protobuf.ByteString
      getCardNetworkBytes();

  /**
   * <code>string card_details = 2;</code>
   * @return The cardDetails.
   */
  java.lang.String getCardDetails();
  /**
   * <code>string card_details = 2;</code>
   * @return The bytes for cardDetails.
   */
  com.google.protobuf.ByteString
      getCardDetailsBytes();

  /**
   * <code>.pb.Address billing_address = 3;</code>
   * @return Whether the billingAddress field is set.
   */
  boolean hasBillingAddress();
  /**
   * <code>.pb.Address billing_address = 3;</code>
   * @return The billingAddress.
   */
  ru.ogon.sdk.model.Address getBillingAddress();
  /**
   * <code>.pb.Address billing_address = 3;</code>
   */
  ru.ogon.sdk.model.AddressOrBuilder getBillingAddressOrBuilder();
}
