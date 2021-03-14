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
            int status = 0;
            if (Math.random() > 0.5) {
                fav = Color.LTGRAY;
                if (Math.random() > 0.66) {
                    status = 1; // Veut pas être dans ses amis
                }
                else if(Math.random() < 0.33) {
                    fav = Color.BLACK;
                }
                else {
                    status = 2; // Veut être son ami
                }
            }
            Student student = new Student(names[i], avatar[i], fav, interets[i], status);
            studentList.add(student);
        }
        return studentList;
    }

    public static class Student {
        String name;
        int picture;
        int like;
        String interet;
        int relation;
        public Student(String n, int p, int l, String i, int r) {
            name = n;
            picture = p;
            like = l;
            interet = i;
            relation = r;
        }
        public void sendRequest() {
            this.relation = 3;
        }
        public void acceptRequest() {
            this.like = Color.BLACK;
            this.relation = 0;
        }
        public void removeFriend() {
            this.relation = 1;
            this.like = Color.LTGRAY;
        }
        public String getName() {return name;}
        public int getPic() {return picture;}
        public int getLike() {return like;}
        public String getInteret() {return interet;}
        public int getId() {return relation;}
    }
}
