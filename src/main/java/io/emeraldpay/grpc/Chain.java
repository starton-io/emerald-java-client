/*
 * Copyright (c) 2016-2019 ETCDEV GmbH, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.emeraldpay.grpc;

public enum Chain {

    UNSPECIFIED(0, "UNSPECIFIED", "Unknown"),

    BITCOIN(1, "BTC", "Bitcoin"),
    // GRIN(2, "GRIN", "Grin"),

    // Networks with tokens
    ETHEREUM(100, "ETH", "Ethereum"),
    ETHEREUM_CLASSIC(101, "ETC", "Ethereum Classic"),
    FANTOM(102, "FTM", "Fantom Opera"),
    BSC(103, "BSC", "Binance Smart Chain"),
    AVAX(104, "AVAX", "Avalanche C-Chain"),
    MOONBEAM(105, "GLMR", "Moonbeam Mainnet"),
    CELO(106, "CELO", "Celo Mainnet"),
    EVMOS(107, "EVMOS", "Evmos Mainnet"),
    CRONOS(108, "AVAX", "Cronos Mainnet Beta"),
    NEON(109, "NEON", "Neon EVM Mainnet"),


    //LIGHTNING(1001, "BTC_LN", "Bitcoin Lightning"),
    MATIC(1002, "MATIC", "Polygon Matic"),
    RSK(1003, "RSK", "Bitcoin RSK"),
    OPTIMISM(1004, "OPTIMISM", "Optimism Mainnet"),
    EOS(1005, "EOS", "EOS Mainnet"),
    MILKOMEDA(1006, "MADA", "Milkomeda C1 Mainnet"),
    ARBITRUM(1007, "ARBITRUM", "Arbitrum Mainnet"),
    NOVA(1008, "NOVA", "Arbitrum Nova"),

    // Testnets
    TESTNET_MORDEN(10001, "MORDEN", "Morden Testnet"),
    TESTNET_KOVAN(10002, "KOVAN", "Kovan Testnet"),
    TESTNET_BITCOIN(10003, "TESTNET_BITCOIN", "Bitcoin Testnet"),
    // TESTNET_FLOONET(10004, "FLOONET", "Floonet Testnet"),
    TESTNET_GOERLI(10005, "GOERLI", "Goerli Testnet"),
    TESTNET_ROPSTEN(10006, "ROPSTEN", "Ropsten Testnet"),
    TESTNET_RINKEBY(10007, "RINKEBY", "Rinkeby Testnet"),
    TESTNET_SEPOLIA(10008, "SEPOLIA", "Sepolia Testnet"),
    TESTNET_BSC(10009, "TESTNET_BSC", "Binance Smart Chain Testnet"),
    TESTNET_MUMBAI(10010, "TESTNET_MUMBAI", "Polygon Mumbai Testnet"),
    TESTNET_FUJI(10011, "TESTNET_FUJI", "Avalanche Fuji Testnet"),
    TESTNET_OPTIMISM(10012, "TESTNET_OPTIMISM", "Optimism Testnet"),
    TESTNET_ARBITRUM(10013, "TESTNET_ARBITRUM", "Arbitrum Testnet"),
    TESTNET_FANTOM(10014, "TESTNET_FANTOM", "Fantom Testnet"),
    TESTNET_EVMOS(10015, "TESTNET_EVMOS", "Evmos Testnet"),
    TESTNET_CRONOS(10016, "TESTNET_CRONOS", "Cronos Testnet"),
    TESTNET_NEON(10017, "TESTNET_NEON", "Neon EVM Testnet"),
    TESTNET_MILKOMEDA(10018, "TESTNET_MILKOMEDA", "Milkomeda C1 Testnet"),
    TESTNET_RSK(10019, "TESTNET_RSK", "Bitcoin RSK Testnet");

    private final int id;
    private final String code;
    private final String fullname;

    Chain(int id, String code, String fullname) {
        this.id = id;
        this.code = code;
        this.fullname = fullname;
    }

    public static Chain byId(int id) {
        for (Chain chain: Chain.values()) {
            if (chain.id == id) {
                return chain;
            }
        }
        return UNSPECIFIED;
    }

    public int getId() {
        return id;
    }

    public String getChainCode() {
        return code;
    }

    public String getChainName() {
        return fullname;
    }
}