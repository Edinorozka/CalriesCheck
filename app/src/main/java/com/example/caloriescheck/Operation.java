package com.example.caloriescheck;

import com.example.caloriescheck.dto.User;

import java.util.Calendar;
import java.util.Date;

public class Operation{
    public Operation(){}

    public int sumKal(User user){
        float result;
        float active = user.getActivity() == 0 ? 1f : user.getActivity() == 1 ? 1.3f : 1.5f;
        Calendar userDate = Calendar.getInstance();
        userDate.setTime(user.getBirthday());
        Calendar nowDate = Calendar.getInstance();
        nowDate.setTime(new Date());
        int age = nowDate.get(Calendar.YEAR) - userDate.get(Calendar.YEAR);

        if (user.isGender()) {
            if (age <= 30){
                result =  (0.063f * user.getWeightNow() + 2.896f) * 240 * active;
            } else if(age <= 60){
                result =  (0.048f * user.getWeightNow() + 3.653f) * 240 * active;
            } else {
                result =  (0.049f * user.getWeightNow() + 2.459f) * 240 * active;
            }
        } else {
            if (age <= 30){
                result =  (0.062f * user.getWeightNow() + 2.036f) * 240 * active;
            } else if(age <= 60){
                result =  (0.034f * user.getWeightNow() + 3.538f) * 240 * active;
            } else {
                result =  (0.049f * user.getWeightNow() + 2.755f) * 240 * active;
            }
        }
        switch(user.getPurpose()){
            case 1:
                result -= 300;
                if (result < user.getWeightNow()/0.45f * 8f) result = user.getWeightNow()/0.45f * 8f;
                return (int) result;
            case 2:
                    result += 500;
                    if (result < user.getWeightNow()/0.45f * 8f) result = user.getWeightNow()/0.45f * 8f;
                    return (int) result;
            case 3:
                return (int) result;
        }
        throw new RuntimeException("При расчёте дневной нормы калорий прризошла ошибка");
    }

    public int sumBel(User user){
        int kolvoBel = 0;
        switch(user.getPurpose()){
            case 1:
            case 2:
                kolvoBel = (int) user.getWeightNow();
                break;
            case 3:
                kolvoBel = (int) (1.7 * user.getWeightNow());
                break;
        }
        return kolvoBel;}

    public int sumYGL(User user){
        int kolvoBel = 0;
        switch(user.getPurpose()){
            case 1:
            case 2:
                kolvoBel = (int) (3 * user.getWeightNow());
                break;
            case 3:
                kolvoBel = (int) (5 * user.getWeightNow());
                break;
        }
        return kolvoBel;}

    public int sumGir(User user){
        int kolvoBel = 0;
        switch(user.getPurpose()){
            case 1:
            case 2:
                kolvoBel = (int) (0.8 * user.getWeightNow());
                break;
            case 3:
                kolvoBel = (int) user.getWeightNow();
                break;
        }
        return kolvoBel;
    }
}
