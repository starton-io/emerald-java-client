package io.emeraldpay.grpc;

public enum BlockchainType {
    BITCOIN,
    ETHEREUM;

    public static BlockchainType from(Chain chain) {
        if (chain == Chain.ETHEREUM
                || chain == Chain.ETHEREUM_CLASSIC
                || chain == Chain.MATIC
                || chain == Chain.BSC
                || chain == Chain.AVAX
                || chain == Chain.FANTOM
                || chain == Chain.RSK
                || chain == Chain.ARBITRUM
                || chain == Chain.MOONBEAM
                || chain == Chain.CELO
                || chain == Chain.EVMOS
                || chain == Chain.CRONOS
                || chain == Chain.NEON
                || chain == Chain.OPTIMISM
                || chain == Chain.EOS
                || chain == Chain.MILKOMEDA
                || chain == Chain.TESTNET_KOVAN
                || chain == Chain.TESTNET_MORDEN
                || chain == Chain.TESTNET_GOERLI
                || chain == Chain.TESTNET_RINKEBY
                || chain == Chain.TESTNET_ROPSTEN
                || chain == Chain.TESTNET_SEPOLIA
                || chain == Chain.TESTNET_BSC
                || chain == Chain.TESTNET_MUMBAI
                || chain == Chain.TESTNET_FUJI
                || chain == Chain.TESTNET_OPTIMISM
                || chain == Chain.TESTNET_ARBITRUM
                || chain == Chain.TESTNET_FANTOM
                || chain == Chain.TESTNET_EVMOS
                || chain == Chain.TESTNET_CRONOS
                || chain == Chain.TESTNET_NEON
                || chain == Chain.TESTNET_MILKOMEDA
                || chain == Chain.TESTNET_RSK
        ) {
            return BlockchainType.ETHEREUM;
        }

        if (chain == Chain.BITCOIN
                || chain == Chain.TESTNET_BITCOIN
        ) {
            return BlockchainType.BITCOIN;
        }

        throw new IllegalArgumentException("Unknown type of blockchain: " + chain);
    }
}
