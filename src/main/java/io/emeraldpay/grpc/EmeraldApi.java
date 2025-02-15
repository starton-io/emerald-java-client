package io.emeraldpay.grpc;

import io.emeraldpay.api.proto.ReactorBlockchainGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.NettyChannelBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Function;

/**
 * Root of Emerald API service
 */
public class EmeraldApi {

    private final ReactorBlockchainGrpc.ReactorBlockchainStub blockchainStub;

    private Channel channel;

    private EmeraldApi(Channel channel) {
        blockchainStub = ReactorBlockchainGrpc.newReactorStub(channel);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newDevelopmentBuilder() {
        return new Builder().connectTo("api.emeraldpay.dev", 443);
    }

    public ReactorBlockchainGrpc.ReactorBlockchainStub getBlockchainApi() {
        return blockchainStub;
    }

    public static class Builder {
        private String host;
        private Integer port;
        private boolean usePlaintext = false;

        /**
         * Default Netty config allows messages up to 4Mb, but in practice Ethereum RPC responses may be larger. Here it allows up to 32Mb by default.
         */
        private Integer maxMessageSize = 32 * 1024 * 1024;

        private Function<NettyChannelBuilder, ManagedChannelBuilder<?>> customChannel = null;

        /**
         * Set target address as a host and port pair
         *
         * @param host remote host
         * @param port remote port
         * @return builder
         */
        public Builder connectTo(String host, int port) {
            this.host = host;
            this.port = port;
            return this;
        }

        /**
         * Set target address, as a host only or as a host:port pair
         *
         * @param host remote address
         * @return builder
         */
        public Builder connectTo(String host) {
            if (host.indexOf(":") > 0) {
                String[] split = host.split(":");
                this.connectTo(split[0], Integer.parseInt(split[1]));
            } else {
                this.host = host;
            }
            return this;
        }

        public Builder connectTo(InetAddress host, int port) {
            this.port = port;
            return this.connectTo(host);
        }

        public Builder connectTo(InetAddress host) {
            this.host = host.getHostAddress();
            return this;
        }

        public Builder usePlaintext() {
            this.usePlaintext = true;
            return this;
        }

        /**
         * St max inbound message size. Default is 32mb.
         *
         * @param value max message size. Set as null to use Netty default config
         * @return builder
         */
        public Builder maxMessageSize(Integer value) {
            this.maxMessageSize = value;
            return this;
        }

        /**
         * Customize Channel Builder by applying any custom options not covered by this Builder
         *
         * @param customChannel function that transforms NettyChannelBuilder prepared by builder before creating api from it
         * @return builder
         */
        public Builder withChannelBuilder(Function<NettyChannelBuilder, ManagedChannelBuilder<?>> customChannel) {
            this.customChannel = customChannel;
            return this;
        }

        protected void initDefaults() throws IOException {
            if (host == null) {
                host = "api.emrld.io";
            }
            if (port == null) {
                port = 443;
            }
        }

        /**
         * Build the API instance
         *
         * @return Emerald API instance
         * @throws Exception if some config params are invalid
         */
        public EmeraldApi build() throws Exception {
            initDefaults();

            NettyChannelBuilder nettyChannelBuilder = NettyChannelBuilder
                    .forAddress(host, port);

            if (port == 80 || usePlaintext) {
                nettyChannelBuilder.usePlaintext();
            }

            if (maxMessageSize != null && maxMessageSize > 0) {
                nettyChannelBuilder.maxInboundMessageSize(maxMessageSize);
            }

            ManagedChannelBuilder<?> channelBuilder;

            if (customChannel != null) {
                channelBuilder = customChannel.apply(nettyChannelBuilder);
            } else {
                channelBuilder = nettyChannelBuilder;
            }

            return new EmeraldApi(channelBuilder.build());
        }


    }
}
