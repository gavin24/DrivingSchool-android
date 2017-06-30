package com.example.gavin.drivingschool.Domain;

/**
 * Created by gavin on 2017/06/27.
 */
public class ContactInfo {
    public long id ;
    public long UserId ;
    public String Email ;
    public long PhoneNumber ;
    public String SecondaryEmail;

    private ContactInfo() {
    }


    public String getEmail(){return Email;}
    public String getSecondaryEmail(){return SecondaryEmail;}

    public long getId() {
        return id;
    }
    public long getPhoneNumber() {
        return PhoneNumber;
    }
    public long getUserId() {
        return UserId;
    }

    public ContactInfo(Builder builder){
        this.id=builder.id;
        this.Email=builder.Email;
        this.SecondaryEmail=builder.SecondaryEmail;
        this.PhoneNumber=builder.PhoneNumber;
        this.UserId=builder.UserId;

    }

    public static class Builder {
        public long id ;
        public long UserId ;
        public String Email ;
        public long PhoneNumber ;
        public String SecondaryEmail;





        public Builder id(long value) {
            this.id = value;
            return this;
        }

        public Builder PhoneNumber(long value) {
            this.PhoneNumber = value;
            return this;
        }

        public Builder Email(String value) {
            this.Email = value;
            return this;
        }


        public Builder SecondaryEmail(String value) {
            this.SecondaryEmail = value;
            return this;
        }

        public Builder UserId(long value) {
            this.UserId = value;
            return this;

        }



        public Builder copy(ContactInfo value) {
            this.id = value.id;
            this.UserId = value.UserId;
            this.PhoneNumber = value.PhoneNumber;
            this.Email = value.Email;
            this.SecondaryEmail = value.SecondaryEmail;

            return this;
        }

        public ContactInfo build() {
            return new ContactInfo(this);
        }
    }
}
