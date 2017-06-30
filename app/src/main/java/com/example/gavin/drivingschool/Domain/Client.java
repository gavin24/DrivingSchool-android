package com.example.gavin.drivingschool.Domain;

/**
 * Created by gavin on 2017/06/27.
 */
public class Client {
    public long id ;
    public String Email ;
    public String Password ;
    public long PhoneNumber ;
    public String Name ;
    public String Surname;
    public long ClientAddressId ;


    private Client() {
    }


    public String getEmail(){return Email;}
    public String getPassword(){return Password;}
    public String getSurname() {
        return Surname;
    }
    public String getName() {
        return Name;
    }
    public long getId() {
        return id;
    }
    public long getPhoneNumber() {
        return PhoneNumber;
    }
    public long getClientAddressId() {
        return ClientAddressId;
    }

    public Client(Builder builder){
        this.id=builder.id;
        this.Email=builder.Email;
        this.Password=builder.Password;
        this.PhoneNumber=builder.PhoneNumber;
        this.Name=builder.Name;
        this.Surname=builder.Surname;
        this.ClientAddressId=builder.ClientAddressId;
    }

    public static class Builder {
        public long id ;
        public String Email ;
        public String Password ;
        public long PhoneNumber ;
        public String Name ;
        public String Surname;
        public long ClientAddressId ;


        public Builder ClientAddressId(long value) {
            this.ClientAddressId = value;
            return this;
        }


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


        public Builder Password(String value) {
            this.Password = value;
            return this;
        }

        public Builder Name(String value) {
            this.Name = value;
            return this;

        }
        public Builder Surname(String value) {
            this.Surname = value;
            return this;

        }


        public Builder copy(Client value) {
            this.id = value.id;
            this.ClientAddressId = value.ClientAddressId;
            this.PhoneNumber = value.PhoneNumber;
            this.Email = value.Email;
            this.Password = value.Password;
            this.Name = value.Name;
            this.Surname = value.Surname;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
