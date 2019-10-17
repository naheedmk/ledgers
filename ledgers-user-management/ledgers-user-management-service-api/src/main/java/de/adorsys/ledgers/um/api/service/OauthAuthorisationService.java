package de.adorsys.ledgers.um.api.service;

import de.adorsys.ledgers.um.api.domain.oauth.OauthCodeResponseBO;
import de.adorsys.ledgers.um.api.domain.oauth.OauthTokenResponseBO;

public interface OauthAuthorisationService {

    /**
     * @param login User login
     * @param pin   User pin
     * @return OauthCodeResponseBO representation of oauth code
     */
    OauthCodeResponseBO oauthCode(String login, String pin);

    /**
     * @param code Oauth code
     * @return OauthTokenResponseBO representation of oauth token
     */
    OauthTokenResponseBO oauthToken(String code);

    OauthCodeResponseBO oauthCode(String userId);
}
