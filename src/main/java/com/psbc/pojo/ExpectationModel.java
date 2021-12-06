package com.psbc.pojo;

import com.psbc.pojo.DatabaseModel;

public interface ExpectationModel extends DatabaseModel {
    default String getStatus(){return null;}
}
