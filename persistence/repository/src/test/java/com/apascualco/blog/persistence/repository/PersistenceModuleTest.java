package com.apascualco.blog.persistence.repository;

import com.apascualco.blog.persistence.model.entities.Role;
import com.apascualco.blog.persistence.model.entities.User;
import com.apascualco.blog.persistence.repository.JPATestConfig;
import com.apascualco.blog.persistence.repository.RoleRepository;
import com.apascualco.blog.persistence.repository.UserRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPATestConfig.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
public class PersistenceModuleTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    @Transactional
    public void should_insert_user_test() {
        assertTrue(userRepository.findAll().size() == 3);

        Set<Role> roleEnts = new HashSet<>();
        roleRepository.findById(1L).ifPresent(roleEnts::add);
        assertTrue(roleEnts.size() > 0);

        User user = new User();
        user.setEmail("ejemplo@mail.com");
        user.setPassword("HexadecimalMD5");
        user.setRoles(roleEnts);
        user.setAuditLastUpdate(Calendar.getInstance());
        user.setAuditUser("TESTING");
        userRepository.save(user);

        assertTrue(userRepository.findAll().size() == 4);
    }

    @Test
    @Transactional
    public void should_delete_user_test() {
        List<User> users = userRepository.findAll();
        assertTrue(users.size() == 3);
        assertTrue(users.get(0).getRoles().size() == 1);
        userRepository.delete(1L);
        assertTrue(userRepository.findAll().size() == 2);
    }

}
