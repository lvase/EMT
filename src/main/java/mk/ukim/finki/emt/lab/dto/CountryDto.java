package mk.ukim.finki.emt.lab.dto;

public class CountryDto {

    String name;
    String continent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public CountryDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}
