package com.mindblank.entities;

import java.sql.ResultSet;
import java.sql.Statement;

public class Admin extends User {
    public Admin(User user) {
        super(user.uName, user.uPass, user.realName, user.email, user.phoneNum, user.address, user.userType);
    }

    public void setAdminInfoFromDB(String NRIC) {
        String getDoctorInfo = "SELECT * FROM USER WHERE NRIC = '" + NRIC + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getDoctorInfo);

            while (queryResult.next()) {
                this.uName = queryResult.getString(1);
                this.realName = queryResult.getString(3);
                this.email = queryResult.getString(4);
                this.phoneNum = queryResult.getString(5);
                this.address = queryResult.getString(6);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public boolean checkNRIC(String NRIC, boolean inDatabase) {
        String validate = "SELECT * FROM USER WHERE NRIC = '" + NRIC + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(validate);

            if (queryResult.next() == inDatabase) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public boolean insertUser(User u) {
        String addUser = "INSERT INTO USER (NRIC, pass, name, email, phoneNum, address, type) " +
                "VALUES ('" + u.getuName() + "', '" + u.getuPass() + "', '" + u.getRealName() + "', '" + u.getEmail() +
                "', '" + u.getPhoneNum() + "', '" + u.getAddress() + "', '" + u.getUserType() + "');";
        try {
            Statement statement = connectDB.createStatement();
            statement.execute(addUser);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return false;
        }
    }

    public User getUserFromDB(String NRIC) {
        String getDoctorInfo = "SELECT * FROM USER WHERE NRIC = '" + NRIC + "';";
        User u = new User();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getDoctorInfo);

            while (queryResult.next()) {
                u.setuName(queryResult.getString(1));
                u.setuPass(queryResult.getString(2));
                u.setRealName(queryResult.getString(3));
                u.setEmail(queryResult.getString(4));
                u.setPhoneNum(queryResult.getString(5));
                u.setAddress(queryResult.getString(6));
                u.setUserType(queryResult.getString(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return u;
    }

    public boolean editUser(User u) {
        String originalUser = "SELECT * FROM USER WHERE NRIC = '" + u.getuName() + "';";

        String updateColumn = "";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(originalUser);

            while (queryResult.next()) {
                Statement updateStatement = connectDB.createStatement();

                if (!(u.getuPass().equals(queryResult.getString(2)))) {
                    updateColumn = "UPDATE USER SET pass = '" + u.getuPass() + "' WHERE NRIC = '" + u.getuName() + "';";
                    updateStatement.execute(updateColumn);
                }

                if (!(u.getRealName().equals(queryResult.getString(3)))) {
                    updateColumn = "UPDATE USER SET name = '" + u.getRealName() + "' WHERE NRIC = '" + u.getuName() + "';";
                    updateStatement.execute(updateColumn);
                }

                if (!(u.getEmail().equals(queryResult.getString(4)))) {
                    updateColumn = "UPDATE USER SET email = '" + u.getEmail() + "' WHERE NRIC = '" + u.getuName() + "';";
                    updateStatement.execute(updateColumn);
                }

                if (!(u.getPhoneNum().equals(queryResult.getString(5)))) {
                    updateColumn = "UPDATE USER SET phoneNum = '" + u.getPhoneNum() + "' WHERE NRIC = '" + u.getuName() + "';";
                    updateStatement.execute(updateColumn);
                }

                if (!(u.getAddress().equals(queryResult.getString(6)))) {
                    updateColumn = "UPDATE USER SET address = '" + u.getAddress() + "' WHERE NRIC = '" + u.getuName() + "';";
                    updateStatement.execute(updateColumn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return false;
        }
        return true;
    }
}
