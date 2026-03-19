/**
* Represents a single South African place record.
* Stores the place name, municipality, province and population.
*
* @author mvlphi006
*/

public class PlaceNameEntry
{
    // Store the all the fiels of the one place
    private String placeName;
    private String province;
    private String municipality;
    private int population;
    
    /**
    * Constructs a PlaceNameEntry with all fields.
    * @param name the place name
    * @param muni the municipality
    * @param pro the province
    * @param pop the population
    */

    public PlaceNameEntry(String name, String muni, String pro, int pop)
    {
        placeName = name;
        municipality = muni;
        province = pro;
        population = pop;
    }
    
    /** @param name the new place name*/
    public void setPlaceName(String name)
    {
        placeName = name;
    }
    
    /** @param muni the new municipality */
    public void setMunicipality(String muni)
    {
        municipality = muni;
    }
    
    /** @param pro the new population */
    public void setProvince(String pro)
    {
        province = pro;
    }
    
    /** @param pop the new population */
    public void setPopulation(int pop)
    {
        population = pop;
    }
    
    /** @return the place name */
    public String getPlaceName()
    {
        return placeName;
    }
    
    /** @return the municipality */
    public String getMunicipality()
    {
        return municipality;
    }
    
    /** @return the province */
    public String getProvince()
    {
        return province;
    }
    
    /** @return the population */
    public int getPopulation()
    {
        return population;
    }
    
}