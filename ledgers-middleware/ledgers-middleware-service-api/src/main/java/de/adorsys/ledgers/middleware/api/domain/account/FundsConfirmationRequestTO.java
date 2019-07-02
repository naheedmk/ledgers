package de.adorsys.ledgers.middleware.api.domain.account;

import de.adorsys.ledgers.middleware.api.domain.payment.AmountTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FundsConfirmationRequestTO {
    private String psuId;
    private AccountReferenceTO psuAccount;
    private AmountTO instructedAmount;
    private String cardNumber;
    private String payee;
}
