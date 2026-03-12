// A class to store all fields for one place
// MVLPHI006
// 06 March 2026

public class PlaceNameEntry
{
    // Store the all the fiels of the one place
    private String placeName;
    private String province;
    private String municipality;
    private int population;

    public PlaceNameEntry(String name, String muni, String pro, int pop)
    {
        placeName = name;
        municipality = muni;
        province = pro;
        population = pop;
    }

    public void setPlaceName(String name)
    {
        placeName = name;
    }
    // Setters
    public void setMunicipality(String muni)
    {
        municipality = muni;
    }
    public void setProvince(String pro)
    {
        province = pro;
    }
    public void setPopulation(int pop)
    {
        population = pop;
    }
    // Getters
    public String getPlaceName()
    {
        return placeName;
    }
    public String getMunicipality()
    {
        return municipality;
    }
    public String getProvince()
    {
        return province;
    }
    public int getPopulation()
    {
        return population;
    }
    
}