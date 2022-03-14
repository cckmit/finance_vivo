package sql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.vivo.finance.FinanceSystemApplication;
import com.vivo.finance.mapper.erp.BankintgStatementMapper;
import com.vivo.finance.service.erp.FmAcctFlowDayService;
import com.vivo.finance.service.erp.FmStatementService;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author zyk
 * @Description
 * @date 2022年03月02日
 */
@SpringBootTest(classes = FinanceSystemApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class StatementTest {

    @Autowired
    private BankintgStatementMapper statementMapper;

    @Autowired
    private FmStatementService statementService;
    @Autowired
    private FmAcctFlowDayService acctFlowDayService;

    //@Test
    public void  test(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("BANK_ACCOUNT_ID as bankAccountId ","attribute1","count(AMOUNT_EXPENSE)");
        wrapper.ge("trx_date",new Date());
        wrapper.apply("attribute1 = 'S01'",null);
        wrapper.groupBy(Arrays.asList("BANK_ACCOUNT_ID","attribute1"));
        List list = statementMapper.selectList(wrapper);
        System.out.println(list);
    }

    @Test
    public void testGetID(){
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());


    }

    @Test
    public void testRule() throws NoSuchAlgorithmException {
        statementService.saveByRule();
    }

    /**
     * @Description: 资金流动余额表生成
     * @author zyk
     * @date 2022/3/8 10:49
     */
    @Test
    public void testAcctFlowDay() throws NoSuchAlgorithmException {
        acctFlowDayService.saveBalance();

    }

}
