package com.lp3i.myapplication.model;

import java.io.Serializable;

public class ApiResponse implements Serializable {

    public int status = 0;
    public String message = "";
    public DataResponse data = null;

}
