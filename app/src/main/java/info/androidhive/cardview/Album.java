package info.androidhive.cardview;


public class Album {
    private String gameTitle,gameDescription,gameTime,gamePlace;



    public Album(String name, String description,String time, String place) {
        this.gameTitle = name;
        this.gameDescription = description;
        this.gamePlace=place;
        this.gameTime=time;

    }

    public String getGameTitle() {
        return gameTitle;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public String getGamePlace() {
        return gamePlace;
    }

    public String getGameTime() {
        return gameTime;
    }

    }







