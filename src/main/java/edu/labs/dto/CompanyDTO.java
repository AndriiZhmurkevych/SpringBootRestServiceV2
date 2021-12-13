package edu.labs.dto;

public class CompanyDTO {

    private long id;
    private String companyName;
    private String country;

    public CompanyDTO(final long id, final String companyName, final String country) {
        this.id = id;
        this.companyName = companyName;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
