package com.example.gavin.drivingschool.Domain;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public class Image {
    private String name;
    private long id;

    private long carId;
    private byte[] image;

    private Image() {
    }




    public long getId() {
        return id;
    }
    public String getName(){return name;}


    public byte[] getImage(){return image;}
    public long getuserId(){return carId;}

    public Image(Builder builder){
        this.id=builder.id;
        this.name=builder.name;

        this.image=builder.image;
        this.carId=builder.carId;

    }

    public static class Builder {
        private long id;
        private String name;

        private byte[] image;
        private long carId;


        public Builder name(String value) {
            this.name = value;
            return this;
        }


        public Builder id(long value) {
            this.id = value;
            return this;
        }

        public Builder image(byte[] value) {
            this.image = value;
            return this;
        }

        public Builder userId(long value) {
            this.carId = value;
            return this;
        }



        public Builder copy(Image value) {
            this.id = value.id;
            this.name = value.name;
            this.image = value.image;

            this.carId = value.carId;

            return this;
        }

        public Image build() {
            return new Image(this);
        }
    }
}
