package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.models.domain.Country;
import mk.ukim.finki.emt.lab.models.enumerations.Category;

public class AuthorDto {


    String name;
    String surname;
    Long countryId;
    public AuthorDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public AuthorDto(String name, String surname, Long countryId) {
        this.name = name;
        this.surname = surname;
        this.countryId = countryId;
    }

}
