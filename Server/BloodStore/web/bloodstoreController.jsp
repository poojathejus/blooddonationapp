
<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<%@page import="Bldstore_dbconnection.dbconnection"%>



<%
    dbconnection mycon = new dbconnection();

    String key = request.getParameter("key");
    System.out.println(key);

    if (key.equals("student_enroll")) {

        String name = request.getParameter("name");
        String course = request.getParameter("course");
        String batch = request.getParameter("batch");
        String dob = request.getParameter("dob").trim();
        String blood = request.getParameter("blood").trim();
        String mail = request.getParameter("email");

        String mobile = request.getParameter("phone");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");

        String str = "INSERT INTO `studentenroll_tb` "
                + "(`st_name`,`st_phone`,`st_email`,`st_address`,`st_dob`,`st_bldgrp`,`st_course`,`st_batch`,`st_gender`) "
                + "VALUES ('" + name + "','" + mobile + "','" + mail + "','" + address + "','" + dob + "','"
                + blood + "','" + course + "','" + batch + "','" + gender + "')";

        if (mycon.putData(str) > 0) {

            String getID = "SELECT MAX(`enroll_id`) FROM `studentenroll_tb`";

            System.out.println(str);
            Iterator i = (Iterator) mycon.getData(getID).iterator();

            String rollnum = "";
            if (i.hasNext()) {

                Vector v = (Vector) i.next();
                rollnum = v.get(0).toString().trim();

                out.println("success" + "#" + rollnum);
                System.out.println("success" + "#" + rollnum);
            }

        } else {
            out.println("failed");
        }

    }

    if (key.equals("student_search")) {

        String bloodname = request.getParameter("bloodgroup");
        String result = "";
        String stu_id = "", name = "", course = "", batch = "", gender = "", phone = "" , address = "";

        // String qry = "SELECT * FROM `studentenroll_tb` WHERE `st_bldgrp` ='" + bloodname + "'";
        
      //  String qry = "SELECT * FROM `blooddonor_tb` WHERE `bloodgrp` ='" + bloodname + "'";
       // System.out.println(qry);
        
       String qry="SELECT DISTINCT `donorname`,`phoneno`,`address` FROM `blooddonor_tb` WHERE `bloodgrp`='"+bloodname+"'";
        System.out.println(qry);
        
        Iterator it = mycon.getData(qry).iterator();

        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();

//                stu_id += v.get(0).toString() + ":";
//                name += v.get(1).toString() + ":";
//                course += v.get(7).toString() + ":";
//                batch += v.get(8).toString() + ":";
//                gender += v.get(9).toString() + ":";
                name += v.get(0).toString() + ":";
                phone += v.get(1).toString() + ":";
                address += v.get(2).toString() + ":";
            }

            result += name + "#" + phone + "#" + address + "#";

