/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.GraduateSchema;
import java.util.List;
import models.Graduate;

/**
 *
 * @author vmascareno
 */
public class GraduateController {

    public static boolean add(Graduate graduate) {
        boolean exists = GraduateSchema.get(graduate.getControlNumber()) != null;
        return exists ? false : GraduateSchema.add(graduate);
    }

    public static boolean delete(int controlNumber) {
        boolean exists = GraduateSchema.get(controlNumber) != null;
        return !exists ? false : GraduateSchema.delete(controlNumber);
    }

    public static boolean update(int controlNumber, Graduate graduate) {
        return GraduateSchema.update(controlNumber, graduate);
    }

    public static Graduate get(int controlNumber) {
        return GraduateSchema.get(controlNumber);
    }

    public static List<Graduate> getAll() {
        return GraduateSchema.getAll();
    }
}
