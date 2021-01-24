package com.piyush.researchapp;

import java.io.Serializable;

public class ResearchData implements Serializable {

    private String userId;
    private String email;
    private int marks;
    private int posttestmarks;
    private Boolean pretest;
    private Boolean pretest2;
    private Boolean posttest;
    //private Boolean loggedIn;
    private String a1,a2,a3,a4,a5,a6,a7,a8;

    public ResearchData(String userId, String email, int marks, Boolean pretest,Boolean pretest2, int posttestmarks/*, Boolean loggedIn*/, String a1,String a2,String a3,String a4,String a5,String a6,String a7,String a8) {
        this.userId = userId;
        this.email = email;
        this.marks = marks;
        this.pretest = pretest;
        this.pretest2 = pretest2;
        this.posttestmarks = posttestmarks;
        //this.loggedIn = loggedIn;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.a5 = a5;
        this.a6 = a6;
        this.a7 = a7;
        this.a8 = a8;
    }

    public ResearchData(){

    }

    public ResearchData(Boolean pretest) {
        this.pretest = pretest;
    }



    public ResearchData(Boolean posttest, String userId) {
        this.posttest = posttest;
        this.userId = userId;
    }

    public ResearchData(String userId,int posttestmarks) {
        this.userId = userId;
        this.posttestmarks = posttestmarks;
    }

    public ResearchData(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public ResearchData(String userId, String email, Boolean pretest2) {
        this.userId = userId;
        this.email = email;
        this.pretest2 = pretest2;
    }

    /*public ResearchData(String a1) {
        this.a1 = a1;
    }

    public ResearchData(String a2) {
        this.a2 = a2;
    }

    public ResearchData(String a3) {
        this.a3 = a3;
    }

    public ResearchData(String a4) {
        this.a4 = a4;
    }

    public ResearchData(String a5) {
        this.a5 = a5;
    }

    public ResearchData(String a6) {
        this.a6 = a6;
    }

    public ResearchData(String a7) {
        this.a7 = a7;
    }

    public ResearchData(String a8) {
        this.a8 = a8;
    }*/

    public ResearchData(int marks) {
        this.marks = marks;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /*public String[] getanswers() {
        return answers;
    }

    public void setUserId(String[] answers) {
        this.answers = answers;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String geta1() {
        return a1;
    }

    public void seta1(String a1) {
        this.a1 = a1;
    }

    public String geta2() {
        return a2;
    }

    public void seta2(String a2) {
        this.a2 = a2;
    }

    public String geta3() {
        return a3;
    }

    public void seta3(String a3) {
        this.a3 = a3;
    }

    public String geta4() {
        return a4;
    }

    public void seta4(String a4) {
        this.a4 = a4;
    }

    public String geta5() {
        return a5;
    }

    public void seta5(String a5) {
        this.a5 = a5;
    }

    public String geta6() {
        return a6;
    }

    public void seta6(String a6) {
        this.a6 = a6;
    }

    public String geta7() {
        return a7;
    }

    public void seta7(String a7) {
        this.a7 = a7;
    }

    public String geta8() {
        return a8;
    }

    public void seta8(String a8) {
        this.a8 = a8;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Boolean getPretest() {
        return pretest;
    }

    public void setPretest(Boolean pretest) {
        this.pretest = pretest;
    }

    public Boolean getPretest2() {
        return pretest2;
    }

    public void setPretest2(Boolean pretest2) {
        this.pretest2 = pretest2;
    }

    /*public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }*/

    public int getPosttestmarks() {
        return posttestmarks;
    }

    public void setPosttestmarks(int posttestmarks) {
        this.posttestmarks = posttestmarks;
    }

    public Boolean getPosttest() {
        return posttest;
    }

    public void setPosttest(Boolean posttest) {
        this.posttest = posttest;
    }
}
