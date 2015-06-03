package com.github.shionit.chronos.model;

/**
 * Created by @shionit on 2015/06/03.
 */
public class Branch implements IBranch {

    private String branchCode;

    @Override
    public String getBranchCode() {
        return branchCode;
    }

    @Override
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
}