//            result += name + "#" + course + "#" + batch + "#" + gender + "#";
            System.out.println(result);
            out.print(result);
        } else {
            System.out.println("else id=" + result);
            out.print("failed");
        }
    }

    if (key.equals("student_search_category")) {

        String mylist = "";

        String searchwrd, searchcat;
        searchwrd = request.getParameter("searchword");
        searchcat = request.getParameter("searchcat");

        if (searchcat.equals("Name")) {

         //   String qry1 = "SELECT * FROM `studentenroll_tb` WHERE `st_name` LIKE '%" + searchwrd + "%'";
         //   System.out.println(qry1);
         
//         String qry1=" SELECT * FROM `blooddonor_tb` WHERE `donorname` LIKE '%"+searchwrd+"%'";
//         System.out.println(qry1);

            String qry1="SELECT DISTINCT `bloodgrp`,`phoneno`,`donortype`,`donorname` FROM `blooddonor_tb` WHERE `donorname` LIKE '%"+searchwrd+"%'";
            System.out.println(qry1);
            
            String result = "";
            String stu_id = "", name = "", course = "", batch = "", gender = "",phono = "", bg = "" ,category = "";

            Iterator it = mycon.getData(qry1).iterator();

            if (it.hasNext()) {
                while (it.hasNext()) {
                    Vector v = (Vector) it.next();

         //           mylist += v.get(3).toString() + "#";

                    stu_id += v.get(0).toString() + ":";
 //                   name += v.get(1).toString() + ":";
//                    course += v.get(7).toString() + ":";
//                    batch += v.get(8).toString() + ":";
//                    gender += v.get(9).toString() + ":";
                    
                    bg += v.get(0).toString() + ":";
                    phono +=v.get(1).toString() + ":";
                    category += v.get(2).toString() + ":";
                    name +=v.get(3).toString() + ":";
                    

                }
              //  result += name + "#" + course + "#" + batch + "#" + gender + "#";
              result += bg + "#" + phono + "#" + category + "#" + name +"#";
                System.out.println(result);
                out.print(result);
            } else {
                out.println("nullarray");
                System.out.println("nullarray by Name");
            }
        } else if (searchcat.equals("Id")) {

//            String qry2 = "SELECT * FROM `studentenroll_tb` WHERE `enroll_id`='" + searchwrd + "'";
//
//            System.out.println(qry2);

//          String qry2="SELECT * FROM `blooddonor_tb` WHERE `regid` ='"+ searchwrd +"'";
//         System.out.println(qry2);

        String qry1="SELECT DISTINCT `bloodgrp`,`phoneno`,`donortype`,`donorname` FROM `blooddonor_tb` WHERE `regid` ='"+ searchwrd +"'";
            System.out.println(qry1);
        

            String result = "";
            String stu_id = "", name = "", course = "", batch = "", gender = "", bg = "", phno = "",category = "";

            Iterator it = mycon.getData(qry1).iterator();

            if (it.hasNext()) {
                while (it.hasNext()) {
                    Vector v = (Vector) it.next();

                 //   mylist += v.get(3).toString() + "#";

//                    stu_id += v.get(0).toString() + ":";
//                    name += v.get(1).toString() + ":";
//                    course += v.get(7).toString() + ":";
//                    batch += v.get(8).toString() + ":";
//                    gender += v.get(9).toString() + ":";
 //                   name += v.get(6).toString() + ":";
                    bg += v.get(0).toString() + ":";
                    phno += v.get(1).toString() + ":";
                    category += v.get(2).toString() + ":";
                    name += v.get(3).toString() + ":";

                }
               // result += name + "#" + course + "#" + batch + "#" + gender + "#";
               result +=  bg + "#" + phno + "#" + category + "#" +name +"#";
                System.out.println(result);
                out.print(result);
            } else {
                out.println("nullarray");
                System.out.println("nullarray by ID");
            }

        }

    }

    if (key.equals("getStudentbyId")) {

        String studenID = request.getParameter("stud_id").trim();

        String qry = "SELECT * FROM `studentenroll_tb` WHERE `enroll_id`='" + studenID + "'";

        System.out.println(qry);

        String result = "";
        String stu_id = "", name = "", course = "", batch = "", gender = "", dob = "", contact = "", address = "";

        Iterator it = mycon.getData(qry).iterator();

        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();

                result = v.get(1).toString() + "#" + v.get(9).toString() + "#" + v.get(5).toString() + "#" + v.get(7).toString() + "#"
                        + v.get(8).toString() + "#" + v.get(6).toString() + "#" + v.get(4).toString() + "#" + v.get(2).toString() + "#";
            }

            System.out.println(result);
            out.print(result);
        } else {
            out.println("nullarray");
            System.out.println("nullarray by ID");
        }
    }
    //11111111111111111111111111

    if (key.equals("enrollblooddonor")) {

        String regid = request.getParameter("reg_id").trim();
        String gender = request.getParameter("gender");
        String donortype = request.getParameter("type");
        String blood = request.getParameter("bldgrp").trim();
        String name = request.getParameter("name").trim();
        String phone = request.getParameter("contact").trim();
        String address=request.getParameter("address").trim();
        
        System.out.println(address);
        String donatedate = java.time.LocalDate.now().toString();

        String str = "insert into `blooddonor_tb` (`regid`,`bloodgrp`,`gender`,`donortype`,`donationdate`,`donorname`,`phoneno`,`address`) "
                + "values ('" + regid + "','" + blood + "','" + gender + "','" + donortype + "','" + donatedate + "','" + name + "','" + phone + "','" + address + "')";

        System.out.println(str);

        if (mycon.putData(str) > 0) {

            out.println("success");

        } else {
            out.println("failed");
        }

    }

    if (key.equals("getdonationdates")) {

        String qry = "SELECT DISTINCT(`donationdate`) FROM `blooddonor_tb` ORDER BY `donationdate` DESC";

        Iterator it = mycon.getData(qry).iterator();
        String dates = "";

        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                dates += v.get(0).toString() + ":";
            }

            System.out.println("success#" + dates);
            out.print("success#" + dates);

        } else {

            out.println("nullarray");

            System.out.println("nullarray");

        }
    }

    if (key.equals("getdonationlistdatewise")) {

        String date = request.getParameter("date").trim();

        String qry = "select * from `blooddonor_tb` where `donationdate`='" + date + "'";

        Iterator it = mycon.getData(qry).iterator();
        String dates = "";

        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();

                dates += "NAME         - " + v.get(6).toString().toUpperCase() + "\n" + "REG NUM   - " + v.get(1).toString() + "\n" + "GROUP       - " + v.get(2).toString() + "\n" + "PHONE     - " + v.get(7).toString() + "\n" + "CATEGORY - " + v.get(4).toString().toUpperCase()
                        + ":";
            }

            System.out.println("success#" + dates);
            out.print("success#" + dates);

        } else {

            out.println("nullarray");

            System.out.println("nullarray");

        }

    }

