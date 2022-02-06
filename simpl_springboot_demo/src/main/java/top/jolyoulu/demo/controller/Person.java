package top.jolyoulu.demo.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: JolyouLu
 * @Date: 2021/11/1 14:26
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private Integer age;
    private String sex;
    private Date birthDate;
    private List<String> arrays;
    private Map<String, String> maps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getArrays() {
        return arrays;
    }

    public void setArrays(List<String> arrays) {
        this.arrays = arrays;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

}
