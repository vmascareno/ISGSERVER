/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import models.Graduate;

/**
 *
 * @author vmascareno
 */
public class GraduateSchema {

    private static final List<Graduate> GRADUATES = new ArrayList();

    public static boolean add(Graduate graduate) {
        GRADUATES.add(graduate);

        return GRADUATES.contains(graduate);
    }

    public static boolean delete(int controlNumber) {
        Iterator iterator = GRADUATES.iterator();
        while (iterator.hasNext()) {
            Graduate graduate = (Graduate) iterator.next();
            if (graduate.getControlNumber() == controlNumber) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    public static boolean update(int controlNumber, Graduate newGraduate) {
        for (Graduate graduate : GRADUATES) {
            if (graduate.getControlNumber() == controlNumber) {
                graduate.setName(newGraduate.getName());
                graduate.setCareer(newGraduate.getCareer());
                graduate.setEgresedAt(newGraduate.getEgresedAt());
                graduate.setSex(newGraduate.getSex());
                graduate.setIsWorking(newGraduate.isIsWorking());
                graduate.setWorkType(newGraduate.getWorkType());
                graduate.setPhoneNumber(newGraduate.getPhoneNumber());
                graduate.setEmail(newGraduate.getEmail());
                graduate.setAddress(newGraduate.getAddress());

                return true;
            }
        }

        return false;

    }

    public static List<Graduate> getAll() {
        return GraduateSchema.GRADUATES;
    }

    public static Graduate get(int controlNumber) {
        if (GRADUATES.isEmpty()) {
            return null;
        }

        for (Graduate graduate : GRADUATES) {
            if (graduate.getControlNumber() == controlNumber) {
                return graduate;
            }
        }

        return null;
    }

}