//*************************************************************************************************************************************************************************
    if (key.equals("staff_enroll")) {
//         System.out.println("vbcxvbcxfgbcx");

        String name = request.getParameter("name");
        String dob = request.getParameter("dob").trim();
        String department = request.getParameter("department");
        String address = request.getParameter("address");
        String phoneno = request.getParameter("phoneno");
        String bloodgrp = request.getParameter("bloodgrp").trim();

        String str = "INSERT INTO `staffenrol_tb` "
                + "(`name`,`dob`,`department`,`address`,`phoneno`,`bloodgrp` )"
                + "VALUES ('" + name + "','" + dob + "','" + department + "','" + address + "','"
                + phoneno + "','" + bloodgrp + "')";

        System.out.println("qry      " + str);

        if (mycon.putData(str) > 0) {

            String getID = "SELECT MAX(`id`) FROM `staffenrol_tb`";

            System.out.println(str);
            Iterator i = (Iterator) mycon.getData(getID).iterator();

            String staffid = "";
            if (i.hasNext()) {

                Vector v = (Vector) i.next();
                staffid = v.get(0).toString().trim();

                out.println("success" + "#" + staffid);
                System.out.println("success" + "#" + staffid);
            }

        } else {
            out.println("failed");
        }

//            out.println("success");
//        } else {
//            out.println("failed");
//        }
    }

