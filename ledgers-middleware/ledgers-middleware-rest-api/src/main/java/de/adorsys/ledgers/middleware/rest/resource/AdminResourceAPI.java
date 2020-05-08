package de.adorsys.ledgers.middleware.rest.resource;

import de.adorsys.ledgers.middleware.api.domain.account.AccountDetailsTO;
import de.adorsys.ledgers.middleware.api.domain.um.UserRoleTO;
import de.adorsys.ledgers.middleware.api.domain.um.UserTO;
import de.adorsys.ledgers.util.domain.CustomPageImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "LDG999 - Admin Management (SYSTEM access)")
public interface AdminResourceAPI {
    String BASE_PATH = "/admin";
    String BRANCH_ID = "branchId";
    String BRANCH_LOGIN = "branchLogin";
    String COUNTRY_CODE = "country";
    String USER_LOGIN = "userLogin";
    String ROLE = "role";
    String BLOCKED = "blocked";
    String IBAN = "ibanParam";
    String PAGE = "page";
    String SIZE = "size";
    String PASSWORD = "password";

    @ApiOperation(value = "Get users",
            notes = "Retrieves Page of Users with required role and Branch id",
            authorizations = @Authorization(value = "apiKey"))
    @GetMapping("/users")
    ResponseEntity<CustomPageImpl<UserTO>> users(@RequestParam(value = COUNTRY_CODE, defaultValue = "", required = false) String countryCode,
                                                 @RequestParam(value = BRANCH_ID, defaultValue = "", required = false) String branchId,
                                                 @RequestParam(value = BRANCH_LOGIN, defaultValue = "", required = false) String branchLogin,
                                                 @RequestParam(value = USER_LOGIN, defaultValue = "", required = false) String userLogin,
                                                 @RequestParam(value = ROLE, required = false) UserRoleTO role,
                                                 @RequestParam(value = BLOCKED, required = false) Boolean blocked,
                                                 @RequestParam(PAGE) int page,
                                                 @RequestParam(SIZE) int size);

    @ApiOperation(value = "Get accounts",
            notes = "Retrieves Page of Accounts with required role and Branch id",
            authorizations = @Authorization(value = "apiKey"))
    @GetMapping("/accounts")
    ResponseEntity<CustomPageImpl<AccountDetailsTO>> accounts(@RequestParam(value = COUNTRY_CODE, defaultValue = "", required = false) String countryCode,
                                                              @RequestParam(value = BRANCH_ID, defaultValue = "", required = false) String branchId,
                                                              @RequestParam(value = BRANCH_LOGIN, defaultValue = "", required = false) String branchLogin,
                                                              @RequestParam(value = IBAN, required = false, defaultValue = "") String iban,
                                                              @RequestParam(value = BLOCKED, required = false) Boolean blocked,
                                                              @RequestParam(PAGE) int page,
                                                              @RequestParam(SIZE) int size);

    @ApiOperation(value = "Set password for Branch",
            notes = "Changes password for given Branch",
            authorizations = @Authorization(value = "apiKey"))
    @PutMapping("/password")
    ResponseEntity<Void> updatePassword(@RequestParam(value = BRANCH_ID) String branchId, @RequestParam(PASSWORD) String password);

    @ApiOperation(value = "Block/Unblock Branch",
            notes = "Changes system block or regular block state for given Branch, returns status being set to the block",
            authorizations = @Authorization(value = "apiKey"))
    @PostMapping("/status")
    ResponseEntity<Boolean> changeStatus(@RequestParam(value = BRANCH_ID) String branchId);
}