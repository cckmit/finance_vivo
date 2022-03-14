package mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.vivo.finance.FinanceSystemApplication;
import com.vivo.finance.entity.dict.SysDict;
import com.vivo.finance.service.dict.SysDictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zyk
 * @Description
 * @date 2022年02月11日
 */
@SpringBootTest(classes = FinanceSystemApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestQuery {

    @Autowired
    private SysDictService service;

    @Test
    public void testGetID(){
        System.out.println(IdWorker.getId());
    }



}