//*************************************************************************************************************************************************************************
    if (key.equals("nonstaff_enroll")) {
//         System.out.println("vbcxvbcxfgbcx");

        String name = request.getParameter("name");
        String dob = request.getParameter("dob").trim();
        String address = request.getParameter("address");
        String phoneno = request.getParameter("phoneno");
        String bloodgrp = request.getParameter("bloodgrp").trim();

        String str = "INSERT INTO `nonstaffenroll_tb` "
                + "(`name`,`dob`,`address`,`phoneno`,`bloodgrp` )"
                + "VALUES ('" + name + "','" + dob + "','" + address + "','"
                + phoneno + "','" + bloodgrp + "')";

        System.out.println("qry      " + str);

        if (mycon.putData(str) > 0) {

            out.println("success");
        } else {
            out.println("failed");
        }

    }

    if (key.equals("getFacultybyId")) {

        String studenID = request.getParameter("fac_id").trim();

        String qry = "SELECT * FROM `staffenrol_tb` WHERE `id`='" + studenID + "'";

        System.out.println(qry);

        String result = "";

        Iterator it = mycon.getData(qry).iterator();

        if (it.hasNext()) {

            while (it.hasNext()) {
                Vector v = (Vector) it.next();

                result = v.get(0).toString() + "#" + v.get(1).toString() + "#" + v.get(2).toString() + "#" + v.get(3).toString() + "#"
                        + v.get(4).toString() + "#" + v.get(5).toString() + "#" + v.get(6).toString() + "#";
            }

            System.out.println(result);
            out.print(result);
        } else {
            out.println("nullarray");
            System.out.println("nullarray by ID");
        }
    }

    if (key.equals("getnonfaclist")) {

        String result = "";
        String nonfac_id = "", name = "", dob = "", address = "", phno = "", bldgrp = "";

        String qry = "SELECT * FROM `nonstaffenroll_tb`";
        System.out.println(qry);
        Iterator it = mycon.getData(qry).iterator();

        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();

                nonfac_id += v.get(0).toString() + ":";
                name += v.get(1).toString() + ":";
                dob += v.get(2).toString() + ":";
                address += v.get(3).toString() + ":";
                phno += v.get(4).toString() + ":";
                bldgrp += v.get(5).toString() + ":";

            }

            result += nonfac_id + "#" + name + "#" + dob + "#" + address + "#" + phno + "#" + bldgrp + "##";

            System.out.println(result);
            out.print(result);
        } else {
            System.out.println("else id=" + result);
            out.print("failed");
        }
    }

    if (key.equals("category")) {
        String id = request.getParameter("id");
        String cat = request.getParameter("categname");

        if (cat.equals("student")) {
            String stid = request.getParameter("id").trim();

            String result = "";
            String name = "", course = "", batch = "", dob = "", bldgrp = "", gender = "", email = "", phno = "", address = "";
            String qry = "SELECT * FROM `studentenroll_tb` WHERE `enroll_id`='" + stid + "'";
            System.out.println(qry);

            Iterator it = mycon.getData(qry).iterator();

            if (it.hasNext()) {

                Vector v = (Vector) it.next();

                name += v.get(1).toString();
                course += v.get(7).toString();
                batch += v.get(8).toString();
                dob += v.get(5).toString();
                gender += v.get(9).toString();
                email += v.get(3).toString();
                address += v.get(4).toString();
                phno += v.get(2).toString();
                bldgrp += v.get(6).toString();

                result += "student#" + name + "#" + phno + "#" + email + "#" + address + "#" + dob + "#" + bldgrp + "#" + course + "#" + batch + "#" + gender + "#";

                System.out.println(result);
                out.print(result);
            } else {
                out.print("failed");
            }
        }
        if (cat.equals("Faculty")) {
            String sfid = request.getParameter("id").trim();
            String result = "";
            String name = "", department = "", dob = "", bldgrp = "", phno = "", address = "";
            String qry = "SELECT * FROM `staffenrol_tb` WHERE `id`='" + sfid + "'";
            System.out.println(qry);

            Iterator it = mycon.getData(qry).iterator();
            if (it.hasNext()) {
                Vector v = (Vector) it.next();
                name += v.get(1).toString();
                department += v.get(3).toString();
                dob += v.get(2).toString();
                bldgrp += v.get(6).toString();
                phno += v.get(5).toString();
                address += v.get(4).toString();

                result += "Faculty#" + name + "#" + department + "#" + dob + "#" + bldgrp + "#" + phno + "#" + address + "#";
                System.out.println(result);

                out.println(result);
            } else {
                out.print("failed");
            }

        }
        if (cat.equals("Non Faculty")) {
            String sfid = request.getParameter("id").trim();
            String result = "";
            String name = "", dob = "", bldgrp = "", phno = "", address = "";
            String qry = "SELECT * FROM `nonstaffenroll_tb` WHERE `nonfac_id`='" + sfid + "'";
            System.out.println(qry);

            Iterator it = mycon.getData(qry).iterator();
            if (it.hasNext()) {
                Vector v = (Vector) it.next();
                name += v.get(1).toString();
                dob += v.get(2).toString();
                bldgrp += v.get(5).toString();
                phno += v.get(4).toString();
                address += v.get(3).toString();

                result += "Non Faculty#" + name + "#" + dob + "#" + bldgrp + "#" + phno + "#" + address + "#";
                System.out.println(result);

                out.println(result);
            } else {
                out.print("failed");
            }

        }

    }

    if (key.equals("studentupdate")) {
        String idd, name, course, batch, dob, bg, gender, mail, address, phno;

        idd = request.getParameter("id");
        name = request.getParameter("name");
        course = request.getParameter("course");
        batch = request.getParameter("batch");
        dob = request.getParameter("dob");
        bg = request.getParameter("bloodgrp");
        gender = request.getParameter("gender");
        mail = request.getParameter("mail");
        phno = request.getParameter("phno");
        address = request.getParameter("address");

        String str = "UPDATE `studentenroll_tb` SET `st_name`='" + name + "',`st_phone`='" + phno + "',`st_email`='" + mail + "',`st_address`='" + address + "',"
                + "`st_dob`='" + dob + "',`st_bldgrp`='" + bg + "',`st_course`='" + course + "',`st_batch`='" + batch + "',`st_gender`='" + gender + "' WHERE `enroll_id`='" + idd + "'";

        System.out.println(str);

        String student = "UPDATE `blooddonor_tb` SET `bloodgrp`='" + bg + "',`gender`='" + gender + "',`donorname`='" + name + "',`phoneno`='" + phno + "' , `address`='"+address+"' WHERE `regid`='" + idd + "'";

        System.out.println(student);

//        if (mycon.putData(str) > 0) {
//
//            out.println("success");
//
//        } else {
//            out.println("failed");
//        }
        if (mycon.putData(str) > 0 && mycon.putData(student) > 0) {

            out.println("success");

        } else {
            out.println("failed");
        }

    }
    if (key.equals("staffupdate")) {

        String idd, name, department, dob, bg, address, phno;

        idd = request.getParameter("id");
        name = request.getParameter("name");
        dob = request.getParameter("dob");
        department = request.getParameter("department");
        address = request.getParameter("address");
        phno = request.getParameter("phno");
        bg = request.getParameter("bloodgrp");

        String str = " UPDATE `staffenrol_tb` SET `name`='" + name + "',`dob`='" + dob + "',`department`='" + department + "',`address`='" + address + "',`phoneno`='" + phno + "',`bloodgrp`='" + bg + "' WHERE `id`='" + idd + "'";

        System.out.println(str);

        String staff = "UPDATE `blooddonor_tb` SET `bloodgrp`='" + bg + "',`donorname`='" + name + "',`phoneno`='" + phno + "', `address`='"+address+"' WHERE `regid`='" + idd + "'";

        System.out.println(staff);

        if (mycon.putData(str) > 0 && mycon.putData(staff) > 0) {
            // if (mycon.putData(str) > 0) {

            out.println("success");

        } else {
            out.println("failed");
        }

    }
    if (key.equals("nonstaffupdate")) {

        String idd, name, dob, bg, address, phno;

        idd = request.getParameter("id");
        name = request.getParameter("name");
        dob = request.getParameter("dob");
        address = request.getParameter("address");
        phno = request.getParameter("phno");
        bg = request.getParameter("bloodgrp");

        String str = " UPDATE `nonstaffenroll_tb` SET `name`='" + name + "',`dob`='" + dob + "',`address`='" + address + "',`phoneno`='" + phno + "',`bloodgrp`='" + bg + "' WHERE `nonfac_id`='" + idd + "'";

        System.out.println(str);

        String nonstaff = "UPDATE `blooddonor_tb` SET `bloodgrp`='" + bg + "',`donorname`='" + name + "',`phoneno`='" + phno + "' , `address`='"+address+"' WHERE `regid`='" + idd + "'";

        System.out.println(nonstaff);

        if (mycon.putData(str) > 0 && mycon.putData(nonstaff) > 0) {

            out.println("success");

        } else {
            out.println("failed");
        }

    }


%>



