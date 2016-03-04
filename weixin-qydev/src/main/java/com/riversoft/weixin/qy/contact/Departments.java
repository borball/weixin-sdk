package com.riversoft.weixin.qy.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;
import com.riversoft.weixin.qy.contact.department.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by exizhai on 10/4/2015.
 */
public class Departments {

    private static Logger logger = LoggerFactory.getLogger(Users.class);

    private WxClient wxClient;

    public static Departments defaultDepartments() {
        return with(CorpSetting.defaultSettings());
    }

    public static Departments with(CorpSetting corpSetting) {
        Departments departments = new Departments();
        departments.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
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

    public List<Department> list() {
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

    /**
     * Created by exizhai on 10/12/2015.
     */
    public static class DepartmentList {

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
}
