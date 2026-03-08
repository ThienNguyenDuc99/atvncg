package com.example.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.56.0)",
    comments = "Source: sendQueue.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RequestTakerServiceGrpc {

  private RequestTakerServiceGrpc() {}

  public static final String SERVICE_NAME = "RequestTakerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.GrpcSendQueueRequest,
      com.example.grpc.GrpcSendQueueResponse> getSendQueueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendQueue",
      requestType = com.example.grpc.GrpcSendQueueRequest.class,
      responseType = com.example.grpc.GrpcSendQueueResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.GrpcSendQueueRequest,
      com.example.grpc.GrpcSendQueueResponse> getSendQueueMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.GrpcSendQueueRequest, com.example.grpc.GrpcSendQueueResponse> getSendQueueMethod;
    if ((getSendQueueMethod = RequestTakerServiceGrpc.getSendQueueMethod) == null) {
      synchronized (RequestTakerServiceGrpc.class) {
        if ((getSendQueueMethod = RequestTakerServiceGrpc.getSendQueueMethod) == null) {
          RequestTakerServiceGrpc.getSendQueueMethod = getSendQueueMethod =
              io.grpc.MethodDescriptor.<com.example.grpc.GrpcSendQueueRequest, com.example.grpc.GrpcSendQueueResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendQueue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.GrpcSendQueueRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.GrpcSendQueueResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RequestTakerServiceMethodDescriptorSupplier("SendQueue"))
              .build();
        }
      }
    }
    return getSendQueueMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RequestTakerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RequestTakerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RequestTakerServiceStub>() {
        @java.lang.Override
        public RequestTakerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RequestTakerServiceStub(channel, callOptions);
        }
      };
    return RequestTakerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RequestTakerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RequestTakerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RequestTakerServiceBlockingStub>() {
        @java.lang.Override
        public RequestTakerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RequestTakerServiceBlockingStub(channel, callOptions);
        }
      };
    return RequestTakerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RequestTakerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RequestTakerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RequestTakerServiceFutureStub>() {
        @java.lang.Override
        public RequestTakerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RequestTakerServiceFutureStub(channel, callOptions);
        }
      };
    return RequestTakerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void sendQueue(com.example.grpc.GrpcSendQueueRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.GrpcSendQueueResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendQueueMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service RequestTakerService.
   */
  public static abstract class RequestTakerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return RequestTakerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service RequestTakerService.
   */
  public static final class RequestTakerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<RequestTakerServiceStub> {
    private RequestTakerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RequestTakerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RequestTakerServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendQueue(com.example.grpc.GrpcSendQueueRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.GrpcSendQueueResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendQueueMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service RequestTakerService.
   */
  public static final class RequestTakerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<RequestTakerServiceBlockingStub> {
    private RequestTakerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RequestTakerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RequestTakerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.GrpcSendQueueResponse sendQueue(com.example.grpc.GrpcSendQueueRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendQueueMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service RequestTakerService.
   */
  public static final class RequestTakerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<RequestTakerServiceFutureStub> {
    private RequestTakerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RequestTakerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RequestTakerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.GrpcSendQueueResponse> sendQueue(
        com.example.grpc.GrpcSendQueueRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendQueueMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_QUEUE = 0;

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
        case METHODID_SEND_QUEUE:
          serviceImpl.sendQueue((com.example.grpc.GrpcSendQueueRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.GrpcSendQueueResponse>) responseObserver);
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
          getSendQueueMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpc.GrpcSendQueueRequest,
              com.example.grpc.GrpcSendQueueResponse>(
                service, METHODID_SEND_QUEUE)))
        .build();
  }

  private static abstract class RequestTakerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RequestTakerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.SendQueueProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RequestTakerService");
    }
  }

  private static final class RequestTakerServiceFileDescriptorSupplier
      extends RequestTakerServiceBaseDescriptorSupplier {
    RequestTakerServiceFileDescriptorSupplier() {}
  }

  private static final class RequestTakerServiceMethodDescriptorSupplier
      extends RequestTakerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RequestTakerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (RequestTakerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RequestTakerServiceFileDescriptorSupplier())
              .addMethod(getSendQueueMethod())
              .build();
        }
      }
    }
    return result;
  }
}
