package com.misha.dao;

import com.misha.model.Employee;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("classpath*:resources/test-context.xml")
@TransactionConfiguration(defaultRollback=true)
public class AppDaoImplTest {

    @Autowired
    private AppDao appDao;

    @Test
    public void testFindById()
    {
        Employee employee = appDao.getEmployeeById(1);

        Assert.assertEquals(1, employee.getId());
        Assert.assertEquals(1, employee.getDepartmentId());
        Assert.assertEquals("Mihail", employee.getFirstName());
        Assert.assertEquals("Spiridonov", employee.getLastName());
        Assert.assertEquals(900, employee.getSalary());
    }

}
