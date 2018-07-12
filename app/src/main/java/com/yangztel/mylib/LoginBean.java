package com.yangztel.mylib;

/**
 * Created by yangzteL on 2018/7/9 0009.
 */

public class LoginBean{

    /**
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC96aGgtYXBpLnRlc3RcL2xvZ2luIiwiaWF0IjoxNTI2MjkyMDY0LCJleHAiOjE1MjYyOTU2NjQsIm5iZiI6MTUyNjI5MjA2NCwianRpIjoiOUpXSDFDc3dBV1JGd0RWcCIsInN1YiI6bnVsbCwicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyJ9.JGMYPUynIin7AnhWmkrX7_Z3ALrZfitZZQTIdR_cQEc
     * type : Bearer
     * expires_in : 3600
     */

    private String token;
    private String type;
    private String expires_in;

    private String name;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
