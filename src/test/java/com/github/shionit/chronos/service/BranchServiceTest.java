package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.Branch;
import com.github.shionit.chronos.model.IBranch;
import com.google.common.base.Supplier;
import static org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by @shionit on 2015/06/04.
 */
public class BranchServiceTest {

    @Test
    public void testCreateDefaultBranch() throws Exception {
        //BranchService target = new BranchService(() -> new Branch());
        BranchService target = new BranchService(new Supplier<IBranch>() {
            @Override
            public IBranch get() {
                return new Branch();
            }
        });

        final Branch result1 = (Branch) target.createDefaultBranch();
        final Branch result2 = (Branch) target.createDefaultBranch();

        assertNotEquals(result1, result2);

        result1.setBranchCode("branch1");
        result2.setBranchCode("branch2");

        assertEquals(result1.getBranchCode(), "branch1");
        assertEquals(result2.getBranchCode(), "branch2");
    }
}