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
import nxt.VoteWeighting;
import nxt.addons.JO;
import nxt.http.callers.GetBlockCall;
import nxt.http.callers.SendMoneyCall;
import nxt.http.responses.BlockResponse;
import nxt.http.responses.TransactionResponse;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Sample Java program which demonstrates how to submit a phased transaction
 */
public class PhasedTransaction {

    private static final String SECRET_PHRASE = "dove bar guy valley due pencil self sweat crumble husband loser cost";

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://tndx.npay.life/nxt");

        PhasedTransaction phasedTransaction = new PhasedTransaction();
        phasedTransaction.submitPhasedTransaction(url);
    }

    private void submitPhasedTransaction(URL url) {
        JO block = GetBlockCall.create().remote(url).call();
        BlockResponse blockResponse = BlockResponse.create(block);
        int height = blockResponse.getHeight();

        JO signedTransactionResponse = SendMoneyCall.create().
                recipient("NDX-2C9N-RJ83-2826-2VSEV").
                amountNQT(12345678).
                secretPhrase(SECRET_PHRASE).
                deadline(15).
                feeNQT(200000000).
                phased(true).
                phasingVotingModel(VoteWeighting.VotingModel.ACCOUNT.getCode()). // Another account will need to approve this
                phasingQuorum(1). // One approver account is enough
                phasingWhitelisted("NDX-BTUQ-FS4F-F3ZX-FATZS"). // This is the account that needs to approve
                phasingFinishHeight(height + 100). // It has 100 blocks to submit the approval
                phasingMinBalanceModel(VoteWeighting.MinBalanceModel.NONE.getCode()). // There is no minimum balance requirement
                remote(url).
                call();

        System.out.printf("SendMoney response: %s\n", signedTransactionResponse.toJSONString());
        TransactionResponse transactionResponse = TransactionResponse.create(signedTransactionResponse.getJo("transactionJSON"));
        System.out.printf("Phased: %s\n", transactionResponse.isPhased());
    }

}
