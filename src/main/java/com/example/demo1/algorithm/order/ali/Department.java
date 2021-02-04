package com.example.demo1.algorithm.order.ali;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author liangqing
 * @since 2021/1/6 21:28
 */
@Slf4j
public class Department implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    //主键
    private Long id;

    private Integer departmentId;

    //当前部门编码
    private String departmentCode;


    //父部门Id
    private Integer parentDepartmentId;

    //父部门编码
    private String parentDepartmentCode;

    //部门组
    private Integer group;

    //删除标识
    private byte delFlag;

    //创建时间
    private LocalDateTime createTime;

    //修改时间
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Integer getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(Integer parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public String getParentDepartmentCode() {
        return parentDepartmentCode;
    }

    public void setParentDepartmentCode(String parentDepartmentCode) {
        this.parentDepartmentCode = parentDepartmentCode;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(byte delFlag) {
        this.delFlag = delFlag;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }


    public Department() {
    }


    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentId=" + departmentId +
                ", departmentCode='" + departmentCode + '\'' +
                ", parentDepartmentId=" + parentDepartmentId +
                ", parentDepartmentCode='" + parentDepartmentCode + '\'' +
                ", group=" + group +
                ", delFlag=" + delFlag +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    private void delete(Integer departmentId) {
        if (Objects.isNull(departmentId)) {
            return;
        }
        //删除自己
        Department department = getDbDepartmentByDepartmentId(departmentId);
        deleteById(department);

        //删除子节点
        dgDelete(department);

    }

    private void dgDelete(Department department) {
        List<Department> departmentTemps = getDbDepartmentByParentDepartmentId(department.getParentDepartmentId());
        //跳出循环
        if (CollectionUtils.isEmpty(departmentTemps)) {
            return;
        }
        final Department departmentTemp = departmentTemps.get(0);

        try {
            //递归调用删除
            dgDelete(departmentTemp);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //doSomething
        }
    }

    /**
     * 通过主键删除
     *
     * @param department
     * @return
     */
    private Integer deleteById(Department department) {
        /**
         通过主键删除
         */
        return department.getDepartmentId();
    }


    /**
     * 通过parentId获取
     *
     * @param departmentParentId
     * @return
     */
    private List<Department> getDbDepartmentByParentDepartmentId(Integer departmentParentId) {
        /**
         此处在数据库获取
         parent_department_id
         */
        return new ArrayList<>();
    }

    /**
     * 通过department_id获取
     *
     * @param departmentId
     * @return
     */
    private Department getDbDepartmentByDepartmentId(Integer departmentId) {
        /**
         此处在数据库获取
         department_id
         */
        return new Department();
    }

    List<Department> getDepartments(){

        //todo 去db查询，如果数据量大的话，可以做分页查询
        return new ArrayList<>();
    }


}
