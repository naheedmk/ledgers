package de.adorsys.ledgers.postings.db.repository;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import de.adorsys.ledgers.postings.db.domain.Ledger;
import de.adorsys.ledgers.postings.db.domain.LedgerStmt;
import de.adorsys.ledgers.postings.db.domain.StmtStatus;
import de.adorsys.ledgers.postings.db.tests.PostingRepositoryApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PostingRepositoryApplication.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
@DatabaseSetup("LedgerStmtRepositoryIT-db-entries.xml")
@DatabaseTearDown(value={"LedgerStmtRepositoryIT-db-entries.xml"}, type=DatabaseOperation.DELETE_ALL)
public class LedgerStmtRepositoryIT {

	@Autowired
	private LedgerRepository ledgerRepository;
	@Autowired
	private LedgerStmtRepository financialStmtRepository;
	
	@Test
	public void test_create_financial_statement_ok() {
		Ledger ledger = ledgerRepository.findById("Zd0ND5YwSzGwIfZilhumPg").orElseThrow(()-> new IllegalStateException("Missing Ledger with id Zd0ND5YwSzGwIfZilhumPg"));
		LedgerStmt f = new LedgerStmt();
		f.setStmtStatus(StmtStatus.SIMULATED);
		f.setLedger(ledger);
		f.setStmtSeqNbr(0);
		LocalDateTime pstTime = LocalDateTime.of(2017, Month.DECEMBER, 31, 23, 59);
		f.setPstTime(pstTime);
		financialStmtRepository.save(f);
	}

}