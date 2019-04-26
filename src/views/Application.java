/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.GraduateController;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Graduate;
import models.Transaction;

/**
 *
 * @author vmascareno
 */
public class Application {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1406);
            while (true) {
                Socket socket = server.accept();
                ObjectInputStream inputStream = new ObjectInputStream(
                        new BufferedInputStream(
                                socket.getInputStream()
                        )
                );
                Transaction transaction = (Transaction) inputStream.readObject();
                ObjectOutputStream outputStream;
                switch (transaction.getOperation()) {
                    case ADD:
                        System.out.println("ADD");
                        String[] dataNewGraduate = transaction.getData().split("\t");
                        Graduate newGraduate = new Graduate(
                                Integer.parseInt(dataNewGraduate[0]),
                                dataNewGraduate[1],
                                dataNewGraduate[2],
                                dataNewGraduate[3],
                                dataNewGraduate[4],
                                Boolean.valueOf(dataNewGraduate[5]),
                                dataNewGraduate[6],
                                dataNewGraduate[7],
                                dataNewGraduate[8],
                                dataNewGraduate[9]
                        );
                        boolean graduateSaved = GraduateController.add(newGraduate);
                        outputStream = new ObjectOutputStream(
                                new BufferedOutputStream(
                                        socket.getOutputStream()
                                )
                        );
                        outputStream.writeBoolean(graduateSaved);
                        outputStream.close();
                        break;
                    case GET_BY_CONTROL_NUMBER:
                        System.out.println("GET_BY_CONTROL_NUMBER");
                        int controlNumberForGetGraduate = Integer.parseInt(
                                transaction.getData().split("\t")[0]
                        );
                        Graduate graduate = GraduateController.get(controlNumberForGetGraduate);
                        outputStream = new ObjectOutputStream(
                                new BufferedOutputStream(
                                        socket.getOutputStream()
                                )
                        );

                        if (graduate == null) {
                            outputStream.writeUTF("");
                        } else {
                            outputStream.writeUTF(graduate.toString());
                        }
                        outputStream.close();
                        break;
                    case GET_BY_CAREER:
                        System.out.println("GET_BY_CAREER");
                        String[] graduatesRelatedToCareer = GraduateController.getByCareer(transaction.getData().split("\t")[2]);
                        outputStream = new ObjectOutputStream(
                                new BufferedOutputStream(
                                        socket.getOutputStream()
                                )
                        );

                        outputStream.writeObject(graduatesRelatedToCareer);
                        outputStream.close();
                        break;
                    case GET_ALL:
                        System.out.println("GET_ALL");
                        String[] graduates = GraduateController.getAll();
                        outputStream = new ObjectOutputStream(
                                new BufferedOutputStream(
                                        socket.getOutputStream()
                                )
                        );

                        outputStream.writeObject(graduates);
                        outputStream.close();
                        break;
                    case DELETE:
                        System.out.println("DELETE");
                        int controlNumberForDeleteFraduate = Integer.parseInt(
                                transaction.getData().split("\t")[0]
                        );

                        boolean graduateRemoved = GraduateController.delete(
                                controlNumberForDeleteFraduate
                        );
                        outputStream = new ObjectOutputStream(
                                new BufferedOutputStream(
                                        socket.getOutputStream()
                                )
                        );
                        outputStream.writeBoolean(graduateRemoved);
                        outputStream.close();
                        break;
                    case UPDATE:
                        System.out.println("UPDATE");
                        String[] dataUpdateGraduate = transaction.getData().split("\t");
                        Graduate updateGraduate = new Graduate(
                                Integer.parseInt(dataUpdateGraduate[0]),
                                dataUpdateGraduate[1],
                                dataUpdateGraduate[2],
                                dataUpdateGraduate[3],
                                dataUpdateGraduate[4],
                                Boolean.valueOf(dataUpdateGraduate[5]),
                                dataUpdateGraduate[6],
                                dataUpdateGraduate[7],
                                dataUpdateGraduate[8],
                                dataUpdateGraduate[9]
                        );
                        boolean graduateUpdated = GraduateController.update(updateGraduate.getControlNumber(), updateGraduate);
                        outputStream = new ObjectOutputStream(
                                new BufferedOutputStream(
                                        socket.getOutputStream()
                                )
                        );
                        outputStream.writeBoolean(graduateUpdated);
                        outputStream.close();
                        break;
                }
            }
        } catch (EOFException e) {
            System.err.println(e.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
