package com.github.shionit.chronos.service;

import com.github.shionit.chronos.model.IBranch;
import com.google.common.base.Supplier;

import java.util.Objects;

/**
 * Created by @shionit on 2015/06/03.
 */
public class BranchService {

    private Supplier<? extends IBranch> branchFactory;

    public BranchService(Supplier<? extends IBranch> branchFactory) {
        Objects.nonNull(branchFactory);
        this.branchFactory = branchFactory;
    }

    public IBranch createDefaultBranch() {
        IBranch branch = branchFactory.get();
        branch.setBranchCode("(default)");
        return branch;
    }
}
