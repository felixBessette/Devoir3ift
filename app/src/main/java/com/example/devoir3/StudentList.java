package com.example.devoir3;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;

public class StudentList {
    Context context;
    int[] avatar;
    public StudentList(Context c) {
        context = c;
    }
    public ArrayList<Student> initialiseStudentList() {
        ArrayList<Student> studentList = new ArrayList<>();
        String[] names = context.getResources().getStringArray(R.array.student_name);
        String[] interets = context.getResources().getStringArray(R.array.student_interet);
        avatar = new int[]{R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar7,
                R.drawable.avatar8, R.drawable.avatar9, R.drawable.avatar10, R.drawable.avatar11, R.drawable.avatar12, R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar7,
                R.drawable.avatar8, R.drawable.avatar9, R.drawable.avatar10, R.drawable.avatar11, R.drawable.avatar12};
        for (int i = 0; i < 18; i ++) {
            int fav = Color.RED;
            if (Math.random() > 0.5) {
                fav = Color.BLACK;
            }
            Student student = new Student(names[i], avatar[i], fav, interets[i], i);
            studentList.add(student);
        }
        return studentList;
    }

    public Student getStudent(ArrayList<Student> studentArrayList,  int id) {
        Student student = null;
        for (int i = 0; i<9; i++) {
            if (studentArrayList.get(i).getId() == id) {
                student = studentArrayList.get(i);
            }
        }
        return student;
    }

    public static class Student {
        String n;
        int p;
        int l;
        String i;
        int id;
        public Student(String name, int picture, int like, String interet, int ident) {
            n = name;
            p = picture;
            l = like;
            i = interet;
            id = ident;
        }
        public String getName() {return n;}
        public int getPic() {return p;}
        public int getLike() {return l;}
        public String getInteret() {return i;}
        public int getId() {return id;}
    }
}
