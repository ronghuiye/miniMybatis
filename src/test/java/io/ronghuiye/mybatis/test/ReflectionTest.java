package io.ronghuiye.mybatis.test;

import com.alibaba.fastjson.JSON;
import io.ronghuiye.mybatis.reflection.MetaObject;
import io.ronghuiye.mybatis.reflection.SystemMetaObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ReflectionTest {

    private Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void test_reflection() {
        Teacher teacher = new Teacher();
        List<Teacher.Student> list = new ArrayList<>();
        list.add(new Teacher.Student());
        teacher.setName("ryan");
        teacher.setStudents(list);

        MetaObject metaObject = SystemMetaObject.forObject(teacher);

        logger.info("getGetterNames：{}", JSON.toJSONString(metaObject.getGetterNames()));
        logger.info("getSetterNames：{}", JSON.toJSONString(metaObject.getSetterNames()));
        logger.info("name-get：{}", JSON.toJSONString(metaObject.getGetterType("name")));
        logger.info("students-set：{}", JSON.toJSONString(metaObject.getGetterType("students")));
        logger.info("name-hasGetter：{}", metaObject.hasGetter("name"));
        logger.info("student.id-hasGetter：{}", metaObject.hasGetter("student.id"));
        logger.info("name：{}", metaObject.getValue("name"));

        metaObject.setValue("name", "ye");
        logger.info("name：{}", metaObject.getValue("name"));

        metaObject.setValue("students[0].id", "001");
        logger.info("students：{}", JSON.toJSONString(metaObject.getValue("students[0].id")));
        logger.info("object：{}", JSON.toJSONString(teacher));
    }

    static class Teacher {

        private String name;

        private double price;

        private List<Student> students;

        private Student student;

        public static class Student {

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
    }

}
