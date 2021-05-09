package com.thirdparty.cards.eligibility;

import javax.validation.constraints.NotNull;

public final class EligibilityApplicant {

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String email;

    public EligibilityApplicant() {}

    public EligibilityApplicant(String name, String email, String address) {

        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

}
