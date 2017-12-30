package weather.hashim.com.showweather;

/**
 * Created by hashim on 12/30/2017.
 */

public class Model {


    private String day;
    private String date;
    private String year;
    private String tempMain;
    private String tempSecondry;
    private String description;
    private String image;
    private String location;


    public Model(String day, String date, String year, String tempMain, String tempSecondry, String description,String location, String image) {
        this.day = day;
        this.date = date;
        this.year = year;
        this.tempMain = tempMain;
        this.tempSecondry = tempSecondry;
        this.description = description;
        this.location = location;
        this.image = image;
    }

    public Model(){

    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTempMain() {
        return tempMain;
    }

    public void setTempMain(String tempMain) {
        this.tempMain = tempMain;
    }

    public String getTempSecondry() {
        return tempSecondry;
    }

    public void setTempSecondry(String tempSecondry) {
        this.tempSecondry = tempSecondry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Model{" +
                "day='" + day + '\'' +
                ", date='" + date + '\'' +
                ", year='" + year + '\'' +
                ", tempMain='" + tempMain + '\'' +
                ", tempSecondry='" + tempSecondry + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
