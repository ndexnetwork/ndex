/*
 * Copyright © 2016-2020 Jelurida IP B.V.
 *
 * See the LICENSE.txt file at the top-level directory of this distribution
 * for licensing information.
 *
 * Unless otherwise agreed in a custom licensing agreement with Jelurida B.V.,
 * no part of this software, including this file, may be copied, modified,
 * propagated, or distributed except according to the terms contained in the
 * LICENSE.txt file.
 *
 * Removal or modification of this copyright notice is prohibited.
 *
 */

package com.ndexnetwork.ndx.client.api;

import nxt.Nxt;
import nxt.addons.JO;
import nxt.http.callers.EncryptToCall;
import nxt.http.callers.SendMoneyCall;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Sample Java program to encrypt a message locally, then send the encrypted data to a remote node without exposing the passphrase
 */
public class MessageEncryption {

    private static final String SECRET_PHRASE = "hope peace happen touch easy pretend worthless talk them indeed wheel state";

    public static void main(String[] args) throws MalformedURLException {
        URL localUrl = new URL("http://localhost:6868/nxt");
        URL remoteUrl = new URL("https://ndxseed.npay.life/nxt");

        // starts the local node, so make sure it is not already running or you'll receive a BindException
        MessageEncryption messageEncryption = new MessageEncryption();
        JO encryptedData = messageEncryption.encrypt(localUrl);
        messageEncryption.submit(encryptedData, remoteUrl);
    }

    private JO encrypt(URL url) {
        return EncryptToCall.create().recipient("NDX-KX2S-UULA-7YZ7-F3R8L").messageToEncrypt("Hello World").messageToEncryptIsText(true).secretPhrase(SECRET_PHRASE).remote(url).call();
    }

    private void submit(JO encrytpedData, URL url) {
        JO signedTransactionResponse = SendMoneyCall.create().
                recipient("NDX-KX2S-UULA-7YZ7-F3R8L").
                amountNQT(12345678).
                secretPhrase(SECRET_PHRASE).
                deadline(15).
                feeNQT(100000000). // See other examples for fee calculation
                encryptedMessageData(encrytpedData.getString("data")).
                encryptedMessageNonce(encrytpedData.getString("nonce")).
                encryptedMessageIsPrunable(true).
                remote(url).
                call();
        System.out.printf("SendMoney response: %s\n", signedTransactionResponse.toJSONString());
    }
}