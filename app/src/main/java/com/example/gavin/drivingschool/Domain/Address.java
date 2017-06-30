package com.example.gavin.drivingschool.Domain;

/**
 * Created by gavin on 2017/06/27.
 */
public class Address {
    public long id ;
    public long ClientId ;
    public String Line1 ;
    public String Line2 ;
    public String Line3 ;
    public String Line4 ;
    public String Line5 ;

    private Address() {
    }


    public String getLine1(){return Line1;}
    public String getLine2(){return Line2;}
    public String getLine3() {
        return Line3;
    }
    public long getId() {
        return id;
    }
    public long getClientId() {
        return id;
    }
    public String getLine4(){return Line4;}
    public String getLine5(){return Line5;}

    public Address(Builder builder){
        this.id=builder.id;
        this.ClientId=builder.ClientId;
        this.Line1=builder.Line1;
        this.Line2=builder.Line2;
        this.Line3=builder.Line3;
        this.Line4=builder.Line4;
        this.Line5=builder.Line5;
    }

    public static class Builder {
        public long id ;
        public long ClientId ;
        public String Line1 ;
        public String Line2 ;
        public String Line3 ;
        public String Line4 ;
        public String Line5 ;

        public Builder ClientId(long value) {
            this.ClientId = value;
            return this;
        }


        public Builder id(long value) {
            this.id = value;
            return this;
        }

        public Builder Line1(String value) {
            this.Line1 = value;
            return this;
        }

        public Builder Line2(String value) {
            this.Line2 = value;
            return this;
        }


        public Builder Line3(String value) {
            this.Line3 = value;
            return this;
        }

        public Builder Line4(String value) {
            this.Line4 = value;
            return this;

        }


        public Builder Line5(String value) {
            this.Line5 = value;
            return this;

        }

        public Builder copy(Address value) {
            this.id = value.id;
            this.ClientId = value.ClientId;
            this.Line1 = value.Line1;
            this.Line2 = value.Line2;
            this.Line3 = value.Line3;
            this.Line4 = value.Line4;
            this.Line5 = value.Line5;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
