package com.riversoft.weixin.contact.bean.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

/**
 * Created by exizhai on 10/12/2015.
 */
public class DepartmentList {

    private List<Department> departments;

    @JsonProperty("department")
    @JsonUnwrapped
    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
