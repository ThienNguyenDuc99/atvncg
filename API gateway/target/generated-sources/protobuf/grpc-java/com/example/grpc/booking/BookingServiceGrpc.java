package com.example.grpc.booking;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.56.0)",
    comments = "Source: booking.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BookingServiceGrpc {

  private BookingServiceGrpc() {}

  public static final String SERVICE_NAME = "BookingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcBookingRequest,
      com.example.grpc.booking.GrpcBookingResponse> getCreateBookingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBooking",
      requestType = com.example.grpc.booking.GrpcBookingRequest.class,
      responseType = com.example.grpc.booking.GrpcBookingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcBookingRequest,
      com.example.grpc.booking.GrpcBookingResponse> getCreateBookingMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcBookingRequest, com.example.grpc.booking.GrpcBookingResponse> getCreateBookingMethod;
    if ((getCreateBookingMethod = BookingServiceGrpc.getCreateBookingMethod) == null) {
      synchronized (BookingServiceGrpc.class) {
        if ((getCreateBookingMethod = BookingServiceGrpc.getCreateBookingMethod) == null) {
          BookingServiceGrpc.getCreateBookingMethod = getCreateBookingMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.booking.GrpcBookingRequest, com.example.grpc.booking.GrpcBookingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBooking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcBookingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcBookingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("CreateBooking"))
              .build();
        }
      }
    }
    return getCreateBookingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcCancelBookingRequest,
      com.example.grpc.booking.GrpcCancelBookingResponse> getCancelBookingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelBooking",
      requestType = com.example.grpc.booking.GrpcCancelBookingRequest.class,
      responseType = com.example.grpc.booking.GrpcCancelBookingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcCancelBookingRequest,
      com.example.grpc.booking.GrpcCancelBookingResponse> getCancelBookingMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcCancelBookingRequest, com.example.grpc.booking.GrpcCancelBookingResponse> getCancelBookingMethod;
    if ((getCancelBookingMethod = BookingServiceGrpc.getCancelBookingMethod) == null) {
      synchronized (BookingServiceGrpc.class) {
        if ((getCancelBookingMethod = BookingServiceGrpc.getCancelBookingMethod) == null) {
          BookingServiceGrpc.getCancelBookingMethod = getCancelBookingMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.booking.GrpcCancelBookingRequest, com.example.grpc.booking.GrpcCancelBookingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelBooking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcCancelBookingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcCancelBookingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("CancelBooking"))
              .build();
        }
      }
    }
    return getCancelBookingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcOrderRequest,
      com.example.grpc.booking.GrpcOrderResponse> getOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Order",
      requestType = com.example.grpc.booking.GrpcOrderRequest.class,
      responseType = com.example.grpc.booking.GrpcOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcOrderRequest,
      com.example.grpc.booking.GrpcOrderResponse> getOrderMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcOrderRequest, com.example.grpc.booking.GrpcOrderResponse> getOrderMethod;
    if ((getOrderMethod = BookingServiceGrpc.getOrderMethod) == null) {
      synchronized (BookingServiceGrpc.class) {
        if ((getOrderMethod = BookingServiceGrpc.getOrderMethod) == null) {
          BookingServiceGrpc.getOrderMethod = getOrderMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.booking.GrpcOrderRequest, com.example.grpc.booking.GrpcOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Order"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("Order"))
              .build();
        }
      }
    }
    return getOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcPaymentRequest,
      com.example.grpc.booking.GrpcPaymentResponse> getPaymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Payment",
      requestType = com.example.grpc.booking.GrpcPaymentRequest.class,
      responseType = com.example.grpc.booking.GrpcPaymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcPaymentRequest,
      com.example.grpc.booking.GrpcPaymentResponse> getPaymentMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcPaymentRequest, com.example.grpc.booking.GrpcPaymentResponse> getPaymentMethod;
    if ((getPaymentMethod = BookingServiceGrpc.getPaymentMethod) == null) {
      synchronized (BookingServiceGrpc.class) {
        if ((getPaymentMethod = BookingServiceGrpc.getPaymentMethod) == null) {
          BookingServiceGrpc.getPaymentMethod = getPaymentMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.booking.GrpcPaymentRequest, com.example.grpc.booking.GrpcPaymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Payment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcPaymentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcPaymentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("Payment"))
              .build();
        }
      }
    }
    return getPaymentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.grpc.booking.GrpcEventsResponse> getGetAllEventsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllEvents",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.example.grpc.booking.GrpcEventsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.example.grpc.booking.GrpcEventsResponse> getGetAllEventsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.example.grpc.booking.GrpcEventsResponse> getGetAllEventsMethod;
    if ((getGetAllEventsMethod = BookingServiceGrpc.getGetAllEventsMethod) == null) {
      synchronized (BookingServiceGrpc.class) {
        if ((getGetAllEventsMethod = BookingServiceGrpc.getGetAllEventsMethod) == null) {
          BookingServiceGrpc.getGetAllEventsMethod = getGetAllEventsMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.example.grpc.booking.GrpcEventsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllEvents"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcEventsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("GetAllEvents"))
              .build();
        }
      }
    }
    return getGetAllEventsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcZonesRequest,
      com.example.grpc.booking.GrpcZonesResponse> getGetZonesByEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetZonesByEvent",
      requestType = com.example.grpc.booking.GrpcZonesRequest.class,
      responseType = com.example.grpc.booking.GrpcZonesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcZonesRequest,
      com.example.grpc.booking.GrpcZonesResponse> getGetZonesByEventMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcZonesRequest, com.example.grpc.booking.GrpcZonesResponse> getGetZonesByEventMethod;
    if ((getGetZonesByEventMethod = BookingServiceGrpc.getGetZonesByEventMethod) == null) {
      synchronized (BookingServiceGrpc.class) {
        if ((getGetZonesByEventMethod = BookingServiceGrpc.getGetZonesByEventMethod) == null) {
          BookingServiceGrpc.getGetZonesByEventMethod = getGetZonesByEventMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.booking.GrpcZonesRequest, com.example.grpc.booking.GrpcZonesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetZonesByEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcZonesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcZonesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("GetZonesByEvent"))
              .build();
        }
      }
    }
    return getGetZonesByEventMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcSeatsRequest,
      com.example.grpc.booking.GrpcSeatsResponse> getGetSeatsByZoneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSeatsByZone",
      requestType = com.example.grpc.booking.GrpcSeatsRequest.class,
      responseType = com.example.grpc.booking.GrpcSeatsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcSeatsRequest,
      com.example.grpc.booking.GrpcSeatsResponse> getGetSeatsByZoneMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.booking.GrpcSeatsRequest, com.example.grpc.booking.GrpcSeatsResponse> getGetSeatsByZoneMethod;
    if ((getGetSeatsByZoneMethod = BookingServiceGrpc.getGetSeatsByZoneMethod) == null) {
      synchronized (BookingServiceGrpc.class) {
        if ((getGetSeatsByZoneMethod = BookingServiceGrpc.getGetSeatsByZoneMethod) == null) {
          BookingServiceGrpc.getGetSeatsByZoneMethod = getGetSeatsByZoneMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.booking.GrpcSeatsRequest, com.example.grpc.booking.GrpcSeatsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSeatsByZone"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcSeatsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.booking.GrpcSeatsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("GetSeatsByZone"))
              .build();
        }
      }
    }
    return getGetSeatsByZoneMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BookingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookingServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookingServiceStub>() {
        @java.lang.Override
        public BookingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookingServiceStub(channel, callOptions);
        }
      };
    return BookingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BookingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookingServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookingServiceBlockingStub>() {
        @java.lang.Override
        public BookingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookingServiceBlockingStub(channel, callOptions);
        }
      };
    return BookingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BookingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookingServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookingServiceFutureStub>() {
        @java.lang.Override
        public BookingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookingServiceFutureStub(channel, callOptions);
        }
      };
    return BookingServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createBooking(com.example.grpc.booking.GrpcBookingRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcBookingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateBookingMethod(), responseObserver);
    }

    /**
     */
    default void cancelBooking(com.example.grpc.booking.GrpcCancelBookingRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcCancelBookingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelBookingMethod(), responseObserver);
    }

    /**
     */
    default void order(com.example.grpc.booking.GrpcOrderRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOrderMethod(), responseObserver);
    }

    /**
     */
    default void payment(com.example.grpc.booking.GrpcPaymentRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcPaymentResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPaymentMethod(), responseObserver);
    }

    /**
     */
    default void getAllEvents(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcEventsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllEventsMethod(), responseObserver);
    }

    /**
     */
    default void getZonesByEvent(com.example.grpc.booking.GrpcZonesRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcZonesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetZonesByEventMethod(), responseObserver);
    }

    /**
     */
    default void getSeatsByZone(com.example.grpc.booking.GrpcSeatsRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcSeatsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSeatsByZoneMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BookingService.
   */
  public static abstract class BookingServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BookingServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BookingService.
   */
  public static final class BookingServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BookingServiceStub> {
    private BookingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookingServiceStub(channel, callOptions);
    }

    /**
     */
    public void createBooking(com.example.grpc.booking.GrpcBookingRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcBookingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateBookingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelBooking(com.example.grpc.booking.GrpcCancelBookingRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcCancelBookingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelBookingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void order(com.example.grpc.booking.GrpcOrderRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void payment(com.example.grpc.booking.GrpcPaymentRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcPaymentResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPaymentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllEvents(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcEventsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllEventsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getZonesByEvent(com.example.grpc.booking.GrpcZonesRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcZonesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetZonesByEventMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSeatsByZone(com.example.grpc.booking.GrpcSeatsRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcSeatsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSeatsByZoneMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BookingService.
   */
  public static final class BookingServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BookingServiceBlockingStub> {
    private BookingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.booking.GrpcBookingResponse createBooking(com.example.grpc.booking.GrpcBookingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateBookingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.booking.GrpcCancelBookingResponse cancelBooking(com.example.grpc.booking.GrpcCancelBookingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelBookingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.booking.GrpcOrderResponse order(com.example.grpc.booking.GrpcOrderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.booking.GrpcPaymentResponse payment(com.example.grpc.booking.GrpcPaymentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPaymentMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.booking.GrpcEventsResponse getAllEvents(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllEventsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.booking.GrpcZonesResponse getZonesByEvent(com.example.grpc.booking.GrpcZonesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetZonesByEventMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.booking.GrpcSeatsResponse getSeatsByZone(com.example.grpc.booking.GrpcSeatsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSeatsByZoneMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BookingService.
   */
  public static final class BookingServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BookingServiceFutureStub> {
    private BookingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.booking.GrpcBookingResponse> createBooking(
        com.example.grpc.booking.GrpcBookingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateBookingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.booking.GrpcCancelBookingResponse> cancelBooking(
        com.example.grpc.booking.GrpcCancelBookingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelBookingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.booking.GrpcOrderResponse> order(
        com.example.grpc.booking.GrpcOrderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.booking.GrpcPaymentResponse> payment(
        com.example.grpc.booking.GrpcPaymentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPaymentMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.booking.GrpcEventsResponse> getAllEvents(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllEventsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.booking.GrpcZonesResponse> getZonesByEvent(
        com.example.grpc.booking.GrpcZonesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetZonesByEventMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.booking.GrpcSeatsResponse> getSeatsByZone(
        com.example.grpc.booking.GrpcSeatsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSeatsByZoneMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_BOOKING = 0;
  private static final int METHODID_CANCEL_BOOKING = 1;
  private static final int METHODID_ORDER = 2;
  private static final int METHODID_PAYMENT = 3;
  private static final int METHODID_GET_ALL_EVENTS = 4;
  private static final int METHODID_GET_ZONES_BY_EVENT = 5;
  private static final int METHODID_GET_SEATS_BY_ZONE = 6;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_BOOKING:
          serviceImpl.createBooking((com.example.grpc.booking.GrpcBookingRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcBookingResponse>) responseObserver);
          break;
        case METHODID_CANCEL_BOOKING:
          serviceImpl.cancelBooking((com.example.grpc.booking.GrpcCancelBookingRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcCancelBookingResponse>) responseObserver);
          break;
        case METHODID_ORDER:
          serviceImpl.order((com.example.grpc.booking.GrpcOrderRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcOrderResponse>) responseObserver);
          break;
        case METHODID_PAYMENT:
          serviceImpl.payment((com.example.grpc.booking.GrpcPaymentRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcPaymentResponse>) responseObserver);
          break;
        case METHODID_GET_ALL_EVENTS:
          serviceImpl.getAllEvents((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcEventsResponse>) responseObserver);
          break;
        case METHODID_GET_ZONES_BY_EVENT:
          serviceImpl.getZonesByEvent((com.example.grpc.booking.GrpcZonesRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcZonesResponse>) responseObserver);
          break;
        case METHODID_GET_SEATS_BY_ZONE:
          serviceImpl.getSeatsByZone((com.example.grpc.booking.GrpcSeatsRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.booking.GrpcSeatsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateBookingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpc.booking.GrpcBookingRequest,
              com.example.grpc.booking.GrpcBookingResponse>(
                service, METHODID_CREATE_BOOKING)))
        .addMethod(
          getCancelBookingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpc.booking.GrpcCancelBookingRequest,
              com.example.grpc.booking.GrpcCancelBookingResponse>(
                service, METHODID_CANCEL_BOOKING)))
        .addMethod(
          getOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpc.booking.GrpcOrderRequest,
              com.example.grpc.booking.GrpcOrderResponse>(
                service, METHODID_ORDER)))
        .addMethod(
          getPaymentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpc.booking.GrpcPaymentRequest,
              com.example.grpc.booking.GrpcPaymentResponse>(
                service, METHODID_PAYMENT)))
        .addMethod(
          getGetAllEventsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.example.grpc.booking.GrpcEventsResponse>(
                service, METHODID_GET_ALL_EVENTS)))
        .addMethod(
          getGetZonesByEventMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpc.booking.GrpcZonesRequest,
              com.example.grpc.booking.GrpcZonesResponse>(
                service, METHODID_GET_ZONES_BY_EVENT)))
        .addMethod(
          getGetSeatsByZoneMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpc.booking.GrpcSeatsRequest,
              com.example.grpc.booking.GrpcSeatsResponse>(
                service, METHODID_GET_SEATS_BY_ZONE)))
        .build();
  }

  private static abstract class BookingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BookingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.booking.BookingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BookingService");
    }
  }

  private static final class BookingServiceFileDescriptorSupplier
      extends BookingServiceBaseDescriptorSupplier {
    BookingServiceFileDescriptorSupplier() {}
  }

  private static final class BookingServiceMethodDescriptorSupplier
      extends BookingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BookingServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BookingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BookingServiceFileDescriptorSupplier())
              .addMethod(getCreateBookingMethod())
              .addMethod(getCancelBookingMethod())
              .addMethod(getOrderMethod())
              .addMethod(getPaymentMethod())
              .addMethod(getGetAllEventsMethod())
              .addMethod(getGetZonesByEventMethod())
              .addMethod(getGetSeatsByZoneMethod())
              .build();
        }
      }
    }
    return result;
  }
}
