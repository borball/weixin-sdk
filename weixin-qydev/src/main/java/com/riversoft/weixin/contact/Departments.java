package com.riversoft.weixin.contact;

import com.riversoft.weixin.base.WxClient;
import com.riversoft.weixin.contact.bean.department.Department;
import com.riversoft.weixin.contact.bean.department.DepartmentList;
import com.riversoft.weixin.util.JsonMapper;
import com.riversoft.weixin.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by exizhai on 10/4/2015.
 */
public class Departments {

    private static Logger logger = LoggerFactory.getLogger(Users.class);

    private static Departments departments = null;
    private WxClient wxClient;

    public static Departments defaultDepartments() {
        if (departments == null) {
            departments = new Departments();
            departments.setWxClient(WxClient.defaultWxClient());
        }

        return departments;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public Department get(int id) {
        String url = WxEndpoint.get("url.department.get");
        String user = wxClient.get(String.format(url, id));
        logger.debug("get department: {}", user);
        return JsonMapper.nonEmptyMapper().fromJson(user, Department.class);
    }

    public void create(Department department) {
        String url = WxEndpoint.get("url.department.create");
        String json = JsonMapper.nonEmptyMapper().toJson(department);
        logger.debug("create department: {}", json);
        wxClient.post(url, json);
    }

    public void update(Department department) {
        String url = WxEndpoint.get("url.department.update");
        String json = JsonMapper.nonEmptyMapper().toJson(department);
        logger.debug("update department: {}", department);
        wxClient.post(url, json);
    }

    public void delete(int id) {
        String url = WxEndpoint.get("url.department.delete");
        logger.debug("delete department: {}", id);
        wxClient.get(String.format(url, id));
    }

    public List<Department> list(){
        String url = WxEndpoint.get("url.department.list.all");
        String response = wxClient.get(url);
        logger.debug("list all departments: {}", response);
        DepartmentList departmentList = JsonMapper.nonEmptyMapper().fromJson(response, DepartmentList.class);
        return departmentList.getDepartments();
    }

    public List<Department> list(int id) {
        String url = WxEndpoint.get("url.department.list");
        String response = wxClient.get(String.format(url, id));
        logger.debug("list departments: {}", response);
        DepartmentList departmentList = JsonMapper.nonEmptyMapper().fromJson(response, DepartmentList.class);
        return departmentList.getDepartments();
    }
}
