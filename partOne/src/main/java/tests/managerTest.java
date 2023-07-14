package tests;


import Data.AccountManager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class managerTest {
    AccountManager accountManager;

    @Before
    public void setup() {
        accountManager = new AccountManager();
    }

    @Test
    public void testAdd_Success() {
        int result = accountManager.add("username", "password");
        assertEquals(AccountManager.CODE_SUCCESS, result);
        assertTrue(accountManager.contains("username", "password"));
    }

    @Test
    public void testAdd_Error() {
        int result = accountManager.add("", "password");
        assertEquals(AccountManager.CODE_ERROR, result);
        assertFalse(accountManager.contains("", "password"));

        result = accountManager.add("username", "");
        assertEquals(AccountManager.CODE_ERROR, result);
        assertFalse(accountManager.contains("username", ""));
    }

    @Test
    public void testAdd_InUse() {
        accountManager.add("username", "password");
        int result = accountManager.add("username", "newpassword");
        assertEquals(AccountManager.CODE_IN_USE, result);
        assertFalse(accountManager.contains("username", "newpassword"));
    }

    @Test
    public void testContains() {
        accountManager.add("username", "password");
        assertTrue(accountManager.contains("username", "password"));
        assertFalse(accountManager.contains("username", "wrongpassword"));
        assertFalse(accountManager.contains("wrongusername", "password"));
        assertFalse(accountManager.contains("wrongusername", "wrongpassword"));
    }

    @Test
    public void testAdd_MultipleAccounts() {
        int result1 = accountManager.add("user1", "pass1");
        int result2 = accountManager.add("user2", "pass2");
        int result3 = accountManager.add("user3", "pass3");

        assertEquals(AccountManager.CODE_SUCCESS, result1);
        assertEquals(AccountManager.CODE_SUCCESS, result2);
        assertEquals(AccountManager.CODE_SUCCESS, result3);

        assertTrue(accountManager.contains("user1", "pass1"));
        assertTrue(accountManager.contains("user2", "pass2"));
        assertTrue(accountManager.contains("user3", "pass3"));
    }

    @Test
    public void testAdd_InvalidCredentials() {
        int result1 = accountManager.add("username", "password");
        int result2 = accountManager.add("USERNAME", "PASSWORD");
        int result3 = accountManager.add("Username", "Password");

        assertEquals(AccountManager.CODE_SUCCESS, result1);
        assertNotEquals(AccountManager.CODE_IN_USE, result2);
        assertEquals(AccountManager.CODE_SUCCESS, result3);

        assertTrue(accountManager.contains("username", "password"));
        assertTrue(accountManager.contains("USERNAME", "PASSWORD"));
        assertTrue(accountManager.contains("Username", "Password"));
    }

}