package info.androidhive.cardview;


import android.os.Parcel;
import android.os.Parcelable;

public class Album implements Parcelable {
    private String gameTitle,gameDescription,gameTime,gamePlace;
    private String thumbnailUri;



    public Album(String name, String description, String time, String place, String thumbnailUri) {
        this.gameTitle = name;
        this.gameDescription = description;
        this.gamePlace=place;
        this.gameTime=time;
        this.thumbnailUri = thumbnailUri;

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

    public String getThumbanilUri(){return thumbnailUri;}

    public Album(Parcel in){
        this.gameTitle = in.readString();
        this.gameDescription = in.readString();
        this.gamePlace =  in.readString();
        this.gameTime =  in.readString();
        this.thumbnailUri = in.readString();
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gameTitle);
        dest.writeString(this.gameDescription);
        dest.writeString(this.gamePlace);
        dest.writeString(this.gameTime);
        dest.writeString(this.thumbnailUri);
    }

    @Override
    public String toString() {
        String string;
        string = gameTitle+" "+gameDescription+" "+gamePlace+" "+gameTime+thumbnailUri;
        return string;
    }
}







