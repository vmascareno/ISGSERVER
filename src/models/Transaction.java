/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import enums.Operation;
import java.io.Serializable;

/**
 *
 * @author vmascareno
 */
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1113799434508676095L;

    private final Operation operation;
    private final String data;

    public Transaction(Operation operation, String data) {
        this.operation = operation;
        this.data = data;
    }

    public Operation getOperation() {
        return operation;
    }

    public String getData() {
        return data;
    }

}
